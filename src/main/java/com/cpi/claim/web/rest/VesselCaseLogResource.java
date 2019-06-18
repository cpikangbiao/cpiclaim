/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 下午4:29
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
import com.cpi.claim.service.CaseAssignLogExtService;
import com.cpi.claim.service.CaseCloseLogExtService;
import com.cpi.claim.service.CaseRegisterLogExtService;
import com.cpi.claim.service.VesselCaseOperateService;
import com.cpi.claim.service.dto.CaseAssignLogDTO;
import com.cpi.claim.service.dto.CaseCloseLogDTO;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseLogResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseLogResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    private final VesselCaseOperateService vesselCaseOperateService;

    @Autowired
    private CaseCloseLogExtService caseCloseLogExtService;

    @Autowired
    private CaseAssignLogExtService caseAssignLogExtService;

    @Autowired
    private CaseRegisterLogExtService caseRegisterLogExtService;

    public VesselCaseLogResource(VesselCaseOperateService vesselCaseOperateService) {
        this.vesselCaseOperateService = vesselCaseOperateService;
    }

    @GetMapping("/vessel-cases/log/get-close-case-logs")
    public ResponseEntity<Page<CaseCloseLogDTO>> getCloseLogsForVesselCase(
        Long id, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get CloseLogs For VesselCase for id : {} pageable : {}", id, pageable);
        return ResponseEntity.ok()
            .body(caseCloseLogExtService.findAllByVesselCase(id, pageable));
    }

    @GetMapping("/vessel-cases/log/get-assign-case-logs")
    public ResponseEntity<Page<CaseAssignLogDTO>> getCaseAssignLogsForVesselCase(
         Long id, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get CloseLogs For VesselCase for id : {} pageable : {}", id, pageable);
        return ResponseEntity.ok()
            .body(caseAssignLogExtService.findAllByVesselCase(id, pageable));
    }

    @GetMapping("/vessel-cases/log/get-register-case-logs")
    public ResponseEntity<Page<CaseRegisterLogDTO>> getCaseRegisterLogsForVesselCase(
        Long id, Pageable pageable) throws URISyntaxException {
        log.debug("REST request to get CloseLogs For VesselCase for id : {} pageable : {}", id, pageable);
        return ResponseEntity.ok()
            .body(caseRegisterLogExtService.findAllByVesselCase(id, pageable));
    }

}
