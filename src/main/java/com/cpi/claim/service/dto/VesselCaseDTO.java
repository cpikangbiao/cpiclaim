/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.service.dto;


import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import java.time.Instant;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.VesselCase} entity.
 */
public class VesselCaseDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private String caseYear;

    @NotNull
    private Long insuredVesselId;

    private String companyName;

    private String vesselName;

    private String companyChineseName;

    private String vesselChineseName;

    private Long reinsureId;

    private BigDecimal deduct;

    private Long assignedHandler;

    private Instant assignedTime;

    private Long registeredHandler;

    private Instant registeredDate;

    private String caseCode;

    @NotNull
    private Instant caseDate;

    @NotNull
    private Long caseLocation;

    @NotNull
    private String caseDescription;

    private String voyageNo;

    private Long loadingPort;

    private Instant loadingDate;

    private Long dischargingPort;

    private Instant dischargingDate;

    private Instant limitTime;

    private Instant cpDate;

    private String cpType;

    private String arrestVessel;

    private Long jurisdiction;

    private Long applicableLaw;

    private Instant closeDate;

    private Long closeHandler;

    @Lob
    private String remark;

    private BigDecimal settlementAmount;

    private Instant settlementDate;


    private Long cpiInsuranceTypeId;

    private String cpiInsuranceTypeCpiInsuranceTypeName;

    private Long caseStatusId;

    private String caseStatusCaseStatusName;

    private Long settlementModeId;

    private String settlementModeSettlementModeName;

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

    public String getCaseYear() {
        return caseYear;
    }

    public void setCaseYear(String caseYear) {
        this.caseYear = caseYear;
    }

    public Long getInsuredVesselId() {
        return insuredVesselId;
    }

    public void setInsuredVesselId(Long insuredVesselId) {
        this.insuredVesselId = insuredVesselId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getCompanyChineseName() {
        return companyChineseName;
    }

    public void setCompanyChineseName(String companyChineseName) {
        this.companyChineseName = companyChineseName;
    }

    public String getVesselChineseName() {
        return vesselChineseName;
    }

    public void setVesselChineseName(String vesselChineseName) {
        this.vesselChineseName = vesselChineseName;
    }

    public Long getReinsureId() {
        return reinsureId;
    }

    public void setReinsureId(Long reinsureId) {
        this.reinsureId = reinsureId;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public Long getAssignedHandler() {
        return assignedHandler;
    }

    public void setAssignedHandler(Long assignedHandler) {
        this.assignedHandler = assignedHandler;
    }

    public Instant getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(Instant assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Long getRegisteredHandler() {
        return registeredHandler;
    }

    public void setRegisteredHandler(Long registeredHandler) {
        this.registeredHandler = registeredHandler;
    }

    public Instant getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Instant registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public Instant getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(Instant caseDate) {
        this.caseDate = caseDate;
    }

    public Long getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(Long caseLocation) {
        this.caseLocation = caseLocation;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
    }

    public Long getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(Long loadingPort) {
        this.loadingPort = loadingPort;
    }

    public Instant getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(Instant loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Long getDischargingPort() {
        return dischargingPort;
    }

    public void setDischargingPort(Long dischargingPort) {
        this.dischargingPort = dischargingPort;
    }

    public Instant getDischargingDate() {
        return dischargingDate;
    }

    public void setDischargingDate(Instant dischargingDate) {
        this.dischargingDate = dischargingDate;
    }

    public Instant getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Instant limitTime) {
        this.limitTime = limitTime;
    }

    public Instant getCpDate() {
        return cpDate;
    }

    public void setCpDate(Instant cpDate) {
        this.cpDate = cpDate;
    }

    public String getCpType() {
        return cpType;
    }

    public void setCpType(String cpType) {
        this.cpType = cpType;
    }

    public String getArrestVessel() {
        return arrestVessel;
    }

    public void setArrestVessel(String arrestVessel) {
        this.arrestVessel = arrestVessel;
    }

    public Long getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Long jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Long getApplicableLaw() {
        return applicableLaw;
    }

    public void setApplicableLaw(Long applicableLaw) {
        this.applicableLaw = applicableLaw;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public Long getCloseHandler() {
        return closeHandler;
    }

    public void setCloseHandler(Long closeHandler) {
        this.closeHandler = closeHandler;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Instant getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Long getCpiInsuranceTypeId() {
        return cpiInsuranceTypeId;
    }

    public void setCpiInsuranceTypeId(Long cpiInsuranceTypeId) {
        this.cpiInsuranceTypeId = cpiInsuranceTypeId;
    }

    public String getCpiInsuranceTypeCpiInsuranceTypeName() {
        return cpiInsuranceTypeCpiInsuranceTypeName;
    }

    public void setCpiInsuranceTypeCpiInsuranceTypeName(String cpiInsuranceTypeCpiInsuranceTypeName) {
        this.cpiInsuranceTypeCpiInsuranceTypeName = cpiInsuranceTypeCpiInsuranceTypeName;
    }

    public Long getCaseStatusId() {
        return caseStatusId;
    }

    public void setCaseStatusId(Long caseStatusTypeId) {
        this.caseStatusId = caseStatusTypeId;
    }

    public String getCaseStatusCaseStatusName() {
        return caseStatusCaseStatusName;
    }

    public void setCaseStatusCaseStatusName(String caseStatusTypeCaseStatusName) {
        this.caseStatusCaseStatusName = caseStatusTypeCaseStatusName;
    }

    public Long getSettlementModeId() {
        return settlementModeId;
    }

    public void setSettlementModeId(Long caseSettlementModeId) {
        this.settlementModeId = caseSettlementModeId;
    }

    public String getSettlementModeSettlementModeName() {
        return settlementModeSettlementModeName;
    }

    public void setSettlementModeSettlementModeName(String caseSettlementModeSettlementModeName) {
        this.settlementModeSettlementModeName = caseSettlementModeSettlementModeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VesselCaseDTO vesselCaseDTO = (VesselCaseDTO) o;
        if (vesselCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vesselCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VesselCaseDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", caseYear='" + getCaseYear() + "'" +
            ", insuredVesselId=" + getInsuredVesselId() +
            ", companyName='" + getCompanyName() + "'" +
            ", vesselName='" + getVesselName() + "'" +
            ", companyChineseName='" + getCompanyChineseName() + "'" +
            ", vesselChineseName='" + getVesselChineseName() + "'" +
            ", reinsureId=" + getReinsureId() +
            ", deduct=" + getDeduct() +
            ", assignedHandler=" + getAssignedHandler() +
            ", assignedTime='" + getAssignedTime() + "'" +
            ", registeredHandler=" + getRegisteredHandler() +
            ", registeredDate='" + getRegisteredDate() + "'" +
            ", caseCode='" + getCaseCode() + "'" +
            ", caseDate='" + getCaseDate() + "'" +
            ", caseLocation=" + getCaseLocation() +
            ", caseDescription='" + getCaseDescription() + "'" +
            ", voyageNo='" + getVoyageNo() + "'" +
            ", loadingPort=" + getLoadingPort() +
            ", loadingDate='" + getLoadingDate() + "'" +
            ", dischargingPort=" + getDischargingPort() +
            ", dischargingDate='" + getDischargingDate() + "'" +
            ", limitTime='" + getLimitTime() + "'" +
            ", cpDate='" + getCpDate() + "'" +
            ", cpType='" + getCpType() + "'" +
            ", arrestVessel='" + getArrestVessel() + "'" +
            ", jurisdiction=" + getJurisdiction() +
            ", applicableLaw=" + getApplicableLaw() +
            ", closeDate='" + getCloseDate() + "'" +
            ", closeHandler=" + getCloseHandler() +
            ", remark='" + getRemark() + "'" +
            ", settlementAmount=" + getSettlementAmount() +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", cpiInsuranceType=" + getCpiInsuranceTypeId() +
            ", cpiInsuranceType='" + getCpiInsuranceTypeCpiInsuranceTypeName() + "'" +
            ", caseStatus=" + getCaseStatusId() +
            ", caseStatus='" + getCaseStatusCaseStatusName() + "'" +
            ", settlementMode=" + getSettlementModeId() +
            ", settlementMode='" + getSettlementModeSettlementModeName() + "'" +
            "}";
    }
}
