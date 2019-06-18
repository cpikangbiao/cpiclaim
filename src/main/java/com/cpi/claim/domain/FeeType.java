package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A FeeType.
 */
@Entity
@Table(name = "fee_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "fee_type_name", nullable = false)
    private String feeTypeName;

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

    public FeeType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public FeeType feeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
        return this;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FeeType)) {
            return false;
        }
        return id != null && id.equals(((FeeType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FeeType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", feeTypeName='" + getFeeTypeName() + "'" +
            "}";
    }
}
