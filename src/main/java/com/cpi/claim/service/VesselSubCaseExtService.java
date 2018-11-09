package com.cpi.claim.service;

import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.repository.VesselSubCaseRepository;
import com.cpi.claim.service.mapper.VesselSubCaseMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VesselSubCaseExtService extends QueryService<VesselSubCase> {

    private final Logger log = LoggerFactory.getLogger(VesselSubCaseExtService.class);

    private final VesselSubCaseRepository vesselSubCaseRepository;

    private final VesselSubCaseMapper vesselSubCaseMapper;

    public VesselSubCaseExtService(VesselSubCaseRepository vesselSubCaseRepository, VesselSubCaseMapper vesselSubCaseMapper) {
        this.vesselSubCaseRepository = vesselSubCaseRepository;
        this.vesselSubCaseMapper = vesselSubCaseMapper;
    }

    @Transactional(readOnly = true)
    public Integer findNextNumberIdByVesselCaseId(Long vesselCaseId) {
        log.debug("find by insuredVesselId : {}", vesselCaseId);
        return new Integer(vesselSubCaseRepository.findMaxNumberIdByVesselCaseId(vesselCaseId) + 1);
    }



}
