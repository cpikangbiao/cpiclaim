/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午4:23
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

import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.repository.VesselSubCaseRepository;
import com.cpi.claim.service.bean.payment.CasePaymentBean;
import com.cpi.claim.service.dto.common.CurrencyDTO;
import com.cpi.claim.service.mapper.VesselSubCaseMapper;
import com.cpi.claim.service.utility.ClaimToolUtility;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class VesselSubCaseForPaymentBillService extends QueryService<VesselSubCase> {

    private final Logger log = LoggerFactory.getLogger(VesselSubCaseForPaymentBillService.class);

    private final VesselSubCaseRepository vesselSubCaseRepository;

    private final VesselSubCaseMapper vesselSubCaseMapper;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    public VesselSubCaseForPaymentBillService(VesselSubCaseRepository vesselSubCaseRepository, VesselSubCaseMapper vesselSubCaseMapper) {
        this.vesselSubCaseRepository = vesselSubCaseRepository;
        this.vesselSubCaseMapper = vesselSubCaseMapper;
    }

    @Transactional(readOnly = true)
    public CasePaymentBean getCasePaymentBeanForVesselSubCaseId(Long vesselSubCaseId,
                                                                Long currencyId,
                                                                BigDecimal currencyRate,
                                                                Boolean hasDeductible,
                                                                Long deductibleCurrencyId,
                                                                BigDecimal deductibleCurrencyRate) {
        log.debug("find by get CasePaymentBean Fo rVesselSubCaseId : {}", vesselSubCaseId);
        CasePaymentBean casePaymentBean = new CasePaymentBean();
        VesselSubCase vesselSubCase = vesselSubCaseRepository.getOne(vesselSubCaseId);
        if (currencyId == null) {
            currencyId = CurrencyDTO.CURRENCY_USD;
        }
        if (currencyRate == null) {
            currencyRate = new BigDecimal(1.0);
        }
        if (hasDeductible == null) {
            hasDeductible = Boolean.TRUE;
        }
        if (deductibleCurrencyId == null) {
            deductibleCurrencyId = CurrencyDTO.CURRENCY_USD;
        }
        if (deductibleCurrencyRate == null) {
            deductibleCurrencyRate = new BigDecimal(1.0);
        }

        if (vesselSubCase != null) {
            casePaymentBean.init(
                vesselSubCase,
                currencyId,
                currencyRate,
                hasDeductible,
                deductibleCurrencyId,
                deductibleCurrencyRate,
                claimToolUtility
            );
        }

        return casePaymentBean;
    }



}
