package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CaseStatusType.
 */
@Entity
@Table(name = "case_status_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseStatusType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "case_status_name", nullable = false)
    private String caseStatusName;

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

    public CaseStatusType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getCaseStatusName() {
        return caseStatusName;
    }

    public CaseStatusType caseStatusName(String caseStatusName) {
        this.caseStatusName = caseStatusName;
        return this;
    }

    public void setCaseStatusName(String caseStatusName) {
        this.caseStatusName = caseStatusName;
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
        CaseStatusType caseStatusType = (CaseStatusType) o;
        if (caseStatusType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseStatusType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseStatusType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", caseStatusName='" + getCaseStatusName() + "'" +
            "}";
    }
}