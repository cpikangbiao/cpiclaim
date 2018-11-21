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
package com.cpi.claim.service.utility.timeline;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.bean.timeline.TimeLineBean;
import com.cpi.claim.service.utility.ClaimToolUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/9
 * @since 1.0.0
 */

@Service
@Transactional
public class VesselCaseTimeLineGenerateUtility {

    private static final String VESSELCASE_TIMELINE_TYPE_CLAIM = "Claim";

    private static final String VESSELCASE_TIMELINE_TYPE_ESTIMATE = "Estimate";

    private static final String VESSELCASE_TIMELINE_TYPE_SUBCASE = "Sub Case";

    private static final String VESSELCASE_TIMELINE_TYPE_CASE = "Vessel Case";

    private static final String VESSELCASE_TIMELINE_TYPE_RECOVERY = "Recovery";

    private static final String VESSELCASE_TIMELINE_TYPE_3RD_RECOVERY = "3rd Recovery";

    private static final String VESSELCASE_TIMELINE_TYPE_GUARANTEE = "Guarantee";

    private static final String VESSELCASE_TIMELINE_TYPE_MEMBER_PAYMENT = "Member Payment";

    private static final String VESSELCASE_TIMELINE_TYPE_3RD_PAYMENT = "3rd Payment";

    private static final String VESSELCASE_TIMELINE_TYPE_FEE = "Fee";

    private static final String VESSELCASE_TIMELINE_TYPE_BILL = "Bill";

    @Autowired
    private VesselCaseRepository vesselCaseRepository;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    public List<TimeLineBean> createVesselCaseTimeLine(Long vesselCaseId) {
        List<TimeLineBean> timeLineBeans = new ArrayList<>();
        VesselCase vesselCase = vesselCaseRepository.getOne(vesselCaseId);
        StringBuilder incidenttemp = new StringBuilder();
        TimeLineBean timeLineBean = null;

        if (vesselCase != null) {
            incidenttemp.append("The Time of Create Case By ").append(vesselCase.getCreatedBy());
            timeLineBean = new TimeLineBean(
                vesselCase.getCreatedDate(),
                incidenttemp.toString(),
                VESSELCASE_TIMELINE_TYPE_CASE
            );
            timeLineBeans.add(timeLineBean);



            List<VesselSubCase> vesselSubCases = claimToolUtility.vesselSubCaseRepository.findAllByVesselCase(vesselCase);
            for (VesselSubCase vesselSubCase : vesselSubCases) {
                // for sub case
                incidenttemp.setLength(0);
                incidenttemp.append("The Time of Create Sub Case By ").append(vesselSubCase.getCreatedBy());
                timeLineBean = new TimeLineBean(
                    vesselSubCase.getCreatedDate(),
                    incidenttemp.toString(),
                    VESSELCASE_TIMELINE_TYPE_SUBCASE
                );
                timeLineBeans.add(timeLineBean);

                // for claim
                List<CaseClaim> caseClaims = claimToolUtility.caseClaimRepository.findAllBySubcase(vesselSubCase);
                for (CaseClaim caseClaim : caseClaims) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Claim By ")
                        .append(caseClaim.getCreatedBy()).append(" Amount(USD): ")
                        .append(caseClaim.getClaimCostDollar());
                    timeLineBean = new TimeLineBean(
                        caseClaim.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_CLAIM
                    );
                    timeLineBeans.add(timeLineBean);
                }

                // for estimate
                List<CaseEstimate> caseEstimates = claimToolUtility.caseEstimateRepository.findAllBySubcase(vesselSubCase);
                for (CaseEstimate caseEstimate : caseEstimates) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Estimate By ")
                        .append(caseEstimate.getCreatedBy()).append(" Entity Fee (USD): ")
                        .append(caseEstimate.getEstimateEntityFee())
                        .append(caseEstimate.getCreatedBy()).append(" Cost Fee (USD): ")
                        .append(caseEstimate.getEstimateCostFee());
                    timeLineBean = new TimeLineBean(
                        caseEstimate.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_ESTIMATE
                    );
                    timeLineBeans.add(timeLineBean);
                }

                // for Guarantee
                List<CaseGuarantee> caseGuarantees = claimToolUtility.caseGuaranteeRepository.findAllBySubcase(vesselSubCase);
                for (CaseGuarantee caseGuarantee : caseGuarantees) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Estimate By ")
                        .append(caseGuarantee.getCreatedBy()).append(" Guarantee Amount (USD): ")
                        .append(caseGuarantee.getGuaranteeAmountDollar());
                    timeLineBean = new TimeLineBean(
                        caseGuarantee.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_GUARANTEE
                    );
                    timeLineBeans.add(timeLineBean);
                }

                // for Recovery
                RecoveryType recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_REINSURANCE);
                List<CaseRecovery> caseRecoveries = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
                for (CaseRecovery caseRecovery : caseRecoveries) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Recovery By ")
                        .append(caseRecovery.getCreatedBy()).append(" Recovery Amount (USD): ")
                        .append(caseRecovery.getAmountDollar());
                    timeLineBean = new TimeLineBean(
                        caseRecovery.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_RECOVERY
                    );
                    timeLineBeans.add(timeLineBean);
                }

                //第三方追偿
                recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_THIRDPART);
                List<CaseRecovery> caseThirdRecoveries = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
                for (CaseRecovery caseRecovery : caseThirdRecoveries) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Third Part Recovery By ")
                        .append(caseRecovery.getCreatedBy()).append(" Recovery Amount (USD): ")
                        .append(caseRecovery.getAmountDollar());
                    timeLineBean = new TimeLineBean(
                        caseRecovery.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_3RD_RECOVERY
                    );
                    timeLineBeans.add(timeLineBean);
                }

                //会员赔偿
                PaymentType paymentType = claimToolUtility.paymentTypeRepository.getOne(PaymentType.PAYMENTTYPE_MEMBER);
                List<CasePayment> memberPayments = claimToolUtility.casePaymentRepository.findAllBySubcaseAndPaymentType(vesselSubCase, paymentType);
                for (CasePayment memberPayment : memberPayments) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Member Payment By ")
                        .append(memberPayment.getCreatedBy()).append(" Payment Amount (USD): ")
                        .append(memberPayment.getAmount());
                    timeLineBean = new TimeLineBean(
                        memberPayment.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_MEMBER_PAYMENT
                    );
                    timeLineBeans.add(timeLineBean);
                }

                //第三方赔偿
                paymentType = claimToolUtility.paymentTypeRepository.getOne(PaymentType.PAYMENTTYPE_THIRDPART);
                List<CasePayment> thirdPayments = claimToolUtility.casePaymentRepository.findAllBySubcaseAndPaymentType(vesselSubCase, paymentType);
                for (CasePayment thirdPayment : thirdPayments) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Case Third Part Payment By ")
                        .append(thirdPayment.getCreatedBy()).append(" Payment Amount (USD): ")
                        .append(thirdPayment.getAmount());
                    timeLineBean = new TimeLineBean(
                        thirdPayment.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_3RD_PAYMENT
                    );
                    timeLineBeans.add(timeLineBean);
                }

                //费用
                List<CaseFee> caseFees = claimToolUtility.caseFeeRepository.findAllBySubcase(vesselSubCase);
                for (CaseFee caseFee : caseFees) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Fee By ")
                        .append(caseFee.getCreatedBy())
                        .append(" ")
                        .append(caseFee.getFeeType().getFeeTypeName())
                        .append(" Amount (USD): ")
                        .append(caseFee.getAmountDollar());
                    timeLineBean = new TimeLineBean(
                        caseFee.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_FEE
                    );
                    timeLineBeans.add(timeLineBean);
                }

                //bill
                List<CaseClaimBill> caseClaimBills = claimToolUtility.caseClaimBillRepository.findAllBySubcase(vesselSubCase);
                for (CaseClaimBill caseClaimBill : caseClaimBills) {
                    incidenttemp.setLength(0);
                    incidenttemp.append("The Time of Create Bill By ")
                        .append(caseClaimBill.getCreatedBy())
                        .append(" Amount (USD): ")
                        .append(caseClaimBill.getBillAmountDollar());
                    timeLineBean = new TimeLineBean(
                        caseClaimBill.getCreatedDate(),
                        incidenttemp.toString(),
                        VESSELCASE_TIMELINE_TYPE_BILL
                    );
                    timeLineBeans.add(timeLineBean);
                }
            }
        }

        Collections.sort(timeLineBeans);

        return timeLineBeans;
    }

}
