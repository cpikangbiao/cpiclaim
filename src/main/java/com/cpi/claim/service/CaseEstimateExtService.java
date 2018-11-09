package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.CaseEstimate;
import com.cpi.claim.domain.CaseEstimate_;
import com.cpi.claim.domain.VesselSubCase_;
import com.cpi.claim.repository.CaseEstimateRepository;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.dto.CaseEstimateCriteria;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.mapper.CaseEstimateMapper;
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
 * Service for executing complex queries for CaseEstimate entities in the database.
 * The main input is a {@link CaseEstimateCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseEstimateDTO} or a {@link Page} of {@link CaseEstimateDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class CaseEstimateExtService extends QueryService<CaseEstimate> {

    private final Logger log = LoggerFactory.getLogger(CaseEstimateExtService.class);

    private final CaseEstimateRepository caseEstimateRepository;

    private final CaseEstimateMapper caseEstimateMapper;

    public CaseEstimateExtService(CaseEstimateRepository caseEstimateRepository, CaseEstimateMapper caseEstimateMapper) {
        this.caseEstimateRepository = caseEstimateRepository;
        this.caseEstimateMapper = caseEstimateMapper;
    }


    @Transactional(readOnly = true)
    public List<CaseEstimateDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseEstimate> caseEstimates = caseEstimateRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseEstimateMapper.toDto(caseEstimates);
    }

    @Transactional(readOnly = true)
    public Page<CaseEstimateDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseEstimate> caseEstimates = caseEstimateRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseEstimates.map(caseEstimateMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = caseEstimateRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }



}
