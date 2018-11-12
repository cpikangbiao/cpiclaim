package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillExtService;
import com.cpi.claim.service.CaseClaimBillQueryService;
import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
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
 * REST controller for managing CaseClaimBill.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillExtResource.class);

    private static final String ENTITY_NAME = "caseClaimBill";

    private final CaseClaimBillService caseClaimBillService;

    private final CaseClaimBillQueryService caseClaimBillQueryService;

    @Autowired
    private CaseClaimBillExtService caseClaimBillExtService;


    public CaseClaimBillExtResource(CaseClaimBillService caseClaimBillService, CaseClaimBillQueryService caseClaimBillQueryService) {
        this.caseClaimBillService = caseClaimBillService;
        this.caseClaimBillQueryService = caseClaimBillQueryService;
    }


    @GetMapping("/case-claim-bills/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getCaseClaimBillForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseClaimBillDTO> caseClaimBillDTOS = caseClaimBillExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseClaimBillDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-claim-bills/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CaseClaimBillDTO>> getAllCaseClaimBills(Long id, Pageable pageable) {
        log.debug("REST request to get CaseClaimBill For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseClaimBillDTO> page = caseClaimBillExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bills/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
