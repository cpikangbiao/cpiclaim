package com.cpi.claim.service;

import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CpiInsuranceType.
 */
public interface CpiInsuranceTypeService {

    /**
     * Save a cpiInsuranceType.
     *
     * @param cpiInsuranceTypeDTO the entity to save
     * @return the persisted entity
     */
    CpiInsuranceTypeDTO save(CpiInsuranceTypeDTO cpiInsuranceTypeDTO);

    /**
     * Get all the cpiInsuranceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CpiInsuranceTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" cpiInsuranceType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CpiInsuranceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" cpiInsuranceType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
