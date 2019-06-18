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
 * Criteria class for the CaseClaim entity. This class is used in CaseClaimResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-claims?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimCriteria implements Serializable {
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

    public CaseClaimCriteria() {
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
