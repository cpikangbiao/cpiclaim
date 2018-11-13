package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillPrintLog;
import com.cpi.claim.domain.CaseClaimBillPrintLog_;
import com.cpi.claim.domain.CaseClaimBill_;
import com.cpi.claim.repository.CaseClaimBillPrintLogRepository;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillPrintLogMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CaseClaimBillPrintLogExtService extends QueryService<CaseClaimBillPrintLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogExtService.class);

    private final CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository;

    private final CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper;

    @Autowired
    private CaseClaimBillRepository caseClaimBillRepository;

    public CaseClaimBillPrintLogExtService(CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository, CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper) {
        this.caseClaimBillPrintLogRepository = caseClaimBillPrintLogRepository;
        this.caseClaimBillPrintLogMapper = caseClaimBillPrintLogMapper;
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimBillPrintLogDTO> findAllByCaseClaimBill(Long caseClaimBillId, Pageable page) {
        log.debug("find by caseClaimBill : {}", caseClaimBillId);
        CaseClaimBill caseClaimBill = caseClaimBillRepository.getOne(caseClaimBillId);

        return caseClaimBillPrintLogRepository.findAllByCaseClaimBill(caseClaimBill, page)
            .map(caseClaimBillPrintLogMapper::toDto);
    }

}
