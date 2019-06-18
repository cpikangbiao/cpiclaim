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
 * Criteria class for the {@link com.cpi.claim.domain.CaseClaimBillPrintLog} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseClaimBillPrintLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-claim-bill-print-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillPrintLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter operateType;

    private StringFilter operateUser;

    private InstantFilter operateDate;

    private LongFilter caseClaimBillId;

    public CaseClaimBillPrintLogCriteria(){
    }

    public CaseClaimBillPrintLogCriteria(CaseClaimBillPrintLogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.operateType = other.operateType == null ? null : other.operateType.copy();
        this.operateUser = other.operateUser == null ? null : other.operateUser.copy();
        this.operateDate = other.operateDate == null ? null : other.operateDate.copy();
        this.caseClaimBillId = other.caseClaimBillId == null ? null : other.caseClaimBillId.copy();
    }

    @Override
    public CaseClaimBillPrintLogCriteria copy() {
        return new CaseClaimBillPrintLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getOperateType() {
        return operateType;
    }

    public void setOperateType(StringFilter operateType) {
        this.operateType = operateType;
    }

    public StringFilter getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(StringFilter operateUser) {
        this.operateUser = operateUser;
    }

    public InstantFilter getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(InstantFilter operateDate) {
        this.operateDate = operateDate;
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
        final CaseClaimBillPrintLogCriteria that = (CaseClaimBillPrintLogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(operateType, that.operateType) &&
            Objects.equals(operateUser, that.operateUser) &&
            Objects.equals(operateDate, that.operateDate) &&
            Objects.equals(caseClaimBillId, that.caseClaimBillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        operateType,
        operateUser,
        operateDate,
        caseClaimBillId
        );
    }

    @Override
    public String toString() {
        return "CaseClaimBillPrintLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (operateType != null ? "operateType=" + operateType + ", " : "") +
                (operateUser != null ? "operateUser=" + operateUser + ", " : "") +
                (operateDate != null ? "operateDate=" + operateDate + ", " : "") +
                (caseClaimBillId != null ? "caseClaimBillId=" + caseClaimBillId + ", " : "") +
            "}";
    }

}
