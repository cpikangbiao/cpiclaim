package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CaseClaimBillPrintLog.
 */
@Entity
@Table(name = "case_claim_bill_print_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseClaimBillPrintLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operate_type")
    private String operateType;

    @Column(name = "operate_user")
    private String operateUser;

    @Column(name = "operate_date")
    private Instant operateDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JsonIgnoreProperties("")
    private CaseClaimBill caseClaimBill;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateType() {
        return operateType;
    }

    public CaseClaimBillPrintLog operateType(String operateType) {
        this.operateType = operateType;
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public CaseClaimBillPrintLog operateUser(String operateUser) {
        this.operateUser = operateUser;
        return this;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Instant getOperateDate() {
        return operateDate;
    }

    public CaseClaimBillPrintLog operateDate(Instant operateDate) {
        this.operateDate = operateDate;
        return this;
    }

    public void setOperateDate(Instant operateDate) {
        this.operateDate = operateDate;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CaseClaimBillPrintLog caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
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
        CaseClaimBillPrintLog caseClaimBillPrintLog = (CaseClaimBillPrintLog) o;
        if (caseClaimBillPrintLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillPrintLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillPrintLog{" +
            "id=" + getId() +
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            "}";
    }
}
