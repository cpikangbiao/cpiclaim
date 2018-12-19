/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-18 上午9:58
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
package com.cpi.claim.service.utility.bill.doc;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.ClaimBillFinanceType;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.utility.ClaimToolUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/10/29
 * @since 1.0.0
 */
@Service
@Transactional
public  class ClaimBillDocUtility {

    @Autowired
    private CaseClaimBillRepository caseClaimBillRepository;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    public ByteArrayOutputStream createClaimBillZipFile(List<CaseClaimBill> caseClaimBills) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        for (CaseClaimBill caseClaimBill : caseClaimBills) {
            if (caseClaimBill.isIsSigned()) {
                String filename = createClaimBillFileName(caseClaimBill.getId());
                ZipEntry zipEntry = new ZipEntry(filename);
                byte[] filebyte = createCaseClaimBillDocPDF(caseClaimBill.getId());
                zipEntry.setSize(filebyte.length);
                try {
                    zipOutputStream.putNextEntry(zipEntry);
                    zipOutputStream.write(filebyte);
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            zipOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;

    }

    public String createClaimBillFileName(Long caseClaimBillId) {
        CaseClaimBill caseClaimBill = caseClaimBillRepository.getOne(caseClaimBillId);
        StringBuilder caseClaimBillNameString = new StringBuilder();
        caseClaimBillNameString.append("CPI Claim Bill (")
            .append(caseClaimBill.getClaimBillCode()).append(")");


        caseClaimBillNameString.append(".pdf");

        return caseClaimBillNameString.toString();
    }

    public byte[] createCaseClaimBillDocPDF(Long caseClaimBillId) {
        CaseClaimBill caseClaimBill = caseClaimBillRepository.getOne(caseClaimBillId);
        ResponseEntity<byte[]> responseEntity  = new ResponseEntity(HttpStatus.OK);
        if (caseClaimBill != null) {
            Map<String, Object> parameter = new HashMap<String, Object>();
            String jasperFilePath = null;
            if (caseClaimBill.getClaimBillFinanceType().getId().equals(ClaimBillFinanceType.BILL_FINANCE_TYPE_CREDIT)) {
                jasperFilePath = "PaymentAdvice.jasper";
                parameter = ClaimBillParameterUtility.createCreditorParameter(caseClaimBill, claimToolUtility);

            } else {
                jasperFilePath = "DebitNote.jasper";
                parameter = ClaimBillParameterUtility.createDebitParameter(caseClaimBill, claimToolUtility);
            }

            responseEntity  = claimToolUtility.jasperReportUtility.processPDF(jasperFilePath, parameter);
        }

        return responseEntity.getBody();
    }

}
