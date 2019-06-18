package com.cpi.claim.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.CaseClaimBillApprovalLog} entity.
 */
public class CaseClaimBillApprovalLogDTO implements Serializable {

    private Long id;

    private Long processId;

    private Instant insertTime;

    private String approvalUser;

    private String approvalOpinion;

    private String approvalTransition;

    private String remark;


    private Long caseClaimBillId;

    private String caseClaimBillClaimBillCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public String getApprovalTransition() {
        return approvalTransition;
    }

    public void setApprovalTransition(String approvalTransition) {
        this.approvalTransition = approvalTransition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

        CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO = (CaseClaimBillApprovalLogDTO) o;
        if (caseClaimBillApprovalLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillApprovalLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillApprovalLogDTO{" +
            "id=" + getId() +
            ", processId=" + getProcessId() +
            ", insertTime='" + getInsertTime() + "'" +
            ", approvalUser='" + getApprovalUser() + "'" +
            ", approvalOpinion='" + getApprovalOpinion() + "'" +
            ", approvalTransition='" + getApprovalTransition() + "'" +
            ", remark='" + getRemark() + "'" +
            ", caseClaimBill=" + getCaseClaimBillId() +
            ", caseClaimBill='" + getCaseClaimBillClaimBillCode() + "'" +
            "}";
    }
}
