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
 * A DTO for the CaseRecovery entity.
 */
public class CaseRecoveryDTO implements Serializable {

    private Long id;

    private String clientNo;

    private Integer numberId;

    private Long currency;

    private BigDecimal currencyRate;

    private Instant issueDate;

    private BigDecimal issueAmount;

    private Instant receivedDate;

    private BigDecimal receivedAmount;

    private BigDecimal amountDollar;

    private Instant registerDate;

    private Long registerUser;

    @Lob
    private String remark;

    private Long subcaseId;

    private String subcaseSubcaseCode;

    private Long recoveryTypeId;

    private String recoveryTypeRecoveryTypeName;

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

    public Instant getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    public Instant getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getAmountDollar() {
        return amountDollar;
    }

    public void setAmountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
    }

    public Instant getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Instant registerDate) {
        this.registerDate = registerDate;
    }

    public Long getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(Long registerUser) {
        this.registerUser = registerUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getRecoveryTypeId() {
        return recoveryTypeId;
    }

    public void setRecoveryTypeId(Long recoveryTypeId) {
        this.recoveryTypeId = recoveryTypeId;
    }

    public String getRecoveryTypeRecoveryTypeName() {
        return recoveryTypeRecoveryTypeName;
    }

    public void setRecoveryTypeRecoveryTypeName(String recoveryTypeRecoveryTypeName) {
        this.recoveryTypeRecoveryTypeName = recoveryTypeRecoveryTypeName;
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

        CaseRecoveryDTO caseRecoveryDTO = (CaseRecoveryDTO) o;
        if (caseRecoveryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseRecoveryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseRecoveryDTO{" +
            "id=" + getId() +
            ", clientNo='" + getClientNo() + "'" +
            ", numberId=" + getNumberId() +
            ", currency=" + getCurrency() +
            ", currencyRate=" + getCurrencyRate() +
            ", issueDate='" + getIssueDate() + "'" +
            ", issueAmount=" + getIssueAmount() +
            ", receivedDate='" + getReceivedDate() + "'" +
            ", receivedAmount=" + getReceivedAmount() +
            ", amountDollar=" + getAmountDollar() +
            ", registerDate='" + getRegisterDate() + "'" +
            ", registerUser=" + getRegisterUser() +
            ", remark='" + getRemark() + "'" +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            ", recoveryType=" + getRecoveryTypeId() +
            ", recoveryType='" + getRecoveryTypeRecoveryTypeName() + "'" +
            ", creditor=" + getCreditorId() +
            ", creditor='" + getCreditorCreditorCode() + "'" +
            "}";
    }
}
