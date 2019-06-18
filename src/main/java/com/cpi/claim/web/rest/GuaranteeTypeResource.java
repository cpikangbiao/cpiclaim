package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.GuaranteeTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.GuaranteeTypeDTO;
import com.cpi.claim.service.dto.GuaranteeTypeCriteria;
import com.cpi.claim.service.GuaranteeTypeQueryService;
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
 * REST controller for managing GuaranteeType.
 */
@RestController
@RequestMapping("/api")
public class GuaranteeTypeResource {

    private final Logger log = LoggerFactory.getLogger(GuaranteeTypeResource.class);

    private static final String ENTITY_NAME = "guaranteeType";

    private final GuaranteeTypeService guaranteeTypeService;

    private final GuaranteeTypeQueryService guaranteeTypeQueryService;

    public GuaranteeTypeResource(GuaranteeTypeService guaranteeTypeService, GuaranteeTypeQueryService guaranteeTypeQueryService) {
        this.guaranteeTypeService = guaranteeTypeService;
        this.guaranteeTypeQueryService = guaranteeTypeQueryService;
    }

    /**
     * POST  /guarantee-types : Create a new guaranteeType.
     *
     * @param guaranteeTypeDTO the guaranteeTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new guaranteeTypeDTO, or with status 400 (Bad Request) if the guaranteeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/guarantee-types")
    @Timed
    public ResponseEntity<GuaranteeTypeDTO> createGuaranteeType(@Valid @RequestBody GuaranteeTypeDTO guaranteeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save GuaranteeType : {}", guaranteeTypeDTO);
        if (guaranteeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new guaranteeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GuaranteeTypeDTO result = guaranteeTypeService.save(guaranteeTypeDTO);
        return ResponseEntity.created(new URI("/api/guarantee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /guarantee-types : Updates an existing guaranteeType.
     *
     * @param guaranteeTypeDTO the guaranteeTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated guaranteeTypeDTO,
     * or with status 400 (Bad Request) if the guaranteeTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the guaranteeTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/guarantee-types")
    @Timed
    public ResponseEntity<GuaranteeTypeDTO> updateGuaranteeType(@Valid @RequestBody GuaranteeTypeDTO guaranteeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update GuaranteeType : {}", guaranteeTypeDTO);
        if (guaranteeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GuaranteeTypeDTO result = guaranteeTypeService.save(guaranteeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, guaranteeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /guarantee-types : get all the guaranteeTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of guaranteeTypes in body
     */
    @GetMapping("/guarantee-types")
    @Timed
    public ResponseEntity<List<GuaranteeTypeDTO>> getAllGuaranteeTypes(GuaranteeTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get GuaranteeTypes by criteria: {}", criteria);
        Page<GuaranteeTypeDTO> page = guaranteeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/guarantee-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /guarantee-types/:id : get the "id" guaranteeType.
     *
     * @param id the id of the guaranteeTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the guaranteeTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/guarantee-types/{id}")
    @Timed
    public ResponseEntity<GuaranteeTypeDTO> getGuaranteeType(@PathVariable Long id) {
        log.debug("REST request to get GuaranteeType : {}", id);
        Optional<GuaranteeTypeDTO> guaranteeTypeDTO = guaranteeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(guaranteeTypeDTO);
    }

    /**
     * DELETE  /guarantee-types/:id : delete the "id" guaranteeType.
     *
     * @param id the id of the guaranteeTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/guarantee-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteGuaranteeType(@PathVariable Long id) {
        log.debug("REST request to delete GuaranteeType : {}", id);
        guaranteeTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
