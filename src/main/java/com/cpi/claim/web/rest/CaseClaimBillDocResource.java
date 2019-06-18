/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-18 上午10:55
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
import com.cpi.claim.service.CaseClaimBillQueryService;
import com.cpi.claim.service.CaseClaimBillService;
import com.cpi.claim.service.utility.bill.doc.ClaimBillDocUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing CaseClaimBill.
 */
@RestController
@RequestMapping("/api")
public class CaseClaimBillDocResource {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDocResource.class);

    private static final String ENTITY_NAME = "caseClaimBill";

    private final CaseClaimBillService caseClaimBillService;

    private final CaseClaimBillQueryService caseClaimBillQueryService;

    @Autowired
    private ClaimBillDocUtility claimBillDocUtility;


    public CaseClaimBillDocResource(CaseClaimBillService caseClaimBillService, CaseClaimBillQueryService caseClaimBillQueryService) {
        this.caseClaimBillService = caseClaimBillService;
        this.caseClaimBillQueryService = caseClaimBillQueryService;
    }


    @GetMapping("/case-claim-bills/doc/{id}/pdf")
    public ResponseEntity<byte[]> getCaseClaimBillDocPDFForId(@PathVariable("id") Long id) {
        log.debug("REST request to get CaseClaimBill Doc PDF For Id: {}", id);
        byte[] bytes = claimBillDocUtility.createCaseClaimBillDocPDF(id);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/pdf"));
        StringBuilder fileName = new StringBuilder();
        fileName.append("attachment; filename=").append("\"").append(claimBillDocUtility.createClaimBillFileName(id)).append("\"");
        header.set(HttpHeaders.CONTENT_DISPOSITION, fileName.toString());

        if (bytes.length > 0) {
            header.setContentLength(bytes.length);
        }

        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }


}
