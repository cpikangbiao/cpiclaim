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

import com.cpi.claim.domain.Creditor;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CreditorRepository;
import com.cpi.claim.service.dto.CreditorCriteria;
import com.cpi.claim.service.dto.CreditorDTO;
import com.cpi.claim.service.mapper.CreditorMapper;

/**
 * Service for executing complex queries for {@link Creditor} entities in the database.
 * The main input is a {@link CreditorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CreditorDTO} or a {@link Page} of {@link CreditorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CreditorQueryService extends QueryService<Creditor> {

    private final Logger log = LoggerFactory.getLogger(CreditorQueryService.class);

    private final CreditorRepository creditorRepository;

    private final CreditorMapper creditorMapper;

    public CreditorQueryService(CreditorRepository creditorRepository, CreditorMapper creditorMapper) {
        this.creditorRepository = creditorRepository;
        this.creditorMapper = creditorMapper;
    }

    /**
     * Return a {@link List} of {@link CreditorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CreditorDTO> findByCriteria(CreditorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Creditor> specification = createSpecification(criteria);
        return creditorMapper.toDto(creditorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CreditorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CreditorDTO> findByCriteria(CreditorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Creditor> specification = createSpecification(criteria);
        return creditorRepository.findAll(specification, page)
            .map(creditorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CreditorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Creditor> specification = createSpecification(criteria);
        return creditorRepository.count(specification);
    }

    /**
     * Function to convert CreditorCriteria to a {@link Specification}.
     */
    private Specification<Creditor> createSpecification(CreditorCriteria criteria) {
        Specification<Creditor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Creditor_.id));
            }
            if (criteria.getCreditorCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreditorCode(), Creditor_.creditorCode));
            }
            if (criteria.getCreditorName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreditorName(), Creditor_.creditorName));
            }
            if (criteria.getCreditorAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreditorAddress(), Creditor_.creditorAddress));
            }
            if (criteria.getPortName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPortName(), Creditor_.portName));
            }
            if (criteria.getSwiftCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSwiftCode(), Creditor_.swiftCode));
            }
            if (criteria.getIbanCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIbanCode(), Creditor_.ibanCode));
            }
            if (criteria.getBankName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankName(), Creditor_.bankName));
            }
            if (criteria.getBankAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAddress(), Creditor_.bankAddress));
            }
            if (criteria.getAccountNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountNo(), Creditor_.accountNo));
            }
            if (criteria.getCorrBankName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorrBankName(), Creditor_.corrBankName));
            }
            if (criteria.getCorrBankAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorrBankAddress(), Creditor_.corrBankAddress));
            }
            if (criteria.getCorrBankName2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorrBankName2(), Creditor_.corrBankName2));
            }
            if (criteria.getCorrBankAddress2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCorrBankAddress2(), Creditor_.corrBankAddress2));
            }
        }
        return specification;
    }
}
