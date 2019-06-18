package com.cpi.claim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the PaymentType entity.
 */
public class PaymentTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String paymentTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getPaymentTypeName() {
        return paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
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

        PaymentTypeDTO paymentTypeDTO = (PaymentTypeDTO) o;
        if (paymentTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paymentTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PaymentTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", paymentTypeName='" + getPaymentTypeName() + "'" +
            "}";
    }
}
