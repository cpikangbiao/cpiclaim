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
 * Criteria class for the {@link com.cpi.claim.domain.ClaimBillFinanceType} entity. This class is used
 * in {@link com.cpi.claim.web.rest.ClaimBillFinanceTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /claim-bill-finance-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClaimBillFinanceTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter claimBillFinanceTypeName;

    public ClaimBillFinanceTypeCriteria(){
    }

    public ClaimBillFinanceTypeCriteria(ClaimBillFinanceTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.claimBillFinanceTypeName = other.claimBillFinanceTypeName == null ? null : other.claimBillFinanceTypeName.copy();
    }

    @Override
    public ClaimBillFinanceTypeCriteria copy() {
        return new ClaimBillFinanceTypeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSortNum() {
        return sortNum;
    }

    public void setSortNum(IntegerFilter sortNum) {
        this.sortNum = sortNum;
    }

    public StringFilter getClaimBillFinanceTypeName() {
        return claimBillFinanceTypeName;
    }

    public void setClaimBillFinanceTypeName(StringFilter claimBillFinanceTypeName) {
        this.claimBillFinanceTypeName = claimBillFinanceTypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClaimBillFinanceTypeCriteria that = (ClaimBillFinanceTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(claimBillFinanceTypeName, that.claimBillFinanceTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        claimBillFinanceTypeName
        );
    }

    @Override
    public String toString() {
        return "ClaimBillFinanceTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (claimBillFinanceTypeName != null ? "claimBillFinanceTypeName=" + claimBillFinanceTypeName + ", " : "") +
            "}";
    }

}
