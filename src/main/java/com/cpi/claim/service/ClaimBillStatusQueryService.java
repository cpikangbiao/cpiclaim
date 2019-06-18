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

import com.cpi.claim.domain.ClaimBillStatus;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.ClaimBillStatusRepository;
import com.cpi.claim.service.dto.ClaimBillStatusCriteria;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;
import com.cpi.claim.service.mapper.ClaimBillStatusMapper;

/**
 * Service for executing complex queries for {@link ClaimBillStatus} entities in the database.
 * The main input is a {@link ClaimBillStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClaimBillStatusDTO} or a {@link Page} of {@link ClaimBillStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClaimBillStatusQueryService extends QueryService<ClaimBillStatus> {

    private final Logger log = LoggerFactory.getLogger(ClaimBillStatusQueryService.class);

    private final ClaimBillStatusRepository claimBillStatusRepository;

    private final ClaimBillStatusMapper claimBillStatusMapper;

    public ClaimBillStatusQueryService(ClaimBillStatusRepository claimBillStatusRepository, ClaimBillStatusMapper claimBillStatusMapper) {
        this.claimBillStatusRepository = claimBillStatusRepository;
        this.claimBillStatusMapper = claimBillStatusMapper;
    }

    /**
     * Return a {@link List} of {@link ClaimBillStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClaimBillStatusDTO> findByCriteria(ClaimBillStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ClaimBillStatus> specification = createSpecification(criteria);
        return claimBillStatusMapper.toDto(claimBillStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClaimBillStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClaimBillStatusDTO> findByCriteria(ClaimBillStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ClaimBillStatus> specification = createSpecification(criteria);
        return claimBillStatusRepository.findAll(specification, page)
            .map(claimBillStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClaimBillStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ClaimBillStatus> specification = createSpecification(criteria);
        return claimBillStatusRepository.count(specification);
    }

    /**
     * Function to convert ClaimBillStatusCriteria to a {@link Specification}.
     */
    private Specification<ClaimBillStatus> createSpecification(ClaimBillStatusCriteria criteria) {
        Specification<ClaimBillStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ClaimBillStatus_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), ClaimBillStatus_.sortNum));
            }
            if (criteria.getClaimBillStatusName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClaimBillStatusName(), ClaimBillStatus_.claimBillStatusName));
            }
        }
        return specification;
    }
}
