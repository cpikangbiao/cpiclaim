package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CaseClaimBillExtService extends QueryService<CaseClaimBill> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillExtService.class);

    private final CaseClaimBillRepository caseClaimBillRepository;

    private final CaseClaimBillMapper caseClaimBillMapper;

    public CaseClaimBillExtService(CaseClaimBillRepository caseClaimBillRepository, CaseClaimBillMapper caseClaimBillMapper) {
        this.caseClaimBillRepository = caseClaimBillRepository;
        this.caseClaimBillMapper = caseClaimBillMapper;
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdByYear(String year) {
        log.debug("find Max NumberId By year : {}", year);
        Integer maxNumberId = caseClaimBillRepository.findMaxNumberIdByYear(year);
        return maxNumberId + 1;
    }


    @Transactional(readOnly = true)
    public List<CaseClaimBillDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseClaimBill> caseClaimBills = caseClaimBillRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseClaimBillMapper.toDto(caseClaimBills);
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimBillDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseClaimBill> caseClaimBills = caseClaimBillRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseClaimBills.map(caseClaimBillMapper::toDto);
    }
}
