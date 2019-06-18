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
import java.util.Objects;
import io.github.jhipster.service.Criteria;
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
 * Criteria class for the {@link com.cpi.claim.domain.CaseFee} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseFeeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-fees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseFeeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter clientNo;

    private IntegerFilter numberId;

    private LongFilter currency;

    private BigDecimalFilter currencyRate;

    private InstantFilter feeCostDate;

    private BigDecimalFilter feeCost;

    private BigDecimalFilter feeCostDollar;

    private BigDecimalFilter deduct;

    private LongFilter deductCurrency;

    private BigDecimalFilter deductCurrencyRate;

    private BigDecimalFilter deductAmount;

    private BigDecimalFilter amountDollar;

    private LongFilter feeCreateUser;

    private InstantFilter feeCreateDate;

    private BooleanFilter isSigned;

    private LongFilter signUser;

    private LongFilter signDate;

    private LongFilter processId;

    private LongFilter feeTypeId;

    private LongFilter subcaseId;

    private LongFilter creditorId;

    public CaseFeeCriteria(){
    }

    public CaseFeeCriteria(CaseFeeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.clientNo = other.clientNo == null ? null : other.clientNo.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.currencyRate = other.currencyRate == null ? null : other.currencyRate.copy();
        this.feeCostDate = other.feeCostDate == null ? null : other.feeCostDate.copy();
        this.feeCost = other.feeCost == null ? null : other.feeCost.copy();
        this.feeCostDollar = other.feeCostDollar == null ? null : other.feeCostDollar.copy();
        this.deduct = other.deduct == null ? null : other.deduct.copy();
        this.deductCurrency = other.deductCurrency == null ? null : other.deductCurrency.copy();
        this.deductCurrencyRate = other.deductCurrencyRate == null ? null : other.deductCurrencyRate.copy();
        this.deductAmount = other.deductAmount == null ? null : other.deductAmount.copy();
        this.amountDollar = other.amountDollar == null ? null : other.amountDollar.copy();
        this.feeCreateUser = other.feeCreateUser == null ? null : other.feeCreateUser.copy();
        this.feeCreateDate = other.feeCreateDate == null ? null : other.feeCreateDate.copy();
        this.isSigned = other.isSigned == null ? null : other.isSigned.copy();
        this.signUser = other.signUser == null ? null : other.signUser.copy();
        this.signDate = other.signDate == null ? null : other.signDate.copy();
        this.processId = other.processId == null ? null : other.processId.copy();
        this.feeTypeId = other.feeTypeId == null ? null : other.feeTypeId.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
        this.creditorId = other.creditorId == null ? null : other.creditorId.copy();
    }

    @Override
    public CaseFeeCriteria copy() {
        return new CaseFeeCriteria(this);
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

    public InstantFilter getFeeCostDate() {
        return feeCostDate;
    }

    public void setFeeCostDate(InstantFilter feeCostDate) {
        this.feeCostDate = feeCostDate;
    }

    public BigDecimalFilter getFeeCost() {
        return feeCost;
    }

    public void setFeeCost(BigDecimalFilter feeCost) {
        this.feeCost = feeCost;
    }

    public BigDecimalFilter getFeeCostDollar() {
        return feeCostDollar;
    }

    public void setFeeCostDollar(BigDecimalFilter feeCostDollar) {
        this.feeCostDollar = feeCostDollar;
    }

    public BigDecimalFilter getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimalFilter deduct) {
        this.deduct = deduct;
    }

    public LongFilter getDeductCurrency() {
        return deductCurrency;
    }

    public void setDeductCurrency(LongFilter deductCurrency) {
        this.deductCurrency = deductCurrency;
    }

    public BigDecimalFilter getDeductCurrencyRate() {
        return deductCurrencyRate;
    }

    public void setDeductCurrencyRate(BigDecimalFilter deductCurrencyRate) {
        this.deductCurrencyRate = deductCurrencyRate;
    }

    public BigDecimalFilter getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimalFilter deductAmount) {
        this.deductAmount = deductAmount;
    }

    public BigDecimalFilter getAmountDollar() {
        return amountDollar;
    }

    public void setAmountDollar(BigDecimalFilter amountDollar) {
        this.amountDollar = amountDollar;
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

    public BooleanFilter getIsSigned() {
        return isSigned;
    }

    public void setIsSigned(BooleanFilter isSigned) {
        this.isSigned = isSigned;
    }

    public LongFilter getSignUser() {
        return signUser;
    }

    public void setSignUser(LongFilter signUser) {
        this.signUser = signUser;
    }

    public LongFilter getSignDate() {
        return signDate;
    }

    public void setSignDate(LongFilter signDate) {
        this.signDate = signDate;
    }

    public LongFilter getProcessId() {
        return processId;
    }

    public void setProcessId(LongFilter processId) {
        this.processId = processId;
    }

    public LongFilter getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(LongFilter feeTypeId) {
        this.feeTypeId = feeTypeId;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseFeeCriteria that = (CaseFeeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(clientNo, that.clientNo) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(currencyRate, that.currencyRate) &&
            Objects.equals(feeCostDate, that.feeCostDate) &&
            Objects.equals(feeCost, that.feeCost) &&
            Objects.equals(feeCostDollar, that.feeCostDollar) &&
            Objects.equals(deduct, that.deduct) &&
            Objects.equals(deductCurrency, that.deductCurrency) &&
            Objects.equals(deductCurrencyRate, that.deductCurrencyRate) &&
            Objects.equals(deductAmount, that.deductAmount) &&
            Objects.equals(amountDollar, that.amountDollar) &&
            Objects.equals(feeCreateUser, that.feeCreateUser) &&
            Objects.equals(feeCreateDate, that.feeCreateDate) &&
            Objects.equals(isSigned, that.isSigned) &&
            Objects.equals(signUser, that.signUser) &&
            Objects.equals(signDate, that.signDate) &&
            Objects.equals(processId, that.processId) &&
            Objects.equals(feeTypeId, that.feeTypeId) &&
            Objects.equals(subcaseId, that.subcaseId) &&
            Objects.equals(creditorId, that.creditorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        clientNo,
        numberId,
        currency,
        currencyRate,
        feeCostDate,
        feeCost,
        feeCostDollar,
        deduct,
        deductCurrency,
        deductCurrencyRate,
        deductAmount,
        amountDollar,
        feeCreateUser,
        feeCreateDate,
        isSigned,
        signUser,
        signDate,
        processId,
        feeTypeId,
        subcaseId,
        creditorId
        );
    }

    @Override
    public String toString() {
        return "CaseFeeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (clientNo != null ? "clientNo=" + clientNo + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (currencyRate != null ? "currencyRate=" + currencyRate + ", " : "") +
                (feeCostDate != null ? "feeCostDate=" + feeCostDate + ", " : "") +
                (feeCost != null ? "feeCost=" + feeCost + ", " : "") +
                (feeCostDollar != null ? "feeCostDollar=" + feeCostDollar + ", " : "") +
                (deduct != null ? "deduct=" + deduct + ", " : "") +
                (deductCurrency != null ? "deductCurrency=" + deductCurrency + ", " : "") +
                (deductCurrencyRate != null ? "deductCurrencyRate=" + deductCurrencyRate + ", " : "") +
                (deductAmount != null ? "deductAmount=" + deductAmount + ", " : "") +
                (amountDollar != null ? "amountDollar=" + amountDollar + ", " : "") +
                (feeCreateUser != null ? "feeCreateUser=" + feeCreateUser + ", " : "") +
                (feeCreateDate != null ? "feeCreateDate=" + feeCreateDate + ", " : "") +
                (isSigned != null ? "isSigned=" + isSigned + ", " : "") +
                (signUser != null ? "signUser=" + signUser + ", " : "") +
                (signDate != null ? "signDate=" + signDate + ", " : "") +
                (processId != null ? "processId=" + processId + ", " : "") +
                (feeTypeId != null ? "feeTypeId=" + feeTypeId + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (creditorId != null ? "creditorId=" + creditorId + ", " : "") +
            "}";
    }

}
