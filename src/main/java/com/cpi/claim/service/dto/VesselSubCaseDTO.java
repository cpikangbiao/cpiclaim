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
import java.time.Instant;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.cpi.claim.domain.VesselSubCase} entity.
 */
public class VesselSubCaseDTO implements Serializable {

    private Long id;

    private Integer numberId;

    private Long assignedUserId;

    private Instant insertTime;

    private String subcaseCode;

    private String blNo;

    private String claimant;

    private String claimAmount;

    private Long currency;

    private BigDecimal deductible;

    private BigDecimal currencyRate;

    private BigDecimal deductDollar;

    @Lob
    private String remark;


    private Long vesselCaseId;

    private String vesselCaseCaseCode;

    private Long riskId;

    private String riskRiskName;

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

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public String getSubcaseCode() {
        return subcaseCode;
    }

    public void setSubcaseCode(String subcaseCode) {
        this.subcaseCode = subcaseCode;
    }

    public String getBlNo() {
        return blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getDeductDollar() {
        return deductDollar;
    }

    public void setDeductDollar(BigDecimal deductDollar) {
        this.deductDollar = deductDollar;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getVesselCaseId() {
        return vesselCaseId;
    }

    public void setVesselCaseId(Long vesselCaseId) {
        this.vesselCaseId = vesselCaseId;
    }

    public String getVesselCaseCaseCode() {
        return vesselCaseCaseCode;
    }

    public void setVesselCaseCaseCode(String vesselCaseCaseCode) {
        this.vesselCaseCaseCode = vesselCaseCaseCode;
    }

    public Long getRiskId() {
        return riskId;
    }

    public void setRiskId(Long riskId) {
        this.riskId = riskId;
    }

    public String getRiskRiskName() {
        return riskRiskName;
    }

    public void setRiskRiskName(String riskRiskName) {
        this.riskRiskName = riskRiskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VesselSubCaseDTO vesselSubCaseDTO = (VesselSubCaseDTO) o;
        if (vesselSubCaseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vesselSubCaseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VesselSubCaseDTO{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", assignedUserId=" + getAssignedUserId() +
            ", insertTime='" + getInsertTime() + "'" +
            ", subcaseCode='" + getSubcaseCode() + "'" +
            ", blNo='" + getBlNo() + "'" +
            ", claimant='" + getClaimant() + "'" +
            ", claimAmount='" + getClaimAmount() + "'" +
            ", currency=" + getCurrency() +
            ", deductible=" + getDeductible() +
            ", currencyRate=" + getCurrencyRate() +
            ", deductDollar=" + getDeductDollar() +
            ", remark='" + getRemark() + "'" +
            ", vesselCase=" + getVesselCaseId() +
            ", vesselCase='" + getVesselCaseCaseCode() + "'" +
            ", risk=" + getRiskId() +
            ", risk='" + getRiskRiskName() + "'" +
            "}";
    }
}
