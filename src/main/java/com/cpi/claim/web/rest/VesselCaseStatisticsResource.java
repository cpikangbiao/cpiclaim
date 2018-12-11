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

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import com.cpi.claim.service.VesselCaseExtService;
import com.cpi.claim.service.VesselCaseStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseStatisticsResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseStatisticsResource.class);

    private static final String ENTITYME = "vesselCase";

    private final VesselCaseStatisticsService vesselCaseStatisticsService;

    public VesselCaseStatisticsResource(VesselCaseStatisticsService vesselCaseStatisticsService) {
        this.vesselCaseStatisticsService = vesselCaseStatisticsService;
    }

    @GetMapping("/vessel-cases/statistics/{startyear}/{endyear}/{startmonth}/{endmonth}/month")
    @Timed
    public ResponseEntity<List<CaseMonthCountStatisticsBean>> getStatisForMonth(
        @PathVariable String startyear,
        @PathVariable String endyear,
        @PathVariable String startmonth,
        @PathVariable String endmonth ) {
        return new ResponseEntity<>(vesselCaseStatisticsService.findCasMonthStaticsCount(startyear, endyear, startmonth, endmonth), HttpStatus.OK);
    }

    @GetMapping("/vessel-cases/statistics/{startyear}/{endyear}/year")
    @Timed
    public ResponseEntity<List<CaseYearCountStatisticsBean>> getStatisForYear(
        @PathVariable String startyear,
        @PathVariable String endyear) {
        return new ResponseEntity<>(vesselCaseStatisticsService.findCaseYearStaticsCount(startyear, endyear), HttpStatus.OK);
    }
}
