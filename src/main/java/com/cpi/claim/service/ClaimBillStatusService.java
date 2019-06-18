package com.cpi.claim.service;

import com.cpi.claim.service.dto.ClaimBillStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.ClaimBillStatus}.
 */
public interface ClaimBillStatusService {

    /**
     * Save a claimBillStatus.
     *
     * @param claimBillStatusDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimBillStatusDTO save(ClaimBillStatusDTO claimBillStatusDTO);

    /**
     * Get all the claimBillStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimBillStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" claimBillStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimBillStatusDTO> findOne(Long id);

    /**
     * Delete the "id" claimBillStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
