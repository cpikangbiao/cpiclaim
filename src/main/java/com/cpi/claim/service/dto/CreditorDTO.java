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
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.Creditor} entity.
 */
public class CreditorDTO implements Serializable {

    private Long id;

    private String creditorCode;

    private String creditorName;

    private String creditorAddress;

    private String portName;

    private String swiftCode;

    private String ibanCode;

    private String bankName;

    private String bankAddress;

    private String accountNo;

    private String corrBankName;

    private String corrBankAddress;

    private String corrBankName2;

    private String corrBankAddress2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditorCode() {
        return creditorCode;
    }

    public void setCreditorCode(String creditorCode) {
        this.creditorCode = creditorCode;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getIbanCode() {
        return ibanCode;
    }

    public void setIbanCode(String ibanCode) {
        this.ibanCode = ibanCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCorrBankName() {
        return corrBankName;
    }

    public void setCorrBankName(String corrBankName) {
        this.corrBankName = corrBankName;
    }

    public String getCorrBankAddress() {
        return corrBankAddress;
    }

    public void setCorrBankAddress(String corrBankAddress) {
        this.corrBankAddress = corrBankAddress;
    }

    public String getCorrBankName2() {
        return corrBankName2;
    }

    public void setCorrBankName2(String corrBankName2) {
        this.corrBankName2 = corrBankName2;
    }

    public String getCorrBankAddress2() {
        return corrBankAddress2;
    }

    public void setCorrBankAddress2(String corrBankAddress2) {
        this.corrBankAddress2 = corrBankAddress2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CreditorDTO creditorDTO = (CreditorDTO) o;
        if (creditorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), creditorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CreditorDTO{" +
            "id=" + getId() +
            ", creditorCode='" + getCreditorCode() + "'" +
            ", creditorName='" + getCreditorName() + "'" +
            ", creditorAddress='" + getCreditorAddress() + "'" +
            ", portName='" + getPortName() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            ", ibanCode='" + getIbanCode() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankAddress='" + getBankAddress() + "'" +
            ", accountNo='" + getAccountNo() + "'" +
            ", corrBankName='" + getCorrBankName() + "'" +
            ", corrBankAddress='" + getCorrBankAddress() + "'" +
            ", corrBankName2='" + getCorrBankName2() + "'" +
            ", corrBankAddress2='" + getCorrBankAddress2() + "'" +
            "}";
    }
}
