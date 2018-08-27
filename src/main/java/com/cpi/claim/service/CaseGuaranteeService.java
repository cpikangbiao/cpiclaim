package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseGuaranteeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CaseGuarantee.
 */
public interface CaseGuaranteeService {

    /**
     * Save a caseGuarantee.
     *
     * @param caseGuaranteeDTO the entity to save
     * @return the persisted entity
     */
    CaseGuaranteeDTO save(CaseGuaranteeDTO caseGuaranteeDTO);

    /**
     * Get all the caseGuarantees.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaseGuaranteeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseGuarantee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaseGuaranteeDTO> findOne(Long id);

    /**
     * Delete the "id" caseGuarantee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
