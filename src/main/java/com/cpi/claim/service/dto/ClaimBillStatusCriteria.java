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
 * Criteria class for the {@link com.cpi.claim.domain.ClaimBillStatus} entity. This class is used
 * in {@link com.cpi.claim.web.rest.ClaimBillStatusResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /claim-bill-statuses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ClaimBillStatusCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter claimBillStatusName;

    public ClaimBillStatusCriteria(){
    }

    public ClaimBillStatusCriteria(ClaimBillStatusCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.claimBillStatusName = other.claimBillStatusName == null ? null : other.claimBillStatusName.copy();
    }

    @Override
    public ClaimBillStatusCriteria copy() {
        return new ClaimBillStatusCriteria(this);
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

    public StringFilter getClaimBillStatusName() {
        return claimBillStatusName;
    }

    public void setClaimBillStatusName(StringFilter claimBillStatusName) {
        this.claimBillStatusName = claimBillStatusName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClaimBillStatusCriteria that = (ClaimBillStatusCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(claimBillStatusName, that.claimBillStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        claimBillStatusName
        );
    }

    @Override
    public String toString() {
        return "ClaimBillStatusCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (claimBillStatusName != null ? "claimBillStatusName=" + claimBillStatusName + ", " : "") +
            "}";
    }

}
