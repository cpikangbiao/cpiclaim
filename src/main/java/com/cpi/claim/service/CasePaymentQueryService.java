/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

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

import com.cpi.claim.domain.CasePayment;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CasePaymentRepository;
import com.cpi.claim.service.dto.CasePaymentCriteria;

import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.mapper.CasePaymentMapper;

/**
 * Service for executing complex queries for CasePayment entities in the database.
 * The main input is a {@link CasePaymentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CasePaymentDTO} or a {@link Page} of {@link CasePaymentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CasePaymentQueryService extends QueryService<CasePayment> {

    private final Logger log = LoggerFactory.getLogger(CasePaymentQueryService.class);

    private final CasePaymentRepository casePaymentRepository;

    private final CasePaymentMapper casePaymentMapper;

    public CasePaymentQueryService(CasePaymentRepository casePaymentRepository, CasePaymentMapper casePaymentMapper) {
        this.casePaymentRepository = casePaymentRepository;
        this.casePaymentMapper = casePaymentMapper;
    }

    /**
     * Return a {@link List} of {@link CasePaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CasePaymentDTO> findByCriteria(CasePaymentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CasePayment> specification = createSpecification(criteria);
        return casePaymentMapper.toDto(casePaymentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CasePaymentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CasePaymentDTO> findByCriteria(CasePaymentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CasePayment> specification = createSpecification(criteria);
        return casePaymentRepository.findAll(specification, page)
            .map(casePaymentMapper::toDto);
    }

    /**
     * Function to convert CasePaymentCriteria to a {@link Specification}
     */
    private Specification<CasePayment> createSpecification(CasePaymentCriteria criteria) {
        Specification<CasePayment> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CasePayment_.id));
            }
            if (criteria.getClientNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClientNo(), CasePayment_.clientNo));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CasePayment_.numberId));
            }
            if (criteria.getPayCostDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPayCostDate(), CasePayment_.payCostDate));
            }
            if (criteria.getCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrency(), CasePayment_.currency));
            }
            if (criteria.getCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyRate(), CasePayment_.currencyRate));
            }
            if (criteria.getPayCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPayCost(), CasePayment_.payCost));
            }
            if (criteria.getPayCostDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPayCostDollar(), CasePayment_.payCostDollar));
            }
            if (criteria.getDeduct() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDeduct(), CasePayment_.deduct));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), CasePayment_.amount));
            }
            if (criteria.getFeeCreateUser() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCreateUser(), CasePayment_.feeCreateUser));
            }
            if (criteria.getFeeCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFeeCreateDate(), CasePayment_.feeCreateDate));
            }
            if (criteria.getPaymentTypeId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getPaymentTypeId(), CasePayment_.paymentType, PaymentType_.id));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CasePayment_.subcase, VesselSubCase_.id));
            }
            if (criteria.getCreditorId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreditorId(), CasePayment_.creditor, Creditor_.id));
            }
        }
        return specification;
    }

}
