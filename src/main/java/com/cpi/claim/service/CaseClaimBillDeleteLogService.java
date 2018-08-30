package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseClaimBillDeleteLog.
 */
public interface CaseClaimBillDeleteLogService {

    /**
     * Save a caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the entity to save
     * @return the persisted entity
     */
    CaseClaimBillDeleteLogDTO save(CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO);

    /**
     * Get all the caseClaimBillDeleteLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseClaimBillDeleteLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseClaimBillDeleteLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
