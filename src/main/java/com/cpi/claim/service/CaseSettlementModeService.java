package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseSettlementModeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseSettlementMode.
 */
public interface CaseSettlementModeService {

    /**
     * Save a caseSettlementMode.
     *
     * @param caseSettlementModeDTO the entity to save
     * @return the persisted entity
     */
    CaseSettlementModeDTO save(CaseSettlementModeDTO caseSettlementModeDTO);

    /**
     * Get all the caseSettlementModes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseSettlementModeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseSettlementMode.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseSettlementModeDTO> findOne(Long id);

    /**
     * Delete the "id" caseSettlementMode.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
