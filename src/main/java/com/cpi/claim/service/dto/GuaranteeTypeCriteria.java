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
 * Criteria class for the {@link com.cpi.claim.domain.GuaranteeType} entity. This class is used
 * in {@link com.cpi.claim.web.rest.GuaranteeTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /guarantee-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GuaranteeTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter guaranteeTypeName;

    public GuaranteeTypeCriteria(){
    }

    public GuaranteeTypeCriteria(GuaranteeTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.guaranteeTypeName = other.guaranteeTypeName == null ? null : other.guaranteeTypeName.copy();
    }

    @Override
    public GuaranteeTypeCriteria copy() {
        return new GuaranteeTypeCriteria(this);
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

    public StringFilter getGuaranteeTypeName() {
        return guaranteeTypeName;
    }

    public void setGuaranteeTypeName(StringFilter guaranteeTypeName) {
        this.guaranteeTypeName = guaranteeTypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GuaranteeTypeCriteria that = (GuaranteeTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(guaranteeTypeName, that.guaranteeTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        guaranteeTypeName
        );
    }

    @Override
    public String toString() {
        return "GuaranteeTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (guaranteeTypeName != null ? "guaranteeTypeName=" + guaranteeTypeName + ", " : "") +
            "}";
    }

}
