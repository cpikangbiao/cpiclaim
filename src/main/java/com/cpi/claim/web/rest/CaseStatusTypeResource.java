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
import com.cpi.claim.service.CaseStatusTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.dto.CaseStatusTypeCriteria;
import com.cpi.claim.service.CaseStatusTypeQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CaseStatusType.
 */
@RestController
@RequestMapping("/api")
public class CaseStatusTypeResource {

    private final Logger log = LoggerFactory.getLogger(CaseStatusTypeResource.class);

    private static final String ENTITY_NAME = "caseStatusType";

    private final CaseStatusTypeService caseStatusTypeService;

    private final CaseStatusTypeQueryService caseStatusTypeQueryService;

    public CaseStatusTypeResource(CaseStatusTypeService caseStatusTypeService, CaseStatusTypeQueryService caseStatusTypeQueryService) {
        this.caseStatusTypeService = caseStatusTypeService;
        this.caseStatusTypeQueryService = caseStatusTypeQueryService;
    }

    /**
     * POST  /case-status-types : Create a new caseStatusType.
     *
     * @param caseStatusTypeDTO the caseStatusTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new caseStatusTypeDTO, or with status 400 (Bad Request) if the caseStatusType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/case-status-types")
    @Timed
    public ResponseEntity<CaseStatusTypeDTO> createCaseStatusType(@Valid @RequestBody CaseStatusTypeDTO caseStatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseStatusType : {}", caseStatusTypeDTO);
        if (caseStatusTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseStatusType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseStatusTypeDTO result = caseStatusTypeService.save(caseStatusTypeDTO);
        return ResponseEntity.created(new URI("/api/case-status-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /case-status-types : Updates an existing caseStatusType.
     *
     * @param caseStatusTypeDTO the caseStatusTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated caseStatusTypeDTO,
     * or with status 400 (Bad Request) if the caseStatusTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the caseStatusTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/case-status-types")
    @Timed
    public ResponseEntity<CaseStatusTypeDTO> updateCaseStatusType(@Valid @RequestBody CaseStatusTypeDTO caseStatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseStatusType : {}", caseStatusTypeDTO);
        if (caseStatusTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseStatusTypeDTO result = caseStatusTypeService.save(caseStatusTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, caseStatusTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /case-status-types : get all the caseStatusTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of caseStatusTypes in body
     */
    @GetMapping("/case-status-types")
    @Timed
    public ResponseEntity<List<CaseStatusTypeDTO>> getAllCaseStatusTypes(CaseStatusTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CaseStatusTypes by criteria: {}", criteria);
        Page<CaseStatusTypeDTO> page = caseStatusTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-status-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /case-status-types/:id : get the "id" caseStatusType.
     *
     * @param id the id of the caseStatusTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the caseStatusTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/case-status-types/{id}")
    @Timed
    public ResponseEntity<CaseStatusTypeDTO> getCaseStatusType(@PathVariable Long id) {
        log.debug("REST request to get CaseStatusType : {}", id);
        Optional<CaseStatusTypeDTO> caseStatusTypeDTO = caseStatusTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseStatusTypeDTO);
    }

    /**
     * DELETE  /case-status-types/:id : delete the "id" caseStatusType.
     *
     * @param id the id of the caseStatusTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/case-status-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteCaseStatusType(@PathVariable Long id) {
        log.debug("REST request to delete CaseStatusType : {}", id);
        caseStatusTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
