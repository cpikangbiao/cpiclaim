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
 * Criteria class for the CaseRegisterLog entity. This class is used in CaseRegisterLogResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-register-logs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseRegisterLogCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter numberId;

    private StringFilter assignUser;

    private StringFilter assignTime;

    private StringFilter assignedUser;

    private LongFilter vesselCaseId;

    public CaseRegisterLogCriteria() {
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

    public StringFilter getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(StringFilter assignTime) {
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
    public String toString() {
        return "CaseRegisterLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (assignUser != null ? "assignUser=" + assignUser + ", " : "") +
                (assignTime != null ? "assignTime=" + assignTime + ", " : "") +
                (assignedUser != null ? "assignedUser=" + assignedUser + ", " : "") +
                (vesselCaseId != null ? "vesselCaseId=" + vesselCaseId + ", " : "") +
            "}";
    }

}