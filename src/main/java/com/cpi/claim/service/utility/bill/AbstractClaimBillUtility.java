/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午1:11
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
package com.cpi.claim.service.utility.bill;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import com.cpi.claim.service.dto.uw.MemberDTO;
import com.cpi.claim.service.utility.ClaimToolUtility;
import com.cpi.claim.service.utility.generate.ClaimBillCodeGenerateUtility;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;

;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/17
 * @since 1.0.0
 */
public abstract class AbstractClaimBillUtility {

    @Autowired
    protected ClaimToolUtility claimToolUtility;

    protected CaseClaimBill createClaimBill(
        VesselSubCase vesselSubCase,
        ClaimBillType claimBillType,
        ClaimBillFinanceType claimBillFinanceType,
        String clientBillNo,
        Creditor creditor,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount,
        Long deductibleCurrency,
        BigDecimal deductibleCurrencyRate,
        BigDecimal deductibleAmount
    ) {
        CaseClaimBill caseClaimBill = new CaseClaimBill();

        ClaimBillStatus claimBillStatus = claimToolUtility.claimBillStatusRepository.getOne((long) ClaimBillStatus.CASE_CLAIM_BILL_STATUS_NOAPPROVAL);
        caseClaimBill.setClaimBillStatus(claimBillStatus);
        caseClaimBill.setClaimBillType(claimBillType);
        caseClaimBill.setClaimBillFinanceType(claimBillFinanceType);
        caseClaimBill.setCreditor(creditor);

        caseClaimBill.setSubcase(vesselSubCase);
        InsuredVesselDTO insuredVessel = claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselSubCase.getVesselCase().getInsuredVesselId());
        MemberDTO memberDTO = claimToolUtility.memberRepository.findMemberByID(insuredVessel.getMemberId());
        caseClaimBill.setMemberYear(memberDTO.getMemberYear());
        caseClaimBill.setNumberId(claimToolUtility.caseClaimBillRepository.findMaxNumberIdByYear(caseClaimBill.getMemberYear()) + 1);

        caseClaimBill.setClaimBillCode(ClaimBillCodeGenerateUtility.createClaimBillCode(
                                                    caseClaimBill.getMemberYear(),
                                                    caseClaimBill.getClaimBillType(),
                                                    caseClaimBill.getNumberId()
                                                ));
        caseClaimBill.setClaimBillDate(Instant.now());
        caseClaimBill.setDueDate(Instant.now());

        caseClaimBill.setRegisterUserId(userId);
        caseClaimBill.setClientBillNo(clientBillNo);
        caseClaimBill.setBillDescription("");


        caseClaimBill.setClaimAmountCurrency(claimAmountCurrency);
        caseClaimBill.setClaimAmount(claimAmount);

        if (deductibleCurrencyRate != null) {
            caseClaimBill.setDeductibleCurrency(deductibleCurrency);
            caseClaimBill.setDeductibleCurrencyRate(deductibleCurrencyRate);
            caseClaimBill.setDeductible(deductibleAmount);
            caseClaimBill.setDeductibleDollar(deductibleAmount.divide(deductibleCurrencyRate, 2, BigDecimal.ROUND_HALF_UP));
        }

        caseClaimBill.setBillCurrency(claimAmountCurrency);
        caseClaimBill.setBillAmount(claimAmount);
        caseClaimBill.setBillCurrencyRate(new BigDecimal("1"));
        caseClaimBill.setBillAmountDollar(claimAmount);

        caseClaimBill.setRemark("");

        caseClaimBill.setIsSigned(false);
        caseClaimBill.setSignUser(null);
        caseClaimBill.setSignDate(null);
        caseClaimBill.setProcessId(null);
        caseClaimBill.setPrintNumber(null);

        claimToolUtility.caseClaimBillRepository.save(caseClaimBill);

        return caseClaimBill;
    }

}
