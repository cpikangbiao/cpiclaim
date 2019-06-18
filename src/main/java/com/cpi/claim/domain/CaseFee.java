package com.cpi.claim.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A CaseFee.
 */
@Entity
@Table(name = "case_fee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseFee implements Serializable {

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

    @Column(name = "currency_rate", precision = 21, scale = 2)
    private BigDecimal currencyRate;

    @Column(name = "fee_cost_date")
    private Instant feeCostDate;

    @Column(name = "fee_cost", precision = 21, scale = 2)
    private BigDecimal feeCost;

    @Column(name = "fee_cost_dollar", precision = 21, scale = 2)
    private BigDecimal feeCostDollar;

    @Column(name = "deduct", precision = 21, scale = 2)
    private BigDecimal deduct;

    @Column(name = "deduct_currency")
    private Long deductCurrency;

    @Column(name = "deduct_currency_rate", precision = 21, scale = 2)
    private BigDecimal deductCurrencyRate;

    @Column(name = "deduct_amount", precision = 21, scale = 2)
    private BigDecimal deductAmount;

    @Column(name = "amount_dollar", precision = 21, scale = 2)
    private BigDecimal amountDollar;

    @Column(name = "fee_create_user")
    private Long feeCreateUser;

    @Column(name = "fee_create_date")
    private Instant feeCreateDate;

    @Lob
    @Column(name = "remark")
    private String remark;

    @Column(name = "is_signed")
    private Boolean isSigned;

    @Column(name = "sign_user")
    private Long signUser;

    @Column(name = "sign_date")
    private Long signDate;

    @Column(name = "process_id")
    private Long processId;

    @ManyToOne
    @JsonIgnoreProperties("caseFees")
    private FeeType feeType;

    @ManyToOne
    @JsonIgnoreProperties("caseFees")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("caseFees")
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

    public CaseFee clientNo(String clientNo) {
        this.clientNo = clientNo;
        return this;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseFee numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getCurrency() {
        return currency;
    }

    public CaseFee currency(Long currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public CaseFee currencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public Instant getFeeCostDate() {
        return feeCostDate;
    }

    public CaseFee feeCostDate(Instant feeCostDate) {
        this.feeCostDate = feeCostDate;
        return this;
    }

    public void setFeeCostDate(Instant feeCostDate) {
        this.feeCostDate = feeCostDate;
    }

    public BigDecimal getFeeCost() {
        return feeCost;
    }

    public CaseFee feeCost(BigDecimal feeCost) {
        this.feeCost = feeCost;
        return this;
    }

    public void setFeeCost(BigDecimal feeCost) {
        this.feeCost = feeCost;
    }

    public BigDecimal getFeeCostDollar() {
        return feeCostDollar;
    }

    public CaseFee feeCostDollar(BigDecimal feeCostDollar) {
        this.feeCostDollar = feeCostDollar;
        return this;
    }

    public void setFeeCostDollar(BigDecimal feeCostDollar) {
        this.feeCostDollar = feeCostDollar;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public CaseFee deduct(BigDecimal deduct) {
        this.deduct = deduct;
        return this;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public Long getDeductCurrency() {
        return deductCurrency;
    }

    public CaseFee deductCurrency(Long deductCurrency) {
        this.deductCurrency = deductCurrency;
        return this;
    }

    public void setDeductCurrency(Long deductCurrency) {
        this.deductCurrency = deductCurrency;
    }

    public BigDecimal getDeductCurrencyRate() {
        return deductCurrencyRate;
    }

    public CaseFee deductCurrencyRate(BigDecimal deductCurrencyRate) {
        this.deductCurrencyRate = deductCurrencyRate;
        return this;
    }

    public void setDeductCurrencyRate(BigDecimal deductCurrencyRate) {
        this.deductCurrencyRate = deductCurrencyRate;
    }

    public BigDecimal getDeductAmount() {
        return deductAmount;
    }

    public CaseFee deductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
        return this;
    }

    public void setDeductAmount(BigDecimal deductAmount) {
        this.deductAmount = deductAmount;
    }

    public BigDecimal getAmountDollar() {
        return amountDollar;
    }

    public CaseFee amountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
        return this;
    }

    public void setAmountDollar(BigDecimal amountDollar) {
        this.amountDollar = amountDollar;
    }

    public Long getFeeCreateUser() {
        return feeCreateUser;
    }

    public CaseFee feeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
        return this;
    }

    public void setFeeCreateUser(Long feeCreateUser) {
        this.feeCreateUser = feeCreateUser;
    }

    public Instant getFeeCreateDate() {
        return feeCreateDate;
    }

    public CaseFee feeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
        return this;
    }

    public void setFeeCreateDate(Instant feeCreateDate) {
        this.feeCreateDate = feeCreateDate;
    }

    public String getRemark() {
        return remark;
    }

    public CaseFee remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isIsSigned() {
        return isSigned;
    }

    public CaseFee isSigned(Boolean isSigned) {
        this.isSigned = isSigned;
        return this;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public Long getSignUser() {
        return signUser;
    }

    public CaseFee signUser(Long signUser) {
        this.signUser = signUser;
        return this;
    }

    public void setSignUser(Long signUser) {
        this.signUser = signUser;
    }

    public Long getSignDate() {
        return signDate;
    }

    public CaseFee signDate(Long signDate) {
        this.signDate = signDate;
        return this;
    }

    public void setSignDate(Long signDate) {
        this.signDate = signDate;
    }

    public Long getProcessId() {
        return processId;
    }

    public CaseFee processId(Long processId) {
        this.processId = processId;
        return this;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public FeeType getFeeType() {
        return feeType;
    }

    public CaseFee feeType(FeeType feeType) {
        this.feeType = feeType;
        return this;
    }

    public void setFeeType(FeeType feeType) {
        this.feeType = feeType;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseFee subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public CaseFee creditor(Creditor creditor) {
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
        if (!(o instanceof CaseFee)) {
            return false;
        }
        return id != null && id.equals(((CaseFee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseFee{" +
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
            "}";
    }
}
