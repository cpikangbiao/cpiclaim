package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.VesselSubCaseExtService;
import com.cpi.claim.service.VesselSubCaseService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.VesselSubCaseDTO;
import com.cpi.claim.service.dto.VesselSubCaseCriteria;
import com.cpi.claim.service.VesselSubCaseQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing VesselSubCase.
 */
@RestController
@RequestMapping("/api")
public class VesselSubCaseResource {

    private final Logger log = LoggerFactory.getLogger(VesselSubCaseResource.class);

    private static final String ENTITY_NAME = "vesselSubCase";

    private final VesselSubCaseService vesselSubCaseService;

    private final VesselSubCaseQueryService vesselSubCaseQueryService;

    @Autowired
    private VesselSubCaseExtService vesselSubCaseExtService;

    public VesselSubCaseResource(VesselSubCaseService vesselSubCaseService, VesselSubCaseQueryService vesselSubCaseQueryService) {
        this.vesselSubCaseService = vesselSubCaseService;
        this.vesselSubCaseQueryService = vesselSubCaseQueryService;
    }

    /**
     * POST  /vessel-sub-cases : Create a new vesselSubCase.
     *
     * @param vesselSubCaseDTO the vesselSubCaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vesselSubCaseDTO, or with status 400 (Bad Request) if the vesselSubCase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vessel-sub-cases")
    @Timed
    public ResponseEntity<VesselSubCaseDTO> createVesselSubCase(@RequestBody VesselSubCaseDTO vesselSubCaseDTO) throws URISyntaxException {
        log.debug("REST request to save VesselSubCase : {}", vesselSubCaseDTO);
        if (vesselSubCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new vesselSubCase cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (vesselSubCaseDTO.getVesselCaseId() == null) {
            throw new BadRequestAlertException("A new vesselSubCase cannot saved because VesselCaseId is NULL", ENTITY_NAME, "idexists");
        }

        vesselSubCaseDTO.setNumberId(vesselSubCaseExtService.findNextNumberIdByVesselCaseId(vesselSubCaseDTO.getVesselCaseId()));

        VesselSubCaseDTO result = vesselSubCaseService.save(vesselSubCaseDTO);
        return ResponseEntity.created(new URI("/api/vessel-sub-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vessel-sub-cases : Updates an existing vesselSubCase.
     *
     * @param vesselSubCaseDTO the vesselSubCaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vesselSubCaseDTO,
     * or with status 400 (Bad Request) if the vesselSubCaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the vesselSubCaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vessel-sub-cases")
    @Timed
    public ResponseEntity<VesselSubCaseDTO> updateVesselSubCase(@RequestBody VesselSubCaseDTO vesselSubCaseDTO) throws URISyntaxException {
        log.debug("REST request to update VesselSubCase : {}", vesselSubCaseDTO);
        if (vesselSubCaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VesselSubCaseDTO result = vesselSubCaseService.save(vesselSubCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vesselSubCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vessel-sub-cases : get all the vesselSubCases.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of vesselSubCases in body
     */
    @GetMapping("/vessel-sub-cases")
    @Timed
    public ResponseEntity<List<VesselSubCaseDTO>> getAllVesselSubCases(VesselSubCaseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VesselSubCases by criteria: {}", criteria);
        Page<VesselSubCaseDTO> page = vesselSubCaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/vessel-sub-cases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /vessel-sub-cases/:id : get the "id" vesselSubCase.
     *
     * @param id the id of the vesselSubCaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vesselSubCaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vessel-sub-cases/{id}")
    @Timed
    public ResponseEntity<VesselSubCaseDTO> getVesselSubCase(@PathVariable Long id) {
        log.debug("REST request to get VesselSubCase : {}", id);
        Optional<VesselSubCaseDTO> vesselSubCaseDTO = vesselSubCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vesselSubCaseDTO);
    }

    /**
     * DELETE  /vessel-sub-cases/:id : delete the "id" vesselSubCase.
     *
     * @param id the id of the vesselSubCaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vessel-sub-cases/{id}")
    @Timed
    public ResponseEntity<Void> deleteVesselSubCase(@PathVariable Long id) {
        log.debug("REST request to delete VesselSubCase : {}", id);
        vesselSubCaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
