package com.cpi.claim.service.impl;

import com.cpi.claim.service.RecoveryTypeService;
import com.cpi.claim.domain.RecoveryType;
import com.cpi.claim.repository.RecoveryTypeRepository;
import com.cpi.claim.service.dto.RecoveryTypeDTO;
import com.cpi.claim.service.mapper.RecoveryTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RecoveryType}.
 */
@Service
@Transactional
public class RecoveryTypeServiceImpl implements RecoveryTypeService {

    private final Logger log = LoggerFactory.getLogger(RecoveryTypeServiceImpl.class);

    private final RecoveryTypeRepository recoveryTypeRepository;

    private final RecoveryTypeMapper recoveryTypeMapper;

    public RecoveryTypeServiceImpl(RecoveryTypeRepository recoveryTypeRepository, RecoveryTypeMapper recoveryTypeMapper) {
        this.recoveryTypeRepository = recoveryTypeRepository;
        this.recoveryTypeMapper = recoveryTypeMapper;
    }

    /**
     * Save a recoveryType.
     *
     * @param recoveryTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RecoveryTypeDTO save(RecoveryTypeDTO recoveryTypeDTO) {
        log.debug("Request to save RecoveryType : {}", recoveryTypeDTO);
        RecoveryType recoveryType = recoveryTypeMapper.toEntity(recoveryTypeDTO);
        recoveryType = recoveryTypeRepository.save(recoveryType);
        return recoveryTypeMapper.toDto(recoveryType);
    }

    /**
     * Get all the recoveryTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RecoveryTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RecoveryTypes");
        return recoveryTypeRepository.findAll(pageable)
            .map(recoveryTypeMapper::toDto);
    }


    /**
     * Get one recoveryType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RecoveryTypeDTO> findOne(Long id) {
        log.debug("Request to get RecoveryType : {}", id);
        return recoveryTypeRepository.findById(id)
            .map(recoveryTypeMapper::toDto);
    }

    /**
     * Delete the recoveryType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RecoveryType : {}", id);
        recoveryTypeRepository.deleteById(id);
    }
}
