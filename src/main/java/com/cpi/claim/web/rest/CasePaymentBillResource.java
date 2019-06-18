package com.cpi.claim.web.rest;

import com.cpi.claim.service.CasePaymentBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CasePaymentBillDTO;
import com.cpi.claim.service.dto.CasePaymentBillCriteria;
import com.cpi.claim.service.CasePaymentBillQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CasePaymentBill}.
 */
@RestController
@RequestMapping("/api")
public class CasePaymentBillResource {

    private final Logger log = LoggerFactory.getLogger(CasePaymentBillResource.class);

    private static final String ENTITY_NAME = "cpiclaimCasePaymentBill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CasePaymentBillService casePaymentBillService;

    private final CasePaymentBillQueryService casePaymentBillQueryService;

    public CasePaymentBillResource(CasePaymentBillService casePaymentBillService, CasePaymentBillQueryService casePaymentBillQueryService) {
        this.casePaymentBillService = casePaymentBillService;
        this.casePaymentBillQueryService = casePaymentBillQueryService;
    }

    /**
     * {@code POST  /case-payment-bills} : Create a new casePaymentBill.
     *
     * @param casePaymentBillDTO the casePaymentBillDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new casePaymentBillDTO, or with status {@code 400 (Bad Request)} if the casePaymentBill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-payment-bills")
    public ResponseEntity<CasePaymentBillDTO> createCasePaymentBill(@RequestBody CasePaymentBillDTO casePaymentBillDTO) throws URISyntaxException {
        log.debug("REST request to save CasePaymentBill : {}", casePaymentBillDTO);
        if (casePaymentBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new casePaymentBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CasePaymentBillDTO result = casePaymentBillService.save(casePaymentBillDTO);
        return ResponseEntity.created(new URI("/api/case-payment-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-payment-bills} : Updates an existing casePaymentBill.
     *
     * @param casePaymentBillDTO the casePaymentBillDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casePaymentBillDTO,
     * or with status {@code 400 (Bad Request)} if the casePaymentBillDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the casePaymentBillDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-payment-bills")
    public ResponseEntity<CasePaymentBillDTO> updateCasePaymentBill(@RequestBody CasePaymentBillDTO casePaymentBillDTO) throws URISyntaxException {
        log.debug("REST request to update CasePaymentBill : {}", casePaymentBillDTO);
        if (casePaymentBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CasePaymentBillDTO result = casePaymentBillService.save(casePaymentBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, casePaymentBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-payment-bills} : get all the casePaymentBills.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of casePaymentBills in body.
     */
    @GetMapping("/case-payment-bills")
    public ResponseEntity<List<CasePaymentBillDTO>> getAllCasePaymentBills(CasePaymentBillCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CasePaymentBills by criteria: {}", criteria);
        Page<CasePaymentBillDTO> page = casePaymentBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-payment-bills/count} : count all the casePaymentBills.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-payment-bills/count")
    public ResponseEntity<Long> countCasePaymentBills(CasePaymentBillCriteria criteria) {
        log.debug("REST request to count CasePaymentBills by criteria: {}", criteria);
        return ResponseEntity.ok().body(casePaymentBillQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-payment-bills/:id} : get the "id" casePaymentBill.
     *
     * @param id the id of the casePaymentBillDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the casePaymentBillDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-payment-bills/{id}")
    public ResponseEntity<CasePaymentBillDTO> getCasePaymentBill(@PathVariable Long id) {
        log.debug("REST request to get CasePaymentBill : {}", id);
        Optional<CasePaymentBillDTO> casePaymentBillDTO = casePaymentBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casePaymentBillDTO);
    }

    /**
     * {@code DELETE  /case-payment-bills/:id} : delete the "id" casePaymentBill.
     *
     * @param id the id of the casePaymentBillDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-payment-bills/{id}")
    public ResponseEntity<Void> deleteCasePaymentBill(@PathVariable Long id) {
        log.debug("REST request to delete CasePaymentBill : {}", id);
        casePaymentBillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
