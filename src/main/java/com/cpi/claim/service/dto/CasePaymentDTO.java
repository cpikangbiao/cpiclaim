package com.cpi.claim.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.cpi.claim.domain.CasePayment} entity.
 */
public class CasePaymentDTO implements Serializable {

    private Long id;

    private String clientNo;

    private Integer numberId;

    private Instant payCostDate;

    private Long currency;

    private BigDecimal currencyRate;

    private BigDecimal payCost;

    private BigDecimal payCostDollar;

    private BigDecimal deduct;

    private BigDecimal amount;

    private Long feeCreateUser;

    private Instant feeCreateDate;

    @Lob
    private String remark;


    private Long paymentTypeId;

    private String paymentTypePaymentTypeName;

    private Long subcaseId;

    private String subcaseSubcaseCode;

    private Long creditorId;

    private String creditorCreditorCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Instant getPayCostDate() {
        return payCostDate;
    }

    public void setPayCostDate(Instant payCostDate) {
        this.payCostDate = payCostDate;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getPayCost() {
        return payCost;
    }

    public void setPayCost(BigDecimal payCost) {
        this.payCost = payCost;
    }

    public BigDecimal getPayCostDollar() {
        return payCostDollar;
    }

    public void setPayCostDollar(BigDecimal payCostDollar) {
        this.payCostDollar = payCostDollar;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFeeCreateUser() {
        return feeCreateUser;
    }

    public void setFeeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
    }

    public Instant getFeeCreateDate() {
        return feeCreateDate;
    }

    public void setFeeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypePaymentTypeName() {
        return paymentTypePaymentTypeName;
    }

    public void setPaymentTypePaymentTypeName(String paymentTypePaymentTypeName) {
        this.paymentTypePaymentTypeName = paymentTypePaymentTypeName;
    }

    public Long getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(Long vesselSubCaseId) {
        this.subcaseId = vesselSubCaseId;
    }

    public String getSubcaseSubcaseCode() {
        return subcaseSubcaseCode;
    }

    public void setSubcaseSubcaseCode(String vesselSubCaseSubcaseCode) {
        this.subcaseSubcaseCode = vesselSubCaseSubcaseCode;
    }

    public Long getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Long creditorId) {
        this.creditorId = creditorId;
    }

    public String getCreditorCreditorCode() {
        return creditorCreditorCode;
    }

    public void setCreditorCreditorCode(String creditorCreditorCode) {
        this.creditorCreditorCode = creditorCreditorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CasePaymentDTO casePaymentDTO = (CasePaymentDTO) o;
        if (casePaymentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casePaymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CasePaymentDTO{" +
            "id=" + getId() +
            ", clientNo='" + getClientNo() + "'" +
            ", numberId=" + getNumberId() +
            ", payCostDate='" + getPayCostDate() + "'" +
            ", currency=" + getCurrency() +
            ", currencyRate=" + getCurrencyRate() +
            ", payCost=" + getPayCost() +
            ", payCostDollar=" + getPayCostDollar() +
            ", deduct=" + getDeduct() +
            ", amount=" + getAmount() +
            ", feeCreateUser=" + getFeeCreateUser() +
            ", feeCreateDate='" + getFeeCreateDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", paymentType=" + getPaymentTypeId() +
            ", paymentType='" + getPaymentTypePaymentTypeName() + "'" +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            ", creditor=" + getCreditorId() +
            ", creditor='" + getCreditorCreditorCode() + "'" +
            "}";
    }
}
