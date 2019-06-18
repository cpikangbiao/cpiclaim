package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.RiskGroupService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.RiskGroupDTO;
import com.cpi.claim.service.dto.RiskGroupCriteria;
import com.cpi.claim.service.RiskGroupQueryService;
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
 * REST controller for managing RiskGroup.
 */
@RestController
@RequestMapping("/api")
public class RiskGroupResource {

    private final Logger log = LoggerFactory.getLogger(RiskGroupResource.class);

    private static final String ENTITY_NAME = "riskGroup";

    private final RiskGroupService riskGroupService;

    private final RiskGroupQueryService riskGroupQueryService;

    public RiskGroupResource(RiskGroupService riskGroupService, RiskGroupQueryService riskGroupQueryService) {
        this.riskGroupService = riskGroupService;
        this.riskGroupQueryService = riskGroupQueryService;
    }

    /**
     * POST  /risk-groups : Create a new riskGroup.
     *
     * @param riskGroupDTO the riskGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new riskGroupDTO, or with status 400 (Bad Request) if the riskGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/risk-groups")
    @Timed
    public ResponseEntity<RiskGroupDTO> createRiskGroup(@Valid @RequestBody RiskGroupDTO riskGroupDTO) throws URISyntaxException {
        log.debug("REST request to save RiskGroup : {}", riskGroupDTO);
        if (riskGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskGroupDTO result = riskGroupService.save(riskGroupDTO);
        return ResponseEntity.created(new URI("/api/risk-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /risk-groups : Updates an existing riskGroup.
     *
     * @param riskGroupDTO the riskGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated riskGroupDTO,
     * or with status 400 (Bad Request) if the riskGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the riskGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/risk-groups")
    @Timed
    public ResponseEntity<RiskGroupDTO> updateRiskGroup(@Valid @RequestBody RiskGroupDTO riskGroupDTO) throws URISyntaxException {
        log.debug("REST request to update RiskGroup : {}", riskGroupDTO);
        if (riskGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskGroupDTO result = riskGroupService.save(riskGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, riskGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /risk-groups : get all the riskGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of riskGroups in body
     */
    @GetMapping("/risk-groups")
    @Timed
    public ResponseEntity<List<RiskGroupDTO>> getAllRiskGroups(RiskGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RiskGroups by criteria: {}", criteria);
        Page<RiskGroupDTO> page = riskGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/risk-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /risk-groups/:id : get the "id" riskGroup.
     *
     * @param id the id of the riskGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the riskGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/risk-groups/{id}")
    @Timed
    public ResponseEntity<RiskGroupDTO> getRiskGroup(@PathVariable Long id) {
        log.debug("REST request to get RiskGroup : {}", id);
        Optional<RiskGroupDTO> riskGroupDTO = riskGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskGroupDTO);
    }

    /**
     * DELETE  /risk-groups/:id : delete the "id" riskGroup.
     *
     * @param id the id of the riskGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/risk-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteRiskGroup(@PathVariable Long id) {
        log.debug("REST request to delete RiskGroup : {}", id);
        riskGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
