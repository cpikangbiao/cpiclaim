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

import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseRecoveryRepository;
import com.cpi.claim.service.dto.CaseRecoveryCriteria;

import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.mapper.CaseRecoveryMapper;

/**
 * Service for executing complex queries for CaseRecovery entities in the database.
 * The main input is a {@link CaseRecoveryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseRecoveryDTO} or a {@link Page} of {@link CaseRecoveryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseRecoveryQueryService extends QueryService<CaseRecovery> {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryQueryService.class);

    private final CaseRecoveryRepository caseRecoveryRepository;

    private final CaseRecoveryMapper caseRecoveryMapper;

    public CaseRecoveryQueryService(CaseRecoveryRepository caseRecoveryRepository, CaseRecoveryMapper caseRecoveryMapper) {
        this.caseRecoveryRepository = caseRecoveryRepository;
        this.caseRecoveryMapper = caseRecoveryMapper;
    }

    /**
     * Return a {@link List} of {@link CaseRecoveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseRecoveryDTO> findByCriteria(CaseRecoveryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseRecovery> specification = createSpecification(criteria);
        return caseRecoveryMapper.toDto(caseRecoveryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseRecoveryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseRecoveryDTO> findByCriteria(CaseRecoveryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseRecovery> specification = createSpecification(criteria);
        return caseRecoveryRepository.findAll(specification, page)
            .map(caseRecoveryMapper::toDto);
    }

    /**
     * Function to convert CaseRecoveryCriteria to a {@link Specification}
     */
    private Specification<CaseRecovery> createSpecification(CaseRecoveryCriteria criteria) {
        Specification<CaseRecovery> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseRecovery_.id));
            }
            if (criteria.getClientNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientNo(), CaseRecovery_.clientNo));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseRecovery_.numberId));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrency(), CaseRecovery_.currency));
            }
            if (criteria.getCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyRate(), CaseRecovery_.currencyRate));
            }
            if (criteria.getIssueDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIssueDate(), CaseRecovery_.issueDate));
            }
            if (criteria.getIssueAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIssueAmount(), CaseRecovery_.issueAmount));
            }
            if (criteria.getReceivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceivedDate(), CaseRecovery_.receivedDate));
            }
            if (criteria.getReceivedAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceivedAmount(), CaseRecovery_.receivedAmount));
            }
            if (criteria.getAmountDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountDollar(), CaseRecovery_.amountDollar));
            }
            if (criteria.getRegisterDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisterDate(), CaseRecovery_.registerDate));
            }
            if (criteria.getRegisterUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisterUser(), CaseRecovery_.registerUser));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CaseRecovery_.subcase, VesselSubCase_.id));
            }
            if (criteria.getRecoveryTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRecoveryTypeId(), CaseRecovery_.recoveryType, RecoveryType_.id));
            }
            if (criteria.getCreditorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreditorId(), CaseRecovery_.creditor, Creditor_.id));
            }
        }
        return specification;
    }

}
