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
 * A CaseClaim.
 */
@Entity
@Table(name = "case_claim")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseClaim extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "claimer")
    private String claimer;

    @Column(name = "claim_date")
    private Instant claimDate;

    @Column(name = "bill_of_lading")
    private String billOfLading;

    @Column(name = "currency_id")
    private Long currencyId;

    @Column(name = "currency_rate", precision = 15, scale = 5)
    private BigDecimal currencyRate;

    @Column(name = "claim_cost", precision = 20, scale = 2)
    private BigDecimal claimCost;

    @Column(name = "claim_cost_dollar", precision = 20, scale = 2)
    private BigDecimal claimCostDollar;

    @Lob
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselSubCase subcase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseClaim numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public String getClaimer() {
        return claimer;
    }

    public CaseClaim claimer(String claimer) {
        this.claimer = claimer;
        return this;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public Instant getClaimDate() {
        return claimDate;
    }

    public CaseClaim claimDate(Instant claimDate) {
        this.claimDate = claimDate;
        return this;
    }

    public void setClaimDate(Instant claimDate) {
        this.claimDate = claimDate;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public CaseClaim billOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
        return this;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public CaseClaim currencyId(Long currencyId) {
        this.currencyId = currencyId;
        return this;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public BigDecimal getCurrencyRate() {
        return currencyRate;
    }

    public CaseClaim currencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

    public void setCurrencyRate(BigDecimal currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getClaimCost() {
        return claimCost;
    }

    public CaseClaim claimCost(BigDecimal claimCost) {
        this.claimCost = claimCost;
        return this;
    }

    public void setClaimCost(BigDecimal claimCost) {
        this.claimCost = claimCost;
    }

    public BigDecimal getClaimCostDollar() {
        return claimCostDollar;
    }

    public CaseClaim claimCostDollar(BigDecimal claimCostDollar) {
        this.claimCostDollar = claimCostDollar;
        return this;
    }

    public void setClaimCostDollar(BigDecimal claimCostDollar) {
        this.claimCostDollar = claimCostDollar;
    }

    public String getRemark() {
        return remark;
    }

    public CaseClaim remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseClaim subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
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
        CaseClaim caseClaim = (CaseClaim) o;
        if (caseClaim.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaim.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaim{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", claimer='" + getClaimer() + "'" +
            ", claimDate='" + getClaimDate() + "'" +
            ", billOfLading='" + getBillOfLading() + "'" +
            ", currencyId=" + getCurrencyId() +
            ", currencyRate=" + getCurrencyRate() +
            ", claimCost=" + getClaimCost() +
            ", claimCostDollar=" + getClaimCostDollar() +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
