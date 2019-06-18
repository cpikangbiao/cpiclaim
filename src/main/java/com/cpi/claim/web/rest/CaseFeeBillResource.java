package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseFeeBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseFeeBillDTO;
import com.cpi.claim.service.dto.CaseFeeBillCriteria;
import com.cpi.claim.service.CaseFeeBillQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseFeeBill}.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeBillResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeBillResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseFeeBill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseFeeBillService caseFeeBillService;

    private final CaseFeeBillQueryService caseFeeBillQueryService;

    public CaseFeeBillResource(CaseFeeBillService caseFeeBillService, CaseFeeBillQueryService caseFeeBillQueryService) {
        this.caseFeeBillService = caseFeeBillService;
        this.caseFeeBillQueryService = caseFeeBillQueryService;
    }

    /**
     * {@code POST  /case-fee-bills} : Create a new caseFeeBill.
     *
     * @param caseFeeBillDTO the caseFeeBillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseFeeBillDTO, or with status {@code 400 (Bad Request)} if the caseFeeBill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-fee-bills")
    public ResponseEntity<CaseFeeBillDTO> createCaseFeeBill(@RequestBody CaseFeeBillDTO caseFeeBillDTO) throws URISyntaxException {
        log.debug("REST request to save CaseFeeBill : {}", caseFeeBillDTO);
        if (caseFeeBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseFeeBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseFeeBillDTO result = caseFeeBillService.save(caseFeeBillDTO);
        return ResponseEntity.created(new URI("/api/case-fee-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-fee-bills} : Updates an existing caseFeeBill.
     *
     * @param caseFeeBillDTO the caseFeeBillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseFeeBillDTO,
     * or with status {@code 400 (Bad Request)} if the caseFeeBillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseFeeBillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-fee-bills")
    public ResponseEntity<CaseFeeBillDTO> updateCaseFeeBill(@RequestBody CaseFeeBillDTO caseFeeBillDTO) throws URISyntaxException {
        log.debug("REST request to update CaseFeeBill : {}", caseFeeBillDTO);
        if (caseFeeBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseFeeBillDTO result = caseFeeBillService.save(caseFeeBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseFeeBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-fee-bills} : get all the caseFeeBills.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseFeeBills in body.
     */
    @GetMapping("/case-fee-bills")
    public ResponseEntity<List<CaseFeeBillDTO>> getAllCaseFeeBills(CaseFeeBillCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseFeeBills by criteria: {}", criteria);
        Page<CaseFeeBillDTO> page = caseFeeBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-fee-bills/count} : count all the caseFeeBills.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-fee-bills/count")
    public ResponseEntity<Long> countCaseFeeBills(CaseFeeBillCriteria criteria) {
        log.debug("REST request to count CaseFeeBills by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseFeeBillQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-fee-bills/:id} : get the "id" caseFeeBill.
     *
     * @param id the id of the caseFeeBillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseFeeBillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-fee-bills/{id}")
    public ResponseEntity<CaseFeeBillDTO> getCaseFeeBill(@PathVariable Long id) {
        log.debug("REST request to get CaseFeeBill : {}", id);
        Optional<CaseFeeBillDTO> caseFeeBillDTO = caseFeeBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseFeeBillDTO);
    }

    /**
     * {@code DELETE  /case-fee-bills/:id} : delete the "id" caseFeeBill.
     *
     * @param id the id of the caseFeeBillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-fee-bills/{id}")
    public ResponseEntity<Void> deleteCaseFeeBill(@PathVariable Long id) {
        log.debug("REST request to delete CaseFeeBill : {}", id);
        caseFeeBillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
