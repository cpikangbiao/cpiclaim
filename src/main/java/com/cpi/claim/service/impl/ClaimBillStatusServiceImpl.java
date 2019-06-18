package com.cpi.claim.service.impl;

import com.cpi.claim.service.ClaimBillStatusService;
import com.cpi.claim.domain.ClaimBillStatus;
import com.cpi.claim.repository.ClaimBillStatusRepository;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;
import com.cpi.claim.service.mapper.ClaimBillStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing ClaimBillStatus.
 */
@Service
@Transactional
public class ClaimBillStatusServiceImpl implements ClaimBillStatusService {

    private final Logger log = LoggerFactory.getLogger(ClaimBillStatusServiceImpl.class);

    private final ClaimBillStatusRepository claimBillStatusRepository;

    private final ClaimBillStatusMapper claimBillStatusMapper;

    public ClaimBillStatusServiceImpl(ClaimBillStatusRepository claimBillStatusRepository, ClaimBillStatusMapper claimBillStatusMapper) {
        this.claimBillStatusRepository = claimBillStatusRepository;
        this.claimBillStatusMapper = claimBillStatusMapper;
    }

    /**
     * Save a claimBillStatus.
     *
     * @param claimBillStatusDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ClaimBillStatusDTO save(ClaimBillStatusDTO claimBillStatusDTO) {
        log.debug("Request to save ClaimBillStatus : {}", claimBillStatusDTO);
        ClaimBillStatus claimBillStatus = claimBillStatusMapper.toEntity(claimBillStatusDTO);
        claimBillStatus = claimBillStatusRepository.save(claimBillStatus);
        return claimBillStatusMapper.toDto(claimBillStatus);
    }

    /**
     * Get all the claimBillStatuses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ClaimBillStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClaimBillStatuses");
        return claimBillStatusRepository.findAll(pageable)
            .map(claimBillStatusMapper::toDto);
    }


    /**
     * Get one claimBillStatus by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ClaimBillStatusDTO> findOne(Long id) {
        log.debug("Request to get ClaimBillStatus : {}", id);
        return claimBillStatusRepository.findById(id)
            .map(claimBillStatusMapper::toDto);
    }

    /**
     * Delete the claimBillStatus by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClaimBillStatus : {}", id);
        claimBillStatusRepository.deleteById(id);
    }
}
