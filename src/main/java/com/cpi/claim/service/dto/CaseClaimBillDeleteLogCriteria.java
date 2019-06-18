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
 * Criteria class for the {@link com.cpi.claim.domain.CaseClaimBillDeleteLog} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseClaimBillDeleteLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-claim-bill-delete-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillDeleteLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter claimBillCode;

    private StringFilter operateType;

    private StringFilter operateUser;

    private InstantFilter operateDate;

    public CaseClaimBillDeleteLogCriteria(){
    }

    public CaseClaimBillDeleteLogCriteria(CaseClaimBillDeleteLogCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.claimBillCode = other.claimBillCode == null ? null : other.claimBillCode.copy();
        this.operateType = other.operateType == null ? null : other.operateType.copy();
        this.operateUser = other.operateUser == null ? null : other.operateUser.copy();
        this.operateDate = other.operateDate == null ? null : other.operateDate.copy();
    }

    @Override
    public CaseClaimBillDeleteLogCriteria copy() {
        return new CaseClaimBillDeleteLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getClaimBillCode() {
        return claimBillCode;
    }

    public void setClaimBillCode(StringFilter claimBillCode) {
        this.claimBillCode = claimBillCode;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseClaimBillDeleteLogCriteria that = (CaseClaimBillDeleteLogCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(claimBillCode, that.claimBillCode) &&
            Objects.equals(operateType, that.operateType) &&
            Objects.equals(operateUser, that.operateUser) &&
            Objects.equals(operateDate, that.operateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        claimBillCode,
        operateType,
        operateUser,
        operateDate
        );
    }

    @Override
    public String toString() {
        return "CaseClaimBillDeleteLogCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (claimBillCode != null ? "claimBillCode=" + claimBillCode + ", " : "") +
                (operateType != null ? "operateType=" + operateType + ", " : "") +
                (operateUser != null ? "operateUser=" + operateUser + ", " : "") +
                (operateDate != null ? "operateDate=" + operateDate + ", " : "") +
            "}";
    }

}
