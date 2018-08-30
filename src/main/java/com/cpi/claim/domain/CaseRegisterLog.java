package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CaseRegisterLog.
 */
@Entity
@Table(name = "case_register_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseRegisterLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private String numberId;

    @Column(name = "assign_user")
    private String assignUser;

    @Column(name = "assign_time")
    private String assignTime;

    @Column(name = "assigned_user")
    private String assignedUser;

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

    public String getNumberId() {
        return numberId;
    }

    public CaseRegisterLog numberId(String numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public CaseRegisterLog assignUser(String assignUser) {
        this.assignUser = assignUser;
        return this;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public CaseRegisterLog assignTime(String assignTime) {
        this.assignTime = assignTime;
        return this;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public CaseRegisterLog assignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
        return this;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public VesselCase getVesselCase() {
        return vesselCase;
    }

    public CaseRegisterLog vesselCase(VesselCase vesselCase) {
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
        CaseRegisterLog caseRegisterLog = (CaseRegisterLog) o;
        if (caseRegisterLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseRegisterLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseRegisterLog{" +
            "id=" + getId() +
            ", numberId='" + getNumberId() + "'" +
            ", assignUser='" + getAssignUser() + "'" +
            ", assignTime='" + getAssignTime() + "'" +
            ", assignedUser='" + getAssignedUser() + "'" +
            "}";
    }
}
