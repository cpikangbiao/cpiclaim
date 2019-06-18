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

import com.cpi.claim.domain.Risk;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.RiskRepository;
import com.cpi.claim.service.dto.RiskCriteria;
import com.cpi.claim.service.dto.RiskDTO;
import com.cpi.claim.service.mapper.RiskMapper;

/**
 * Service for executing complex queries for {@link Risk} entities in the database.
 * The main input is a {@link RiskCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RiskDTO} or a {@link Page} of {@link RiskDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RiskQueryService extends QueryService<Risk> {

    private final Logger log = LoggerFactory.getLogger(RiskQueryService.class);

    private final RiskRepository riskRepository;

    private final RiskMapper riskMapper;

    public RiskQueryService(RiskRepository riskRepository, RiskMapper riskMapper) {
        this.riskRepository = riskRepository;
        this.riskMapper = riskMapper;
    }

    /**
     * Return a {@link List} of {@link RiskDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RiskDTO> findByCriteria(RiskCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Risk> specification = createSpecification(criteria);
        return riskMapper.toDto(riskRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RiskDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskDTO> findByCriteria(RiskCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Risk> specification = createSpecification(criteria);
        return riskRepository.findAll(specification, page)
            .map(riskMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RiskCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Risk> specification = createSpecification(criteria);
        return riskRepository.count(specification);
    }

    /**
     * Function to convert RiskCriteria to a {@link Specification}.
     */
    private Specification<Risk> createSpecification(RiskCriteria criteria) {
        Specification<Risk> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Risk_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), Risk_.sortNum));
            }
            if (criteria.getTclType() != null) {
                specification = specification.and(buildSpecification(criteria.getTclType(), Risk_.tclType));
            }
            if (criteria.getPiType() != null) {
                specification = specification.and(buildSpecification(criteria.getPiType(), Risk_.piType));
            }
            if (criteria.getRiskName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskName(), Risk_.riskName));
            }
            if (criteria.getRiskNameChinese() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskNameChinese(), Risk_.riskNameChinese));
            }
            if (criteria.getRiskNameEnglish() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskNameEnglish(), Risk_.riskNameEnglish));
            }
            if (criteria.getRiskNameEnglishAbbr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskNameEnglishAbbr(), Risk_.riskNameEnglishAbbr));
            }
            if (criteria.getRiskGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getRiskGroupId(),
                    root -> root.join(Risk_.riskGroup, JoinType.LEFT).get(RiskGroup_.id)));
            }
        }
        return specification;
    }
}
