package com.cpi.claim.service;

import com.cpi.claim.service.dto.CasePaymentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CasePayment}.
 */
public interface CasePaymentService {

    /**
     * Save a casePayment.
     *
     * @param casePaymentDTO the entity to save.
     * @return the persisted entity.
     */
    CasePaymentDTO save(CasePaymentDTO casePaymentDTO);

    /**
     * Get all the casePayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CasePaymentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" casePayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CasePaymentDTO> findOne(Long id);

    /**
     * Delete the "id" casePayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
