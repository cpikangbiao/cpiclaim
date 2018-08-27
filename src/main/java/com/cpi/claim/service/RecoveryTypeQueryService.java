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

import com.cpi.claim.domain.RecoveryType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.RecoveryTypeRepository;
import com.cpi.claim.service.dto.RecoveryTypeCriteria;

import com.cpi.claim.service.dto.RecoveryTypeDTO;
import com.cpi.claim.service.mapper.RecoveryTypeMapper;

/**
 * Service for executing complex queries for RecoveryType entities in the database.
 * The main input is a {@link RecoveryTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RecoveryTypeDTO} or a {@link Page} of {@link RecoveryTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RecoveryTypeQueryService extends QueryService<RecoveryType> {

    private final Logger log = LoggerFactory.getLogger(RecoveryTypeQueryService.class);

    private final RecoveryTypeRepository recoveryTypeRepository;

    private final RecoveryTypeMapper recoveryTypeMapper;

    public RecoveryTypeQueryService(RecoveryTypeRepository recoveryTypeRepository, RecoveryTypeMapper recoveryTypeMapper) {
        this.recoveryTypeRepository = recoveryTypeRepository;
        this.recoveryTypeMapper = recoveryTypeMapper;
    }

    /**
     * Return a {@link List} of {@link RecoveryTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RecoveryTypeDTO> findByCriteria(RecoveryTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RecoveryType> specification = createSpecification(criteria);
        return recoveryTypeMapper.toDto(recoveryTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RecoveryTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RecoveryTypeDTO> findByCriteria(RecoveryTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RecoveryType> specification = createSpecification(criteria);
        return recoveryTypeRepository.findAll(specification, page)
            .map(recoveryTypeMapper::toDto);
    }

    /**
     * Function to convert RecoveryTypeCriteria to a {@link Specification}
     */
    private Specification<RecoveryType> createSpecification(RecoveryTypeCriteria criteria) {
        Specification<RecoveryType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RecoveryType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), RecoveryType_.sortNum));
            }
            if (criteria.getRecoveryTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecoveryTypeName(), RecoveryType_.recoveryTypeName));
            }
        }
        return specification;
    }

}
