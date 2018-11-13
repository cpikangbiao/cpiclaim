package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillDeleteLogQueryService;
import com.cpi.claim.service.CaseClaimBillDeleteLogService;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing CaseClaimBillDeleteLog.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillDeleteLogExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogExtResource.class);

    private static final String ENTITY_NAME = "caseClaimBillDeleteLog";

    private final CaseClaimBillDeleteLogService caseClaimBillDeleteLogService;

    private final CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService;

    public CaseClaimBillDeleteLogExtResource(CaseClaimBillDeleteLogService caseClaimBillDeleteLogService, CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService) {
        this.caseClaimBillDeleteLogService = caseClaimBillDeleteLogService;
        this.caseClaimBillDeleteLogQueryService = caseClaimBillDeleteLogQueryService;
    }


    @GetMapping("/case-claim-bill-delete-logs")
    @Timed
    public ResponseEntity<List<CaseClaimBillDeleteLogDTO>> getAllCaseClaimBillDeleteLogs(CaseClaimBillDeleteLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaimBillDeleteLogs by criteria: {}", criteria);
        Page<CaseClaimBillDeleteLogDTO> page = caseClaimBillDeleteLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bill-delete-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
