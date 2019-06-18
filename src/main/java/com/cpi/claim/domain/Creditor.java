package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Creditor.
 */
@Entity
@Table(name = "creditor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Creditor implements Serializable {

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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Creditor creditor = (Creditor) o;
        if (creditor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), creditor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
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
