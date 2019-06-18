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

import com.cpi.claim.domain.FeeType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.FeeTypeRepository;
import com.cpi.claim.service.dto.FeeTypeCriteria;

import com.cpi.claim.service.dto.FeeTypeDTO;
import com.cpi.claim.service.mapper.FeeTypeMapper;

/**
 * Service for executing complex queries for FeeType entities in the database.
 * The main input is a {@link FeeTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FeeTypeDTO} or a {@link Page} of {@link FeeTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FeeTypeQueryService extends QueryService<FeeType> {

    private final Logger log = LoggerFactory.getLogger(FeeTypeQueryService.class);

    private final FeeTypeRepository feeTypeRepository;

    private final FeeTypeMapper feeTypeMapper;

    public FeeTypeQueryService(FeeTypeRepository feeTypeRepository, FeeTypeMapper feeTypeMapper) {
        this.feeTypeRepository = feeTypeRepository;
        this.feeTypeMapper = feeTypeMapper;
    }

    /**
     * Return a {@link List} of {@link FeeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FeeTypeDTO> findByCriteria(FeeTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FeeType> specification = createSpecification(criteria);
        return feeTypeMapper.toDto(feeTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FeeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FeeTypeDTO> findByCriteria(FeeTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FeeType> specification = createSpecification(criteria);
        return feeTypeRepository.findAll(specification, page)
            .map(feeTypeMapper::toDto);
    }

    /**
     * Function to convert FeeTypeCriteria to a {@link Specification}
     */
    private Specification<FeeType> createSpecification(FeeTypeCriteria criteria) {
        Specification<FeeType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FeeType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), FeeType_.sortNum));
            }
            if (criteria.getFeeTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFeeTypeName(), FeeType_.feeTypeName));
            }
        }
        return specification;
    }

}
