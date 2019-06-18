package com.cpi.claim.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CaseFeeBill.
 */
@Entity
@Table(name = "case_fee_bill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseFeeBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "is_write_off")
    private Boolean isWriteOff;

    @ManyToOne
    @JsonIgnoreProperties("caseFeeBills")
    private CaseFee caseFee;

    @ManyToOne
    @JsonIgnoreProperties("caseFeeBills")
    private CaseClaimBill caseClaimBill;

    @ManyToOne
    @JsonIgnoreProperties("caseFeeBills")
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

    public CaseFeeBill numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public CaseFeeBill isWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
        return this;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public CaseFee getCaseFee() {
        return caseFee;
    }

    public CaseFeeBill caseFee(CaseFee caseFee) {
        this.caseFee = caseFee;
        return this;
    }

    public void setCaseFee(CaseFee caseFee) {
        this.caseFee = caseFee;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CaseFeeBill caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
    }

    public CaseClaimBill getWriteOffBill() {
        return writeOffBill;
    }

    public CaseFeeBill writeOffBill(CaseClaimBill caseClaimBill) {
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
        if (!(o instanceof CaseFeeBill)) {
            return false;
        }
        return id != null && id.equals(((CaseFeeBill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseFeeBill{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            "}";
    }
}
