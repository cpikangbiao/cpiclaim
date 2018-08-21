package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseSatusTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseSatusTypeDTO;
import com.cpi.claim.service.dto.CaseSatusTypeCriteria;
import com.cpi.claim.service.CaseSatusTypeQueryService;
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
 * REST controller for managing CaseSatusType.
 */
@RestController
@RequestMapping("/api")
public class CaseSatusTypeResource {

    private final Logger log = LoggerFactory.getLogger(CaseSatusTypeResource.class);

    private static final String ENTITY_NAME = "caseSatusType";

    private final CaseSatusTypeService caseSatusTypeService;

    private final CaseSatusTypeQueryService caseSatusTypeQueryService;

    public CaseSatusTypeResource(CaseSatusTypeService caseSatusTypeService, CaseSatusTypeQueryService caseSatusTypeQueryService) {
        this.caseSatusTypeService = caseSatusTypeService;
        this.caseSatusTypeQueryService = caseSatusTypeQueryService;
    }

    /**
     * POST  /case-satus-types : Create a new caseSatusType.
     *
     * @param caseSatusTypeDTO the caseSatusTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseSatusTypeDTO, or with status 400 (Bad Request) if the caseSatusType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-satus-types")
    @Timed
    public ResponseEntity<CaseSatusTypeDTO> createCaseSatusType(@Valid @RequestBody CaseSatusTypeDTO caseSatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseSatusType : {}", caseSatusTypeDTO);
        if (caseSatusTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseSatusType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseSatusTypeDTO result = caseSatusTypeService.save(caseSatusTypeDTO);
        return ResponseEntity.created(new URI("/api/case-satus-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-satus-types : Updates an existing caseSatusType.
     *
     * @param caseSatusTypeDTO the caseSatusTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseSatusTypeDTO,
     * or with status 400 (Bad Request) if the caseSatusTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseSatusTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-satus-types")
    @Timed
    public ResponseEntity<CaseSatusTypeDTO> updateCaseSatusType(@Valid @RequestBody CaseSatusTypeDTO caseSatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseSatusType : {}", caseSatusTypeDTO);
        if (caseSatusTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseSatusTypeDTO result = caseSatusTypeService.save(caseSatusTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseSatusTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-satus-types : get all the caseSatusTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseSatusTypes in body
     */
    @GetMapping("/case-satus-types")
    @Timed
    public ResponseEntity<List<CaseSatusTypeDTO>> getAllCaseSatusTypes(CaseSatusTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseSatusTypes by criteria: {}", criteria);
        Page<CaseSatusTypeDTO> page = caseSatusTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-satus-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-satus-types/:id : get the "id" caseSatusType.
     *
     * @param id the id of the caseSatusTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseSatusTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-satus-types/{id}")
    @Timed
    public ResponseEntity<CaseSatusTypeDTO> getCaseSatusType(@PathVariable Long id) {
        log.debug("REST request to get CaseSatusType : {}", id);
        Optional<CaseSatusTypeDTO> caseSatusTypeDTO = caseSatusTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseSatusTypeDTO);
    }

    /**
     * DELETE  /case-satus-types/:id : delete the "id" caseSatusType.
     *
     * @param id the id of the caseSatusTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-satus-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseSatusType(@PathVariable Long id) {
        log.debug("REST request to delete CaseSatusType : {}", id);
        caseSatusTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
