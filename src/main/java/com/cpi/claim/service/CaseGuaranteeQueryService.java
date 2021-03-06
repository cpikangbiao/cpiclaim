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

import com.cpi.claim.domain.CaseGuarantee;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseGuaranteeRepository;
import com.cpi.claim.service.dto.CaseGuaranteeCriteria;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
import com.cpi.claim.service.mapper.CaseGuaranteeMapper;

/**
 * Service for executing complex queries for {@link CaseGuarantee} entities in the database.
 * The main input is a {@link CaseGuaranteeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseGuaranteeDTO} or a {@link Page} of {@link CaseGuaranteeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseGuaranteeQueryService extends QueryService<CaseGuarantee> {

    private final Logger log = LoggerFactory.getLogger(CaseGuaranteeQueryService.class);

    private final CaseGuaranteeRepository caseGuaranteeRepository;

    private final CaseGuaranteeMapper caseGuaranteeMapper;

    public CaseGuaranteeQueryService(CaseGuaranteeRepository caseGuaranteeRepository, CaseGuaranteeMapper caseGuaranteeMapper) {
        this.caseGuaranteeRepository = caseGuaranteeRepository;
        this.caseGuaranteeMapper = caseGuaranteeMapper;
    }

    /**
     * Return a {@link List} of {@link CaseGuaranteeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseGuaranteeDTO> findByCriteria(CaseGuaranteeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseGuarantee> specification = createSpecification(criteria);
        return caseGuaranteeMapper.toDto(caseGuaranteeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseGuaranteeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseGuaranteeDTO> findByCriteria(CaseGuaranteeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseGuarantee> specification = createSpecification(criteria);
        return caseGuaranteeRepository.findAll(specification, page)
            .map(caseGuaranteeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseGuaranteeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseGuarantee> specification = createSpecification(criteria);
        return caseGuaranteeRepository.count(specification);
    }

    /**
     * Function to convert CaseGuaranteeCriteria to a {@link Specification}.
     */
    private Specification<CaseGuarantee> createSpecification(CaseGuaranteeCriteria criteria) {
        Specification<CaseGuarantee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseGuarantee_.id));
            }
            if (criteria.getVesselId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVesselId(), CaseGuarantee_.vesselId));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseGuarantee_.numberId));
            }
            if (criteria.getArrestDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArrestDate(), CaseGuarantee_.arrestDate));
            }
            if (criteria.getArrestPort() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArrestPort(), CaseGuarantee_.arrestPort));
            }
            if (criteria.getArrestVesselId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArrestVesselId(), CaseGuarantee_.arrestVesselId));
            }
            if (criteria.getArrestVesselName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArrestVesselName(), CaseGuarantee_.arrestVesselName));
            }
            if (criteria.getBillOfLading() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBillOfLading(), CaseGuarantee_.billOfLading));
            }
            if (criteria.getBillLadingDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBillLadingDate(), CaseGuarantee_.billLadingDate));
            }
            if (criteria.getNatureOfClaim() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNatureOfClaim(), CaseGuarantee_.natureOfClaim));
            }
            if (criteria.getGuarantee() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuarantee(), CaseGuarantee_.guarantee));
            }
            if (criteria.getGuaranteeDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeDate(), CaseGuarantee_.guaranteeDate));
            }
            if (criteria.getGuaranteeCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeCurrency(), CaseGuarantee_.guaranteeCurrency));
            }
            if (criteria.getGuaranteeRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeRate(), CaseGuarantee_.guaranteeRate));
            }
            if (criteria.getGuaranteeAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeAmount(), CaseGuarantee_.guaranteeAmount));
            }
            if (criteria.getGuaranteeAmountDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeAmountDollar(), CaseGuarantee_.guaranteeAmountDollar));
            }
            if (criteria.getGuaranteeTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuaranteeTo(), CaseGuarantee_.guaranteeTo));
            }
            if (criteria.getGuaranteeToAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuaranteeToAddress(), CaseGuarantee_.guaranteeToAddress));
            }
            if (criteria.getGuaranteeNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuaranteeNo(), CaseGuarantee_.guaranteeNo));
            }
            if (criteria.getGuaranteeFee() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGuaranteeFee(), CaseGuarantee_.guaranteeFee));
            }
            if (criteria.getGuaranteeOther() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuaranteeOther(), CaseGuarantee_.guaranteeOther));
            }
            if (criteria.getCancelDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCancelDate(), CaseGuarantee_.cancelDate));
            }
            if (criteria.getConGuarantee() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConGuarantee(), CaseGuarantee_.conGuarantee));
            }
            if (criteria.getConGuaranteeDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeDate(), CaseGuarantee_.conGuaranteeDate));
            }
            if (criteria.getConGuaranteeCurrency() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeCurrency(), CaseGuarantee_.conGuaranteeCurrency));
            }
            if (criteria.getConGuaranteeRate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeRate(), CaseGuarantee_.conGuaranteeRate));
            }
            if (criteria.getConGuaranteeAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeAmount(), CaseGuarantee_.conGuaranteeAmount));
            }
            if (criteria.getConGuaranteeAmountDollar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeAmountDollar(), CaseGuarantee_.conGuaranteeAmountDollar));
            }
            if (criteria.getConGuaranteeNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConGuaranteeNo(), CaseGuarantee_.conGuaranteeNo));
            }
            if (criteria.getConGuaranteeTo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConGuaranteeTo(), CaseGuarantee_.conGuaranteeTo));
            }
            if (criteria.getConGuaranteeCancelDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConGuaranteeCancelDate(), CaseGuarantee_.conGuaranteeCancelDate));
            }
            if (criteria.getReleaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReleaseDate(), CaseGuarantee_.releaseDate));
            }
            if (criteria.getRegisterUserId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegisterUserId(), CaseGuarantee_.registerUserId));
            }
            if (criteria.getSubcaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getSubcaseId(),
                    root -> root.join(CaseGuarantee_.subcase, JoinType.LEFT).get(VesselSubCase_.id)));
            }
            if (criteria.getGuaranteeTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getGuaranteeTypeId(),
                    root -> root.join(CaseGuarantee_.guaranteeType, JoinType.LEFT).get(GuaranteeType_.id)));
            }
            if (criteria.getConGuaranteeTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getConGuaranteeTypeId(),
                    root -> root.join(CaseGuarantee_.conGuaranteeType, JoinType.LEFT).get(GuaranteeType_.id)));
            }
        }
        return specification;
    }
}
