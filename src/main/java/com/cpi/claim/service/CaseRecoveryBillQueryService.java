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

import com.cpi.claim.domain.CaseRecoveryBill;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseRecoveryBillRepository;
import com.cpi.claim.service.dto.CaseRecoveryBillCriteria;
import com.cpi.claim.service.dto.CaseRecoveryBillDTO;
import com.cpi.claim.service.mapper.CaseRecoveryBillMapper;

/**
 * Service for executing complex queries for {@link CaseRecoveryBill} entities in the database.
 * The main input is a {@link CaseRecoveryBillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseRecoveryBillDTO} or a {@link Page} of {@link CaseRecoveryBillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseRecoveryBillQueryService extends QueryService<CaseRecoveryBill> {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryBillQueryService.class);

    private final CaseRecoveryBillRepository caseRecoveryBillRepository;

    private final CaseRecoveryBillMapper caseRecoveryBillMapper;

    public CaseRecoveryBillQueryService(CaseRecoveryBillRepository caseRecoveryBillRepository, CaseRecoveryBillMapper caseRecoveryBillMapper) {
        this.caseRecoveryBillRepository = caseRecoveryBillRepository;
        this.caseRecoveryBillMapper = caseRecoveryBillMapper;
    }

    /**
     * Return a {@link List} of {@link CaseRecoveryBillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseRecoveryBillDTO> findByCriteria(CaseRecoveryBillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseRecoveryBill> specification = createSpecification(criteria);
        return caseRecoveryBillMapper.toDto(caseRecoveryBillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseRecoveryBillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseRecoveryBillDTO> findByCriteria(CaseRecoveryBillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseRecoveryBill> specification = createSpecification(criteria);
        return caseRecoveryBillRepository.findAll(specification, page)
            .map(caseRecoveryBillMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseRecoveryBillCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseRecoveryBill> specification = createSpecification(criteria);
        return caseRecoveryBillRepository.count(specification);
    }

    /**
     * Function to convert CaseRecoveryBillCriteria to a {@link Specification}.
     */
    private Specification<CaseRecoveryBill> createSpecification(CaseRecoveryBillCriteria criteria) {
        Specification<CaseRecoveryBill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseRecoveryBill_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseRecoveryBill_.numberId));
            }
            if (criteria.getIsWriteOff() != null) {
                specification = specification.and(buildSpecification(criteria.getIsWriteOff(), CaseRecoveryBill_.isWriteOff));
            }
            if (criteria.getCaseRecoveryId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseRecoveryId(),
                    root -> root.join(CaseRecoveryBill_.caseRecovery, JoinType.LEFT).get(CaseRecovery_.id)));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseClaimBillId(),
                    root -> root.join(CaseRecoveryBill_.caseClaimBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
            if (criteria.getWriteOffBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getWriteOffBillId(),
                    root -> root.join(CaseRecoveryBill_.writeOffBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
        }
        return specification;
    }
}
