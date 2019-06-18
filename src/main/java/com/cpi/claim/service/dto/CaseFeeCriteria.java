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
 * Criteria class for the CaseFee entity. This class is used in CaseFeeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-fees?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseFeeCriteria implements Serializable {
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

    public CaseFeeCriteria() {
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
