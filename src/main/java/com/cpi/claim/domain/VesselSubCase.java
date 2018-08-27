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
 * A VesselSubCase.
 */
@Entity
@Table(name = "vessel_sub_case")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VesselSubCase extends  AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "assigned_user_id")
    private Long assignedUserId;

    @Column(name = "insert_time")
    private Instant insertTime;

    @Column(name = "subcase_code")
    private String subcaseCode;

    @Column(name = "bl_no")
    private String blNo;

    @Column(name = "claimant")
    private String claimant;

    @Column(name = "claim_amount")
    private String claimAmount;

    @Column(name = "currency")
    private Long currency;

    @Column(name = "deductible", precision = 10, scale = 2)
    private BigDecimal deductible;

    @Column(name = "currency_rate")
    private Double currencyRate;

    @Column(name = "deduct_dollar", precision = 10, scale = 2)
    private BigDecimal deductDollar;

    @Lob
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselCase vesselCase;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Risk risk;

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

    public VesselSubCase numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getAssignedUserId() {
        return assignedUserId;
    }

    public VesselSubCase assignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
        return this;
    }

    public void setAssignedUserId(Long assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public VesselSubCase insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public String getSubcaseCode() {
        return subcaseCode;
    }

    public VesselSubCase subcaseCode(String subcaseCode) {
        this.subcaseCode = subcaseCode;
        return this;
    }

    public void setSubcaseCode(String subcaseCode) {
        this.subcaseCode = subcaseCode;
    }

    public String getBlNo() {
        return blNo;
    }

    public VesselSubCase blNo(String blNo) {
        this.blNo = blNo;
        return this;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }

    public String getClaimant() {
        return claimant;
    }

    public VesselSubCase claimant(String claimant) {
        this.claimant = claimant;
        return this;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getClaimAmount() {
        return claimAmount;
    }

    public VesselSubCase claimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
        return this;
    }

    public void setClaimAmount(String claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Long getCurrency() {
        return currency;
    }

    public VesselSubCase currency(Long currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public VesselSubCase deductible(BigDecimal deductible) {
        this.deductible = deductible;
        return this;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public Double getCurrencyRate() {
        return currencyRate;
    }

    public VesselSubCase currencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

    public void setCurrencyRate(Double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public BigDecimal getDeductDollar() {
        return deductDollar;
    }

    public VesselSubCase deductDollar(BigDecimal deductDollar) {
        this.deductDollar = deductDollar;
        return this;
    }

    public void setDeductDollar(BigDecimal deductDollar) {
        this.deductDollar = deductDollar;
    }

    public String getRemark() {
        return remark;
    }

    public VesselSubCase remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public VesselCase getVesselCase() {
        return vesselCase;
    }

    public VesselSubCase vesselCase(VesselCase vesselCase) {
        this.vesselCase = vesselCase;
        return this;
    }

    public void setVesselCase(VesselCase vesselCase) {
        this.vesselCase = vesselCase;
    }

    public Risk getRisk() {
        return risk;
    }

    public VesselSubCase risk(Risk risk) {
        this.risk = risk;
        return this;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
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
        VesselSubCase vesselSubCase = (VesselSubCase) o;
        if (vesselSubCase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vesselSubCase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VesselSubCase{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", assignedUserId=" + getAssignedUserId() +
            ", insertTime='" + getInsertTime() + "'" +
            ", subcaseCode='" + getSubcaseCode() + "'" +
            ", blNo='" + getBlNo() + "'" +
            ", claimant='" + getClaimant() + "'" +
            ", claimAmount='" + getClaimAmount() + "'" +
            ", currency=" + getCurrency() +
            ", deductible=" + getDeductible() +
            ", currencyRate=" + getCurrencyRate() +
            ", deductDollar=" + getDeductDollar() +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
