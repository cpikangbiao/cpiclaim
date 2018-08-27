package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.CaseFeeQueryService;
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
 * REST controller for managing CaseFee.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeResource.class);

    private static final String ENTITY_NAME = "caseFee";

    private final CaseFeeService caseFeeService;

    private final CaseFeeQueryService caseFeeQueryService;

    public CaseFeeResource(CaseFeeService caseFeeService, CaseFeeQueryService caseFeeQueryService) {
        this.caseFeeService = caseFeeService;
        this.caseFeeQueryService = caseFeeQueryService;
    }

    /**
     * POST  /case-fees : Create a new caseFee.
     *
     * @param caseFeeDTO the caseFeeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseFeeDTO, or with status 400 (Bad Request) if the caseFee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-fees")
    @Timed
    public ResponseEntity<CaseFeeDTO> createCaseFee(@RequestBody CaseFeeDTO caseFeeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseFee : {}", caseFeeDTO);
        if (caseFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseFeeDTO result = caseFeeService.save(caseFeeDTO);
        return ResponseEntity.created(new URI("/api/case-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-fees : Updates an existing caseFee.
     *
     * @param caseFeeDTO the caseFeeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseFeeDTO,
     * or with status 400 (Bad Request) if the caseFeeDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseFeeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-fees")
    @Timed
    public ResponseEntity<CaseFeeDTO> updateCaseFee(@RequestBody CaseFeeDTO caseFeeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseFee : {}", caseFeeDTO);
        if (caseFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseFeeDTO result = caseFeeService.save(caseFeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-fees : get all the caseFees.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseFees in body
     */
    @GetMapping("/case-fees")
    @Timed
    public ResponseEntity<List<CaseFeeDTO>> getAllCaseFees(CaseFeeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseFees by criteria: {}", criteria);
        Page<CaseFeeDTO> page = caseFeeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-fees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-fees/:id : get the "id" caseFee.
     *
     * @param id the id of the caseFeeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseFeeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-fees/{id}")
    @Timed
    public ResponseEntity<CaseFeeDTO> getCaseFee(@PathVariable Long id) {
        log.debug("REST request to get CaseFee : {}", id);
        Optional<CaseFeeDTO> caseFeeDTO = caseFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseFeeDTO);
    }

    /**
     * DELETE  /case-fees/:id : delete the "id" caseFee.
     *
     * @param id the id of the caseFeeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-fees/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseFee(@PathVariable Long id) {
        log.debug("REST request to delete CaseFee : {}", id);
        caseFeeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}