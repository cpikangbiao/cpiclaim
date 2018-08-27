package com.cpi.claim.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Creditor entity. This class is used in CreditorResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /creditors?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CreditorCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter creditorCode;

    private StringFilter creditorName;

    private StringFilter creditorAddress;

    private StringFilter portName;

    private StringFilter swiftCode;

    private StringFilter ibanCode;

    private StringFilter bankName;

    private StringFilter bankAddress;

    private StringFilter accountNo;

    private StringFilter corrBankName;

    private StringFilter corrBankAddress;

    private StringFilter corrBankName2;

    private StringFilter corrBankAddress2;

    public CreditorCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCreditorCode() {
        return creditorCode;
    }

    public void setCreditorCode(StringFilter creditorCode) {
        this.creditorCode = creditorCode;
    }

    public StringFilter getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(StringFilter creditorName) {
        this.creditorName = creditorName;
    }

    public StringFilter getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(StringFilter creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

    public StringFilter getPortName() {
        return portName;
    }

    public void setPortName(StringFilter portName) {
        this.portName = portName;
    }

    public StringFilter getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(StringFilter swiftCode) {
        this.swiftCode = swiftCode;
    }

    public StringFilter getIbanCode() {
        return ibanCode;
    }

    public void setIbanCode(StringFilter ibanCode) {
        this.ibanCode = ibanCode;
    }

    public StringFilter getBankName() {
        return bankName;
    }

    public void setBankName(StringFilter bankName) {
        this.bankName = bankName;
    }

    public StringFilter getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(StringFilter bankAddress) {
        this.bankAddress = bankAddress;
    }

    public StringFilter getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(StringFilter accountNo) {
        this.accountNo = accountNo;
    }

    public StringFilter getCorrBankName() {
        return corrBankName;
    }

    public void setCorrBankName(StringFilter corrBankName) {
        this.corrBankName = corrBankName;
    }

    public StringFilter getCorrBankAddress() {
        return corrBankAddress;
    }

    public void setCorrBankAddress(StringFilter corrBankAddress) {
        this.corrBankAddress = corrBankAddress;
    }

    public StringFilter getCorrBankName2() {
        return corrBankName2;
    }

    public void setCorrBankName2(StringFilter corrBankName2) {
        this.corrBankName2 = corrBankName2;
    }

    public StringFilter getCorrBankAddress2() {
        return corrBankAddress2;
    }

    public void setCorrBankAddress2(StringFilter corrBankAddress2) {
        this.corrBankAddress2 = corrBankAddress2;
    }

    @Override
    public String toString() {
        return "CreditorCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (creditorCode != null ? "creditorCode=" + creditorCode + ", " : "") +
                (creditorName != null ? "creditorName=" + creditorName + ", " : "") +
                (creditorAddress != null ? "creditorAddress=" + creditorAddress + ", " : "") +
                (portName != null ? "portName=" + portName + ", " : "") +
                (swiftCode != null ? "swiftCode=" + swiftCode + ", " : "") +
                (ibanCode != null ? "ibanCode=" + ibanCode + ", " : "") +
                (bankName != null ? "bankName=" + bankName + ", " : "") +
                (bankAddress != null ? "bankAddress=" + bankAddress + ", " : "") +
                (accountNo != null ? "accountNo=" + accountNo + ", " : "") +
                (corrBankName != null ? "corrBankName=" + corrBankName + ", " : "") +
                (corrBankAddress != null ? "corrBankAddress=" + corrBankAddress + ", " : "") +
                (corrBankName2 != null ? "corrBankName2=" + corrBankName2 + ", " : "") +
                (corrBankAddress2 != null ? "corrBankAddress2=" + corrBankAddress2 + ", " : "") +
            "}";
    }

}
