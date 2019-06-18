package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseRegisterLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import com.cpi.claim.service.dto.CaseRegisterLogCriteria;
import com.cpi.claim.service.CaseRegisterLogQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseRegisterLog}.
 */
@RestController
@RequestMapping("/api")
public class CaseRegisterLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseRegisterLogResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseRegisterLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseRegisterLogService caseRegisterLogService;

    private final CaseRegisterLogQueryService caseRegisterLogQueryService;

    public CaseRegisterLogResource(CaseRegisterLogService caseRegisterLogService, CaseRegisterLogQueryService caseRegisterLogQueryService) {
        this.caseRegisterLogService = caseRegisterLogService;
        this.caseRegisterLogQueryService = caseRegisterLogQueryService;
    }

    /**
     * {@code POST  /case-register-logs} : Create a new caseRegisterLog.
     *
     * @param caseRegisterLogDTO the caseRegisterLogDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseRegisterLogDTO, or with status {@code 400 (Bad Request)} if the caseRegisterLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-register-logs")
    public ResponseEntity<CaseRegisterLogDTO> createCaseRegisterLog(@RequestBody CaseRegisterLogDTO caseRegisterLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseRegisterLog : {}", caseRegisterLogDTO);
        if (caseRegisterLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseRegisterLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseRegisterLogDTO result = caseRegisterLogService.save(caseRegisterLogDTO);
        return ResponseEntity.created(new URI("/api/case-register-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-register-logs} : Updates an existing caseRegisterLog.
     *
     * @param caseRegisterLogDTO the caseRegisterLogDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseRegisterLogDTO,
     * or with status {@code 400 (Bad Request)} if the caseRegisterLogDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseRegisterLogDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-register-logs")
    public ResponseEntity<CaseRegisterLogDTO> updateCaseRegisterLog(@RequestBody CaseRegisterLogDTO caseRegisterLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseRegisterLog : {}", caseRegisterLogDTO);
        if (caseRegisterLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseRegisterLogDTO result = caseRegisterLogService.save(caseRegisterLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseRegisterLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-register-logs} : get all the caseRegisterLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseRegisterLogs in body.
     */
    @GetMapping("/case-register-logs")
    public ResponseEntity<List<CaseRegisterLogDTO>> getAllCaseRegisterLogs(CaseRegisterLogCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseRegisterLogs by criteria: {}", criteria);
        Page<CaseRegisterLogDTO> page = caseRegisterLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-register-logs/count} : count all the caseRegisterLogs.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-register-logs/count")
    public ResponseEntity<Long> countCaseRegisterLogs(CaseRegisterLogCriteria criteria) {
        log.debug("REST request to count CaseRegisterLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseRegisterLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-register-logs/:id} : get the "id" caseRegisterLog.
     *
     * @param id the id of the caseRegisterLogDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseRegisterLogDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-register-logs/{id}")
    public ResponseEntity<CaseRegisterLogDTO> getCaseRegisterLog(@PathVariable Long id) {
        log.debug("REST request to get CaseRegisterLog : {}", id);
        Optional<CaseRegisterLogDTO> caseRegisterLogDTO = caseRegisterLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseRegisterLogDTO);
    }

    /**
     * {@code DELETE  /case-register-logs/:id} : delete the "id" caseRegisterLog.
     *
     * @param id the id of the caseRegisterLogDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-register-logs/{id}")
    public ResponseEntity<Void> deleteCaseRegisterLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseRegisterLog : {}", id);
        caseRegisterLogService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
