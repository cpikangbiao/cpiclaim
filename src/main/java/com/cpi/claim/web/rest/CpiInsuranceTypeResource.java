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

import com.cpi.claim.service.CpiInsuranceTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;
import com.cpi.claim.service.dto.CpiInsuranceTypeCriteria;
import com.cpi.claim.service.CpiInsuranceTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CpiInsuranceType}.
 */
@RestController
@RequestMapping("/api")
public class CpiInsuranceTypeResource {

    private final Logger log = LoggerFactory.getLogger(CpiInsuranceTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimCpiInsuranceType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CpiInsuranceTypeService cpiInsuranceTypeService;

    private final CpiInsuranceTypeQueryService cpiInsuranceTypeQueryService;

    public CpiInsuranceTypeResource(CpiInsuranceTypeService cpiInsuranceTypeService, CpiInsuranceTypeQueryService cpiInsuranceTypeQueryService) {
        this.cpiInsuranceTypeService = cpiInsuranceTypeService;
        this.cpiInsuranceTypeQueryService = cpiInsuranceTypeQueryService;
    }

    /**
     * {@code POST  /cpi-insurance-types} : Create a new cpiInsuranceType.
     *
     * @param cpiInsuranceTypeDTO the cpiInsuranceTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cpiInsuranceTypeDTO, or with status {@code 400 (Bad Request)} if the cpiInsuranceType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cpi-insurance-types")
    public ResponseEntity<CpiInsuranceTypeDTO> createCpiInsuranceType(@Valid @RequestBody CpiInsuranceTypeDTO cpiInsuranceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CpiInsuranceType : {}", cpiInsuranceTypeDTO);
        if (cpiInsuranceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new cpiInsuranceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CpiInsuranceTypeDTO result = cpiInsuranceTypeService.save(cpiInsuranceTypeDTO);
        return ResponseEntity.created(new URI("/api/cpi-insurance-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cpi-insurance-types} : Updates an existing cpiInsuranceType.
     *
     * @param cpiInsuranceTypeDTO the cpiInsuranceTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cpiInsuranceTypeDTO,
     * or with status {@code 400 (Bad Request)} if the cpiInsuranceTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cpiInsuranceTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cpi-insurance-types")
    public ResponseEntity<CpiInsuranceTypeDTO> updateCpiInsuranceType(@Valid @RequestBody CpiInsuranceTypeDTO cpiInsuranceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CpiInsuranceType : {}", cpiInsuranceTypeDTO);
        if (cpiInsuranceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CpiInsuranceTypeDTO result = cpiInsuranceTypeService.save(cpiInsuranceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cpiInsuranceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cpi-insurance-types} : get all the cpiInsuranceTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cpiInsuranceTypes in body.
     */
    @GetMapping("/cpi-insurance-types")
    public ResponseEntity<List<CpiInsuranceTypeDTO>> getAllCpiInsuranceTypes(CpiInsuranceTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CpiInsuranceTypes by criteria: {}", criteria);
        Page<CpiInsuranceTypeDTO> page = cpiInsuranceTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /cpi-insurance-types/count} : count all the cpiInsuranceTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/cpi-insurance-types/count")
    public ResponseEntity<Long> countCpiInsuranceTypes(CpiInsuranceTypeCriteria criteria) {
        log.debug("REST request to count CpiInsuranceTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(cpiInsuranceTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /cpi-insurance-types/:id} : get the "id" cpiInsuranceType.
     *
     * @param id the id of the cpiInsuranceTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cpiInsuranceTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cpi-insurance-types/{id}")
    public ResponseEntity<CpiInsuranceTypeDTO> getCpiInsuranceType(@PathVariable Long id) {
        log.debug("REST request to get CpiInsuranceType : {}", id);
        Optional<CpiInsuranceTypeDTO> cpiInsuranceTypeDTO = cpiInsuranceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cpiInsuranceTypeDTO);
    }

    /**
     * {@code DELETE  /cpi-insurance-types/:id} : delete the "id" cpiInsuranceType.
     *
     * @param id the id of the cpiInsuranceTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cpi-insurance-types/{id}")
    public ResponseEntity<Void> deleteCpiInsuranceType(@PathVariable Long id) {
        log.debug("REST request to delete CpiInsuranceType : {}", id);
        cpiInsuranceTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
