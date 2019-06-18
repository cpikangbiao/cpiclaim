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


import com.cpi.claim.service.CasePaymentExtService;

import com.cpi.claim.service.CasePaymentService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CasePaymentDTO;
import com.cpi.claim.service.dto.CasePaymentCriteria;
import com.cpi.claim.service.CasePaymentQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing {@link com.cpi.claim.domain.CasePayment}.
 */
@RestController
@RequestMapping("/api")
public class CasePaymentResource {

    private final Logger log = LoggerFactory.getLogger(CasePaymentResource.class);

    private static final String ENTITY_NAME = "cpiclaimCasePayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CasePaymentService casePaymentService;

    private final CasePaymentQueryService casePaymentQueryService;

    @Autowired
    private CasePaymentExtService casePaymentExtService;

    public CasePaymentResource(CasePaymentService casePaymentService, CasePaymentQueryService casePaymentQueryService) {
        this.casePaymentService = casePaymentService;
        this.casePaymentQueryService = casePaymentQueryService;
    }

    /**
     * {@code POST  /case-payments} : Create a new casePayment.
     *
     * @param casePaymentDTO the casePaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new casePaymentDTO, or with status {@code 400 (Bad Request)} if the casePayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-payments")
    public ResponseEntity<CasePaymentDTO> createCasePayment(@RequestBody CasePaymentDTO casePaymentDTO) throws URISyntaxException {
        log.debug("REST request to save CasePayment : {}", casePaymentDTO);
        if (casePaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new casePayment cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (casePaymentDTO.getSubcaseId() == null) {
            throw new BadRequestAlertException("A new casePayment not have Sub Case ID", ENTITY_NAME, "idexists");
        }

        casePaymentDTO.setNumberId(casePaymentExtService.findMaxNumberIdBySubCaseId(casePaymentDTO.getSubcaseId()));

        CasePaymentDTO result = casePaymentService.save(casePaymentDTO);
        return ResponseEntity.created(new URI("/api/case-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-payments} : Updates an existing casePayment.
     *
     * @param casePaymentDTO the casePaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated casePaymentDTO,
     * or with status {@code 400 (Bad Request)} if the casePaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the casePaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-payments")
    public ResponseEntity<CasePaymentDTO> updateCasePayment(@RequestBody CasePaymentDTO casePaymentDTO) throws URISyntaxException {
        log.debug("REST request to update CasePayment : {}", casePaymentDTO);
        if (casePaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CasePaymentDTO result = casePaymentService.save(casePaymentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, casePaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-payments} : get all the casePayments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of casePayments in body.
     */
    @GetMapping("/case-payments")
    public ResponseEntity<List<CasePaymentDTO>> getAllCasePayments(CasePaymentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CasePayments by criteria: {}", criteria);
        Page<CasePaymentDTO> page = casePaymentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-payments/count} : count all the casePayments.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-payments/count")
    public ResponseEntity<Long> countCasePayments(CasePaymentCriteria criteria) {
        log.debug("REST request to count CasePayments by criteria: {}", criteria);
        return ResponseEntity.ok().body(casePaymentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-payments/:id} : get the "id" casePayment.
     *
     * @param id the id of the casePaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the casePaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-payments/{id}")
    public ResponseEntity<CasePaymentDTO> getCasePayment(@PathVariable Long id) {
        log.debug("REST request to get CasePayment : {}", id);
        Optional<CasePaymentDTO> casePaymentDTO = casePaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(casePaymentDTO);
    }

    /**
     * {@code DELETE  /case-payments/:id} : delete the "id" casePayment.
     *
     * @param id the id of the casePaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-payments/{id}")
    public ResponseEntity<Void> deleteCasePayment(@PathVariable Long id) {
        log.debug("REST request to delete CasePayment : {}", id);
        casePaymentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
