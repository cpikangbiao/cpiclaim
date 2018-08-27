package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.RecoveryTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.RecoveryTypeDTO;
import com.cpi.claim.service.dto.RecoveryTypeCriteria;
import com.cpi.claim.service.RecoveryTypeQueryService;
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
 * REST controller for managing RecoveryType.
 */
@RestController
@RequestMapping("/api")
public class RecoveryTypeResource {

    private final Logger log = LoggerFactory.getLogger(RecoveryTypeResource.class);

    private static final String ENTITY_NAME = "recoveryType";

    private final RecoveryTypeService recoveryTypeService;

    private final RecoveryTypeQueryService recoveryTypeQueryService;

    public RecoveryTypeResource(RecoveryTypeService recoveryTypeService, RecoveryTypeQueryService recoveryTypeQueryService) {
        this.recoveryTypeService = recoveryTypeService;
        this.recoveryTypeQueryService = recoveryTypeQueryService;
    }

    /**
     * POST  /recovery-types : Create a new recoveryType.
     *
     * @param recoveryTypeDTO the recoveryTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new recoveryTypeDTO, or with status 400 (Bad Request) if the recoveryType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/recovery-types")
    @Timed
    public ResponseEntity<RecoveryTypeDTO> createRecoveryType(@Valid @RequestBody RecoveryTypeDTO recoveryTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RecoveryType : {}", recoveryTypeDTO);
        if (recoveryTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new recoveryType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecoveryTypeDTO result = recoveryTypeService.save(recoveryTypeDTO);
        return ResponseEntity.created(new URI("/api/recovery-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /recovery-types : Updates an existing recoveryType.
     *
     * @param recoveryTypeDTO the recoveryTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated recoveryTypeDTO,
     * or with status 400 (Bad Request) if the recoveryTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the recoveryTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/recovery-types")
    @Timed
    public ResponseEntity<RecoveryTypeDTO> updateRecoveryType(@Valid @RequestBody RecoveryTypeDTO recoveryTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RecoveryType : {}", recoveryTypeDTO);
        if (recoveryTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecoveryTypeDTO result = recoveryTypeService.save(recoveryTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, recoveryTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /recovery-types : get all the recoveryTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of recoveryTypes in body
     */
    @GetMapping("/recovery-types")
    @Timed
    public ResponseEntity<List<RecoveryTypeDTO>> getAllRecoveryTypes(RecoveryTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RecoveryTypes by criteria: {}", criteria);
        Page<RecoveryTypeDTO> page = recoveryTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/recovery-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /recovery-types/:id : get the "id" recoveryType.
     *
     * @param id the id of the recoveryTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the recoveryTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/recovery-types/{id}")
    @Timed
    public ResponseEntity<RecoveryTypeDTO> getRecoveryType(@PathVariable Long id) {
        log.debug("REST request to get RecoveryType : {}", id);
        Optional<RecoveryTypeDTO> recoveryTypeDTO = recoveryTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recoveryTypeDTO);
    }

    /**
     * DELETE  /recovery-types/:id : delete the "id" recoveryType.
     *
     * @param id the id of the recoveryTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/recovery-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteRecoveryType(@PathVariable Long id) {
        log.debug("REST request to delete RecoveryType : {}", id);
        recoveryTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
