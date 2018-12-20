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
package com.cpi.claim.service.utility;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.*;
import com.cpi.claim.repository.common.*;
import com.cpi.claim.repository.jasperreport.JasperReportUtility;
import com.cpi.claim.repository.uaa.UserRepository;
import com.cpi.claim.repository.uw.InsuredVesselRepository;
import com.cpi.claim.repository.uw.MemberRepository;
import com.cpi.claim.repository.workflow.WorkflowRepository;
import com.cpi.claim.service.bean.bill.CaseClaimBillFeeBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/9/5
 * @since 1.0.0
 */

@Component
public class ClaimToolUtility {

    @Autowired
    public VesselRepository vesselRepository;

    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public CurrencyRepository currencyRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public WorkflowRepository workflowRepository;

    @Autowired
    public PortRepository portRepository;

    @Autowired
    public JasperReportUtility jasperReportUtility;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public InsuredVesselRepository insuredVesselRepository;

    @Autowired
    public MemberRepository memberRepository;

    @Autowired
    public CompanyNameHistoryRepository companyNameHistoryRepository;

    @Autowired
    public VesselNameHistoryRepository vesselNameHistoryRepository;


    @Autowired
    public CurrencyRateRepository currencyRateRepository;


    @Autowired
    public CaseGuaranteeRepository caseGuaranteeRepository;

    @Autowired
    public CaseEstimateRepository caseEstimateRepository;

    @Autowired
    public CaseClaimRepository caseClaimRepository;

    @Autowired
    public RecoveryTypeRepository recoveryTypeRepository;

    @Autowired
    public CaseRecoveryRepository caseRecoveryRepository;

    @Autowired
    public CaseRecoveryBillRepository caseRecoveryBillRepository;

    @Autowired
    public CaseClaimBillRepository caseClaimBillRepository;

    @Autowired
    public PaymentTypeRepository paymentTypeRepository;

    @Autowired
    public  CasePaymentRepository casePaymentRepository;

    @Autowired
    public CasePaymentBillRepository casePaymentBillRepository;

    @Autowired
    public CaseFeeRepository caseFeeRepository;

    @Autowired
    public CaseFeeBillRepository caseFeeBillRepository;

    @Autowired
    public VesselSubCaseRepository vesselSubCaseRepository;

    @Autowired
    public VesselCaseRepository vesselCaseRepository;

    @Autowired
    public ClaimBillStatusRepository claimBillStatusRepository;

    @Autowired
    public ClaimBillTypeRepository claimBillTypeRepository;

    @Autowired
    public ClaimBillFinanceTypeRepository claimBillFinanceTypeRepository;


    @Autowired
    public CreditorRepository creditorRepository;

    @Autowired
    public CaseStatusTypeRepository caseStatusTypeRepository;

}
