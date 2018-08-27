package com.cpi.claim.service.impl;

import com.cpi.claim.service.GuaranteeTypeService;
import com.cpi.claim.domain.GuaranteeType;
import com.cpi.claim.repository.GuaranteeTypeRepository;
import com.cpi.claim.service.dto.GuaranteeTypeDTO;
import com.cpi.claim.service.mapper.GuaranteeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing GuaranteeType.
 */
@Service
@Transactional
public class GuaranteeTypeServiceImpl implements GuaranteeTypeService {

    private final Logger log = LoggerFactory.getLogger(GuaranteeTypeServiceImpl.class);

    private final GuaranteeTypeRepository guaranteeTypeRepository;

    private final GuaranteeTypeMapper guaranteeTypeMapper;

    public GuaranteeTypeServiceImpl(GuaranteeTypeRepository guaranteeTypeRepository, GuaranteeTypeMapper guaranteeTypeMapper) {
        this.guaranteeTypeRepository = guaranteeTypeRepository;
        this.guaranteeTypeMapper = guaranteeTypeMapper;
    }

    /**
     * Save a guaranteeType.
     *
     * @param guaranteeTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GuaranteeTypeDTO save(GuaranteeTypeDTO guaranteeTypeDTO) {
        log.debug("Request to save GuaranteeType : {}", guaranteeTypeDTO);
        GuaranteeType guaranteeType = guaranteeTypeMapper.toEntity(guaranteeTypeDTO);
        guaranteeType = guaranteeTypeRepository.save(guaranteeType);
        return guaranteeTypeMapper.toDto(guaranteeType);
    }

    /**
     * Get all the guaranteeTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GuaranteeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GuaranteeTypes");
        return guaranteeTypeRepository.findAll(pageable)
            .map(guaranteeTypeMapper::toDto);
    }


    /**
     * Get one guaranteeType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GuaranteeTypeDTO> findOne(Long id) {
        log.debug("Request to get GuaranteeType : {}", id);
        return guaranteeTypeRepository.findById(id)
            .map(guaranteeTypeMapper::toDto);
    }

    /**
     * Delete the guaranteeType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GuaranteeType : {}", id);
        guaranteeTypeRepository.deleteById(id);
    }
}
