package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RiskGroup.
 */
@Entity
@Table(name = "risk_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RiskGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "risk_group_name", nullable = false)
    private String riskGroupName;

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

    public RiskGroup sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getRiskGroupName() {
        return riskGroupName;
    }

    public RiskGroup riskGroupName(String riskGroupName) {
        this.riskGroupName = riskGroupName;
        return this;
    }

    public void setRiskGroupName(String riskGroupName) {
        this.riskGroupName = riskGroupName;
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
        RiskGroup riskGroup = (RiskGroup) o;
        if (riskGroup.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskGroup.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskGroup{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", riskGroupName='" + getRiskGroupName() + "'" +
            "}";
    }
}
