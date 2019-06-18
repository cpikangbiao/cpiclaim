/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
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

package com.cpi.claim.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A VesselCase.
 */
@Entity
@Table(name = "vessel_case")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VesselCase extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id", nullable = false)
    private Integer numberId;

    @Column(name = "case_year")
    private String caseYear;

    @NotNull
    @Column(name = "insured_vessel_id", nullable = false)
    private Long insuredVesselId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vessel_name")
    private String vesselName;

    @Column(name = "company_chinese_name")
    private String companyChineseName;

    @Column(name = "vessel_chinese_name")
    private String vesselChineseName;

    @Column(name = "reinsure_id")
    private Long reinsureId;

    @Column(name = "deduct", precision = 21, scale = 2)
    private BigDecimal deduct;

    @Column(name = "assigned_handler")
    private Long assignedHandler;

    @Column(name = "assigned_time")
    private Instant assignedTime;

    @Column(name = "registered_handler")
    private Long registeredHandler;

    @Column(name = "registered_date")
    private Instant registeredDate;

    @NotNull
    @Column(name = "case_code", nullable = false)
    private String caseCode;

    @Column(name = "case_date")
    private Instant caseDate;

    @Column(name = "case_location")
    private Long caseLocation;

    @Column(name = "case_description")
    private String caseDescription;

    @Column(name = "voyage_no")
    private String voyageNo;

    @Column(name = "loading_port")
    private Long loadingPort;

    @Column(name = "loading_date")
    private Instant loadingDate;

    @Column(name = "discharging_port")
    private Long dischargingPort;

    @Column(name = "discharging_date")
    private Instant dischargingDate;

    @Column(name = "limit_time")
    private Instant limitTime;

    @Column(name = "cp_date")
    private Instant cpDate;

    @Column(name = "cp_type")
    private String cpType;

    @Column(name = "arrest_vessel")
    private String arrestVessel;

    @Column(name = "jurisdiction")
    private Long jurisdiction;

    @Column(name = "applicable_law")
    private Long applicableLaw;

    @Column(name = "close_date")
    private Instant closeDate;

    @Column(name = "close_handler")
    private Long closeHandler;

    @Lob
    @Column(name = "remark")
    private String remark;

    @Column(name = "settlement_amount", precision = 21, scale = 2)
    private BigDecimal settlementAmount;

    @Column(name = "settlement_date")
    private Instant settlementDate;

    @ManyToOne
    @JsonIgnoreProperties("vesselCases")
    private CpiInsuranceType cpiInsuranceType;

    @ManyToOne
    @JsonIgnoreProperties("vesselCases")
    private CaseStatusType caseStatus;

    @ManyToOne
    @JsonIgnoreProperties("vesselCases")
    private CaseSettlementMode settlementMode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public VesselCase numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public String getCaseYear() {
        return caseYear;
    }

    public VesselCase caseYear(String caseYear) {
        this.caseYear = caseYear;
        return this;
    }

    public void setCaseYear(String caseYear) {
        this.caseYear = caseYear;
    }

    public Long getInsuredVesselId() {
        return insuredVesselId;
    }

    public VesselCase insuredVesselId(Long insuredVesselId) {
        this.insuredVesselId = insuredVesselId;
        return this;
    }

    public void setInsuredVesselId(Long insuredVesselId) {
        this.insuredVesselId = insuredVesselId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public VesselCase companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVesselName() {
        return vesselName;
    }

    public VesselCase vesselName(String vesselName) {
        this.vesselName = vesselName;
        return this;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getCompanyChineseName() {
        return companyChineseName;
    }

    public VesselCase companyChineseName(String companyChineseName) {
        this.companyChineseName = companyChineseName;
        return this;
    }

    public void setCompanyChineseName(String companyChineseName) {
        this.companyChineseName = companyChineseName;
    }

    public String getVesselChineseName() {
        return vesselChineseName;
    }

    public VesselCase vesselChineseName(String vesselChineseName) {
        this.vesselChineseName = vesselChineseName;
        return this;
    }

    public void setVesselChineseName(String vesselChineseName) {
        this.vesselChineseName = vesselChineseName;
    }

    public Long getReinsureId() {
        return reinsureId;
    }

    public VesselCase reinsureId(Long reinsureId) {
        this.reinsureId = reinsureId;
        return this;
    }

    public void setReinsureId(Long reinsureId) {
        this.reinsureId = reinsureId;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public VesselCase deduct(BigDecimal deduct) {
        this.deduct = deduct;
        return this;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public Long getAssignedHandler() {
        return assignedHandler;
    }

    public VesselCase assignedHandler(Long assignedHandler) {
        this.assignedHandler = assignedHandler;
        return this;
    }

    public void setAssignedHandler(Long assignedHandler) {
        this.assignedHandler = assignedHandler;
    }

    public Instant getAssignedTime() {
        return assignedTime;
    }

    public VesselCase assignedTime(Instant assignedTime) {
        this.assignedTime = assignedTime;
        return this;
    }

    public void setAssignedTime(Instant assignedTime) {
        this.assignedTime = assignedTime;
    }

    public Long getRegisteredHandler() {
        return registeredHandler;
    }

    public VesselCase registeredHandler(Long registeredHandler) {
        this.registeredHandler = registeredHandler;
        return this;
    }

    public void setRegisteredHandler(Long registeredHandler) {
        this.registeredHandler = registeredHandler;
    }

    public Instant getRegisteredDate() {
        return registeredDate;
    }

    public VesselCase registeredDate(Instant registeredDate) {
        this.registeredDate = registeredDate;
        return this;
    }

    public void setRegisteredDate(Instant registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public VesselCase caseCode(String caseCode) {
        this.caseCode = caseCode;
        return this;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public Instant getCaseDate() {
        return caseDate;
    }

    public VesselCase caseDate(Instant caseDate) {
        this.caseDate = caseDate;
        return this;
    }

    public void setCaseDate(Instant caseDate) {
        this.caseDate = caseDate;
    }

    public Long getCaseLocation() {
        return caseLocation;
    }

    public VesselCase caseLocation(Long caseLocation) {
        this.caseLocation = caseLocation;
        return this;
    }

    public void setCaseLocation(Long caseLocation) {
        this.caseLocation = caseLocation;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public VesselCase caseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
        return this;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getVoyageNo() {
        return voyageNo;
    }

    public VesselCase voyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
        return this;
    }

    public void setVoyageNo(String voyageNo) {
        this.voyageNo = voyageNo;
    }

    public Long getLoadingPort() {
        return loadingPort;
    }

    public VesselCase loadingPort(Long loadingPort) {
        this.loadingPort = loadingPort;
        return this;
    }

    public void setLoadingPort(Long loadingPort) {
        this.loadingPort = loadingPort;
    }

    public Instant getLoadingDate() {
        return loadingDate;
    }

    public VesselCase loadingDate(Instant loadingDate) {
        this.loadingDate = loadingDate;
        return this;
    }

    public void setLoadingDate(Instant loadingDate) {
        this.loadingDate = loadingDate;
    }

    public Long getDischargingPort() {
        return dischargingPort;
    }

    public VesselCase dischargingPort(Long dischargingPort) {
        this.dischargingPort = dischargingPort;
        return this;
    }

    public void setDischargingPort(Long dischargingPort) {
        this.dischargingPort = dischargingPort;
    }

    public Instant getDischargingDate() {
        return dischargingDate;
    }

    public VesselCase dischargingDate(Instant dischargingDate) {
        this.dischargingDate = dischargingDate;
        return this;
    }

    public void setDischargingDate(Instant dischargingDate) {
        this.dischargingDate = dischargingDate;
    }

    public Instant getLimitTime() {
        return limitTime;
    }

    public VesselCase limitTime(Instant limitTime) {
        this.limitTime = limitTime;
        return this;
    }

    public void setLimitTime(Instant limitTime) {
        this.limitTime = limitTime;
    }

    public Instant getCpDate() {
        return cpDate;
    }

    public VesselCase cpDate(Instant cpDate) {
        this.cpDate = cpDate;
        return this;
    }

    public void setCpDate(Instant cpDate) {
        this.cpDate = cpDate;
    }

    public String getCpType() {
        return cpType;
    }

    public VesselCase cpType(String cpType) {
        this.cpType = cpType;
        return this;
    }

    public void setCpType(String cpType) {
        this.cpType = cpType;
    }

    public String getArrestVessel() {
        return arrestVessel;
    }

    public VesselCase arrestVessel(String arrestVessel) {
        this.arrestVessel = arrestVessel;
        return this;
    }

    public void setArrestVessel(String arrestVessel) {
        this.arrestVessel = arrestVessel;
    }

    public Long getJurisdiction() {
        return jurisdiction;
    }

    public VesselCase jurisdiction(Long jurisdiction) {
        this.jurisdiction = jurisdiction;
        return this;
    }

    public void setJurisdiction(Long jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Long getApplicableLaw() {
        return applicableLaw;
    }

    public VesselCase applicableLaw(Long applicableLaw) {
        this.applicableLaw = applicableLaw;
        return this;
    }

    public void setApplicableLaw(Long applicableLaw) {
        this.applicableLaw = applicableLaw;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public VesselCase closeDate(Instant closeDate) {
        this.closeDate = closeDate;
        return this;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public Long getCloseHandler() {
        return closeHandler;
    }

    public VesselCase closeHandler(Long closeHandler) {
        this.closeHandler = closeHandler;
        return this;
    }

    public void setCloseHandler(Long closeHandler) {
        this.closeHandler = closeHandler;
    }

    public String getRemark() {
        return remark;
    }

    public VesselCase remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public VesselCase settlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
        return this;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public Instant getSettlementDate() {
        return settlementDate;
    }

    public VesselCase settlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
        return this;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public CpiInsuranceType getCpiInsuranceType() {
        return cpiInsuranceType;
    }

    public VesselCase cpiInsuranceType(CpiInsuranceType cpiInsuranceType) {
        this.cpiInsuranceType = cpiInsuranceType;
        return this;
    }

    public void setCpiInsuranceType(CpiInsuranceType cpiInsuranceType) {
        this.cpiInsuranceType = cpiInsuranceType;
    }

    public CaseStatusType getCaseStatus() {
        return caseStatus;
    }

    public VesselCase caseStatus(CaseStatusType caseStatusType) {
        this.caseStatus = caseStatusType;
        return this;
    }

    public void setCaseStatus(CaseStatusType caseStatusType) {
        this.caseStatus = caseStatusType;
    }

    public CaseSettlementMode getSettlementMode() {
        return settlementMode;
    }

    public VesselCase settlementMode(CaseSettlementMode caseSettlementMode) {
        this.settlementMode = caseSettlementMode;
        return this;
    }

    public void setSettlementMode(CaseSettlementMode caseSettlementMode) {
        this.settlementMode = caseSettlementMode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VesselCase)) {
            return false;
        }
        return id != null && id.equals(((VesselCase) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VesselCase{" +
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
            "}";
    }
}
