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
 * Criteria class for the CaseClaimBillDeleteLog entity. This class is used in CaseClaimBillDeleteLogResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-claim-bill-delete-logs?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseClaimBillDeleteLogCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter claimBillCode;

    private StringFilter operateType;

    private StringFilter operateUser;

    private InstantFilter operateDate;

    public CaseClaimBillDeleteLogCriteria() {
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
