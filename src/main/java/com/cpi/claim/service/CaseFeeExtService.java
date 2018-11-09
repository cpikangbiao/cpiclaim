package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseFeeMapper;
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
 * Service for executing complex queries for CaseFee entities in the database.
 * The main input is a {@link CaseFeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseFeeDTO} or a {@link Page} of {@link CaseFeeDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class CaseFeeExtService extends QueryService<CaseFee> {

    private final Logger log = LoggerFactory.getLogger(CaseFeeExtService.class);

    private final CaseFeeRepository caseFeeRepository;

    private final CaseFeeMapper caseFeeMapper;

    public CaseFeeExtService(CaseFeeRepository caseFeeRepository, CaseFeeMapper caseFeeMapper) {
        this.caseFeeRepository = caseFeeRepository;
        this.caseFeeMapper = caseFeeMapper;
    }


    @Transactional(readOnly = true)
    public List<CaseFeeDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseFee> caseFees = caseFeeRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseFeeMapper.toDto(caseFees);
    }

    @Transactional(readOnly = true)
    public Page<CaseFeeDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseFee> caseFees = caseFeeRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseFees.map(caseFeeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = caseFeeRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }


}
