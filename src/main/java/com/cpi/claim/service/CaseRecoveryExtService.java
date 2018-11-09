package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseRecoveryRepository;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.dto.CaseRecoveryCriteria;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.mapper.CaseRecoveryMapper;
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
public class CaseRecoveryExtService extends QueryService<CaseRecovery> {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryExtService.class);

    private final CaseRecoveryRepository caseRecoveryRepository;

    private final CaseRecoveryMapper caseRecoveryMapper;

    public CaseRecoveryExtService(CaseRecoveryRepository caseRecoveryRepository, CaseRecoveryMapper caseRecoveryMapper) {
        this.caseRecoveryRepository = caseRecoveryRepository;
        this.caseRecoveryMapper = caseRecoveryMapper;
    }


    @Transactional(readOnly = true)
    public List<CaseRecoveryDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseRecovery> caseRecoveries = caseRecoveryRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseRecoveryMapper.toDto(caseRecoveries);
    }

    @Transactional(readOnly = true)
    public Page<CaseRecoveryDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseRecovery> caseRecoveries = caseRecoveryRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseRecoveries.map(caseRecoveryMapper::toDto);
    }


    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = caseRecoveryRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }

}
