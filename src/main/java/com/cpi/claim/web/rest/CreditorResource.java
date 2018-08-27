package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CreditorService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CreditorDTO;
import com.cpi.claim.service.dto.CreditorCriteria;
import com.cpi.claim.service.CreditorQueryService;
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
 * REST controller for managing Creditor.
 */
@RestController
@RequestMapping("/api")
public class CreditorResource {

    private final Logger log = LoggerFactory.getLogger(CreditorResource.class);

    private static final String ENTITY_NAME = "creditor";

    private final CreditorService creditorService;

    private final CreditorQueryService creditorQueryService;

    public CreditorResource(CreditorService creditorService, CreditorQueryService creditorQueryService) {
        this.creditorService = creditorService;
        this.creditorQueryService = creditorQueryService;
    }

    /**
     * POST  /creditors : Create a new creditor.
     *
     * @param creditorDTO the creditorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new creditorDTO, or with status 400 (Bad Request) if the creditor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/creditors")
    @Timed
    public ResponseEntity<CreditorDTO> createCreditor(@RequestBody CreditorDTO creditorDTO) throws URISyntaxException {
        log.debug("REST request to save Creditor : {}", creditorDTO);
        if (creditorDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreditorDTO result = creditorService.save(creditorDTO);
        return ResponseEntity.created(new URI("/api/creditors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /creditors : Updates an existing creditor.
     *
     * @param creditorDTO the creditorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated creditorDTO,
     * or with status 400 (Bad Request) if the creditorDTO is not valid,
     * or with status 500 (Internal Server Error) if the creditorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/creditors")
    @Timed
    public ResponseEntity<CreditorDTO> updateCreditor(@RequestBody CreditorDTO creditorDTO) throws URISyntaxException {
        log.debug("REST request to update Creditor : {}", creditorDTO);
        if (creditorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreditorDTO result = creditorService.save(creditorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, creditorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /creditors : get all the creditors.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of creditors in body
     */
    @GetMapping("/creditors")
    @Timed
    public ResponseEntity<List<CreditorDTO>> getAllCreditors(CreditorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Creditors by criteria: {}", criteria);
        Page<CreditorDTO> page = creditorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/creditors");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /creditors/:id : get the "id" creditor.
     *
     * @param id the id of the creditorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the creditorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/creditors/{id}")
    @Timed
    public ResponseEntity<CreditorDTO> getCreditor(@PathVariable Long id) {
        log.debug("REST request to get Creditor : {}", id);
        Optional<CreditorDTO> creditorDTO = creditorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditorDTO);
    }

    /**
     * DELETE  /creditors/:id : delete the "id" creditor.
     *
     * @param id the id of the creditorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/creditors/{id}")
    @Timed
    public ResponseEntity<Void> deleteCreditor(@PathVariable Long id) {
        log.debug("REST request to delete Creditor : {}", id);
        creditorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
