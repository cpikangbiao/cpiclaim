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
 * Criteria class for the {@link com.cpi.claim.domain.CaseFeeBill} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseFeeBillResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-fee-bills?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseFeeBillCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter numberId;

    private BooleanFilter isWriteOff;

    private LongFilter caseFeeId;

    private LongFilter caseClaimBillId;

    private LongFilter writeOffBillId;

    public CaseFeeBillCriteria(){
    }

    public CaseFeeBillCriteria(CaseFeeBillCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.isWriteOff = other.isWriteOff == null ? null : other.isWriteOff.copy();
        this.caseFeeId = other.caseFeeId == null ? null : other.caseFeeId.copy();
        this.caseClaimBillId = other.caseClaimBillId == null ? null : other.caseClaimBillId.copy();
        this.writeOffBillId = other.writeOffBillId == null ? null : other.writeOffBillId.copy();
    }

    @Override
    public CaseFeeBillCriteria copy() {
        return new CaseFeeBillCriteria(this);
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

    public LongFilter getCaseFeeId() {
        return caseFeeId;
    }

    public void setCaseFeeId(LongFilter caseFeeId) {
        this.caseFeeId = caseFeeId;
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
        final CaseFeeBillCriteria that = (CaseFeeBillCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(isWriteOff, that.isWriteOff) &&
            Objects.equals(caseFeeId, that.caseFeeId) &&
            Objects.equals(caseClaimBillId, that.caseClaimBillId) &&
            Objects.equals(writeOffBillId, that.writeOffBillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        isWriteOff,
        caseFeeId,
        caseClaimBillId,
        writeOffBillId
        );
    }

    @Override
    public String toString() {
        return "CaseFeeBillCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (isWriteOff != null ? "isWriteOff=" + isWriteOff + ", " : "") +
                (caseFeeId != null ? "caseFeeId=" + caseFeeId + ", " : "") +
                (caseClaimBillId != null ? "caseClaimBillId=" + caseClaimBillId + ", " : "") +
                (writeOffBillId != null ? "writeOffBillId=" + writeOffBillId + ", " : "") +
            "}";
    }

}
