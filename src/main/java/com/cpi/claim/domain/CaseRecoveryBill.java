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

/**
 * A CaseRecoveryBill.
 */
@Entity
@Table(name = "case_recovery_bill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseRecoveryBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "is_write_off")
    private Boolean isWriteOff;

    @ManyToOne
    @JsonIgnoreProperties("caseRecoveryBills")
    private CaseRecovery caseRecovery;

    @ManyToOne
    @JsonIgnoreProperties("caseRecoveryBills")
    private CaseClaimBill caseClaimBill;

    @ManyToOne
    @JsonIgnoreProperties("caseRecoveryBills")
    private CaseClaimBill writeOffBill;

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

    public CaseRecoveryBill numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Boolean isIsWriteOff() {
        return isWriteOff;
    }

    public CaseRecoveryBill isWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
        return this;
    }

    public void setIsWriteOff(Boolean isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public CaseRecovery getCaseRecovery() {
        return caseRecovery;
    }

    public CaseRecoveryBill caseRecovery(CaseRecovery caseRecovery) {
        this.caseRecovery = caseRecovery;
        return this;
    }

    public void setCaseRecovery(CaseRecovery caseRecovery) {
        this.caseRecovery = caseRecovery;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public CaseRecoveryBill caseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
        return this;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
    }

    public CaseClaimBill getWriteOffBill() {
        return writeOffBill;
    }

    public CaseRecoveryBill writeOffBill(CaseClaimBill caseClaimBill) {
        this.writeOffBill = caseClaimBill;
        return this;
    }

    public void setWriteOffBill(CaseClaimBill caseClaimBill) {
        this.writeOffBill = caseClaimBill;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaseRecoveryBill)) {
            return false;
        }
        return id != null && id.equals(((CaseRecoveryBill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseRecoveryBill{" +
            "id=" + getId() +
            ", numberId=" + getNumberId() +
            ", isWriteOff='" + isIsWriteOff() + "'" +
            "}";
    }
}
