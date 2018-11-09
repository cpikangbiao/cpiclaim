package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.CaseClaimBillQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CaseClaimBill.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillResource.class);

    private static final String ENTITY_NAME = "caseClaimBill";

    private final CaseClaimBillService caseClaimBillService;

    private final CaseClaimBillQueryService caseClaimBillQueryService;

    public CaseClaimBillResource(CaseClaimBillService caseClaimBillService, CaseClaimBillQueryService caseClaimBillQueryService) {
        this.caseClaimBillService = caseClaimBillService;
        this.caseClaimBillQueryService = caseClaimBillQueryService;
    }

    /**
     * POST  /case-claim-bills : Create a new caseClaimBill.
     *
     * @param caseClaimBillDTO the caseClaimBillDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseClaimBillDTO, or with status 400 (Bad Request) if the caseClaimBill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-claim-bills")
    @Timed
    public ResponseEntity<CaseClaimBillDTO> createCaseClaimBill(@RequestBody CaseClaimBillDTO caseClaimBillDTO) throws URISyntaxException {
        log.debug("REST request to save CaseClaimBill : {}", caseClaimBillDTO);
        if (caseClaimBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseClaimBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseClaimBillDTO result = caseClaimBillService.save(caseClaimBillDTO);
        return ResponseEntity.created(new URI("/api/case-claim-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-claim-bills : Updates an existing caseClaimBill.
     *
     * @param caseClaimBillDTO the caseClaimBillDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseClaimBillDTO,
     * or with status 400 (Bad Request) if the caseClaimBillDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseClaimBillDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-claim-bills")
    @Timed
    public ResponseEntity<CaseClaimBillDTO> updateCaseClaimBill(@RequestBody CaseClaimBillDTO caseClaimBillDTO) throws URISyntaxException {
        log.debug("REST request to update CaseClaimBill : {}", caseClaimBillDTO);
        if (caseClaimBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseClaimBillDTO result = caseClaimBillService.save(caseClaimBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseClaimBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-claim-bills : get all the caseClaimBills.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseClaimBills in body
     */
    @GetMapping("/case-claim-bills")
    @Timed
    public ResponseEntity<List<CaseClaimBillDTO>> getAllCaseClaimBills(CaseClaimBillCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseClaimBills by criteria: {}", criteria);
        Page<CaseClaimBillDTO> page = caseClaimBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claim-bills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-claim-bills/:id : get the "id" caseClaimBill.
     *
     * @param id the id of the caseClaimBillDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseClaimBillDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-claim-bills/{id}")
    @Timed
    public ResponseEntity<CaseClaimBillDTO> getCaseClaimBill(@PathVariable Long id) {
        log.debug("REST request to get CaseClaimBill : {}", id);
        Optional<CaseClaimBillDTO> caseClaimBillDTO = caseClaimBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseClaimBillDTO);
    }

    /**
     * DELETE  /case-claim-bills/:id : delete the "id" caseClaimBill.
     *
     * @param id the id of the caseClaimBillDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-claim-bills/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseClaimBill(@PathVariable Long id) {
        log.debug("REST request to delete CaseClaimBill : {}", id);
        caseClaimBillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}