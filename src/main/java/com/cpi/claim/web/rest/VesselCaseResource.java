package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.VesselCaseQueryService;
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
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    private final VesselCaseService vesselCaseService;

    private final VesselCaseQueryService vesselCaseQueryService;

    public VesselCaseResource(VesselCaseService vesselCaseService, VesselCaseQueryService vesselCaseQueryService) {
        this.vesselCaseService = vesselCaseService;
        this.vesselCaseQueryService = vesselCaseQueryService;
    }

    /**
     * POST  /vessel-cases : Create a new vesselCase.
     *
     * @param vesselCaseDTO the vesselCaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vesselCaseDTO, or with status 400 (Bad Request) if the vesselCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vessel-cases")
    @Timed
    public ResponseEntity<VesselCaseDTO> createVesselCase(@Valid @RequestBody VesselCaseDTO vesselCaseDTO) throws URISyntaxException {
        log.debug("REST request to save VesselCase : {}", vesselCaseDTO);
        if (vesselCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new vesselCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VesselCaseDTO result = vesselCaseService.save(vesselCaseDTO);
        return ResponseEntity.created(new URI("/api/vessel-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vessel-cases : Updates an existing vesselCase.
     *
     * @param vesselCaseDTO the vesselCaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vesselCaseDTO,
     * or with status 400 (Bad Request) if the vesselCaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the vesselCaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vessel-cases")
    @Timed
    public ResponseEntity<VesselCaseDTO> updateVesselCase(@Valid @RequestBody VesselCaseDTO vesselCaseDTO) throws URISyntaxException {
        log.debug("REST request to update VesselCase : {}", vesselCaseDTO);
        if (vesselCaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VesselCaseDTO result = vesselCaseService.save(vesselCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vesselCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vessel-cases : get all the vesselCases.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vesselCases in body
     */
    @GetMapping("/vessel-cases")
    @Timed
    public ResponseEntity<List<VesselCaseDTO>> getAllVesselCases(VesselCaseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VesselCases by criteria: {}", criteria);
        Page<VesselCaseDTO> page = vesselCaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vessel-cases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vessel-cases/:id : get the "id" vesselCase.
     *
     * @param id the id of the vesselCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vesselCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vessel-cases/{id}")
    @Timed
    public ResponseEntity<VesselCaseDTO> getVesselCase(@PathVariable Long id) {
        log.debug("REST request to get VesselCase : {}", id);
        Optional<VesselCaseDTO> vesselCaseDTO = vesselCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vesselCaseDTO);
    }

    /**
     * DELETE  /vessel-cases/:id : delete the "id" vesselCase.
     *
     * @param id the id of the vesselCaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vessel-cases/{id}")
    @Timed
    public ResponseEntity<Void> deleteVesselCase(@PathVariable Long id) {
        log.debug("REST request to delete VesselCase : {}", id);
        vesselCaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}