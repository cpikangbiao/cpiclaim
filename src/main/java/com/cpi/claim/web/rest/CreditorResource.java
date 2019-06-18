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

import com.cpi.claim.service.CreditorService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CreditorDTO;
import com.cpi.claim.service.dto.CreditorCriteria;
import com.cpi.claim.service.CreditorQueryService;

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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.cpi.claim.domain.Creditor}.
 */
@RestController
@RequestMapping("/api")
public class CreditorResource {

    private final Logger log = LoggerFactory.getLogger(CreditorResource.class);

    private static final String ENTITY_NAME = "cpiclaimCreditor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditorService creditorService;

    private final CreditorQueryService creditorQueryService;

    public CreditorResource(CreditorService creditorService, CreditorQueryService creditorQueryService) {
        this.creditorService = creditorService;
        this.creditorQueryService = creditorQueryService;
    }

    /**
     * {@code POST  /creditors} : Create a new creditor.
     *
     * @param creditorDTO the creditorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditorDTO, or with status {@code 400 (Bad Request)} if the creditor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/creditors")
    public ResponseEntity<CreditorDTO> createCreditor(@RequestBody CreditorDTO creditorDTO) throws URISyntaxException {
        log.debug("REST request to save Creditor : {}", creditorDTO);
        if (creditorDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CreditorDTO result = creditorService.save(creditorDTO);
        return ResponseEntity.created(new URI("/api/creditors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /creditors} : Updates an existing creditor.
     *
     * @param creditorDTO the creditorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditorDTO,
     * or with status {@code 400 (Bad Request)} if the creditorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/creditors")
    public ResponseEntity<CreditorDTO> updateCreditor(@RequestBody CreditorDTO creditorDTO) throws URISyntaxException {
        log.debug("REST request to update Creditor : {}", creditorDTO);
        if (creditorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CreditorDTO result = creditorService.save(creditorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /creditors} : get all the creditors.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditors in body.
     */
    @GetMapping("/creditors")
    public ResponseEntity<List<CreditorDTO>> getAllCreditors(CreditorCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Creditors by criteria: {}", criteria);
        Page<CreditorDTO> page = creditorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /creditors/count} : count all the creditors.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/creditors/count")
    public ResponseEntity<Long> countCreditors(CreditorCriteria criteria) {
        log.debug("REST request to count Creditors by criteria: {}", criteria);
        return ResponseEntity.ok().body(creditorQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /creditors/:id} : get the "id" creditor.
     *
     * @param id the id of the creditorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/creditors/{id}")
    public ResponseEntity<CreditorDTO> getCreditor(@PathVariable Long id) {
        log.debug("REST request to get Creditor : {}", id);
        Optional<CreditorDTO> creditorDTO = creditorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditorDTO);
    }

    /**
     * {@code DELETE  /creditors/:id} : delete the "id" creditor.
     *
     * @param id the id of the creditorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/creditors/{id}")
    public ResponseEntity<Void> deleteCreditor(@PathVariable Long id) {
        log.debug("REST request to delete Creditor : {}", id);
        creditorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
