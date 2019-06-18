package com.cpi.claim.service.impl;

import com.cpi.claim.service.FeeTypeService;
import com.cpi.claim.domain.FeeType;
import com.cpi.claim.repository.FeeTypeRepository;
import com.cpi.claim.service.dto.FeeTypeDTO;
import com.cpi.claim.service.mapper.FeeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing FeeType.
 */
@Service
@Transactional
public class FeeTypeServiceImpl implements FeeTypeService {

    private final Logger log = LoggerFactory.getLogger(FeeTypeServiceImpl.class);

    private final FeeTypeRepository feeTypeRepository;

    private final FeeTypeMapper feeTypeMapper;

    public FeeTypeServiceImpl(FeeTypeRepository feeTypeRepository, FeeTypeMapper feeTypeMapper) {
        this.feeTypeRepository = feeTypeRepository;
        this.feeTypeMapper = feeTypeMapper;
    }

    /**
     * Save a feeType.
     *
     * @param feeTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeeTypeDTO save(FeeTypeDTO feeTypeDTO) {
        log.debug("Request to save FeeType : {}", feeTypeDTO);
        FeeType feeType = feeTypeMapper.toEntity(feeTypeDTO);
        feeType = feeTypeRepository.save(feeType);
        return feeTypeMapper.toDto(feeType);
    }

    /**
     * Get all the feeTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FeeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FeeTypes");
        return feeTypeRepository.findAll(pageable)
            .map(feeTypeMapper::toDto);
    }


    /**
     * Get one feeType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeeTypeDTO> findOne(Long id) {
        log.debug("Request to get FeeType : {}", id);
        return feeTypeRepository.findById(id)
            .map(feeTypeMapper::toDto);
    }

    /**
     * Delete the feeType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FeeType : {}", id);
        feeTypeRepository.deleteById(id);
    }
}
