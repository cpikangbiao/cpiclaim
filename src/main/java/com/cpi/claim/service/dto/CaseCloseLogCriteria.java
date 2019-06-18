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
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.cpi.claim.domain.CaseCloseLog} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseCloseLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-close-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseCloseLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter operateUserId;

    private InstantFilter operateDate;

    private StringFilter operateType;

    private LongFilter vesselCaseId;

    public CaseCloseLogCriteria(){
    }

    public CaseCloseLogCriteria(CaseCloseLogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.operateUserId = other.operateUserId == null ? null : other.operateUserId.copy();
        this.operateDate = other.operateDate == null ? null : other.operateDate.copy();
        this.operateType = other.operateType == null ? null : other.operateType.copy();
        this.vesselCaseId = other.vesselCaseId == null ? null : other.vesselCaseId.copy();
    }

    @Override
    public CaseCloseLogCriteria copy() {
        return new CaseCloseLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(StringFilter operateUserId) {
        this.operateUserId = operateUserId;
    }

    public InstantFilter getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(InstantFilter operateDate) {
        this.operateDate = operateDate;
    }

    public StringFilter getOperateType() {
        return operateType;
    }

    public void setOperateType(StringFilter operateType) {
        this.operateType = operateType;
    }

    public LongFilter getVesselCaseId() {
        return vesselCaseId;
    }

    public void setVesselCaseId(LongFilter vesselCaseId) {
        this.vesselCaseId = vesselCaseId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseCloseLogCriteria that = (CaseCloseLogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(operateUserId, that.operateUserId) &&
            Objects.equals(operateDate, that.operateDate) &&
            Objects.equals(operateType, that.operateType) &&
            Objects.equals(vesselCaseId, that.vesselCaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        operateUserId,
        operateDate,
        operateType,
        vesselCaseId
        );
    }

    @Override
    public String toString() {
        return "CaseCloseLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (operateUserId != null ? "operateUserId=" + operateUserId + ", " : "") +
                (operateDate != null ? "operateDate=" + operateDate + ", " : "") +
                (operateType != null ? "operateType=" + operateType + ", " : "") +
                (vesselCaseId != null ? "vesselCaseId=" + vesselCaseId + ", " : "") +
            "}";
    }

}
