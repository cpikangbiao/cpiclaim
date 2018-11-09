package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
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

/**
 * Service for executing complex queries for CaseClaimBill entities in the database.
 * The main input is a {@link CaseClaimBillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimBillDTO} or a {@link Page} of {@link CaseClaimBillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimBillQueryExtService extends QueryService<CaseClaimBill> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillQueryExtService.class);

    private final CaseClaimBillRepository caseClaimBillRepository;

    private final CaseClaimBillMapper caseClaimBillMapper;

    public CaseClaimBillQueryExtService(CaseClaimBillRepository caseClaimBillRepository, CaseClaimBillMapper caseClaimBillMapper) {
        this.caseClaimBillRepository = caseClaimBillRepository;
        this.caseClaimBillMapper = caseClaimBillMapper;
    }





}
