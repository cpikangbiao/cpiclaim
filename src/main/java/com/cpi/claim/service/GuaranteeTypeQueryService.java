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

import com.cpi.claim.domain.GuaranteeType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.GuaranteeTypeRepository;
import com.cpi.claim.service.dto.GuaranteeTypeCriteria;

import com.cpi.claim.service.dto.GuaranteeTypeDTO;
import com.cpi.claim.service.mapper.GuaranteeTypeMapper;

/**
 * Service for executing complex queries for GuaranteeType entities in the database.
 * The main input is a {@link GuaranteeTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GuaranteeTypeDTO} or a {@link Page} of {@link GuaranteeTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GuaranteeTypeQueryService extends QueryService<GuaranteeType> {

    private final Logger log = LoggerFactory.getLogger(GuaranteeTypeQueryService.class);

    private final GuaranteeTypeRepository guaranteeTypeRepository;

    private final GuaranteeTypeMapper guaranteeTypeMapper;

    public GuaranteeTypeQueryService(GuaranteeTypeRepository guaranteeTypeRepository, GuaranteeTypeMapper guaranteeTypeMapper) {
        this.guaranteeTypeRepository = guaranteeTypeRepository;
        this.guaranteeTypeMapper = guaranteeTypeMapper;
    }

    /**
     * Return a {@link List} of {@link GuaranteeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GuaranteeTypeDTO> findByCriteria(GuaranteeTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<GuaranteeType> specification = createSpecification(criteria);
        return guaranteeTypeMapper.toDto(guaranteeTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GuaranteeTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GuaranteeTypeDTO> findByCriteria(GuaranteeTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<GuaranteeType> specification = createSpecification(criteria);
        return guaranteeTypeRepository.findAll(specification, page)
            .map(guaranteeTypeMapper::toDto);
    }

    /**
     * Function to convert GuaranteeTypeCriteria to a {@link Specification}
     */
    private Specification<GuaranteeType> createSpecification(GuaranteeTypeCriteria criteria) {
        Specification<GuaranteeType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), GuaranteeType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), GuaranteeType_.sortNum));
            }
            if (criteria.getGuaranteeTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuaranteeTypeName(), GuaranteeType_.guaranteeTypeName));
            }
        }
        return specification;
    }

}
