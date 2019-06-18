package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseEstimateService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.dto.CaseEstimateCriteria;
import com.cpi.claim.service.CaseEstimateQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseEstimate}.
 */
@RestController
@RequestMapping("/api")
public class CaseEstimateResource {

    private final Logger log = LoggerFactory.getLogger(CaseEstimateResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseEstimate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseEstimateService caseEstimateService;

    private final CaseEstimateQueryService caseEstimateQueryService;

    public CaseEstimateResource(CaseEstimateService caseEstimateService, CaseEstimateQueryService caseEstimateQueryService) {
        this.caseEstimateService = caseEstimateService;
        this.caseEstimateQueryService = caseEstimateQueryService;
    }

    /**
     * {@code POST  /case-estimates} : Create a new caseEstimate.
     *
     * @param caseEstimateDTO the caseEstimateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseEstimateDTO, or with status {@code 400 (Bad Request)} if the caseEstimate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-estimates")
    public ResponseEntity<CaseEstimateDTO> createCaseEstimate(@RequestBody CaseEstimateDTO caseEstimateDTO) throws URISyntaxException {
        log.debug("REST request to save CaseEstimate : {}", caseEstimateDTO);
        if (caseEstimateDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseEstimate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseEstimateDTO result = caseEstimateService.save(caseEstimateDTO);
        return ResponseEntity.created(new URI("/api/case-estimates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-estimates} : Updates an existing caseEstimate.
     *
     * @param caseEstimateDTO the caseEstimateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseEstimateDTO,
     * or with status {@code 400 (Bad Request)} if the caseEstimateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseEstimateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-estimates")
    public ResponseEntity<CaseEstimateDTO> updateCaseEstimate(@RequestBody CaseEstimateDTO caseEstimateDTO) throws URISyntaxException {
        log.debug("REST request to update CaseEstimate : {}", caseEstimateDTO);
        if (caseEstimateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseEstimateDTO result = caseEstimateService.save(caseEstimateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseEstimateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-estimates} : get all the caseEstimates.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseEstimates in body.
     */
    @GetMapping("/case-estimates")
    public ResponseEntity<List<CaseEstimateDTO>> getAllCaseEstimates(CaseEstimateCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseEstimates by criteria: {}", criteria);
        Page<CaseEstimateDTO> page = caseEstimateQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-estimates/count} : count all the caseEstimates.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-estimates/count")
    public ResponseEntity<Long> countCaseEstimates(CaseEstimateCriteria criteria) {
        log.debug("REST request to count CaseEstimates by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseEstimateQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-estimates/:id} : get the "id" caseEstimate.
     *
     * @param id the id of the caseEstimateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseEstimateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-estimates/{id}")
    public ResponseEntity<CaseEstimateDTO> getCaseEstimate(@PathVariable Long id) {
        log.debug("REST request to get CaseEstimate : {}", id);
        Optional<CaseEstimateDTO> caseEstimateDTO = caseEstimateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseEstimateDTO);
    }

    /**
     * {@code DELETE  /case-estimates/:id} : delete the "id" caseEstimate.
     *
     * @param id the id of the caseEstimateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-estimates/{id}")
    public ResponseEntity<Void> deleteCaseEstimate(@PathVariable Long id) {
        log.debug("REST request to delete CaseEstimate : {}", id);
        caseEstimateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
