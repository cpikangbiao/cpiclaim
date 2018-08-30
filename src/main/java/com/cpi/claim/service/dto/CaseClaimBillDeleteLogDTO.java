package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseClaimBillDeleteLog entity.
 */
public class CaseClaimBillDeleteLogDTO implements Serializable {

    private Long id;

    private String operateType;

    private String operateUser;

    private String operateDate;

    private Long caseClaimBillId;

    private String caseClaimBillClaimBillCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public Long getCaseClaimBillId() {
        return caseClaimBillId;
    }

    public void setCaseClaimBillId(Long caseClaimBillId) {
        this.caseClaimBillId = caseClaimBillId;
    }

    public String getCaseClaimBillClaimBillCode() {
        return caseClaimBillClaimBillCode;
    }

    public void setCaseClaimBillClaimBillCode(String caseClaimBillClaimBillCode) {
        this.caseClaimBillClaimBillCode = caseClaimBillClaimBillCode;
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
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", caseClaimBill=" + getCaseClaimBillId() +
            ", caseClaimBill='" + getCaseClaimBillClaimBillCode() + "'" +
            "}";
    }
}
