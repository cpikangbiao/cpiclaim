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
package com.cpi.claim.service.utility.generate;

import com.cpi.claim.domain.ClaimBillType;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/9
 * @since 1.0.0
 */
public class ClaimBillCodeGenerateUtility {

    /**
     *  理赔账单编码
     *  CLYYYYXXXNNNNN
     *  编码实例：
     *  CL2012PAY00111
     *
     *  说明：
     *  	CL   : 理赔账单
     *     YYYY ：（四位）案件发生年度
     *     XXX  ：（三位）账单类型代码
     *     NNNNN：（五位）账单本年度编号
     *
     * @param year 案件发生年度
     * @param claimBillType 账单类型
     * @param numberId 账单本年度编号
     * @return 理赔账单编码
     *
     */
    public static String createClaimBillCode(String year, ClaimBillType claimBillType, Integer numberId) {
        StringBuilder claimBillCode = new StringBuilder();
        NumberFormat formatter5     = new DecimalFormat("00000");
        String tag = "PAY";

        switch (claimBillType.getId().intValue()) {
            case ClaimBillType.CLAIM_BILL_TYPE_PAYMENT:
                tag = "PAY";
                break;
            case ClaimBillType.CLAIM_BILL_TYPE_CLAIMFEE:
                tag = "FEE";
                break;
            case ClaimBillType.CLAIM_BILL_TYPE_RECOVERY:
                tag = "RCV";
                break;
            case ClaimBillType.CLAIM_BILL_TYPE_RECANDPAY:
                tag = "REC";
                break;
            case ClaimBillType.CLAIM_BILL_TYPE_OTHERS:
                tag = "OTH";
                break;
            default:
                tag = "ERR";
                break;
        }

        claimBillCode.append("CL")
            .append(year)
            .append(tag)
            .append(formatter5.format(numberId));

        return claimBillCode.toString();
    }

}
