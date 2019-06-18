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

/**
 * Criteria class for the {@link com.cpi.claim.domain.CaseRecoveryBill} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseRecoveryBillResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-recovery-bills?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseRecoveryBillCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter numberId;

    private BooleanFilter isWriteOff;

    private LongFilter caseRecoveryId;

    private LongFilter caseClaimBillId;

    private LongFilter writeOffBillId;

    public CaseRecoveryBillCriteria(){
    }

    public CaseRecoveryBillCriteria(CaseRecoveryBillCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.isWriteOff = other.isWriteOff == null ? null : other.isWriteOff.copy();
        this.caseRecoveryId = other.caseRecoveryId == null ? null : other.caseRecoveryId.copy();
        this.caseClaimBillId = other.caseClaimBillId == null ? null : other.caseClaimBillId.copy();
        this.writeOffBillId = other.writeOffBillId == null ? null : other.writeOffBillId.copy();
    }

    @Override
    public CaseRecoveryBillCriteria copy() {
        return new CaseRecoveryBillCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public BooleanFilter getIsWriteOff() {
        return isWriteOff;
    }

    public void setIsWriteOff(BooleanFilter isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public LongFilter getCaseRecoveryId() {
        return caseRecoveryId;
    }

    public void setCaseRecoveryId(LongFilter caseRecoveryId) {
        this.caseRecoveryId = caseRecoveryId;
    }

    public LongFilter getCaseClaimBillId() {
        return caseClaimBillId;
    }

    public void setCaseClaimBillId(LongFilter caseClaimBillId) {
        this.caseClaimBillId = caseClaimBillId;
    }

    public LongFilter getWriteOffBillId() {
        return writeOffBillId;
    }

    public void setWriteOffBillId(LongFilter writeOffBillId) {
        this.writeOffBillId = writeOffBillId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseRecoveryBillCriteria that = (CaseRecoveryBillCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(isWriteOff, that.isWriteOff) &&
            Objects.equals(caseRecoveryId, that.caseRecoveryId) &&
            Objects.equals(caseClaimBillId, that.caseClaimBillId) &&
            Objects.equals(writeOffBillId, that.writeOffBillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        isWriteOff,
        caseRecoveryId,
        caseClaimBillId,
        writeOffBillId
        );
    }

    @Override
    public String toString() {
        return "CaseRecoveryBillCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (isWriteOff != null ? "isWriteOff=" + isWriteOff + ", " : "") +
                (caseRecoveryId != null ? "caseRecoveryId=" + caseRecoveryId + ", " : "") +
                (caseClaimBillId != null ? "caseClaimBillId=" + caseClaimBillId + ", " : "") +
                (writeOffBillId != null ? "writeOffBillId=" + writeOffBillId + ", " : "") +
            "}";
    }

}
