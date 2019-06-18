package com.cpi.claim.service;

import com.cpi.claim.service.dto.RecoveryTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.RecoveryType}.
 */
public interface RecoveryTypeService {

    /**
     * Save a recoveryType.
     *
     * @param recoveryTypeDTO the entity to save.
     * @return the persisted entity.
     */
    RecoveryTypeDTO save(RecoveryTypeDTO recoveryTypeDTO);

    /**
     * Get all the recoveryTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RecoveryTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" recoveryType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecoveryTypeDTO> findOne(Long id);

    /**
     * Delete the "id" recoveryType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
