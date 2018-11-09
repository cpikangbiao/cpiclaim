package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the CaseRecoveryBill entity. This class is used in CaseRecoveryBillResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-recovery-bills?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseRecoveryBillCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter numberId;

    private BooleanFilter isWriteOff;

    private LongFilter caseRecoveryId;

    private LongFilter caseClaimBillId;

    private LongFilter writeOffBillId;

    public CaseRecoveryBillCriteria() {
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
