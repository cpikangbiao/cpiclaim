package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
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
 * Criteria class for the {@link com.cpi.claim.domain.VesselCase} entity. This class is used
 * in {@link com.cpi.claim.web.rest.VesselCaseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /vessel-cases?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VesselCaseCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter numberId;

    private StringFilter caseYear;

    private LongFilter insuredVesselId;

    private StringFilter companyName;

    private StringFilter vesselName;

    private StringFilter companyChineseName;

    private StringFilter vesselChineseName;

    private LongFilter reinsureId;

    private BigDecimalFilter deduct;

    private LongFilter assignedHandler;

    private InstantFilter assignedTime;

    private LongFilter registeredHandler;

    private InstantFilter registeredDate;

    private StringFilter caseCode;

    private InstantFilter caseDate;

    private LongFilter caseLocation;

    private StringFilter caseDescription;

    private StringFilter voyageNo;

    private LongFilter loadingPort;

    private InstantFilter loadingDate;

    private LongFilter dischargingPort;

    private InstantFilter dischargingDate;

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

    public VesselCaseCriteria(){
    }

    public VesselCaseCriteria(VesselCaseCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.caseYear = other.caseYear == null ? null : other.caseYear.copy();
        this.insuredVesselId = other.insuredVesselId == null ? null : other.insuredVesselId.copy();
        this.companyName = other.companyName == null ? null : other.companyName.copy();
        this.vesselName = other.vesselName == null ? null : other.vesselName.copy();
        this.companyChineseName = other.companyChineseName == null ? null : other.companyChineseName.copy();
        this.vesselChineseName = other.vesselChineseName == null ? null : other.vesselChineseName.copy();
        this.reinsureId = other.reinsureId == null ? null : other.reinsureId.copy();
        this.deduct = other.deduct == null ? null : other.deduct.copy();
        this.assignedHandler = other.assignedHandler == null ? null : other.assignedHandler.copy();
        this.assignedTime = other.assignedTime == null ? null : other.assignedTime.copy();
        this.registeredHandler = other.registeredHandler == null ? null : other.registeredHandler.copy();
        this.registeredDate = other.registeredDate == null ? null : other.registeredDate.copy();
        this.caseCode = other.caseCode == null ? null : other.caseCode.copy();
        this.caseDate = other.caseDate == null ? null : other.caseDate.copy();
        this.caseLocation = other.caseLocation == null ? null : other.caseLocation.copy();
        this.caseDescription = other.caseDescription == null ? null : other.caseDescription.copy();
        this.voyageNo = other.voyageNo == null ? null : other.voyageNo.copy();
        this.loadingPort = other.loadingPort == null ? null : other.loadingPort.copy();
        this.loadingDate = other.loadingDate == null ? null : other.loadingDate.copy();
        this.dischargingPort = other.dischargingPort == null ? null : other.dischargingPort.copy();
        this.dischargingDate = other.dischargingDate == null ? null : other.dischargingDate.copy();
        this.limitTime = other.limitTime == null ? null : other.limitTime.copy();
        this.cpDate = other.cpDate == null ? null : other.cpDate.copy();
        this.cpType = other.cpType == null ? null : other.cpType.copy();
        this.arrestVessel = other.arrestVessel == null ? null : other.arrestVessel.copy();
        this.jurisdiction = other.jurisdiction == null ? null : other.jurisdiction.copy();
        this.applicableLaw = other.applicableLaw == null ? null : other.applicableLaw.copy();
        this.closeDate = other.closeDate == null ? null : other.closeDate.copy();
        this.closeHandler = other.closeHandler == null ? null : other.closeHandler.copy();
        this.settlementAmount = other.settlementAmount == null ? null : other.settlementAmount.copy();
        this.settlementDate = other.settlementDate == null ? null : other.settlementDate.copy();
        this.cpiInsuranceTypeId = other.cpiInsuranceTypeId == null ? null : other.cpiInsuranceTypeId.copy();
        this.caseStatusId = other.caseStatusId == null ? null : other.caseStatusId.copy();
        this.settlementModeId = other.settlementModeId == null ? null : other.settlementModeId.copy();
    }

    @Override
    public VesselCaseCriteria copy() {
        return new VesselCaseCriteria(this);
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

    public LongFilter getInsuredVesselId() {
        return insuredVesselId;
    }

    public void setInsuredVesselId(LongFilter insuredVesselId) {
        this.insuredVesselId = insuredVesselId;
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

    public LongFilter getReinsureId() {
        return reinsureId;
    }

    public void setReinsureId(LongFilter reinsureId) {
        this.reinsureId = reinsureId;
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

    public InstantFilter getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(InstantFilter caseDate) {
        this.caseDate = caseDate;
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

    public StringFilter getVoyageNo() {
        return voyageNo;
    }

    public void setVoyageNo(StringFilter voyageNo) {
        this.voyageNo = voyageNo;
    }

    public LongFilter getLoadingPort() {
        return loadingPort;
    }

    public void setLoadingPort(LongFilter loadingPort) {
        this.loadingPort = loadingPort;
    }

    public InstantFilter getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(InstantFilter loadingDate) {
        this.loadingDate = loadingDate;
    }

    public LongFilter getDischargingPort() {
        return dischargingPort;
    }

    public void setDischargingPort(LongFilter dischargingPort) {
        this.dischargingPort = dischargingPort;
    }

    public InstantFilter getDischargingDate() {
        return dischargingDate;
    }

    public void setDischargingDate(InstantFilter dischargingDate) {
        this.dischargingDate = dischargingDate;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VesselCaseCriteria that = (VesselCaseCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(caseYear, that.caseYear) &&
            Objects.equals(insuredVesselId, that.insuredVesselId) &&
            Objects.equals(companyName, that.companyName) &&
            Objects.equals(vesselName, that.vesselName) &&
            Objects.equals(companyChineseName, that.companyChineseName) &&
            Objects.equals(vesselChineseName, that.vesselChineseName) &&
            Objects.equals(reinsureId, that.reinsureId) &&
            Objects.equals(deduct, that.deduct) &&
            Objects.equals(assignedHandler, that.assignedHandler) &&
            Objects.equals(assignedTime, that.assignedTime) &&
            Objects.equals(registeredHandler, that.registeredHandler) &&
            Objects.equals(registeredDate, that.registeredDate) &&
            Objects.equals(caseCode, that.caseCode) &&
            Objects.equals(caseDate, that.caseDate) &&
            Objects.equals(caseLocation, that.caseLocation) &&
            Objects.equals(caseDescription, that.caseDescription) &&
            Objects.equals(voyageNo, that.voyageNo) &&
            Objects.equals(loadingPort, that.loadingPort) &&
            Objects.equals(loadingDate, that.loadingDate) &&
            Objects.equals(dischargingPort, that.dischargingPort) &&
            Objects.equals(dischargingDate, that.dischargingDate) &&
            Objects.equals(limitTime, that.limitTime) &&
            Objects.equals(cpDate, that.cpDate) &&
            Objects.equals(cpType, that.cpType) &&
            Objects.equals(arrestVessel, that.arrestVessel) &&
            Objects.equals(jurisdiction, that.jurisdiction) &&
            Objects.equals(applicableLaw, that.applicableLaw) &&
            Objects.equals(closeDate, that.closeDate) &&
            Objects.equals(closeHandler, that.closeHandler) &&
            Objects.equals(settlementAmount, that.settlementAmount) &&
            Objects.equals(settlementDate, that.settlementDate) &&
            Objects.equals(cpiInsuranceTypeId, that.cpiInsuranceTypeId) &&
            Objects.equals(caseStatusId, that.caseStatusId) &&
            Objects.equals(settlementModeId, that.settlementModeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        numberId,
        caseYear,
        insuredVesselId,
        companyName,
        vesselName,
        companyChineseName,
        vesselChineseName,
        reinsureId,
        deduct,
        assignedHandler,
        assignedTime,
        registeredHandler,
        registeredDate,
        caseCode,
        caseDate,
        caseLocation,
        caseDescription,
        voyageNo,
        loadingPort,
        loadingDate,
        dischargingPort,
        dischargingDate,
        limitTime,
        cpDate,
        cpType,
        arrestVessel,
        jurisdiction,
        applicableLaw,
        closeDate,
        closeHandler,
        settlementAmount,
        settlementDate,
        cpiInsuranceTypeId,
        caseStatusId,
        settlementModeId
        );
    }

    @Override
    public String toString() {
        return "VesselCaseCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (caseYear != null ? "caseYear=" + caseYear + ", " : "") +
                (insuredVesselId != null ? "insuredVesselId=" + insuredVesselId + ", " : "") +
                (companyName != null ? "companyName=" + companyName + ", " : "") +
                (vesselName != null ? "vesselName=" + vesselName + ", " : "") +
                (companyChineseName != null ? "companyChineseName=" + companyChineseName + ", " : "") +
                (vesselChineseName != null ? "vesselChineseName=" + vesselChineseName + ", " : "") +
                (reinsureId != null ? "reinsureId=" + reinsureId + ", " : "") +
                (deduct != null ? "deduct=" + deduct + ", " : "") +
                (assignedHandler != null ? "assignedHandler=" + assignedHandler + ", " : "") +
                (assignedTime != null ? "assignedTime=" + assignedTime + ", " : "") +
                (registeredHandler != null ? "registeredHandler=" + registeredHandler + ", " : "") +
                (registeredDate != null ? "registeredDate=" + registeredDate + ", " : "") +
                (caseCode != null ? "caseCode=" + caseCode + ", " : "") +
                (caseDate != null ? "caseDate=" + caseDate + ", " : "") +
                (caseLocation != null ? "caseLocation=" + caseLocation + ", " : "") +
                (caseDescription != null ? "caseDescription=" + caseDescription + ", " : "") +
                (voyageNo != null ? "voyageNo=" + voyageNo + ", " : "") +
                (loadingPort != null ? "loadingPort=" + loadingPort + ", " : "") +
                (loadingDate != null ? "loadingDate=" + loadingDate + ", " : "") +
                (dischargingPort != null ? "dischargingPort=" + dischargingPort + ", " : "") +
                (dischargingDate != null ? "dischargingDate=" + dischargingDate + ", " : "") +
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
