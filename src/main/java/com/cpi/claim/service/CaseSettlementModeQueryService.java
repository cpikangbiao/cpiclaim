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

import com.cpi.claim.domain.CaseSettlementMode;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseSettlementModeRepository;
import com.cpi.claim.service.dto.CaseSettlementModeCriteria;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.mapper.CaseSettlementModeMapper;

/**
 * Service for executing complex queries for {@link CaseSettlementMode} entities in the database.
 * The main input is a {@link CaseSettlementModeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseSettlementModeDTO} or a {@link Page} of {@link CaseSettlementModeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseSettlementModeQueryService extends QueryService<CaseSettlementMode> {

    private final Logger log = LoggerFactory.getLogger(CaseSettlementModeQueryService.class);

    private final CaseSettlementModeRepository caseSettlementModeRepository;

    private final CaseSettlementModeMapper caseSettlementModeMapper;

    public CaseSettlementModeQueryService(CaseSettlementModeRepository caseSettlementModeRepository, CaseSettlementModeMapper caseSettlementModeMapper) {
        this.caseSettlementModeRepository = caseSettlementModeRepository;
        this.caseSettlementModeMapper = caseSettlementModeMapper;
    }

    /**
     * Return a {@link List} of {@link CaseSettlementModeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseSettlementModeDTO> findByCriteria(CaseSettlementModeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseSettlementMode> specification = createSpecification(criteria);
        return caseSettlementModeMapper.toDto(caseSettlementModeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseSettlementModeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseSettlementModeDTO> findByCriteria(CaseSettlementModeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseSettlementMode> specification = createSpecification(criteria);
        return caseSettlementModeRepository.findAll(specification, page)
            .map(caseSettlementModeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseSettlementModeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseSettlementMode> specification = createSpecification(criteria);
        return caseSettlementModeRepository.count(specification);
    }

    /**
     * Function to convert CaseSettlementModeCriteria to a {@link Specification}.
     */
    private Specification<CaseSettlementMode> createSpecification(CaseSettlementModeCriteria criteria) {
        Specification<CaseSettlementMode> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseSettlementMode_.id));
            }
            if (criteria.getSortNum() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSortNum(), CaseSettlementMode_.sortNum));
            }
            if (criteria.getSettlementModeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSettlementModeName(), CaseSettlementMode_.settlementModeName));
            }
        }
        return specification;
    }
}
