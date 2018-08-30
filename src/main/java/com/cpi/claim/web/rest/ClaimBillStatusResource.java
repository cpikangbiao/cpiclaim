package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.ClaimBillStatusService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;
import com.cpi.claim.service.dto.ClaimBillStatusCriteria;
import com.cpi.claim.service.ClaimBillStatusQueryService;
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
 * REST controller for managing ClaimBillStatus.
 */
@RestController
@RequestMapping("/api")
public class ClaimBillStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimBillStatusResource.class);

    private static final String ENTITY_NAME = "claimBillStatus";

    private final ClaimBillStatusService claimBillStatusService;

    private final ClaimBillStatusQueryService claimBillStatusQueryService;

    public ClaimBillStatusResource(ClaimBillStatusService claimBillStatusService, ClaimBillStatusQueryService claimBillStatusQueryService) {
        this.claimBillStatusService = claimBillStatusService;
        this.claimBillStatusQueryService = claimBillStatusQueryService;
    }

    /**
     * POST  /claim-bill-statuses : Create a new claimBillStatus.
     *
     * @param claimBillStatusDTO the claimBillStatusDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new claimBillStatusDTO, or with status 400 (Bad Request) if the claimBillStatus has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/claim-bill-statuses")
    @Timed
    public ResponseEntity<ClaimBillStatusDTO> createClaimBillStatus(@Valid @RequestBody ClaimBillStatusDTO claimBillStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimBillStatus : {}", claimBillStatusDTO);
        if (claimBillStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimBillStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimBillStatusDTO result = claimBillStatusService.save(claimBillStatusDTO);
        return ResponseEntity.created(new URI("/api/claim-bill-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /claim-bill-statuses : Updates an existing claimBillStatus.
     *
     * @param claimBillStatusDTO the claimBillStatusDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated claimBillStatusDTO,
     * or with status 400 (Bad Request) if the claimBillStatusDTO is not valid,
     * or with status 500 (Internal Server Error) if the claimBillStatusDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/claim-bill-statuses")
    @Timed
    public ResponseEntity<ClaimBillStatusDTO> updateClaimBillStatus(@Valid @RequestBody ClaimBillStatusDTO claimBillStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimBillStatus : {}", claimBillStatusDTO);
        if (claimBillStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimBillStatusDTO result = claimBillStatusService.save(claimBillStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, claimBillStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /claim-bill-statuses : get all the claimBillStatuses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of claimBillStatuses in body
     */
    @GetMapping("/claim-bill-statuses")
    @Timed
    public ResponseEntity<List<ClaimBillStatusDTO>> getAllClaimBillStatuses(ClaimBillStatusCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ClaimBillStatuses by criteria: {}", criteria);
        Page<ClaimBillStatusDTO> page = claimBillStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/claim-bill-statuses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /claim-bill-statuses/:id : get the "id" claimBillStatus.
     *
     * @param id the id of the claimBillStatusDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the claimBillStatusDTO, or with status 404 (Not Found)
     */
    @GetMapping("/claim-bill-statuses/{id}")
    @Timed
    public ResponseEntity<ClaimBillStatusDTO> getClaimBillStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimBillStatus : {}", id);
        Optional<ClaimBillStatusDTO> claimBillStatusDTO = claimBillStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimBillStatusDTO);
    }

    /**
     * DELETE  /claim-bill-statuses/:id : delete the "id" claimBillStatus.
     *
     * @param id the id of the claimBillStatusDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/claim-bill-statuses/{id}")
    @Timed
    public ResponseEntity<Void> deleteClaimBillStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimBillStatus : {}", id);
        claimBillStatusService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
