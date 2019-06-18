package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseClaimService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.service.dto.CaseClaimCriteria;
import com.cpi.claim.service.CaseClaimQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseClaim}.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseClaim";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseClaimService caseClaimService;

    private final CaseClaimQueryService caseClaimQueryService;

    public CaseClaimResource(CaseClaimService caseClaimService, CaseClaimQueryService caseClaimQueryService) {
        this.caseClaimService = caseClaimService;
        this.caseClaimQueryService = caseClaimQueryService;
    }

    /**
     * {@code POST  /case-claims} : Create a new caseClaim.
     *
     * @param caseClaimDTO the caseClaimDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseClaimDTO, or with status {@code 400 (Bad Request)} if the caseClaim has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-claims")
    public ResponseEntity<CaseClaimDTO> createCaseClaim(@RequestBody CaseClaimDTO caseClaimDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaim : {}", caseClaimDTO);
        if (caseClaimDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaim cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimDTO result = caseClaimService.save(caseClaimDTO);
        return ResponseEntity.created(new URI("/api/case-claims/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-claims} : Updates an existing caseClaim.
     *
     * @param caseClaimDTO the caseClaimDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseClaimDTO,
     * or with status {@code 400 (Bad Request)} if the caseClaimDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseClaimDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-claims")
    public ResponseEntity<CaseClaimDTO> updateCaseClaim(@RequestBody CaseClaimDTO caseClaimDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaim : {}", caseClaimDTO);
        if (caseClaimDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimDTO result = caseClaimService.save(caseClaimDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseClaimDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-claims} : get all the caseClaims.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseClaims in body.
     */
    @GetMapping("/case-claims")
    public ResponseEntity<List<CaseClaimDTO>> getAllCaseClaims(CaseClaimCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseClaims by criteria: {}", criteria);
        Page<CaseClaimDTO> page = caseClaimQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-claims/count} : count all the caseClaims.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-claims/count")
    public ResponseEntity<Long> countCaseClaims(CaseClaimCriteria criteria) {
        log.debug("REST request to count CaseClaims by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseClaimQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-claims/:id} : get the "id" caseClaim.
     *
     * @param id the id of the caseClaimDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseClaimDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-claims/{id}")
    public ResponseEntity<CaseClaimDTO> getCaseClaim(@PathVariable Long id) {
        log.debug("REST request to get CaseClaim : {}", id);
        Optional<CaseClaimDTO> caseClaimDTO = caseClaimService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimDTO);
    }

    /**
     * {@code DELETE  /case-claims/:id} : delete the "id" caseClaim.
     *
     * @param id the id of the caseClaimDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-claims/{id}")
    public ResponseEntity<Void> deleteCaseClaim(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaim : {}", id);
        caseClaimService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
