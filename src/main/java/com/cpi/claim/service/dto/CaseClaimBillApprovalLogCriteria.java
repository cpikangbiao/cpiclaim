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
 * Criteria class for the {@link com.cpi.claim.domain.CaseClaimBillApprovalLog} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseClaimBillApprovalLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-claim-bill-approval-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillApprovalLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter processId;

    private InstantFilter insertTime;

    private StringFilter approvalUser;

    private StringFilter approvalOpinion;

    private StringFilter approvalTransition;

    private StringFilter remark;

    private LongFilter caseClaimBillId;

    public CaseClaimBillApprovalLogCriteria(){
    }

    public CaseClaimBillApprovalLogCriteria(CaseClaimBillApprovalLogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.processId = other.processId == null ? null : other.processId.copy();
        this.insertTime = other.insertTime == null ? null : other.insertTime.copy();
        this.approvalUser = other.approvalUser == null ? null : other.approvalUser.copy();
        this.approvalOpinion = other.approvalOpinion == null ? null : other.approvalOpinion.copy();
        this.approvalTransition = other.approvalTransition == null ? null : other.approvalTransition.copy();
        this.remark = other.remark == null ? null : other.remark.copy();
        this.caseClaimBillId = other.caseClaimBillId == null ? null : other.caseClaimBillId.copy();
    }

    @Override
    public CaseClaimBillApprovalLogCriteria copy() {
        return new CaseClaimBillApprovalLogCriteria(this);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseClaimBillApprovalLogCriteria that = (CaseClaimBillApprovalLogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(processId, that.processId) &&
            Objects.equals(insertTime, that.insertTime) &&
            Objects.equals(approvalUser, that.approvalUser) &&
            Objects.equals(approvalOpinion, that.approvalOpinion) &&
            Objects.equals(approvalTransition, that.approvalTransition) &&
            Objects.equals(remark, that.remark) &&
            Objects.equals(caseClaimBillId, that.caseClaimBillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        processId,
        insertTime,
        approvalUser,
        approvalOpinion,
        approvalTransition,
        remark,
        caseClaimBillId
        );
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
