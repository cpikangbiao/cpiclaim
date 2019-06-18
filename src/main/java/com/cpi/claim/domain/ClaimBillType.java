package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ClaimBillType.
 */
@Entity
@Table(name = "claim_bill_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimBillType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "claim_bill_type_name", nullable = false)
    private String claimBillTypeName;

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

    public ClaimBillType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getClaimBillTypeName() {
        return claimBillTypeName;
    }

    public ClaimBillType claimBillTypeName(String claimBillTypeName) {
        this.claimBillTypeName = claimBillTypeName;
        return this;
    }

    public void setClaimBillTypeName(String claimBillTypeName) {
        this.claimBillTypeName = claimBillTypeName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimBillType)) {
            return false;
        }
        return id != null && id.equals(((ClaimBillType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimBillType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillTypeName='" + getClaimBillTypeName() + "'" +
            "}";
    }
}
