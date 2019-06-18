package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseClaimBillApprovalLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogCriteria;
import com.cpi.claim.service.CaseClaimBillApprovalLogQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.CaseClaimBillApprovalLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillApprovalLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseClaimBillApprovalLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseClaimBillApprovalLogService caseClaimBillApprovalLogService;

    private final CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService;

    public CaseClaimBillApprovalLogResource(CaseClaimBillApprovalLogService caseClaimBillApprovalLogService, CaseClaimBillApprovalLogQueryService caseClaimBillApprovalLogQueryService) {
        this.caseClaimBillApprovalLogService = caseClaimBillApprovalLogService;
        this.caseClaimBillApprovalLogQueryService = caseClaimBillApprovalLogQueryService;
    }

    /**
     * {@code POST  /case-claim-bill-approval-logs} : Create a new caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the caseClaimBillApprovalLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseClaimBillApprovalLogDTO, or with status {@code 400 (Bad Request)} if the caseClaimBillApprovalLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-claim-bill-approval-logs")
    public ResponseEntity<CaseClaimBillApprovalLogDTO> createCaseClaimBillApprovalLog(@RequestBody CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillApprovalLog : {}", caseClaimBillApprovalLogDTO);
        if (caseClaimBillApprovalLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillApprovalLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillApprovalLogDTO result = caseClaimBillApprovalLogService.save(caseClaimBillApprovalLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-approval-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-claim-bill-approval-logs} : Updates an existing caseClaimBillApprovalLog.
     *
     * @param caseClaimBillApprovalLogDTO the caseClaimBillApprovalLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseClaimBillApprovalLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseClaimBillApprovalLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseClaimBillApprovalLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-claim-bill-approval-logs")
    public ResponseEntity<CaseClaimBillApprovalLogDTO> updateCaseClaimBillApprovalLog(@RequestBody CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillApprovalLog : {}", caseClaimBillApprovalLogDTO);
        if (caseClaimBillApprovalLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillApprovalLogDTO result = caseClaimBillApprovalLogService.save(caseClaimBillApprovalLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseClaimBillApprovalLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-claim-bill-approval-logs} : get all the caseClaimBillApprovalLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseClaimBillApprovalLogs in body.
     */
    @GetMapping("/case-claim-bill-approval-logs")
    public ResponseEntity<List<CaseClaimBillApprovalLogDTO>> getAllCaseClaimBillApprovalLogs(CaseClaimBillApprovalLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseClaimBillApprovalLogs by criteria: {}", criteria);
        Page<CaseClaimBillApprovalLogDTO> page = caseClaimBillApprovalLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-claim-bill-approval-logs/count} : count all the caseClaimBillApprovalLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-claim-bill-approval-logs/count")
    public ResponseEntity<Long> countCaseClaimBillApprovalLogs(CaseClaimBillApprovalLogCriteria criteria) {
        log.debug("REST request to count CaseClaimBillApprovalLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseClaimBillApprovalLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-claim-bill-approval-logs/:id} : get the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the caseClaimBillApprovalLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseClaimBillApprovalLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-claim-bill-approval-logs/{id}")
    public ResponseEntity<CaseClaimBillApprovalLogDTO> getCaseClaimBillApprovalLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillApprovalLog : {}", id);
        Optional<CaseClaimBillApprovalLogDTO> caseClaimBillApprovalLogDTO = caseClaimBillApprovalLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillApprovalLogDTO);
    }

    /**
     * {@code DELETE  /case-claim-bill-approval-logs/:id} : delete the "id" caseClaimBillApprovalLog.
     *
     * @param id the id of the caseClaimBillApprovalLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-claim-bill-approval-logs/{id}")
    public ResponseEntity<Void> deleteCaseClaimBillApprovalLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillApprovalLog : {}", id);
        caseClaimBillApprovalLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
