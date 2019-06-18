package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseRecoveryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseRecovery}.
 */
public interface CaseRecoveryService {

    /**
     * Save a caseRecovery.
     *
     * @param caseRecoveryDTO the entity to save.
     * @return the persisted entity.
     */
    CaseRecoveryDTO save(CaseRecoveryDTO caseRecoveryDTO);

    /**
     * Get all the caseRecoveries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseRecoveryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseRecovery.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseRecoveryDTO> findOne(Long id);

    /**
     * Delete the "id" caseRecovery.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
