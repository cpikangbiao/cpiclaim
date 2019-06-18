package com.cpi.claim.service;

import com.cpi.claim.service.dto.CasePaymentBillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CasePaymentBill}.
 */
public interface CasePaymentBillService {

    /**
     * Save a casePaymentBill.
     *
     * @param casePaymentBillDTO the entity to save.
     * @return the persisted entity.
     */
    CasePaymentBillDTO save(CasePaymentBillDTO casePaymentBillDTO);

    /**
     * Get all the casePaymentBills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CasePaymentBillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" casePaymentBill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CasePaymentBillDTO> findOne(Long id);

    /**
     * Delete the "id" casePaymentBill.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
