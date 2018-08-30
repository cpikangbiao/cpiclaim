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
 * Criteria class for the CaseClaimBill entity. This class is used in CaseClaimBillResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-claim-bills?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillCriteria implements Serializable {
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

    private DoubleFilter deductibleCurrencyRate;

    private BigDecimalFilter deductibleDollar;

    private LongFilter billCurrency;

    private BigDecimalFilter billAmount;

    private DoubleFilter billCurrencyRate;

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

    public CaseClaimBillCriteria() {
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

    public DoubleFilter getDeductibleCurrencyRate() {
        return deductibleCurrencyRate;
    }

    public void setDeductibleCurrencyRate(DoubleFilter deductibleCurrencyRate) {
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

    public DoubleFilter getBillCurrencyRate() {
        return billCurrencyRate;
    }

    public void setBillCurrencyRate(DoubleFilter billCurrencyRate) {
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
