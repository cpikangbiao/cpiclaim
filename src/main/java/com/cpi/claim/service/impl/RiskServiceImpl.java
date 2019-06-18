package com.cpi.claim.service.impl;

import com.cpi.claim.service.RiskService;
import com.cpi.claim.domain.Risk;
import com.cpi.claim.repository.RiskRepository;
import com.cpi.claim.service.dto.RiskDTO;
import com.cpi.claim.service.mapper.RiskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Risk.
 */
@Service
@Transactional
public class RiskServiceImpl implements RiskService {

    private final Logger log = LoggerFactory.getLogger(RiskServiceImpl.class);

    private final RiskRepository riskRepository;

    private final RiskMapper riskMapper;

    public RiskServiceImpl(RiskRepository riskRepository, RiskMapper riskMapper) {
        this.riskRepository = riskRepository;
        this.riskMapper = riskMapper;
    }

    /**
     * Save a risk.
     *
     * @param riskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RiskDTO save(RiskDTO riskDTO) {
        log.debug("Request to save Risk : {}", riskDTO);
        Risk risk = riskMapper.toEntity(riskDTO);
        risk = riskRepository.save(risk);
        return riskMapper.toDto(risk);
    }

    /**
     * Get all the risks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RiskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Risks");
        return riskRepository.findAll(pageable)
            .map(riskMapper::toDto);
    }


    /**
     * Get one risk by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RiskDTO> findOne(Long id) {
        log.debug("Request to get Risk : {}", id);
        return riskRepository.findById(id)
            .map(riskMapper::toDto);
    }

    /**
     * Delete the risk by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Risk : {}", id);
        riskRepository.deleteById(id);
    }
}
