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

import com.cpi.claim.service.CaseSettlementModeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;
import com.cpi.claim.service.dto.CaseSettlementModeCriteria;
import com.cpi.claim.service.CaseSettlementModeQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.CaseSettlementMode}.
 */
@RestController
@RequestMapping("/api")
public class CaseSettlementModeResource {

    private final Logger log = LoggerFactory.getLogger(CaseSettlementModeResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseSettlementMode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseSettlementModeService caseSettlementModeService;

    private final CaseSettlementModeQueryService caseSettlementModeQueryService;

    public CaseSettlementModeResource(CaseSettlementModeService caseSettlementModeService, CaseSettlementModeQueryService caseSettlementModeQueryService) {
        this.caseSettlementModeService = caseSettlementModeService;
        this.caseSettlementModeQueryService = caseSettlementModeQueryService;
    }

    /**
     * {@code POST  /case-settlement-modes} : Create a new caseSettlementMode.
     *
     * @param caseSettlementModeDTO the caseSettlementModeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseSettlementModeDTO, or with status {@code 400 (Bad Request)} if the caseSettlementMode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-settlement-modes")
    public ResponseEntity<CaseSettlementModeDTO> createCaseSettlementMode(@Valid @RequestBody CaseSettlementModeDTO caseSettlementModeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseSettlementMode : {}", caseSettlementModeDTO);
        if (caseSettlementModeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseSettlementMode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseSettlementModeDTO result = caseSettlementModeService.save(caseSettlementModeDTO);
        return ResponseEntity.created(new URI("/api/case-settlement-modes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-settlement-modes} : Updates an existing caseSettlementMode.
     *
     * @param caseSettlementModeDTO the caseSettlementModeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseSettlementModeDTO,
     * or with status {@code 400 (Bad Request)} if the caseSettlementModeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseSettlementModeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-settlement-modes")
    public ResponseEntity<CaseSettlementModeDTO> updateCaseSettlementMode(@Valid @RequestBody CaseSettlementModeDTO caseSettlementModeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseSettlementMode : {}", caseSettlementModeDTO);
        if (caseSettlementModeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseSettlementModeDTO result = caseSettlementModeService.save(caseSettlementModeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseSettlementModeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-settlement-modes} : get all the caseSettlementModes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseSettlementModes in body.
     */
    @GetMapping("/case-settlement-modes")
    public ResponseEntity<List<CaseSettlementModeDTO>> getAllCaseSettlementModes(CaseSettlementModeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseSettlementModes by criteria: {}", criteria);
        Page<CaseSettlementModeDTO> page = caseSettlementModeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-settlement-modes/count} : count all the caseSettlementModes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-settlement-modes/count")
    public ResponseEntity<Long> countCaseSettlementModes(CaseSettlementModeCriteria criteria) {
        log.debug("REST request to count CaseSettlementModes by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseSettlementModeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-settlement-modes/:id} : get the "id" caseSettlementMode.
     *
     * @param id the id of the caseSettlementModeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseSettlementModeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-settlement-modes/{id}")
    public ResponseEntity<CaseSettlementModeDTO> getCaseSettlementMode(@PathVariable Long id) {
        log.debug("REST request to get CaseSettlementMode : {}", id);
        Optional<CaseSettlementModeDTO> caseSettlementModeDTO = caseSettlementModeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseSettlementModeDTO);
    }

    /**
     * {@code DELETE  /case-settlement-modes/:id} : delete the "id" caseSettlementMode.
     *
     * @param id the id of the caseSettlementModeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-settlement-modes/{id}")
    public ResponseEntity<Void> deleteCaseSettlementMode(@PathVariable Long id) {
        log.debug("REST request to delete CaseSettlementMode : {}", id);
        caseSettlementModeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
