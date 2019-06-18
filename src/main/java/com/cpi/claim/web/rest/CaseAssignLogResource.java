package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseAssignLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseAssignLogDTO;
import com.cpi.claim.service.dto.CaseAssignLogCriteria;
import com.cpi.claim.service.CaseAssignLogQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseAssignLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseAssignLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseAssignLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseAssignLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseAssignLogService caseAssignLogService;

    private final CaseAssignLogQueryService caseAssignLogQueryService;

    public CaseAssignLogResource(CaseAssignLogService caseAssignLogService, CaseAssignLogQueryService caseAssignLogQueryService) {
        this.caseAssignLogService = caseAssignLogService;
        this.caseAssignLogQueryService = caseAssignLogQueryService;
    }

    /**
     * {@code POST  /case-assign-logs} : Create a new caseAssignLog.
     *
     * @param caseAssignLogDTO the caseAssignLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseAssignLogDTO, or with status {@code 400 (Bad Request)} if the caseAssignLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-assign-logs")
    public ResponseEntity<CaseAssignLogDTO> createCaseAssignLog(@RequestBody CaseAssignLogDTO caseAssignLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseAssignLog : {}", caseAssignLogDTO);
        if (caseAssignLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseAssignLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseAssignLogDTO result = caseAssignLogService.save(caseAssignLogDTO);
        return ResponseEntity.created(new URI("/api/case-assign-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-assign-logs} : Updates an existing caseAssignLog.
     *
     * @param caseAssignLogDTO the caseAssignLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseAssignLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseAssignLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseAssignLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-assign-logs")
    public ResponseEntity<CaseAssignLogDTO> updateCaseAssignLog(@RequestBody CaseAssignLogDTO caseAssignLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseAssignLog : {}", caseAssignLogDTO);
        if (caseAssignLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseAssignLogDTO result = caseAssignLogService.save(caseAssignLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseAssignLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-assign-logs} : get all the caseAssignLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseAssignLogs in body.
     */
    @GetMapping("/case-assign-logs")
    public ResponseEntity<List<CaseAssignLogDTO>> getAllCaseAssignLogs(CaseAssignLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseAssignLogs by criteria: {}", criteria);
        Page<CaseAssignLogDTO> page = caseAssignLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-assign-logs/count} : count all the caseAssignLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-assign-logs/count")
    public ResponseEntity<Long> countCaseAssignLogs(CaseAssignLogCriteria criteria) {
        log.debug("REST request to count CaseAssignLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseAssignLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-assign-logs/:id} : get the "id" caseAssignLog.
     *
     * @param id the id of the caseAssignLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseAssignLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-assign-logs/{id}")
    public ResponseEntity<CaseAssignLogDTO> getCaseAssignLog(@PathVariable Long id) {
        log.debug("REST request to get CaseAssignLog : {}", id);
        Optional<CaseAssignLogDTO> caseAssignLogDTO = caseAssignLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseAssignLogDTO);
    }

    /**
     * {@code DELETE  /case-assign-logs/:id} : delete the "id" caseAssignLog.
     *
     * @param id the id of the caseAssignLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-assign-logs/{id}")
    public ResponseEntity<Void> deleteCaseAssignLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseAssignLog : {}", id);
        caseAssignLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
