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
 * Criteria class for the {@link com.cpi.claim.domain.CaseClaim} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseClaimResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-claims?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter numberId;

    private StringFilter claimer;

    private InstantFilter claimDate;

    private StringFilter billOfLading;

    private LongFilter currencyId;

    private BigDecimalFilter currencyRate;

    private BigDecimalFilter claimCost;

    private BigDecimalFilter claimCostDollar;

    private LongFilter subcaseId;

    public CaseClaimCriteria(){
    }

    public CaseClaimCriteria(CaseClaimCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.claimer = other.claimer == null ? null : other.claimer.copy();
        this.claimDate = other.claimDate == null ? null : other.claimDate.copy();
        this.billOfLading = other.billOfLading == null ? null : other.billOfLading.copy();
        this.currencyId = other.currencyId == null ? null : other.currencyId.copy();
        this.currencyRate = other.currencyRate == null ? null : other.currencyRate.copy();
        this.claimCost = other.claimCost == null ? null : other.claimCost.copy();
        this.claimCostDollar = other.claimCostDollar == null ? null : other.claimCostDollar.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
    }

    @Override
    public CaseClaimCriteria copy() {
        return new CaseClaimCriteria(this);
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

    public StringFilter getClaimer() {
        return claimer;
    }

    public void setClaimer(StringFilter claimer) {
        this.claimer = claimer;
    }

    public InstantFilter getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(InstantFilter claimDate) {
        this.claimDate = claimDate;
    }

    public StringFilter getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(StringFilter billOfLading) {
        this.billOfLading = billOfLading;
    }

    public LongFilter getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(LongFilter currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimalFilter getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimalFilter currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimalFilter getClaimCost() {
        return claimCost;
    }

    public void setClaimCost(BigDecimalFilter claimCost) {
        this.claimCost = claimCost;
    }

    public BigDecimalFilter getClaimCostDollar() {
        return claimCostDollar;
    }

    public void setClaimCostDollar(BigDecimalFilter claimCostDollar) {
        this.claimCostDollar = claimCostDollar;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseClaimCriteria that = (CaseClaimCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(claimer, that.claimer) &&
            Objects.equals(claimDate, that.claimDate) &&
            Objects.equals(billOfLading, that.billOfLading) &&
            Objects.equals(currencyId, that.currencyId) &&
            Objects.equals(currencyRate, that.currencyRate) &&
            Objects.equals(claimCost, that.claimCost) &&
            Objects.equals(claimCostDollar, that.claimCostDollar) &&
            Objects.equals(subcaseId, that.subcaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        claimer,
        claimDate,
        billOfLading,
        currencyId,
        currencyRate,
        claimCost,
        claimCostDollar,
        subcaseId
        );
    }

    @Override
    public String toString() {
        return "CaseClaimCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (claimer != null ? "claimer=" + claimer + ", " : "") +
                (claimDate != null ? "claimDate=" + claimDate + ", " : "") +
                (billOfLading != null ? "billOfLading=" + billOfLading + ", " : "") +
                (currencyId != null ? "currencyId=" + currencyId + ", " : "") +
                (currencyRate != null ? "currencyRate=" + currencyRate + ", " : "") +
                (claimCost != null ? "claimCost=" + claimCost + ", " : "") +
                (claimCostDollar != null ? "claimCostDollar=" + claimCostDollar + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
            "}";
    }

}
