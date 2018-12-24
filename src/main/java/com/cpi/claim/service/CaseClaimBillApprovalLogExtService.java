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
import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import com.cpi.claim.repository.CaseClaimBillApprovalLogRepository;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.security.SecurityUtils;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;
import com.cpi.claim.service.mapper.CaseClaimBillApprovalLogMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class CaseClaimBillApprovalLogExtService extends QueryService<CaseClaimBillApprovalLog> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillApprovalLogExtService.class);

    private final CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository;

    private final CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper;

    @Autowired
    private CaseClaimBillRepository caseClaimBillRepository;

    public CaseClaimBillApprovalLogExtService(
        CaseClaimBillApprovalLogRepository caseClaimBillApprovalLogRepository,
        CaseClaimBillApprovalLogMapper caseClaimBillApprovalLogMapper) {
        this.caseClaimBillApprovalLogRepository = caseClaimBillApprovalLogRepository;
        this.caseClaimBillApprovalLogMapper = caseClaimBillApprovalLogMapper;
    }

    @Transactional(readOnly = true)
    public Page<CaseClaimBillApprovalLogDTO> findAllByCaseClaimBill(Long caseClaimBillId, Pageable page) {
        log.debug("find by caseClaimBillId : {}", caseClaimBillId);
        CaseClaimBill caseClaimBill = caseClaimBillRepository.getOne(caseClaimBillId);
        if (caseClaimBill != null) {
            return caseClaimBillApprovalLogRepository.findAllByCaseClaimBill(caseClaimBill, page)
                .map(caseClaimBillApprovalLogMapper::toDto);
        }
        return null;
    }

    @Transactional
    public void saveBillApprovalLog(CaseClaimBill caseClaimBill,
                                Long processId,
                                String approvalOptiion,
                                String approvalTransition) {
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = new  CaseClaimBillApprovalLog();

        caseClaimBillApprovalLog.setCaseClaimBill(caseClaimBill);
        caseClaimBillApprovalLog.setProcessId(processId);
        caseClaimBillApprovalLog.setInsertTime(Instant.now());
        if (SecurityUtils.getCurrentUserLogin().isPresent()) {
            caseClaimBillApprovalLog.setApprovalUser(SecurityUtils.getCurrentUserLogin().get());
        } else {
            caseClaimBillApprovalLog.setApprovalUser("Error User");
        }
        caseClaimBillApprovalLog.setApprovalOpinion(approvalOptiion);
        caseClaimBillApprovalLog.setApprovalTransition(approvalTransition);
        caseClaimBillApprovalLog.setRemark(null);

        caseClaimBillApprovalLogRepository.save(caseClaimBillApprovalLog);
    }

}
