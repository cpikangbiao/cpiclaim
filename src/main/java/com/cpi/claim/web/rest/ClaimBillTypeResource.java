package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.ClaimBillTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.ClaimBillTypeDTO;
import com.cpi.claim.service.dto.ClaimBillTypeCriteria;
import com.cpi.claim.service.ClaimBillTypeQueryService;
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
 * REST controller for managing ClaimBillType.
 */
@RestController
@RequestMapping("/api")
public class ClaimBillTypeResource {

    private final Logger log = LoggerFactory.getLogger(ClaimBillTypeResource.class);

    private static final String ENTITY_NAME = "claimBillType";

    private final ClaimBillTypeService claimBillTypeService;

    private final ClaimBillTypeQueryService claimBillTypeQueryService;

    public ClaimBillTypeResource(ClaimBillTypeService claimBillTypeService, ClaimBillTypeQueryService claimBillTypeQueryService) {
        this.claimBillTypeService = claimBillTypeService;
        this.claimBillTypeQueryService = claimBillTypeQueryService;
    }

    /**
     * POST  /claim-bill-types : Create a new claimBillType.
     *
     * @param claimBillTypeDTO the claimBillTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new claimBillTypeDTO, or with status 400 (Bad Request) if the claimBillType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/claim-bill-types")
    @Timed
    public ResponseEntity<ClaimBillTypeDTO> createClaimBillType(@Valid @RequestBody ClaimBillTypeDTO claimBillTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimBillType : {}", claimBillTypeDTO);
        if (claimBillTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimBillType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimBillTypeDTO result = claimBillTypeService.save(claimBillTypeDTO);
        return ResponseEntity.created(new URI("/api/claim-bill-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /claim-bill-types : Updates an existing claimBillType.
     *
     * @param claimBillTypeDTO the claimBillTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated claimBillTypeDTO,
     * or with status 400 (Bad Request) if the claimBillTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the claimBillTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/claim-bill-types")
    @Timed
    public ResponseEntity<ClaimBillTypeDTO> updateClaimBillType(@Valid @RequestBody ClaimBillTypeDTO claimBillTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimBillType : {}", claimBillTypeDTO);
        if (claimBillTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimBillTypeDTO result = claimBillTypeService.save(claimBillTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, claimBillTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /claim-bill-types : get all the claimBillTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of claimBillTypes in body
     */
    @GetMapping("/claim-bill-types")
    @Timed
    public ResponseEntity<List<ClaimBillTypeDTO>> getAllClaimBillTypes(ClaimBillTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ClaimBillTypes by criteria: {}", criteria);
        Page<ClaimBillTypeDTO> page = claimBillTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/claim-bill-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /claim-bill-types/:id : get the "id" claimBillType.
     *
     * @param id the id of the claimBillTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the claimBillTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/claim-bill-types/{id}")
    @Timed
    public ResponseEntity<ClaimBillTypeDTO> getClaimBillType(@PathVariable Long id) {
        log.debug("REST request to get ClaimBillType : {}", id);
        Optional<ClaimBillTypeDTO> claimBillTypeDTO = claimBillTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimBillTypeDTO);
    }

    /**
     * DELETE  /claim-bill-types/:id : delete the "id" claimBillType.
     *
     * @param id the id of the claimBillTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/claim-bill-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteClaimBillType(@PathVariable Long id) {
        log.debug("REST request to delete ClaimBillType : {}", id);
        claimBillTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
