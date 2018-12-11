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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A CaseRecovery.
 */
@Entity
@Table(name = "case_recovery")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseRecovery extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_no")
    private String clientNo;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "currency")
    private Long currency;

    @Column(name = "currency_rate", precision = 10, scale = 5)
    private BigDecimal currencyRate;

    @Column(name = "issue_date")
    private Instant issueDate;

    @Column(name = "issue_amount", precision = 20, scale = 2)
    private BigDecimal issueAmount;

    @Column(name = "received_date")
    private Instant receivedDate;

    @Column(name = "received_amount", precision = 20, scale = 2)
    private BigDecimal receivedAmount;

    @Column(name = "amount_dollar", precision = 20, scale = 2)
    private BigDecimal amountDollar;

    @Column(name = "register_date")
    private Instant registerDate;

    @Column(name = "register_user")
    private Long registerUser;

    @Lob
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RecoveryType recoveryType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Creditor creditor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNo() {
        return clientNo;
    }

    public CaseRecovery clientNo(String clientNo) {
        this.clientNo = clientNo;
        return this;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseRecovery numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getCurrency() {
        return currency;
    }

    public CaseRecovery currency(Long currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public CaseRecovery currencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public Instant getIssueDate() {
        return issueDate;
    }

    public CaseRecovery issueDate(Instant issueDate) {
        this.issueDate = issueDate;
        return this;
    }

    public void setIssueDate(Instant issueDate) {
        this.issueDate = issueDate;
    }

    public BigDecimal getIssueAmount() {
        return issueAmount;
    }

    public CaseRecovery issueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
        return this;
    }

    public void setIssueAmount(BigDecimal issueAmount) {
        this.issueAmount = issueAmount;
    }

    public Instant getReceivedDate() {
        return receivedDate;
    }

    public CaseRecovery receivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public void setReceivedDate(Instant receivedDate) {
        this.receivedDate = receivedDate;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public CaseRecovery receivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
        return this;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getAmountDollar() {
        return amountDollar;
    }

    public CaseRecovery amountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
        return this;
    }

    public void setAmountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
    }

    public Instant getRegisterDate() {
        return registerDate;
    }

    public CaseRecovery registerDate(Instant registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public void setRegisterDate(Instant registerDate) {
        this.registerDate = registerDate;
    }

    public Long getRegisterUser() {
        return registerUser;
    }

    public CaseRecovery registerUser(Long registerUser) {
        this.registerUser = registerUser;
        return this;
    }

    public void setRegisterUser(Long registerUser) {
        this.registerUser = registerUser;
    }

    public String getRemark() {
        return remark;
    }

    public CaseRecovery remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseRecovery subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public RecoveryType getRecoveryType() {
        return recoveryType;
    }

    public CaseRecovery recoveryType(RecoveryType recoveryType) {
        this.recoveryType = recoveryType;
        return this;
    }

    public void setRecoveryType(RecoveryType recoveryType) {
        this.recoveryType = recoveryType;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public CaseRecovery creditor(Creditor creditor) {
        this.creditor = creditor;
        return this;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
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
        CaseRecovery caseRecovery = (CaseRecovery) o;
        if (caseRecovery.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseRecovery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseRecovery{" +
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
            "}";
    }
}
