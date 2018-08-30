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

import com.cpi.claim.domain.CaseAssignLog;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseAssignLogRepository;
import com.cpi.claim.service.dto.CaseAssignLogCriteria;

import com.cpi.claim.service.dto.CaseAssignLogDTO;
import com.cpi.claim.service.mapper.CaseAssignLogMapper;

/**
 * Service for executing complex queries for CaseAssignLog entities in the database.
 * The main input is a {@link CaseAssignLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseAssignLogDTO} or a {@link Page} of {@link CaseAssignLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseAssignLogQueryService extends QueryService<CaseAssignLog> {

    private final Logger log = LoggerFactory.getLogger(CaseAssignLogQueryService.class);

    private final CaseAssignLogRepository caseAssignLogRepository;

    private final CaseAssignLogMapper caseAssignLogMapper;

    public CaseAssignLogQueryService(CaseAssignLogRepository caseAssignLogRepository, CaseAssignLogMapper caseAssignLogMapper) {
        this.caseAssignLogRepository = caseAssignLogRepository;
        this.caseAssignLogMapper = caseAssignLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseAssignLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseAssignLogDTO> findByCriteria(CaseAssignLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseAssignLog> specification = createSpecification(criteria);
        return caseAssignLogMapper.toDto(caseAssignLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseAssignLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseAssignLogDTO> findByCriteria(CaseAssignLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseAssignLog> specification = createSpecification(criteria);
        return caseAssignLogRepository.findAll(specification, page)
            .map(caseAssignLogMapper::toDto);
    }

    /**
     * Function to convert CaseAssignLogCriteria to a {@link Specification}
     */
    private Specification<CaseAssignLog> createSpecification(CaseAssignLogCriteria criteria) {
        Specification<CaseAssignLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseAssignLog_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumberId(), CaseAssignLog_.numberId));
            }
            if (criteria.getAssignUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignUser(), CaseAssignLog_.assignUser));
            }
            if (criteria.getAssignTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignTime(), CaseAssignLog_.assignTime));
            }
            if (criteria.getAssignedUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignedUser(), CaseAssignLog_.assignedUser));
            }
            if (criteria.getVesselCaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getVesselCaseId(), CaseAssignLog_.vesselCase, VesselCase_.id));
            }
        }
        return specification;
    }

}
