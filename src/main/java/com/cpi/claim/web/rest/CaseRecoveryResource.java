package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseRecoveryService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseRecoveryDTO;
import com.cpi.claim.service.dto.CaseRecoveryCriteria;
import com.cpi.claim.service.CaseRecoveryQueryService;
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
 * REST controller for managing CaseRecovery.
 */
@RestController
@RequestMapping("/api")
public class CaseRecoveryResource {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryResource.class);

    private static final String ENTITY_NAME = "caseRecovery";

    private final CaseRecoveryService caseRecoveryService;

    private final CaseRecoveryQueryService caseRecoveryQueryService;

    public CaseRecoveryResource(CaseRecoveryService caseRecoveryService, CaseRecoveryQueryService caseRecoveryQueryService) {
        this.caseRecoveryService = caseRecoveryService;
        this.caseRecoveryQueryService = caseRecoveryQueryService;
    }

    /**
     * POST  /case-recoveries : Create a new caseRecovery.
     *
     * @param caseRecoveryDTO the caseRecoveryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseRecoveryDTO, or with status 400 (Bad Request) if the caseRecovery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-recoveries")
    @Timed
    public ResponseEntity<CaseRecoveryDTO> createCaseRecovery(@RequestBody CaseRecoveryDTO caseRecoveryDTO) throws URISyntaxException {
        log.debug("REST request to save CaseRecovery : {}", caseRecoveryDTO);
        if (caseRecoveryDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseRecovery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseRecoveryDTO result = caseRecoveryService.save(caseRecoveryDTO);
        return ResponseEntity.created(new URI("/api/case-recoveries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-recoveries : Updates an existing caseRecovery.
     *
     * @param caseRecoveryDTO the caseRecoveryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseRecoveryDTO,
     * or with status 400 (Bad Request) if the caseRecoveryDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseRecoveryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-recoveries")
    @Timed
    public ResponseEntity<CaseRecoveryDTO> updateCaseRecovery(@RequestBody CaseRecoveryDTO caseRecoveryDTO) throws URISyntaxException {
        log.debug("REST request to update CaseRecovery : {}", caseRecoveryDTO);
        if (caseRecoveryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseRecoveryDTO result = caseRecoveryService.save(caseRecoveryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseRecoveryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-recoveries : get all the caseRecoveries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseRecoveries in body
     */
    @GetMapping("/case-recoveries")
    @Timed
    public ResponseEntity<List<CaseRecoveryDTO>> getAllCaseRecoveries(CaseRecoveryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseRecoveries by criteria: {}", criteria);
        Page<CaseRecoveryDTO> page = caseRecoveryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-recoveries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-recoveries/:id : get the "id" caseRecovery.
     *
     * @param id the id of the caseRecoveryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseRecoveryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-recoveries/{id}")
    @Timed
    public ResponseEntity<CaseRecoveryDTO> getCaseRecovery(@PathVariable Long id) {
        log.debug("REST request to get CaseRecovery : {}", id);
        Optional<CaseRecoveryDTO> caseRecoveryDTO = caseRecoveryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseRecoveryDTO);
    }

    /**
     * DELETE  /case-recoveries/:id : delete the "id" caseRecovery.
     *
     * @param id the id of the caseRecoveryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-recoveries/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseRecovery(@PathVariable Long id) {
        log.debug("REST request to delete CaseRecovery : {}", id);
        caseRecoveryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}