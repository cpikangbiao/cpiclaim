package com.cpi.claim.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the CaseEstimate entity.
 */
public class CaseEstimateDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private Long registerUserId;

    private Instant estimateDate;

    private BigDecimal estimateEntityFee;

    private BigDecimal estimateCostFee;

    @Lob
    private String remark;

    private Long subcaseId;

    private String subcaseSubcaseCode;

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

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public Instant getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(Instant estimateDate) {
        this.estimateDate = estimateDate;
    }

    public BigDecimal getEstimateEntityFee() {
        return estimateEntityFee;
    }

    public void setEstimateEntityFee(BigDecimal estimateEntityFee) {
        this.estimateEntityFee = estimateEntityFee;
    }

    public BigDecimal getEstimateCostFee() {
        return estimateCostFee;
    }

    public void setEstimateCostFee(BigDecimal estimateCostFee) {
        this.estimateCostFee = estimateCostFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseEstimateDTO caseEstimateDTO = (CaseEstimateDTO) o;
        if (caseEstimateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseEstimateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseEstimateDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", registerUserId=" + getRegisterUserId() +
            ", estimateDate='" + getEstimateDate() + "'" +
            ", estimateEntityFee=" + getEstimateEntityFee() +
            ", estimateCostFee=" + getEstimateCostFee() +
            ", remark='" + getRemark() + "'" +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            "}";
    }
}
