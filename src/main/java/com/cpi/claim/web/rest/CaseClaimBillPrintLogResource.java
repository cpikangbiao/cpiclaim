package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseClaimBillPrintLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogCriteria;
import com.cpi.claim.service.CaseClaimBillPrintLogQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseClaimBillPrintLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillPrintLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillPrintLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseClaimBillPrintLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseClaimBillPrintLogService caseClaimBillPrintLogService;

    private final CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService;

    public CaseClaimBillPrintLogResource(CaseClaimBillPrintLogService caseClaimBillPrintLogService, CaseClaimBillPrintLogQueryService caseClaimBillPrintLogQueryService) {
        this.caseClaimBillPrintLogService = caseClaimBillPrintLogService;
        this.caseClaimBillPrintLogQueryService = caseClaimBillPrintLogQueryService;
    }

    /**
     * {@code POST  /case-claim-bill-print-logs} : Create a new caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the caseClaimBillPrintLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseClaimBillPrintLogDTO, or with status {@code 400 (Bad Request)} if the caseClaimBillPrintLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-claim-bill-print-logs")
    public ResponseEntity<CaseClaimBillPrintLogDTO> createCaseClaimBillPrintLog(@RequestBody CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillPrintLog : {}", caseClaimBillPrintLogDTO);
        if (caseClaimBillPrintLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillPrintLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillPrintLogDTO result = caseClaimBillPrintLogService.save(caseClaimBillPrintLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-print-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-claim-bill-print-logs} : Updates an existing caseClaimBillPrintLog.
     *
     * @param caseClaimBillPrintLogDTO the caseClaimBillPrintLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseClaimBillPrintLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseClaimBillPrintLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseClaimBillPrintLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-claim-bill-print-logs")
    public ResponseEntity<CaseClaimBillPrintLogDTO> updateCaseClaimBillPrintLog(@RequestBody CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillPrintLog : {}", caseClaimBillPrintLogDTO);
        if (caseClaimBillPrintLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillPrintLogDTO result = caseClaimBillPrintLogService.save(caseClaimBillPrintLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseClaimBillPrintLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-claim-bill-print-logs} : get all the caseClaimBillPrintLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseClaimBillPrintLogs in body.
     */
    @GetMapping("/case-claim-bill-print-logs")
    public ResponseEntity<List<CaseClaimBillPrintLogDTO>> getAllCaseClaimBillPrintLogs(CaseClaimBillPrintLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseClaimBillPrintLogs by criteria: {}", criteria);
        Page<CaseClaimBillPrintLogDTO> page = caseClaimBillPrintLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-claim-bill-print-logs/count} : count all the caseClaimBillPrintLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-claim-bill-print-logs/count")
    public ResponseEntity<Long> countCaseClaimBillPrintLogs(CaseClaimBillPrintLogCriteria criteria) {
        log.debug("REST request to count CaseClaimBillPrintLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseClaimBillPrintLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-claim-bill-print-logs/:id} : get the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the caseClaimBillPrintLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseClaimBillPrintLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-claim-bill-print-logs/{id}")
    public ResponseEntity<CaseClaimBillPrintLogDTO> getCaseClaimBillPrintLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillPrintLog : {}", id);
        Optional<CaseClaimBillPrintLogDTO> caseClaimBillPrintLogDTO = caseClaimBillPrintLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillPrintLogDTO);
    }

    /**
     * {@code DELETE  /case-claim-bill-print-logs/:id} : delete the "id" caseClaimBillPrintLog.
     *
     * @param id the id of the caseClaimBillPrintLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-claim-bill-print-logs/{id}")
    public ResponseEntity<Void> deleteCaseClaimBillPrintLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillPrintLog : {}", id);
        caseClaimBillPrintLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
