package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseRecoveryExtService;
import com.cpi.claim.service.CaseRecoveryQueryService;
import com.cpi.claim.service.CaseRecoveryService;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.dto.CaseRecoveryCriteria;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CaseRecovery.
 */
@RestController
@RequestMapping("/api")
public class CaseRecoveryExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryExtResource.class);

    private static final String ENTITY_NAME = "caseRecovery";

    private final CaseRecoveryService caseRecoveryService;

    private final CaseRecoveryQueryService caseRecoveryQueryService;

    @Autowired
    private CaseRecoveryExtService caseRecoveryExtService;

    public CaseRecoveryExtResource(CaseRecoveryService caseRecoveryService, CaseRecoveryQueryService caseRecoveryQueryService) {
        this.caseRecoveryService = caseRecoveryService;
        this.caseRecoveryQueryService = caseRecoveryQueryService;
    }

    @GetMapping("/case-recoveries/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getcGuaranteeForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseRecoveryDTO> caseRecoveryDTOS = caseRecoveryExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseRecoveryDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-recoveries/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CaseRecoveryDTO>> getcGuaranteeForVesselCaseId(Long id, Pageable pageable) {
        log.debug("REST request to get caseFee For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseRecoveryDTO> page = caseRecoveryExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-recoveries/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
