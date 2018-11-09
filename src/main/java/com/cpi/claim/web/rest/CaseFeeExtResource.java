package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseFeeExtService;
import com.cpi.claim.service.CaseFeeQueryService;
import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.service.dto.CaseFeeDTO;
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
 * REST controller for managing CaseFee.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeExtResource.class);

    private static final String ENTITY_NAME = "caseFee";

    private final CaseFeeService caseFeeService;

    private final CaseFeeQueryService caseFeeQueryService;

    @Autowired
    private CaseFeeExtService caseFeeExtService;

    public CaseFeeExtResource(CaseFeeService caseFeeService, CaseFeeQueryService caseFeeQueryService) {
        this.caseFeeService = caseFeeService;
        this.caseFeeQueryService = caseFeeQueryService;
    }


    @GetMapping("/case-fees/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getcaseFeeForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseFeeDTO> caseFeeDTOS = caseFeeExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseFeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-fees/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CaseFeeDTO>> getAllcaseFees(Long id, Pageable pageable) {
        log.debug("REST request to get caseFee For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseFeeDTO> page = caseFeeExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-fees/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
