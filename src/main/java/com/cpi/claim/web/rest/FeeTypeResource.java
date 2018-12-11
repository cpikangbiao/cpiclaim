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
import com.cpi.claim.service.FeeTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import com.cpi.claim.service.dto.FeeTypeDTO;
import com.cpi.claim.service.dto.FeeTypeCriteria;
import com.cpi.claim.service.FeeTypeQueryService;
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
 * REST controller for managing FeeType.
 */
@RestController
@RequestMapping("/api")
public class FeeTypeResource {

    private final Logger log = LoggerFactory.getLogger(FeeTypeResource.class);

    private static final String ENTITY_NAME = "feeType";

    private final FeeTypeService feeTypeService;

    private final FeeTypeQueryService feeTypeQueryService;

    public FeeTypeResource(FeeTypeService feeTypeService, FeeTypeQueryService feeTypeQueryService) {
        this.feeTypeService = feeTypeService;
        this.feeTypeQueryService = feeTypeQueryService;
    }

    /**
     * POST  /fee-types : Create a new feeType.
     *
     * @param feeTypeDTO the feeTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new feeTypeDTO, or with status 400 (Bad Request) if the feeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fee-types")
    @Timed
    public ResponseEntity<FeeTypeDTO> createFeeType(@Valid @RequestBody FeeTypeDTO feeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save FeeType : {}", feeTypeDTO);
        if (feeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new feeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeTypeDTO result = feeTypeService.save(feeTypeDTO);
        return ResponseEntity.created(new URI("/api/fee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fee-types : Updates an existing feeType.
     *
     * @param feeTypeDTO the feeTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated feeTypeDTO,
     * or with status 400 (Bad Request) if the feeTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the feeTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fee-types")
    @Timed
    public ResponseEntity<FeeTypeDTO> updateFeeType(@Valid @RequestBody FeeTypeDTO feeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update FeeType : {}", feeTypeDTO);
        if (feeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeTypeDTO result = feeTypeService.save(feeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, feeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fee-types : get all the feeTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of feeTypes in body
     */
    @GetMapping("/fee-types")
    @Timed
    public ResponseEntity<List<FeeTypeDTO>> getAllFeeTypes(FeeTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FeeTypes by criteria: {}", criteria);
        Page<FeeTypeDTO> page = feeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fee-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fee-types/:id : get the "id" feeType.
     *
     * @param id the id of the feeTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feeTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fee-types/{id}")
    @Timed
    public ResponseEntity<FeeTypeDTO> getFeeType(@PathVariable Long id) {
        log.debug("REST request to get FeeType : {}", id);
        Optional<FeeTypeDTO> feeTypeDTO = feeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(feeTypeDTO);
    }

    /**
     * DELETE  /fee-types/:id : delete the "id" feeType.
     *
     * @param id the id of the feeTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fee-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteFeeType(@PathVariable Long id) {
        log.debug("REST request to delete FeeType : {}", id);
        feeTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
