package com.cpi.claim.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CaseAssignLog.
 */
@Entity
@Table(name = "case_assign_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseAssignLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private String numberId;

    @Column(name = "assign_user")
    private String assignUser;

    @Column(name = "assign_time")
    private Instant assignTime;

    @Column(name = "assigned_user")
    private String assignedUser;

    @ManyToOne
    @JsonIgnoreProperties("caseAssignLogs")
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

    public CaseAssignLog numberId(String numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public String getAssignUser() {
        return assignUser;
    }

    public CaseAssignLog assignUser(String assignUser) {
        this.assignUser = assignUser;
        return this;
    }

    public void setAssignUser(String assignUser) {
        this.assignUser = assignUser;
    }

    public Instant getAssignTime() {
        return assignTime;
    }

    public CaseAssignLog assignTime(Instant assignTime) {
        this.assignTime = assignTime;
        return this;
    }

    public void setAssignTime(Instant assignTime) {
        this.assignTime = assignTime;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public CaseAssignLog assignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
        return this;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public VesselCase getVesselCase() {
        return vesselCase;
    }

    public CaseAssignLog vesselCase(VesselCase vesselCase) {
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
        if (!(o instanceof CaseAssignLog)) {
            return false;
        }
        return id != null && id.equals(((CaseAssignLog) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseAssignLog{" +
            "id=" + getId() +
            ", numberId='" + getNumberId() + "'" +
            ", assignUser='" + getAssignUser() + "'" +
            ", assignTime='" + getAssignTime() + "'" +
            ", assignedUser='" + getAssignedUser() + "'" +
            "}";
    }
}
