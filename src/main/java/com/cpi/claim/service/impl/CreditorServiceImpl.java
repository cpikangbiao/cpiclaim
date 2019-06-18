package com.cpi.claim.service.impl;

import com.cpi.claim.service.CreditorService;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CreditorRepository;
import com.cpi.claim.service.dto.CreditorDTO;
import com.cpi.claim.service.mapper.CreditorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Creditor}.
 */
@Service
@Transactional
public class CreditorServiceImpl implements CreditorService {

    private final Logger log = LoggerFactory.getLogger(CreditorServiceImpl.class);

    private final CreditorRepository creditorRepository;

    private final CreditorMapper creditorMapper;

    public CreditorServiceImpl(CreditorRepository creditorRepository, CreditorMapper creditorMapper) {
        this.creditorRepository = creditorRepository;
        this.creditorMapper = creditorMapper;
    }

    /**
     * Save a creditor.
     *
     * @param creditorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CreditorDTO save(CreditorDTO creditorDTO) {
        log.debug("Request to save Creditor : {}", creditorDTO);
        Creditor creditor = creditorMapper.toEntity(creditorDTO);
        creditor = creditorRepository.save(creditor);
        return creditorMapper.toDto(creditor);
    }

    /**
     * Get all the creditors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CreditorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Creditors");
        return creditorRepository.findAll(pageable)
            .map(creditorMapper::toDto);
    }


    /**
     * Get one creditor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CreditorDTO> findOne(Long id) {
        log.debug("Request to get Creditor : {}", id);
        return creditorRepository.findById(id)
            .map(creditorMapper::toDto);
    }

    /**
     * Delete the creditor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Creditor : {}", id);
        creditorRepository.deleteById(id);
    }
}
