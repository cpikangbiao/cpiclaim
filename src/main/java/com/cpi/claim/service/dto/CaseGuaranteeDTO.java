package com.cpi.claim.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the CaseGuarantee entity.
 */
public class CaseGuaranteeDTO implements Serializable {

    private Long id;

    private Long vesselId;

    private Integer numberId;

    private Instant arrestDate;

    private Long arrestPort;

    private Long arrestVesselId;

    private String arrestVesselName;

    private String billOfLading;

    private Instant billLadingDate;

    private String natureOfClaim;

    private String guarantee;

    private Instant guaranteeDate;

    private Long guaranteeCurrency;

    private Double guaranteeRate;

    private BigDecimal guaranteeAmount;

    private BigDecimal guaranteeAmountDollar;

    private String guaranteeTo;

    private String guaranteeToAddress;

    private String guaranteeNo;

    private BigDecimal guaranteeFee;

    private String guaranteeOther;

    private Instant cancelDate;

    private String conGuarantee;

    private Instant conGuaranteeDate;

    private Long conGuaranteeCurrency;

    private Double conGuaranteeRate;

    private BigDecimal conGuaranteeAmount;

    private BigDecimal conGuaranteeAmountDollar;

    private String conGuaranteeNo;

    private String conGuaranteeTo;

    private Instant conGuaranteeCancelDate;

    @Lob
    private String remark;

    private Instant releaseDate;

    private Long registerUserId;

    private Long subcaseId;

    private String subcaseSubcaseCode;

    private Long guaranteeTypeId;

    private String guaranteeTypeGuaranteeTypeName;

    private Long conGuaranteeTypeId;

    private String conGuaranteeTypeGuaranteeTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVesselId() {
        return vesselId;
    }

    public void setVesselId(Long vesselId) {
        this.vesselId = vesselId;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Instant getArrestDate() {
        return arrestDate;
    }

    public void setArrestDate(Instant arrestDate) {
        this.arrestDate = arrestDate;
    }

    public Long getArrestPort() {
        return arrestPort;
    }

    public void setArrestPort(Long arrestPort) {
        this.arrestPort = arrestPort;
    }

    public Long getArrestVesselId() {
        return arrestVesselId;
    }

    public void setArrestVesselId(Long arrestVesselId) {
        this.arrestVesselId = arrestVesselId;
    }

    public String getArrestVesselName() {
        return arrestVesselName;
    }

    public void setArrestVesselName(String arrestVesselName) {
        this.arrestVesselName = arrestVesselName;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }

    public Instant getBillLadingDate() {
        return billLadingDate;
    }

    public void setBillLadingDate(Instant billLadingDate) {
        this.billLadingDate = billLadingDate;
    }

    public String getNatureOfClaim() {
        return natureOfClaim;
    }

    public void setNatureOfClaim(String natureOfClaim) {
        this.natureOfClaim = natureOfClaim;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public Instant getGuaranteeDate() {
        return guaranteeDate;
    }

    public void setGuaranteeDate(Instant guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
    }

    public Long getGuaranteeCurrency() {
        return guaranteeCurrency;
    }

    public void setGuaranteeCurrency(Long guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
    }

    public Double getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(Double guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public BigDecimal getGuaranteeAmountDollar() {
        return guaranteeAmountDollar;
    }

    public void setGuaranteeAmountDollar(BigDecimal guaranteeAmountDollar) {
        this.guaranteeAmountDollar = guaranteeAmountDollar;
    }

    public String getGuaranteeTo() {
        return guaranteeTo;
    }

    public void setGuaranteeTo(String guaranteeTo) {
        this.guaranteeTo = guaranteeTo;
    }

    public String getGuaranteeToAddress() {
        return guaranteeToAddress;
    }

    public void setGuaranteeToAddress(String guaranteeToAddress) {
        this.guaranteeToAddress = guaranteeToAddress;
    }

    public String getGuaranteeNo() {
        return guaranteeNo;
    }

    public void setGuaranteeNo(String guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
    }

    public BigDecimal getGuaranteeFee() {
        return guaranteeFee;
    }

    public void setGuaranteeFee(BigDecimal guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
    }

    public String getGuaranteeOther() {
        return guaranteeOther;
    }

    public void setGuaranteeOther(String guaranteeOther) {
        this.guaranteeOther = guaranteeOther;
    }

    public Instant getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Instant cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getConGuarantee() {
        return conGuarantee;
    }

    public void setConGuarantee(String conGuarantee) {
        this.conGuarantee = conGuarantee;
    }

    public Instant getConGuaranteeDate() {
        return conGuaranteeDate;
    }

    public void setConGuaranteeDate(Instant conGuaranteeDate) {
        this.conGuaranteeDate = conGuaranteeDate;
    }

    public Long getConGuaranteeCurrency() {
        return conGuaranteeCurrency;
    }

    public void setConGuaranteeCurrency(Long conGuaranteeCurrency) {
        this.conGuaranteeCurrency = conGuaranteeCurrency;
    }

    public Double getConGuaranteeRate() {
        return conGuaranteeRate;
    }

    public void setConGuaranteeRate(Double conGuaranteeRate) {
        this.conGuaranteeRate = conGuaranteeRate;
    }

    public BigDecimal getConGuaranteeAmount() {
        return conGuaranteeAmount;
    }

    public void setConGuaranteeAmount(BigDecimal conGuaranteeAmount) {
        this.conGuaranteeAmount = conGuaranteeAmount;
    }

    public BigDecimal getConGuaranteeAmountDollar() {
        return conGuaranteeAmountDollar;
    }

    public void setConGuaranteeAmountDollar(BigDecimal conGuaranteeAmountDollar) {
        this.conGuaranteeAmountDollar = conGuaranteeAmountDollar;
    }

    public String getConGuaranteeNo() {
        return conGuaranteeNo;
    }

    public void setConGuaranteeNo(String conGuaranteeNo) {
        this.conGuaranteeNo = conGuaranteeNo;
    }

    public String getConGuaranteeTo() {
        return conGuaranteeTo;
    }

    public void setConGuaranteeTo(String conGuaranteeTo) {
        this.conGuaranteeTo = conGuaranteeTo;
    }

    public Instant getConGuaranteeCancelDate() {
        return conGuaranteeCancelDate;
    }

    public void setConGuaranteeCancelDate(Instant conGuaranteeCancelDate) {
        this.conGuaranteeCancelDate = conGuaranteeCancelDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
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

    public Long getGuaranteeTypeId() {
        return guaranteeTypeId;
    }

    public void setGuaranteeTypeId(Long guaranteeTypeId) {
        this.guaranteeTypeId = guaranteeTypeId;
    }

    public String getGuaranteeTypeGuaranteeTypeName() {
        return guaranteeTypeGuaranteeTypeName;
    }

    public void setGuaranteeTypeGuaranteeTypeName(String guaranteeTypeGuaranteeTypeName) {
        this.guaranteeTypeGuaranteeTypeName = guaranteeTypeGuaranteeTypeName;
    }

    public Long getConGuaranteeTypeId() {
        return conGuaranteeTypeId;
    }

    public void setConGuaranteeTypeId(Long guaranteeTypeId) {
        this.conGuaranteeTypeId = guaranteeTypeId;
    }

    public String getConGuaranteeTypeGuaranteeTypeName() {
        return conGuaranteeTypeGuaranteeTypeName;
    }

    public void setConGuaranteeTypeGuaranteeTypeName(String guaranteeTypeGuaranteeTypeName) {
        this.conGuaranteeTypeGuaranteeTypeName = guaranteeTypeGuaranteeTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseGuaranteeDTO caseGuaranteeDTO = (CaseGuaranteeDTO) o;
        if (caseGuaranteeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseGuaranteeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseGuaranteeDTO{" +
            "id=" + getId() +
            ", vesselId=" + getVesselId() +
            ", numberId=" + getNumberId() +
            ", arrestDate='" + getArrestDate() + "'" +
            ", arrestPort=" + getArrestPort() +
            ", arrestVesselId=" + getArrestVesselId() +
            ", arrestVesselName='" + getArrestVesselName() + "'" +
            ", billOfLading='" + getBillOfLading() + "'" +
            ", billLadingDate='" + getBillLadingDate() + "'" +
            ", natureOfClaim='" + getNatureOfClaim() + "'" +
            ", guarantee='" + getGuarantee() + "'" +
            ", guaranteeDate='" + getGuaranteeDate() + "'" +
            ", guaranteeCurrency=" + getGuaranteeCurrency() +
            ", guaranteeRate=" + getGuaranteeRate() +
            ", guaranteeAmount=" + getGuaranteeAmount() +
            ", guaranteeAmountDollar=" + getGuaranteeAmountDollar() +
            ", guaranteeTo='" + getGuaranteeTo() + "'" +
            ", guaranteeToAddress='" + getGuaranteeToAddress() + "'" +
            ", guaranteeNo='" + getGuaranteeNo() + "'" +
            ", guaranteeFee=" + getGuaranteeFee() +
            ", guaranteeOther='" + getGuaranteeOther() + "'" +
            ", cancelDate='" + getCancelDate() + "'" +
            ", conGuarantee='" + getConGuarantee() + "'" +
            ", conGuaranteeDate='" + getConGuaranteeDate() + "'" +
            ", conGuaranteeCurrency=" + getConGuaranteeCurrency() +
            ", conGuaranteeRate=" + getConGuaranteeRate() +
            ", conGuaranteeAmount=" + getConGuaranteeAmount() +
            ", conGuaranteeAmountDollar=" + getConGuaranteeAmountDollar() +
            ", conGuaranteeNo='" + getConGuaranteeNo() + "'" +
            ", conGuaranteeTo='" + getConGuaranteeTo() + "'" +
            ", conGuaranteeCancelDate='" + getConGuaranteeCancelDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", registerUserId=" + getRegisterUserId() +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            ", guaranteeType=" + getGuaranteeTypeId() +
            ", guaranteeType='" + getGuaranteeTypeGuaranteeTypeName() + "'" +
            ", conGuaranteeType=" + getConGuaranteeTypeId() +
            ", conGuaranteeType='" + getConGuaranteeTypeGuaranteeTypeName() + "'" +
            "}";
    }
}
