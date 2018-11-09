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

import com.cpi.claim.domain.CpiInsuranceType;

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
public class CaseCodeGenerateUtility {

    public static String createCaseCode(CpiInsuranceType cpiInsuranceType, String year, Integer numberId) {
        if (cpiInsuranceType.getId().equals(CpiInsuranceType.CPI_INSURANCE_PI)) {
            return createPICaseCode(year, numberId);
        } else if (cpiInsuranceType.getId().equals(CpiInsuranceType.CPI_INSURANCE_FDD)) {
            return createFDDCaseCode(year, numberId);
        } else if (cpiInsuranceType.getId().equals(CpiInsuranceType.CPI_INSURANCE_TCL)) {
            return createTCLCaseCode(year, numberId);
        } else {
            return createOtherCaseCode(year, numberId);
        }
    }


    private static String createFDDCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("F");
        NumberFormat formatter4 = new DecimalFormat("0000");
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createTCLCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("TCL");
        NumberFormat formatter4 = new DecimalFormat("0000");
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createPICaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        NumberFormat formatter4 = new DecimalFormat("0000");
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createOtherCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("OTHER");
        NumberFormat formatter4 = new DecimalFormat("0000");
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

}
