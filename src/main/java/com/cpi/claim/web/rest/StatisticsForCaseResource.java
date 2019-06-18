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

import com.cpi.claim.service.utility.statistics.StatisticsForCaseService;
import com.cpi.claim.web.rest.bean.StatisticsForCaseBean;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
