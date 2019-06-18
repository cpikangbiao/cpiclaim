package com.cpi.claim.service.impl;

import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CaseClaimBill}.
 */
@Service
@Transactional
public class CaseClaimBillServiceImpl implements CaseClaimBillService {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillServiceImpl.class);

    private final CaseClaimBillRepository caseClaimBillRepository;

    private final CaseClaimBillMapper caseClaimBillMapper;

    public CaseClaimBillServiceImpl(CaseClaimBillRepository caseClaimBillRepository, CaseClaimBillMapper caseClaimBillMapper) {
        this.caseClaimBillRepository = caseClaimBillRepository;
        this.caseClaimBillMapper = caseClaimBillMapper;
    }

    /**
     * Save a caseClaimBill.
     *
     * @param caseClaimBillDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CaseClaimBillDTO save(CaseClaimBillDTO caseClaimBillDTO) {
        log.debug("Request to save CaseClaimBill : {}", caseClaimBillDTO);
        CaseClaimBill caseClaimBill = caseClaimBillMapper.toEntity(caseClaimBillDTO);
        caseClaimBill = caseClaimBillRepository.save(caseClaimBill);
        return caseClaimBillMapper.toDto(caseClaimBill);
    }

    /**
     * Get all the caseClaimBills.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaseClaimBillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CaseClaimBills");
        return caseClaimBillRepository.findAll(pageable)
            .map(caseClaimBillMapper::toDto);
    }


    /**
     * Get one caseClaimBill by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaseClaimBillDTO> findOne(Long id) {
        log.debug("Request to get CaseClaimBill : {}", id);
        return caseClaimBillRepository.findById(id)
            .map(caseClaimBillMapper::toDto);
    }

    /**
     * Delete the caseClaimBill by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaseClaimBill : {}", id);
        caseClaimBillRepository.deleteById(id);
    }
}
