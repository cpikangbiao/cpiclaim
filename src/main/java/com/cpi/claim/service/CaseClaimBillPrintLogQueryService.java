package com.cpi.claim.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.cpi.claim.domain.CaseClaimBillPrintLog;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseClaimBillPrintLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillPrintLogMapper;

/**
 * Service for executing complex queries for {@link CaseClaimBillPrintLog} entities in the database.
 * The main input is a {@link CaseClaimBillPrintLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimBillPrintLogDTO} or a {@link Page} of {@link CaseClaimBillPrintLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimBillPrintLogQueryService extends QueryService<CaseClaimBillPrintLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogQueryService.class);

    private final CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository;

    private final CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper;

    public CaseClaimBillPrintLogQueryService(CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository, CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper) {
        this.caseClaimBillPrintLogRepository = caseClaimBillPrintLogRepository;
        this.caseClaimBillPrintLogMapper = caseClaimBillPrintLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseClaimBillPrintLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseClaimBillPrintLogDTO> findByCriteria(CaseClaimBillPrintLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseClaimBillPrintLog> specification = createSpecification(criteria);
        return caseClaimBillPrintLogMapper.toDto(caseClaimBillPrintLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseClaimBillPrintLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseClaimBillPrintLogDTO> findByCriteria(CaseClaimBillPrintLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseClaimBillPrintLog> specification = createSpecification(criteria);
        return caseClaimBillPrintLogRepository.findAll(specification, page)
            .map(caseClaimBillPrintLogMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseClaimBillPrintLogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseClaimBillPrintLog> specification = createSpecification(criteria);
        return caseClaimBillPrintLogRepository.count(specification);
    }

    /**
     * Function to convert CaseClaimBillPrintLogCriteria to a {@link Specification}.
     */
    private Specification<CaseClaimBillPrintLog> createSpecification(CaseClaimBillPrintLogCriteria criteria) {
        Specification<CaseClaimBillPrintLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseClaimBillPrintLog_.id));
            }
            if (criteria.getOperateType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateType(), CaseClaimBillPrintLog_.operateType));
            }
            if (criteria.getOperateUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateUser(), CaseClaimBillPrintLog_.operateUser));
            }
            if (criteria.getOperateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOperateDate(), CaseClaimBillPrintLog_.operateDate));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseClaimBillId(),
                    root -> root.join(CaseClaimBillPrintLog_.caseClaimBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
        }
        return specification;
    }
}
