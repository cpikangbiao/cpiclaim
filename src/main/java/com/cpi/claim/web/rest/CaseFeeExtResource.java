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

import com.cpi.claim.service.CaseFeeExtService;
import com.cpi.claim.service.CaseFeeQueryService;
import com.cpi.claim.service.CaseFeeService;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing CaseFee.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeExtResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeExtResource.class);

    private static final String ENTITY_NAME = "caseFee";

    private final CaseFeeService caseFeeService;

    private final CaseFeeQueryService caseFeeQueryService;

    @Autowired
    private CaseFeeExtService caseFeeExtService;

    public CaseFeeExtResource(CaseFeeService caseFeeService, CaseFeeQueryService caseFeeQueryService) {
        this.caseFeeService = caseFeeService;
        this.caseFeeQueryService = caseFeeQueryService;
    }


    @GetMapping("/case-fees/by-vessel-case/{id}")
    public ResponseEntity<List> getCaseFeeForVesselCaseId(@PathVariable Long id) {
        log.debug("REST request to get caseFee For VesselCaseId(: {}", id);
        List<CaseFeeDTO> caseFeeDTOS = caseFeeExtService.findAllByVesselCaseId(id);
        return new ResponseEntity<>(caseFeeDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-fees/by-vessel-case/page")
    public ResponseEntity<List<CaseFeeDTO>> getAllcaseFees(Long id, Pageable pageable) {
        log.debug("REST request to get caseFee For VesselCaseId: {} pageable: {}", id, pageable);
        Page<CaseFeeDTO> page = caseFeeExtService.findAllByVesselCaseId(id, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/case-fees/by-vessel-case/page");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
