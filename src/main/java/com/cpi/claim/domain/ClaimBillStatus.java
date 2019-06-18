package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ClaimBillStatus.
 */
@Entity
@Table(name = "claim_bill_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ClaimBillStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "claim_bill_status_name", nullable = false)
    private String claimBillStatusName;

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

    public ClaimBillStatus sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getClaimBillStatusName() {
        return claimBillStatusName;
    }

    public ClaimBillStatus claimBillStatusName(String claimBillStatusName) {
        this.claimBillStatusName = claimBillStatusName;
        return this;
    }

    public void setClaimBillStatusName(String claimBillStatusName) {
        this.claimBillStatusName = claimBillStatusName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClaimBillStatus)) {
            return false;
        }
        return id != null && id.equals(((ClaimBillStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ClaimBillStatus{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillStatusName='" + getClaimBillStatusName() + "'" +
            "}";
    }
}
