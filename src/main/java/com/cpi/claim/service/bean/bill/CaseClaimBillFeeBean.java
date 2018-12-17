/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午2:49
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

import com.cpi.claim.domain.CaseFeeBill;
import com.cpi.claim.service.utility.ClaimToolUtility;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/17
 * @since 1.0.0
 */
public class CaseClaimBillFeeBean extends AbstractCaseClaimBillBase {

    private CaseFeeBill caseFeeBill;

    public void init(CaseFeeBill caseFeeBill, ClaimToolUtility claimToolUtility) {
        this.caseFeeBill = caseFeeBill;
        this.isWriteOff        = false;
        this.isWritedOff       = false;

        //这个账单是否是冲销账单，冲销那个账单
        this.caseClaimBill     = caseFeeBill.getCaseClaimBill();
        this.isWriteOff        = caseFeeBill.isIsWriteOff();
        if (this.isWriteOff.equals(true)) {
            this.writeOffCaseClaimBill      = caseFeeBill.getWriteOffBill();
        }

        //这个账单是否被冲销，由那个账单冲销
        CaseFeeBill caseFeeBill1 =  claimToolUtility.caseFeeBillRepository.findFirstByWriteOffBill(caseClaimBill);
        if (caseFeeBill1 != null) {
            this.writedOffCaseClaimBill = caseFeeBill1.getCaseClaimBill();
            if (this.writedOffCaseClaimBill != null) {
                this.isWritedOff = true;
            }
        }
    }

    public CaseClaimBillFeeBean() {
        super();
        this.caseFeeBill = null;
    }

    public CaseFeeBill getCaseFeeBill() {
        return caseFeeBill;
    }

    public void setCaseFeeBill(CaseFeeBill caseFeeBill) {
        this.caseFeeBill = caseFeeBill;
    }
}
