package com.cpi.claim.web.rest;

import com.cpi.claim.service.ClaimBillStatusService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;
import com.cpi.claim.service.dto.ClaimBillStatusCriteria;
import com.cpi.claim.service.ClaimBillStatusQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.ClaimBillStatus}.
 */
@RestController
@RequestMapping("/api")
public class ClaimBillStatusResource {

    private final Logger log = LoggerFactory.getLogger(ClaimBillStatusResource.class);

    private static final String ENTITY_NAME = "cpiclaimClaimBillStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimBillStatusService claimBillStatusService;

    private final ClaimBillStatusQueryService claimBillStatusQueryService;

    public ClaimBillStatusResource(ClaimBillStatusService claimBillStatusService, ClaimBillStatusQueryService claimBillStatusQueryService) {
        this.claimBillStatusService = claimBillStatusService;
        this.claimBillStatusQueryService = claimBillStatusQueryService;
    }

    /**
     * {@code POST  /claim-bill-statuses} : Create a new claimBillStatus.
     *
     * @param claimBillStatusDTO the claimBillStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimBillStatusDTO, or with status {@code 400 (Bad Request)} if the claimBillStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-bill-statuses")
    public ResponseEntity<ClaimBillStatusDTO> createClaimBillStatus(@Valid @RequestBody ClaimBillStatusDTO claimBillStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimBillStatus : {}", claimBillStatusDTO);
        if (claimBillStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimBillStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimBillStatusDTO result = claimBillStatusService.save(claimBillStatusDTO);
        return ResponseEntity.created(new URI("/api/claim-bill-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-bill-statuses} : Updates an existing claimBillStatus.
     *
     * @param claimBillStatusDTO the claimBillStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimBillStatusDTO,
     * or with status {@code 400 (Bad Request)} if the claimBillStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimBillStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-bill-statuses")
    public ResponseEntity<ClaimBillStatusDTO> updateClaimBillStatus(@Valid @RequestBody ClaimBillStatusDTO claimBillStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimBillStatus : {}", claimBillStatusDTO);
        if (claimBillStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimBillStatusDTO result = claimBillStatusService.save(claimBillStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimBillStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-bill-statuses} : get all the claimBillStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimBillStatuses in body.
     */
    @GetMapping("/claim-bill-statuses")
    public ResponseEntity<List<ClaimBillStatusDTO>> getAllClaimBillStatuses(ClaimBillStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ClaimBillStatuses by criteria: {}", criteria);
        Page<ClaimBillStatusDTO> page = claimBillStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /claim-bill-statuses/count} : count all the claimBillStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/claim-bill-statuses/count")
    public ResponseEntity<Long> countClaimBillStatuses(ClaimBillStatusCriteria criteria) {
        log.debug("REST request to count ClaimBillStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(claimBillStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /claim-bill-statuses/:id} : get the "id" claimBillStatus.
     *
     * @param id the id of the claimBillStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimBillStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-bill-statuses/{id}")
    public ResponseEntity<ClaimBillStatusDTO> getClaimBillStatus(@PathVariable Long id) {
        log.debug("REST request to get ClaimBillStatus : {}", id);
        Optional<ClaimBillStatusDTO> claimBillStatusDTO = claimBillStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimBillStatusDTO);
    }

    /**
     * {@code DELETE  /claim-bill-statuses/:id} : delete the "id" claimBillStatus.
     *
     * @param id the id of the claimBillStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-bill-statuses/{id}")
    public ResponseEntity<Void> deleteClaimBillStatus(@PathVariable Long id) {
        log.debug("REST request to delete ClaimBillStatus : {}", id);
        claimBillStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
