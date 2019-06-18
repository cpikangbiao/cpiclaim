package com.cpi.claim.service.impl;

import com.cpi.claim.service.ClaimBillTypeService;
import com.cpi.claim.domain.ClaimBillType;
import com.cpi.claim.repository.ClaimBillTypeRepository;
import com.cpi.claim.service.dto.ClaimBillTypeDTO;
import com.cpi.claim.service.mapper.ClaimBillTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ClaimBillType}.
 */
@Service
@Transactional
public class ClaimBillTypeServiceImpl implements ClaimBillTypeService {

    private final Logger log = LoggerFactory.getLogger(ClaimBillTypeServiceImpl.class);

    private final ClaimBillTypeRepository claimBillTypeRepository;

    private final ClaimBillTypeMapper claimBillTypeMapper;

    public ClaimBillTypeServiceImpl(ClaimBillTypeRepository claimBillTypeRepository, ClaimBillTypeMapper claimBillTypeMapper) {
        this.claimBillTypeRepository = claimBillTypeRepository;
        this.claimBillTypeMapper = claimBillTypeMapper;
    }

    /**
     * Save a claimBillType.
     *
     * @param claimBillTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ClaimBillTypeDTO save(ClaimBillTypeDTO claimBillTypeDTO) {
        log.debug("Request to save ClaimBillType : {}", claimBillTypeDTO);
        ClaimBillType claimBillType = claimBillTypeMapper.toEntity(claimBillTypeDTO);
        claimBillType = claimBillTypeRepository.save(claimBillType);
        return claimBillTypeMapper.toDto(claimBillType);
    }

    /**
     * Get all the claimBillTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClaimBillTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimBillTypes");
        return claimBillTypeRepository.findAll(pageable)
            .map(claimBillTypeMapper::toDto);
    }


    /**
     * Get one claimBillType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimBillTypeDTO> findOne(Long id) {
        log.debug("Request to get ClaimBillType : {}", id);
        return claimBillTypeRepository.findById(id)
            .map(claimBillTypeMapper::toDto);
    }

    /**
     * Delete the claimBillType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimBillType : {}", id);
        claimBillTypeRepository.deleteById(id);
    }
}
