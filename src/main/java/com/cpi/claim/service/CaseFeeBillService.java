package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseFeeBillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseFeeBill}.
 */
public interface CaseFeeBillService {

    /**
     * Save a caseFeeBill.
     *
     * @param caseFeeBillDTO the entity to save.
     * @return the persisted entity.
     */
    CaseFeeBillDTO save(CaseFeeBillDTO caseFeeBillDTO);

    /**
     * Get all the caseFeeBills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseFeeBillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseFeeBill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseFeeBillDTO> findOne(Long id);

    /**
     * Delete the "id" caseFeeBill.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
