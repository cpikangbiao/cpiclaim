/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午2:47
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
package com.cpi.claim.service.bean.fee;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseFeeBill;
import com.cpi.claim.domain.ClaimBillFinanceType;
import com.cpi.claim.service.bean.bill.CaseClaimBillFeeBean;
import com.cpi.claim.service.common.Contants;
import com.cpi.claim.service.utility.ClaimToolUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/17
 * @since 1.0.0
 */
public class CaseFeeBean implements Serializable{

    private List<CaseClaimBillFeeBean> caseClaimBillFeeBeans;

    private Long currencyId;

    private String currency;

    private BigDecimal feeSumAmount;

    private Integer    billNum;

    private BigDecimal     billSumAmount;

    private BigDecimal     remainAmount;

    private Boolean    hasRemain;

    public CaseFeeBean() {
        this.caseClaimBillFeeBeans = new ArrayList<>();
        this.currencyId = null;
        this.currency = null;
        this.feeSumAmount = BigDecimal.ZERO;
        this.billNum = 0;
        this.billSumAmount = BigDecimal.ZERO;
        this.remainAmount = BigDecimal.ZERO;
        this.hasRemain = true;
    }

    public void init(CaseFee caseFee, ClaimToolUtility claimToolUtility) {
        this.currencyId      = caseFee.getCurrency();
        this.feeSumAmount  = caseFee.getFeeCost();
        BigDecimal currencyRate = BigDecimal.ZERO;
        if (caseFee.getCurrencyRate() != null) {
            currencyRate = caseFee.getCurrencyRate();
        }

        List<CaseFeeBill> caseFeeBills = claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
        for (CaseFeeBill caseFeeBill : caseFeeBills) {
            this. billNum += 1;
            CaseClaimBillFeeBean caseClaimBillFeeBean = new CaseClaimBillFeeBean();
            caseClaimBillFeeBean.init(caseFeeBill, claimToolUtility);
            this.caseClaimBillFeeBeans.add(caseClaimBillFeeBean);

            CaseClaimBill caseClaimBill = caseFeeBill.getCaseClaimBill();
            if (caseClaimBill.getClaimBillFinanceType().getId().equals(ClaimBillFinanceType.BILL_FINANCE_TYPE_DEBIT)) {
                billSumAmount = billSumAmount.subtract(caseClaimBill.getBillAmount().divide(caseClaimBill.getBillCurrencyRate(),
                    Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
            }
            if (caseClaimBill.getClaimBillFinanceType().getId().equals(ClaimBillFinanceType.BILL_FINANCE_TYPE_CREDIT)) {
                billSumAmount = billSumAmount.add(caseClaimBill.getBillAmount().divide(caseClaimBill.getBillCurrencyRate(),
                    Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
            }
        }

        billSumAmount = billSumAmount.multiply(currencyRate);

        remainAmount = feeSumAmount.subtract(billSumAmount);

        if (remainAmount.compareTo(BigDecimal.ZERO) != 1) {
            hasRemain = false;
        }
    }

    public List<CaseClaimBillFeeBean> getCaseClaimBillFeeBeans() {
        return caseClaimBillFeeBeans;
    }

    public void setCaseClaimBillFeeBeans(List<CaseClaimBillFeeBean> caseClaimBillFeeBeans) {
        this.caseClaimBillFeeBeans = caseClaimBillFeeBeans;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getFeeSumAmount() {
        return feeSumAmount;
    }

    public void setFeeSumAmount(BigDecimal feeSumAmount) {
        this.feeSumAmount = feeSumAmount;
    }

    public Integer getBillNum() {
        return billNum;
    }

    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
    }

    public BigDecimal getBillSumAmount() {
        return billSumAmount;
    }

    public void setBillSumAmount(BigDecimal billSumAmount) {
        this.billSumAmount = billSumAmount;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Boolean getHasRemain() {
        return hasRemain;
    }

    public void setHasRemain(Boolean hasRemain) {
        this.hasRemain = hasRemain;
    }
}
