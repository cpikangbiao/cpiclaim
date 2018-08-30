package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the CaseCloseLog entity. This class is used in CaseCloseLogResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-close-logs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseCloseLogCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter operateUserId;

    private StringFilter operateDate;

    private StringFilter operateType;

    private LongFilter vesselCaseId;

    public CaseCloseLogCriteria() {
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

    public StringFilter getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(StringFilter operateDate) {
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
