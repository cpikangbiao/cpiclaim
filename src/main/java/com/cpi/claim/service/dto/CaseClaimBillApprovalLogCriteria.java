package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the CaseClaimBillApprovalLog entity. This class is used in CaseClaimBillApprovalLogResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-claim-bill-approval-logs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillApprovalLogCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private LongFilter processId;

    private InstantFilter insertTime;

    private StringFilter approvalUser;

    private StringFilter approvalOpinion;

    private StringFilter approvalTransition;

    private StringFilter remark;

    private LongFilter caseClaimBillId;

    public CaseClaimBillApprovalLogCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getProcessId() {
        return processId;
    }

    public void setProcessId(LongFilter processId) {
        this.processId = processId;
    }

    public InstantFilter getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(InstantFilter insertTime) {
        this.insertTime = insertTime;
    }

    public StringFilter getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(StringFilter approvalUser) {
        this.approvalUser = approvalUser;
    }

    public StringFilter getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(StringFilter approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public StringFilter getApprovalTransition() {
        return approvalTransition;
    }

    public void setApprovalTransition(StringFilter approvalTransition) {
        this.approvalTransition = approvalTransition;
    }

    public StringFilter getRemark() {
        return remark;
    }

    public void setRemark(StringFilter remark) {
        this.remark = remark;
    }

    public LongFilter getCaseClaimBillId() {
        return caseClaimBillId;
    }

    public void setCaseClaimBillId(LongFilter caseClaimBillId) {
        this.caseClaimBillId = caseClaimBillId;
    }

    @Override
    public String toString() {
        return "CaseClaimBillApprovalLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (processId != null ? "processId=" + processId + ", " : "") +
                (insertTime != null ? "insertTime=" + insertTime + ", " : "") +
                (approvalUser != null ? "approvalUser=" + approvalUser + ", " : "") +
                (approvalOpinion != null ? "approvalOpinion=" + approvalOpinion + ", " : "") +
                (approvalTransition != null ? "approvalTransition=" + approvalTransition + ", " : "") +
                (remark != null ? "remark=" + remark + ", " : "") +
                (caseClaimBillId != null ? "caseClaimBillId=" + caseClaimBillId + ", " : "") +
            "}";
    }

}
