package com.cpi.claim.service;

import com.cpi.claim.service.dto.GuaranteeTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.GuaranteeType}.
 */
public interface GuaranteeTypeService {

    /**
     * Save a guaranteeType.
     *
     * @param guaranteeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    GuaranteeTypeDTO save(GuaranteeTypeDTO guaranteeTypeDTO);

    /**
     * Get all the guaranteeTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GuaranteeTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" guaranteeType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GuaranteeTypeDTO> findOne(Long id);

    /**
     * Delete the "id" guaranteeType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
