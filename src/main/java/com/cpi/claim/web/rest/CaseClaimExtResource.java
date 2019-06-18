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
    public ResponseEntity<List> getCaseClaimForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get CaseClaim For VesselCaseId(: {}", id);
        List<CaseClaimDTO> caseClaimDTOs = caseClaimExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseClaimDTOs, HttpStatus.OK);
    }

    @GetMapping("/case-claims/by-vessel-case/page")
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
