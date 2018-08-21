package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CpiInsuranceType.
 */
@Entity
@Table(name = "cpi_insurance_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CpiInsuranceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "cpi_insurance_type_name", nullable = false)
    private String cpiInsuranceTypeName;

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

    public CpiInsuranceType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getCpiInsuranceTypeName() {
        return cpiInsuranceTypeName;
    }

    public CpiInsuranceType cpiInsuranceTypeName(String cpiInsuranceTypeName) {
        this.cpiInsuranceTypeName = cpiInsuranceTypeName;
        return this;
    }

    public void setCpiInsuranceTypeName(String cpiInsuranceTypeName) {
        this.cpiInsuranceTypeName = cpiInsuranceTypeName;
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
        CpiInsuranceType cpiInsuranceType = (CpiInsuranceType) o;
        if (cpiInsuranceType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cpiInsuranceType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CpiInsuranceType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", cpiInsuranceTypeName='" + getCpiInsuranceTypeName() + "'" +
            "}";
    }
}
