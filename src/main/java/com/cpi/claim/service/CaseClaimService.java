package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseClaimDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseClaim.
 */
public interface CaseClaimService {

    /**
     * Save a caseClaim.
     *
     * @param caseClaimDTO the entity to save
     * @return the persisted entity
     */
    CaseClaimDTO save(CaseClaimDTO caseClaimDTO);

    /**
     * Get all the caseClaims.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseClaimDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseClaim.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseClaimDTO> findOne(Long id);

    /**
     * Delete the "id" caseClaim.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
