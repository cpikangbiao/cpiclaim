package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.ClaimBillFinanceTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeCriteria;
import com.cpi.claim.service.ClaimBillFinanceTypeQueryService;
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
 * REST controller for managing ClaimBillFinanceType.
 */
@RestController
@RequestMapping("/api")
public class ClaimBillFinanceTypeResource {

    private final Logger log = LoggerFactory.getLogger(ClaimBillFinanceTypeResource.class);

    private static final String ENTITY_NAME = "claimBillFinanceType";

    private final ClaimBillFinanceTypeService claimBillFinanceTypeService;

    private final ClaimBillFinanceTypeQueryService claimBillFinanceTypeQueryService;

    public ClaimBillFinanceTypeResource(ClaimBillFinanceTypeService claimBillFinanceTypeService, ClaimBillFinanceTypeQueryService claimBillFinanceTypeQueryService) {
        this.claimBillFinanceTypeService = claimBillFinanceTypeService;
        this.claimBillFinanceTypeQueryService = claimBillFinanceTypeQueryService;
    }

    /**
     * POST  /claim-bill-finance-types : Create a new claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the claimBillFinanceTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new claimBillFinanceTypeDTO, or with status 400 (Bad Request) if the claimBillFinanceType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/claim-bill-finance-types")
    @Timed
    public ResponseEntity<ClaimBillFinanceTypeDTO> createClaimBillFinanceType(@Valid @RequestBody ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimBillFinanceType : {}", claimBillFinanceTypeDTO);
        if (claimBillFinanceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimBillFinanceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimBillFinanceTypeDTO result = claimBillFinanceTypeService.save(claimBillFinanceTypeDTO);
        return ResponseEntity.created(new URI("/api/claim-bill-finance-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /claim-bill-finance-types : Updates an existing claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the claimBillFinanceTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated claimBillFinanceTypeDTO,
     * or with status 400 (Bad Request) if the claimBillFinanceTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the claimBillFinanceTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/claim-bill-finance-types")
    @Timed
    public ResponseEntity<ClaimBillFinanceTypeDTO> updateClaimBillFinanceType(@Valid @RequestBody ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimBillFinanceType : {}", claimBillFinanceTypeDTO);
        if (claimBillFinanceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimBillFinanceTypeDTO result = claimBillFinanceTypeService.save(claimBillFinanceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, claimBillFinanceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /claim-bill-finance-types : get all the claimBillFinanceTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of claimBillFinanceTypes in body
     */
    @GetMapping("/claim-bill-finance-types")
    @Timed
    public ResponseEntity<List<ClaimBillFinanceTypeDTO>> getAllClaimBillFinanceTypes(ClaimBillFinanceTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ClaimBillFinanceTypes by criteria: {}", criteria);
        Page<ClaimBillFinanceTypeDTO> page = claimBillFinanceTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/claim-bill-finance-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /claim-bill-finance-types/:id : get the "id" claimBillFinanceType.
     *
     * @param id the id of the claimBillFinanceTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the claimBillFinanceTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/claim-bill-finance-types/{id}")
    @Timed
    public ResponseEntity<ClaimBillFinanceTypeDTO> getClaimBillFinanceType(@PathVariable Long id) {
        log.debug("REST request to get ClaimBillFinanceType : {}", id);
        Optional<ClaimBillFinanceTypeDTO> claimBillFinanceTypeDTO = claimBillFinanceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimBillFinanceTypeDTO);
    }

    /**
     * DELETE  /claim-bill-finance-types/:id : delete the "id" claimBillFinanceType.
     *
     * @param id the id of the claimBillFinanceTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/claim-bill-finance-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteClaimBillFinanceType(@PathVariable Long id) {
        log.debug("REST request to delete ClaimBillFinanceType : {}", id);
        claimBillFinanceTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
