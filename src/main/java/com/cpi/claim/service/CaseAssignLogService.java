package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseAssignLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseAssignLog.
 */
public interface CaseAssignLogService {

    /**
     * Save a caseAssignLog.
     *
     * @param caseAssignLogDTO the entity to save
     * @return the persisted entity
     */
    CaseAssignLogDTO save(CaseAssignLogDTO caseAssignLogDTO);

    /**
     * Get all the caseAssignLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseAssignLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseAssignLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseAssignLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseAssignLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
