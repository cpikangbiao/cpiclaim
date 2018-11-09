package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseGuaranteeExtService;
import com.cpi.claim.service.CaseGuaranteeQueryService;
import com.cpi.claim.service.CaseGuaranteeService;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
import com.cpi.claim.service.dto.CaseGuaranteeCriteria;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
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
 * REST controller for managing CaseGuarantee.
 */
@RestController
@RequestMapping("/api")
public class CaseGuaranteeExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseGuaranteeExtResource.class);

    private static final String ENTITY_NAME = "caseGuarantee";

    private final CaseGuaranteeService caseGuaranteeService;

    private final CaseGuaranteeQueryService caseGuaranteeQueryService;

    @Autowired
    private CaseGuaranteeExtService caseGuaranteeExtService;

    public CaseGuaranteeExtResource(CaseGuaranteeService caseGuaranteeService, CaseGuaranteeQueryService caseGuaranteeQueryService) {
        this.caseGuaranteeService = caseGuaranteeService;
        this.caseGuaranteeQueryService = caseGuaranteeQueryService;
    }

    @GetMapping("/case-guarantees/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getcGuaranteeForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseGuaranteeDTO> caseGuaranteeDTOS = caseGuaranteeExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseGuaranteeDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-guarantees/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CaseGuaranteeDTO>> getcGuaranteeForVesselCaseId(Long id, Pageable pageable) {
        log.debug("REST request to get caseFee For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseGuaranteeDTO> page = caseGuaranteeExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-guarantees/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
