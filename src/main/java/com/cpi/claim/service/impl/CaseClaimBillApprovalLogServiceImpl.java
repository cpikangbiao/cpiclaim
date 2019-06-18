package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseClaimBillApprovalLogService;
import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import com.cpi.claim.repository.CaseClaimBillApprovalLogRepository;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillApprovalLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseClaimBillApprovalLog}.
 */
@Service
@Transactional
public class CaseClaimBillApprovalLogServiceImpl implements CaseClaimBillApprovalLogService {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogServiceImpl.class);

    private final CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository;

    private final CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper;

    public CaseClaimBillApprovalLogServiceImpl(CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository, CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper) {
        this.caseClaimBillApprovalLogRepository = caseClaimBillApprovalLogRepository;
        this.caseClaimBillApprovalLogMapper = caseClaimBillApprovalLogMapper;
    }

    /**
     * Save a caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseClaimBillApprovalLogDTO save(CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO) {
        log.debug("Request to save CaseClaimBillApprovalLog : {}", caseClaimBillApprovalLogDTO);
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = caseClaimBillApprovalLogMapper.toEntity(caseClaimBillApprovalLogDTO);
        caseClaimBillApprovalLog = caseClaimBillApprovalLogRepository.save(caseClaimBillApprovalLog);
        return caseClaimBillApprovalLogMapper.toDto(caseClaimBillApprovalLog);
    }

    /**
     * Get all the caseClaimBillApprovalLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseClaimBillApprovalLogDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseClaimBillApprovalLogs");
        return caseClaimBillApprovalLogRepository.findAll(pageable)
            .map(caseClaimBillApprovalLogMapper::toDto);
    }


    /**
     * Get one caseClaimBillApprovalLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseClaimBillApprovalLogDTO> findOne(Long id) {
        log.debug("Request to get CaseClaimBillApprovalLog : {}", id);
        return caseClaimBillApprovalLogRepository.findById(id)
            .map(caseClaimBillApprovalLogMapper::toDto);
    }

    /**
     * Delete the caseClaimBillApprovalLog by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseClaimBillApprovalLog : {}", id);
        caseClaimBillApprovalLogRepository.deleteById(id);
    }
}
