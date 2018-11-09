package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the CasePaymentBill entity.
 */
public class CasePaymentBillDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private Long currency;

    private BigDecimal amount;

    private Boolean isWriteOff;

    private Long subcaseId;

    private Long caseClaimBillId;

    private String caseClaimBillClaimBillCode;

    private Long writeOffBillId;

    private String writeOffBillClaimBillCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public Long getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(Long vesselSubCaseId) {
        this.subcaseId = vesselSubCaseId;
    }

    public Long getCaseClaimBillId() {
        return caseClaimBillId;
    }

    public void setCaseClaimBillId(Long caseClaimBillId) {
        this.caseClaimBillId = caseClaimBillId;
    }

    public String getCaseClaimBillClaimBillCode() {
        return caseClaimBillClaimBillCode;
    }

    public void setCaseClaimBillClaimBillCode(String caseClaimBillClaimBillCode) {
        this.caseClaimBillClaimBillCode = caseClaimBillClaimBillCode;
    }

    public Long getWriteOffBillId() {
        return writeOffBillId;
    }

    public void setWriteOffBillId(Long caseClaimBillId) {
        this.writeOffBillId = caseClaimBillId;
    }

    public String getWriteOffBillClaimBillCode() {
        return writeOffBillClaimBillCode;
    }

    public void setWriteOffBillClaimBillCode(String caseClaimBillClaimBillCode) {
        this.writeOffBillClaimBillCode = caseClaimBillClaimBillCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CasePaymentBillDTO casePaymentBillDTO = (CasePaymentBillDTO) o;
        if (casePaymentBillDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casePaymentBillDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CasePaymentBillDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", currency=" + getCurrency() +
            ", amount=" + getAmount() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            ", subcase=" + getSubcaseId() +
            ", caseClaimBill=" + getCaseClaimBillId() +
            ", caseClaimBill='" + getCaseClaimBillClaimBillCode() + "'" +
            ", writeOffBill=" + getWriteOffBillId() +
            ", writeOffBill='" + getWriteOffBillClaimBillCode() + "'" +
            "}";
    }
}
