package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseCloseLog entity.
 */
public class CaseCloseLogDTO implements Serializable {

    private Long id;

    private String operateUserId;

    private String operateDate;

    private String operateType;

    private Long vesselCaseId;

    private String vesselCaseCaseCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
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

        CaseCloseLogDTO caseCloseLogDTO = (CaseCloseLogDTO) o;
        if (caseCloseLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseCloseLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseCloseLogDTO{" +
            "id=" + getId() +
            ", operateUserId='" + getOperateUserId() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", operateType='" + getOperateType() + "'" +
            ", vesselCase=" + getVesselCaseId() +
            ", vesselCase='" + getVesselCaseCaseCode() + "'" +
            "}";
    }
}
