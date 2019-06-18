package com.cpi.claim.service;

import com.cpi.claim.service.dto.VesselCaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VesselCase.
 */
public interface VesselCaseService {

    /**
     * Save a vesselCase.
     *
     * @param vesselCaseDTO the entity to save
     * @return the persisted entity
     */
    VesselCaseDTO save(VesselCaseDTO vesselCaseDTO);

    /**
     * Get all the vesselCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VesselCaseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vesselCase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VesselCaseDTO> findOne(Long id);

    /**
     * Delete the "id" vesselCase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
