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

import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseClaimBillApprovalLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillApprovalLogMapper;

/**
 * Service for executing complex queries for {@link CaseClaimBillApprovalLog} entities in the database.
 * The main input is a {@link CaseClaimBillApprovalLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimBillApprovalLogDTO} or a {@link Page} of {@link CaseClaimBillApprovalLogDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseClaimBillApprovalLogQueryService extends QueryService<CaseClaimBillApprovalLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogQueryService.class);

    private final CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository;

    private final CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper;

    public CaseClaimBillApprovalLogQueryService(CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository, CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper) {
        this.caseClaimBillApprovalLogRepository = caseClaimBillApprovalLogRepository;
        this.caseClaimBillApprovalLogMapper = caseClaimBillApprovalLogMapper;
    }

    /**
     * Return a {@link List} of {@link CaseClaimBillApprovalLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseClaimBillApprovalLogDTO> findByCriteria(CaseClaimBillApprovalLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseClaimBillApprovalLog> specification = createSpecification(criteria);
        return caseClaimBillApprovalLogMapper.toDto(caseClaimBillApprovalLogRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseClaimBillApprovalLogDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseClaimBillApprovalLogDTO> findByCriteria(CaseClaimBillApprovalLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseClaimBillApprovalLog> specification = createSpecification(criteria);
        return caseClaimBillApprovalLogRepository.findAll(specification, page)
            .map(caseClaimBillApprovalLogMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseClaimBillApprovalLogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseClaimBillApprovalLog> specification = createSpecification(criteria);
        return caseClaimBillApprovalLogRepository.count(specification);
    }

    /**
     * Function to convert CaseClaimBillApprovalLogCriteria to a {@link Specification}.
     */
    private Specification<CaseClaimBillApprovalLog> createSpecification(CaseClaimBillApprovalLogCriteria criteria) {
        Specification<CaseClaimBillApprovalLog> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseClaimBillApprovalLog_.id));
            }
            if (criteria.getProcessId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcessId(), CaseClaimBillApprovalLog_.processId));
            }
            if (criteria.getInsertTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInsertTime(), CaseClaimBillApprovalLog_.insertTime));
            }
            if (criteria.getApprovalUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApprovalUser(), CaseClaimBillApprovalLog_.approvalUser));
            }
            if (criteria.getApprovalOpinion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApprovalOpinion(), CaseClaimBillApprovalLog_.approvalOpinion));
            }
            if (criteria.getApprovalTransition() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApprovalTransition(), CaseClaimBillApprovalLog_.approvalTransition));
            }
            if (criteria.getRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemark(), CaseClaimBillApprovalLog_.remark));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseClaimBillId(),
                    root -> root.join(CaseClaimBillApprovalLog_.caseClaimBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
        }
        return specification;
    }
}
