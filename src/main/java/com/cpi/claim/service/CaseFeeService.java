package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseFeeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseFee}.
 */
public interface CaseFeeService {

    /**
     * Save a caseFee.
     *
     * @param caseFeeDTO the entity to save.
     * @return the persisted entity.
     */
    CaseFeeDTO save(CaseFeeDTO caseFeeDTO);

    /**
     * Get all the caseFees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseFeeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseFee.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseFeeDTO> findOne(Long id);

    /**
     * Delete the "id" caseFee.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
