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

    private static final String NUMBER_PATTERN = "0000";

    public static String createCaseCode(Long cpiInsuranceTypeId, String year, Integer numberId) {
        if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_PI)) {
            return createPICaseCode(year, numberId);
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_FDD)) {
            return createFDDCaseCode(year, numberId);
        } else if (cpiInsuranceTypeId.equals(CpiInsuranceType.CPI_INSURANCE_TCL)) {
            return createTCLCaseCode(year, numberId);
        } else {
            return createOtherCaseCode(year, numberId);
        }
    }


    private static String createFDDCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("F");
        NumberFormat formatter4 = new DecimalFormat(NUMBER_PATTERN);
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createTCLCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("TCL");
        NumberFormat formatter4 = new DecimalFormat(NUMBER_PATTERN);
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createPICaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        NumberFormat formatter4 = new DecimalFormat(NUMBER_PATTERN);
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

    private static String createOtherCaseCode(String year, Integer numberId) {
        StringBuilder caseCode = new StringBuilder();
        caseCode.append("OTHER");
        NumberFormat formatter4 = new DecimalFormat(NUMBER_PATTERN);
        caseCode.append(year);
        caseCode.append(formatter4.format(numberId));
        return caseCode.toString();
    }

}
