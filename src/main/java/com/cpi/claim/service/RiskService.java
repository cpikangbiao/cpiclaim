package com.cpi.claim.service;

import com.cpi.claim.service.dto.RiskDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Risk.
 */
public interface RiskService {

    /**
     * Save a risk.
     *
     * @param riskDTO the entity to save
     * @return the persisted entity
     */
    RiskDTO save(RiskDTO riskDTO);

    /**
     * Get all the risks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RiskDTO> findAll(Pageable pageable);


    /**
     * Get the "id" risk.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RiskDTO> findOne(Long id);

    /**
     * Delete the "id" risk.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
