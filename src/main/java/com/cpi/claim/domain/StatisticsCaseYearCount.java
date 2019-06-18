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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A StatisticsCaseYearCount.
 */
@Entity
@Table(name = "statistics_case_year_count")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StatisticsCaseYearCount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "case_year")
    private String caseYear;

    @Column(name = "insurance_type")
    private String insuranceType;

    @Column(name = "case_count")
    private Integer caseCount;

    @Column(name = "update_time")
    private Instant updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseYear() {
        return caseYear;
    }

    public StatisticsCaseYearCount caseYear(String caseYear) {
        this.caseYear = caseYear;
        return this;
    }

    public void setCaseYear(String caseYear) {
        this.caseYear = caseYear;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public StatisticsCaseYearCount insuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
        return this;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Integer getCaseCount() {
        return caseCount;
    }

    public StatisticsCaseYearCount caseCount(Integer caseCount) {
        this.caseCount = caseCount;
        return this;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public StatisticsCaseYearCount updateTime(Instant updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatisticsCaseYearCount)) {
            return false;
        }
        return id != null && id.equals(((StatisticsCaseYearCount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StatisticsCaseYearCount{" +
            "id=" + getId() +
            ", caseYear='" + getCaseYear() + "'" +
            ", insuranceType='" + getInsuranceType() + "'" +
            ", caseCount=" + getCaseCount() +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
