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

import com.cpi.claim.service.GuaranteeTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.GuaranteeTypeDTO;
import com.cpi.claim.service.dto.GuaranteeTypeCriteria;
import com.cpi.claim.service.GuaranteeTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.GuaranteeType}.
 */
@RestController
@RequestMapping("/api")
public class GuaranteeTypeResource {

    private final Logger log = LoggerFactory.getLogger(GuaranteeTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimGuaranteeType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GuaranteeTypeService guaranteeTypeService;

    private final GuaranteeTypeQueryService guaranteeTypeQueryService;

    public GuaranteeTypeResource(GuaranteeTypeService guaranteeTypeService, GuaranteeTypeQueryService guaranteeTypeQueryService) {
        this.guaranteeTypeService = guaranteeTypeService;
        this.guaranteeTypeQueryService = guaranteeTypeQueryService;
    }

    /**
     * {@code POST  /guarantee-types} : Create a new guaranteeType.
     *
     * @param guaranteeTypeDTO the guaranteeTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new guaranteeTypeDTO, or with status {@code 400 (Bad Request)} if the guaranteeType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/guarantee-types")
    public ResponseEntity<GuaranteeTypeDTO> createGuaranteeType(@Valid @RequestBody GuaranteeTypeDTO guaranteeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save GuaranteeType : {}", guaranteeTypeDTO);
        if (guaranteeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new guaranteeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GuaranteeTypeDTO result = guaranteeTypeService.save(guaranteeTypeDTO);
        return ResponseEntity.created(new URI("/api/guarantee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /guarantee-types} : Updates an existing guaranteeType.
     *
     * @param guaranteeTypeDTO the guaranteeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated guaranteeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the guaranteeTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the guaranteeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/guarantee-types")
    public ResponseEntity<GuaranteeTypeDTO> updateGuaranteeType(@Valid @RequestBody GuaranteeTypeDTO guaranteeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update GuaranteeType : {}", guaranteeTypeDTO);
        if (guaranteeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GuaranteeTypeDTO result = guaranteeTypeService.save(guaranteeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, guaranteeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /guarantee-types} : get all the guaranteeTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of guaranteeTypes in body.
     */
    @GetMapping("/guarantee-types")
    public ResponseEntity<List<GuaranteeTypeDTO>> getAllGuaranteeTypes(GuaranteeTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get GuaranteeTypes by criteria: {}", criteria);
        Page<GuaranteeTypeDTO> page = guaranteeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /guarantee-types/count} : count all the guaranteeTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/guarantee-types/count")
    public ResponseEntity<Long> countGuaranteeTypes(GuaranteeTypeCriteria criteria) {
        log.debug("REST request to count GuaranteeTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(guaranteeTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /guarantee-types/:id} : get the "id" guaranteeType.
     *
     * @param id the id of the guaranteeTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the guaranteeTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/guarantee-types/{id}")
    public ResponseEntity<GuaranteeTypeDTO> getGuaranteeType(@PathVariable Long id) {
        log.debug("REST request to get GuaranteeType : {}", id);
        Optional<GuaranteeTypeDTO> guaranteeTypeDTO = guaranteeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(guaranteeTypeDTO);
    }

    /**
     * {@code DELETE  /guarantee-types/:id} : delete the "id" guaranteeType.
     *
     * @param id the id of the guaranteeTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/guarantee-types/{id}")
    public ResponseEntity<Void> deleteGuaranteeType(@PathVariable Long id) {
        log.debug("REST request to delete GuaranteeType : {}", id);
        guaranteeTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
