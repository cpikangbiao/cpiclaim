package com.cpi.claim.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseClaimBillDeleteLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;

import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillDeleteLogMapper;

/**
 * Service for executing complex queries for CaseClaimBillDeleteLog entities in the database.
 * The main input is a {@link CaseClaimBillDeleteLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimBillDeleteLogDTO} or a {@link Page} of {@link CaseClaimBillDeleteLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimBillDeleteLogQueryService extends QueryService<CaseClaimBillDeleteLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogQueryService.class);

    private final CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository;

    private final CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper;

    public CaseClaimBillDeleteLogQueryService(CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository, CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper) {
        this.caseClaimBillDeleteLogRepository = caseClaimBillDeleteLogRepository;
        this.caseClaimBillDeleteLogMapper = caseClaimBillDeleteLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseClaimBillDeleteLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseClaimBillDeleteLogDTO> findByCriteria(CaseClaimBillDeleteLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseClaimBillDeleteLog> specification = createSpecification(criteria);
        return caseClaimBillDeleteLogMapper.toDto(caseClaimBillDeleteLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseClaimBillDeleteLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseClaimBillDeleteLogDTO> findByCriteria(CaseClaimBillDeleteLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseClaimBillDeleteLog> specification = createSpecification(criteria);
        return caseClaimBillDeleteLogRepository.findAll(specification, page)
            .map(caseClaimBillDeleteLogMapper::toDto);
    }

    /**
     * Function to convert CaseClaimBillDeleteLogCriteria to a {@link Specification}
     */
    private Specification<CaseClaimBillDeleteLog> createSpecification(CaseClaimBillDeleteLogCriteria criteria) {
        Specification<CaseClaimBillDeleteLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseClaimBillDeleteLog_.id));
            }
            if (criteria.getOperateType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateType(), CaseClaimBillDeleteLog_.operateType));
            }
            if (criteria.getOperateUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateUser(), CaseClaimBillDeleteLog_.operateUser));
            }
            if (criteria.getOperateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateDate(), CaseClaimBillDeleteLog_.operateDate));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCaseClaimBillId(), CaseClaimBillDeleteLog_.caseClaimBill, CaseClaimBill_.id));
            }
        }
        return specification;
    }

}
