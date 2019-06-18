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

import com.cpi.claim.domain.CaseCloseLog;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseCloseLogRepository;
import com.cpi.claim.service.dto.CaseCloseLogCriteria;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.mapper.CaseCloseLogMapper;

/**
 * Service for executing complex queries for {@link CaseCloseLog} entities in the database.
 * The main input is a {@link CaseCloseLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseCloseLogDTO} or a {@link Page} of {@link CaseCloseLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseCloseLogQueryService extends QueryService<CaseCloseLog> {

    private final Logger log = LoggerFactory.getLogger(CaseCloseLogQueryService.class);

    private final CaseCloseLogRepository caseCloseLogRepository;

    private final CaseCloseLogMapper caseCloseLogMapper;

    public CaseCloseLogQueryService(CaseCloseLogRepository caseCloseLogRepository, CaseCloseLogMapper caseCloseLogMapper) {
        this.caseCloseLogRepository = caseCloseLogRepository;
        this.caseCloseLogMapper = caseCloseLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseCloseLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseCloseLogDTO> findByCriteria(CaseCloseLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseCloseLog> specification = createSpecification(criteria);
        return caseCloseLogMapper.toDto(caseCloseLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseCloseLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseCloseLogDTO> findByCriteria(CaseCloseLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseCloseLog> specification = createSpecification(criteria);
        return caseCloseLogRepository.findAll(specification, page)
            .map(caseCloseLogMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseCloseLogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseCloseLog> specification = createSpecification(criteria);
        return caseCloseLogRepository.count(specification);
    }

    /**
     * Function to convert CaseCloseLogCriteria to a {@link Specification}.
     */
    private Specification<CaseCloseLog> createSpecification(CaseCloseLogCriteria criteria) {
        Specification<CaseCloseLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseCloseLog_.id));
            }
            if (criteria.getOperateUserId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateUserId(), CaseCloseLog_.operateUserId));
            }
            if (criteria.getOperateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOperateDate(), CaseCloseLog_.operateDate));
            }
            if (criteria.getOperateType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOperateType(), CaseCloseLog_.operateType));
            }
            if (criteria.getVesselCaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getVesselCaseId(),
                    root -> root.join(CaseCloseLog_.vesselCase, JoinType.LEFT).get(VesselCase_.id)));
            }
        }
        return specification;
    }
}
