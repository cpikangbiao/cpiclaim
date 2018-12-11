/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A CaseClaimBillApprovalLog.
 */
@Entity
@Table(name = "case_claim_bill_approval_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseClaimBillApprovalLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "insert_time")
    private Instant insertTime;

    @Column(name = "approval_user")
    private String approvalUser;

    @Column(name = "approval_opinion")
    private String approvalOpinion;

    @Column(name = "approval_transition")
    private String approvalTransition;

    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("")
    private CaseClaimBill caseClaimBill;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessId() {
        return processId;
    }

    public CaseClaimBillApprovalLog processId(Long processId) {
        this.processId = processId;
        return this;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public CaseClaimBillApprovalLog insertTime(Instant insertTime) {
        this.insertTime = insertTime;
        return this;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public String getApprovalUser() {
        return approvalUser;
    }

    public CaseClaimBillApprovalLog approvalUser(String approvalUser) {
        this.approvalUser = approvalUser;
        return this;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public CaseClaimBillApprovalLog approvalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
        return this;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public String getApprovalTransition() {
        return approvalTransition;
    }

    public CaseClaimBillApprovalLog approvalTransition(String approvalTransition) {
        this.approvalTransition = approvalTransition;
        return this;
    }

    public void setApprovalTransition(String approvalTransition) {
        this.approvalTransition = approvalTransition;
    }

    public String getRemark() {
        return remark;
    }

    public CaseClaimBillApprovalLog remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CaseClaimBillApprovalLog caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
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
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = (CaseClaimBillApprovalLog) o;
        if (caseClaimBillApprovalLog.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillApprovalLog.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillApprovalLog{" +
            "id=" + getId() +
            ", processId=" + getProcessId() +
            ", insertTime='" + getInsertTime() + "'" +
            ", approvalUser='" + getApprovalUser() + "'" +
            ", approvalOpinion='" + getApprovalOpinion() + "'" +
            ", approvalTransition='" + getApprovalTransition() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
