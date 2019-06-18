package com.cpi.claim.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseClaimBillDeleteLog entity.
 */
public class CaseClaimBillDeleteLogDTO implements Serializable {

    private Long id;

    private String claimBillCode;

    private String operateType;

    private String operateUser;

    private Instant operateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimBillCode() {
        return claimBillCode;
    }

    public void setClaimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Instant getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Instant operateDate) {
        this.operateDate = operateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = (CaseClaimBillDeleteLogDTO) o;
        if (caseClaimBillDeleteLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillDeleteLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillDeleteLogDTO{" +
            "id=" + getId() +
            ", claimBillCode='" + getClaimBillCode() + "'" +
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            "}";
    }
}
