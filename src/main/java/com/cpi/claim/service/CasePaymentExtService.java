package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CasePaymentRepository;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.dto.CasePaymentCriteria;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.mapper.CasePaymentMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CasePaymentExtService extends QueryService<CasePayment> {

    private final Logger log = LoggerFactory.getLogger(CasePaymentExtService.class);

    private final CasePaymentRepository casePaymentRepository;

    private final CasePaymentMapper casePaymentMapper;

    public CasePaymentExtService(CasePaymentRepository casePaymentRepository, CasePaymentMapper casePaymentMapper) {
        this.casePaymentRepository = casePaymentRepository;
        this.casePaymentMapper = casePaymentMapper;
    }

    @Transactional(readOnly = true)
    public List<CasePaymentDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CasePayment> casePayments = casePaymentRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return casePaymentMapper.toDto(casePayments);
    }

    @Transactional(readOnly = true)
    public Page<CasePaymentDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CasePayment> casePayments = casePaymentRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return casePayments.map(casePaymentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = casePaymentRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }

}
