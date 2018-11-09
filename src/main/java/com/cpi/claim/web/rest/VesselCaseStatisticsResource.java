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
