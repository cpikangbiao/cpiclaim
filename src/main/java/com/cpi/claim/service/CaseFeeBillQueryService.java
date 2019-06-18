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

import com.cpi.claim.domain.CaseFeeBill;
import com.cpi.claim.domain.*; // for static metamodels
import com.cpi.claim.repository.CaseFeeBillRepository;
import com.cpi.claim.service.dto.CaseFeeBillCriteria;
import com.cpi.claim.service.dto.CaseFeeBillDTO;
import com.cpi.claim.service.mapper.CaseFeeBillMapper;

/**
 * Service for executing complex queries for {@link CaseFeeBill} entities in the database.
 * The main input is a {@link CaseFeeBillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseFeeBillDTO} or a {@link Page} of {@link CaseFeeBillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CaseFeeBillQueryService extends QueryService<CaseFeeBill> {

    private final Logger log = LoggerFactory.getLogger(CaseFeeBillQueryService.class);

    private final CaseFeeBillRepository caseFeeBillRepository;

    private final CaseFeeBillMapper caseFeeBillMapper;

    public CaseFeeBillQueryService(CaseFeeBillRepository caseFeeBillRepository, CaseFeeBillMapper caseFeeBillMapper) {
        this.caseFeeBillRepository = caseFeeBillRepository;
        this.caseFeeBillMapper = caseFeeBillMapper;
    }

    /**
     * Return a {@link List} of {@link CaseFeeBillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CaseFeeBillDTO> findByCriteria(CaseFeeBillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CaseFeeBill> specification = createSpecification(criteria);
        return caseFeeBillMapper.toDto(caseFeeBillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CaseFeeBillDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CaseFeeBillDTO> findByCriteria(CaseFeeBillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CaseFeeBill> specification = createSpecification(criteria);
        return caseFeeBillRepository.findAll(specification, page)
            .map(caseFeeBillMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CaseFeeBillCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CaseFeeBill> specification = createSpecification(criteria);
        return caseFeeBillRepository.count(specification);
    }

    /**
     * Function to convert CaseFeeBillCriteria to a {@link Specification}.
     */
    private Specification<CaseFeeBill> createSpecification(CaseFeeBillCriteria criteria) {
        Specification<CaseFeeBill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CaseFeeBill_.id));
            }
            if (criteria.getNumberId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberId(), CaseFeeBill_.numberId));
            }
            if (criteria.getIsWriteOff() != null) {
                specification = specification.and(buildSpecification(criteria.getIsWriteOff(), CaseFeeBill_.isWriteOff));
            }
            if (criteria.getCaseFeeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseFeeId(),
                    root -> root.join(CaseFeeBill_.caseFee, JoinType.LEFT).get(CaseFee_.id)));
            }
            if (criteria.getCaseClaimBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getCaseClaimBillId(),
                    root -> root.join(CaseFeeBill_.caseClaimBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
            if (criteria.getWriteOffBillId() != null) {
                specification = specification.and(buildSpecification(criteria.getWriteOffBillId(),
                    root -> root.join(CaseFeeBill_.writeOffBill, JoinType.LEFT).get(CaseClaimBill_.id)));
            }
        }
        return specification;
    }
}
