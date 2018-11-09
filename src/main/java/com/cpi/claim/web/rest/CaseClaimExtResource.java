package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.CaseClaimExtService;
import com.cpi.claim.service.CaseClaimService;
import com.cpi.claim.service.dto.CaseClaimDTO;
import com.cpi.claim.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing CaseClaim.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimExtResource.class);

    private static final String ENTITY_NAME = "caseClaim";

    private final CaseClaimService caseClaimService;

    private final CaseClaimExtService caseClaimExtService;

    public CaseClaimExtResource(CaseClaimService caseClaimService, CaseClaimExtService caseClaimExtService) {
        this.caseClaimService = caseClaimService;
        this.caseClaimExtService = caseClaimExtService;
    }


    @GetMapping("/case-claims/by-vessel-case/{id}")
    @Timed
    public ResponseEntity<List> getCaseClaimForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get CaseClaim For VesselCaseId(: {}", id);
        List<CaseClaimDTO> caseClaimDTOs = caseClaimExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseClaimDTOs, HttpStatus.OK);
    }

    @GetMapping("/case-claims/by-vessel-case/page")
    @Timed
    public ResponseEntity<List<CaseClaimDTO>> getAllCaseClaims(Long id, Pageable pageable) {
        log.debug("REST request to get CaseClaim For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseClaimDTO> page = caseClaimExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-claims/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

//    @GetMapping("/case-claims/max-number-id/{id}")
//    @Timed
//    public ResponseEntity<Integer> getMaxNumberIdForVesselCaseId(@PathVariable Long id) {
//        log.debug("REST request to get CaseClaim For VesselCaseId(: {}", id);
//        Integer maxNumberId = caseClaimQueryExtService.findMaxNumberIdBySubCaseId(id);
//        return new ResponseEntity<>(maxNumberId, HttpStatus.OK);
//    }


}
