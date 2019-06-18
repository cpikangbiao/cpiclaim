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
 * Criteria class for the {@link com.cpi.claim.domain.CaseGuarantee} entity. This class is used
 * in {@link com.cpi.claim.web.rest.CaseGuaranteeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /case-guarantees?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseGuaranteeCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter vesselId;

    private IntegerFilter numberId;

    private InstantFilter arrestDate;

    private LongFilter arrestPort;

    private LongFilter arrestVesselId;

    private StringFilter arrestVesselName;

    private StringFilter billOfLading;

    private InstantFilter billLadingDate;

    private StringFilter natureOfClaim;

    private StringFilter guarantee;

    private InstantFilter guaranteeDate;

    private LongFilter guaranteeCurrency;

    private BigDecimalFilter guaranteeRate;

    private BigDecimalFilter guaranteeAmount;

    private BigDecimalFilter guaranteeAmountDollar;

    private StringFilter guaranteeTo;

    private StringFilter guaranteeToAddress;

    private StringFilter guaranteeNo;

    private BigDecimalFilter guaranteeFee;

    private StringFilter guaranteeOther;

    private InstantFilter cancelDate;

    private StringFilter conGuarantee;

    private InstantFilter conGuaranteeDate;

    private LongFilter conGuaranteeCurrency;

    private BigDecimalFilter conGuaranteeRate;

    private BigDecimalFilter conGuaranteeAmount;

    private BigDecimalFilter conGuaranteeAmountDollar;

    private StringFilter conGuaranteeNo;

    private StringFilter conGuaranteeTo;

    private InstantFilter conGuaranteeCancelDate;

    private InstantFilter releaseDate;

    private LongFilter registerUserId;

    private LongFilter subcaseId;

    private LongFilter guaranteeTypeId;

    private LongFilter conGuaranteeTypeId;

    public CaseGuaranteeCriteria(){
    }

    public CaseGuaranteeCriteria(CaseGuaranteeCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.vesselId = other.vesselId == null ? null : other.vesselId.copy();
        this.numberId = other.numberId == null ? null : other.numberId.copy();
        this.arrestDate = other.arrestDate == null ? null : other.arrestDate.copy();
        this.arrestPort = other.arrestPort == null ? null : other.arrestPort.copy();
        this.arrestVesselId = other.arrestVesselId == null ? null : other.arrestVesselId.copy();
        this.arrestVesselName = other.arrestVesselName == null ? null : other.arrestVesselName.copy();
        this.billOfLading = other.billOfLading == null ? null : other.billOfLading.copy();
        this.billLadingDate = other.billLadingDate == null ? null : other.billLadingDate.copy();
        this.natureOfClaim = other.natureOfClaim == null ? null : other.natureOfClaim.copy();
        this.guarantee = other.guarantee == null ? null : other.guarantee.copy();
        this.guaranteeDate = other.guaranteeDate == null ? null : other.guaranteeDate.copy();
        this.guaranteeCurrency = other.guaranteeCurrency == null ? null : other.guaranteeCurrency.copy();
        this.guaranteeRate = other.guaranteeRate == null ? null : other.guaranteeRate.copy();
        this.guaranteeAmount = other.guaranteeAmount == null ? null : other.guaranteeAmount.copy();
        this.guaranteeAmountDollar = other.guaranteeAmountDollar == null ? null : other.guaranteeAmountDollar.copy();
        this.guaranteeTo = other.guaranteeTo == null ? null : other.guaranteeTo.copy();
        this.guaranteeToAddress = other.guaranteeToAddress == null ? null : other.guaranteeToAddress.copy();
        this.guaranteeNo = other.guaranteeNo == null ? null : other.guaranteeNo.copy();
        this.guaranteeFee = other.guaranteeFee == null ? null : other.guaranteeFee.copy();
        this.guaranteeOther = other.guaranteeOther == null ? null : other.guaranteeOther.copy();
        this.cancelDate = other.cancelDate == null ? null : other.cancelDate.copy();
        this.conGuarantee = other.conGuarantee == null ? null : other.conGuarantee.copy();
        this.conGuaranteeDate = other.conGuaranteeDate == null ? null : other.conGuaranteeDate.copy();
        this.conGuaranteeCurrency = other.conGuaranteeCurrency == null ? null : other.conGuaranteeCurrency.copy();
        this.conGuaranteeRate = other.conGuaranteeRate == null ? null : other.conGuaranteeRate.copy();
        this.conGuaranteeAmount = other.conGuaranteeAmount == null ? null : other.conGuaranteeAmount.copy();
        this.conGuaranteeAmountDollar = other.conGuaranteeAmountDollar == null ? null : other.conGuaranteeAmountDollar.copy();
        this.conGuaranteeNo = other.conGuaranteeNo == null ? null : other.conGuaranteeNo.copy();
        this.conGuaranteeTo = other.conGuaranteeTo == null ? null : other.conGuaranteeTo.copy();
        this.conGuaranteeCancelDate = other.conGuaranteeCancelDate == null ? null : other.conGuaranteeCancelDate.copy();
        this.releaseDate = other.releaseDate == null ? null : other.releaseDate.copy();
        this.registerUserId = other.registerUserId == null ? null : other.registerUserId.copy();
        this.subcaseId = other.subcaseId == null ? null : other.subcaseId.copy();
        this.guaranteeTypeId = other.guaranteeTypeId == null ? null : other.guaranteeTypeId.copy();
        this.conGuaranteeTypeId = other.conGuaranteeTypeId == null ? null : other.conGuaranteeTypeId.copy();
    }

    @Override
    public CaseGuaranteeCriteria copy() {
        return new CaseGuaranteeCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getVesselId() {
        return vesselId;
    }

    public void setVesselId(LongFilter vesselId) {
        this.vesselId = vesselId;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public InstantFilter getArrestDate() {
        return arrestDate;
    }

    public void setArrestDate(InstantFilter arrestDate) {
        this.arrestDate = arrestDate;
    }

    public LongFilter getArrestPort() {
        return arrestPort;
    }

    public void setArrestPort(LongFilter arrestPort) {
        this.arrestPort = arrestPort;
    }

    public LongFilter getArrestVesselId() {
        return arrestVesselId;
    }

    public void setArrestVesselId(LongFilter arrestVesselId) {
        this.arrestVesselId = arrestVesselId;
    }

    public StringFilter getArrestVesselName() {
        return arrestVesselName;
    }

    public void setArrestVesselName(StringFilter arrestVesselName) {
        this.arrestVesselName = arrestVesselName;
    }

    public StringFilter getBillOfLading() {
        return billOfLading;
    }

    public void setBillOfLading(StringFilter billOfLading) {
        this.billOfLading = billOfLading;
    }

    public InstantFilter getBillLadingDate() {
        return billLadingDate;
    }

    public void setBillLadingDate(InstantFilter billLadingDate) {
        this.billLadingDate = billLadingDate;
    }

    public StringFilter getNatureOfClaim() {
        return natureOfClaim;
    }

    public void setNatureOfClaim(StringFilter natureOfClaim) {
        this.natureOfClaim = natureOfClaim;
    }

    public StringFilter getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(StringFilter guarantee) {
        this.guarantee = guarantee;
    }

    public InstantFilter getGuaranteeDate() {
        return guaranteeDate;
    }

    public void setGuaranteeDate(InstantFilter guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
    }

    public LongFilter getGuaranteeCurrency() {
        return guaranteeCurrency;
    }

    public void setGuaranteeCurrency(LongFilter guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
    }

    public BigDecimalFilter getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(BigDecimalFilter guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public BigDecimalFilter getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimalFilter guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public BigDecimalFilter getGuaranteeAmountDollar() {
        return guaranteeAmountDollar;
    }

    public void setGuaranteeAmountDollar(BigDecimalFilter guaranteeAmountDollar) {
        this.guaranteeAmountDollar = guaranteeAmountDollar;
    }

    public StringFilter getGuaranteeTo() {
        return guaranteeTo;
    }

    public void setGuaranteeTo(StringFilter guaranteeTo) {
        this.guaranteeTo = guaranteeTo;
    }

    public StringFilter getGuaranteeToAddress() {
        return guaranteeToAddress;
    }

    public void setGuaranteeToAddress(StringFilter guaranteeToAddress) {
        this.guaranteeToAddress = guaranteeToAddress;
    }

    public StringFilter getGuaranteeNo() {
        return guaranteeNo;
    }

    public void setGuaranteeNo(StringFilter guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
    }

    public BigDecimalFilter getGuaranteeFee() {
        return guaranteeFee;
    }

    public void setGuaranteeFee(BigDecimalFilter guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
    }

    public StringFilter getGuaranteeOther() {
        return guaranteeOther;
    }

    public void setGuaranteeOther(StringFilter guaranteeOther) {
        this.guaranteeOther = guaranteeOther;
    }

    public InstantFilter getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(InstantFilter cancelDate) {
        this.cancelDate = cancelDate;
    }

    public StringFilter getConGuarantee() {
        return conGuarantee;
    }

    public void setConGuarantee(StringFilter conGuarantee) {
        this.conGuarantee = conGuarantee;
    }

    public InstantFilter getConGuaranteeDate() {
        return conGuaranteeDate;
    }

    public void setConGuaranteeDate(InstantFilter conGuaranteeDate) {
        this.conGuaranteeDate = conGuaranteeDate;
    }

    public LongFilter getConGuaranteeCurrency() {
        return conGuaranteeCurrency;
    }

    public void setConGuaranteeCurrency(LongFilter conGuaranteeCurrency) {
        this.conGuaranteeCurrency = conGuaranteeCurrency;
    }

    public BigDecimalFilter getConGuaranteeRate() {
        return conGuaranteeRate;
    }

    public void setConGuaranteeRate(BigDecimalFilter conGuaranteeRate) {
        this.conGuaranteeRate = conGuaranteeRate;
    }

    public BigDecimalFilter getConGuaranteeAmount() {
        return conGuaranteeAmount;
    }

    public void setConGuaranteeAmount(BigDecimalFilter conGuaranteeAmount) {
        this.conGuaranteeAmount = conGuaranteeAmount;
    }

    public BigDecimalFilter getConGuaranteeAmountDollar() {
        return conGuaranteeAmountDollar;
    }

    public void setConGuaranteeAmountDollar(BigDecimalFilter conGuaranteeAmountDollar) {
        this.conGuaranteeAmountDollar = conGuaranteeAmountDollar;
    }

    public StringFilter getConGuaranteeNo() {
        return conGuaranteeNo;
    }

    public void setConGuaranteeNo(StringFilter conGuaranteeNo) {
        this.conGuaranteeNo = conGuaranteeNo;
    }

    public StringFilter getConGuaranteeTo() {
        return conGuaranteeTo;
    }

    public void setConGuaranteeTo(StringFilter conGuaranteeTo) {
        this.conGuaranteeTo = conGuaranteeTo;
    }

    public InstantFilter getConGuaranteeCancelDate() {
        return conGuaranteeCancelDate;
    }

    public void setConGuaranteeCancelDate(InstantFilter conGuaranteeCancelDate) {
        this.conGuaranteeCancelDate = conGuaranteeCancelDate;
    }

    public InstantFilter getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(InstantFilter releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LongFilter getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(LongFilter registerUserId) {
        this.registerUserId = registerUserId;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }

    public LongFilter getGuaranteeTypeId() {
        return guaranteeTypeId;
    }

    public void setGuaranteeTypeId(LongFilter guaranteeTypeId) {
        this.guaranteeTypeId = guaranteeTypeId;
    }

    public LongFilter getConGuaranteeTypeId() {
        return conGuaranteeTypeId;
    }

    public void setConGuaranteeTypeId(LongFilter conGuaranteeTypeId) {
        this.conGuaranteeTypeId = conGuaranteeTypeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CaseGuaranteeCriteria that = (CaseGuaranteeCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(vesselId, that.vesselId) &&
            Objects.equals(numberId, that.numberId) &&
            Objects.equals(arrestDate, that.arrestDate) &&
            Objects.equals(arrestPort, that.arrestPort) &&
            Objects.equals(arrestVesselId, that.arrestVesselId) &&
            Objects.equals(arrestVesselName, that.arrestVesselName) &&
            Objects.equals(billOfLading, that.billOfLading) &&
            Objects.equals(billLadingDate, that.billLadingDate) &&
            Objects.equals(natureOfClaim, that.natureOfClaim) &&
            Objects.equals(guarantee, that.guarantee) &&
            Objects.equals(guaranteeDate, that.guaranteeDate) &&
            Objects.equals(guaranteeCurrency, that.guaranteeCurrency) &&
            Objects.equals(guaranteeRate, that.guaranteeRate) &&
            Objects.equals(guaranteeAmount, that.guaranteeAmount) &&
            Objects.equals(guaranteeAmountDollar, that.guaranteeAmountDollar) &&
            Objects.equals(guaranteeTo, that.guaranteeTo) &&
            Objects.equals(guaranteeToAddress, that.guaranteeToAddress) &&
            Objects.equals(guaranteeNo, that.guaranteeNo) &&
            Objects.equals(guaranteeFee, that.guaranteeFee) &&
            Objects.equals(guaranteeOther, that.guaranteeOther) &&
            Objects.equals(cancelDate, that.cancelDate) &&
            Objects.equals(conGuarantee, that.conGuarantee) &&
            Objects.equals(conGuaranteeDate, that.conGuaranteeDate) &&
            Objects.equals(conGuaranteeCurrency, that.conGuaranteeCurrency) &&
            Objects.equals(conGuaranteeRate, that.conGuaranteeRate) &&
            Objects.equals(conGuaranteeAmount, that.conGuaranteeAmount) &&
            Objects.equals(conGuaranteeAmountDollar, that.conGuaranteeAmountDollar) &&
            Objects.equals(conGuaranteeNo, that.conGuaranteeNo) &&
            Objects.equals(conGuaranteeTo, that.conGuaranteeTo) &&
            Objects.equals(conGuaranteeCancelDate, that.conGuaranteeCancelDate) &&
            Objects.equals(releaseDate, that.releaseDate) &&
            Objects.equals(registerUserId, that.registerUserId) &&
            Objects.equals(subcaseId, that.subcaseId) &&
            Objects.equals(guaranteeTypeId, that.guaranteeTypeId) &&
            Objects.equals(conGuaranteeTypeId, that.conGuaranteeTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        vesselId,
        numberId,
        arrestDate,
        arrestPort,
        arrestVesselId,
        arrestVesselName,
        billOfLading,
        billLadingDate,
        natureOfClaim,
        guarantee,
        guaranteeDate,
        guaranteeCurrency,
        guaranteeRate,
        guaranteeAmount,
        guaranteeAmountDollar,
        guaranteeTo,
        guaranteeToAddress,
        guaranteeNo,
        guaranteeFee,
        guaranteeOther,
        cancelDate,
        conGuarantee,
        conGuaranteeDate,
        conGuaranteeCurrency,
        conGuaranteeRate,
        conGuaranteeAmount,
        conGuaranteeAmountDollar,
        conGuaranteeNo,
        conGuaranteeTo,
        conGuaranteeCancelDate,
        releaseDate,
        registerUserId,
        subcaseId,
        guaranteeTypeId,
        conGuaranteeTypeId
        );
    }

    @Override
    public String toString() {
        return "CaseGuaranteeCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (vesselId != null ? "vesselId=" + vesselId + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (arrestDate != null ? "arrestDate=" + arrestDate + ", " : "") +
                (arrestPort != null ? "arrestPort=" + arrestPort + ", " : "") +
                (arrestVesselId != null ? "arrestVesselId=" + arrestVesselId + ", " : "") +
                (arrestVesselName != null ? "arrestVesselName=" + arrestVesselName + ", " : "") +
                (billOfLading != null ? "billOfLading=" + billOfLading + ", " : "") +
                (billLadingDate != null ? "billLadingDate=" + billLadingDate + ", " : "") +
                (natureOfClaim != null ? "natureOfClaim=" + natureOfClaim + ", " : "") +
                (guarantee != null ? "guarantee=" + guarantee + ", " : "") +
                (guaranteeDate != null ? "guaranteeDate=" + guaranteeDate + ", " : "") +
                (guaranteeCurrency != null ? "guaranteeCurrency=" + guaranteeCurrency + ", " : "") +
                (guaranteeRate != null ? "guaranteeRate=" + guaranteeRate + ", " : "") +
                (guaranteeAmount != null ? "guaranteeAmount=" + guaranteeAmount + ", " : "") +
                (guaranteeAmountDollar != null ? "guaranteeAmountDollar=" + guaranteeAmountDollar + ", " : "") +
                (guaranteeTo != null ? "guaranteeTo=" + guaranteeTo + ", " : "") +
                (guaranteeToAddress != null ? "guaranteeToAddress=" + guaranteeToAddress + ", " : "") +
                (guaranteeNo != null ? "guaranteeNo=" + guaranteeNo + ", " : "") +
                (guaranteeFee != null ? "guaranteeFee=" + guaranteeFee + ", " : "") +
                (guaranteeOther != null ? "guaranteeOther=" + guaranteeOther + ", " : "") +
                (cancelDate != null ? "cancelDate=" + cancelDate + ", " : "") +
                (conGuarantee != null ? "conGuarantee=" + conGuarantee + ", " : "") +
                (conGuaranteeDate != null ? "conGuaranteeDate=" + conGuaranteeDate + ", " : "") +
                (conGuaranteeCurrency != null ? "conGuaranteeCurrency=" + conGuaranteeCurrency + ", " : "") +
                (conGuaranteeRate != null ? "conGuaranteeRate=" + conGuaranteeRate + ", " : "") +
                (conGuaranteeAmount != null ? "conGuaranteeAmount=" + conGuaranteeAmount + ", " : "") +
                (conGuaranteeAmountDollar != null ? "conGuaranteeAmountDollar=" + conGuaranteeAmountDollar + ", " : "") +
                (conGuaranteeNo != null ? "conGuaranteeNo=" + conGuaranteeNo + ", " : "") +
                (conGuaranteeTo != null ? "conGuaranteeTo=" + conGuaranteeTo + ", " : "") +
                (conGuaranteeCancelDate != null ? "conGuaranteeCancelDate=" + conGuaranteeCancelDate + ", " : "") +
                (releaseDate != null ? "releaseDate=" + releaseDate + ", " : "") +
                (registerUserId != null ? "registerUserId=" + registerUserId + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (guaranteeTypeId != null ? "guaranteeTypeId=" + guaranteeTypeId + ", " : "") +
                (conGuaranteeTypeId != null ? "conGuaranteeTypeId=" + conGuaranteeTypeId + ", " : "") +
            "}";
    }

}
