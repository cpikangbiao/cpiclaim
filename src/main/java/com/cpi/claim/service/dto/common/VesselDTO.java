package com.cpi.claim.service.dto.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Vessel entity.
 */
public class VesselDTO implements Serializable {

    private Long id;

    private String vesselCode;

    private String vesselName;

    private String vesselChineseName;

    @NotNull
    private Double gtr;

    private Double wrt;

    private String buildLocation;

    @Size(min = 4, max = 4)
    private String buildYear;

    @Size(max = 20)
    private String foc;

    private Double dwt;

    private Double hullHmAmount;

    private Double hullHmValue;

    private Double hullIvAmount;

    private Double hullIvValue;

    private Double hullWarAmount;

    private Double hullWarValue;

    private String vesselSize;

    @Size(max = 20)
    private String line;

    private Double deeper;

    @Size(max = 15)
    private String callSign;

    @Size(max = 10)
    private String imoNumber;

    private Long vesselCountryId;

    private String vesselCountryCountryName;

    private Long vesselCurrencyId;

    private String vesselCurrencyNameAbbre;

    private Long vesselTypeId;

    private String vesselTypeVesselTypeName;

    private Long vesselOwnerCompanyId;

    private String vesselOwnerCompanyCompanyName;

    private Long registedPortId;

    private String registedPortPortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVesselCode() {
        return vesselCode;
    }

    public void setVesselCode(String vesselCode) {
        this.vesselCode = vesselCode;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getVesselChineseName() {
        return vesselChineseName;
    }

    public void setVesselChineseName(String vesselChineseName) {
        this.vesselChineseName = vesselChineseName;
    }

    public Double getGtr() {
        return gtr;
    }

    public void setGtr(Double gtr) {
        this.gtr = gtr;
    }

    public Double getWrt() {
        return wrt;
    }

    public void setWrt(Double wrt) {
        this.wrt = wrt;
    }

    public String getBuildLocation() {
        return buildLocation;
    }

    public void setBuildLocation(String buildLocation) {
        this.buildLocation = buildLocation;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getFoc() {
        return foc;
    }

    public void setFoc(String foc) {
        this.foc = foc;
    }

    public Double getDwt() {
        return dwt;
    }

    public void setDwt(Double dwt) {
        this.dwt = dwt;
    }

    public Double getHullHmAmount() {
        return hullHmAmount;
    }

    public void setHullHmAmount(Double hullHmAmount) {
        this.hullHmAmount = hullHmAmount;
    }

    public Double getHullHmValue() {
        return hullHmValue;
    }

    public void setHullHmValue(Double hullHmValue) {
        this.hullHmValue = hullHmValue;
    }

    public Double getHullIvAmount() {
        return hullIvAmount;
    }

    public void setHullIvAmount(Double hullIvAmount) {
        this.hullIvAmount = hullIvAmount;
    }

    public Double getHullIvValue() {
        return hullIvValue;
    }

    public void setHullIvValue(Double hullIvValue) {
        this.hullIvValue = hullIvValue;
    }

    public Double getHullWarAmount() {
        return hullWarAmount;
    }

    public void setHullWarAmount(Double hullWarAmount) {
        this.hullWarAmount = hullWarAmount;
    }

    public Double getHullWarValue() {
        return hullWarValue;
    }

    public void setHullWarValue(Double hullWarValue) {
        this.hullWarValue = hullWarValue;
    }

    public String getVesselSize() {
        return vesselSize;
    }

    public void setVesselSize(String vesselSize) {
        this.vesselSize = vesselSize;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Double getDeeper() {
        return deeper;
    }

    public void setDeeper(Double deeper) {
        this.deeper = deeper;
    }

    public String getCallSign() {
        return callSign;
    }

    public void setCallSign(String callSign) {
        this.callSign = callSign;
    }

    public String getImoNumber() {
        return imoNumber;
    }

    public void setImoNumber(String imoNumber) {
        this.imoNumber = imoNumber;
    }

    public Long getVesselCountryId() {
        return vesselCountryId;
    }

    public void setVesselCountryId(Long countryId) {
        this.vesselCountryId = countryId;
    }

    public String getVesselCountryCountryName() {
        return vesselCountryCountryName;
    }

    public void setVesselCountryCountryName(String countryCountryName) {
        this.vesselCountryCountryName = countryCountryName;
    }

    public Long getVesselCurrencyId() {
        return vesselCurrencyId;
    }

    public void setVesselCurrencyId(Long currencyId) {
        this.vesselCurrencyId = currencyId;
    }

    public String getVesselCurrencyNameAbbre() {
        return vesselCurrencyNameAbbre;
    }

    public void setVesselCurrencyNameAbbre(String currencyNameAbbre) {
        this.vesselCurrencyNameAbbre = currencyNameAbbre;
    }

    public Long getVesselTypeId() {
        return vesselTypeId;
    }

    public void setVesselTypeId(Long vesselTypeId) {
        this.vesselTypeId = vesselTypeId;
    }

    public String getVesselTypeVesselTypeName() {
        return vesselTypeVesselTypeName;
    }

    public void setVesselTypeVesselTypeName(String vesselTypeVesselTypeName) {
        this.vesselTypeVesselTypeName = vesselTypeVesselTypeName;
    }

    public Long getVesselOwnerCompanyId() {
        return vesselOwnerCompanyId;
    }

    public void setVesselOwnerCompanyId(Long companyId) {
        this.vesselOwnerCompanyId = companyId;
    }

    public String getVesselOwnerCompanyCompanyName() {
        return vesselOwnerCompanyCompanyName;
    }

    public void setVesselOwnerCompanyCompanyName(String companyCompanyName) {
        this.vesselOwnerCompanyCompanyName = companyCompanyName;
    }

    public Long getRegistedPortId() {
        return registedPortId;
    }

    public void setRegistedPortId(Long portId) {
        this.registedPortId = portId;
    }

    public String getRegistedPortPortName() {
        return registedPortPortName;
    }

    public void setRegistedPortPortName(String portPortName) {
        this.registedPortPortName = portPortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VesselDTO vesselDTO = (VesselDTO) o;
        if (vesselDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vesselDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VesselDTO{" +
            "id=" + getId() +
            ", vesselCode='" + getVesselCode() + "'" +
            ", vesselName='" + getVesselName() + "'" +
            ", vesselChineseName='" + getVesselChineseName() + "'" +
            ", gtr=" + getGtr() +
            ", wrt=" + getWrt() +
            ", buildLocation='" + getBuildLocation() + "'" +
            ", buildYear='" + getBuildYear() + "'" +
            ", foc='" + getFoc() + "'" +
            ", dwt=" + getDwt() +
            ", hullHmAmount=" + getHullHmAmount() +
            ", hullHmValue=" + getHullHmValue() +
            ", hullIvAmount=" + getHullIvAmount() +
            ", hullIvValue=" + getHullIvValue() +
            ", hullWarAmount=" + getHullWarAmount() +
            ", hullWarValue=" + getHullWarValue() +
            ", vesselSize='" + getVesselSize() + "'" +
            ", line='" + getLine() + "'" +
            ", deeper=" + getDeeper() +
            ", callSign='" + getCallSign() + "'" +
            ", imoNumber='" + getImoNumber() + "'" +
            ", vesselCountry=" + getVesselCountryId() +
            ", vesselCountry='" + getVesselCountryCountryName() + "'" +
            ", vesselCurrency=" + getVesselCurrencyId() +
            ", vesselCurrency='" + getVesselCurrencyNameAbbre() + "'" +
            ", vesselType=" + getVesselTypeId() +
            ", vesselType='" + getVesselTypeVesselTypeName() + "'" +
            ", vesselOwnerCompany=" + getVesselOwnerCompanyId() +
            ", vesselOwnerCompany='" + getVesselOwnerCompanyCompanyName() + "'" +
            ", registedPort=" + getRegistedPortId() +
            ", registedPort='" + getRegistedPortPortName() + "'" +
            "}";
    }
}
