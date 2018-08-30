package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseAssignLog entity.
 */
public class CaseAssignLogDTO implements Serializable {

    private Long id;

    private String numberId;

    private String assignUser;

    private String assignTime;

    private String assignedUser;

    private Long vesselCaseId;

    private String vesselCaseCaseCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Long getVesselCaseId() {
        return vesselCaseId;
    }

    public void setVesselCaseId(Long vesselCaseId) {
        this.vesselCaseId = vesselCaseId;
    }

    public String getVesselCaseCaseCode() {
        return vesselCaseCaseCode;
    }

    public void setVesselCaseCaseCode(String vesselCaseCaseCode) {
        this.vesselCaseCaseCode = vesselCaseCaseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseAssignLogDTO caseAssignLogDTO = (CaseAssignLogDTO) o;
        if (caseAssignLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseAssignLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseAssignLogDTO{" +
            "id=" + getId() +
            ", numberId='" + getNumberId() + "'" +
            ", assignUser='" + getAssignUser() + "'" +
            ", assignTime='" + getAssignTime() + "'" +
            ", assignedUser='" + getAssignedUser() + "'" +
            ", vesselCase=" + getVesselCaseId() +
            ", vesselCase='" + getVesselCaseCaseCode() + "'" +
            "}";
    }
}
