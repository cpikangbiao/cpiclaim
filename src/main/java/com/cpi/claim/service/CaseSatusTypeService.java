package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseSatusTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseSatusType.
 */
public interface CaseSatusTypeService {

    /**
     * Save a caseSatusType.
     *
     * @param caseSatusTypeDTO the entity to save
     * @return the persisted entity
     */
    CaseSatusTypeDTO save(CaseSatusTypeDTO caseSatusTypeDTO);

    /**
     * Get all the caseSatusTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseSatusTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseSatusType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseSatusTypeDTO> findOne(Long id);

    /**
     * Delete the "id" caseSatusType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
