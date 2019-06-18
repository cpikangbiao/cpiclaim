package com.cpi.claim.service;

import com.cpi.claim.service.dto.FeeTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.FeeType}.
 */
public interface FeeTypeService {

    /**
     * Save a feeType.
     *
     * @param feeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    FeeTypeDTO save(FeeTypeDTO feeTypeDTO);

    /**
     * Get all the feeTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FeeTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" feeType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FeeTypeDTO> findOne(Long id);

    /**
     * Delete the "id" feeType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
