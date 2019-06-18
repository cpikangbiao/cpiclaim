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
 * Criteria class for the CaseGuarantee entity. This class is used in CaseGuaranteeResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-guarantees?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CaseGuaranteeCriteria implements Serializable {
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

    public CaseGuaranteeCriteria() {
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
