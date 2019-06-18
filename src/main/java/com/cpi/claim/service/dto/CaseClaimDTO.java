package com.cpi.claim.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.cpi.claim.domain.CaseClaim} entity.
 */
public class CaseClaimDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private String claimer;

    private Instant claimDate;

    private String billOfLading;

    private Long currencyId;

    private BigDecimal currencyRate;

    private BigDecimal claimCost;

    private BigDecimal claimCostDollar;

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

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public Instant getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Instant claimDate) {
        this.claimDate = claimDate;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getClaimCost() {
        return claimCost;
    }

    public void setClaimCost(BigDecimal claimCost) {
        this.claimCost = claimCost;
    }

    public BigDecimal getClaimCostDollar() {
        return claimCostDollar;
    }

    public void setClaimCostDollar(BigDecimal claimCostDollar) {
        this.claimCostDollar = claimCostDollar;
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

        CaseClaimDTO caseClaimDTO = (CaseClaimDTO) o;
        if (caseClaimDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", claimer='" + getClaimer() + "'" +
            ", claimDate='" + getClaimDate() + "'" +
            ", billOfLading='" + getBillOfLading() + "'" +
            ", currencyId=" + getCurrencyId() +
            ", currencyRate=" + getCurrencyRate() +
            ", claimCost=" + getClaimCost() +
            ", claimCostDollar=" + getClaimCostDollar() +
            ", remark='" + getRemark() + "'" +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            "}";
    }
}
