package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CaseSettlementMode.
 */
@Entity
@Table(name = "case_settlement_mode")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseSettlementMode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "settlement_mode_name", nullable = false)
    private String settlementModeName;

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

    public CaseSettlementMode sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getSettlementModeName() {
        return settlementModeName;
    }

    public CaseSettlementMode settlementModeName(String settlementModeName) {
        this.settlementModeName = settlementModeName;
        return this;
    }

    public void setSettlementModeName(String settlementModeName) {
        this.settlementModeName = settlementModeName;
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
        CaseSettlementMode caseSettlementMode = (CaseSettlementMode) o;
        if (caseSettlementMode.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseSettlementMode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseSettlementMode{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", settlementModeName='" + getSettlementModeName() + "'" +
            "}";
    }
}
