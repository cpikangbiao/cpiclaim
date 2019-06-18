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

import com.cpi.claim.service.ClaimBillFinanceTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeCriteria;
import com.cpi.claim.service.ClaimBillFinanceTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.ClaimBillFinanceType}.
 */
@RestController
@RequestMapping("/api")
public class ClaimBillFinanceTypeResource {

    private final Logger log = LoggerFactory.getLogger(ClaimBillFinanceTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimClaimBillFinanceType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClaimBillFinanceTypeService claimBillFinanceTypeService;

    private final ClaimBillFinanceTypeQueryService claimBillFinanceTypeQueryService;

    public ClaimBillFinanceTypeResource(ClaimBillFinanceTypeService claimBillFinanceTypeService, ClaimBillFinanceTypeQueryService claimBillFinanceTypeQueryService) {
        this.claimBillFinanceTypeService = claimBillFinanceTypeService;
        this.claimBillFinanceTypeQueryService = claimBillFinanceTypeQueryService;
    }

    /**
     * {@code POST  /claim-bill-finance-types} : Create a new claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the claimBillFinanceTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new claimBillFinanceTypeDTO, or with status {@code 400 (Bad Request)} if the claimBillFinanceType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/claim-bill-finance-types")
    public ResponseEntity<ClaimBillFinanceTypeDTO> createClaimBillFinanceType(@Valid @RequestBody ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ClaimBillFinanceType : {}", claimBillFinanceTypeDTO);
        if (claimBillFinanceTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new claimBillFinanceType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClaimBillFinanceTypeDTO result = claimBillFinanceTypeService.save(claimBillFinanceTypeDTO);
        return ResponseEntity.created(new URI("/api/claim-bill-finance-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /claim-bill-finance-types} : Updates an existing claimBillFinanceType.
     *
     * @param claimBillFinanceTypeDTO the claimBillFinanceTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated claimBillFinanceTypeDTO,
     * or with status {@code 400 (Bad Request)} if the claimBillFinanceTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the claimBillFinanceTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/claim-bill-finance-types")
    public ResponseEntity<ClaimBillFinanceTypeDTO> updateClaimBillFinanceType(@Valid @RequestBody ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ClaimBillFinanceType : {}", claimBillFinanceTypeDTO);
        if (claimBillFinanceTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClaimBillFinanceTypeDTO result = claimBillFinanceTypeService.save(claimBillFinanceTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, claimBillFinanceTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /claim-bill-finance-types} : get all the claimBillFinanceTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of claimBillFinanceTypes in body.
     */
    @GetMapping("/claim-bill-finance-types")
    public ResponseEntity<List<ClaimBillFinanceTypeDTO>> getAllClaimBillFinanceTypes(ClaimBillFinanceTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ClaimBillFinanceTypes by criteria: {}", criteria);
        Page<ClaimBillFinanceTypeDTO> page = claimBillFinanceTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /claim-bill-finance-types/count} : count all the claimBillFinanceTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/claim-bill-finance-types/count")
    public ResponseEntity<Long> countClaimBillFinanceTypes(ClaimBillFinanceTypeCriteria criteria) {
        log.debug("REST request to count ClaimBillFinanceTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(claimBillFinanceTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /claim-bill-finance-types/:id} : get the "id" claimBillFinanceType.
     *
     * @param id the id of the claimBillFinanceTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the claimBillFinanceTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/claim-bill-finance-types/{id}")
    public ResponseEntity<ClaimBillFinanceTypeDTO> getClaimBillFinanceType(@PathVariable Long id) {
        log.debug("REST request to get ClaimBillFinanceType : {}", id);
        Optional<ClaimBillFinanceTypeDTO> claimBillFinanceTypeDTO = claimBillFinanceTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(claimBillFinanceTypeDTO);
    }

    /**
     * {@code DELETE  /claim-bill-finance-types/:id} : delete the "id" claimBillFinanceType.
     *
     * @param id the id of the claimBillFinanceTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/claim-bill-finance-types/{id}")
    public ResponseEntity<Void> deleteClaimBillFinanceType(@PathVariable Long id) {
        log.debug("REST request to delete ClaimBillFinanceType : {}", id);
        claimBillFinanceTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
