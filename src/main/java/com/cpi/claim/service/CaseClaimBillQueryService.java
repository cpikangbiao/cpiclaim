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

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;

import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;

/**
 * Service for executing complex queries for CaseClaimBill entities in the database.
 * The main input is a {@link CaseClaimBillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimBillDTO} or a {@link Page} of {@link CaseClaimBillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimBillQueryService extends QueryService<CaseClaimBill> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillQueryService.class);

    private final CaseClaimBillRepository caseClaimBillRepository;

    private final CaseClaimBillMapper caseClaimBillMapper;

    public CaseClaimBillQueryService(CaseClaimBillRepository caseClaimBillRepository, CaseClaimBillMapper caseClaimBillMapper) {
        this.caseClaimBillRepository = caseClaimBillRepository;
        this.caseClaimBillMapper = caseClaimBillMapper;
    }

    /**
     * Return a {@link List} of {@link CaseClaimBillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseClaimBillDTO> findByCriteria(CaseClaimBillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseClaimBill> specification = createSpecification(criteria);
        return caseClaimBillMapper.toDto(caseClaimBillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseClaimBillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseClaimBillDTO> findByCriteria(CaseClaimBillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseClaimBill> specification = createSpecification(criteria);
        return caseClaimBillRepository.findAll(specification, page)
            .map(caseClaimBillMapper::toDto);
    }

    /**
     * Function to convert CaseClaimBillCriteria to a {@link Specification}
     */
    private Specification<CaseClaimBill> createSpecification(CaseClaimBillCriteria criteria) {
        Specification<CaseClaimBill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseClaimBill_.id));
            }
            if (criteria.getClaimBillCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimBillCode(), CaseClaimBill_.claimBillCode));
            }
            if (criteria.getClaimBillDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimBillDate(), CaseClaimBill_.claimBillDate));
            }
            if (criteria.getRegisterUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisterUserId(), CaseClaimBill_.registerUserId));
            }
            if (criteria.getClientBillNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientBillNo(), CaseClaimBill_.clientBillNo));
            }
            if (criteria.getBillDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBillDescription(), CaseClaimBill_.billDescription));
            }
            if (criteria.getDueDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDueDate(), CaseClaimBill_.dueDate));
            }
            if (criteria.getMemberYear() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMemberYear(), CaseClaimBill_.memberYear));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseClaimBill_.numberId));
            }
            if (criteria.getClaimAmountCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimAmountCurrency(), CaseClaimBill_.claimAmountCurrency));
            }
            if (criteria.getClaimAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimAmount(), CaseClaimBill_.claimAmount));
            }
            if (criteria.getDeductible() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductible(), CaseClaimBill_.deductible));
            }
            if (criteria.getDeductibleCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductibleCurrency(), CaseClaimBill_.deductibleCurrency));
            }
            if (criteria.getDeductibleCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductibleCurrencyRate(), CaseClaimBill_.deductibleCurrencyRate));
            }
            if (criteria.getDeductibleDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeductibleDollar(), CaseClaimBill_.deductibleDollar));
            }
            if (criteria.getBillCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBillCurrency(), CaseClaimBill_.billCurrency));
            }
            if (criteria.getBillAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBillAmount(), CaseClaimBill_.billAmount));
            }
            if (criteria.getBillCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBillCurrencyRate(), CaseClaimBill_.billCurrencyRate));
            }
            if (criteria.getBillAmountDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBillAmountDollar(), CaseClaimBill_.billAmountDollar));
            }
            if (criteria.getIsSigned() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSigned(), CaseClaimBill_.isSigned));
            }
            if (criteria.getSignUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSignUser(), CaseClaimBill_.signUser));
            }
            if (criteria.getSignDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSignDate(), CaseClaimBill_.signDate));
            }
            if (criteria.getProcessId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcessId(), CaseClaimBill_.processId));
            }
            if (criteria.getPrintNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrintNumber(), CaseClaimBill_.printNumber));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CaseClaimBill_.subcase, VesselSubCase_.id));
            }
            if (criteria.getClaimBillStatusId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getClaimBillStatusId(), CaseClaimBill_.claimBillStatus, ClaimBillStatus_.id));
            }
            if (criteria.getClaimBillTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getClaimBillTypeId(), CaseClaimBill_.claimBillType, ClaimBillType_.id));
            }
            if (criteria.getClaimBillFinanceTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getClaimBillFinanceTypeId(), CaseClaimBill_.claimBillFinanceType, ClaimBillFinanceType_.id));
            }
            if (criteria.getCreditorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreditorId(), CaseClaimBill_.creditor, Creditor_.id));
            }
        }
        return specification;
    }

}
