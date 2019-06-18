package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseGuaranteeService;
import com.cpi.claim.domain.CaseGuarantee;
import com.cpi.claim.repository.CaseGuaranteeRepository;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
import com.cpi.claim.service.mapper.CaseGuaranteeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseGuarantee}.
 */
@Service
@Transactional
public class CaseGuaranteeServiceImpl implements CaseGuaranteeService {

    private final Logger log = LoggerFactory.getLogger(CaseGuaranteeServiceImpl.class);

    private final CaseGuaranteeRepository caseGuaranteeRepository;

    private final CaseGuaranteeMapper caseGuaranteeMapper;

    public CaseGuaranteeServiceImpl(CaseGuaranteeRepository caseGuaranteeRepository, CaseGuaranteeMapper caseGuaranteeMapper) {
        this.caseGuaranteeRepository = caseGuaranteeRepository;
        this.caseGuaranteeMapper = caseGuaranteeMapper;
    }

    /**
     * Save a caseGuarantee.
     *
     * @param caseGuaranteeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseGuaranteeDTO save(CaseGuaranteeDTO caseGuaranteeDTO) {
        log.debug("Request to save CaseGuarantee : {}", caseGuaranteeDTO);
        CaseGuarantee caseGuarantee = caseGuaranteeMapper.toEntity(caseGuaranteeDTO);
        caseGuarantee = caseGuaranteeRepository.save(caseGuarantee);
        return caseGuaranteeMapper.toDto(caseGuarantee);
    }

    /**
     * Get all the caseGuarantees.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseGuaranteeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseGuarantees");
        return caseGuaranteeRepository.findAll(pageable)
            .map(caseGuaranteeMapper::toDto);
    }


    /**
     * Get one caseGuarantee by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseGuaranteeDTO> findOne(Long id) {
        log.debug("Request to get CaseGuarantee : {}", id);
        return caseGuaranteeRepository.findById(id)
            .map(caseGuaranteeMapper::toDto);
    }

    /**
     * Delete the caseGuarantee by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseGuarantee : {}", id);
        caseGuaranteeRepository.deleteById(id);
    }
}
