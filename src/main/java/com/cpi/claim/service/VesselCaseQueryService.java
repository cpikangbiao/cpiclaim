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

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.dto.VesselCaseCriteria;

import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;

/**
 * Service for executing complex queries for VesselCase entities in the database.
 * The main input is a {@link VesselCaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VesselCaseDTO} or a {@link Page} of {@link VesselCaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VesselCaseQueryService extends QueryService<VesselCase> {

    private final Logger log = LoggerFactory.getLogger(VesselCaseQueryService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    public VesselCaseQueryService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    /**
     * Return a {@link List} of {@link VesselCaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VesselCaseDTO> findByCriteria(VesselCaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<VesselCase> specification = createSpecification(criteria);
        return vesselCaseMapper.toDto(vesselCaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VesselCaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VesselCaseDTO> findByCriteria(VesselCaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<VesselCase> specification = createSpecification(criteria);
        return vesselCaseRepository.findAll(specification, page)
            .map(vesselCaseMapper::toDto);
    }

    /**
     * Function to convert VesselCaseCriteria to a {@link Specification}
     */
    private Specification<VesselCase> createSpecification(VesselCaseCriteria criteria) {
        Specification<VesselCase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), VesselCase_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), VesselCase_.numberId));
            }
            if (criteria.getCaseYear() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseYear(), VesselCase_.caseYear));
            }
            if (criteria.getInsuredVessel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsuredVessel(), VesselCase_.insuredVessel));
            }
            if (criteria.getCompanyName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyName(), VesselCase_.companyName));
            }
            if (criteria.getVesselName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVesselName(), VesselCase_.vesselName));
            }
            if (criteria.getCompanyChineseName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompanyChineseName(), VesselCase_.companyChineseName));
            }
            if (criteria.getVesselChineseName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVesselChineseName(), VesselCase_.vesselChineseName));
            }
            if (criteria.getDeduct() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeduct(), VesselCase_.deduct));
            }
            if (criteria.getAssignedHandler() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAssignedHandler(), VesselCase_.assignedHandler));
            }
            if (criteria.getAssignedTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAssignedTime(), VesselCase_.assignedTime));
            }
            if (criteria.getRegisteredHandler() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisteredHandler(), VesselCase_.registeredHandler));
            }
            if (criteria.getRegisteredDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisteredDate(), VesselCase_.registeredDate));
            }
            if (criteria.getCaseCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseCode(), VesselCase_.caseCode));
            }
            if (criteria.getCaseLocation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCaseLocation(), VesselCase_.caseLocation));
            }
            if (criteria.getCaseDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseDescription(), VesselCase_.caseDescription));
            }
            if (criteria.getLoadingPort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLoadingPort(), VesselCase_.loadingPort));
            }
            if (criteria.getPolDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPolDate(), VesselCase_.polDate));
            }
            if (criteria.getDischargingPort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDischargingPort(), VesselCase_.dischargingPort));
            }
            if (criteria.getLimitTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLimitTime(), VesselCase_.limitTime));
            }
            if (criteria.getCpDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCpDate(), VesselCase_.cpDate));
            }
            if (criteria.getCpType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCpType(), VesselCase_.cpType));
            }
            if (criteria.getArrestVessel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArrestVessel(), VesselCase_.arrestVessel));
            }
            if (criteria.getJurisdiction() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJurisdiction(), VesselCase_.jurisdiction));
            }
            if (criteria.getApplicableLaw() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getApplicableLaw(), VesselCase_.applicableLaw));
            }
            if (criteria.getCloseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCloseDate(), VesselCase_.closeDate));
            }
            if (criteria.getCloseHandler() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCloseHandler(), VesselCase_.closeHandler));
            }
            if (criteria.getSettlementAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSettlementAmount(), VesselCase_.settlementAmount));
            }
            if (criteria.getSettlementDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSettlementDate(), VesselCase_.settlementDate));
            }
            if (criteria.getCpiInsuranceTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCpiInsuranceTypeId(), VesselCase_.cpiInsuranceType, CpiInsuranceType_.id));
            }
            if (criteria.getCaseStatusId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCaseStatusId(), VesselCase_.caseStatus, CaseSatusType_.id));
            }
            if (criteria.getSettlementModeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSettlementModeId(), VesselCase_.settlementMode, CaseSettlementMode_.id));
            }
        }
        return specification;
    }

}
