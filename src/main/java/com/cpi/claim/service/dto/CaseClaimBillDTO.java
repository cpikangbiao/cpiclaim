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
 * A DTO for the CaseClaimBill entity.
 */
public class CaseClaimBillDTO implements Serializable {

    private Long id;

    private String claimBillCode;

    private Instant claimBillDate;

    private Long registerUserId;

    private String clientBillNo;

    private String billDescription;

    private Instant dueDate;

    private String memberYear;

    private Integer numberId;

    private Long claimAmountCurrency;

    private BigDecimal claimAmount;

    private BigDecimal deductible;

    private Long deductibleCurrency;

    private BigDecimal deductibleCurrencyRate;

    private BigDecimal deductibleDollar;

    private Long billCurrency;

    private BigDecimal billAmount;

    private BigDecimal billCurrencyRate;

    private BigDecimal billAmountDollar;

    @Lob
    private String remark;

    private Boolean isSigned;

    private Long signUser;

    private Instant signDate;

    private Long processId;

    private Integer printNumber;

    private Long subcaseId;

    private String subcaseSubcaseCode;

    private Long claimBillStatusId;

    private String claimBillStatusClaimBillStatusName;

    private Long claimBillTypeId;

    private String claimBillTypeClaimBillTypeName;

    private Long claimBillFinanceTypeId;

    private String claimBillFinanceTypeClaimBillFinanceTypeName;

    private Long creditorId;

    private String creditorCreditorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimBillCode() {
        return claimBillCode;
    }

    public void setClaimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public Instant getClaimBillDate() {
        return claimBillDate;
    }

    public void setClaimBillDate(Instant claimBillDate) {
        this.claimBillDate = claimBillDate;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public String getClientBillNo() {
        return clientBillNo;
    }

    public void setClientBillNo(String clientBillNo) {
        this.clientBillNo = clientBillNo;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public String getMemberYear() {
        return memberYear;
    }

    public void setMemberYear(String memberYear) {
        this.memberYear = memberYear;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getClaimAmountCurrency() {
        return claimAmountCurrency;
    }

    public void setClaimAmountCurrency(Long claimAmountCurrency) {
        this.claimAmountCurrency = claimAmountCurrency;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public Long getDeductibleCurrency() {
        return deductibleCurrency;
    }

    public void setDeductibleCurrency(Long deductibleCurrency) {
        this.deductibleCurrency = deductibleCurrency;
    }

    public BigDecimal getDeductibleCurrencyRate() {
        return deductibleCurrencyRate;
    }

    public void setDeductibleCurrencyRate(BigDecimal deductibleCurrencyRate) {
        this.deductibleCurrencyRate = deductibleCurrencyRate;
    }

    public BigDecimal getDeductibleDollar() {
        return deductibleDollar;
    }

    public void setDeductibleDollar(BigDecimal deductibleDollar) {
        this.deductibleDollar = deductibleDollar;
    }

    public Long getBillCurrency() {
        return billCurrency;
    }

    public void setBillCurrency(Long billCurrency) {
        this.billCurrency = billCurrency;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public BigDecimal getBillCurrencyRate() {
        return billCurrencyRate;
    }

    public void setBillCurrencyRate(BigDecimal billCurrencyRate) {
        this.billCurrencyRate = billCurrencyRate;
    }

    public BigDecimal getBillAmountDollar() {
        return billAmountDollar;
    }

    public void setBillAmountDollar(BigDecimal billAmountDollar) {
        this.billAmountDollar = billAmountDollar;
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

    public Integer getPrintNumber() {
        return printNumber;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
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

    public Long getClaimBillStatusId() {
        return claimBillStatusId;
    }

    public void setClaimBillStatusId(Long claimBillStatusId) {
        this.claimBillStatusId = claimBillStatusId;
    }

    public String getClaimBillStatusClaimBillStatusName() {
        return claimBillStatusClaimBillStatusName;
    }

    public void setClaimBillStatusClaimBillStatusName(String claimBillStatusClaimBillStatusName) {
        this.claimBillStatusClaimBillStatusName = claimBillStatusClaimBillStatusName;
    }

    public Long getClaimBillTypeId() {
        return claimBillTypeId;
    }

    public void setClaimBillTypeId(Long claimBillTypeId) {
        this.claimBillTypeId = claimBillTypeId;
    }

    public String getClaimBillTypeClaimBillTypeName() {
        return claimBillTypeClaimBillTypeName;
    }

    public void setClaimBillTypeClaimBillTypeName(String claimBillTypeClaimBillTypeName) {
        this.claimBillTypeClaimBillTypeName = claimBillTypeClaimBillTypeName;
    }

    public Long getClaimBillFinanceTypeId() {
        return claimBillFinanceTypeId;
    }

    public void setClaimBillFinanceTypeId(Long claimBillFinanceTypeId) {
        this.claimBillFinanceTypeId = claimBillFinanceTypeId;
    }

    public String getClaimBillFinanceTypeClaimBillFinanceTypeName() {
        return claimBillFinanceTypeClaimBillFinanceTypeName;
    }

    public void setClaimBillFinanceTypeClaimBillFinanceTypeName(String claimBillFinanceTypeClaimBillFinanceTypeName) {
        this.claimBillFinanceTypeClaimBillFinanceTypeName = claimBillFinanceTypeClaimBillFinanceTypeName;
    }

    public Long getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(Long creditorId) {
        this.creditorId = creditorId;
    }

    public String getCreditorCreditorName() {
        return creditorCreditorName;
    }

    public void setCreditorCreditorName(String creditorCreditorName) {
        this.creditorCreditorName = creditorCreditorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseClaimBillDTO caseClaimBillDTO = (CaseClaimBillDTO) o;
        if (caseClaimBillDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillDTO{" +
            "id=" + getId() +
            ", claimBillCode='" + getClaimBillCode() + "'" +
            ", claimBillDate='" + getClaimBillDate() + "'" +
            ", registerUserId=" + getRegisterUserId() +
            ", clientBillNo='" + getClientBillNo() + "'" +
            ", billDescription='" + getBillDescription() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", memberYear='" + getMemberYear() + "'" +
            ", numberId=" + getNumberId() +
            ", claimAmountCurrency=" + getClaimAmountCurrency() +
            ", claimAmount=" + getClaimAmount() +
            ", deductible=" + getDeductible() +
            ", deductibleCurrency=" + getDeductibleCurrency() +
            ", deductibleCurrencyRate=" + getDeductibleCurrencyRate() +
            ", deductibleDollar=" + getDeductibleDollar() +
            ", billCurrency=" + getBillCurrency() +
            ", billAmount=" + getBillAmount() +
            ", billCurrencyRate=" + getBillCurrencyRate() +
            ", billAmountDollar=" + getBillAmountDollar() +
            ", remark='" + getRemark() + "'" +
            ", isSigned='" + isIsSigned() + "'" +
            ", signUser=" + getSignUser() +
            ", signDate='" + getSignDate() + "'" +
            ", processId=" + getProcessId() +
            ", printNumber=" + getPrintNumber() +
            ", subcase=" + getSubcaseId() +
            ", subcase='" + getSubcaseSubcaseCode() + "'" +
            ", claimBillStatus=" + getClaimBillStatusId() +
            ", claimBillStatus='" + getClaimBillStatusClaimBillStatusName() + "'" +
            ", claimBillType=" + getClaimBillTypeId() +
            ", claimBillType='" + getClaimBillTypeClaimBillTypeName() + "'" +
            ", claimBillFinanceType=" + getClaimBillFinanceTypeId() +
            ", claimBillFinanceType='" + getClaimBillFinanceTypeClaimBillFinanceTypeName() + "'" +
            ", creditor=" + getCreditorId() +
            ", creditor='" + getCreditorCreditorName() + "'" +
            "}";
    }
}
