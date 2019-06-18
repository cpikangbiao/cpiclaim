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

package com.cpi.claim.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.CaseClaimBillDeleteLog} entity.
 */
public class CaseClaimBillDeleteLogDTO implements Serializable {

    private Long id;

    private String claimBillCode;

    private String operateType;

    private String operateUser;

    private Instant operateDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimBillCode() {
        return claimBillCode;
    }

    public void setClaimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Instant getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Instant operateDate) {
        this.operateDate = operateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO = (CaseClaimBillDeleteLogDTO) o;
        if (caseClaimBillDeleteLogDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBillDeleteLogDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBillDeleteLogDTO{" +
            "id=" + getId() +
            ", claimBillCode='" + getClaimBillCode() + "'" +
            ", operateType='" + getOperateType() + "'" +
            ", operateUser='" + getOperateUser() + "'" +
            ", operateDate='" + getOperateDate() + "'" +
            "}";
    }
}
