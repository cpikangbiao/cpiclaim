package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseRegisterLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseRegisterLog}.
 */
public interface CaseRegisterLogService {

    /**
     * Save a caseRegisterLog.
     *
     * @param caseRegisterLogDTO the entity to save.
     * @return the persisted entity.
     */
    CaseRegisterLogDTO save(CaseRegisterLogDTO caseRegisterLogDTO);

    /**
     * Get all the caseRegisterLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseRegisterLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseRegisterLog.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseRegisterLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseRegisterLog.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
