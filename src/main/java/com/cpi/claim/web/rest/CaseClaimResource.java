package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.dto.CaseClaimCriteria;
import com.cpi.claim.service.CaseClaimQueryService;
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
 * REST controller for managing CaseClaim.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimResource.class);

    private static final String ENTITY_NAME = "caseClaim";

    private final CaseClaimService caseClaimService;

    private final CaseClaimQueryService caseClaimQueryService;

    public CaseClaimResource(CaseClaimService caseClaimService, CaseClaimQueryService caseClaimQueryService) {
        this.caseClaimService = caseClaimService;
        this.caseClaimQueryService = caseClaimQueryService;
    }

    /**
     * POST  /case-claims : Create a new caseClaim.
     *
     * @param caseClaimDTO the caseClaimDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseClaimDTO, or with status 400 (Bad Request) if the caseClaim has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-claims")
    @Timed
    public ResponseEntity<CaseClaimDTO> createCaseClaim(@RequestBody CaseClaimDTO caseClaimDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaim : {}", caseClaimDTO);
        if (caseClaimDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimDTO result = caseClaimService.save(caseClaimDTO);
        return ResponseEntity.created(new URI("/api/case-claims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-claims : Updates an existing caseClaim.
     *
     * @param caseClaimDTO the caseClaimDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseClaimDTO,
     * or with status 400 (Bad Request) if the caseClaimDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseClaimDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-claims")
    @Timed
    public ResponseEntity<CaseClaimDTO> updateCaseClaim(@RequestBody CaseClaimDTO caseClaimDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaim : {}", caseClaimDTO);
        if (caseClaimDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimDTO result = caseClaimService.save(caseClaimDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseClaimDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-claims : get all the caseClaims.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseClaims in body
     */
    @GetMapping("/case-claims")
    @Timed
    public ResponseEntity<List<CaseClaimDTO>> getAllCaseClaims(CaseClaimCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaims by criteria: {}", criteria);
        Page<CaseClaimDTO> page = caseClaimQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claims");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-claims/:id : get the "id" caseClaim.
     *
     * @param id the id of the caseClaimDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseClaimDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-claims/{id}")
    @Timed
    public ResponseEntity<CaseClaimDTO> getCaseClaim(@PathVariable Long id) {
        log.debug("REST request to get CaseClaim : {}", id);
        Optional<CaseClaimDTO> caseClaimDTO = caseClaimService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimDTO);
    }

    /**
     * DELETE  /case-claims/:id : delete the "id" caseClaim.
     *
     * @param id the id of the caseClaimDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-claims/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseClaim(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaim : {}", id);
        caseClaimService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
