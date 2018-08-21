package com.cpi.claim.service;

import com.cpi.claim.service.dto.VesselSubCaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing VesselSubCase.
 */
public interface VesselSubCaseService {

    /**
     * Save a vesselSubCase.
     *
     * @param vesselSubCaseDTO the entity to save
     * @return the persisted entity
     */
    VesselSubCaseDTO save(VesselSubCaseDTO vesselSubCaseDTO);

    /**
     * Get all the vesselSubCases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<VesselSubCaseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" vesselSubCase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VesselSubCaseDTO> findOne(Long id);

    /**
     * Delete the "id" vesselSubCase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
