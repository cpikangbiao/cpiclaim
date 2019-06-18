package com.cpi.claim.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A GuaranteeType.
 */
@Entity
@Table(name = "guarantee_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GuaranteeType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @NotNull
    @Column(name = "guarantee_type_name", nullable = false)
    private String guaranteeTypeName;

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

    public GuaranteeType sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getGuaranteeTypeName() {
        return guaranteeTypeName;
    }

    public GuaranteeType guaranteeTypeName(String guaranteeTypeName) {
        this.guaranteeTypeName = guaranteeTypeName;
        return this;
    }

    public void setGuaranteeTypeName(String guaranteeTypeName) {
        this.guaranteeTypeName = guaranteeTypeName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GuaranteeType)) {
            return false;
        }
        return id != null && id.equals(((GuaranteeType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GuaranteeType{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", guaranteeTypeName='" + getGuaranteeTypeName() + "'" +
            "}";
    }
}
