package com.cpi.claim.web.rest;

import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.VesselCaseQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.VesselCase}.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseResource.class);

    private static final String ENTITY_NAME = "cpiclaimVesselCase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VesselCaseService vesselCaseService;

    private final VesselCaseQueryService vesselCaseQueryService;

    public VesselCaseResource(VesselCaseService vesselCaseService, VesselCaseQueryService vesselCaseQueryService) {
        this.vesselCaseService = vesselCaseService;
        this.vesselCaseQueryService = vesselCaseQueryService;
    }

    /**
     * {@code POST  /vessel-cases} : Create a new vesselCase.
     *
     * @param vesselCaseDTO the vesselCaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vesselCaseDTO, or with status {@code 400 (Bad Request)} if the vesselCase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vessel-cases")
    public ResponseEntity<VesselCaseDTO> createVesselCase(@Valid @RequestBody VesselCaseDTO vesselCaseDTO) throws URISyntaxException {
        log.debug("REST request to save VesselCase : {}", vesselCaseDTO);
        if (vesselCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new vesselCase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VesselCaseDTO result = vesselCaseService.save(vesselCaseDTO);
        return ResponseEntity.created(new URI("/api/vessel-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vessel-cases} : Updates an existing vesselCase.
     *
     * @param vesselCaseDTO the vesselCaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vesselCaseDTO,
     * or with status {@code 400 (Bad Request)} if the vesselCaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vesselCaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vessel-cases")
    public ResponseEntity<VesselCaseDTO> updateVesselCase(@Valid @RequestBody VesselCaseDTO vesselCaseDTO) throws URISyntaxException {
        log.debug("REST request to update VesselCase : {}", vesselCaseDTO);
        if (vesselCaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VesselCaseDTO result = vesselCaseService.save(vesselCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vesselCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vessel-cases} : get all the vesselCases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vesselCases in body.
     */
    @GetMapping("/vessel-cases")
    public ResponseEntity<List<VesselCaseDTO>> getAllVesselCases(VesselCaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get VesselCases by criteria: {}", criteria);
        Page<VesselCaseDTO> page = vesselCaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /vessel-cases/count} : count all the vesselCases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/vessel-cases/count")
    public ResponseEntity<Long> countVesselCases(VesselCaseCriteria criteria) {
        log.debug("REST request to count VesselCases by criteria: {}", criteria);
        return ResponseEntity.ok().body(vesselCaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vessel-cases/:id} : get the "id" vesselCase.
     *
     * @param id the id of the vesselCaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vesselCaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vessel-cases/{id}")
    public ResponseEntity<VesselCaseDTO> getVesselCase(@PathVariable Long id) {
        log.debug("REST request to get VesselCase : {}", id);
        Optional<VesselCaseDTO> vesselCaseDTO = vesselCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vesselCaseDTO);
    }

    /**
     * {@code DELETE  /vessel-cases/:id} : delete the "id" vesselCase.
     *
     * @param id the id of the vesselCaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vessel-cases/{id}")
    public ResponseEntity<Void> deleteVesselCase(@PathVariable Long id) {
        log.debug("REST request to delete VesselCase : {}", id);
        vesselCaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
