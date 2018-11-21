package com.cpi.claim.service.impl;

import com.cpi.claim.service.PaymentTypeService;
import com.cpi.claim.domain.PaymentType;
import com.cpi.claim.repository.PaymentTypeRepository;
import com.cpi.claim.service.dto.PaymentTypeDTO;
import com.cpi.claim.service.mapper.PaymentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing PaymentType.
 */
@Service
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final Logger log = LoggerFactory.getLogger(PaymentTypeServiceImpl.class);

    private final PaymentTypeRepository paymentTypeRepository;

    private final PaymentTypeMapper paymentTypeMapper;

    public PaymentTypeServiceImpl(PaymentTypeRepository paymentTypeRepository, PaymentTypeMapper paymentTypeMapper) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.paymentTypeMapper = paymentTypeMapper;
    }

    /**
     * Save a paymentType.
     *
     * @param paymentTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PaymentTypeDTO save(PaymentTypeDTO paymentTypeDTO) {
        log.debug("Request to save PaymentType : {}", paymentTypeDTO);
        PaymentType paymentType = paymentTypeMapper.toEntity(paymentTypeDTO);
        paymentType = paymentTypeRepository.save(paymentType);
        return paymentTypeMapper.toDto(paymentType);
    }

    /**
     * Get all the paymentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentTypes");
        return paymentTypeRepository.findAll(pageable)
            .map(paymentTypeMapper::toDto);
    }


    /**
     * Get one paymentType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentTypeDTO> findOne(Long id) {
        log.debug("Request to get PaymentType : {}", id);
        return paymentTypeRepository.findById(id)
            .map(paymentTypeMapper::toDto);
    }

    /**
     * Delete the paymentType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentType : {}", id);
        paymentTypeRepository.deleteById(id);
    }
}
