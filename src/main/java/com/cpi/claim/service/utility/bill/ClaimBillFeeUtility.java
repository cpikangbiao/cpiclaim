/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午3:33
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
import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseClaimBill;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.common.CurrencyDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/17
 * @since 1.0.0
 */
@Service
@Transactional
public class ClaimBillFeeUtility extends AbstractClaimBillUtility {

    @Transactional
    public  CaseClaimBill createClaimBillForDebit(
        CaseFee caseFee,
        String clientBillNo,
        Creditor creditor,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount
    ) {
        ClaimBillFinanceType claimBillFinanceType = claimToolUtility.claimBillFinanceTypeRepository.getOne(ClaimBillFinanceType.BILL_FINANCE_TYPE_DEBIT);
        CaseClaimBill caseClaimBill = createFeeClaimBill(
            caseFee,
            claimBillFinanceType,
            clientBillNo,
            creditor,
            userId,
            claimAmountCurrency,
            claimAmount,
            CurrencyDTO.CURRENCY_USD,
            new BigDecimal(1.0),
            new BigDecimal(0.0)
        );

        return caseClaimBill;
    }

    @Transactional
    public  CaseClaimBill createClaimBillForCredit(
        CaseFee caseFee,
        String clientBillNo,
        Creditor creditor,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount
    ) {
        ClaimBillFinanceType claimBillFinanceType = claimToolUtility.claimBillFinanceTypeRepository.getOne(ClaimBillFinanceType.BILL_FINANCE_TYPE_CREDIT);
        CaseClaimBill caseClaimBill = createFeeClaimBill(
            caseFee,
            claimBillFinanceType,
            clientBillNo,
            creditor,
            userId,
            claimAmountCurrency,
            claimAmount,
            CurrencyDTO.CURRENCY_USD,
            new BigDecimal(1.0),
            new BigDecimal(0.0)
        );

        return caseClaimBill;
    }

    @Transactional
    public  CaseClaimBill createClaimBillForCreditWithDeductible(
        CaseFee caseFee,
        String clientBillNo,
        Creditor creditor,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount,
        Long deductibleCurrency,
        BigDecimal deductibleCurrencyRate,
        BigDecimal deductibleAmount
    ) {
        ClaimBillFinanceType claimBillFinanceType = claimToolUtility.claimBillFinanceTypeRepository.getOne(ClaimBillFinanceType.BILL_FINANCE_TYPE_CREDIT);
        CaseClaimBill caseClaimBill = createFeeClaimBill(
            caseFee,
            claimBillFinanceType,
            clientBillNo,
            creditor,
            userId,
            claimAmountCurrency,
            claimAmount,
            deductibleCurrency,
            deductibleCurrencyRate,
            deductibleAmount
        );

        return caseClaimBill;
    }


    private  CaseClaimBill createFeeClaimBill(
        CaseFee caseFee,
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
        ClaimBillType claimBillType = claimToolUtility.claimBillTypeRepository.getOne((long)ClaimBillType.CLAIM_BILL_TYPE_CLAIMFEE);

        CaseClaimBill caseClaimBill = createClaimBill(
            caseFee.getSubcase(),
                            claimBillType,
                            claimBillFinanceType,
                            clientBillNo,
                            creditor,
                            userId,
                            claimAmountCurrency,
                            claimAmount,
                            deductibleCurrency,
                            deductibleCurrencyRate,
                            deductibleAmount
                        );

        createCaseFeeBill(
            caseFee,
            caseClaimBill
        );

        return caseClaimBill;
    }

    private CaseFeeBill createCaseFeeBill(
        CaseFee caseFee,
        CaseClaimBill caseClaimBill
    ) {
        CaseFeeBill caseFeeBill = new CaseFeeBill();

        caseFeeBill.setCaseFee(caseFee);
        caseFeeBill.setNumberId(claimToolUtility.caseFeeBillRepository.findMaxNumberIdByCaseFee(caseFee) + 1);
        caseFeeBill.setCaseClaimBill(caseClaimBill);

        caseFeeBill.setIsWriteOff(false);
        caseFeeBill.setWriteOffBill(null);

        claimToolUtility.caseFeeBillRepository.save(caseFeeBill);

        return caseFeeBill;
    }
}
