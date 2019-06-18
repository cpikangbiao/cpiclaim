package com.cpi.claim.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RecoveryType.
 */
@Entity
@Table(name = "recovery_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RecoveryType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "recovery_type_name", nullable = false)
    private String recoveryTypeName;

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

    public RecoveryType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getRecoveryTypeName() {
        return recoveryTypeName;
    }

    public RecoveryType recoveryTypeName(String recoveryTypeName) {
        this.recoveryTypeName = recoveryTypeName;
        return this;
    }

    public void setRecoveryTypeName(String recoveryTypeName) {
        this.recoveryTypeName = recoveryTypeName;
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
        RecoveryType recoveryType = (RecoveryType) o;
        if (recoveryType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recoveryType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecoveryType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", recoveryTypeName='" + getRecoveryTypeName() + "'" +
            "}";
    }
}
