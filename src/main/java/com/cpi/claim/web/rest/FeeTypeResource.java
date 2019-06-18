package com.cpi.claim.web.rest;

import com.cpi.claim.service.FeeTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.FeeTypeDTO;
import com.cpi.claim.service.dto.FeeTypeCriteria;
import com.cpi.claim.service.FeeTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.FeeType}.
 */
@RestController
@RequestMapping("/api")
public class FeeTypeResource {

    private final Logger log = LoggerFactory.getLogger(FeeTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimFeeType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FeeTypeService feeTypeService;

    private final FeeTypeQueryService feeTypeQueryService;

    public FeeTypeResource(FeeTypeService feeTypeService, FeeTypeQueryService feeTypeQueryService) {
        this.feeTypeService = feeTypeService;
        this.feeTypeQueryService = feeTypeQueryService;
    }

    /**
     * {@code POST  /fee-types} : Create a new feeType.
     *
     * @param feeTypeDTO the feeTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new feeTypeDTO, or with status {@code 400 (Bad Request)} if the feeType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fee-types")
    public ResponseEntity<FeeTypeDTO> createFeeType(@Valid @RequestBody FeeTypeDTO feeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save FeeType : {}", feeTypeDTO);
        if (feeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new feeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeTypeDTO result = feeTypeService.save(feeTypeDTO);
        return ResponseEntity.created(new URI("/api/fee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fee-types} : Updates an existing feeType.
     *
     * @param feeTypeDTO the feeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated feeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the feeTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the feeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fee-types")
    public ResponseEntity<FeeTypeDTO> updateFeeType(@Valid @RequestBody FeeTypeDTO feeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update FeeType : {}", feeTypeDTO);
        if (feeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeTypeDTO result = feeTypeService.save(feeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, feeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fee-types} : get all the feeTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of feeTypes in body.
     */
    @GetMapping("/fee-types")
    public ResponseEntity<List<FeeTypeDTO>> getAllFeeTypes(FeeTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get FeeTypes by criteria: {}", criteria);
        Page<FeeTypeDTO> page = feeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /fee-types/count} : count all the feeTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/fee-types/count")
    public ResponseEntity<Long> countFeeTypes(FeeTypeCriteria criteria) {
        log.debug("REST request to count FeeTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(feeTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /fee-types/:id} : get the "id" feeType.
     *
     * @param id the id of the feeTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the feeTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fee-types/{id}")
    public ResponseEntity<FeeTypeDTO> getFeeType(@PathVariable Long id) {
        log.debug("REST request to get FeeType : {}", id);
        Optional<FeeTypeDTO> feeTypeDTO = feeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feeTypeDTO);
    }

    /**
     * {@code DELETE  /fee-types/:id} : delete the "id" feeType.
     *
     * @param id the id of the feeTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fee-types/{id}")
    public ResponseEntity<Void> deleteFeeType(@PathVariable Long id) {
        log.debug("REST request to delete FeeType : {}", id);
        feeTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
