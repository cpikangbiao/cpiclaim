/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 下午2:29
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

package com.cpi.claim.service;

import com.cpi.claim.domain.CaseCloseLog;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseCloseLogRepository;
import com.cpi.claim.security.SecurityUtils;
import com.cpi.claim.service.mapper.CaseCloseLogMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@Transactional
public class CaseCloseLogExtService extends QueryService<CaseCloseLog> {

    private final Logger log = LoggerFactory.getLogger(CaseCloseLogExtService.class);

    private final CaseCloseLogRepository caseCloseLogRepository;

    private final CaseCloseLogMapper caseCloseLogMapper;


    public CaseCloseLogExtService(CaseCloseLogRepository caseCloseLogRepository, CaseCloseLogMapper caseCloseLogMapper) {
        this.caseCloseLogRepository = caseCloseLogRepository;
        this.caseCloseLogMapper = caseCloseLogMapper;
    }

    @Transactional
    public void saveCaseCloseLog(VesselCase vesselCase, String operateType) {
        CaseCloseLog caseCloseLog = new CaseCloseLog();

        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            caseCloseLog.setOperateUserId(SecurityUtils.getCurrentUserLogin().get());
        } else {
            caseCloseLog.setOperateUserId("Error User");
        }

        caseCloseLog.setVesselCase(vesselCase);

        caseCloseLog.setOperateDate(Instant.now());
        caseCloseLog.setOperateType(operateType);
        caseCloseLog.setVesselCase(new VesselCase());

        caseCloseLogRepository.save(caseCloseLog);
    }



}
