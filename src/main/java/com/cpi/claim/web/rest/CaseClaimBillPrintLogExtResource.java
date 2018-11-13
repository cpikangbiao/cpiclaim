package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillPrintLogExtService;
import com.cpi.claim.service.CaseClaimBillPrintLogQueryService;
import com.cpi.claim.service.CaseClaimBillPrintLogService;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
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

@RestController
@RequestMapping("/api")
public class CaseClaimBillPrintLogExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogExtResource.class);

    private static final String ENTITY_NAME = "caseClaimBillPrintLog";

    private final CaseClaimBillPrintLogService caseClaimBillPrintLogService;

    private final CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService;

    @Autowired
    private CaseClaimBillPrintLogExtService caseClaimBillPrintLogExtService;

    public CaseClaimBillPrintLogExtResource(CaseClaimBillPrintLogService caseClaimBillPrintLogService, CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService) {
        this.caseClaimBillPrintLogService = caseClaimBillPrintLogService;
        this.caseClaimBillPrintLogQueryService = caseClaimBillPrintLogQueryService;
    }

    @GetMapping("/case-claim-bill-print-logs/by-claim-bill")
    @Timed
    public ResponseEntity<List<CaseClaimBillPrintLogDTO>> getAllCaseClaimBillPrintLogsByClaimBill(Long claimBillId, Pageable pageable) {
        log.debug("REST request to get CaseClaimBillPrintLogs by id: {}", claimBillId);
        Page<CaseClaimBillPrintLogDTO> page = caseClaimBillPrintLogExtService.findAllByCaseClaimBill(claimBillId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bill-print-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
