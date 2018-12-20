package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillPrintLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.CaseClaimBillPrintLogQueryService;
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
 * REST controller for managing CaseClaimBillPrintLog.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillPrintLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogResource.class);

    private static final String ENTITY_NAME = "caseClaimBillPrintLog";

    private final CaseClaimBillPrintLogService caseClaimBillPrintLogService;

    private final CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService;

    public CaseClaimBillPrintLogResource(CaseClaimBillPrintLogService caseClaimBillPrintLogService, CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService) {
        this.caseClaimBillPrintLogService = caseClaimBillPrintLogService;
        this.caseClaimBillPrintLogQueryService = caseClaimBillPrintLogQueryService;
    }

    /**
     * POST  /case-claim-bill-print-logs : Create a new caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the caseClaimBillPrintLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseClaimBillPrintLogDTO, or with status 400 (Bad Request) if the caseClaimBillPrintLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-claim-bill-print-logs")
    @Timed
    public ResponseEntity<CaseClaimBillPrintLogDTO> createCaseClaimBillPrintLog(@RequestBody CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillPrintLog : {}", caseClaimBillPrintLogDTO);
        if (caseClaimBillPrintLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillPrintLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillPrintLogDTO result = caseClaimBillPrintLogService.save(caseClaimBillPrintLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-print-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-claim-bill-print-logs : Updates an existing caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the caseClaimBillPrintLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseClaimBillPrintLogDTO,
     * or with status 400 (Bad Request) if the caseClaimBillPrintLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseClaimBillPrintLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-claim-bill-print-logs")
    @Timed
    public ResponseEntity<CaseClaimBillPrintLogDTO> updateCaseClaimBillPrintLog(@RequestBody CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillPrintLog : {}", caseClaimBillPrintLogDTO);
        if (caseClaimBillPrintLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillPrintLogDTO result = caseClaimBillPrintLogService.save(caseClaimBillPrintLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseClaimBillPrintLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-claim-bill-print-logs : get all the caseClaimBillPrintLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseClaimBillPrintLogs in body
     */
    @GetMapping("/case-claim-bill-print-logs")
    @Timed
    public ResponseEntity<List<CaseClaimBillPrintLogDTO>> getAllCaseClaimBillPrintLogs(CaseClaimBillPrintLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaimBillPrintLogs by criteria: {}", criteria);
        Page<CaseClaimBillPrintLogDTO> page = caseClaimBillPrintLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bill-print-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-claim-bill-print-logs/:id : get the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the caseClaimBillPrintLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseClaimBillPrintLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-claim-bill-print-logs/{id}")
    @Timed
    public ResponseEntity<CaseClaimBillPrintLogDTO> getCaseClaimBillPrintLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillPrintLog : {}", id);
        Optional<CaseClaimBillPrintLogDTO> caseClaimBillPrintLogDTO = caseClaimBillPrintLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillPrintLogDTO);
    }

    /**
     * DELETE  /case-claim-bill-print-logs/:id : delete the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the caseClaimBillPrintLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-claim-bill-print-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseClaimBillPrintLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillPrintLog : {}", id);
        caseClaimBillPrintLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
