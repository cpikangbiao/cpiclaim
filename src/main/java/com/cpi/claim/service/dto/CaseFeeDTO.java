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
 * A DTO for the {@link com.cpi.claim.domain.CaseFee} entity.
 */
public class CaseFeeDTO implements Serializable {

    private Long id;

    private String clientNo;

    private Integer numberId;

    private Long currency;

    private BigDecimal currencyRate;

    private Instant feeCostDate;

    private BigDecimal feeCost;

    private BigDecimal feeCostDollar;

    private BigDecimal deduct;

    private Long deductCurrency;

    private BigDecimal deductCurrencyRate;

    private BigDecimal deductAmount;

    private BigDecimal amountDollar;

    private Long feeCreateUser;

    private Instant feeCreateDate;

    @Lob
    private String remark;

    private Boolean isSigned;

    private Long signUser;

    private Long signDate;

    private Long processId;


    private Long feeTypeId;

    private String feeTypeFeeTypeName;

    private Long subcaseId;

    private String subcaseSubcaseCode;

    private Long creditorId;

    private String creditorCreditorCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public Instant getFeeCostDate() {
        return feeCostDate;
    }

    public void setFeeCostDate(Instant feeCostDate) {
        this.feeCostDate = feeCostDate;
    }

    public BigDecimal getFeeCost() {
        return feeCost;
    }

    public void setFeeCost(BigDecimal feeCost) {
        this.feeCost = feeCost;
    }

    public BigDecimal getFeeCostDollar() {
        return feeCostDollar;
    }

    public void setFeeCostDollar(BigDecimal feeCostDollar) {
        this.feeCostDollar = feeCostDollar;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public Long getDeductCurrency() {
        return deductCurrency;
    }

    public void setDeductCurrency(Long deductCurrency) {
        this.deductCurrency = deductCurrency;
    }

    public BigDecimal getDeductCurrencyRate() {
        return deductCurrencyRate;
    }

    public void setDeductCurrencyRate(BigDecimal deductCurrencyRate) {
        this.deductCurrencyRate = deductCurrencyRate;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public BigDecimal getAmountDollar() {
        return amountDollar;
    }

    public void setAmountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
    }

    public Long getFeeCreateUser() {
        return feeCreateUser;
    }

    public void setFeeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
    }

    public Instant getFeeCreateDate() {
        return feeCreateDate;
    }

    public void setFeeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getSignDate() {
        return signDate;
    }

    public void setSignDate(Long signDate) {
        this.signDate = signDate;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Long feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public String getFeeTypeFeeTypeName() {
        return feeTypeFeeTypeName;
    }

    public void setFeeTypeFeeTypeName(String feeTypeFeeTypeName) {
        this.feeTypeFeeTypeName = feeTypeFeeTypeName;
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

    public Long getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Long creditorId) {
        this.creditorId = creditorId;
    }

    public String getCreditorCreditorCode() {
        return creditorCreditorCode;
    }

    public void setCreditorCreditorCode(String creditorCreditorCode) {
        this.creditorCreditorCode = creditorCreditorCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseFeeDTO caseFeeDTO = (CaseFeeDTO) o;
        if (caseFeeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseFeeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseFeeDTO{" +
            "id=" + getId() +
            ", clientNo='" + getClientNo() + "'" +
            ", numberId=" + getNumberId() +
            ", currency=" + getCurrency() +
            ", currencyRate=" + getCurrencyRate() +
            ", feeCostDate='" + getFeeCostDate() + "'" +
            ", feeCost=" + getFeeCost() +
            ", feeCostDollar=" + getFeeCostDollar() +
            ", deduct=" + getDeduct() +
            ", deductCurrency=" + getDeductCurrency() +
            ", deductCurrencyRate=" + getDeductCurrencyRate() +
            ", deductAmount=" + getDeductAmount() +
            ", amountDollar=" + getAmountDollar() +
            ", feeCreateUser=" + getFeeCreateUser() +
            ", feeCreateDate='" + getFeeCreateDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", isSigned='" + isIsSigned() + "'" +
            ", signUser=" + getSignUser() +
            ", signDate=" + getSignDate() +
            ", processId=" + getProcessId() +
            ", feeType=" + getFeeTypeId() +
            ", feeType='" + getFeeTypeFeeTypeName() + "'" +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            ", creditor=" + getCreditorId() +
            ", creditor='" + getCreditorCreditorCode() + "'" +
            "}";
    }
}
