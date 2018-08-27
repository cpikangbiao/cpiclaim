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
 * Criteria class for the CaseEstimate entity. This class is used in CaseEstimateResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-estimates?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseEstimateCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter numberId;

    private LongFilter registerUserId;

    private InstantFilter estimateDate;

    private BigDecimalFilter estimateEntityFee;

    private BigDecimalFilter estimateCostFee;

    private LongFilter subcaseId;

    public CaseEstimateCriteria() {
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
