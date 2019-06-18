package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseFeeBillService;
import com.cpi.claim.domain.CaseFeeBill;
import com.cpi.claim.repository.CaseFeeBillRepository;
import com.cpi.claim.service.dto.CaseFeeBillDTO;
import com.cpi.claim.service.mapper.CaseFeeBillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseFeeBill.
 */
@Service
@Transactional
public class CaseFeeBillServiceImpl implements CaseFeeBillService {

    private final Logger log = LoggerFactory.getLogger(CaseFeeBillServiceImpl.class);

    private final CaseFeeBillRepository caseFeeBillRepository;

    private final CaseFeeBillMapper caseFeeBillMapper;

    public CaseFeeBillServiceImpl(CaseFeeBillRepository caseFeeBillRepository, CaseFeeBillMapper caseFeeBillMapper) {
        this.caseFeeBillRepository = caseFeeBillRepository;
        this.caseFeeBillMapper = caseFeeBillMapper;
    }

    /**
     * Save a caseFeeBill.
     *
     * @param caseFeeBillDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseFeeBillDTO save(CaseFeeBillDTO caseFeeBillDTO) {
        log.debug("Request to save CaseFeeBill : {}", caseFeeBillDTO);
        CaseFeeBill caseFeeBill = caseFeeBillMapper.toEntity(caseFeeBillDTO);
        caseFeeBill = caseFeeBillRepository.save(caseFeeBill);
        return caseFeeBillMapper.toDto(caseFeeBill);
    }

    /**
     * Get all the caseFeeBills.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseFeeBillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseFeeBills");
        return caseFeeBillRepository.findAll(pageable)
            .map(caseFeeBillMapper::toDto);
    }


    /**
     * Get one caseFeeBill by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseFeeBillDTO> findOne(Long id) {
        log.debug("Request to get CaseFeeBill : {}", id);
        return caseFeeBillRepository.findById(id)
            .map(caseFeeBillMapper::toDto);
    }

    /**
     * Delete the caseFeeBill by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseFeeBill : {}", id);
        caseFeeBillRepository.deleteById(id);
    }
}
