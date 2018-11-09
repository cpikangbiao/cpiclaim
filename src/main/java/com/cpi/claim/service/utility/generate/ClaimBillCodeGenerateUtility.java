/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CaseCodeGenerateUtility
 * Author:   admin
 * Date:     2018/11/9 9:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
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
