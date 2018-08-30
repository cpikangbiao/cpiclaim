package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseClaimBillDeleteLogService;
import com.cpi.claim.domain.CaseClaimBillDeleteLog;
import com.cpi.claim.repository.CaseClaimBillDeleteLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillDeleteLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseClaimBillDeleteLog.
 */
@Service
@Transactional
public class CaseClaimBillDeleteLogServiceImpl implements CaseClaimBillDeleteLogService {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogServiceImpl.class);

    private final CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository;

    private final CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper;

    public CaseClaimBillDeleteLogServiceImpl(CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository, CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper) {
        this.caseClaimBillDeleteLogRepository = caseClaimBillDeleteLogRepository;
        this.caseClaimBillDeleteLogMapper = caseClaimBillDeleteLogMapper;
    }

    /**
     * Save a caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseClaimBillDeleteLogDTO save(CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO) {
        log.debug("Request to save CaseClaimBillDeleteLog : {}", caseClaimBillDeleteLogDTO);
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = caseClaimBillDeleteLogMapper.toEntity(caseClaimBillDeleteLogDTO);
        caseClaimBillDeleteLog = caseClaimBillDeleteLogRepository.save(caseClaimBillDeleteLog);
        return caseClaimBillDeleteLogMapper.toDto(caseClaimBillDeleteLog);
    }

    /**
     * Get all the caseClaimBillDeleteLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseClaimBillDeleteLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseClaimBillDeleteLogs");
        return caseClaimBillDeleteLogRepository.findAll(pageable)
            .map(caseClaimBillDeleteLogMapper::toDto);
    }


    /**
     * Get one caseClaimBillDeleteLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseClaimBillDeleteLogDTO> findOne(Long id) {
        log.debug("Request to get CaseClaimBillDeleteLog : {}", id);
        return caseClaimBillDeleteLogRepository.findById(id)
            .map(caseClaimBillDeleteLogMapper::toDto);
    }

    /**
     * Delete the caseClaimBillDeleteLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseClaimBillDeleteLog : {}", id);
        caseClaimBillDeleteLogRepository.deleteById(id);
    }
}
