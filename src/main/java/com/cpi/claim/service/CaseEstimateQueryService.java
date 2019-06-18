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

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.cpi.claim.domain.CaseEstimate;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseEstimateRepository;
import com.cpi.claim.service.dto.CaseEstimateCriteria;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.mapper.CaseEstimateMapper;

/**
 * Service for executing complex queries for {@link CaseEstimate} entities in the database.
 * The main input is a {@link CaseEstimateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseEstimateDTO} or a {@link Page} of {@link CaseEstimateDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseEstimateQueryService extends QueryService<CaseEstimate> {

    private final Logger log = LoggerFactory.getLogger(CaseEstimateQueryService.class);

    private final CaseEstimateRepository caseEstimateRepository;

    private final CaseEstimateMapper caseEstimateMapper;

    public CaseEstimateQueryService(CaseEstimateRepository caseEstimateRepository, CaseEstimateMapper caseEstimateMapper) {
        this.caseEstimateRepository = caseEstimateRepository;
        this.caseEstimateMapper = caseEstimateMapper;
    }

    /**
     * Return a {@link List} of {@link CaseEstimateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseEstimateDTO> findByCriteria(CaseEstimateCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseEstimate> specification = createSpecification(criteria);
        return caseEstimateMapper.toDto(caseEstimateRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseEstimateDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseEstimateDTO> findByCriteria(CaseEstimateCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseEstimate> specification = createSpecification(criteria);
        return caseEstimateRepository.findAll(specification, page)
            .map(caseEstimateMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseEstimateCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseEstimate> specification = createSpecification(criteria);
        return caseEstimateRepository.count(specification);
    }

    /**
     * Function to convert CaseEstimateCriteria to a {@link Specification}.
     */
    private Specification<CaseEstimate> createSpecification(CaseEstimateCriteria criteria) {
        Specification<CaseEstimate> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseEstimate_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseEstimate_.numberId));
            }
            if (criteria.getRegisterUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisterUserId(), CaseEstimate_.registerUserId));
            }
            if (criteria.getEstimateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstimateDate(), CaseEstimate_.estimateDate));
            }
            if (criteria.getEstimateEntityFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstimateEntityFee(), CaseEstimate_.estimateEntityFee));
            }
            if (criteria.getEstimateCostFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEstimateCostFee(), CaseEstimate_.estimateCostFee));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getSubcaseId(),
                    root -> root.join(CaseEstimate_.subcase, JoinType.LEFT).get(VesselSubCase_.id)));
            }
        }
        return specification;
    }
}
