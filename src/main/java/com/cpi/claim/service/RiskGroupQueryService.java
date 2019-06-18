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

import com.cpi.claim.domain.RiskGroup;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.RiskGroupRepository;
import com.cpi.claim.service.dto.RiskGroupCriteria;

import com.cpi.claim.service.dto.RiskGroupDTO;
import com.cpi.claim.service.mapper.RiskGroupMapper;

/**
 * Service for executing complex queries for RiskGroup entities in the database.
 * The main input is a {@link RiskGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RiskGroupDTO} or a {@link Page} of {@link RiskGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RiskGroupQueryService extends QueryService<RiskGroup> {

    private final Logger log = LoggerFactory.getLogger(RiskGroupQueryService.class);

    private final RiskGroupRepository riskGroupRepository;

    private final RiskGroupMapper riskGroupMapper;

    public RiskGroupQueryService(RiskGroupRepository riskGroupRepository, RiskGroupMapper riskGroupMapper) {
        this.riskGroupRepository = riskGroupRepository;
        this.riskGroupMapper = riskGroupMapper;
    }

    /**
     * Return a {@link List} of {@link RiskGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RiskGroupDTO> findByCriteria(RiskGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RiskGroup> specification = createSpecification(criteria);
        return riskGroupMapper.toDto(riskGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RiskGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskGroupDTO> findByCriteria(RiskGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RiskGroup> specification = createSpecification(criteria);
        return riskGroupRepository.findAll(specification, page)
            .map(riskGroupMapper::toDto);
    }

    /**
     * Function to convert RiskGroupCriteria to a {@link Specification}
     */
    private Specification<RiskGroup> createSpecification(RiskGroupCriteria criteria) {
        Specification<RiskGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RiskGroup_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), RiskGroup_.sortNum));
            }
            if (criteria.getRiskGroupName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRiskGroupName(), RiskGroup_.riskGroupName));
            }
        }
        return specification;
    }

}
