package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.CaseFeeQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.CaseFee}.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseFee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseFeeService caseFeeService;

    private final CaseFeeQueryService caseFeeQueryService;

    public CaseFeeResource(CaseFeeService caseFeeService, CaseFeeQueryService caseFeeQueryService) {
        this.caseFeeService = caseFeeService;
        this.caseFeeQueryService = caseFeeQueryService;
    }

    /**
     * {@code POST  /case-fees} : Create a new caseFee.
     *
     * @param caseFeeDTO the caseFeeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseFeeDTO, or with status {@code 400 (Bad Request)} if the caseFee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-fees")
    public ResponseEntity<CaseFeeDTO> createCaseFee(@RequestBody CaseFeeDTO caseFeeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseFee : {}", caseFeeDTO);
        if (caseFeeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseFeeDTO result = caseFeeService.save(caseFeeDTO);
        return ResponseEntity.created(new URI("/api/case-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-fees} : Updates an existing caseFee.
     *
     * @param caseFeeDTO the caseFeeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseFeeDTO,
     * or with status {@code 400 (Bad Request)} if the caseFeeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseFeeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-fees")
    public ResponseEntity<CaseFeeDTO> updateCaseFee(@RequestBody CaseFeeDTO caseFeeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseFee : {}", caseFeeDTO);
        if (caseFeeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseFeeDTO result = caseFeeService.save(caseFeeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseFeeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-fees} : get all the caseFees.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseFees in body.
     */
    @GetMapping("/case-fees")
    public ResponseEntity<List<CaseFeeDTO>> getAllCaseFees(CaseFeeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseFees by criteria: {}", criteria);
        Page<CaseFeeDTO> page = caseFeeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-fees/count} : count all the caseFees.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-fees/count")
    public ResponseEntity<Long> countCaseFees(CaseFeeCriteria criteria) {
        log.debug("REST request to count CaseFees by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseFeeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-fees/:id} : get the "id" caseFee.
     *
     * @param id the id of the caseFeeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseFeeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-fees/{id}")
    public ResponseEntity<CaseFeeDTO> getCaseFee(@PathVariable Long id) {
        log.debug("REST request to get CaseFee : {}", id);
        Optional<CaseFeeDTO> caseFeeDTO = caseFeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseFeeDTO);
    }

    /**
     * {@code DELETE  /case-fees/:id} : delete the "id" caseFee.
     *
     * @param id the id of the caseFeeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-fees/{id}")
    public ResponseEntity<Void> deleteCaseFee(@PathVariable Long id) {
        log.debug("REST request to delete CaseFee : {}", id);
        caseFeeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
