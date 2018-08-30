package com.cpi.claim.service;

import com.cpi.claim.service.dto.ClaimBillTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ClaimBillType.
 */
public interface ClaimBillTypeService {

    /**
     * Save a claimBillType.
     *
     * @param claimBillTypeDTO the entity to save
     * @return the persisted entity
     */
    ClaimBillTypeDTO save(ClaimBillTypeDTO claimBillTypeDTO);

    /**
     * Get all the claimBillTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ClaimBillTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" claimBillType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ClaimBillTypeDTO> findOne(Long id);

    /**
     * Delete the "id" claimBillType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
