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
 * Criteria class for the {@link com.cpi.claim.domain.CaseEstimate} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseEstimateResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-estimates?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseEstimateCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter numberId;

    private LongFilter registerUserId;

    private InstantFilter estimateDate;

    private BigDecimalFilter estimateEntityFee;

    private BigDecimalFilter estimateCostFee;

    private LongFilter subcaseId;

    public CaseEstimateCriteria(){
    }

    public CaseEstimateCriteria(CaseEstimateCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.registerUserId = other.registerUserId == null ? null : other.registerUserId.copy();
        this.estimateDate = other.estimateDate == null ? null : other.estimateDate.copy();
        this.estimateEntityFee = other.estimateEntityFee == null ? null : other.estimateEntityFee.copy();
        this.estimateCostFee = other.estimateCostFee == null ? null : other.estimateCostFee.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
    }

    @Override
    public CaseEstimateCriteria copy() {
        return new CaseEstimateCriteria(this);
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

    public LongFilter getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(LongFilter registerUserId) {
        this.registerUserId = registerUserId;
    }

    public InstantFilter getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(InstantFilter estimateDate) {
        this.estimateDate = estimateDate;
    }

    public BigDecimalFilter getEstimateEntityFee() {
        return estimateEntityFee;
    }

    public void setEstimateEntityFee(BigDecimalFilter estimateEntityFee) {
        this.estimateEntityFee = estimateEntityFee;
    }

    public BigDecimalFilter getEstimateCostFee() {
        return estimateCostFee;
    }

    public void setEstimateCostFee(BigDecimalFilter estimateCostFee) {
        this.estimateCostFee = estimateCostFee;
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
        final CaseEstimateCriteria that = (CaseEstimateCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(registerUserId, that.registerUserId) &&
            Objects.equals(estimateDate, that.estimateDate) &&
            Objects.equals(estimateEntityFee, that.estimateEntityFee) &&
            Objects.equals(estimateCostFee, that.estimateCostFee) &&
            Objects.equals(subcaseId, that.subcaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        registerUserId,
        estimateDate,
        estimateEntityFee,
        estimateCostFee,
        subcaseId
        );
    }

    @Override
    public String toString() {
        return "CaseEstimateCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (registerUserId != null ? "registerUserId=" + registerUserId + ", " : "") +
                (estimateDate != null ? "estimateDate=" + estimateDate + ", " : "") +
                (estimateEntityFee != null ? "estimateEntityFee=" + estimateEntityFee + ", " : "") +
                (estimateCostFee != null ? "estimateCostFee=" + estimateCostFee + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
            "}";
    }

}
