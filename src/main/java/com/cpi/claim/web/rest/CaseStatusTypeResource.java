package com.cpi.claim.web.rest;

import com.cpi.claim.service.CaseStatusTypeService;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;
import com.cpi.claim.service.dto.CaseStatusTypeCriteria;
import com.cpi.claim.service.CaseStatusTypeQueryService;

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
 * REST controller for managing {@link com.cpi.claim.domain.CaseStatusType}.
 */
@RestController
@RequestMapping("/api")
public class CaseStatusTypeResource {

    private final Logger log = LoggerFactory.getLogger(CaseStatusTypeResource.class);

    private static final String ENTITY_NAME = "cpiclaimCaseStatusType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaseStatusTypeService caseStatusTypeService;

    private final CaseStatusTypeQueryService caseStatusTypeQueryService;

    public CaseStatusTypeResource(CaseStatusTypeService caseStatusTypeService, CaseStatusTypeQueryService caseStatusTypeQueryService) {
        this.caseStatusTypeService = caseStatusTypeService;
        this.caseStatusTypeQueryService = caseStatusTypeQueryService;
    }

    /**
     * {@code POST  /case-status-types} : Create a new caseStatusType.
     *
     * @param caseStatusTypeDTO the caseStatusTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caseStatusTypeDTO, or with status {@code 400 (Bad Request)} if the caseStatusType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/case-status-types")
    public ResponseEntity<CaseStatusTypeDTO> createCaseStatusType(@Valid @RequestBody CaseStatusTypeDTO caseStatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CaseStatusType : {}", caseStatusTypeDTO);
        if (caseStatusTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new caseStatusType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaseStatusTypeDTO result = caseStatusTypeService.save(caseStatusTypeDTO);
        return ResponseEntity.created(new URI("/api/case-status-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /case-status-types} : Updates an existing caseStatusType.
     *
     * @param caseStatusTypeDTO the caseStatusTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caseStatusTypeDTO,
     * or with status {@code 400 (Bad Request)} if the caseStatusTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caseStatusTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/case-status-types")
    public ResponseEntity<CaseStatusTypeDTO> updateCaseStatusType(@Valid @RequestBody CaseStatusTypeDTO caseStatusTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CaseStatusType : {}", caseStatusTypeDTO);
        if (caseStatusTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CaseStatusTypeDTO result = caseStatusTypeService.save(caseStatusTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caseStatusTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /case-status-types} : get all the caseStatusTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caseStatusTypes in body.
     */
    @GetMapping("/case-status-types")
    public ResponseEntity<List<CaseStatusTypeDTO>> getAllCaseStatusTypes(CaseStatusTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get CaseStatusTypes by criteria: {}", criteria);
        Page<CaseStatusTypeDTO> page = caseStatusTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /case-status-types/count} : count all the caseStatusTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/case-status-types/count")
    public ResponseEntity<Long> countCaseStatusTypes(CaseStatusTypeCriteria criteria) {
        log.debug("REST request to count CaseStatusTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(caseStatusTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /case-status-types/:id} : get the "id" caseStatusType.
     *
     * @param id the id of the caseStatusTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caseStatusTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/case-status-types/{id}")
    public ResponseEntity<CaseStatusTypeDTO> getCaseStatusType(@PathVariable Long id) {
        log.debug("REST request to get CaseStatusType : {}", id);
        Optional<CaseStatusTypeDTO> caseStatusTypeDTO = caseStatusTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caseStatusTypeDTO);
    }

    /**
     * {@code DELETE  /case-status-types/:id} : delete the "id" caseStatusType.
     *
     * @param id the id of the caseStatusTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/case-status-types/{id}")
    public ResponseEntity<Void> deleteCaseStatusType(@PathVariable Long id) {
        log.debug("REST request to delete CaseStatusType : {}", id);
        caseStatusTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
