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

import com.cpi.claim.service.RiskGroupService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.RiskGroupDTO;
import com.cpi.claim.service.dto.RiskGroupCriteria;
import com.cpi.claim.service.RiskGroupQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.RiskGroup}.
 */
@RestController
@RequestMapping("/api")
public class RiskGroupResource {

    private final Logger log = LoggerFactory.getLogger(RiskGroupResource.class);

    private static final String ENTITY_NAME = "cpiclaimRiskGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskGroupService riskGroupService;

    private final RiskGroupQueryService riskGroupQueryService;

    public RiskGroupResource(RiskGroupService riskGroupService, RiskGroupQueryService riskGroupQueryService) {
        this.riskGroupService = riskGroupService;
        this.riskGroupQueryService = riskGroupQueryService;
    }

    /**
     * {@code POST  /risk-groups} : Create a new riskGroup.
     *
     * @param riskGroupDTO the riskGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskGroupDTO, or with status {@code 400 (Bad Request)} if the riskGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-groups")
    public ResponseEntity<RiskGroupDTO> createRiskGroup(@Valid @RequestBody RiskGroupDTO riskGroupDTO) throws URISyntaxException {
        log.debug("REST request to save RiskGroup : {}", riskGroupDTO);
        if (riskGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskGroupDTO result = riskGroupService.save(riskGroupDTO);
        return ResponseEntity.created(new URI("/api/risk-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-groups} : Updates an existing riskGroup.
     *
     * @param riskGroupDTO the riskGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskGroupDTO,
     * or with status {@code 400 (Bad Request)} if the riskGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-groups")
    public ResponseEntity<RiskGroupDTO> updateRiskGroup(@Valid @RequestBody RiskGroupDTO riskGroupDTO) throws URISyntaxException {
        log.debug("REST request to update RiskGroup : {}", riskGroupDTO);
        if (riskGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskGroupDTO result = riskGroupService.save(riskGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, riskGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risk-groups} : get all the riskGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskGroups in body.
     */
    @GetMapping("/risk-groups")
    public ResponseEntity<List<RiskGroupDTO>> getAllRiskGroups(RiskGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get RiskGroups by criteria: {}", criteria);
        Page<RiskGroupDTO> page = riskGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /risk-groups/count} : count all the riskGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/risk-groups/count")
    public ResponseEntity<Long> countRiskGroups(RiskGroupCriteria criteria) {
        log.debug("REST request to count RiskGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(riskGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /risk-groups/:id} : get the "id" riskGroup.
     *
     * @param id the id of the riskGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-groups/{id}")
    public ResponseEntity<RiskGroupDTO> getRiskGroup(@PathVariable Long id) {
        log.debug("REST request to get RiskGroup : {}", id);
        Optional<RiskGroupDTO> riskGroupDTO = riskGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskGroupDTO);
    }

    /**
     * {@code DELETE  /risk-groups/:id} : delete the "id" riskGroup.
     *
     * @param id the id of the riskGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-groups/{id}")
    public ResponseEntity<Void> deleteRiskGroup(@PathVariable Long id) {
        log.debug("REST request to delete RiskGroup : {}", id);
        riskGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
