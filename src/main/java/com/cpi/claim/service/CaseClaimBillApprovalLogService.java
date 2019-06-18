package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseClaimBillApprovalLog.
 */
public interface CaseClaimBillApprovalLogService {

    /**
     * Save a caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the entity to save
     * @return the persisted entity
     */
    CaseClaimBillApprovalLogDTO save(CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO);

    /**
     * Get all the caseClaimBillApprovalLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseClaimBillApprovalLogDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseClaimBillApprovalLogDTO> findOne(Long id);

    /**
     * Delete the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
