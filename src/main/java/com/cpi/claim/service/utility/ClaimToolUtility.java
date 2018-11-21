/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: UWToolUtility
 * Author:   admin
 * Date:     2018/9/5 9:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cpi.claim.service.utility;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.CaseGuarantee;
import com.cpi.claim.domain.CasePayment;
import com.cpi.claim.repository.*;
import com.cpi.claim.repository.common.*;
import com.cpi.claim.repository.jasperreport.JasperReportUtility;
import com.cpi.claim.repository.uaa.UserRepository;
import com.cpi.claim.repository.workflow.WorkflowRepository;
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

}
