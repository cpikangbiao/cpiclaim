package com.cpi.claim.service.utility.statistics;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.*;
import com.cpi.claim.service.bean.statistics.CaseStatsBean;
import com.cpi.claim.service.common.Contants;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.dto.common.CompanyDTO;
import com.cpi.claim.service.dto.common.VesselDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import com.cpi.claim.service.utility.ClaimToolUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for executing complex queries for VesselCase entities in the database.
 * The main input is a {@link VesselCaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VesselCaseDTO} or a {@link Page} of {@link VesselCaseDTO} which fulfills the criteria.
 */
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
            if (company != null && company.getCompanyName() != null)
                parameter.put("companyName", company.getCompanyCode() + " " + company.getCompanyName());
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
        parameter.put("claimAmount", caseStatsBean.getClaimAmount());
        parameter.put("estimateAmount", caseStatsBean.getEstimateAmount()	);
        parameter.put("memberPaymentAmount", caseStatsBean.getMemberPaymentAmount());
        parameter.put("correspondentFee", caseStatsBean.getCorrespondentFee());
        parameter.put("surveyorFee", caseStatsBean.getSurveyorFee());
        parameter.put("lawyerFee", caseStatsBean.getLawyerFee());
        parameter.put("otherFee", caseStatsBean.getOtherFee());
        parameter.put("paymentAmount", caseStatsBean.getPaymentAmount());
        parameter.put("benifitRatio", caseStatsBean.getBenifitRatio());
        parameter.put("costRatio", caseStatsBean.getCostRatio());
        parameter.put("thirdpartAmount", caseStatsBean.getThirdpartAmount());
        parameter.put("totalCost", caseStatsBean.getTotalCost());

        return parameter;
    }

    private String createJasperFilePath(Long cpiInsuranceTypeId, Integer language, Integer operateType, Long caseStatusId) {
        String jasperFilePath = null;

        if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_PI)) {
            //By Case
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase.jasper";
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase_en.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase_en.jasper";
            }
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_FDD)) {
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase_fdd.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase_fdd.jasper";
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase_en_fdd.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase_en_fdd.jasper";
            }
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_TCL)) {
            if (language.equals(Contants.CONTANT_LANGUAGE_CHINESE)) {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase_tcl.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase_tcl.jasper";
            } else {
                if (operateType.equals(Contants.CONTANT_OUTER_TYPE_OUTER) || caseStatusId.equals(CaseStatusType.CASE_STATUS_OPEN))
                    jasperFilePath = "CaseStatsByMemberOuterCase_en_tcl.jasper";
                else
                    jasperFilePath = "CaseStatsByMemberInnerCase_en_tcl.jasper";
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

        List<Long> insuredVesselIds = claimToolUtility.insuredVesselRepository.getIdsForYearAndCompany(
            fromYear,
            endYear,
            companyId,
            cpiInsuranceTypeId
        );

        CpiInsuranceType cpiInsuranceType = null;
        if (cpiInsuranceTypeId != null) {
            cpiInsuranceType = cpiInsuranceTypeRepository.getOne(CpiInsuranceType.CPI_INSURANCE_PI);
        }

        CaseStatusType caseStatus = null;
        if (caseStatusId != null) {
            caseStatus = caseStatusTypeRepository.getOne(caseStatusId);
        }

        List<VesselCase> vesselCases = vesselCaseRepository.findAllCaseForStatics(
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
