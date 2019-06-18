package com.cpi.claim.service;

import com.cpi.claim.service.dto.CreditorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Creditor.
 */
public interface CreditorService {

    /**
     * Save a creditor.
     *
     * @param creditorDTO the entity to save
     * @return the persisted entity
     */
    CreditorDTO save(CreditorDTO creditorDTO);

    /**
     * Get all the creditors.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CreditorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" creditor.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CreditorDTO> findOne(Long id);

    /**
     * Delete the "id" creditor.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
