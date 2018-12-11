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

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseClaimRepository;
import com.cpi.claim.service.dto.CaseClaimCriteria;

import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.mapper.CaseClaimMapper;

/**
 * Service for executing complex queries for CaseClaim entities in the database.
 * The main input is a {@link CaseClaimCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimDTO} or a {@link Page} of {@link CaseClaimDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimQueryService extends QueryService<CaseClaim> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimQueryService.class);

    private final CaseClaimRepository caseClaimRepository;

    private final CaseClaimMapper caseClaimMapper;

    public CaseClaimQueryService(CaseClaimRepository caseClaimRepository, CaseClaimMapper caseClaimMapper) {
        this.caseClaimRepository = caseClaimRepository;
        this.caseClaimMapper = caseClaimMapper;
    }

    /**
     * Return a {@link List} of {@link CaseClaimDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseClaimDTO> findByCriteria(CaseClaimCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseClaim> specification = createSpecification(criteria);
        return caseClaimMapper.toDto(caseClaimRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseClaimDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseClaimDTO> findByCriteria(CaseClaimCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseClaim> specification = createSpecification(criteria);
        return caseClaimRepository.findAll(specification, page)
            .map(caseClaimMapper::toDto);
    }

    /**
     * Function to convert CaseClaimCriteria to a {@link Specification}
     */
    private Specification<CaseClaim> createSpecification(CaseClaimCriteria criteria) {
        Specification<CaseClaim> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseClaim_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseClaim_.numberId));
            }
            if (criteria.getClaimer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimer(), CaseClaim_.claimer));
            }
            if (criteria.getClaimDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimDate(), CaseClaim_.claimDate));
            }
            if (criteria.getBillOfLading() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBillOfLading(), CaseClaim_.billOfLading));
            }
            if (criteria.getCurrencyId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyId(), CaseClaim_.currencyId));
            }
            if (criteria.getCurrencyRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCurrencyRate(), CaseClaim_.currencyRate));
            }
            if (criteria.getClaimCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimCost(), CaseClaim_.claimCost));
            }
            if (criteria.getClaimCostDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getClaimCostDollar(), CaseClaim_.claimCostDollar));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getSubcaseId(), CaseClaim_.subcase, VesselSubCase_.id));
            }
        }
        return specification;
    }

}
