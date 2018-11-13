package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import com.cpi.claim.domain.CaseClaimBillApprovalLog_;
import com.cpi.claim.domain.CaseClaimBill_;
import com.cpi.claim.repository.CaseClaimBillApprovalLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillApprovalLogMapper;
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
public class CaseClaimBillApprovalLogExtService extends QueryService<CaseClaimBillApprovalLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogExtService.class);

    private final CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository;

    private final CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper;

    public CaseClaimBillApprovalLogExtService(CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository, CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper) {
        this.caseClaimBillApprovalLogRepository = caseClaimBillApprovalLogRepository;
        this.caseClaimBillApprovalLogMapper = caseClaimBillApprovalLogMapper;
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimBillApprovalLogDTO> findAllByCaseClaimBill(CaseClaimBill caseClaimBill, Pageable page) {
        log.debug("find by caseClaimBill : {}", caseClaimBill);
        return caseClaimBillApprovalLogRepository.findAllByCaseClaimBill(caseClaimBill, page)
            .map(caseClaimBillApprovalLogMapper::toDto);
    }

}
