package com.cpi.claim.service.impl;

import com.cpi.claim.service.CasePaymentService;
import com.cpi.claim.domain.CasePayment;
import com.cpi.claim.repository.CasePaymentRepository;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.mapper.CasePaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing CasePayment.
 */
@Service
@Transactional
public class CasePaymentServiceImpl implements CasePaymentService {

    private final Logger log = LoggerFactory.getLogger(CasePaymentServiceImpl.class);

    private final CasePaymentRepository casePaymentRepository;

    private final CasePaymentMapper casePaymentMapper;

    public CasePaymentServiceImpl(CasePaymentRepository casePaymentRepository, CasePaymentMapper casePaymentMapper) {
        this.casePaymentRepository = casePaymentRepository;
        this.casePaymentMapper = casePaymentMapper;
    }

    /**
     * Save a casePayment.
     *
     * @param casePaymentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CasePaymentDTO save(CasePaymentDTO casePaymentDTO) {
        log.debug("Request to save CasePayment : {}", casePaymentDTO);
        CasePayment casePayment = casePaymentMapper.toEntity(casePaymentDTO);
        casePayment = casePaymentRepository.save(casePayment);
        return casePaymentMapper.toDto(casePayment);
    }

    /**
     * Get all the casePayments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CasePaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CasePayments");
        return casePaymentRepository.findAll(pageable)
            .map(casePaymentMapper::toDto);
    }


    /**
     * Get one casePayment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CasePaymentDTO> findOne(Long id) {
        log.debug("Request to get CasePayment : {}", id);
        return casePaymentRepository.findById(id)
            .map(casePaymentMapper::toDto);
    }

    /**
     * Delete the casePayment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CasePayment : {}", id);
        casePaymentRepository.deleteById(id);
    }
}
