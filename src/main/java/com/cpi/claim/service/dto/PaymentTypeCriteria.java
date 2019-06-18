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
 * Criteria class for the {@link com.cpi.claim.domain.PaymentType} entity. This class is used
 * in {@link com.cpi.claim.web.rest.PaymentTypeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /payment-types?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PaymentTypeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private StringFilter paymentTypeName;

    public PaymentTypeCriteria(){
    }

    public PaymentTypeCriteria(PaymentTypeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.paymentTypeName = other.paymentTypeName == null ? null : other.paymentTypeName.copy();
    }

    @Override
    public PaymentTypeCriteria copy() {
        return new PaymentTypeCriteria(this);
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

    public StringFilter getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(StringFilter paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PaymentTypeCriteria that = (PaymentTypeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(paymentTypeName, that.paymentTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        paymentTypeName
        );
    }

    @Override
    public String toString() {
        return "PaymentTypeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (paymentTypeName != null ? "paymentTypeName=" + paymentTypeName + ", " : "") +
            "}";
    }

}
