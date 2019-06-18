package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ClaimBillFinanceType.
 */
@Entity
@Table(name = "claim_bill_finance_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimBillFinanceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "claim_bill_finance_type_name", nullable = false)
    private String claimBillFinanceTypeName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public ClaimBillFinanceType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getClaimBillFinanceTypeName() {
        return claimBillFinanceTypeName;
    }

    public ClaimBillFinanceType claimBillFinanceTypeName(String claimBillFinanceTypeName) {
        this.claimBillFinanceTypeName = claimBillFinanceTypeName;
        return this;
    }

    public void setClaimBillFinanceTypeName(String claimBillFinanceTypeName) {
        this.claimBillFinanceTypeName = claimBillFinanceTypeName;
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
        ClaimBillFinanceType claimBillFinanceType = (ClaimBillFinanceType) o;
        if (claimBillFinanceType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimBillFinanceType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimBillFinanceType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillFinanceTypeName='" + getClaimBillFinanceTypeName() + "'" +
            "}";
    }
}
