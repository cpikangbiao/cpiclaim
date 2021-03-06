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
 * Criteria class for the {@link com.cpi.claim.domain.CaseClaimBill} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseClaimBillResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-claim-bills?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter claimBillCode;

    private InstantFilter claimBillDate;

    private LongFilter registerUserId;

    private StringFilter clientBillNo;

    private StringFilter billDescription;

    private InstantFilter dueDate;

    private StringFilter memberYear;

    private IntegerFilter numberId;

    private LongFilter claimAmountCurrency;

    private BigDecimalFilter claimAmount;

    private BigDecimalFilter deductible;

    private LongFilter deductibleCurrency;

    private BigDecimalFilter deductibleCurrencyRate;

    private BigDecimalFilter deductibleDollar;

    private LongFilter billCurrency;

    private BigDecimalFilter billAmount;

    private BigDecimalFilter billCurrencyRate;

    private BigDecimalFilter billAmountDollar;

    private BooleanFilter isSigned;

    private LongFilter signUser;

    private InstantFilter signDate;

    private LongFilter processId;

    private IntegerFilter printNumber;

    private LongFilter subcaseId;

    private LongFilter claimBillStatusId;

    private LongFilter claimBillTypeId;

    private LongFilter claimBillFinanceTypeId;

    private LongFilter creditorId;

    public CaseClaimBillCriteria(){
    }

    public CaseClaimBillCriteria(CaseClaimBillCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.claimBillCode = other.claimBillCode == null ? null : other.claimBillCode.copy();
        this.claimBillDate = other.claimBillDate == null ? null : other.claimBillDate.copy();
        this.registerUserId = other.registerUserId == null ? null : other.registerUserId.copy();
        this.clientBillNo = other.clientBillNo == null ? null : other.clientBillNo.copy();
        this.billDescription = other.billDescription == null ? null : other.billDescription.copy();
        this.dueDate = other.dueDate == null ? null : other.dueDate.copy();
        this.memberYear = other.memberYear == null ? null : other.memberYear.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.claimAmountCurrency = other.claimAmountCurrency == null ? null : other.claimAmountCurrency.copy();
        this.claimAmount = other.claimAmount == null ? null : other.claimAmount.copy();
        this.deductible = other.deductible == null ? null : other.deductible.copy();
        this.deductibleCurrency = other.deductibleCurrency == null ? null : other.deductibleCurrency.copy();
        this.deductibleCurrencyRate = other.deductibleCurrencyRate == null ? null : other.deductibleCurrencyRate.copy();
        this.deductibleDollar = other.deductibleDollar == null ? null : other.deductibleDollar.copy();
        this.billCurrency = other.billCurrency == null ? null : other.billCurrency.copy();
        this.billAmount = other.billAmount == null ? null : other.billAmount.copy();
        this.billCurrencyRate = other.billCurrencyRate == null ? null : other.billCurrencyRate.copy();
        this.billAmountDollar = other.billAmountDollar == null ? null : other.billAmountDollar.copy();
        this.isSigned = other.isSigned == null ? null : other.isSigned.copy();
        this.signUser = other.signUser == null ? null : other.signUser.copy();
        this.signDate = other.signDate == null ? null : other.signDate.copy();
        this.processId = other.processId == null ? null : other.processId.copy();
        this.printNumber = other.printNumber == null ? null : other.printNumber.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
        this.claimBillStatusId = other.claimBillStatusId == null ? null : other.claimBillStatusId.copy();
        this.claimBillTypeId = other.claimBillTypeId == null ? null : other.claimBillTypeId.copy();
        this.claimBillFinanceTypeId = other.claimBillFinanceTypeId == null ? null : other.claimBillFinanceTypeId.copy();
        this.creditorId = other.creditorId == null ? null : other.creditorId.copy();
    }

    @Override
    public CaseClaimBillCriteria copy() {
        return new CaseClaimBillCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getClaimBillCode() {
        return claimBillCode;
    }

    public void setClaimBillCode(StringFilter claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public InstantFilter getClaimBillDate() {
        return claimBillDate;
    }

    public void setClaimBillDate(InstantFilter claimBillDate) {
        this.claimBillDate = claimBillDate;
    }

    public LongFilter getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(LongFilter registerUserId) {
        this.registerUserId = registerUserId;
    }

    public StringFilter getClientBillNo() {
        return clientBillNo;
    }

    public void setClientBillNo(StringFilter clientBillNo) {
        this.clientBillNo = clientBillNo;
    }

    public StringFilter getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(StringFilter billDescription) {
        this.billDescription = billDescription;
    }

    public InstantFilter getDueDate() {
        return dueDate;
    }

    public void setDueDate(InstantFilter dueDate) {
        this.dueDate = dueDate;
    }

    public StringFilter getMemberYear() {
        return memberYear;
    }

    public void setMemberYear(StringFilter memberYear) {
        this.memberYear = memberYear;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public LongFilter getClaimAmountCurrency() {
        return claimAmountCurrency;
    }

    public void setClaimAmountCurrency(LongFilter claimAmountCurrency) {
        this.claimAmountCurrency = claimAmountCurrency;
    }

    public BigDecimalFilter getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimalFilter claimAmount) {
        this.claimAmount = claimAmount;
    }

    public BigDecimalFilter getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimalFilter deductible) {
        this.deductible = deductible;
    }

    public LongFilter getDeductibleCurrency() {
        return deductibleCurrency;
    }

    public void setDeductibleCurrency(LongFilter deductibleCurrency) {
        this.deductibleCurrency = deductibleCurrency;
    }

    public BigDecimalFilter getDeductibleCurrencyRate() {
        return deductibleCurrencyRate;
    }

    public void setDeductibleCurrencyRate(BigDecimalFilter deductibleCurrencyRate) {
        this.deductibleCurrencyRate = deductibleCurrencyRate;
    }

    public BigDecimalFilter getDeductibleDollar() {
        return deductibleDollar;
    }

    public void setDeductibleDollar(BigDecimalFilter deductibleDollar) {
        this.deductibleDollar = deductibleDollar;
    }

    public LongFilter getBillCurrency() {
        return billCurrency;
    }

    public void setBillCurrency(LongFilter billCurrency) {
        this.billCurrency = billCurrency;
    }

    public BigDecimalFilter getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimalFilter billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimalFilter getBillCurrencyRate() {
        return billCurrencyRate;
    }

    public void setBillCurrencyRate(BigDecimalFilter billCurrencyRate) {
        this.billCurrencyRate = billCurrencyRate;
    }

    public BigDecimalFilter getBillAmountDollar() {
        return billAmountDollar;
    }

    public void setBillAmountDollar(BigDecimalFilter billAmountDollar) {
        this.billAmountDollar = billAmountDollar;
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

    public InstantFilter getSignDate() {
        return signDate;
    }

    public void setSignDate(InstantFilter signDate) {
        this.signDate = signDate;
    }

    public LongFilter getProcessId() {
        return processId;
    }

    public void setProcessId(LongFilter processId) {
        this.processId = processId;
    }

    public IntegerFilter getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(IntegerFilter printNumber) {
        this.printNumber = printNumber;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }

    public LongFilter getClaimBillStatusId() {
        return claimBillStatusId;
    }

    public void setClaimBillStatusId(LongFilter claimBillStatusId) {
        this.claimBillStatusId = claimBillStatusId;
    }

    public LongFilter getClaimBillTypeId() {
        return claimBillTypeId;
    }

    public void setClaimBillTypeId(LongFilter claimBillTypeId) {
        this.claimBillTypeId = claimBillTypeId;
    }

    public LongFilter getClaimBillFinanceTypeId() {
        return claimBillFinanceTypeId;
    }

    public void setClaimBillFinanceTypeId(LongFilter claimBillFinanceTypeId) {
        this.claimBillFinanceTypeId = claimBillFinanceTypeId;
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
        final CaseClaimBillCriteria that = (CaseClaimBillCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(claimBillCode, that.claimBillCode) &&
            Objects.equals(claimBillDate, that.claimBillDate) &&
            Objects.equals(registerUserId, that.registerUserId) &&
            Objects.equals(clientBillNo, that.clientBillNo) &&
            Objects.equals(billDescription, that.billDescription) &&
            Objects.equals(dueDate, that.dueDate) &&
            Objects.equals(memberYear, that.memberYear) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(claimAmountCurrency, that.claimAmountCurrency) &&
            Objects.equals(claimAmount, that.claimAmount) &&
            Objects.equals(deductible, that.deductible) &&
            Objects.equals(deductibleCurrency, that.deductibleCurrency) &&
            Objects.equals(deductibleCurrencyRate, that.deductibleCurrencyRate) &&
            Objects.equals(deductibleDollar, that.deductibleDollar) &&
            Objects.equals(billCurrency, that.billCurrency) &&
            Objects.equals(billAmount, that.billAmount) &&
            Objects.equals(billCurrencyRate, that.billCurrencyRate) &&
            Objects.equals(billAmountDollar, that.billAmountDollar) &&
            Objects.equals(isSigned, that.isSigned) &&
            Objects.equals(signUser, that.signUser) &&
            Objects.equals(signDate, that.signDate) &&
            Objects.equals(processId, that.processId) &&
            Objects.equals(printNumber, that.printNumber) &&
            Objects.equals(subcaseId, that.subcaseId) &&
            Objects.equals(claimBillStatusId, that.claimBillStatusId) &&
            Objects.equals(claimBillTypeId, that.claimBillTypeId) &&
            Objects.equals(claimBillFinanceTypeId, that.claimBillFinanceTypeId) &&
            Objects.equals(creditorId, that.creditorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        claimBillCode,
        claimBillDate,
        registerUserId,
        clientBillNo,
        billDescription,
        dueDate,
        memberYear,
        numberId,
        claimAmountCurrency,
        claimAmount,
        deductible,
        deductibleCurrency,
        deductibleCurrencyRate,
        deductibleDollar,
        billCurrency,
        billAmount,
        billCurrencyRate,
        billAmountDollar,
        isSigned,
        signUser,
        signDate,
        processId,
        printNumber,
        subcaseId,
        claimBillStatusId,
        claimBillTypeId,
        claimBillFinanceTypeId,
        creditorId
        );
    }

    @Override
    public String toString() {
        return "CaseClaimBillCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (claimBillCode != null ? "claimBillCode=" + claimBillCode + ", " : "") +
                (claimBillDate != null ? "claimBillDate=" + claimBillDate + ", " : "") +
                (registerUserId != null ? "registerUserId=" + registerUserId + ", " : "") +
                (clientBillNo != null ? "clientBillNo=" + clientBillNo + ", " : "") +
                (billDescription != null ? "billDescription=" + billDescription + ", " : "") +
                (dueDate != null ? "dueDate=" + dueDate + ", " : "") +
                (memberYear != null ? "memberYear=" + memberYear + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (claimAmountCurrency != null ? "claimAmountCurrency=" + claimAmountCurrency + ", " : "") +
                (claimAmount != null ? "claimAmount=" + claimAmount + ", " : "") +
                (deductible != null ? "deductible=" + deductible + ", " : "") +
                (deductibleCurrency != null ? "deductibleCurrency=" + deductibleCurrency + ", " : "") +
                (deductibleCurrencyRate != null ? "deductibleCurrencyRate=" + deductibleCurrencyRate + ", " : "") +
                (deductibleDollar != null ? "deductibleDollar=" + deductibleDollar + ", " : "") +
                (billCurrency != null ? "billCurrency=" + billCurrency + ", " : "") +
                (billAmount != null ? "billAmount=" + billAmount + ", " : "") +
                (billCurrencyRate != null ? "billCurrencyRate=" + billCurrencyRate + ", " : "") +
                (billAmountDollar != null ? "billAmountDollar=" + billAmountDollar + ", " : "") +
                (isSigned != null ? "isSigned=" + isSigned + ", " : "") +
                (signUser != null ? "signUser=" + signUser + ", " : "") +
                (signDate != null ? "signDate=" + signDate + ", " : "") +
                (processId != null ? "processId=" + processId + ", " : "") +
                (printNumber != null ? "printNumber=" + printNumber + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (claimBillStatusId != null ? "claimBillStatusId=" + claimBillStatusId + ", " : "") +
                (claimBillTypeId != null ? "claimBillTypeId=" + claimBillTypeId + ", " : "") +
                (claimBillFinanceTypeId != null ? "claimBillFinanceTypeId=" + claimBillFinanceTypeId + ", " : "") +
                (creditorId != null ? "creditorId=" + creditorId + ", " : "") +
            "}";
    }

}
