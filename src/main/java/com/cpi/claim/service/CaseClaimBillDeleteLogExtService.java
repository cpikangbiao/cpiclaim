package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillDeleteLog;
import com.cpi.claim.domain.CaseClaimBillDeleteLog_;
import com.cpi.claim.repository.CaseClaimBillDeleteLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillDeleteLogMapper;
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
public class CaseClaimBillDeleteLogExtService extends QueryService<CaseClaimBillDeleteLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogExtService.class);

    private final CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository;

    private final CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper;

    public CaseClaimBillDeleteLogExtService(CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository, CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper) {
        this.caseClaimBillDeleteLogRepository = caseClaimBillDeleteLogRepository;
        this.caseClaimBillDeleteLogMapper = caseClaimBillDeleteLogMapper;
    }

//    @Transactional(readOnly = true)
//    public Page<CaseClaimBillDeleteLogDTO> findAllByCaseClaimBill(CaseClaimBill caseClaimBill, Pageable page) {
//        log.debug("find by caseClaimBill : {}", caseClaimBill);
//        return caseClaimBillDeleteLogRepository.findAllByCaseClaimBill(caseClaimBill, page)
//            .map(caseClaimBillDeleteLogMapper::toDto);
//    }

}
