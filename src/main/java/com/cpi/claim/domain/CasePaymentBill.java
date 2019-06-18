package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A CasePaymentBill.
 */
@Entity
@Table(name = "case_payment_bill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CasePaymentBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "currency")
    private Long currency;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "is_write_off")
    private Boolean isWriteOff;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CaseClaimBill caseClaimBill;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CaseClaimBill writeOffBill;

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

    public CasePaymentBill numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getCurrency() {
        return currency;
    }

    public CasePaymentBill currency(Long currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CasePaymentBill amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public CasePaymentBill isWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
        return this;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CasePaymentBill subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CasePaymentBill caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
    }

    public CaseClaimBill getWriteOffBill() {
        return writeOffBill;
    }

    public CasePaymentBill writeOffBill(CaseClaimBill caseClaimBill) {
        this.writeOffBill = caseClaimBill;
        return this;
    }

    public void setWriteOffBill(CaseClaimBill caseClaimBill) {
        this.writeOffBill = caseClaimBill;
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
        CasePaymentBill casePaymentBill = (CasePaymentBill) o;
        if (casePaymentBill.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), casePaymentBill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CasePaymentBill{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", currency=" + getCurrency() +
            ", amount=" + getAmount() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            "}";
    }
}
