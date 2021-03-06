/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
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

package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Creditor.
 */
@Entity
@Table(name = "creditor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Creditor extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creditor_code")
    private String creditorCode;

    @Column(name = "creditor_name")
    private String creditorName;

    @Column(name = "creditor_address")
    private String creditorAddress;

    @Column(name = "port_name")
    private String portName;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "iban_code")
    private String ibanCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_address")
    private String bankAddress;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "corr_bank_name")
    private String corrBankName;

    @Column(name = "corr_bank_address")
    private String corrBankAddress;

    @Column(name = "corr_bank_name_2")
    private String corrBankName2;

    @Column(name = "corr_bank_address_2")
    private String corrBankAddress2;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditorCode() {
        return creditorCode;
    }

    public Creditor creditorCode(String creditorCode) {
        this.creditorCode = creditorCode;
        return this;
    }

    public void setCreditorCode(String creditorCode) {
        this.creditorCode = creditorCode;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public Creditor creditorName(String creditorName) {
        this.creditorName = creditorName;
        return this;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorAddress() {
        return creditorAddress;
    }

    public Creditor creditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
        return this;
    }

    public void setCreditorAddress(String creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

    public String getPortName() {
        return portName;
    }

    public Creditor portName(String portName) {
        this.portName = portName;
        return this;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public Creditor swiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public String getIbanCode() {
        return ibanCode;
    }

    public Creditor ibanCode(String ibanCode) {
        this.ibanCode = ibanCode;
        return this;
    }

    public void setIbanCode(String ibanCode) {
        this.ibanCode = ibanCode;
    }

    public String getBankName() {
        return bankName;
    }

    public Creditor bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public Creditor bankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
        return this;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public Creditor accountNo(String accountNo) {
        this.accountNo = accountNo;
        return this;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCorrBankName() {
        return corrBankName;
    }

    public Creditor corrBankName(String corrBankName) {
        this.corrBankName = corrBankName;
        return this;
    }

    public void setCorrBankName(String corrBankName) {
        this.corrBankName = corrBankName;
    }

    public String getCorrBankAddress() {
        return corrBankAddress;
    }

    public Creditor corrBankAddress(String corrBankAddress) {
        this.corrBankAddress = corrBankAddress;
        return this;
    }

    public void setCorrBankAddress(String corrBankAddress) {
        this.corrBankAddress = corrBankAddress;
    }

    public String getCorrBankName2() {
        return corrBankName2;
    }

    public Creditor corrBankName2(String corrBankName2) {
        this.corrBankName2 = corrBankName2;
        return this;
    }

    public void setCorrBankName2(String corrBankName2) {
        this.corrBankName2 = corrBankName2;
    }

    public String getCorrBankAddress2() {
        return corrBankAddress2;
    }

    public Creditor corrBankAddress2(String corrBankAddress2) {
        this.corrBankAddress2 = corrBankAddress2;
        return this;
    }

    public void setCorrBankAddress2(String corrBankAddress2) {
        this.corrBankAddress2 = corrBankAddress2;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Creditor)) {
            return false;
        }
        return id != null && id.equals(((Creditor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Creditor{" +
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
