package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillApprovalLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.CaseClaimBillApprovalLogQueryService;
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
public class CaseClaimBillApprovalLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogResource.class);

    private static final String ENTITY_NAME = "caseClaimBillApprovalLog";

    private final CaseClaimBillApprovalLogService caseClaimBillApprovalLogService;

    private final CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService;

    public CaseClaimBillApprovalLogResource(CaseClaimBillApprovalLogService caseClaimBillApprovalLogService, CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService) {
        this.caseClaimBillApprovalLogService = caseClaimBillApprovalLogService;
        this.caseClaimBillApprovalLogQueryService = caseClaimBillApprovalLogQueryService;
    }

    /**
     * POST  /case-claim-bill-approval-logs : Create a new caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the caseClaimBillApprovalLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseClaimBillApprovalLogDTO, or with status 400 (Bad Request) if the caseClaimBillApprovalLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-claim-bill-approval-logs")
    @Timed
    public ResponseEntity<CaseClaimBillApprovalLogDTO> createCaseClaimBillApprovalLog(@RequestBody CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillApprovalLog : {}", caseClaimBillApprovalLogDTO);
        if (caseClaimBillApprovalLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillApprovalLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillApprovalLogDTO result = caseClaimBillApprovalLogService.save(caseClaimBillApprovalLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-approval-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-claim-bill-approval-logs : Updates an existing caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the caseClaimBillApprovalLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseClaimBillApprovalLogDTO,
     * or with status 400 (Bad Request) if the caseClaimBillApprovalLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseClaimBillApprovalLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-claim-bill-approval-logs")
    @Timed
    public ResponseEntity<CaseClaimBillApprovalLogDTO> updateCaseClaimBillApprovalLog(@RequestBody CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillApprovalLog : {}", caseClaimBillApprovalLogDTO);
        if (caseClaimBillApprovalLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillApprovalLogDTO result = caseClaimBillApprovalLogService.save(caseClaimBillApprovalLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseClaimBillApprovalLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-claim-bill-approval-logs : get all the caseClaimBillApprovalLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseClaimBillApprovalLogs in body
     */
    @GetMapping("/case-claim-bill-approval-logs")
    @Timed
    public ResponseEntity<List<CaseClaimBillApprovalLogDTO>> getAllCaseClaimBillApprovalLogs(CaseClaimBillApprovalLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaimBillApprovalLogs by criteria: {}", criteria);
        Page<CaseClaimBillApprovalLogDTO> page = caseClaimBillApprovalLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bill-approval-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-claim-bill-approval-logs/:id : get the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the caseClaimBillApprovalLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseClaimBillApprovalLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-claim-bill-approval-logs/{id}")
    @Timed
    public ResponseEntity<CaseClaimBillApprovalLogDTO> getCaseClaimBillApprovalLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillApprovalLog : {}", id);
        Optional<CaseClaimBillApprovalLogDTO> caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillApprovalLogDTO);
    }

    /**
     * DELETE  /case-claim-bill-approval-logs/:id : delete the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the caseClaimBillApprovalLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-claim-bill-approval-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseClaimBillApprovalLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillApprovalLog : {}", id);
        caseClaimBillApprovalLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}