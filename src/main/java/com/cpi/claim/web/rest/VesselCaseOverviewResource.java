package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.VesselCaseQueryService;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.service.bean.vesselcase.VesselCaseOverviewBean;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.utility.vesselcase.VesselCaseOverviewUtility;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VesselCase.
 */
@RestController
@RequestMapping("/api")
public class VesselCaseOverviewResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseOverviewResource.class);

    private static final String ENTITY_NAME = "vesselCase";

    private final VesselCaseService vesselCaseService;

    private final VesselCaseQueryService vesselCaseQueryService;

    @Autowired
    private VesselCaseOverviewUtility vesselCaseOverviewUtility;

    public VesselCaseOverviewResource(VesselCaseService vesselCaseService, VesselCaseQueryService vesselCaseQueryService) {
        this.vesselCaseService = vesselCaseService;
        this.vesselCaseQueryService = vesselCaseQueryService;
    }


    @GetMapping("/vessel-cases/overview/{id}")
    @Timed
    public ResponseEntity<VesselCaseOverviewBean> getVesselCaseOverviewBean(@PathVariable("id") Long id) {
        log.debug("REST request to get VesselCases by id: {}", id);
        VesselCaseOverviewBean vesselCaseOverviewBean = vesselCaseOverviewUtility.createVesselCaseOverviewBean(id);
        return new ResponseEntity<>(vesselCaseOverviewBean, HttpStatus.OK);
    }


}
