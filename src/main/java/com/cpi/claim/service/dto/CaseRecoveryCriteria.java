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
 * Criteria class for the {@link com.cpi.claim.domain.CaseRecovery} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseRecoveryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-recoveries?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseRecoveryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter clientNo;

    private IntegerFilter numberId;

    private LongFilter currency;

    private BigDecimalFilter currencyRate;

    private InstantFilter issueDate;

    private BigDecimalFilter issueAmount;

    private InstantFilter receivedDate;

    private BigDecimalFilter receivedAmount;

    private BigDecimalFilter amountDollar;

    private InstantFilter registerDate;

    private LongFilter registerUser;

    private LongFilter subcaseId;

    private LongFilter recoveryTypeId;

    private LongFilter creditorId;

    public CaseRecoveryCriteria(){
    }

    public CaseRecoveryCriteria(CaseRecoveryCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.clientNo = other.clientNo == null ? null : other.clientNo.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.currencyRate = other.currencyRate == null ? null : other.currencyRate.copy();
        this.issueDate = other.issueDate == null ? null : other.issueDate.copy();
        this.issueAmount = other.issueAmount == null ? null : other.issueAmount.copy();
        this.receivedDate = other.receivedDate == null ? null : other.receivedDate.copy();
        this.receivedAmount = other.receivedAmount == null ? null : other.receivedAmount.copy();
        this.amountDollar = other.amountDollar == null ? null : other.amountDollar.copy();
        this.registerDate = other.registerDate == null ? null : other.registerDate.copy();
        this.registerUser = other.registerUser == null ? null : other.registerUser.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
        this.recoveryTypeId = other.recoveryTypeId == null ? null : other.recoveryTypeId.copy();
        this.creditorId = other.creditorId == null ? null : other.creditorId.copy();
    }

    @Override
    public CaseRecoveryCriteria copy() {
        return new CaseRecoveryCriteria(this);
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

    public InstantFilter getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(InstantFilter issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimalFilter getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(BigDecimalFilter issueAmount) {
        this.issueAmount = issueAmount;
    }

    public InstantFilter getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(InstantFilter receivedDate) {
        this.receivedDate = receivedDate;
    }

    public BigDecimalFilter getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimalFilter receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimalFilter getAmountDollar() {
        return amountDollar;
    }

    public void setAmountDollar(BigDecimalFilter amountDollar) {
        this.amountDollar = amountDollar;
    }

    public InstantFilter getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(InstantFilter registerDate) {
        this.registerDate = registerDate;
    }

    public LongFilter getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(LongFilter registerUser) {
        this.registerUser = registerUser;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }

    public LongFilter getRecoveryTypeId() {
        return recoveryTypeId;
    }

    public void setRecoveryTypeId(LongFilter recoveryTypeId) {
        this.recoveryTypeId = recoveryTypeId;
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
        final CaseRecoveryCriteria that = (CaseRecoveryCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(clientNo, that.clientNo) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(currencyRate, that.currencyRate) &&
            Objects.equals(issueDate, that.issueDate) &&
            Objects.equals(issueAmount, that.issueAmount) &&
            Objects.equals(receivedDate, that.receivedDate) &&
            Objects.equals(receivedAmount, that.receivedAmount) &&
            Objects.equals(amountDollar, that.amountDollar) &&
            Objects.equals(registerDate, that.registerDate) &&
            Objects.equals(registerUser, that.registerUser) &&
            Objects.equals(subcaseId, that.subcaseId) &&
            Objects.equals(recoveryTypeId, that.recoveryTypeId) &&
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
        issueDate,
        issueAmount,
        receivedDate,
        receivedAmount,
        amountDollar,
        registerDate,
        registerUser,
        subcaseId,
        recoveryTypeId,
        creditorId
        );
    }

    @Override
    public String toString() {
        return "CaseRecoveryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (clientNo != null ? "clientNo=" + clientNo + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (currencyRate != null ? "currencyRate=" + currencyRate + ", " : "") +
                (issueDate != null ? "issueDate=" + issueDate + ", " : "") +
                (issueAmount != null ? "issueAmount=" + issueAmount + ", " : "") +
                (receivedDate != null ? "receivedDate=" + receivedDate + ", " : "") +
                (receivedAmount != null ? "receivedAmount=" + receivedAmount + ", " : "") +
                (amountDollar != null ? "amountDollar=" + amountDollar + ", " : "") +
                (registerDate != null ? "registerDate=" + registerDate + ", " : "") +
                (registerUser != null ? "registerUser=" + registerUser + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (recoveryTypeId != null ? "recoveryTypeId=" + recoveryTypeId + ", " : "") +
                (creditorId != null ? "creditorId=" + creditorId + ", " : "") +
            "}";
    }

}
