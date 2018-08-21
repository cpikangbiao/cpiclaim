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
 * Criteria class for the VesselSubCase entity. This class is used in VesselSubCaseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vessel-sub-cases?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VesselSubCaseCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter numberId;

    private LongFilter assignedUserId;

    private InstantFilter insertTime;

    private StringFilter subcaseCode;

    private StringFilter blNo;

    private StringFilter claimant;

    private StringFilter claimAmount;

    private LongFilter currency;

    private BigDecimalFilter deductible;

    private DoubleFilter currencyRate;

    private BigDecimalFilter deductDollar;

    private LongFilter vesselCaseId;

    private LongFilter riskId;

    public VesselSubCaseCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public LongFilter getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(LongFilter assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public InstantFilter getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(InstantFilter insertTime) {
        this.insertTime = insertTime;
    }

    public StringFilter getSubcaseCode() {
        return subcaseCode;
    }

    public void setSubcaseCode(StringFilter subcaseCode) {
        this.subcaseCode = subcaseCode;
    }

    public StringFilter getBlNo() {
        return blNo;
    }

    public void setBlNo(StringFilter blNo) {
        this.blNo = blNo;
    }

    public StringFilter getClaimant() {
        return claimant;
    }

    public void setClaimant(StringFilter claimant) {
        this.claimant = claimant;
    }

    public StringFilter getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(StringFilter claimAmount) {
        this.claimAmount = claimAmount;
    }

    public LongFilter getCurrency() {
        return currency;
    }

    public void setCurrency(LongFilter currency) {
        this.currency = currency;
    }

    public BigDecimalFilter getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimalFilter deductible) {
        this.deductible = deductible;
    }

    public DoubleFilter getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(DoubleFilter currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimalFilter getDeductDollar() {
        return deductDollar;
    }

    public void setDeductDollar(BigDecimalFilter deductDollar) {
        this.deductDollar = deductDollar;
    }

    public LongFilter getVesselCaseId() {
        return vesselCaseId;
    }

    public void setVesselCaseId(LongFilter vesselCaseId) {
        this.vesselCaseId = vesselCaseId;
    }

    public LongFilter getRiskId() {
        return riskId;
    }

    public void setRiskId(LongFilter riskId) {
        this.riskId = riskId;
    }

    @Override
    public String toString() {
        return "VesselSubCaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (assignedUserId != null ? "assignedUserId=" + assignedUserId + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (subcaseCode != null ? "subcaseCode=" + subcaseCode + ", " : "") +
                (blNo != null ? "blNo=" + blNo + ", " : "") +
                (claimant != null ? "claimant=" + claimant + ", " : "") +
                (claimAmount != null ? "claimAmount=" + claimAmount + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (deductible != null ? "deductible=" + deductible + ", " : "") +
                (currencyRate != null ? "currencyRate=" + currencyRate + ", " : "") +
                (deductDollar != null ? "deductDollar=" + deductDollar + ", " : "") +
                (vesselCaseId != null ? "vesselCaseId=" + vesselCaseId + ", " : "") +
                (riskId != null ? "riskId=" + riskId + ", " : "") +
            "}";
    }

}
