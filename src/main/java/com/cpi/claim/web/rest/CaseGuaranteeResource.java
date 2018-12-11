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
import com.cpi.claim.service.CaseGuaranteeExtService;
import com.cpi.claim.service.CaseGuaranteeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;
import com.cpi.claim.service.dto.CaseGuaranteeCriteria;
import com.cpi.claim.service.CaseGuaranteeQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing CaseGuarantee.
 */
@RestController
@RequestMapping("/api")
public class CaseGuaranteeResource {

    private final Logger log = LoggerFactory.getLogger(CaseGuaranteeResource.class);

    private static final String ENTITY_NAME = "caseGuarantee";

    private final CaseGuaranteeService caseGuaranteeService;

    private final CaseGuaranteeQueryService caseGuaranteeQueryService;

    @Autowired
    private CaseGuaranteeExtService caseGuaranteeExtService;

    public CaseGuaranteeResource(CaseGuaranteeService caseGuaranteeService, CaseGuaranteeQueryService caseGuaranteeQueryService) {
        this.caseGuaranteeService = caseGuaranteeService;
        this.caseGuaranteeQueryService = caseGuaranteeQueryService;
    }

    /**
     * POST  /case-guarantees : Create a new caseGuarantee.
     *
     * @param caseGuaranteeDTO the caseGuaranteeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseGuaranteeDTO, or with status 400 (Bad Request) if the caseGuarantee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-guarantees")
    @Timed
    public ResponseEntity<CaseGuaranteeDTO> createCaseGuarantee(@RequestBody CaseGuaranteeDTO caseGuaranteeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseGuarantee : {}", caseGuaranteeDTO);
        if (caseGuaranteeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseGuarantee cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (caseGuaranteeDTO.getSubcaseId() == null) {
            throw new BadRequestAlertException("A new caseFee not have Sub Case ID", ENTITY_NAME, "idexists");
        }

        caseGuaranteeDTO.setNumberId(caseGuaranteeExtService.findMaxNumberIdBySubCaseId(caseGuaranteeDTO.getSubcaseId()));

        CaseGuaranteeDTO result = caseGuaranteeService.save(caseGuaranteeDTO);
        return ResponseEntity.created(new URI("/api/case-guarantees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-guarantees : Updates an existing caseGuarantee.
     *
     * @param caseGuaranteeDTO the caseGuaranteeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseGuaranteeDTO,
     * or with status 400 (Bad Request) if the caseGuaranteeDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseGuaranteeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-guarantees")
    @Timed
    public ResponseEntity<CaseGuaranteeDTO> updateCaseGuarantee(@RequestBody CaseGuaranteeDTO caseGuaranteeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseGuarantee : {}", caseGuaranteeDTO);
        if (caseGuaranteeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseGuaranteeDTO result = caseGuaranteeService.save(caseGuaranteeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseGuaranteeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-guarantees : get all the caseGuarantees.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseGuarantees in body
     */
    @GetMapping("/case-guarantees")
    @Timed
    public ResponseEntity<List<CaseGuaranteeDTO>> getAllCaseGuarantees(CaseGuaranteeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseGuarantees by criteria: {}", criteria);
        Page<CaseGuaranteeDTO> page = caseGuaranteeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-guarantees");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-guarantees/:id : get the "id" caseGuarantee.
     *
     * @param id the id of the caseGuaranteeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseGuaranteeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-guarantees/{id}")
    @Timed
    public ResponseEntity<CaseGuaranteeDTO> getCaseGuarantee(@PathVariable Long id) {
        log.debug("REST request to get CaseGuarantee : {}", id);
        Optional<CaseGuaranteeDTO> caseGuaranteeDTO = caseGuaranteeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseGuaranteeDTO);
    }

    /**
     * DELETE  /case-guarantees/:id : delete the "id" caseGuarantee.
     *
     * @param id the id of the caseGuaranteeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-guarantees/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseGuarantee(@PathVariable Long id) {
        log.debug("REST request to delete CaseGuarantee : {}", id);
        caseGuaranteeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
