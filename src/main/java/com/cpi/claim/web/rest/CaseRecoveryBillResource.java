package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseRecoveryBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseRecoveryBillDTO;
import com.cpi.claim.service.dto.CaseRecoveryBillCriteria;
import com.cpi.claim.service.CaseRecoveryBillQueryService;
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
 * REST controller for managing CaseRecoveryBill.
 */
@RestController
@RequestMapping("/api")
public class CaseRecoveryBillResource {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryBillResource.class);

    private static final String ENTITY_NAME = "caseRecoveryBill";

    private final CaseRecoveryBillService caseRecoveryBillService;

    private final CaseRecoveryBillQueryService caseRecoveryBillQueryService;

    public CaseRecoveryBillResource(CaseRecoveryBillService caseRecoveryBillService, CaseRecoveryBillQueryService caseRecoveryBillQueryService) {
        this.caseRecoveryBillService = caseRecoveryBillService;
        this.caseRecoveryBillQueryService = caseRecoveryBillQueryService;
    }

    /**
     * POST  /case-recovery-bills : Create a new caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the caseRecoveryBillDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseRecoveryBillDTO, or with status 400 (Bad Request) if the caseRecoveryBill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-recovery-bills")
    @Timed
    public ResponseEntity<CaseRecoveryBillDTO> createCaseRecoveryBill(@RequestBody CaseRecoveryBillDTO caseRecoveryBillDTO) throws URISyntaxException {
        log.debug("REST request to save CaseRecoveryBill : {}", caseRecoveryBillDTO);
        if (caseRecoveryBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseRecoveryBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseRecoveryBillDTO result = caseRecoveryBillService.save(caseRecoveryBillDTO);
        return ResponseEntity.created(new URI("/api/case-recovery-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-recovery-bills : Updates an existing caseRecoveryBill.
     *
     * @param caseRecoveryBillDTO the caseRecoveryBillDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseRecoveryBillDTO,
     * or with status 400 (Bad Request) if the caseRecoveryBillDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseRecoveryBillDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-recovery-bills")
    @Timed
    public ResponseEntity<CaseRecoveryBillDTO> updateCaseRecoveryBill(@RequestBody CaseRecoveryBillDTO caseRecoveryBillDTO) throws URISyntaxException {
        log.debug("REST request to update CaseRecoveryBill : {}", caseRecoveryBillDTO);
        if (caseRecoveryBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseRecoveryBillDTO result = caseRecoveryBillService.save(caseRecoveryBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseRecoveryBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-recovery-bills : get all the caseRecoveryBills.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseRecoveryBills in body
     */
    @GetMapping("/case-recovery-bills")
    @Timed
    public ResponseEntity<List<CaseRecoveryBillDTO>> getAllCaseRecoveryBills(CaseRecoveryBillCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseRecoveryBills by criteria: {}", criteria);
        Page<CaseRecoveryBillDTO> page = caseRecoveryBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-recovery-bills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-recovery-bills/:id : get the "id" caseRecoveryBill.
     *
     * @param id the id of the caseRecoveryBillDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseRecoveryBillDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-recovery-bills/{id}")
    @Timed
    public ResponseEntity<CaseRecoveryBillDTO> getCaseRecoveryBill(@PathVariable Long id) {
        log.debug("REST request to get CaseRecoveryBill : {}", id);
        Optional<CaseRecoveryBillDTO> caseRecoveryBillDTO = caseRecoveryBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseRecoveryBillDTO);
    }

    /**
     * DELETE  /case-recovery-bills/:id : delete the "id" caseRecoveryBill.
     *
     * @param id the id of the caseRecoveryBillDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-recovery-bills/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseRecoveryBill(@PathVariable Long id) {
        log.debug("REST request to delete CaseRecoveryBill : {}", id);
        caseRecoveryBillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
