package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseStatusTypeService;
import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.repository.CaseStatusTypeRepository;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.mapper.CaseStatusTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseStatusType}.
 */
@Service
@Transactional
public class CaseStatusTypeServiceImpl implements CaseStatusTypeService {

    private final Logger log = LoggerFactory.getLogger(CaseStatusTypeServiceImpl.class);

    private final CaseStatusTypeRepository caseStatusTypeRepository;

    private final CaseStatusTypeMapper caseStatusTypeMapper;

    public CaseStatusTypeServiceImpl(CaseStatusTypeRepository caseStatusTypeRepository, CaseStatusTypeMapper caseStatusTypeMapper) {
        this.caseStatusTypeRepository = caseStatusTypeRepository;
        this.caseStatusTypeMapper = caseStatusTypeMapper;
    }

    /**
     * Save a caseStatusType.
     *
     * @param caseStatusTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseStatusTypeDTO save(CaseStatusTypeDTO caseStatusTypeDTO) {
        log.debug("Request to save CaseStatusType : {}", caseStatusTypeDTO);
        CaseStatusType caseStatusType = caseStatusTypeMapper.toEntity(caseStatusTypeDTO);
        caseStatusType = caseStatusTypeRepository.save(caseStatusType);
        return caseStatusTypeMapper.toDto(caseStatusType);
    }

    /**
     * Get all the caseStatusTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseStatusTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseStatusTypes");
        return caseStatusTypeRepository.findAll(pageable)
            .map(caseStatusTypeMapper::toDto);
    }


    /**
     * Get one caseStatusType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseStatusTypeDTO> findOne(Long id) {
        log.debug("Request to get CaseStatusType : {}", id);
        return caseStatusTypeRepository.findById(id)
            .map(caseStatusTypeMapper::toDto);
    }

    /**
     * Delete the caseStatusType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseStatusType : {}", id);
        caseStatusTypeRepository.deleteById(id);
    }
}
