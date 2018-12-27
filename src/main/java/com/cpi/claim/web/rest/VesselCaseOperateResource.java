/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 下午2:20
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
import com.cpi.claim.service.VesselCaseCheckService;
import com.cpi.claim.service.VesselCaseOperateService;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseOperateResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseOperateResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    private final VesselCaseOperateService vesselCaseOperateService;

    @Autowired
    private VesselCaseCheckService vesselCaseCheckService;

    @Autowired
    private VesselCaseService vesselCaseService;

    public VesselCaseOperateResource(VesselCaseOperateService vesselCaseOperateService) {
        this.vesselCaseOperateService = vesselCaseOperateService;
    }

    @PutMapping("/vessel-cases/close-case/{id}")
    @Timed
    public ResponseEntity<VesselCaseDTO> closeCase(@PathVariable("id") Long id) throws URISyntaxException {
        log.debug("REST request to close Case for id : {}", id);

        if ( ! vesselCaseCheckService.checkEstimateIsZero(id)) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if ( ! vesselCaseCheckService.checkGuaranteeNoCancelDate(id)) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        vesselCaseOperateService.closeCase(id);

        return ResponseEntity.ok()
            .body(vesselCaseService.findOne(id).get());
    }

    @PutMapping("/vessel-cases/reopen-case/{id}")
    @Timed
    public ResponseEntity<VesselCaseDTO> reopenCase(@PathVariable("id") Long id) throws URISyntaxException {
        log.debug("REST request to close Case for id : {}", id);

        vesselCaseOperateService.reopenCase(id);

        return ResponseEntity.ok()
            .body(vesselCaseService.findOne(id).get());
    }

    @PutMapping("/vessel-cases/change-registered-handler/{id}/{registeredHandlerId}")
    @Timed
    public ResponseEntity<VesselCaseDTO> changeRegisteredHandler(
        @PathVariable("id") Long id,
        @PathVariable("registeredHandlerId") Long registeredHandlerId) throws URISyntaxException {
        log.debug("REST request to close Case for id : {}", id);

        vesselCaseOperateService.changeCaseRegisteredHandler(id, registeredHandlerId);

        return ResponseEntity.ok()
            .body(vesselCaseService.findOne(id).get());
    }

    @PutMapping("/vessel-cases/change-assigned-handler/{id}/{assignedHandlerId}")
    @Timed
    public ResponseEntity<VesselCaseDTO> changeCaseAssignedHandler(
        @PathVariable("id") Long id,
        @PathVariable("assignedHandlerId") Long assignedHandlerId) throws URISyntaxException {
        log.debug("REST request to close Case for id : {}", id);

        vesselCaseOperateService.changeCaseAssignedHandler(id, assignedHandlerId);

        return ResponseEntity.ok()
            .body(vesselCaseService.findOne(id).get());
    }

}