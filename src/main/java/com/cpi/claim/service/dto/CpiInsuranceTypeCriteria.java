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
 * Criteria class for the {@link com.cpi.claim.domain.CpiInsuranceType} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CpiInsuranceTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /cpi-insurance-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CpiInsuranceTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter cpiInsuranceTypeName;

    public CpiInsuranceTypeCriteria(){
    }

    public CpiInsuranceTypeCriteria(CpiInsuranceTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.cpiInsuranceTypeName = other.cpiInsuranceTypeName == null ? null : other.cpiInsuranceTypeName.copy();
    }

    @Override
    public CpiInsuranceTypeCriteria copy() {
        return new CpiInsuranceTypeCriteria(this);
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

    public StringFilter getCpiInsuranceTypeName() {
        return cpiInsuranceTypeName;
    }

    public void setCpiInsuranceTypeName(StringFilter cpiInsuranceTypeName) {
        this.cpiInsuranceTypeName = cpiInsuranceTypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CpiInsuranceTypeCriteria that = (CpiInsuranceTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(cpiInsuranceTypeName, that.cpiInsuranceTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        cpiInsuranceTypeName
        );
    }

    @Override
    public String toString() {
        return "CpiInsuranceTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (cpiInsuranceTypeName != null ? "cpiInsuranceTypeName=" + cpiInsuranceTypeName + ", " : "") +
            "}";
    }

}
