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

import com.cpi.claim.domain.CaseRegisterLog;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseRegisterLogRepository;
import com.cpi.claim.service.dto.CaseRegisterLogCriteria;

import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import com.cpi.claim.service.mapper.CaseRegisterLogMapper;

/**
 * Service for executing complex queries for CaseRegisterLog entities in the database.
 * The main input is a {@link CaseRegisterLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseRegisterLogDTO} or a {@link Page} of {@link CaseRegisterLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseRegisterLogQueryService extends QueryService<CaseRegisterLog> {

    private final Logger log = LoggerFactory.getLogger(CaseRegisterLogQueryService.class);

    private final CaseRegisterLogRepository caseRegisterLogRepository;

    private final CaseRegisterLogMapper caseRegisterLogMapper;

    public CaseRegisterLogQueryService(CaseRegisterLogRepository caseRegisterLogRepository, CaseRegisterLogMapper caseRegisterLogMapper) {
        this.caseRegisterLogRepository = caseRegisterLogRepository;
        this.caseRegisterLogMapper = caseRegisterLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseRegisterLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseRegisterLogDTO> findByCriteria(CaseRegisterLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseRegisterLog> specification = createSpecification(criteria);
        return caseRegisterLogMapper.toDto(caseRegisterLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseRegisterLogDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseRegisterLogDTO> findByCriteria(CaseRegisterLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseRegisterLog> specification = createSpecification(criteria);
        return caseRegisterLogRepository.findAll(specification, page)
            .map(caseRegisterLogMapper::toDto);
    }

    /**
     * Function to convert CaseRegisterLogCriteria to a {@link Specification}
     */
    private Specification<CaseRegisterLog> createSpecification(CaseRegisterLogCriteria criteria) {
        Specification<CaseRegisterLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseRegisterLog_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNumberId(), CaseRegisterLog_.numberId));
            }
            if (criteria.getAssignUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignUser(), CaseRegisterLog_.assignUser));
            }
            if (criteria.getAssignTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignTime(), CaseRegisterLog_.assignTime));
            }
            if (criteria.getAssignedUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAssignedUser(), CaseRegisterLog_.assignedUser));
            }
            if (criteria.getVesselCaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getVesselCaseId(), CaseRegisterLog_.vesselCase, VesselCase_.id));
            }
        }
        return specification;
    }

}
