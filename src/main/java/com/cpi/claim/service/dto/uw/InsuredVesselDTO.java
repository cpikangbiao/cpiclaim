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

package com.cpi.claim.service.dto.uw;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the InsuredVessel entity.
 */
public class InsuredVesselDTO implements Serializable {

    private Long id;

    private Long numberId;

    @NotNull
    private Long vesselId;

    @NotNull
    private String insuredVesselCode;

    @NotNull
    private Instant entryDate;

    @NotNull
    private Instant drawDate;

    private String note;

    private Boolean noReleaseCall;

    private Boolean isWithdraw;

    private Instant withdrawDate;

    private Long withdrawUser;

    private Instant withdrawOperDate;

    private Boolean isSigned;

    private Long signUser;

    private Instant signDate;

    private Long processId;

    @Lob
    private String specialItem;

    private Long cpiInsuranceTypeId;

    private String cpiInsuranceTypeCpiInsuranceTypeName;

    private Long ivCertificateTypeId;

    private String ivCertificateTypeIvCertificateTypeName;

    private Long languageId;

    private String languageLanguageName;

    private Long memberId;

    private Long premiumAlgorithmTypeId;

    private String premiumAlgorithmTypePremiumAlgorithmTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberId() {
        return numberId;
    }

    public void setNumberId(Long numberId) {
        this.numberId = numberId;
    }

    public Long getVesselId() {
        return vesselId;
    }

    public void setVesselId(Long vesselId) {
        this.vesselId = vesselId;
    }

    public String getInsuredVesselCode() {
        return insuredVesselCode;
    }

    public void setInsuredVesselCode(String insuredVesselCode) {
        this.insuredVesselCode = insuredVesselCode;
    }

    public Instant getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Instant entryDate) {
        this.entryDate = entryDate;
    }

    public Instant getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Instant drawDate) {
        this.drawDate = drawDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean isNoReleaseCall() {
        return noReleaseCall;
    }

    public void setNoReleaseCall(Boolean noReleaseCall) {
        this.noReleaseCall = noReleaseCall;
    }

    public Boolean isIsWithdraw() {
        return isWithdraw;
    }

    public void setIsWithdraw(Boolean isWithdraw) {
        this.isWithdraw = isWithdraw;
    }

    public Instant getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Instant withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    public Long getWithdrawUser() {
        return withdrawUser;
    }

    public void setWithdrawUser(Long withdrawUser) {
        this.withdrawUser = withdrawUser;
    }

    public Instant getWithdrawOperDate() {
        return withdrawOperDate;
    }

    public void setWithdrawOperDate(Instant withdrawOperDate) {
        this.withdrawOperDate = withdrawOperDate;
    }

    public Boolean isIsSigned() {
        return isSigned;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public Long getSignUser() {
        return signUser;
    }

    public void setSignUser(Long signUser) {
        this.signUser = signUser;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public String getSpecialItem() {
        return specialItem;
    }

    public void setSpecialItem(String specialItem) {
        this.specialItem = specialItem;
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

    public Long getIvCertificateTypeId() {
        return ivCertificateTypeId;
    }

    public void setIvCertificateTypeId(Long ivCertificateTypeId) {
        this.ivCertificateTypeId = ivCertificateTypeId;
    }

    public String getIvCertificateTypeIvCertificateTypeName() {
        return ivCertificateTypeIvCertificateTypeName;
    }

    public void setIvCertificateTypeIvCertificateTypeName(String ivCertificateTypeIvCertificateTypeName) {
        this.ivCertificateTypeIvCertificateTypeName = ivCertificateTypeIvCertificateTypeName;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public String getLanguageLanguageName() {
        return languageLanguageName;
    }

    public void setLanguageLanguageName(String languageLanguageName) {
        this.languageLanguageName = languageLanguageName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPremiumAlgorithmTypeId() {
        return premiumAlgorithmTypeId;
    }

    public void setPremiumAlgorithmTypeId(Long premiumAlgorithmTypeId) {
        this.premiumAlgorithmTypeId = premiumAlgorithmTypeId;
    }

    public String getPremiumAlgorithmTypePremiumAlgorithmTypeName() {
        return premiumAlgorithmTypePremiumAlgorithmTypeName;
    }

    public void setPremiumAlgorithmTypePremiumAlgorithmTypeName(String premiumAlgorithmTypePremiumAlgorithmTypeName) {
        this.premiumAlgorithmTypePremiumAlgorithmTypeName = premiumAlgorithmTypePremiumAlgorithmTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InsuredVesselDTO insuredVesselDTO = (InsuredVesselDTO) o;
        if (insuredVesselDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), insuredVesselDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InsuredVesselDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", vesselId=" + getVesselId() +
            ", insuredVesselCode='" + getInsuredVesselCode() + "'" +
            ", entryDate='" + getEntryDate() + "'" +
            ", drawDate='" + getDrawDate() + "'" +
            ", note='" + getNote() + "'" +
            ", noReleaseCall='" + isNoReleaseCall() + "'" +
            ", isWithdraw='" + isIsWithdraw() + "'" +
            ", withdrawDate='" + getWithdrawDate() + "'" +
            ", withdrawUser=" + getWithdrawUser() +
            ", withdrawOperDate='" + getWithdrawOperDate() + "'" +
            ", isSigned='" + isIsSigned() + "'" +
            ", signUser=" + getSignUser() +
            ", signDate='" + getSignDate() + "'" +
            ", processId=" + getProcessId() +
            ", specialItem='" + getSpecialItem() + "'" +
            ", cpiInsuranceType=" + getCpiInsuranceTypeId() +
            ", cpiInsuranceType='" + getCpiInsuranceTypeCpiInsuranceTypeName() + "'" +
            ", ivCertificateType=" + getIvCertificateTypeId() +
            ", ivCertificateType='" + getIvCertificateTypeIvCertificateTypeName() + "'" +
            ", language=" + getLanguageId() +
            ", language='" + getLanguageLanguageName() + "'" +
            ", member=" + getMemberId() +
            ", premiumAlgorithmType=" + getPremiumAlgorithmTypeId() +
            ", premiumAlgorithmType='" + getPremiumAlgorithmTypePremiumAlgorithmTypeName() + "'" +
            "}";
    }
}
