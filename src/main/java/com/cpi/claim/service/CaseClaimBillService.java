package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseClaimBillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseClaimBill.
 */
public interface CaseClaimBillService {

    /**
     * Save a caseClaimBill.
     *
     * @param caseClaimBillDTO the entity to save
     * @return the persisted entity
     */
    CaseClaimBillDTO save(CaseClaimBillDTO caseClaimBillDTO);

    /**
     * Get all the caseClaimBills.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseClaimBillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseClaimBill.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseClaimBillDTO> findOne(Long id);

    /**
     * Delete the "id" caseClaimBill.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
