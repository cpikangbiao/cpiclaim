/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午4:15
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

import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.repository.CaseRecoveryRepository;
import com.cpi.claim.service.bean.recovery.CaseRecoveryBean;
import com.cpi.claim.service.mapper.CaseRecoveryMapper;
import com.cpi.claim.service.utility.ClaimToolUtility;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CaseRecoveryForBillService extends QueryService<CaseRecovery> {

    private final Logger log = LoggerFactory.getLogger(CaseRecoveryForBillService.class);

    private final CaseRecoveryRepository caseRecoveryRepository;

    private final CaseRecoveryMapper caseRecoveryMapper;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    public CaseRecoveryForBillService(CaseRecoveryRepository caseRecoveryRepository, CaseRecoveryMapper caseRecoveryMapper) {
        this.caseRecoveryRepository = caseRecoveryRepository;
        this.caseRecoveryMapper = caseRecoveryMapper;
    }


    @Transactional(readOnly = true)
    public CaseRecoveryBean getCaseRecoveryBeanForCaseRecoveryId(Long caseRecoveryId) {
        log.debug("find getCaseFeeBeanForCaseFeeId By caseRecoveryId Id : {}", caseRecoveryId);
        CaseRecoveryBean caseRecoveryBean = new CaseRecoveryBean();
        CaseRecovery caseRecovery = caseRecoveryRepository.getOne(caseRecoveryId);
        if (caseRecovery != null) {
            caseRecoveryBean.init(caseRecovery, claimToolUtility);
        }

        return caseRecoveryBean;
    }

}
