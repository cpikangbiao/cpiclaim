package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.VesselCaseQueryService;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.service.bean.timeline.TimeLineBean;
import com.cpi.claim.service.bean.vesselcase.VesselCaseOverviewBean;
import com.cpi.claim.service.utility.timeline.VesselCaseTimeLineGenerateUtility;
import com.cpi.claim.service.utility.vesselcase.VesselCaseOverviewUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseTimeLineResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseTimeLineResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    @Autowired
    private VesselCaseTimeLineGenerateUtility vesselCaseTimeLineGenerateUtility;

    @GetMapping("/vessel-cases/time-line/{id}")
    @Timed
    public ResponseEntity<List<TimeLineBean>> getVesselCaseTimeLine(@PathVariable("id") Long id) {
        log.debug("REST request to get getVesselCaseTimeLine by id: {}", id);
        return new ResponseEntity<>(vesselCaseTimeLineGenerateUtility.createVesselCaseTimeLine(id), HttpStatus.OK);
    }


}
