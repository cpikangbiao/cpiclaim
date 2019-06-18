package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseCloseLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.dto.CaseCloseLogCriteria;
import com.cpi.claim.service.CaseCloseLogQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseCloseLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseCloseLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseCloseLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseCloseLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseCloseLogService caseCloseLogService;

    private final CaseCloseLogQueryService caseCloseLogQueryService;

    public CaseCloseLogResource(CaseCloseLogService caseCloseLogService, CaseCloseLogQueryService caseCloseLogQueryService) {
        this.caseCloseLogService = caseCloseLogService;
        this.caseCloseLogQueryService = caseCloseLogQueryService;
    }

    /**
     * {@code POST  /case-close-logs} : Create a new caseCloseLog.
     *
     * @param caseCloseLogDTO the caseCloseLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseCloseLogDTO, or with status {@code 400 (Bad Request)} if the caseCloseLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-close-logs")
    public ResponseEntity<CaseCloseLogDTO> createCaseCloseLog(@RequestBody CaseCloseLogDTO caseCloseLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseCloseLog : {}", caseCloseLogDTO);
        if (caseCloseLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseCloseLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseCloseLogDTO result = caseCloseLogService.save(caseCloseLogDTO);
        return ResponseEntity.created(new URI("/api/case-close-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-close-logs} : Updates an existing caseCloseLog.
     *
     * @param caseCloseLogDTO the caseCloseLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseCloseLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseCloseLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseCloseLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-close-logs")
    public ResponseEntity<CaseCloseLogDTO> updateCaseCloseLog(@RequestBody CaseCloseLogDTO caseCloseLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseCloseLog : {}", caseCloseLogDTO);
        if (caseCloseLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseCloseLogDTO result = caseCloseLogService.save(caseCloseLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseCloseLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-close-logs} : get all the caseCloseLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseCloseLogs in body.
     */
    @GetMapping("/case-close-logs")
    public ResponseEntity<List<CaseCloseLogDTO>> getAllCaseCloseLogs(CaseCloseLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseCloseLogs by criteria: {}", criteria);
        Page<CaseCloseLogDTO> page = caseCloseLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-close-logs/count} : count all the caseCloseLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-close-logs/count")
    public ResponseEntity<Long> countCaseCloseLogs(CaseCloseLogCriteria criteria) {
        log.debug("REST request to count CaseCloseLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseCloseLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-close-logs/:id} : get the "id" caseCloseLog.
     *
     * @param id the id of the caseCloseLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseCloseLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-close-logs/{id}")
    public ResponseEntity<CaseCloseLogDTO> getCaseCloseLog(@PathVariable Long id) {
        log.debug("REST request to get CaseCloseLog : {}", id);
        Optional<CaseCloseLogDTO> caseCloseLogDTO = caseCloseLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseCloseLogDTO);
    }

    /**
     * {@code DELETE  /case-close-logs/:id} : delete the "id" caseCloseLog.
     *
     * @param id the id of the caseCloseLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-close-logs/{id}")
    public ResponseEntity<Void> deleteCaseCloseLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseCloseLog : {}", id);
        caseCloseLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
