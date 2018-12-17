/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午2:50
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
package com.cpi.claim.service.bean.bill;

import com.cpi.claim.domain.CaseClaimBill;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/17
 * @since 1.0.0
 */
public class AbstractCaseClaimBillBase implements Serializable {

    //对应的账单
    protected CaseClaimBill caseClaimBill;

    //这个账单是否是对冲账单
    protected Boolean        isWriteOff;

    //对冲的是那个账单
    protected CaseClaimBill    writeOffCaseClaimBill;

    //这个账单是否已经被对冲（冲销）
    protected Boolean        isWritedOff;

    //这个账单是由那个账单冲销
    protected CaseClaimBill    writedOffCaseClaimBill;

    protected void init(CaseClaimBill caseClaimBill, Boolean isIsWriteOff,
                     CaseClaimBill writeOffCaseClaimBill, CaseClaimBill writedOffCaseClaimBill) {
        this.isWriteOff        = false;
        this.isWritedOff       = false;

        //这个账单是否是冲销账单，冲销那个账单
        this.caseClaimBill  = caseClaimBill;
        this.isWriteOff     = isIsWriteOff;
        if (isWriteOff.equals(true)) {
            this.writeOffCaseClaimBill      = writeOffCaseClaimBill;
        }

        //这个账单是否被冲销，由那个账单冲销
        if (writedOffCaseClaimBill != null) {
            this. writedOffCaseClaimBill = writedOffCaseClaimBill;
            this.isWritedOff = true;
        }

    }

    public AbstractCaseClaimBillBase() {
        this.caseClaimBill = null;
        this.isWriteOff = false;
        this.writeOffCaseClaimBill = null;
        this.isWritedOff = false;
        this.writedOffCaseClaimBill = null;
    }

    public CaseClaimBill getCaseClaimBill() {
        return caseClaimBill;
    }

    public void setCaseClaimBill(CaseClaimBill caseClaimBill) {
        this.caseClaimBill = caseClaimBill;
    }

    public Boolean getWriteOff() {
        return isWriteOff;
    }

    public void setWriteOff(Boolean writeOff) {
        isWriteOff = writeOff;
    }

    public CaseClaimBill getWriteOffCaseClaimBill() {
        return writeOffCaseClaimBill;
    }

    public void setWriteOffCaseClaimBill(CaseClaimBill writeOffCaseClaimBill) {
        this.writeOffCaseClaimBill = writeOffCaseClaimBill;
    }

    public Boolean getWritedOff() {
        return isWritedOff;
    }

    public void setWritedOff(Boolean writedOff) {
        isWritedOff = writedOff;
    }

    public CaseClaimBill getWritedOffCaseClaimBill() {
        return writedOffCaseClaimBill;
    }

    public void setWritedOffCaseClaimBill(CaseClaimBill writedOffCaseClaimBill) {
        this.writedOffCaseClaimBill = writedOffCaseClaimBill;
    }
}
