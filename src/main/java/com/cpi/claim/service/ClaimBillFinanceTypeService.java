package com.cpi.claim.service;

import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cpi.claim.domain.ClaimBillFinanceType}.
 */
public interface ClaimBillFinanceTypeService {

    /**
     * Save a claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ClaimBillFinanceTypeDTO save(ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO);

    /**
     * Get all the claimBillFinanceTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ClaimBillFinanceTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" claimBillFinanceType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClaimBillFinanceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" claimBillFinanceType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
