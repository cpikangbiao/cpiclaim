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

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;





/**
 * Criteria class for the CasePaymentBill entity. This class is used in CasePaymentBillResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /case-payment-bills?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CasePaymentBillCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private IntegerFilter numberId;

    private LongFilter currency;

    private BigDecimalFilter amount;

    private BooleanFilter isWriteOff;

    private LongFilter subcaseId;

    private LongFilter caseClaimBillId;

    private LongFilter writeOffBillId;

    public CasePaymentBillCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNumberId() {
        return numberId;
    }

    public void setNumberId(IntegerFilter numberId) {
        this.numberId = numberId;
    }

    public LongFilter getCurrency() {
        return currency;
    }

    public void setCurrency(LongFilter currency) {
        this.currency = currency;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public BooleanFilter getIsWriteOff() {
        return isWriteOff;
    }

    public void setIsWriteOff(BooleanFilter isWriteOff) {
        this.isWriteOff = isWriteOff;
    }

    public LongFilter getSubcaseId() {
        return subcaseId;
    }

    public void setSubcaseId(LongFilter subcaseId) {
        this.subcaseId = subcaseId;
    }

    public LongFilter getCaseClaimBillId() {
        return caseClaimBillId;
    }

    public void setCaseClaimBillId(LongFilter caseClaimBillId) {
        this.caseClaimBillId = caseClaimBillId;
    }

    public LongFilter getWriteOffBillId() {
        return writeOffBillId;
    }

    public void setWriteOffBillId(LongFilter writeOffBillId) {
        this.writeOffBillId = writeOffBillId;
    }

    @Override
    public String toString() {
        return "CasePaymentBillCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (numberId != null ? "numberId=" + numberId + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (isWriteOff != null ? "isWriteOff=" + isWriteOff + ", " : "") +
                (subcaseId != null ? "subcaseId=" + subcaseId + ", " : "") +
                (caseClaimBillId != null ? "caseClaimBillId=" + caseClaimBillId + ", " : "") +
                (writeOffBillId != null ? "writeOffBillId=" + writeOffBillId + ", " : "") +
            "}";
    }

}
