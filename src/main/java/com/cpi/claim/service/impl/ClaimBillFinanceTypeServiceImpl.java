package com.cpi.claim.service.impl;

import com.cpi.claim.service.ClaimBillFinanceTypeService;
import com.cpi.claim.domain.ClaimBillFinanceType;
import com.cpi.claim.repository.ClaimBillFinanceTypeRepository;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;
import com.cpi.claim.service.mapper.ClaimBillFinanceTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ClaimBillFinanceType.
 */
@Service
@Transactional
public class ClaimBillFinanceTypeServiceImpl implements ClaimBillFinanceTypeService {

    private final Logger log = LoggerFactory.getLogger(ClaimBillFinanceTypeServiceImpl.class);

    private final ClaimBillFinanceTypeRepository claimBillFinanceTypeRepository;

    private final ClaimBillFinanceTypeMapper claimBillFinanceTypeMapper;

    public ClaimBillFinanceTypeServiceImpl(ClaimBillFinanceTypeRepository claimBillFinanceTypeRepository, ClaimBillFinanceTypeMapper claimBillFinanceTypeMapper) {
        this.claimBillFinanceTypeRepository = claimBillFinanceTypeRepository;
        this.claimBillFinanceTypeMapper = claimBillFinanceTypeMapper;
    }

    /**
     * Save a claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ClaimBillFinanceTypeDTO save(ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO) {
        log.debug("Request to save ClaimBillFinanceType : {}", claimBillFinanceTypeDTO);
        ClaimBillFinanceType claimBillFinanceType = claimBillFinanceTypeMapper.toEntity(claimBillFinanceTypeDTO);
        claimBillFinanceType = claimBillFinanceTypeRepository.save(claimBillFinanceType);
        return claimBillFinanceTypeMapper.toDto(claimBillFinanceType);
    }

    /**
     * Get all the claimBillFinanceTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClaimBillFinanceTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimBillFinanceTypes");
        return claimBillFinanceTypeRepository.findAll(pageable)
            .map(claimBillFinanceTypeMapper::toDto);
    }


    /**
     * Get one claimBillFinanceType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimBillFinanceTypeDTO> findOne(Long id) {
        log.debug("Request to get ClaimBillFinanceType : {}", id);
        return claimBillFinanceTypeRepository.findById(id)
            .map(claimBillFinanceTypeMapper::toDto);
    }

    /**
     * Delete the claimBillFinanceType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimBillFinanceType : {}", id);
        claimBillFinanceTypeRepository.deleteById(id);
    }
}
