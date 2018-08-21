package com.cpi.claim.service;

import com.cpi.claim.service.dto.RiskGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RiskGroup.
 */
public interface RiskGroupService {

    /**
     * Save a riskGroup.
     *
     * @param riskGroupDTO the entity to save
     * @return the persisted entity
     */
    RiskGroupDTO save(RiskGroupDTO riskGroupDTO);

    /**
     * Get all the riskGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RiskGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" riskGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RiskGroupDTO> findOne(Long id);

    /**
     * Delete the "id" riskGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
