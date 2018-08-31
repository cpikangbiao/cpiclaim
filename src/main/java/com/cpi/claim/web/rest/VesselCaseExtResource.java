package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import com.cpi.claim.service.VesselCaseExtService;
import com.cpi.claim.service.VesselCaseQueryService;
import com.cpi.claim.service.VesselCaseService;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.web.rest.errors.BadRequestAlertException;
import com.cpi.claim.web.rest.util.HeaderUtil;
import com.cpi.claim.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class VesselCaseExtResource {

    private final Logger log = LoggerFactory.getLogger(VesselCaseExtResource.class);

    private static final String ENTITYME = "vesselCase";

    private final VesselCaseExtService vesselCaseExtService;

    public VesselCaseExtResource(VesselCaseExtService vesselCaseExtService) {
        this.vesselCaseExtService = vesselCaseExtService;
    }

    @GetMapping("/vessel-cases/statistics/{startyear}/{endyear}/{startmonth}/{endmonth}/month")
    @Timed
    public ResponseEntity<List<CaseMonthCountStatisticsBean>> getStatisForMonth(
        @PathVariable String startyear,
        @PathVariable String endyear,
        @PathVariable String startmonth,
        @PathVariable String endmonth ) {
        return new ResponseEntity<>(vesselCaseExtService.findCasMonthStaticsCount(startyear, endyear, startmonth, endmonth), HttpStatus.OK);
    }

    @GetMapping("/vessel-cases/statistics/{startyear}/{endyear}/year")
    @Timed
    public ResponseEntity<List<CaseYearCountStatisticsBean>> getStatisForYear(
        @PathVariable String startyear,
        @PathVariable String endyear) {
        return new ResponseEntity<>(vesselCaseExtService.findCaseYearStaticsCount(startyear, endyear), HttpStatus.OK);
    }
}
