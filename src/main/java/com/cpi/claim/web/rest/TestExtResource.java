package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import com.cpi.claim.service.VesselCaseExtService;

import com.cpi.claim.service.VesselCaseStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing Member.
 */
@RestController
@RequestMapping("/test")
public class TestExtResource {

    private final Logger log = LoggerFactory.getLogger(TestExtResource.class);

    private static final String ENTITY_NAME = "member";


    private VesselCaseStatisticsService vesselCaseStatisticsService;

    public TestExtResource(VesselCaseStatisticsService vesselCaseStatisticsService) {
        this.vesselCaseStatisticsService = vesselCaseStatisticsService;
    }

    @GetMapping("/statistics/year")
    @Timed
    public void getStatisForYear() {

        vesselCaseStatisticsService.gatherStaticForCaseMonthCount();
    }

    @GetMapping("/statistics/year/{startyear}/{endyear}")
    @Timed
    public ResponseEntity<List<CaseYearCountStatisticsBean>> getStatisForYear(@PathVariable String startyear, @PathVariable String endyear) {
        return new ResponseEntity<>(vesselCaseStatisticsService.findCaseYearStaticsCount(startyear, endyear), HttpStatus.OK);
    }

}
