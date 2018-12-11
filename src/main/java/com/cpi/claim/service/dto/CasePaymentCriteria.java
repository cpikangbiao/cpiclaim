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

package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the CasePayment entity. This class is used in CasePaymentResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-payments?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CasePaymentCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter clientNo;

    private IntegerFilter numberId;

    private InstantFilter payCostDate;

    private LongFilter currency;

    private BigDecimalFilter currencyRate;

    private BigDecimalFilter payCost;

    private BigDecimalFilter payCostDollar;

    private BigDecimalFilter deduct;

    private BigDecimalFilter amount;

    private LongFilter feeCreateUser;

    private InstantFilter feeCreateDate;

    private LongFilter paymentTypeId;

    private LongFilter subcaseId;

    private LongFilter creditorId;

    public CasePaymentCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getClientNo() {
        return clientNo;
    }

    public void setClientNo(StringFilter clientNo) {
        this.clientNo = clientNo;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public InstantFilter getPayCostDate() {
        return payCostDate;
    }

    public void setPayCostDate(InstantFilter payCostDate) {
        this.payCostDate = payCostDate;
    }

    public LongFilter getCurrency() {
        return currency;
    }

    public void setCurrency(LongFilter currency) {
        this.currency = currency;
    }

    public BigDecimalFilter getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimalFilter currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimalFilter getPayCost() {
        return payCost;
    }

    public void setPayCost(BigDecimalFilter payCost) {
        this.payCost = payCost;
    }

    public BigDecimalFilter getPayCostDollar() {
        return payCostDollar;
    }

    public void setPayCostDollar(BigDecimalFilter payCostDollar) {
        this.payCostDollar = payCostDollar;
    }

    public BigDecimalFilter getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimalFilter deduct) {
        this.deduct = deduct;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public LongFilter getFeeCreateUser() {
        return feeCreateUser;
    }

    public void setFeeCreateUser(LongFilter feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
    }

    public InstantFilter getFeeCreateDate() {
        return feeCreateDate;
    }

    public void setFeeCreateDate(InstantFilter feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
    }

    public LongFilter getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(LongFilter paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }

    public LongFilter getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(LongFilter creditorId) {
        this.creditorId = creditorId;
    }

    @Override
    public String toString() {
        return "CasePaymentCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (clientNo != null ? "clientNo=" + clientNo + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (payCostDate != null ? "payCostDate=" + payCostDate + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (currencyRate != null ? "currencyRate=" + currencyRate + ", " : "") +
                (payCost != null ? "payCost=" + payCost + ", " : "") +
                (payCostDollar != null ? "payCostDollar=" + payCostDollar + ", " : "") +
                (deduct != null ? "deduct=" + deduct + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (feeCreateUser != null ? "feeCreateUser=" + feeCreateUser + ", " : "") +
                (feeCreateDate != null ? "feeCreateDate=" + feeCreateDate + ", " : "") +
                (paymentTypeId != null ? "paymentTypeId=" + paymentTypeId + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (creditorId != null ? "creditorId=" + creditorId + ", " : "") +
            "}";
    }

}
