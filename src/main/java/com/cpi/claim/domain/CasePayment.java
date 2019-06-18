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

/**
 * A CasePayment.
 */
@Entity
@Table(name = "case_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CasePayment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_no")
    private String clientNo;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "pay_cost_date")
    private Instant payCostDate;

    @Column(name = "currency")
    private Long currency;

    @Column(name = "currency_rate", precision = 21, scale = 2)
    private BigDecimal currencyRate;

    @Column(name = "pay_cost", precision = 21, scale = 2)
    private BigDecimal payCost;

    @Column(name = "pay_cost_dollar", precision = 21, scale = 2)
    private BigDecimal payCostDollar;

    @Column(name = "deduct", precision = 21, scale = 2)
    private BigDecimal deduct;

    @Column(name = "amount", precision = 21, scale = 2)

    private BigDecimal amount;

    @Column(name = "fee_create_user")
    private Long feeCreateUser;

    @Column(name = "fee_create_date")
    private Instant feeCreateDate;

    @Lob
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("casePayments")
    private PaymentType paymentType;

    @ManyToOne
    @JsonIgnoreProperties("casePayments")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("casePayments")
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

    public CasePayment clientNo(String clientNo) {
        this.clientNo = clientNo;
        return this;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CasePayment numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Instant getPayCostDate() {
        return payCostDate;
    }

    public CasePayment payCostDate(Instant payCostDate) {
        this.payCostDate = payCostDate;
        return this;
    }

    public void setPayCostDate(Instant payCostDate) {
        this.payCostDate = payCostDate;
    }

    public Long getCurrency() {
        return currency;
    }

    public CasePayment currency(Long currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public CasePayment currencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getPayCost() {
        return payCost;
    }

    public CasePayment payCost(BigDecimal payCost) {
        this.payCost = payCost;
        return this;
    }

    public void setPayCost(BigDecimal payCost) {
        this.payCost = payCost;
    }

    public BigDecimal getPayCostDollar() {
        return payCostDollar;
    }

    public CasePayment payCostDollar(BigDecimal payCostDollar) {
        this.payCostDollar = payCostDollar;
        return this;
    }

    public void setPayCostDollar(BigDecimal payCostDollar) {
        this.payCostDollar = payCostDollar;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public CasePayment deduct(BigDecimal deduct) {
        this.deduct = deduct;
        return this;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CasePayment amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getFeeCreateUser() {
        return feeCreateUser;
    }

    public CasePayment feeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
        return this;
    }

    public void setFeeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
    }

    public Instant getFeeCreateDate() {
        return feeCreateDate;
    }

    public CasePayment feeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
        return this;
    }

    public void setFeeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
    }

    public String getRemark() {
        return remark;
    }

    public CasePayment remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public CasePayment paymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CasePayment subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public CasePayment creditor(Creditor creditor) {
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
        if (!(o instanceof CasePayment)) {
            return false;
        }
        return id != null && id.equals(((CasePayment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CasePayment{" +
            "id=" + getId() +
            ", clientNo='" + getClientNo() + "'" +
            ", numberId=" + getNumberId() +
            ", payCostDate='" + getPayCostDate() + "'" +
            ", currency=" + getCurrency() +
            ", currencyRate=" + getCurrencyRate() +
            ", payCost=" + getPayCost() +
            ", payCostDollar=" + getPayCostDollar() +
            ", deduct=" + getDeduct() +
            ", amount=" + getAmount() +
            ", feeCreateUser=" + getFeeCreateUser() +
            ", feeCreateDate='" + getFeeCreateDate() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
