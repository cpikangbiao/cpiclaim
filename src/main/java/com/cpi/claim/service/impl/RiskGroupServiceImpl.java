package com.cpi.claim.service.impl;

import com.cpi.claim.service.RiskGroupService;
import com.cpi.claim.domain.RiskGroup;
import com.cpi.claim.repository.RiskGroupRepository;
import com.cpi.claim.service.dto.RiskGroupDTO;
import com.cpi.claim.service.mapper.RiskGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RiskGroup}.
 */
@Service
@Transactional
public class RiskGroupServiceImpl implements RiskGroupService {

    private final Logger log = LoggerFactory.getLogger(RiskGroupServiceImpl.class);

    private final RiskGroupRepository riskGroupRepository;

    private final RiskGroupMapper riskGroupMapper;

    public RiskGroupServiceImpl(RiskGroupRepository riskGroupRepository, RiskGroupMapper riskGroupMapper) {
        this.riskGroupRepository = riskGroupRepository;
        this.riskGroupMapper = riskGroupMapper;
    }

    /**
     * Save a riskGroup.
     *
     * @param riskGroupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public RiskGroupDTO save(RiskGroupDTO riskGroupDTO) {
        log.debug("Request to save RiskGroup : {}", riskGroupDTO);
        RiskGroup riskGroup = riskGroupMapper.toEntity(riskGroupDTO);
        riskGroup = riskGroupRepository.save(riskGroup);
        return riskGroupMapper.toDto(riskGroup);
    }

    /**
     * Get all the riskGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RiskGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskGroups");
        return riskGroupRepository.findAll(pageable)
            .map(riskGroupMapper::toDto);
    }


    /**
     * Get one riskGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RiskGroupDTO> findOne(Long id) {
        log.debug("Request to get RiskGroup : {}", id);
        return riskGroupRepository.findById(id)
            .map(riskGroupMapper::toDto);
    }

    /**
     * Delete the riskGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RiskGroup : {}", id);
        riskGroupRepository.deleteById(id);
    }
}
