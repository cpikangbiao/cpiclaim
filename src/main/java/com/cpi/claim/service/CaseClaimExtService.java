package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.repository.CaseClaimRepository;
import com.cpi.claim.service.dto.CaseClaimCriteria;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.mapper.CaseClaimMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for CaseClaim entities in the database.
 * The main input is a {@link CaseClaimCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseClaimDTO} or a {@link Page} of {@link CaseClaimDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class CaseClaimExtService extends QueryService<CaseClaim> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimExtService.class);

    private final CaseClaimRepository caseClaimRepository;

    private final CaseClaimMapper caseClaimMapper;

    public CaseClaimExtService(CaseClaimRepository caseClaimRepository, CaseClaimMapper caseClaimMapper) {
        this.caseClaimRepository = caseClaimRepository;
        this.caseClaimMapper = caseClaimMapper;
    }

    @Transactional(readOnly = true)
    public List<CaseClaimDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseClaim> caseClaims = caseClaimRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseClaimMapper.toDto(caseClaims);
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseClaim> caseClaims = caseClaimRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseClaims.map(caseClaimMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = caseClaimRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }

}
