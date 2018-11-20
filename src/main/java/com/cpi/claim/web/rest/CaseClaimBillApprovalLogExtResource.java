package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillApprovalLogQueryService;
import com.cpi.claim.service.CaseClaimBillApprovalLogService;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
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
 * REST controller for managing CaseClaimBillApprovalLog.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillApprovalLogExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogExtResource.class);

    private static final String ENTITY_NAME = "caseClaimBillApprovalLog";

    private final CaseClaimBillApprovalLogService caseClaimBillApprovalLogService;

    private final CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService;

    public CaseClaimBillApprovalLogExtResource(CaseClaimBillApprovalLogService caseClaimBillApprovalLogService, CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService) {
        this.caseClaimBillApprovalLogService = caseClaimBillApprovalLogService;
        this.caseClaimBillApprovalLogQueryService = caseClaimBillApprovalLogQueryService;
    }

   
}
