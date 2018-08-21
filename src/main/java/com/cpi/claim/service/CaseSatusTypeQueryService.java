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

import com.cpi.claim.domain.CaseSatusType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseSatusTypeRepository;
import com.cpi.claim.service.dto.CaseSatusTypeCriteria;

import com.cpi.claim.service.dto.CaseSatusTypeDTO;
import com.cpi.claim.service.mapper.CaseSatusTypeMapper;

/**
 * Service for executing complex queries for CaseSatusType entities in the database.
 * The main input is a {@link CaseSatusTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseSatusTypeDTO} or a {@link Page} of {@link CaseSatusTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseSatusTypeQueryService extends QueryService<CaseSatusType> {

    private final Logger log = LoggerFactory.getLogger(CaseSatusTypeQueryService.class);

    private final CaseSatusTypeRepository caseSatusTypeRepository;

    private final CaseSatusTypeMapper caseSatusTypeMapper;

    public CaseSatusTypeQueryService(CaseSatusTypeRepository caseSatusTypeRepository, CaseSatusTypeMapper caseSatusTypeMapper) {
        this.caseSatusTypeRepository = caseSatusTypeRepository;
        this.caseSatusTypeMapper = caseSatusTypeMapper;
    }

    /**
     * Return a {@link List} of {@link CaseSatusTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseSatusTypeDTO> findByCriteria(CaseSatusTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseSatusType> specification = createSpecification(criteria);
        return caseSatusTypeMapper.toDto(caseSatusTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseSatusTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseSatusTypeDTO> findByCriteria(CaseSatusTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseSatusType> specification = createSpecification(criteria);
        return caseSatusTypeRepository.findAll(specification, page)
            .map(caseSatusTypeMapper::toDto);
    }

    /**
     * Function to convert CaseSatusTypeCriteria to a {@link Specification}
     */
    private Specification<CaseSatusType> createSpecification(CaseSatusTypeCriteria criteria) {
        Specification<CaseSatusType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseSatusType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), CaseSatusType_.sortNum));
            }
            if (criteria.getCaseStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCaseStatusName(), CaseSatusType_.caseStatusName));
            }
        }
        return specification;
    }

}
