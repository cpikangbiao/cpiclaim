package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseSettlementModeService;
import com.cpi.claim.domain.CaseSettlementMode;
import com.cpi.claim.repository.CaseSettlementModeRepository;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.mapper.CaseSettlementModeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseSettlementMode.
 */
@Service
@Transactional
public class CaseSettlementModeServiceImpl implements CaseSettlementModeService {

    private final Logger log = LoggerFactory.getLogger(CaseSettlementModeServiceImpl.class);

    private final CaseSettlementModeRepository caseSettlementModeRepository;

    private final CaseSettlementModeMapper caseSettlementModeMapper;

    public CaseSettlementModeServiceImpl(CaseSettlementModeRepository caseSettlementModeRepository, CaseSettlementModeMapper caseSettlementModeMapper) {
        this.caseSettlementModeRepository = caseSettlementModeRepository;
        this.caseSettlementModeMapper = caseSettlementModeMapper;
    }

    /**
     * Save a caseSettlementMode.
     *
     * @param caseSettlementModeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseSettlementModeDTO save(CaseSettlementModeDTO caseSettlementModeDTO) {
        log.debug("Request to save CaseSettlementMode : {}", caseSettlementModeDTO);
        CaseSettlementMode caseSettlementMode = caseSettlementModeMapper.toEntity(caseSettlementModeDTO);
        caseSettlementMode = caseSettlementModeRepository.save(caseSettlementMode);
        return caseSettlementModeMapper.toDto(caseSettlementMode);
    }

    /**
     * Get all the caseSettlementModes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseSettlementModeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseSettlementModes");
        return caseSettlementModeRepository.findAll(pageable)
            .map(caseSettlementModeMapper::toDto);
    }


    /**
     * Get one caseSettlementMode by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseSettlementModeDTO> findOne(Long id) {
        log.debug("Request to get CaseSettlementMode : {}", id);
        return caseSettlementModeRepository.findById(id)
            .map(caseSettlementModeMapper::toDto);
    }

    /**
     * Delete the caseSettlementMode by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseSettlementMode : {}", id);
        caseSettlementModeRepository.deleteById(id);
    }
}
