package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CaseClaimBillDeleteLog.
 */
@Entity
@Table(name = "case_claim_bill_delete_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseClaimBillDeleteLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_bill_code")
    private String claimBillCode;

    @Column(name = "operate_type")
    private String operateType;

    @Column(name = "operate_user")
    private String operateUser;

    @Column(name = "operate_date")
    private Instant operateDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimBillCode() {
        return claimBillCode;
    }

    public CaseClaimBillDeleteLog claimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
        return this;
    }

    public void setClaimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public String getOperateType() {
        return operateType;
    }

    public CaseClaimBillDeleteLog operateType(String operateType) {
        this.operateType = operateType;
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public CaseClaimBillDeleteLog operateUser(String operateUser) {
        this.operateUser = operateUser;
        return this;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Instant getOperateDate() {
        return operateDate;
    }

    public CaseClaimBillDeleteLog operateDate(Instant operateDate) {
        this.operateDate = operateDate;
        return this;
    }

    public void setOperateDate(Instant operateDate) {
        this.operateDate = operateDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaseClaimBillDeleteLog)) {
            return false;
        }
        return id != null && id.equals(((CaseClaimBillDeleteLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseClaimBillDeleteLog{" +
            "id=" + getId() +
            ", claimBillCode='" + getClaimBillCode() + "'" +
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            "}";
    }
}
