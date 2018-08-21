package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;
import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the VesselCase entity. This class is used in VesselCaseResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /vessel-cases?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VesselCaseCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter numberId;

    private StringFilter caseYear;

    private LongFilter insuredVessel;

    private StringFilter companyName;

    private StringFilter vesselName;

    private StringFilter companyChineseName;

    private StringFilter vesselChineseName;

    private BigDecimalFilter deduct;

    private LongFilter assignedHandler;

    private InstantFilter assignedTime;

    private LongFilter registeredHandler;

    private InstantFilter registeredDate;

    private StringFilter caseCode;

    private LongFilter caseLocation;

    private StringFilter caseDescription;

    private LongFilter loadingPort;

    private InstantFilter polDate;

    private LongFilter dischargingPort;

    private InstantFilter limitTime;

    private InstantFilter cpDate;

    private StringFilter cpType;

    private StringFilter arrestVessel;

    private LongFilter jurisdiction;

    private LongFilter applicableLaw;

    private InstantFilter closeDate;

    private LongFilter closeHandler;

    private BigDecimalFilter settlementAmount;

    private InstantFilter settlementDate;

    private LongFilter cpiInsuranceTypeId;

    private LongFilter caseStatusId;

    private LongFilter settlementModeId;

    public VesselCaseCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public StringFilter getCaseYear() {
        return caseYear;
    }

    public void setCaseYear(StringFilter caseYear) {
        this.caseYear = caseYear;
    }

    public LongFilter getInsuredVessel() {
        return insuredVessel;
    }

    public void setInsuredVessel(LongFilter insuredVessel) {
        this.insuredVessel = insuredVessel;
    }

    public StringFilter getCompanyName() {
        return companyName;
    }

    public void setCompanyName(StringFilter companyName) {
        this.companyName = companyName;
    }

    public StringFilter getVesselName() {
        return vesselName;
    }

    public void setVesselName(StringFilter vesselName) {
        this.vesselName = vesselName;
    }

    public StringFilter getCompanyChineseName() {
        return companyChineseName;
    }

    public void setCompanyChineseName(StringFilter companyChineseName) {
        this.companyChineseName = companyChineseName;
    }

    public StringFilter getVesselChineseName() {
        return vesselChineseName;
    }

    public void setVesselChineseName(StringFilter vesselChineseName) {
        this.vesselChineseName = vesselChineseName;
    }

    public BigDecimalFilter getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimalFilter deduct) {
        this.deduct = deduct;
    }

    public LongFilter getAssignedHandler() {
        return assignedHandler;
    }

    public void setAssignedHandler(LongFilter assignedHandler) {
        this.assignedHandler = assignedHandler;
    }

    public InstantFilter getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(InstantFilter assignedTime) {
        this.assignedTime = assignedTime;
    }

    public LongFilter getRegisteredHandler() {
        return registeredHandler;
    }

    public void setRegisteredHandler(LongFilter registeredHandler) {
        this.registeredHandler = registeredHandler;
    }

    public InstantFilter getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(InstantFilter registeredDate) {
        this.registeredDate = registeredDate;
    }

    public StringFilter getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(StringFilter caseCode) {
        this.caseCode = caseCode;
    }

    public LongFilter getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(LongFilter caseLocation) {
        this.caseLocation = caseLocation;
    }

    public StringFilter getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(StringFilter caseDescription) {
        this.caseDescription = caseDescription;
    }

    public LongFilter getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(LongFilter loadingPort) {
        this.loadingPort = loadingPort;
    }

    public InstantFilter getPolDate() {
        return polDate;
    }

    public void setPolDate(InstantFilter polDate) {
        this.polDate = polDate;
    }

    public LongFilter getDischargingPort() {
        return dischargingPort;
    }

    public void setDischargingPort(LongFilter dischargingPort) {
        this.dischargingPort = dischargingPort;
    }

    public InstantFilter getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(InstantFilter limitTime) {
        this.limitTime = limitTime;
    }

    public InstantFilter getCpDate() {
        return cpDate;
    }

    public void setCpDate(InstantFilter cpDate) {
        this.cpDate = cpDate;
    }

    public StringFilter getCpType() {
        return cpType;
    }

    public void setCpType(StringFilter cpType) {
        this.cpType = cpType;
    }

    public StringFilter getArrestVessel() {
        return arrestVessel;
    }

    public void setArrestVessel(StringFilter arrestVessel) {
        this.arrestVessel = arrestVessel;
    }

    public LongFilter getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(LongFilter jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public LongFilter getApplicableLaw() {
        return applicableLaw;
    }

    public void setApplicableLaw(LongFilter applicableLaw) {
        this.applicableLaw = applicableLaw;
    }

    public InstantFilter getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(InstantFilter closeDate) {
        this.closeDate = closeDate;
    }

    public LongFilter getCloseHandler() {
        return closeHandler;
    }

    public void setCloseHandler(LongFilter closeHandler) {
        this.closeHandler = closeHandler;
    }

    public BigDecimalFilter getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimalFilter settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public InstantFilter getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(InstantFilter settlementDate) {
        this.settlementDate = settlementDate;
    }

    public LongFilter getCpiInsuranceTypeId() {
        return cpiInsuranceTypeId;
    }

    public void setCpiInsuranceTypeId(LongFilter cpiInsuranceTypeId) {
        this.cpiInsuranceTypeId = cpiInsuranceTypeId;
    }

    public LongFilter getCaseStatusId() {
        return caseStatusId;
    }

    public void setCaseStatusId(LongFilter caseStatusId) {
        this.caseStatusId = caseStatusId;
    }

    public LongFilter getSettlementModeId() {
        return settlementModeId;
    }

    public void setSettlementModeId(LongFilter settlementModeId) {
        this.settlementModeId = settlementModeId;
    }

    @Override
    public String toString() {
        return "VesselCaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (caseYear != null ? "caseYear=" + caseYear + ", " : "") +
                (insuredVessel != null ? "insuredVessel=" + insuredVessel + ", " : "") +
                (companyName != null ? "companyName=" + companyName + ", " : "") +
                (vesselName != null ? "vesselName=" + vesselName + ", " : "") +
                (companyChineseName != null ? "companyChineseName=" + companyChineseName + ", " : "") +
                (vesselChineseName != null ? "vesselChineseName=" + vesselChineseName + ", " : "") +
                (deduct != null ? "deduct=" + deduct + ", " : "") +
                (assignedHandler != null ? "assignedHandler=" + assignedHandler + ", " : "") +
                (assignedTime != null ? "assignedTime=" + assignedTime + ", " : "") +
                (registeredHandler != null ? "registeredHandler=" + registeredHandler + ", " : "") +
                (registeredDate != null ? "registeredDate=" + registeredDate + ", " : "") +
                (caseCode != null ? "caseCode=" + caseCode + ", " : "") +
                (caseLocation != null ? "caseLocation=" + caseLocation + ", " : "") +
                (caseDescription != null ? "caseDescription=" + caseDescription + ", " : "") +
                (loadingPort != null ? "loadingPort=" + loadingPort + ", " : "") +
                (polDate != null ? "polDate=" + polDate + ", " : "") +
                (dischargingPort != null ? "dischargingPort=" + dischargingPort + ", " : "") +
                (limitTime != null ? "limitTime=" + limitTime + ", " : "") +
                (cpDate != null ? "cpDate=" + cpDate + ", " : "") +
                (cpType != null ? "cpType=" + cpType + ", " : "") +
                (arrestVessel != null ? "arrestVessel=" + arrestVessel + ", " : "") +
                (jurisdiction != null ? "jurisdiction=" + jurisdiction + ", " : "") +
                (applicableLaw != null ? "applicableLaw=" + applicableLaw + ", " : "") +
                (closeDate != null ? "closeDate=" + closeDate + ", " : "") +
                (closeHandler != null ? "closeHandler=" + closeHandler + ", " : "") +
                (settlementAmount != null ? "settlementAmount=" + settlementAmount + ", " : "") +
                (settlementDate != null ? "settlementDate=" + settlementDate + ", " : "") +
                (cpiInsuranceTypeId != null ? "cpiInsuranceTypeId=" + cpiInsuranceTypeId + ", " : "") +
                (caseStatusId != null ? "caseStatusId=" + caseStatusId + ", " : "") +
                (settlementModeId != null ? "settlementModeId=" + settlementModeId + ", " : "") +
            "}";
    }

}
