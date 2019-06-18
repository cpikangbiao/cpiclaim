package com.cpi.claim.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseClaimBillPrintLog entity.
 */
public class CaseClaimBillPrintLogDTO implements Serializable {

    private Long id;

    private String operateType;

    private String operateUser;

    private Instant operateDate;

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

    public Instant getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Instant operateDate) {
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

        CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO = (CaseClaimBillPrintLogDTO) o;
        if (caseClaimBillPrintLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillPrintLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillPrintLogDTO{" +
            "id=" + getId() +
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", caseClaimBill=" + getCaseClaimBillId() +
            ", caseClaimBill='" + getCaseClaimBillClaimBillCode() + "'" +
            "}";
    }
}
