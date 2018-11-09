package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CasePaymentExtService;
import com.cpi.claim.service.CasePaymentQueryService;
import com.cpi.claim.service.CasePaymentService;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.dto.CasePaymentCriteria;
import com.cpi.claim.service.dto.CasePaymentDTO;
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
 * REST controller for managing CasePayment.
 */
@RestController
@RequestMapping("/api")
public class CasePaymentExtResource {

    private final Logger log = LoggerFactory.getLogger(CasePaymentExtResource.class);

    private static final String ENTITY_NAME = "casePayment";

    private final CasePaymentService casePaymentService;

    private final CasePaymentQueryService casePaymentQueryService;

    @Autowired
    private CasePaymentExtService casePaymentExtService;

    public CasePaymentExtResource(CasePaymentService casePaymentService, CasePaymentQueryService casePaymentQueryService) {
        this.casePaymentService = casePaymentService;
        this.casePaymentQueryService = casePaymentQueryService;
    }

    @GetMapping("/case-payments/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getPaymentForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CasePaymentDTO> casePaymentDTOS = casePaymentExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(casePaymentDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-payments/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CasePaymentDTO>> Timed(Long id, Pageable pageable) {
        log.debug("REST request to get caseFee For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CasePaymentDTO> page = casePaymentExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-payments/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
