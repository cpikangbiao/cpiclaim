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

import com.cpi.claim.domain.ClaimBillType;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.ClaimBillTypeRepository;
import com.cpi.claim.service.dto.ClaimBillTypeCriteria;
import com.cpi.claim.service.dto.ClaimBillTypeDTO;
import com.cpi.claim.service.mapper.ClaimBillTypeMapper;

/**
 * Service for executing complex queries for {@link ClaimBillType} entities in the database.
 * The main input is a {@link ClaimBillTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClaimBillTypeDTO} or a {@link Page} of {@link ClaimBillTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClaimBillTypeQueryService extends QueryService<ClaimBillType> {

    private final Logger log = LoggerFactory.getLogger(ClaimBillTypeQueryService.class);

    private final ClaimBillTypeRepository claimBillTypeRepository;

    private final ClaimBillTypeMapper claimBillTypeMapper;

    public ClaimBillTypeQueryService(ClaimBillTypeRepository claimBillTypeRepository, ClaimBillTypeMapper claimBillTypeMapper) {
        this.claimBillTypeRepository = claimBillTypeRepository;
        this.claimBillTypeMapper = claimBillTypeMapper;
    }

    /**
     * Return a {@link List} of {@link ClaimBillTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClaimBillTypeDTO> findByCriteria(ClaimBillTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ClaimBillType> specification = createSpecification(criteria);
        return claimBillTypeMapper.toDto(claimBillTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClaimBillTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClaimBillTypeDTO> findByCriteria(ClaimBillTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ClaimBillType> specification = createSpecification(criteria);
        return claimBillTypeRepository.findAll(specification, page)
            .map(claimBillTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClaimBillTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ClaimBillType> specification = createSpecification(criteria);
        return claimBillTypeRepository.count(specification);
    }

    /**
     * Function to convert ClaimBillTypeCriteria to a {@link Specification}.
     */
    private Specification<ClaimBillType> createSpecification(ClaimBillTypeCriteria criteria) {
        Specification<ClaimBillType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ClaimBillType_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), ClaimBillType_.sortNum));
            }
            if (criteria.getClaimBillTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimBillTypeName(), ClaimBillType_.claimBillTypeName));
            }
        }
        return specification;
    }
}
