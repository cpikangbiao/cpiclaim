package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseSatusTypeService;
import com.cpi.claim.domain.CaseSatusType;
import com.cpi.claim.repository.CaseSatusTypeRepository;
import com.cpi.claim.service.dto.CaseSatusTypeDTO;
import com.cpi.claim.service.mapper.CaseSatusTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseSatusType.
 */
@Service
@Transactional
public class CaseSatusTypeServiceImpl implements CaseSatusTypeService {

    private final Logger log = LoggerFactory.getLogger(CaseSatusTypeServiceImpl.class);

    private final CaseSatusTypeRepository caseSatusTypeRepository;

    private final CaseSatusTypeMapper caseSatusTypeMapper;

    public CaseSatusTypeServiceImpl(CaseSatusTypeRepository caseSatusTypeRepository, CaseSatusTypeMapper caseSatusTypeMapper) {
        this.caseSatusTypeRepository = caseSatusTypeRepository;
        this.caseSatusTypeMapper = caseSatusTypeMapper;
    }

    /**
     * Save a caseSatusType.
     *
     * @param caseSatusTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseSatusTypeDTO save(CaseSatusTypeDTO caseSatusTypeDTO) {
        log.debug("Request to save CaseSatusType : {}", caseSatusTypeDTO);
        CaseSatusType caseSatusType = caseSatusTypeMapper.toEntity(caseSatusTypeDTO);
        caseSatusType = caseSatusTypeRepository.save(caseSatusType);
        return caseSatusTypeMapper.toDto(caseSatusType);
    }

    /**
     * Get all the caseSatusTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseSatusTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseSatusTypes");
        return caseSatusTypeRepository.findAll(pageable)
            .map(caseSatusTypeMapper::toDto);
    }


    /**
     * Get one caseSatusType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseSatusTypeDTO> findOne(Long id) {
        log.debug("Request to get CaseSatusType : {}", id);
        return caseSatusTypeRepository.findById(id)
            .map(caseSatusTypeMapper::toDto);
    }

    /**
     * Delete the caseSatusType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseSatusType : {}", id);
        caseSatusTypeRepository.deleteById(id);
    }
}
