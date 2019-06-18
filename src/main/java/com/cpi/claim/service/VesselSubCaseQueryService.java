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

import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.VesselSubCaseRepository;
import com.cpi.claim.service.dto.VesselSubCaseCriteria;
import com.cpi.claim.service.dto.VesselSubCaseDTO;
import com.cpi.claim.service.mapper.VesselSubCaseMapper;

/**
 * Service for executing complex queries for {@link VesselSubCase} entities in the database.
 * The main input is a {@link VesselSubCaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VesselSubCaseDTO} or a {@link Page} of {@link VesselSubCaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VesselSubCaseQueryService extends QueryService<VesselSubCase> {

    private final Logger log = LoggerFactory.getLogger(VesselSubCaseQueryService.class);

    private final VesselSubCaseRepository vesselSubCaseRepository;

    private final VesselSubCaseMapper vesselSubCaseMapper;

    public VesselSubCaseQueryService(VesselSubCaseRepository vesselSubCaseRepository, VesselSubCaseMapper vesselSubCaseMapper) {
        this.vesselSubCaseRepository = vesselSubCaseRepository;
        this.vesselSubCaseMapper = vesselSubCaseMapper;
    }

    /**
     * Return a {@link List} of {@link VesselSubCaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VesselSubCaseDTO> findByCriteria(VesselSubCaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VesselSubCase> specification = createSpecification(criteria);
        return vesselSubCaseMapper.toDto(vesselSubCaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VesselSubCaseDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VesselSubCaseDTO> findByCriteria(VesselSubCaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VesselSubCase> specification = createSpecification(criteria);
        return vesselSubCaseRepository.findAll(specification, page)
            .map(vesselSubCaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VesselSubCaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<VesselSubCase> specification = createSpecification(criteria);
        return vesselSubCaseRepository.count(specification);
    }

    /**
     * Function to convert VesselSubCaseCriteria to a {@link Specification}.
     */
    private Specification<VesselSubCase> createSpecification(VesselSubCaseCriteria criteria) {
        Specification<VesselSubCase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), VesselSubCase_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), VesselSubCase_.numberId));
            }
            if (criteria.getAssignedUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAssignedUserId(), VesselSubCase_.assignedUserId));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), VesselSubCase_.insertTime));
            }
            if (criteria.getSubcaseCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubcaseCode(), VesselSubCase_.subcaseCode));
            }
            if (criteria.getBlNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBlNo(), VesselSubCase_.blNo));
            }
            if (criteria.getClaimant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimant(), VesselSubCase_.claimant));
            }
            if (criteria.getClaimAmount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimAmount(), VesselSubCase_.claimAmount));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrency(), VesselSubCase_.currency));
            }
            if (criteria.getDeductible() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductible(), VesselSubCase_.deductible));
            }
            if (criteria.getCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyRate(), VesselSubCase_.currencyRate));
            }
            if (criteria.getDeductDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductDollar(), VesselSubCase_.deductDollar));
            }
            if (criteria.getVesselCaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getVesselCaseId(),
                    root -> root.join(VesselSubCase_.vesselCase, JoinType.LEFT).get(VesselCase_.id)));
            }
            if (criteria.getRiskId() != null) {
                specification = specification.and(buildSpecification(criteria.getRiskId(),
                    root -> root.join(VesselSubCase_.risk, JoinType.LEFT).get(Risk_.id)));
            }
        }
        return specification;
    }
}
