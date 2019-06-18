package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CaseRecoveryBill.
 */
@Entity
@Table(name = "case_recovery_bill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseRecoveryBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "is_write_off")
    private Boolean isWriteOff;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CaseRecovery caseRecovery;

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

    public CaseRecoveryBill numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public CaseRecoveryBill isWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
        return this;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public CaseRecovery getCaseRecovery() {
        return caseRecovery;
    }

    public CaseRecoveryBill caseRecovery(CaseRecovery caseRecovery) {
        this.caseRecovery = caseRecovery;
        return this;
    }

    public void setCaseRecovery(CaseRecovery caseRecovery) {
        this.caseRecovery = caseRecovery;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CaseRecoveryBill caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
    }

    public CaseClaimBill getWriteOffBill() {
        return writeOffBill;
    }

    public CaseRecoveryBill writeOffBill(CaseClaimBill caseClaimBill) {
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
        CaseRecoveryBill caseRecoveryBill = (CaseRecoveryBill) o;
        if (caseRecoveryBill.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseRecoveryBill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseRecoveryBill{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            "}";
    }
}
