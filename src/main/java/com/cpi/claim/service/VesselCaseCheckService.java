/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 上午9:36
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.dto.common.CompanyDTO;
import com.cpi.claim.service.dto.common.CompanyNameHistoryDTO;
import com.cpi.claim.service.dto.common.VesselDTO;
import com.cpi.claim.service.dto.common.VesselNameHistoryDTO;
import com.cpi.claim.service.dto.uaa.UserDTO;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import com.cpi.claim.service.dto.uw.MemberDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import com.cpi.claim.service.user.UserService;
import com.cpi.claim.service.utility.ClaimToolUtility;
import com.cpi.claim.service.utility.generate.CaseCodeGenerateUtility;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VesselCaseCheckService extends QueryService<VesselCase> {

    private final Logger log = LoggerFactory.getLogger(VesselCaseCheckService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Autowired
    private UserService userService;

    public VesselCaseCheckService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    @Transactional(readOnly = true)
    public Boolean checkEstimateIsZero(Long vesselCaseId) {
        VesselCase vesselCase = claimToolUtility.vesselCaseRepository.getOne(vesselCaseId);
        List<VesselSubCase> vesselSubCases = claimToolUtility.vesselSubCaseRepository.findAllByVesselCase(vesselCase);
        BigDecimal estimateAmount = new BigDecimal(0.0);
        for (VesselSubCase vesselSubCase : vesselSubCases) {
            CaseEstimate caseEstimate = claimToolUtility.caseEstimateRepository.findFirstBySubcaseOrderByEstimateDateDesc(vesselSubCase);

            if (caseEstimate != null && caseEstimate.getEstimateEntityFee() != null) {
                estimateAmount =  estimateAmount.add(caseEstimate.getEstimateEntityFee());
            }
            if (caseEstimate != null && caseEstimate.getEstimateCostFee() != null) {
                estimateAmount =  estimateAmount.add(caseEstimate.getEstimateCostFee());
            }
        }

        if (estimateAmount.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }

    //1.4.8 	2018-08-10 	理赔部新加功能：在有担保出具的案件中，必须将担保的Cancel Date输入方可关案，以满足业务管理要求。 	新需求开发 	理赔部 	严莉芳
    @Transactional(readOnly = true)
    public Boolean checkGuaranteeNoCancelDate(Long vesselCaseId) {
        VesselCase vesselCase = claimToolUtility.vesselCaseRepository.getOne(vesselCaseId);
        List<VesselSubCase> vesselSubCases = claimToolUtility.vesselSubCaseRepository.findAllByVesselCase(vesselCase);
        Boolean guaranteeNoCancelDate = true;

        for (VesselSubCase vesselSubCase : vesselSubCases) {
            List<CaseGuarantee> caseGuarantees = claimToolUtility.caseGuaranteeRepository.findAllBySubcase(vesselSubCase);
            for (CaseGuarantee caseGuarantee : caseGuarantees) {
                if (caseGuarantee.getCancelDate() == null) {
                    guaranteeNoCancelDate = false;
                }
            }
        }

        return guaranteeNoCancelDate;
    }


    @Transactional
    public void setDefaultValue(VesselCaseDTO vesselCaseDTO) {

        InsuredVesselDTO insuredVesselDTO =
            claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselCaseDTO.getInsuredVesselId());
        MemberDTO memberDTO = claimToolUtility.memberRepository.findMemberByID(insuredVesselDTO.getMemberId());
        vesselCaseDTO.setCaseYear(memberDTO.getMemberYear());
        vesselCaseDTO.setCpiInsuranceTypeId(insuredVesselDTO.getCpiInsuranceTypeId());
        vesselCaseDTO.setNumberId(
            vesselCaseRepository.findMaxNumberIdByCaseYearAndCpiInsuranceTypeId(
                vesselCaseDTO.getCaseYear(), vesselCaseDTO.getCpiInsuranceTypeId()) + 1
        );

        vesselCaseDTO.setCaseCode(
            CaseCodeGenerateUtility.createCaseCode(
                vesselCaseDTO.getCpiInsuranceTypeId(),
                vesselCaseDTO.getCaseYear(),
                vesselCaseDTO.getNumberId()
        ));


        Optional<UserDTO> optional = userService.getCurrentUser();
        if (optional.isPresent()) {
            vesselCaseDTO.setAssignedHandler(optional.get().getId());
            vesselCaseDTO.setAssignedTime(Instant.now());
            vesselCaseDTO.setRegisteredHandler(optional.get().getId());
            vesselCaseDTO.setRegisteredDate(Instant.now());
        }

        CaseStatusType caseStatusType = claimToolUtility.caseStatusTypeRepository.getOne(CaseStatusType.CASE_STATUS_OPEN);
        vesselCaseDTO.setCaseStatusId(CaseStatusType.CASE_STATUS_OPEN);
        vesselCaseDTO.setCaseStatusCaseStatusName(caseStatusType.getCaseStatusName());
        vesselCaseDTO.setCloseDate(null);
        vesselCaseDTO.setCloseHandler(null);

//        vesselCaseDTO.setVoyageNo("");
//        vesselCaseDTO.setLoadingPort(0L);
//        vesselCaseDTO.setLoadingDate(new Instant());
//        vesselCaseDTO.setDischargingPort(0L);
//        vesselCaseDTO.setDischargingDate(new Instant());
//        vesselCaseDTO.setLimitTime(new Instant());
//        vesselCaseDTO.setCpDate(new Instant());
//        vesselCaseDTO.setCpType("");
//        vesselCaseDTO.setArrestVessel("");
//        vesselCaseDTO.setJurisdiction(0L);
//        vesselCaseDTO.setApplicableLaw(0L);
//
//        vesselCaseDTO.setRemark("");
//        vesselCaseDTO.setSettlementAmount(new BigDecimal("0"));
//        vesselCaseDTO.setSettlementDate(new Instant());

//
//        vesselCaseDTO.setSettlementModeId(0L);
//        vesselCaseDTO.setSettlementModeSettlementModeName("");

    }

    @Transactional
    public void checkCompanyAndVesselName(VesselCaseDTO vesselCaseDTO) {
        log.debug("check Company And VesselName for  VesselCaseDTO: {}", vesselCaseDTO);

        if (vesselCaseDTO.getCaseDate() != null
            && vesselCaseDTO.getInsuredVesselId() != null) {
            //自动检查案件发生时间，自动判断在案件发生时间的时候曾用船名名称。
            if (vesselCaseDTO.getCompanyName() == null) {
                InsuredVesselDTO insuredVesselDTO = claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselCaseDTO.getInsuredVesselId());
                MemberDTO memberDTO = claimToolUtility.memberRepository.findMemberByID(insuredVesselDTO.getMemberId());
                CompanyDTO companyDTO = claimToolUtility.companyRepository.findCompanyByID(memberDTO.getCompany());
                CompanyNameHistoryDTO companyNameHistory =
                    claimToolUtility.companyNameHistoryRepository.findFitCompanyNameByCompanyId(memberDTO.getCompany(), vesselCaseDTO.getCaseDate());
                if (companyNameHistory != null) {
                    vesselCaseDTO.setCompanyName(companyNameHistory.getCompanyName());
                    vesselCaseDTO.setCompanyChineseName(companyNameHistory.getCompanyChineseName());
                } else {
                    vesselCaseDTO.setCompanyName(companyDTO.getCompanyName());
                    vesselCaseDTO.setCompanyChineseName(companyDTO.getCompanyNameChinese());
                }
            }

            //自动检查案件发生时间，自动判断在案件发生时间的时候曾用公司名称。
            if (vesselCaseDTO.getVesselName() == null
                && vesselCaseDTO.getInsuredVesselId() != null) {
                InsuredVesselDTO insuredVesselDTO = claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselCaseDTO.getInsuredVesselId());
                VesselDTO vesselDTO = claimToolUtility.vesselRepository.findVesselByID(insuredVesselDTO.getVesselId());

                VesselNameHistoryDTO vesselNameHistory =
                    claimToolUtility.vesselNameHistoryRepository.findFitVesselNameByVesselId(vesselDTO.getId(), vesselCaseDTO.getCaseDate());

                if (vesselNameHistory != null) {
                    vesselCaseDTO.setVesselName(vesselNameHistory.getVesselName());
                    vesselCaseDTO.setVesselChineseName(vesselNameHistory.getVesselChineseName());
                } else {
                    vesselCaseDTO.setVesselName(vesselDTO.getVesselName());
                    vesselCaseDTO.setVesselChineseName(vesselDTO.getVesselChineseName());
                }
            }
        }
    }

    @Transactional
    public void checkReinsures(VesselCaseDTO vesselCaseDTO) {
//        List<InsuredVesselReinsureVO> ivreinsures =
//            new InsuredVesselReinsureDAO().getListByInsuredVessel(vesselCaseDTO.getInsuredVesselId(), null);
//        if (vesselCaseDTO.getCaseDate() != null) {
//            if (ivreinsures.size() > 0) {
//                for (InsuredVesselReinsureVO ivreinsure : ivreinsures) {
//                    if (vesselCaseDTO.getCaseDate() != null
//                        && !vesselCaseDTO.getCaseDate().before(ivreinsure.getEntryDate())
//                        && !vesselCaseDTO.getCaseDate().after(ivreinsure.getWithdrawDate())) {
//                        vesselCaseDTO.setReinsureId(ivreinsure.getReinsure());
//                        vesselCaseDTO.setDeduct(ivreinsure.getDeductibles());
//                    }
//                }
//            } else {
//                vesselCaseDTO.setReinsureId(null);
//                vesselCaseDTO.setDeduct(null);
//            }
//        } else {
//            vesselCaseDTO.setReinsureId(null);
//            vesselCaseDTO.setDeduct(null);
//        }
    }


}
