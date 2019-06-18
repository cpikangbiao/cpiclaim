package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseRegisterLogService;
import com.cpi.claim.domain.CaseRegisterLog;
import com.cpi.claim.repository.CaseRegisterLogRepository;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import com.cpi.claim.service.mapper.CaseRegisterLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseRegisterLog}.
 */
@Service
@Transactional
public class CaseRegisterLogServiceImpl implements CaseRegisterLogService {

    private final Logger log = LoggerFactory.getLogger(CaseRegisterLogServiceImpl.class);

    private final CaseRegisterLogRepository caseRegisterLogRepository;

    private final CaseRegisterLogMapper caseRegisterLogMapper;

    public CaseRegisterLogServiceImpl(CaseRegisterLogRepository caseRegisterLogRepository, CaseRegisterLogMapper caseRegisterLogMapper) {
        this.caseRegisterLogRepository = caseRegisterLogRepository;
        this.caseRegisterLogMapper = caseRegisterLogMapper;
    }

    /**
     * Save a caseRegisterLog.
     *
     * @param caseRegisterLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseRegisterLogDTO save(CaseRegisterLogDTO caseRegisterLogDTO) {
        log.debug("Request to save CaseRegisterLog : {}", caseRegisterLogDTO);
        CaseRegisterLog caseRegisterLog = caseRegisterLogMapper.toEntity(caseRegisterLogDTO);
        caseRegisterLog = caseRegisterLogRepository.save(caseRegisterLog);
        return caseRegisterLogMapper.toDto(caseRegisterLog);
    }

    /**
     * Get all the caseRegisterLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseRegisterLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseRegisterLogs");
        return caseRegisterLogRepository.findAll(pageable)
            .map(caseRegisterLogMapper::toDto);
    }


    /**
     * Get one caseRegisterLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseRegisterLogDTO> findOne(Long id) {
        log.debug("Request to get CaseRegisterLog : {}", id);
        return caseRegisterLogRepository.findById(id)
            .map(caseRegisterLogMapper::toDto);
    }

    /**
     * Delete the caseRegisterLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseRegisterLog : {}", id);
        caseRegisterLogRepository.deleteById(id);
    }
}
