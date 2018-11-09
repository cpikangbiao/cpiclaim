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

import com.cpi.claim.domain.CasePaymentBill;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CasePaymentBillRepository;
import com.cpi.claim.service.dto.CasePaymentBillCriteria;

import com.cpi.claim.service.dto.CasePaymentBillDTO;
import com.cpi.claim.service.mapper.CasePaymentBillMapper;

/**
 * Service for executing complex queries for CasePaymentBill entities in the database.
 * The main input is a {@link CasePaymentBillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CasePaymentBillDTO} or a {@link Page} of {@link CasePaymentBillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CasePaymentBillQueryService extends QueryService<CasePaymentBill> {

    private final Logger log = LoggerFactory.getLogger(CasePaymentBillQueryService.class);

    private final CasePaymentBillRepository casePaymentBillRepository;

    private final CasePaymentBillMapper casePaymentBillMapper;

    public CasePaymentBillQueryService(CasePaymentBillRepository casePaymentBillRepository, CasePaymentBillMapper casePaymentBillMapper) {
        this.casePaymentBillRepository = casePaymentBillRepository;
        this.casePaymentBillMapper = casePaymentBillMapper;
    }

    /**
     * Return a {@link List} of {@link CasePaymentBillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CasePaymentBillDTO> findByCriteria(CasePaymentBillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CasePaymentBill> specification = createSpecification(criteria);
        return casePaymentBillMapper.toDto(casePaymentBillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CasePaymentBillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CasePaymentBillDTO> findByCriteria(CasePaymentBillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CasePaymentBill> specification = createSpecification(criteria);
        return casePaymentBillRepository.findAll(specification, page)
            .map(casePaymentBillMapper::toDto);
    }

    /**
     * Function to convert CasePaymentBillCriteria to a {@link Specification}
     */
    private Specification<CasePaymentBill> createSpecification(CasePaymentBillCriteria criteria) {
        Specification<CasePaymentBill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CasePaymentBill_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CasePaymentBill_.numberId));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrency(), CasePaymentBill_.currency));
            }
            if (criteria.getAmout() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmout(), CasePaymentBill_.amout));
            }
            if (criteria.getIsWriteOff() != null) {
                specification = specification.and(buildSpecification(criteria.getIsWriteOff(), CasePaymentBill_.isWriteOff));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CasePaymentBill_.subcase, VesselSubCase_.id));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCaseClaimBillId(), CasePaymentBill_.caseClaimBill, CaseClaimBill_.id));
            }
            if (criteria.getWriteOffBillId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getWriteOffBillId(), CasePaymentBill_.writeOffBill, CaseClaimBill_.id));
            }
        }
        return specification;
    }

}
