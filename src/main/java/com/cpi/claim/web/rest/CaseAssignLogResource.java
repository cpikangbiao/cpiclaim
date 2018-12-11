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
import com.cpi.claim.service.CaseAssignLogService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseAssignLogDTO;
import com.cpi.claim.service.dto.CaseAssignLogCriteria;
import com.cpi.claim.service.CaseAssignLogQueryService;
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
 * REST controller for managing CaseAssignLog.
 */
@RestController
@RequestMapping("/api")
public class CaseAssignLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseAssignLogResource.class);

    private static final String ENTITY_NAME = "caseAssignLog";

    private final CaseAssignLogService caseAssignLogService;

    private final CaseAssignLogQueryService caseAssignLogQueryService;

    public CaseAssignLogResource(CaseAssignLogService caseAssignLogService, CaseAssignLogQueryService caseAssignLogQueryService) {
        this.caseAssignLogService = caseAssignLogService;
        this.caseAssignLogQueryService = caseAssignLogQueryService;
    }

    /**
     * POST  /case-assign-logs : Create a new caseAssignLog.
     *
     * @param caseAssignLogDTO the caseAssignLogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseAssignLogDTO, or with status 400 (Bad Request) if the caseAssignLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-assign-logs")
    @Timed
    public ResponseEntity<CaseAssignLogDTO> createCaseAssignLog(@RequestBody CaseAssignLogDTO caseAssignLogDTO) throws URISyntaxException {
        log.debug("REST request to save CaseAssignLog : {}", caseAssignLogDTO);
        if (caseAssignLogDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseAssignLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseAssignLogDTO result = caseAssignLogService.save(caseAssignLogDTO);
        return ResponseEntity.created(new URI("/api/case-assign-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-assign-logs : Updates an existing caseAssignLog.
     *
     * @param caseAssignLogDTO the caseAssignLogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseAssignLogDTO,
     * or with status 400 (Bad Request) if the caseAssignLogDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseAssignLogDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-assign-logs")
    @Timed
    public ResponseEntity<CaseAssignLogDTO> updateCaseAssignLog(@RequestBody CaseAssignLogDTO caseAssignLogDTO) throws URISyntaxException {
        log.debug("REST request to update CaseAssignLog : {}", caseAssignLogDTO);
        if (caseAssignLogDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseAssignLogDTO result = caseAssignLogService.save(caseAssignLogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseAssignLogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-assign-logs : get all the caseAssignLogs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseAssignLogs in body
     */
    @GetMapping("/case-assign-logs")
    @Timed
    public ResponseEntity<List<CaseAssignLogDTO>> getAllCaseAssignLogs(CaseAssignLogCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseAssignLogs by criteria: {}", criteria);
        Page<CaseAssignLogDTO> page = caseAssignLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-assign-logs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-assign-logs/:id : get the "id" caseAssignLog.
     *
     * @param id the id of the caseAssignLogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseAssignLogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-assign-logs/{id}")
    @Timed
    public ResponseEntity<CaseAssignLogDTO> getCaseAssignLog(@PathVariable Long id) {
        log.debug("REST request to get CaseAssignLog : {}", id);
        Optional<CaseAssignLogDTO> caseAssignLogDTO = caseAssignLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseAssignLogDTO);
    }

    /**
     * DELETE  /case-assign-logs/:id : delete the "id" caseAssignLog.
     *
     * @param id the id of the caseAssignLogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-assign-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseAssignLog(@PathVariable Long id) {
        log.debug("REST request to delete CaseAssignLog : {}", id);
        caseAssignLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
