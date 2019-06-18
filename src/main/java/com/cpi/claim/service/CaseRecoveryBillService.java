package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseRecoveryBillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseRecoveryBill}.
 */
public interface CaseRecoveryBillService {

    /**
     * Save a caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the entity to save.
     * @return the persisted entity.
     */
    CaseRecoveryBillDTO save(CaseRecoveryBillDTO caseRecoveryBillDTO);

    /**
     * Get all the caseRecoveryBills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseRecoveryBillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseRecoveryBill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseRecoveryBillDTO> findOne(Long id);

    /**
     * Delete the "id" caseRecoveryBill.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
