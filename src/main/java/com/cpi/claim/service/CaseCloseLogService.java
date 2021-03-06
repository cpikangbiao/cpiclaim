package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseCloseLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseCloseLog}.
 */
public interface CaseCloseLogService {

    /**
     * Save a caseCloseLog.
     *
     * @param caseCloseLogDTO the entity to save.
     * @return the persisted entity.
     */
    CaseCloseLogDTO save(CaseCloseLogDTO caseCloseLogDTO);

    /**
     * Get all the caseCloseLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseCloseLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseCloseLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseCloseLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseCloseLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
