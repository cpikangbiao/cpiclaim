/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CasePaymentBillService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CasePaymentBillDTO;
import com.cpi.claim.service.dto.CasePaymentBillCriteria;
import com.cpi.claim.service.CasePaymentBillQueryService;
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
 * REST controller for managing CasePaymentBill.
 */
@RestController
@RequestMapping("/api")
public class CasePaymentBillResource {

    private final Logger log = LoggerFactory.getLogger(CasePaymentBillResource.class);

    private static final String ENTITY_NAME = "casePaymentBill";

    private final CasePaymentBillService casePaymentBillService;

    private final CasePaymentBillQueryService casePaymentBillQueryService;

    public CasePaymentBillResource(CasePaymentBillService casePaymentBillService, CasePaymentBillQueryService casePaymentBillQueryService) {
        this.casePaymentBillService = casePaymentBillService;
        this.casePaymentBillQueryService = casePaymentBillQueryService;
    }

    /**
     * POST  /case-payment-bills : Create a new casePaymentBill.
     *
     * @param casePaymentBillDTO the casePaymentBillDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new casePaymentBillDTO, or with status 400 (Bad Request) if the casePaymentBill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-payment-bills")
    @Timed
    public ResponseEntity<CasePaymentBillDTO> createCasePaymentBill(@RequestBody CasePaymentBillDTO casePaymentBillDTO) throws URISyntaxException {
        log.debug("REST request to save CasePaymentBill : {}", casePaymentBillDTO);
        if (casePaymentBillDTO.getId() != null) {
            throw new BadRequestAlertException("A new casePaymentBill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CasePaymentBillDTO result = casePaymentBillService.save(casePaymentBillDTO);
        return ResponseEntity.created(new URI("/api/case-payment-bills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-payment-bills : Updates an existing casePaymentBill.
     *
     * @param casePaymentBillDTO the casePaymentBillDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated casePaymentBillDTO,
     * or with status 400 (Bad Request) if the casePaymentBillDTO is not valid,
     * or with status 500 (Internal Server Error) if the casePaymentBillDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-payment-bills")
    @Timed
    public ResponseEntity<CasePaymentBillDTO> updateCasePaymentBill(@RequestBody CasePaymentBillDTO casePaymentBillDTO) throws URISyntaxException {
        log.debug("REST request to update CasePaymentBill : {}", casePaymentBillDTO);
        if (casePaymentBillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CasePaymentBillDTO result = casePaymentBillService.save(casePaymentBillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, casePaymentBillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-payment-bills : get all the casePaymentBills.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of casePaymentBills in body
     */
    @GetMapping("/case-payment-bills")
    @Timed
    public ResponseEntity<List<CasePaymentBillDTO>> getAllCasePaymentBills(CasePaymentBillCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CasePaymentBills by criteria: {}", criteria);
        Page<CasePaymentBillDTO> page = casePaymentBillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-payment-bills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-payment-bills/:id : get the "id" casePaymentBill.
     *
     * @param id the id of the casePaymentBillDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the casePaymentBillDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-payment-bills/{id}")
    @Timed
    public ResponseEntity<CasePaymentBillDTO> getCasePaymentBill(@PathVariable Long id) {
        log.debug("REST request to get CasePaymentBill : {}", id);
        Optional<CasePaymentBillDTO> casePaymentBillDTO = casePaymentBillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casePaymentBillDTO);
    }

    /**
     * DELETE  /case-payment-bills/:id : delete the "id" casePaymentBill.
     *
     * @param id the id of the casePaymentBillDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-payment-bills/{id}")
    @Timed
    public ResponseEntity<Void> deleteCasePaymentBill(@PathVariable Long id) {
        log.debug("REST request to delete CasePaymentBill : {}", id);
        casePaymentBillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
