package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseFeeBill entity.
 */
public class CaseFeeBillDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private Boolean isWriteOff;

    private Long caseFeeId;

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

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public Long getCaseFeeId() {
        return caseFeeId;
    }

    public void setCaseFeeId(Long caseFeeId) {
        this.caseFeeId = caseFeeId;
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

        CaseFeeBillDTO caseFeeBillDTO = (CaseFeeBillDTO) o;
        if (caseFeeBillDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseFeeBillDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseFeeBillDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            ", caseFee=" + getCaseFeeId() +
            ", caseClaimBill=" + getCaseClaimBillId() +
            ", caseClaimBill='" + getCaseClaimBillClaimBillCode() + "'" +
            ", writeOffBill=" + getWriteOffBillId() +
            ", writeOffBill='" + getWriteOffBillClaimBillCode() + "'" +
            "}";
    }
}
