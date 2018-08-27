package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseEstimateService;
import com.cpi.claim.domain.CaseEstimate;
import com.cpi.claim.repository.CaseEstimateRepository;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.mapper.CaseEstimateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseEstimate.
 */
@Service
@Transactional
public class CaseEstimateServiceImpl implements CaseEstimateService {

    private final Logger log = LoggerFactory.getLogger(CaseEstimateServiceImpl.class);

    private final CaseEstimateRepository caseEstimateRepository;

    private final CaseEstimateMapper caseEstimateMapper;

    public CaseEstimateServiceImpl(CaseEstimateRepository caseEstimateRepository, CaseEstimateMapper caseEstimateMapper) {
        this.caseEstimateRepository = caseEstimateRepository;
        this.caseEstimateMapper = caseEstimateMapper;
    }

    /**
     * Save a caseEstimate.
     *
     * @param caseEstimateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseEstimateDTO save(CaseEstimateDTO caseEstimateDTO) {
        log.debug("Request to save CaseEstimate : {}", caseEstimateDTO);
        CaseEstimate caseEstimate = caseEstimateMapper.toEntity(caseEstimateDTO);
        caseEstimate = caseEstimateRepository.save(caseEstimate);
        return caseEstimateMapper.toDto(caseEstimate);
    }

    /**
     * Get all the caseEstimates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseEstimateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseEstimates");
        return caseEstimateRepository.findAll(pageable)
            .map(caseEstimateMapper::toDto);
    }


    /**
     * Get one caseEstimate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseEstimateDTO> findOne(Long id) {
        log.debug("Request to get CaseEstimate : {}", id);
        return caseEstimateRepository.findById(id)
            .map(caseEstimateMapper::toDto);
    }

    /**
     * Delete the caseEstimate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseEstimate : {}", id);
        caseEstimateRepository.deleteById(id);
    }
}
