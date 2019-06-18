package com.cpi.claim.service.impl;

import com.cpi.claim.service.CpiInsuranceTypeService;
import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.repository.CpiInsuranceTypeRepository;
import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;
import com.cpi.claim.service.mapper.CpiInsuranceTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CpiInsuranceType.
 */
@Service
@Transactional
public class CpiInsuranceTypeServiceImpl implements CpiInsuranceTypeService {

    private final Logger log = LoggerFactory.getLogger(CpiInsuranceTypeServiceImpl.class);

    private final CpiInsuranceTypeRepository cpiInsuranceTypeRepository;

    private final CpiInsuranceTypeMapper cpiInsuranceTypeMapper;

    public CpiInsuranceTypeServiceImpl(CpiInsuranceTypeRepository cpiInsuranceTypeRepository, CpiInsuranceTypeMapper cpiInsuranceTypeMapper) {
        this.cpiInsuranceTypeRepository = cpiInsuranceTypeRepository;
        this.cpiInsuranceTypeMapper = cpiInsuranceTypeMapper;
    }

    /**
     * Save a cpiInsuranceType.
     *
     * @param cpiInsuranceTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CpiInsuranceTypeDTO save(CpiInsuranceTypeDTO cpiInsuranceTypeDTO) {
        log.debug("Request to save CpiInsuranceType : {}", cpiInsuranceTypeDTO);
        CpiInsuranceType cpiInsuranceType = cpiInsuranceTypeMapper.toEntity(cpiInsuranceTypeDTO);
        cpiInsuranceType = cpiInsuranceTypeRepository.save(cpiInsuranceType);
        return cpiInsuranceTypeMapper.toDto(cpiInsuranceType);
    }

    /**
     * Get all the cpiInsuranceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CpiInsuranceTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CpiInsuranceTypes");
        return cpiInsuranceTypeRepository.findAll(pageable)
            .map(cpiInsuranceTypeMapper::toDto);
    }


    /**
     * Get one cpiInsuranceType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CpiInsuranceTypeDTO> findOne(Long id) {
        log.debug("Request to get CpiInsuranceType : {}", id);
        return cpiInsuranceTypeRepository.findById(id)
            .map(cpiInsuranceTypeMapper::toDto);
    }

    /**
     * Delete the cpiInsuranceType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CpiInsuranceType : {}", id);
        cpiInsuranceTypeRepository.deleteById(id);
    }
}
