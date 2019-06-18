/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
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
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A CaseEstimate.
 */
@Entity
@Table(name = "case_estimate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseEstimate extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "register_user_id")
    private Long registerUserId;

    @Column(name = "estimate_date")
    private Instant estimateDate;

    @Column(name = "estimate_entity_fee", precision = 21, scale = 2)
    private BigDecimal estimateEntityFee;

    @Column(name = "estimate_cost_fee", precision = 21, scale = 2)
    private BigDecimal estimateCostFee;

    @Lob
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    @JsonIgnoreProperties("caseEstimates")
    private VesselSubCase subcase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseEstimate numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public CaseEstimate registerUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
        return this;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public Instant getEstimateDate() {
        return estimateDate;
    }

    public CaseEstimate estimateDate(Instant estimateDate) {
        this.estimateDate = estimateDate;
        return this;
    }

    public void setEstimateDate(Instant estimateDate) {
        this.estimateDate = estimateDate;
    }

    public BigDecimal getEstimateEntityFee() {
        return estimateEntityFee;
    }

    public CaseEstimate estimateEntityFee(BigDecimal estimateEntityFee) {
        this.estimateEntityFee = estimateEntityFee;
        return this;
    }

    public void setEstimateEntityFee(BigDecimal estimateEntityFee) {
        this.estimateEntityFee = estimateEntityFee;
    }

    public BigDecimal getEstimateCostFee() {
        return estimateCostFee;
    }

    public CaseEstimate estimateCostFee(BigDecimal estimateCostFee) {
        this.estimateCostFee = estimateCostFee;
        return this;
    }

    public void setEstimateCostFee(BigDecimal estimateCostFee) {
        this.estimateCostFee = estimateCostFee;
    }

    public String getRemark() {
        return remark;
    }

    public CaseEstimate remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseEstimate subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaseEstimate)) {
            return false;
        }
        return id != null && id.equals(((CaseEstimate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseEstimate{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", registerUserId=" + getRegisterUserId() +
            ", estimateDate='" + getEstimateDate() + "'" +
            ", estimateEntityFee=" + getEstimateEntityFee() +
            ", estimateCostFee=" + getEstimateCostFee() +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
