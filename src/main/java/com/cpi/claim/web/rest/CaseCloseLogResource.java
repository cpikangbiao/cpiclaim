package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseCloseLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.dto.CaseCloseLogCriteria;
import com.cpi.claim.service.CaseCloseLogQueryService;
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
 * REST controller for managing CaseCloseLog.
 */
@RestController
@RequestMapping("/api")
public class CaseCloseLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseCloseLogResource.class);

    private static final String ENTITY_NAME = "caseCloseLog";

    private final CaseCloseLogService caseCloseLogService;

    private final CaseCloseLogQueryService caseCloseLogQueryService;

    public CaseCloseLogResource(CaseCloseLogService caseCloseLogService, CaseCloseLogQueryService caseCloseLogQueryService) {
        this.caseCloseLogService = caseCloseLogService;
        this.caseCloseLogQueryService = caseCloseLogQueryService;
    }

    /**
     * POST  /case-close-logs : Create a new caseCloseLog.
     *
     * @param caseCloseLogDTO the caseCloseLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseCloseLogDTO, or with status 400 (Bad Request) if the caseCloseLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-close-logs")
    @Timed
    public ResponseEntity<CaseCloseLogDTO> createCaseCloseLog(@RequestBody CaseCloseLogDTO caseCloseLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseCloseLog : {}", caseCloseLogDTO);
        if (caseCloseLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseCloseLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseCloseLogDTO result = caseCloseLogService.save(caseCloseLogDTO);
        return ResponseEntity.created(new URI("/api/case-close-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-close-logs : Updates an existing caseCloseLog.
     *
     * @param caseCloseLogDTO the caseCloseLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseCloseLogDTO,
     * or with status 400 (Bad Request) if the caseCloseLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseCloseLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-close-logs")
    @Timed
    public ResponseEntity<CaseCloseLogDTO> updateCaseCloseLog(@RequestBody CaseCloseLogDTO caseCloseLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseCloseLog : {}", caseCloseLogDTO);
        if (caseCloseLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseCloseLogDTO result = caseCloseLogService.save(caseCloseLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseCloseLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-close-logs : get all the caseCloseLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseCloseLogs in body
     */
    @GetMapping("/case-close-logs")
    @Timed
    public ResponseEntity<List<CaseCloseLogDTO>> getAllCaseCloseLogs(CaseCloseLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseCloseLogs by criteria: {}", criteria);
        Page<CaseCloseLogDTO> page = caseCloseLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-close-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-close-logs/:id : get the "id" caseCloseLog.
     *
     * @param id the id of the caseCloseLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseCloseLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-close-logs/{id}")
    @Timed
    public ResponseEntity<CaseCloseLogDTO> getCaseCloseLog(@PathVariable Long id) {
        log.debug("REST request to get CaseCloseLog : {}", id);
        Optional<CaseCloseLogDTO> caseCloseLogDTO = caseCloseLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseCloseLogDTO);
    }

    /**
     * DELETE  /case-close-logs/:id : delete the "id" caseCloseLog.
     *
     * @param id the id of the caseCloseLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-close-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseCloseLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseCloseLog : {}", id);
        caseCloseLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
