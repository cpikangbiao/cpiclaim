package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseSettlementModeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.dto.CaseSettlementModeCriteria;
import com.cpi.claim.service.CaseSettlementModeQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CaseSettlementMode.
 */
@RestController
@RequestMapping("/api")
public class CaseSettlementModeResource {

    private final Logger log = LoggerFactory.getLogger(CaseSettlementModeResource.class);

    private static final String ENTITY_NAME = "caseSettlementMode";

    private final CaseSettlementModeService caseSettlementModeService;

    private final CaseSettlementModeQueryService caseSettlementModeQueryService;

    public CaseSettlementModeResource(CaseSettlementModeService caseSettlementModeService, CaseSettlementModeQueryService caseSettlementModeQueryService) {
        this.caseSettlementModeService = caseSettlementModeService;
        this.caseSettlementModeQueryService = caseSettlementModeQueryService;
    }

    /**
     * POST  /case-settlement-modes : Create a new caseSettlementMode.
     *
     * @param caseSettlementModeDTO the caseSettlementModeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseSettlementModeDTO, or with status 400 (Bad Request) if the caseSettlementMode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-settlement-modes")
    @Timed
    public ResponseEntity<CaseSettlementModeDTO> createCaseSettlementMode(@Valid @RequestBody CaseSettlementModeDTO caseSettlementModeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseSettlementMode : {}", caseSettlementModeDTO);
        if (caseSettlementModeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseSettlementMode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseSettlementModeDTO result = caseSettlementModeService.save(caseSettlementModeDTO);
        return ResponseEntity.created(new URI("/api/case-settlement-modes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-settlement-modes : Updates an existing caseSettlementMode.
     *
     * @param caseSettlementModeDTO the caseSettlementModeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseSettlementModeDTO,
     * or with status 400 (Bad Request) if the caseSettlementModeDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseSettlementModeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-settlement-modes")
    @Timed
    public ResponseEntity<CaseSettlementModeDTO> updateCaseSettlementMode(@Valid @RequestBody CaseSettlementModeDTO caseSettlementModeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseSettlementMode : {}", caseSettlementModeDTO);
        if (caseSettlementModeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseSettlementModeDTO result = caseSettlementModeService.save(caseSettlementModeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseSettlementModeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-settlement-modes : get all the caseSettlementModes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseSettlementModes in body
     */
    @GetMapping("/case-settlement-modes")
    @Timed
    public ResponseEntity<List<CaseSettlementModeDTO>> getAllCaseSettlementModes(CaseSettlementModeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseSettlementModes by criteria: {}", criteria);
        Page<CaseSettlementModeDTO> page = caseSettlementModeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-settlement-modes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-settlement-modes/:id : get the "id" caseSettlementMode.
     *
     * @param id the id of the caseSettlementModeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseSettlementModeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-settlement-modes/{id}")
    @Timed
    public ResponseEntity<CaseSettlementModeDTO> getCaseSettlementMode(@PathVariable Long id) {
        log.debug("REST request to get CaseSettlementMode : {}", id);
        Optional<CaseSettlementModeDTO> caseSettlementModeDTO = caseSettlementModeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseSettlementModeDTO);
    }

    /**
     * DELETE  /case-settlement-modes/:id : delete the "id" caseSettlementMode.
     *
     * @param id the id of the caseSettlementModeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-settlement-modes/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseSettlementMode(@PathVariable Long id) {
        log.debug("REST request to delete CaseSettlementMode : {}", id);
        caseSettlementModeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
