package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseClaimBillPrintLog.
 */
public interface CaseClaimBillPrintLogService {

    /**
     * Save a caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the entity to save
     * @return the persisted entity
     */
    CaseClaimBillPrintLogDTO save(CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO);

    /**
     * Get all the caseClaimBillPrintLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseClaimBillPrintLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseClaimBillPrintLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
