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
public class SubCaseCodeGenerateUtility {

    public static String createSubCaseCode(String caseCode, Integer numberId) {
        StringBuilder subCaseCode = new StringBuilder();
        subCaseCode.append(caseCode);
        NumberFormat formatter2 = new DecimalFormat("00");
        subCaseCode.append(formatter2.format(numberId));
        return subCaseCode.toString();
    }
}
