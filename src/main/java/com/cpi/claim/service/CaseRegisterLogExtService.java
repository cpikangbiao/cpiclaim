/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 下午3:21
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

import com.cpi.claim.domain.CaseRegisterLog;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.CaseRegisterLogRepository;
import com.cpi.claim.security.SecurityUtils;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;
import com.cpi.claim.service.mapper.CaseRegisterLogMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional(readOnly = true)
public class CaseRegisterLogExtService extends QueryService<CaseRegisterLog> {

    private final Logger log = LoggerFactory.getLogger(CaseRegisterLogExtService.class);

    private final CaseRegisterLogRepository caseRegisterLogRepository;

    private final CaseRegisterLogMapper caseRegisterLogMapper;

    public CaseRegisterLogExtService(CaseRegisterLogRepository caseRegisterLogRepository, CaseRegisterLogMapper caseRegisterLogMapper) {
        this.caseRegisterLogRepository = caseRegisterLogRepository;
        this.caseRegisterLogMapper = caseRegisterLogMapper;
    }

    @Transactional(readOnly = true)
    public Page<CaseRegisterLogDTO> findAllByVesselCase(Long vesselCaseId, Pageable page) {
        return caseRegisterLogRepository.findAllByVesselCaseIdOrderByAssignTimeDesc(vesselCaseId, page)
            .map(caseRegisterLogMapper::toDto);
    }

    @Transactional
    public void saveCaseRegisterLog(VesselCase vesselCase, String registeredUser) {
        CaseRegisterLog caseRegisterLog = new CaseRegisterLog();


        caseRegisterLog.setNumberId("");
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            caseRegisterLog.setAssignUser(SecurityUtils.getCurrentUserLogin().get());
        } else {
            caseRegisterLog.setAssignUser("Error User");
        }
        caseRegisterLog.setAssignTime(Instant.now());
        caseRegisterLog.setVesselCase(vesselCase);
        caseRegisterLog.setAssignedUser(registeredUser);

        caseRegisterLogRepository.save(caseRegisterLog);
    }


}
