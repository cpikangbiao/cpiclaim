package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseEstimateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseEstimate}.
 */
public interface CaseEstimateService {

    /**
     * Save a caseEstimate.
     *
     * @param caseEstimateDTO the entity to save.
     * @return the persisted entity.
     */
    CaseEstimateDTO save(CaseEstimateDTO caseEstimateDTO);

    /**
     * Get all the caseEstimates.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseEstimateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseEstimate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseEstimateDTO> findOne(Long id);

    /**
     * Delete the "id" caseEstimate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
