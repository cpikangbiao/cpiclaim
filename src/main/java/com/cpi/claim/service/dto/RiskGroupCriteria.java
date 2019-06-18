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
 * Criteria class for the {@link com.cpi.claim.domain.RiskGroup} entity. This class is used
 * in {@link com.cpi.claim.web.rest.RiskGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /risk-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RiskGroupCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter riskGroupName;

    public RiskGroupCriteria(){
    }

    public RiskGroupCriteria(RiskGroupCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.riskGroupName = other.riskGroupName == null ? null : other.riskGroupName.copy();
    }

    @Override
    public RiskGroupCriteria copy() {
        return new RiskGroupCriteria(this);
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

    public StringFilter getRiskGroupName() {
        return riskGroupName;
    }

    public void setRiskGroupName(StringFilter riskGroupName) {
        this.riskGroupName = riskGroupName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RiskGroupCriteria that = (RiskGroupCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(riskGroupName, that.riskGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        riskGroupName
        );
    }

    @Override
    public String toString() {
        return "RiskGroupCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (riskGroupName != null ? "riskGroupName=" + riskGroupName + ", " : "") +
            "}";
    }

}
