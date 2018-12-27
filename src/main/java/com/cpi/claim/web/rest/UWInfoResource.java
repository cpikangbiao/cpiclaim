/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-27 上午10:05
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
import com.cpi.claim.service.UWInfoService;
import com.cpi.share.uw.insuredvessel.InsuredVesselInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class UWInfoResource {

    private final Logger log = LoggerFactory.getLogger(UWInfoResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    private final UWInfoService uwInfoService;

    public UWInfoResource(UWInfoService uwInfoService) {
        this.uwInfoService = uwInfoService;
    }

    @GetMapping("/get-uw-info/get-iv-info")
    @Timed
    public ResponseEntity<InsuredVesselInfo> getInsuredVesselInfo(Long vesselCaseId) throws URISyntaxException {
        log.debug("REST request to get  InsuredVesselInfo For VesselCase for id : {} ", vesselCaseId);
        return ResponseEntity.ok()
            .body(uwInfoService.getInsuredVesselInfo(vesselCaseId));
    }

    @GetMapping("/get-uw-info/get-iv-certificate-version/html/{ivCertificateVersionId}")
    @Timed
    public ResponseEntity<byte[]> getHTMLFileForCertificate(@PathVariable Long ivCertificateVersionId) throws URISyntaxException {
        log.debug("REST request to get HTML File For Certificate For ivCertificateVersionId: {} ", ivCertificateVersionId);
        return ResponseEntity.ok()
            .body(uwInfoService.getHTMLFileForCertificate(ivCertificateVersionId));
    }

}