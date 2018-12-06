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
 * Criteria class for the CaseRecovery entity. This class is used in CaseRecoveryResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-recoveries?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseRecoveryCriteria implements Serializable {
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

    public CaseRecoveryCriteria() {
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
