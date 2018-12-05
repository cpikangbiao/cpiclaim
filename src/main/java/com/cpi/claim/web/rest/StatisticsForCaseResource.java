package com.cpi.claim.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.service.VesselSubCaseExtService;
import com.cpi.claim.service.VesselSubCaseQueryService;
import com.cpi.claim.service.VesselSubCaseService;
import com.cpi.claim.service.dto.VesselSubCaseCriteria;
import com.cpi.claim.service.dto.VesselSubCaseDTO;
import com.cpi.claim.service.utility.generate.SubCaseCodeGenerateUtility;
import com.cpi.claim.service.utility.statistics.StatisticsForCaseService;
import com.cpi.claim.web.rest.bean.StatisticsForCaseBean;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing VesselSubCase.
 */
@RestController
@RequestMapping("/api")
public class StatisticsForCaseResource {

    private final Logger log = LoggerFactory.getLogger(StatisticsForCaseResource.class);

    @Autowired
    private StatisticsForCaseService statisticsForCaseService;


//    @GetMapping("/statistics/case-by-member/{fromYear}/{endYear}/{companyId}/{cpiInsuranceTypeId}/{caseStatusId}/{caseDateFrom}/{caseDateTo}/{registeredDateFrom}/{registeredDateTo}/{closeDateFrom}/{closeDateTo}/{language}/{operateType}/pdf")
    @GetMapping("/statistics/case-by-member/pdf")
    @Timed
    public ResponseEntity<byte[]> getStatisticsPDFFileForCaseByMember(StatisticsForCaseBean statisticsForCaseBean) {
        log.debug("REST request to get getStatisticsPDFFileForCaseByMember pdf ");
        byte[] bytes = statisticsForCaseService.createCaseStatsPDFFile(
            statisticsForCaseBean.getFromYear(),
            statisticsForCaseBean.getEndYear(),
            statisticsForCaseBean.getCompanyId(),
            statisticsForCaseBean.getCpiInsuranceTypeId(),
            statisticsForCaseBean.getCaseStatusId(),
            statisticsForCaseBean.getCaseDateFrom(),
            statisticsForCaseBean.getCaseDateTo(),
            statisticsForCaseBean.getRegisteredDateFrom(),
            statisticsForCaseBean.getRegisteredDateTo(),
            statisticsForCaseBean.getCloseDateFrom(),
            statisticsForCaseBean.getCloseDateTo(),
            statisticsForCaseBean.getLanguage(),
            statisticsForCaseBean.getOperateType()
        );

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/pdf"));
        StringBuilder fileName = new StringBuilder();
        fileName.append("attachment; filename=").append("\"").append(
            statisticsForCaseService.createCaseStatsPDFFileName(
                statisticsForCaseBean.getFromYear(),
                statisticsForCaseBean.getEndYear(),
                statisticsForCaseBean.getCompanyId())).append("\"");
        header.set(HttpHeaders.CONTENT_DISPOSITION, fileName.toString());

        if (bytes != null) {
            header.setContentLength(bytes.length);
        }

        return new ResponseEntity<>(bytes, header, HttpStatus.OK);
    }
}
