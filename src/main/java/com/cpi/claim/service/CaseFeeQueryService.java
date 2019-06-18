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

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.dto.CaseFeeCriteria;

import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseFeeMapper;

/**
 * Service for executing complex queries for CaseFee entities in the database.
 * The main input is a {@link CaseFeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseFeeDTO} or a {@link Page} of {@link CaseFeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseFeeQueryService extends QueryService<CaseFee> {

    private final Logger log = LoggerFactory.getLogger(CaseFeeQueryService.class);

    private final CaseFeeRepository caseFeeRepository;

    private final CaseFeeMapper caseFeeMapper;

    public CaseFeeQueryService(CaseFeeRepository caseFeeRepository, CaseFeeMapper caseFeeMapper) {
        this.caseFeeRepository = caseFeeRepository;
        this.caseFeeMapper = caseFeeMapper;
    }

    /**
     * Return a {@link List} of {@link CaseFeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseFeeDTO> findByCriteria(CaseFeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseFee> specification = createSpecification(criteria);
        return caseFeeMapper.toDto(caseFeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseFeeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseFeeDTO> findByCriteria(CaseFeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseFee> specification = createSpecification(criteria);
        return caseFeeRepository.findAll(specification, page)
            .map(caseFeeMapper::toDto);
    }

    /**
     * Function to convert CaseFeeCriteria to a {@link Specification}
     */
    private Specification<CaseFee> createSpecification(CaseFeeCriteria criteria) {
        Specification<CaseFee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseFee_.id));
            }
            if (criteria.getClientNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientNo(), CaseFee_.clientNo));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseFee_.numberId));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrency(), CaseFee_.currency));
            }
            if (criteria.getCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyRate(), CaseFee_.currencyRate));
            }
            if (criteria.getFeeCostDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCostDate(), CaseFee_.feeCostDate));
            }
            if (criteria.getFeeCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCost(), CaseFee_.feeCost));
            }
            if (criteria.getFeeCostDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCostDollar(), CaseFee_.feeCostDollar));
            }
            if (criteria.getDeduct() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeduct(), CaseFee_.deduct));
            }
            if (criteria.getDeductCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductCurrency(), CaseFee_.deductCurrency));
            }
            if (criteria.getDeductCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductCurrencyRate(), CaseFee_.deductCurrencyRate));
            }
            if (criteria.getDeductAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductAmount(), CaseFee_.deductAmount));
            }
            if (criteria.getAmountDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmountDollar(), CaseFee_.amountDollar));
            }
            if (criteria.getFeeCreateUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCreateUser(), CaseFee_.feeCreateUser));
            }
            if (criteria.getFeeCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCreateDate(), CaseFee_.feeCreateDate));
            }
            if (criteria.getIsSigned() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSigned(), CaseFee_.isSigned));
            }
            if (criteria.getSignUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSignUser(), CaseFee_.signUser));
            }
            if (criteria.getSignDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSignDate(), CaseFee_.signDate));
            }
            if (criteria.getProcessId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcessId(), CaseFee_.processId));
            }
            if (criteria.getFeeTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getFeeTypeId(), CaseFee_.feeType, FeeType_.id));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CaseFee_.subcase, VesselSubCase_.id));
            }
            if (criteria.getCreditorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreditorId(), CaseFee_.creditor, Creditor_.id));
            }
        }
        return specification;
    }

}
