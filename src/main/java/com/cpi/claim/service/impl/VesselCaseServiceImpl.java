package com.cpi.claim.service.impl;

import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link VesselCase}.
 */
@Service
@Transactional
public class VesselCaseServiceImpl implements VesselCaseService {

    private final Logger log = LoggerFactory.getLogger(VesselCaseServiceImpl.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    public VesselCaseServiceImpl(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    /**
     * Save a vesselCase.
     *
     * @param vesselCaseDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VesselCaseDTO save(VesselCaseDTO vesselCaseDTO) {
        log.debug("Request to save VesselCase : {}", vesselCaseDTO);
        VesselCase vesselCase = vesselCaseMapper.toEntity(vesselCaseDTO);
        vesselCase = vesselCaseRepository.save(vesselCase);
        return vesselCaseMapper.toDto(vesselCase);
    }

    /**
     * Get all the vesselCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VesselCaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VesselCases");
        return vesselCaseRepository.findAll(pageable)
            .map(vesselCaseMapper::toDto);
    }


    /**
     * Get one vesselCase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VesselCaseDTO> findOne(Long id) {
        log.debug("Request to get VesselCase : {}", id);
        return vesselCaseRepository.findById(id)
            .map(vesselCaseMapper::toDto);
    }

    /**
     * Delete the vesselCase by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete VesselCase : {}", id);
        vesselCaseRepository.deleteById(id);
    }
}
