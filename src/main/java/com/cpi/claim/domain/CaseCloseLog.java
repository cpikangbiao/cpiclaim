package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CaseCloseLog.
 */
@Entity
@Table(name = "case_close_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseCloseLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operate_user_id")
    private String operateUserId;

    @Column(name = "operate_date")
    private String operateDate;

    @Column(name = "operate_type")
    private String operateType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselCase vesselCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public CaseCloseLog operateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
        return this;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public CaseCloseLog operateDate(String operateDate) {
        this.operateDate = operateDate;
        return this;
    }

    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperateType() {
        return operateType;
    }

    public CaseCloseLog operateType(String operateType) {
        this.operateType = operateType;
        return this;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public VesselCase getVesselCase() {
        return vesselCase;
    }

    public CaseCloseLog vesselCase(VesselCase vesselCase) {
        this.vesselCase = vesselCase;
        return this;
    }

    public void setVesselCase(VesselCase vesselCase) {
        this.vesselCase = vesselCase;
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
        CaseCloseLog caseCloseLog = (CaseCloseLog) o;
        if (caseCloseLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseCloseLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseCloseLog{" +
            "id=" + getId() +
            ", operateUserId='" + getOperateUserId() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            ", operateType='" + getOperateType() + "'" +
            "}";
    }
}
