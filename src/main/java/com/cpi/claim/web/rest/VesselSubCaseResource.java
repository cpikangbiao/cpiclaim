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


import com.cpi.claim.service.VesselSubCaseExtService;

import com.cpi.claim.service.VesselSubCaseService;
import com.cpi.claim.service.utility.generate.SubCaseCodeGenerateUtility;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.VesselSubCaseDTO;
import com.cpi.claim.service.dto.VesselSubCaseCriteria;
import com.cpi.claim.service.VesselSubCaseQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.VesselSubCase}.
 */
@RestController
@RequestMapping("/api")
public class VesselSubCaseResource {

    private final Logger log = LoggerFactory.getLogger(VesselSubCaseResource.class);

    private static final String ENTITY_NAME = "cpiclaimVesselSubCase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VesselSubCaseService vesselSubCaseService;

    private final VesselSubCaseQueryService vesselSubCaseQueryService;

    @Autowired
    private VesselSubCaseExtService vesselSubCaseExtService;

    public VesselSubCaseResource(VesselSubCaseService vesselSubCaseService, VesselSubCaseQueryService vesselSubCaseQueryService) {
        this.vesselSubCaseService = vesselSubCaseService;
        this.vesselSubCaseQueryService = vesselSubCaseQueryService;
    }

    /**
     * {@code POST  /vessel-sub-cases} : Create a new vesselSubCase.
     *
     * @param vesselSubCaseDTO the vesselSubCaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vesselSubCaseDTO, or with status {@code 400 (Bad Request)} if the vesselSubCase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vessel-sub-cases")
    public ResponseEntity<VesselSubCaseDTO> createVesselSubCase(@RequestBody VesselSubCaseDTO vesselSubCaseDTO) throws URISyntaxException {
        log.debug("REST request to save VesselSubCase : {}", vesselSubCaseDTO);
        if (vesselSubCaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new vesselSubCase cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if (vesselSubCaseDTO.getVesselCaseId() == null) {
            throw new BadRequestAlertException("A new vesselSubCase cannot saved because VesselCaseId is NULL", ENTITY_NAME, "idexists");
        }

        vesselSubCaseDTO.setNumberId(vesselSubCaseExtService.findNextNumberIdByVesselCaseId(vesselSubCaseDTO.getVesselCaseId()));
        vesselSubCaseDTO.setSubcaseCode(SubCaseCodeGenerateUtility.createSubCaseCode(
            vesselSubCaseDTO.getVesselCaseCaseCode(),
            vesselSubCaseDTO.getNumberId()
        ));

        VesselSubCaseDTO result = vesselSubCaseService.save(vesselSubCaseDTO);
        return ResponseEntity.created(new URI("/api/vessel-sub-cases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vessel-sub-cases} : Updates an existing vesselSubCase.
     *
     * @param vesselSubCaseDTO the vesselSubCaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vesselSubCaseDTO,
     * or with status {@code 400 (Bad Request)} if the vesselSubCaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vesselSubCaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vessel-sub-cases")
    public ResponseEntity<VesselSubCaseDTO> updateVesselSubCase(@RequestBody VesselSubCaseDTO vesselSubCaseDTO) throws URISyntaxException {
        log.debug("REST request to update VesselSubCase : {}", vesselSubCaseDTO);
        if (vesselSubCaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VesselSubCaseDTO result = vesselSubCaseService.save(vesselSubCaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vesselSubCaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vessel-sub-cases} : get all the vesselSubCases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vesselSubCases in body.
     */
    @GetMapping("/vessel-sub-cases")
    public ResponseEntity<List<VesselSubCaseDTO>> getAllVesselSubCases(VesselSubCaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get VesselSubCases by criteria: {}", criteria);
        Page<VesselSubCaseDTO> page = vesselSubCaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /vessel-sub-cases/count} : count all the vesselSubCases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/vessel-sub-cases/count")
    public ResponseEntity<Long> countVesselSubCases(VesselSubCaseCriteria criteria) {
        log.debug("REST request to count VesselSubCases by criteria: {}", criteria);
        return ResponseEntity.ok().body(vesselSubCaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vessel-sub-cases/:id} : get the "id" vesselSubCase.
     *
     * @param id the id of the vesselSubCaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vesselSubCaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vessel-sub-cases/{id}")
    public ResponseEntity<VesselSubCaseDTO> getVesselSubCase(@PathVariable Long id) {
        log.debug("REST request to get VesselSubCase : {}", id);
        Optional<VesselSubCaseDTO> vesselSubCaseDTO = vesselSubCaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vesselSubCaseDTO);
    }

    /**
     * {@code DELETE  /vessel-sub-cases/:id} : delete the "id" vesselSubCase.
     *
     * @param id the id of the vesselSubCaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vessel-sub-cases/{id}")
    public ResponseEntity<Void> deleteVesselSubCase(@PathVariable Long id) {
        log.debug("REST request to delete VesselSubCase : {}", id);
        vesselSubCaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
