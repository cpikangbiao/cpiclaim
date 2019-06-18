package com.cpi.claim.service;

import com.cpi.claim.service.dto.CaseStatusTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.CaseStatusType}.
 */
public interface CaseStatusTypeService {

    /**
     * Save a caseStatusType.
     *
     * @param caseStatusTypeDTO the entity to save.
     * @return the persisted entity.
     */
    CaseStatusTypeDTO save(CaseStatusTypeDTO caseStatusTypeDTO);

    /**
     * Get all the caseStatusTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CaseStatusTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caseStatusType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CaseStatusTypeDTO> findOne(Long id);

    /**
     * Delete the "id" caseStatusType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
