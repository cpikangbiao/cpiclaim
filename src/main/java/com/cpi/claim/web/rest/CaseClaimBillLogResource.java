/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-21 上午9:10
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
import com.cpi.claim.service.CaseClaimBillApprovalLogExtService;
import com.cpi.claim.service.CaseClaimBillPrintLogExtService;
import com.cpi.claim.service.CaseClaimBillQueryService;
import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing CaseClaimBill.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillLogResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillLogResource.class);

    private static final String ENTITY_NAME = "caseClaimBill";

    private final CaseClaimBillService caseClaimBillService;

    private final CaseClaimBillQueryService caseClaimBillQueryService;

    @Autowired
    private CaseClaimBillApprovalLogExtService caseClaimBillApprovalLogExtService ;

    @Autowired
    private CaseClaimBillPrintLogExtService caseClaimBillPrintLogExtService;


    public CaseClaimBillLogResource(CaseClaimBillService caseClaimBillService, CaseClaimBillQueryService caseClaimBillQueryService) {
        this.caseClaimBillService = caseClaimBillService;
        this.caseClaimBillQueryService = caseClaimBillQueryService;
    }


    @GetMapping("/case-claim-bills/log/get-approval-log/{id}")
    @Timed
    public ResponseEntity<Page<CaseClaimBillApprovalLogDTO>> getClaimBillApprovalLogsForcaseClaimBillId(
        @PathVariable Long id,
        @RequestParam("page") Pageable page) {
        log.debug("REST request to get ClaimBill Approval Logs For caseClaimBillId(: {} page: {}", id, page);
        Page<CaseClaimBillApprovalLogDTO> caseClaimBillApprovalLogDTOS = caseClaimBillApprovalLogExtService.findAllByCaseClaimBill(id, page);
        return new ResponseEntity<>(caseClaimBillApprovalLogDTOS, HttpStatus.OK);
    }

    @GetMapping("/case-claim-bills/log/get-print-log/{id}")
    @Timed
    public ResponseEntity<Page<CaseClaimBillPrintLogDTO>> getClaimBillPrintlLogsForcaseClaimBillId(
        @PathVariable Long id,
        @RequestParam("page") Pageable page) {
        log.debug("REST request to get ClaimBill Print Logs For caseClaimBillId(: {}", id);
        Page<CaseClaimBillPrintLogDTO> caseClaimBillPrintLogDTOS = caseClaimBillPrintLogExtService.findAllByCaseClaimBill(id, page);
        return new ResponseEntity<>(caseClaimBillPrintLogDTOS, HttpStatus.OK);
    }


}
