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
 * Criteria class for the {@link com.cpi.claim.domain.CaseAssignLog} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseAssignLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-assign-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseAssignLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter numberId;

    private StringFilter assignUser;

    private InstantFilter assignTime;

    private StringFilter assignedUser;

    private LongFilter vesselCaseId;

    public CaseAssignLogCriteria(){
    }

    public CaseAssignLogCriteria(CaseAssignLogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.assignUser = other.assignUser == null ? null : other.assignUser.copy();
        this.assignTime = other.assignTime == null ? null : other.assignTime.copy();
        this.assignedUser = other.assignedUser == null ? null : other.assignedUser.copy();
        this.vesselCaseId = other.vesselCaseId == null ? null : other.vesselCaseId.copy();
    }

    @Override
    public CaseAssignLogCriteria copy() {
        return new CaseAssignLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(StringFilter numberId) {
        this.numberId = numberId;
    }

    public StringFilter getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringFilter assignUser) {
        this.assignUser = assignUser;
    }

    public InstantFilter getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(InstantFilter assignTime) {
        this.assignTime = assignTime;
    }

    public StringFilter getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(StringFilter assignedUser) {
        this.assignedUser = assignedUser;
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
        final CaseAssignLogCriteria that = (CaseAssignLogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(assignUser, that.assignUser) &&
            Objects.equals(assignTime, that.assignTime) &&
            Objects.equals(assignedUser, that.assignedUser) &&
            Objects.equals(vesselCaseId, that.vesselCaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        assignUser,
        assignTime,
        assignedUser,
        vesselCaseId
        );
    }

    @Override
    public String toString() {
        return "CaseAssignLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (assignUser != null ? "assignUser=" + assignUser + ", " : "") +
                (assignTime != null ? "assignTime=" + assignTime + ", " : "") +
                (assignedUser != null ? "assignedUser=" + assignedUser + ", " : "") +
                (vesselCaseId != null ? "vesselCaseId=" + vesselCaseId + ", " : "") +
            "}";
    }

}
