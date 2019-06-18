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
 * Criteria class for the {@link com.cpi.claim.domain.VesselSubCase} entity. This class is used
 * in {@link com.cpi.claim.web.rest.VesselSubCaseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /vessel-sub-cases?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VesselSubCaseCriteria implements Serializable, Criteria {

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

    private BigDecimalFilter currencyRate;

    private BigDecimalFilter deductDollar;

    private LongFilter vesselCaseId;

    private LongFilter riskId;

    public VesselSubCaseCriteria(){
    }

    public VesselSubCaseCriteria(VesselSubCaseCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.assignedUserId = other.assignedUserId == null ? null : other.assignedUserId.copy();
        this.insertTime = other.insertTime == null ? null : other.insertTime.copy();
        this.subcaseCode = other.subcaseCode == null ? null : other.subcaseCode.copy();
        this.blNo = other.blNo == null ? null : other.blNo.copy();
        this.claimant = other.claimant == null ? null : other.claimant.copy();
        this.claimAmount = other.claimAmount == null ? null : other.claimAmount.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.deductible = other.deductible == null ? null : other.deductible.copy();
        this.currencyRate = other.currencyRate == null ? null : other.currencyRate.copy();
        this.deductDollar = other.deductDollar == null ? null : other.deductDollar.copy();
        this.vesselCaseId = other.vesselCaseId == null ? null : other.vesselCaseId.copy();
        this.riskId = other.riskId == null ? null : other.riskId.copy();
    }

    @Override
    public VesselSubCaseCriteria copy() {
        return new VesselSubCaseCriteria(this);
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

    public BigDecimalFilter getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimalFilter currencyRate) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VesselSubCaseCriteria that = (VesselSubCaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(assignedUserId, that.assignedUserId) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(subcaseCode, that.subcaseCode) &&
            Objects.equals(blNo, that.blNo) &&
            Objects.equals(claimant, that.claimant) &&
            Objects.equals(claimAmount, that.claimAmount) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(deductible, that.deductible) &&
            Objects.equals(currencyRate, that.currencyRate) &&
            Objects.equals(deductDollar, that.deductDollar) &&
            Objects.equals(vesselCaseId, that.vesselCaseId) &&
            Objects.equals(riskId, that.riskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        assignedUserId,
        insertTime,
        subcaseCode,
        blNo,
        claimant,
        claimAmount,
        currency,
        deductible,
        currencyRate,
        deductDollar,
        vesselCaseId,
        riskId
        );
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
