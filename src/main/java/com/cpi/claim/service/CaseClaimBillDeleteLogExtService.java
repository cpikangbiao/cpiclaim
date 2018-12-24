/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
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

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillDeleteLog;
import com.cpi.claim.repository.CaseClaimBillDeleteLogRepository;
import com.cpi.claim.security.SecurityUtils;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillDeleteLogMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@Transactional
public class CaseClaimBillDeleteLogExtService extends QueryService<CaseClaimBillDeleteLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillDeleteLogExtService.class);

    private final CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository;

    private final CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper;

    public CaseClaimBillDeleteLogExtService(CaseClaimBillDeleteLogRepository caseClaimBillDeleteLogRepository, CaseClaimBillDeleteLogMapper caseClaimBillDeleteLogMapper) {
        this.caseClaimBillDeleteLogRepository = caseClaimBillDeleteLogRepository;
        this.caseClaimBillDeleteLogMapper = caseClaimBillDeleteLogMapper;
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimBillDeleteLogDTO> findAll(Pageable page) {
        log.debug("find by page : {}", page);
        return caseClaimBillDeleteLogRepository.findAll(page)
            .map(caseClaimBillDeleteLogMapper::toDto);
    }


    @Transactional
    public void saveBillPrintLog(CaseClaimBill caseClaimBill, String operateType) {
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = new  CaseClaimBillDeleteLog();

        caseClaimBillDeleteLog.setClaimBillCode(caseClaimBill.getClaimBillCode());

        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            caseClaimBillDeleteLog.setOperateUser(SecurityUtils.getCurrentUserLogin().get());
        } else {
            caseClaimBillDeleteLog.setOperateUser("Error User");
        }

        caseClaimBillDeleteLog.setOperateType(operateType);
        caseClaimBillDeleteLog.setOperateDate(Instant.now());

        caseClaimBillDeleteLogRepository.save(caseClaimBillDeleteLog);
    }
}
