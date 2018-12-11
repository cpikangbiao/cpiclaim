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

import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseStatusTypeRepository;
import com.cpi.claim.service.dto.CaseStatusTypeCriteria;

import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.mapper.CaseStatusTypeMapper;

/**
 * Service for executing complex queries for CaseStatusType entities in the database.
 * The main input is a {@link CaseStatusTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseStatusTypeDTO} or a {@link Page} of {@link CaseStatusTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseStatusTypeQueryService extends QueryService<CaseStatusType> {

    private final Logger log = LoggerFactory.getLogger(CaseStatusTypeQueryService.class);

    private final CaseStatusTypeRepository caseStatusTypeRepository;

    private final CaseStatusTypeMapper caseStatusTypeMapper;

    public CaseStatusTypeQueryService(CaseStatusTypeRepository caseStatusTypeRepository, CaseStatusTypeMapper caseStatusTypeMapper) {
        this.caseStatusTypeRepository = caseStatusTypeRepository;
        this.caseStatusTypeMapper = caseStatusTypeMapper;
    }

    /**
     * Return a {@link List} of {@link CaseStatusTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseStatusTypeDTO> findByCriteria(CaseStatusTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseStatusType> specification = createSpecification(criteria);
        return caseStatusTypeMapper.toDto(caseStatusTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseStatusTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseStatusTypeDTO> findByCriteria(CaseStatusTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseStatusType> specification = createSpecification(criteria);
        return caseStatusTypeRepository.findAll(specification, page)
            .map(caseStatusTypeMapper::toDto);
    }

    /**
     * Function to convert CaseStatusTypeCriteria to a {@link Specification}
     */
    private Specification<CaseStatusType> createSpecification(CaseStatusTypeCriteria criteria) {
        Specification<CaseStatusType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseStatusType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), CaseStatusType_.sortNum));
            }
            if (criteria.getCaseStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseStatusName(), CaseStatusType_.caseStatusName));
            }
        }
        return specification;
    }

}
