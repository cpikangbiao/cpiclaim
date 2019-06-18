package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseRecoveryBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseRecoveryBillDTO;
import com.cpi.claim.service.dto.CaseRecoveryBillCriteria;
import com.cpi.claim.service.CaseRecoveryBillQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseRecoveryBill}.
 */
@RestController
@RequestMapping("/api")
public class CaseRecoveryBillResource {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryBillResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseRecoveryBill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseRecoveryBillService caseRecoveryBillService;

    private final CaseRecoveryBillQueryService caseRecoveryBillQueryService;

    public CaseRecoveryBillResource(CaseRecoveryBillService caseRecoveryBillService, CaseRecoveryBillQueryService caseRecoveryBillQueryService) {
        this.caseRecoveryBillService = caseRecoveryBillService;
        this.caseRecoveryBillQueryService = caseRecoveryBillQueryService;
    }

    /**
     * {@code POST  /case-recovery-bills} : Create a new caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the caseRecoveryBillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseRecoveryBillDTO, or with status {@code 400 (Bad Request)} if the caseRecoveryBill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-recovery-bills")
    public ResponseEntity<CaseRecoveryBillDTO> createCaseRecoveryBill(@RequestBody CaseRecoveryBillDTO caseRecoveryBillDTO) throws URISyntaxException {
        log.debug("REST request to save CaseRecoveryBill : {}", caseRecoveryBillDTO);
        if (caseRecoveryBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseRecoveryBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseRecoveryBillDTO result = caseRecoveryBillService.save(caseRecoveryBillDTO);
        return ResponseEntity.created(new URI("/api/case-recovery-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-recovery-bills} : Updates an existing caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the caseRecoveryBillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseRecoveryBillDTO,
     * or with status {@code 400 (Bad Request)} if the caseRecoveryBillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseRecoveryBillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-recovery-bills")
    public ResponseEntity<CaseRecoveryBillDTO> updateCaseRecoveryBill(@RequestBody CaseRecoveryBillDTO caseRecoveryBillDTO) throws URISyntaxException {
        log.debug("REST request to update CaseRecoveryBill : {}", caseRecoveryBillDTO);
        if (caseRecoveryBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseRecoveryBillDTO result = caseRecoveryBillService.save(caseRecoveryBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseRecoveryBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-recovery-bills} : get all the caseRecoveryBills.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseRecoveryBills in body.
     */
    @GetMapping("/case-recovery-bills")
    public ResponseEntity<List<CaseRecoveryBillDTO>> getAllCaseRecoveryBills(CaseRecoveryBillCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseRecoveryBills by criteria: {}", criteria);
        Page<CaseRecoveryBillDTO> page = caseRecoveryBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-recovery-bills/count} : count all the caseRecoveryBills.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-recovery-bills/count")
    public ResponseEntity<Long> countCaseRecoveryBills(CaseRecoveryBillCriteria criteria) {
        log.debug("REST request to count CaseRecoveryBills by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseRecoveryBillQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-recovery-bills/:id} : get the "id" caseRecoveryBill.
     *
     * @param id the id of the caseRecoveryBillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseRecoveryBillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-recovery-bills/{id}")
    public ResponseEntity<CaseRecoveryBillDTO> getCaseRecoveryBill(@PathVariable Long id) {
        log.debug("REST request to get CaseRecoveryBill : {}", id);
        Optional<CaseRecoveryBillDTO> caseRecoveryBillDTO = caseRecoveryBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseRecoveryBillDTO);
    }

    /**
     * {@code DELETE  /case-recovery-bills/:id} : delete the "id" caseRecoveryBill.
     *
     * @param id the id of the caseRecoveryBillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-recovery-bills/{id}")
    public ResponseEntity<Void> deleteCaseRecoveryBill(@PathVariable Long id) {
        log.debug("REST request to delete CaseRecoveryBill : {}", id);
        caseRecoveryBillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
