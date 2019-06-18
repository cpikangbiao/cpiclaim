package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseClaimBillDeleteLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogCriteria;
import com.cpi.claim.service.CaseClaimBillDeleteLogQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseClaimBillDeleteLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillDeleteLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseClaimBillDeleteLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseClaimBillDeleteLogService caseClaimBillDeleteLogService;

    private final CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService;

    public CaseClaimBillDeleteLogResource(CaseClaimBillDeleteLogService caseClaimBillDeleteLogService, CaseClaimBillDeleteLogQueryService caseClaimBillDeleteLogQueryService) {
        this.caseClaimBillDeleteLogService = caseClaimBillDeleteLogService;
        this.caseClaimBillDeleteLogQueryService = caseClaimBillDeleteLogQueryService;
    }

    /**
     * {@code POST  /case-claim-bill-delete-logs} : Create a new caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the caseClaimBillDeleteLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseClaimBillDeleteLogDTO, or with status {@code 400 (Bad Request)} if the caseClaimBillDeleteLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-claim-bill-delete-logs")
    public ResponseEntity<CaseClaimBillDeleteLogDTO> createCaseClaimBillDeleteLog(@RequestBody CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBillDeleteLog : {}", caseClaimBillDeleteLogDTO);
        if (caseClaimBillDeleteLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBillDeleteLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillDeleteLogDTO result = caseClaimBillDeleteLogService.save(caseClaimBillDeleteLogDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bill-delete-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-claim-bill-delete-logs} : Updates an existing caseClaimBillDeleteLog.
     *
     * @param caseClaimBillDeleteLogDTO the caseClaimBillDeleteLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseClaimBillDeleteLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseClaimBillDeleteLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseClaimBillDeleteLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-claim-bill-delete-logs")
    public ResponseEntity<CaseClaimBillDeleteLogDTO> updateCaseClaimBillDeleteLog(@RequestBody CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBillDeleteLog : {}", caseClaimBillDeleteLogDTO);
        if (caseClaimBillDeleteLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillDeleteLogDTO result = caseClaimBillDeleteLogService.save(caseClaimBillDeleteLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseClaimBillDeleteLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-claim-bill-delete-logs} : get all the caseClaimBillDeleteLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseClaimBillDeleteLogs in body.
     */
    @GetMapping("/case-claim-bill-delete-logs")
    public ResponseEntity<List<CaseClaimBillDeleteLogDTO>> getAllCaseClaimBillDeleteLogs(CaseClaimBillDeleteLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseClaimBillDeleteLogs by criteria: {}", criteria);
        Page<CaseClaimBillDeleteLogDTO> page = caseClaimBillDeleteLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-claim-bill-delete-logs/count} : count all the caseClaimBillDeleteLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-claim-bill-delete-logs/count")
    public ResponseEntity<Long> countCaseClaimBillDeleteLogs(CaseClaimBillDeleteLogCriteria criteria) {
        log.debug("REST request to count CaseClaimBillDeleteLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseClaimBillDeleteLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-claim-bill-delete-logs/:id} : get the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the caseClaimBillDeleteLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseClaimBillDeleteLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-claim-bill-delete-logs/{id}")
    public ResponseEntity<CaseClaimBillDeleteLogDTO> getCaseClaimBillDeleteLog(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBillDeleteLog : {}", id);
        Optional<CaseClaimBillDeleteLogDTO> caseClaimBillDeleteLogDTO = caseClaimBillDeleteLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillDeleteLogDTO);
    }

    /**
     * {@code DELETE  /case-claim-bill-delete-logs/:id} : delete the "id" caseClaimBillDeleteLog.
     *
     * @param id the id of the caseClaimBillDeleteLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-claim-bill-delete-logs/{id}")
    public ResponseEntity<Void> deleteCaseClaimBillDeleteLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBillDeleteLog : {}", id);
        caseClaimBillDeleteLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
