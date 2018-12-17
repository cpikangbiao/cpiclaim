/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
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

package com.cpi.claim.service.utility.statistics;

import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseStatusTypeRepository;
import com.cpi.claim.repository.CpiInsuranceTypeRepository;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.VesselCaseQueryExtService;
import com.cpi.claim.service.bean.statistics.CaseStatsBean;
import com.cpi.claim.service.common.Contants;
import com.cpi.claim.service.dto.common.CompanyDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import com.cpi.claim.service.utility.ClaimToolUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StatisticsForCaseService {

    private final Logger log = LoggerFactory.getLogger(StatisticsForCaseService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Autowired
    private CpiInsuranceTypeRepository cpiInsuranceTypeRepository;

    @Autowired
    private CaseStatusTypeRepository caseStatusTypeRepository;

    @Autowired
    private VesselCaseQueryExtService vesselCaseQueryExtService;

    public StatisticsForCaseService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    public byte[] createCaseStatsPDFFile(
        String fromYear,
        String endYear,
        Long companyId,
        Long cpiInsuranceTypeId,
        Long caseStatusId,
        Instant caseDateFrom,
        Instant caseDateTo,
        Instant registeredDateFrom,
        Instant registeredDateTo,
        Instant closeDateFrom,
        Instant closeDateTo,
        Integer language,
        Integer operateType
    ) {
        ResponseEntity<byte[]> responseEntity  = new ResponseEntity(HttpStatus.OK);

        String jasperFilePath = createJasperFilePath(cpiInsuranceTypeId, language, operateType, caseStatusId) ;

        CaseStatsBean caseStatsBean = getCaseStatsBean(
             fromYear,
             endYear,
             companyId,
             cpiInsuranceTypeId,
             caseStatusId,
             caseDateFrom,
             caseDateTo,
             registeredDateFrom,
             registeredDateTo,
             closeDateFrom,
             closeDateTo,
             language);

        Map<String, Object> parameter = createParameter(caseStatsBean);
        parameter.put("fromYear", fromYear);
        parameter.put("toYear", endYear);
        if (companyId != null) {
            CompanyDTO company = claimToolUtility.companyRepository.findCompanyByID(companyId);
            if (company != null && company.getCompanyName() != null) {
                parameter.put("companyName", company.getCompanyCode() + " " + company.getCompanyName());
            }
        }

        if (caseStatsBean != null) {
            responseEntity  = claimToolUtility.jasperReportUtility.processPDF(jasperFilePath, parameter);
        }

        return responseEntity.getBody();
    }

    public String createCaseStatsPDFFileName(
        String fromYear,
        String endYear,
        Long companyId
    ) {
        StringBuilder fileNameString = new StringBuilder();
        fileNameString.append("CaseStats_");
        if (companyId != null) {
            CompanyDTO company = claimToolUtility.companyRepository.findCompanyByID(companyId);
            if (company != null && company.getCompanyName() != null) {
                fileNameString.append(company.getCompanyCode()).append(" ").append(company.getCompanyName()).append(" ");
            }
        }
        fileNameString.append(fromYear).append("_").append(endYear).append(".pdf");

        return fileNameString.toString();
    }

    private Map<String, Object> createParameter(CaseStatsBean caseStatsBean) {
        Map<String, Object> parameter = new HashMap<String, Object>();

        parameter.put("caseNum", caseStatsBean.getCaseNum());
        parameter.put("subCaseNum", caseStatsBean.getSubCaseNum());
        parameter.put("claimAmount", caseStatsBean.getClaimAmount().doubleValue());
        parameter.put("estimateAmount", caseStatsBean.getEstimateAmount().doubleValue()	);
        parameter.put("memberPaymentAmount", caseStatsBean.getMemberPaymentAmount().doubleValue());
        parameter.put("correspondentFee", caseStatsBean.getCorrespondentFee().doubleValue());
        parameter.put("surveyorFee", caseStatsBean.getSurveyorFee().doubleValue());
        parameter.put("lawyerFee", caseStatsBean.getLawyerFee().doubleValue());
        parameter.put("otherFee", caseStatsBean.getOtherFee().doubleValue());
        parameter.put("paymentAmount", caseStatsBean.getPaymentAmount().doubleValue());
        parameter.put("benifitRatio", caseStatsBean.getBenifitRatio().doubleValue());
        parameter.put("costRatio", caseStatsBean.getCostRatio().doubleValue());
        parameter.put("thirdpartAmount", caseStatsBean.getThirdpartAmount().doubleValue());
        parameter.put("totalCost", caseStatsBean.getTotalCost().doubleValue());

//        parameter.put("datasource", caseStatsBean.getCaseStatsPerCaseBeans());
//
//        JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(caseStatsBean.getCaseStatsPerCaseBeans().toArray()) ;
//        parameter.put("JRDataSource", dataSource);

        parameter.put("datasource", caseStatsBean.getCaseStatsPerCaseBeans());

        return parameter;
    }

    private String createJasperFilePath(Long cpiInsuranceTypeId, Integer language, Integer operateType, Long caseStatusId) {
        String jasperFilePath = null;

        if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_PI)) {
            //By Case
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase.jasper";
                }
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase_en.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase_en.jasper";
                }
            }
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_FDD)) {
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase_fdd.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase_fdd.jasper";
                }
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase_en_fdd.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase_en_fdd.jasper";
                }
            }
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_TCL)) {
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase_tcl.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase_tcl.jasper";
                }
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN)) {
                    jasperFilePath = "CaseStatsByMemberOuterCase_en_tcl.jasper";
                } else {
                    jasperFilePath = "CaseStatsByMemberInnerCase_en_tcl.jasper";
                }
            }
        }

        return jasperFilePath;
    }

    public CaseStatsBean getCaseStatsBean(
                                String fromYear,
                                String endYear,
                                Long companyId,
                                Long cpiInsuranceTypeId,
                                Long caseStatusId,
                                Instant caseDateFrom,
                                Instant caseDateTo,
                                Instant registeredDateFrom,
                                Instant registeredDateTo,
                                Instant closeDateFrom,
                                Instant closeDateTo,
                                Integer language
        ) {
        CaseStatsBean caseStatsBean = new CaseStatsBean();

        List<Long> insuredVesselIds = new ArrayList<>();
        if (fromYear != null && endYear != null) {
            insuredVesselIds = claimToolUtility.insuredVesselRepository.getIdsForYearAndCompany(
                fromYear,
                endYear,
                companyId,
                cpiInsuranceTypeId
            );
        }


        CpiInsuranceType cpiInsuranceType = null;
        if (cpiInsuranceTypeId != null) {
            cpiInsuranceType = cpiInsuranceTypeRepository.getOne(CpiInsuranceType.CPI_INSURANCE_PI);
        }

        CaseStatusType caseStatus = null;
        if (caseStatusId != null) {
            caseStatus = caseStatusTypeRepository.getOne(caseStatusId);
        }

        List<VesselCase> vesselCases = vesselCaseQueryExtService.getListForStats(
                                                         insuredVesselIds,
                                                        cpiInsuranceType,
                                                        caseStatus,
                                                        caseDateFrom,
                                                        caseDateTo,
                                                        registeredDateFrom,
                                                        registeredDateTo,
                                                        closeDateFrom,
                                                        closeDateTo
        );

        caseStatsBean.init(vesselCases, language, claimToolUtility);

        return caseStatsBean;
    }



}
