/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午1:58
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
package com.cpi.claim.service.bean.payment;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.bean.bill.CaseClaimBillPaymentBean;
import com.cpi.claim.service.common.Contants;
import com.cpi.claim.service.dto.common.CurrencyDTO;
import com.cpi.claim.service.utility.ClaimToolUtility;

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
public class CasePaymentBean {

    private List<CaseClaimBillPaymentBean> caseClaimBillPaymentBeans;

    private Integer    paymentNum;

    private BigDecimal paymentSumAmount;

    //2014-04-04 by kangbiao
    //根据财务部的要求需要在菜单上添加免赔额信息。所以在这里需要对免赔额进行细化，
    //根据要求需要将免赔额分成三部分：
    //1. 本子案件的原本免赔额totalDeductible;
    //2. 本子案件账单中免赔额总额totalBillDeductible;及所有已经开的账单中存在的免赔额的和
    //3. 本子案件剩余免赔额deductible

    //1. 本子案件的原本免赔额totalDeductible;
    private BigDecimal     totalDeductibleDollar;

    //2. 本子案件账单中免赔额总额totalBillDeductible;及所有已经开的账单中存在的免赔额的和
    private BigDecimal     totalBillDeductibleDollar;

    //3. 本子案件剩余免赔额deductible

    private Long      deductibleCurrencyId;

    private String    deductibleCurrency;

    private BigDecimal     deductibleCurrencyRate;

    private BigDecimal     deductible;

    private BigDecimal     deductibleDollar;

    private Boolean    hasDeductible;

    private Boolean    hasRemainDeductible;

    private Integer    billNum;

    private BigDecimal     billSumAmount;

    private BigDecimal     remainAmountDollar;

    private Long remainAmountCurrencyId;

    private String remainAmountCurrency;

    private BigDecimal     remainAmountCurrencyRate;

    private BigDecimal     remainAmount;

    private Long claimAmountCurrencyId;

    private String claimAmountCurrency;

    private BigDecimal     claimAmount;

    private Boolean    hasRemain;

    public CasePaymentBean() {
        this.caseClaimBillPaymentBeans = new ArrayList<>();
        this.paymentNum = 0;
        this.paymentSumAmount = BigDecimal.ZERO;
        this.totalDeductibleDollar = BigDecimal.ZERO;
        this.totalBillDeductibleDollar = BigDecimal.ZERO;
        this.deductibleCurrencyId = CurrencyDTO.CURRENCY_USD;
        this.deductibleCurrency = null;
        this.deductibleCurrencyRate = BigDecimal.ZERO;
        this.deductible = BigDecimal.ZERO;
        this.deductibleDollar = BigDecimal.ZERO;
        this.hasDeductible = false;
        this.hasRemainDeductible = false;
        this.billNum = 0;
        this.billSumAmount = BigDecimal.ZERO;
        this.remainAmountDollar = BigDecimal.ZERO;
        this.remainAmountCurrencyId = CurrencyDTO.CURRENCY_USD;
        this.remainAmountCurrency = null;
        this.remainAmountCurrencyRate = BigDecimal.ZERO;
        this.remainAmount = BigDecimal.ZERO;
        this.claimAmountCurrencyId = CurrencyDTO.CURRENCY_USD;
        this.claimAmountCurrency = null;
        this.claimAmount = BigDecimal.ZERO;
        this.hasRemain = false;
    }

    public void init(VesselSubCase vesselSubCase, Boolean hasDeductible,
                     Long remainAmountCurrencyId, BigDecimal remainAmountCurrencyRate,
                     ClaimToolUtility claimToolUtility) {
//        this.vesselSubCase = vesselSubCase;
        this.hasDeductible    = hasDeductible;

        //2014-05-07优化币种转换中的误差
        List<CasePayment> casePayments = claimToolUtility.casePaymentRepository.findAllBySubcase(vesselSubCase);
        for (CasePayment casePayment : casePayments) {
            paymentNum      += 1;
            paymentSumAmount = paymentSumAmount.add(casePayment.getPayCost().divide(casePayment.getCurrencyRate(),
                Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
        }

        if (vesselSubCase.getDeductible() != null) {
            totalDeductibleDollar = vesselSubCase.getDeductible().divide(vesselSubCase.getCurrencyRate(),
                Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP);
        }

        List<CasePaymentBill> casePaymentBills = claimToolUtility.casePaymentBillRepository.findAllBySubcase(vesselSubCase);
        for (CasePaymentBill casePaymentBill : casePaymentBills) {
            CaseClaimBillPaymentBean caseClaimBillPaymentBean = new CaseClaimBillPaymentBean();
            caseClaimBillPaymentBean.init(casePaymentBill, claimToolUtility);
            this.caseClaimBillPaymentBeans.add(caseClaimBillPaymentBean);
            billNum       += 1;

            CaseClaimBill caseClaimBill = casePaymentBill.getCaseClaimBill();

            if (casePaymentBill.getCaseClaimBill().getBillAmount() != null) {

                if (caseClaimBill.getClaimBillFinanceType().getId().equals(ClaimBillFinanceType.BILL_FINANCE_TYPE_DEBIT)) {
                    billSumAmount = billSumAmount.subtract(
                                                    caseClaimBill.getBillAmount().divide(
                                                        caseClaimBill.getBillCurrencyRate(),
                                                                        Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
                }
                if (caseClaimBill.getClaimBillFinanceType().getId().equals(ClaimBillFinanceType.BILL_FINANCE_TYPE_CREDIT)) {
                    billSumAmount = billSumAmount.add( caseClaimBill.getBillAmount().divide(
                                                    caseClaimBill.getBillCurrencyRate(),
                                                        Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
                }
            }
            if (caseClaimBill.getDeductible() != null) {
                totalBillDeductibleDollar = totalBillDeductibleDollar.add(caseClaimBill.getDeductible().divide(caseClaimBill.getDeductibleCurrencyRate(),
                    Contants.CLAIM_CONTANTS_CURRENCY_SCALE, BigDecimal.ROUND_HALF_UP));
            } else {
                totalBillDeductibleDollar = totalBillDeductibleDollar.add(caseClaimBill.getDeductibleDollar());
            }
        }

        deductibleDollar = totalDeductibleDollar.subtract(totalBillDeductibleDollar) ;

        deductibleCurrencyId    = vesselSubCase.getCurrency();
        CurrencyDTO currencyDTO = claimToolUtility.currencyRepository.findCurrencyByID(vesselSubCase.getCurrency());
        deductibleCurrency     = currencyDTO.getNameAbbre();
        deductibleCurrencyRate = remainAmountCurrencyRate;
        deductible             = deductibleDollar.multiply(deductibleCurrencyRate);

        if (hasDeductible) {
            remainAmountDollar = paymentSumAmount.subtract(billSumAmount.subtract(totalBillDeductibleDollar.subtract(deductibleDollar)));
        } else {
            remainAmountDollar = paymentSumAmount.subtract(billSumAmount) .subtract(totalBillDeductibleDollar);
        }

        if (remainAmountDollar.compareTo(BigDecimal.ZERO) > 1) {
            hasRemain = true;
        }

        if (deductible.compareTo(BigDecimal.ZERO) > 1) {
            hasRemainDeductible = true;
        }

        this.remainAmountCurrencyId    = remainAmountCurrencyId;
        currencyDTO = claimToolUtility.currencyRepository.findCurrencyByID(remainAmountCurrencyId);
        this.remainAmountCurrency     = currencyDTO.getNameAbbre();
        this.remainAmountCurrencyRate = remainAmountCurrencyRate;
        remainAmount = remainAmountDollar.multiply(remainAmountCurrencyRate);

        claimAmountCurrency      = remainAmountCurrency;
        claimAmount              = (remainAmountDollar .add(deductibleDollar)).multiply(remainAmountCurrencyRate);
    }

    public List<CaseClaimBillPaymentBean> getCaseClaimBillPaymentBeans() {
        return caseClaimBillPaymentBeans;
    }

    public void setCaseClaimBillPaymentBeans(List<CaseClaimBillPaymentBean> caseClaimBillPaymentBeans) {
        this.caseClaimBillPaymentBeans = caseClaimBillPaymentBeans;
    }

    public Integer getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(Integer paymentNum) {
        this.paymentNum = paymentNum;
    }

    public BigDecimal getPaymentSumAmount() {
        return paymentSumAmount;
    }

    public void setPaymentSumAmount(BigDecimal paymentSumAmount) {
        this.paymentSumAmount = paymentSumAmount;
    }

    public BigDecimal getTotalDeductibleDollar() {
        return totalDeductibleDollar;
    }

    public void setTotalDeductibleDollar(BigDecimal totalDeductibleDollar) {
        this.totalDeductibleDollar = totalDeductibleDollar;
    }

    public BigDecimal getTotalBillDeductibleDollar() {
        return totalBillDeductibleDollar;
    }

    public void setTotalBillDeductibleDollar(BigDecimal totalBillDeductibleDollar) {
        this.totalBillDeductibleDollar = totalBillDeductibleDollar;
    }

    public Long getDeductibleCurrencyId() {
        return deductibleCurrencyId;
    }

    public void setDeductibleCurrencyId(Long deductibleCurrencyId) {
        this.deductibleCurrencyId = deductibleCurrencyId;
    }

    public String getDeductibleCurrency() {
        return deductibleCurrency;
    }

    public void setDeductibleCurrency(String deductibleCurrency) {
        this.deductibleCurrency = deductibleCurrency;
    }

    public BigDecimal getDeductibleCurrencyRate() {
        return deductibleCurrencyRate;
    }

    public void setDeductibleCurrencyRate(BigDecimal deductibleCurrencyRate) {
        this.deductibleCurrencyRate = deductibleCurrencyRate;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public BigDecimal getDeductibleDollar() {
        return deductibleDollar;
    }

    public void setDeductibleDollar(BigDecimal deductibleDollar) {
        this.deductibleDollar = deductibleDollar;
    }

    public Boolean getHasDeductible() {
        return hasDeductible;
    }

    public void setHasDeductible(Boolean hasDeductible) {
        this.hasDeductible = hasDeductible;
    }

    public Boolean getHasRemainDeductible() {
        return hasRemainDeductible;
    }

    public void setHasRemainDeductible(Boolean hasRemainDeductible) {
        this.hasRemainDeductible = hasRemainDeductible;
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

    public BigDecimal getRemainAmountDollar() {
        return remainAmountDollar;
    }

    public void setRemainAmountDollar(BigDecimal remainAmountDollar) {
        this.remainAmountDollar = remainAmountDollar;
    }

    public Long getRemainAmountCurrencyId() {
        return remainAmountCurrencyId;
    }

    public void setRemainAmountCurrencyId(Long remainAmountCurrencyId) {
        this.remainAmountCurrencyId = remainAmountCurrencyId;
    }

    public String getRemainAmountCurrency() {
        return remainAmountCurrency;
    }

    public void setRemainAmountCurrency(String remainAmountCurrency) {
        this.remainAmountCurrency = remainAmountCurrency;
    }

    public BigDecimal getRemainAmountCurrencyRate() {
        return remainAmountCurrencyRate;
    }

    public void setRemainAmountCurrencyRate(BigDecimal remainAmountCurrencyRate) {
        this.remainAmountCurrencyRate = remainAmountCurrencyRate;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public Long getClaimAmountCurrencyId() {
        return claimAmountCurrencyId;
    }

    public void setClaimAmountCurrencyId(Long claimAmountCurrencyId) {
        this.claimAmountCurrencyId = claimAmountCurrencyId;
    }

    public String getClaimAmountCurrency() {
        return claimAmountCurrency;
    }

    public void setClaimAmountCurrency(String claimAmountCurrency) {
        this.claimAmountCurrency = claimAmountCurrency;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Boolean getHasRemain() {
        return hasRemain;
    }

    public void setHasRemain(Boolean hasRemain) {
        this.hasRemain = hasRemain;
    }
}
