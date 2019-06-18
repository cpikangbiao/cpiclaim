package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseRecoveryBillService;
import com.cpi.claim.domain.CaseRecoveryBill;
import com.cpi.claim.repository.CaseRecoveryBillRepository;
import com.cpi.claim.service.dto.CaseRecoveryBillDTO;
import com.cpi.claim.service.mapper.CaseRecoveryBillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseRecoveryBill}.
 */
@Service
@Transactional
public class CaseRecoveryBillServiceImpl implements CaseRecoveryBillService {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryBillServiceImpl.class);

    private final CaseRecoveryBillRepository caseRecoveryBillRepository;

    private final CaseRecoveryBillMapper caseRecoveryBillMapper;

    public CaseRecoveryBillServiceImpl(CaseRecoveryBillRepository caseRecoveryBillRepository, CaseRecoveryBillMapper caseRecoveryBillMapper) {
        this.caseRecoveryBillRepository = caseRecoveryBillRepository;
        this.caseRecoveryBillMapper = caseRecoveryBillMapper;
    }

    /**
     * Save a caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseRecoveryBillDTO save(CaseRecoveryBillDTO caseRecoveryBillDTO) {
        log.debug("Request to save CaseRecoveryBill : {}", caseRecoveryBillDTO);
        CaseRecoveryBill caseRecoveryBill = caseRecoveryBillMapper.toEntity(caseRecoveryBillDTO);
        caseRecoveryBill = caseRecoveryBillRepository.save(caseRecoveryBill);
        return caseRecoveryBillMapper.toDto(caseRecoveryBill);
    }

    /**
     * Get all the caseRecoveryBills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseRecoveryBillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseRecoveryBills");
        return caseRecoveryBillRepository.findAll(pageable)
            .map(caseRecoveryBillMapper::toDto);
    }


    /**
     * Get one caseRecoveryBill by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseRecoveryBillDTO> findOne(Long id) {
        log.debug("Request to get CaseRecoveryBill : {}", id);
        return caseRecoveryBillRepository.findById(id)
            .map(caseRecoveryBillMapper::toDto);
    }

    /**
     * Delete the caseRecoveryBill by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseRecoveryBill : {}", id);
        caseRecoveryBillRepository.deleteById(id);
    }
}
