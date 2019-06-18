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

import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CpiInsuranceTypeRepository;
import com.cpi.claim.service.dto.CpiInsuranceTypeCriteria;

import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;
import com.cpi.claim.service.mapper.CpiInsuranceTypeMapper;

/**
 * Service for executing complex queries for CpiInsuranceType entities in the database.
 * The main input is a {@link CpiInsuranceTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CpiInsuranceTypeDTO} or a {@link Page} of {@link CpiInsuranceTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CpiInsuranceTypeQueryService extends QueryService<CpiInsuranceType> {

    private final Logger log = LoggerFactory.getLogger(CpiInsuranceTypeQueryService.class);

    private final CpiInsuranceTypeRepository cpiInsuranceTypeRepository;

    private final CpiInsuranceTypeMapper cpiInsuranceTypeMapper;

    public CpiInsuranceTypeQueryService(CpiInsuranceTypeRepository cpiInsuranceTypeRepository, CpiInsuranceTypeMapper cpiInsuranceTypeMapper) {
        this.cpiInsuranceTypeRepository = cpiInsuranceTypeRepository;
        this.cpiInsuranceTypeMapper = cpiInsuranceTypeMapper;
    }

    /**
     * Return a {@link List} of {@link CpiInsuranceTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CpiInsuranceTypeDTO> findByCriteria(CpiInsuranceTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CpiInsuranceType> specification = createSpecification(criteria);
        return cpiInsuranceTypeMapper.toDto(cpiInsuranceTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CpiInsuranceTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CpiInsuranceTypeDTO> findByCriteria(CpiInsuranceTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CpiInsuranceType> specification = createSpecification(criteria);
        return cpiInsuranceTypeRepository.findAll(specification, page)
            .map(cpiInsuranceTypeMapper::toDto);
    }

    /**
     * Function to convert CpiInsuranceTypeCriteria to a {@link Specification}
     */
    private Specification<CpiInsuranceType> createSpecification(CpiInsuranceTypeCriteria criteria) {
        Specification<CpiInsuranceType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CpiInsuranceType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), CpiInsuranceType_.sortNum));
            }
            if (criteria.getCpiInsuranceTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCpiInsuranceTypeName(), CpiInsuranceType_.cpiInsuranceTypeName));
            }
        }
        return specification;
    }

}
