package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A PaymentType.
 */
@Entity
@Table(name = "payment_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentType implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static Long PAYMENTTYPE_MEMBER    = new Long(1);

    public final static Long PAYMENTTYPE_THIRDPART = new Long(2);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "payment_type_name", nullable = false)
    private String paymentTypeName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public PaymentType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public PaymentType paymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
        return this;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentType paymentType = (PaymentType) o;
        if (paymentType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", paymentTypeName='" + getPaymentTypeName() + "'" +
            "}";
    }
}
