package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillDeleteLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;
import com.cpi.claim.service.CaseClaimBillDeleteLogQueryService;
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
public class CaseClaimBillDeleteLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogResource.class);

    private static final String ENTITY_NAME = "caseClaimBillDeleteLog";

    private final CaseClaimBillDeleteLogService caseClaimBillDeleteLogService;

    private final CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService;

    public CaseClaimBillDeleteLogResource(CaseClaimBillDeleteLogService caseClaimBillDeleteLogService, CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService) {
        this.caseClaimBillDeleteLogService = caseClaimBillDeleteLogService;
        this.caseClaimBillDeleteLogQueryService = caseClaimBillDeleteLogQueryService;
    }

    /**
     * POST  /case-claim-bill-delete-logs : Create a new caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the caseClaimBillDeleteLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseClaimBillDeleteLogDTO, or with status 400 (Bad Request) if the caseClaimBillDeleteLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-claim-bill-delete-logs")
    @Timed
    public ResponseEntity<CaseClaimBillDeleteLogDTO> createCaseClaimBillDeleteLog(@RequestBody CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillDeleteLog : {}", caseClaimBillDeleteLogDTO);
        if (caseClaimBillDeleteLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillDeleteLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillDeleteLogDTO result = caseClaimBillDeleteLogService.save(caseClaimBillDeleteLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-delete-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-claim-bill-delete-logs : Updates an existing caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the caseClaimBillDeleteLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseClaimBillDeleteLogDTO,
     * or with status 400 (Bad Request) if the caseClaimBillDeleteLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseClaimBillDeleteLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-claim-bill-delete-logs")
    @Timed
    public ResponseEntity<CaseClaimBillDeleteLogDTO> updateCaseClaimBillDeleteLog(@RequestBody CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillDeleteLog : {}", caseClaimBillDeleteLogDTO);
        if (caseClaimBillDeleteLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillDeleteLogDTO result = caseClaimBillDeleteLogService.save(caseClaimBillDeleteLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseClaimBillDeleteLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-claim-bill-delete-logs : get all the caseClaimBillDeleteLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseClaimBillDeleteLogs in body
     */
    @GetMapping("/case-claim-bill-delete-logs")
    @Timed
    public ResponseEntity<List<CaseClaimBillDeleteLogDTO>> getAllCaseClaimBillDeleteLogs(CaseClaimBillDeleteLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaimBillDeleteLogs by criteria: {}", criteria);
        Page<CaseClaimBillDeleteLogDTO> page = caseClaimBillDeleteLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bill-delete-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-claim-bill-delete-logs/:id : get the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the caseClaimBillDeleteLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseClaimBillDeleteLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-claim-bill-delete-logs/{id}")
    @Timed
    public ResponseEntity<CaseClaimBillDeleteLogDTO> getCaseClaimBillDeleteLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillDeleteLog : {}", id);
        Optional<CaseClaimBillDeleteLogDTO> caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillDeleteLogDTO);
    }

    /**
     * DELETE  /case-claim-bill-delete-logs/:id : delete the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the caseClaimBillDeleteLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-claim-bill-delete-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseClaimBillDeleteLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillDeleteLog : {}", id);
        caseClaimBillDeleteLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
