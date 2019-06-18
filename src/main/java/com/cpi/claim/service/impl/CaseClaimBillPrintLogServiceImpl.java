package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseClaimBillPrintLogService;
import com.cpi.claim.domain.CaseClaimBillPrintLog;
import com.cpi.claim.repository.CaseClaimBillPrintLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillPrintLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CaseClaimBillPrintLog.
 */
@Service
@Transactional
public class CaseClaimBillPrintLogServiceImpl implements CaseClaimBillPrintLogService {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogServiceImpl.class);

    private final CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository;

    private final CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper;

    public CaseClaimBillPrintLogServiceImpl(CaseClaimBillPrintLogRepository caseClaimBillPrintLogRepository, CaseClaimBillPrintLogMapper caseClaimBillPrintLogMapper) {
        this.caseClaimBillPrintLogRepository = caseClaimBillPrintLogRepository;
        this.caseClaimBillPrintLogMapper = caseClaimBillPrintLogMapper;
    }

    /**
     * Save a caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CaseClaimBillPrintLogDTO save(CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO) {
        log.debug("Request to save CaseClaimBillPrintLog : {}", caseClaimBillPrintLogDTO);
        CaseClaimBillPrintLog caseClaimBillPrintLog = caseClaimBillPrintLogMapper.toEntity(caseClaimBillPrintLogDTO);
        caseClaimBillPrintLog = caseClaimBillPrintLogRepository.save(caseClaimBillPrintLog);
        return caseClaimBillPrintLogMapper.toDto(caseClaimBillPrintLog);
    }

    /**
     * Get all the caseClaimBillPrintLogs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseClaimBillPrintLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseClaimBillPrintLogs");
        return caseClaimBillPrintLogRepository.findAll(pageable)
            .map(caseClaimBillPrintLogMapper::toDto);
    }


    /**
     * Get one caseClaimBillPrintLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseClaimBillPrintLogDTO> findOne(Long id) {
        log.debug("Request to get CaseClaimBillPrintLog : {}", id);
        return caseClaimBillPrintLogRepository.findById(id)
            .map(caseClaimBillPrintLogMapper::toDto);
    }

    /**
     * Delete the caseClaimBillPrintLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseClaimBillPrintLog : {}", id);
        caseClaimBillPrintLogRepository.deleteById(id);
    }
}
