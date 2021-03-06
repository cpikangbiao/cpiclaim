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

import com.cpi.claim.service.RecoveryTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.RecoveryTypeDTO;
import com.cpi.claim.service.dto.RecoveryTypeCriteria;
import com.cpi.claim.service.RecoveryTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.RecoveryType}.
 */
@RestController
@RequestMapping("/api")
public class RecoveryTypeResource {

    private final Logger log = LoggerFactory.getLogger(RecoveryTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimRecoveryType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecoveryTypeService recoveryTypeService;

    private final RecoveryTypeQueryService recoveryTypeQueryService;

    public RecoveryTypeResource(RecoveryTypeService recoveryTypeService, RecoveryTypeQueryService recoveryTypeQueryService) {
        this.recoveryTypeService = recoveryTypeService;
        this.recoveryTypeQueryService = recoveryTypeQueryService;
    }

    /**
     * {@code POST  /recovery-types} : Create a new recoveryType.
     *
     * @param recoveryTypeDTO the recoveryTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recoveryTypeDTO, or with status {@code 400 (Bad Request)} if the recoveryType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/recovery-types")
    public ResponseEntity<RecoveryTypeDTO> createRecoveryType(@Valid @RequestBody RecoveryTypeDTO recoveryTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RecoveryType : {}", recoveryTypeDTO);
        if (recoveryTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new recoveryType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecoveryTypeDTO result = recoveryTypeService.save(recoveryTypeDTO);
        return ResponseEntity.created(new URI("/api/recovery-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /recovery-types} : Updates an existing recoveryType.
     *
     * @param recoveryTypeDTO the recoveryTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recoveryTypeDTO,
     * or with status {@code 400 (Bad Request)} if the recoveryTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recoveryTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/recovery-types")
    public ResponseEntity<RecoveryTypeDTO> updateRecoveryType(@Valid @RequestBody RecoveryTypeDTO recoveryTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RecoveryType : {}", recoveryTypeDTO);
        if (recoveryTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RecoveryTypeDTO result = recoveryTypeService.save(recoveryTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recoveryTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /recovery-types} : get all the recoveryTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recoveryTypes in body.
     */
    @GetMapping("/recovery-types")
    public ResponseEntity<List<RecoveryTypeDTO>> getAllRecoveryTypes(RecoveryTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get RecoveryTypes by criteria: {}", criteria);
        Page<RecoveryTypeDTO> page = recoveryTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /recovery-types/count} : count all the recoveryTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/recovery-types/count")
    public ResponseEntity<Long> countRecoveryTypes(RecoveryTypeCriteria criteria) {
        log.debug("REST request to count RecoveryTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(recoveryTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /recovery-types/:id} : get the "id" recoveryType.
     *
     * @param id the id of the recoveryTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recoveryTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/recovery-types/{id}")
    public ResponseEntity<RecoveryTypeDTO> getRecoveryType(@PathVariable Long id) {
        log.debug("REST request to get RecoveryType : {}", id);
        Optional<RecoveryTypeDTO> recoveryTypeDTO = recoveryTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recoveryTypeDTO);
    }

    /**
     * {@code DELETE  /recovery-types/:id} : delete the "id" recoveryType.
     *
     * @param id the id of the recoveryTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/recovery-types/{id}")
    public ResponseEntity<Void> deleteRecoveryType(@PathVariable Long id) {
        log.debug("REST request to delete RecoveryType : {}", id);
        recoveryTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
