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
import com.cpi.claim.service.CaseEstimateExtService;
import com.cpi.claim.service.CaseEstimateQueryService;
import com.cpi.claim.service.CaseEstimateService;
import com.cpi.claim.service.dto.CaseEstimateCriteria;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CaseEstimate.
 */
@RestController
@RequestMapping("/api")
public class CaseEstimateExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseEstimateExtResource.class);

    private static final String ENTITY_NAME = "caseEstimate";

    private final CaseEstimateService caseEstimateService;

    private final CaseEstimateQueryService caseEstimateQueryService;

    @Autowired
    private CaseEstimateExtService caseEstimateExtService;

    public CaseEstimateExtResource(CaseEstimateService caseEstimateService, CaseEstimateQueryService caseEstimateQueryService) {
        this.caseEstimateService = caseEstimateService;
        this.caseEstimateQueryService = caseEstimateQueryService;
    }

    @GetMapping("/case-estimates/by-vessel-case/{id}")
    public ResponseEntity<List> getCaseEstimateForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseEstimateDTO> caseEstimateDTOS = caseEstimateExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseEstimateDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-estimates/by-vessel-case/page")
    public ResponseEntity<List<CaseEstimateDTO>> getAllCaseEstimates(Long id, Pageable pageable) {
        log.debug("REST request to get CaseEstimate For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseEstimateDTO> page = caseEstimateExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-estimates/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
