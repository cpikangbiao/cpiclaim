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
package com.cpi.claim.service.bean.vesselcase;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.utility.ClaimToolUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/21
 * @since 1.0.0
 */
public class VesselSubCaseOverviewBean implements Serializable {

    private VesselSubCase vesselSubCase;

    private String  vesselSubCaseCode;

    private String  risk;

    //担保
    private Integer guaranteeNumber;

    private BigDecimal guaranteeAmount;

    private Instant guaranteeLastDate;

    //索赔
    private Integer claimNumber;

    private BigDecimal  claimAmount;

    private Instant    claimLastDate;

    //评估
    private Integer estimateNumber;

    private BigDecimal  estimateAmount;

    private Instant    estimateLastDate;

    //分保赔偿
    private Integer recoveryNumber;

    private BigDecimal  recoveryAmount;

    private Instant    recoveryLastDate;

    private Integer recoveryBillNumber;

    private Integer recoveryBillSigned;

    private Integer recoveryBillNoSign;

    //第三方追偿
    private Integer thirdpartNumber;

    private BigDecimal  thirdpartAmount;

    private Instant thirdpartLastDate;

    private Integer thirdpartBillNumber;

    private Integer thirdpartBillSigned;

    private Integer thirdpartBillNoSign;

    //会员赔偿
    private Integer memberPaymentNumber;

    private BigDecimal  memberPaymentAmount;

    private Instant    memberPaymentLastDate;

    private Integer paymentBillNumber;

    private Integer paymentBillSigned;

    private Integer paymentBillNoSign;

    //第三方赔偿
    private Integer thirdpartPaymentNumber;

    private BigDecimal  thirdpartPaymentAmount;

    private Instant    thirdpartPaymentLastDate;


    //检验费
    private Integer surveytNumber;

    private BigDecimal  surveyAmount;

    private Instant    surveyLastDate;

    private Integer surveyBillNumber;

    private Integer surveyBillSigned;

    private Integer surveyBillNoSign;


    //律师费
    private Integer lawayerNumber;

    private BigDecimal  lawayerAmount;

    private Instant    lawayerLastDate;

    private Integer lawayerBillNumber;

    private Integer lawayerBillSigned;

    private Integer lawayerBillNoSign;

    //通代费
    private Integer correspondentNumber;

    private BigDecimal  correspondentAmount;

    private Instant    correspondentLastDate;

    private Integer correspondentBillNumber;

    private Integer correspondentBillSigned;

    private Integer correspondentBillNoSign;

    //其他费用
    private Integer otherNumber;

    private BigDecimal  otherAmount;

    private Instant    otherLastDate;

    private Integer otherBillNumber;

    private Integer otherBillSigned;

    private Integer otherBillNoSign;

    //花费总数
    private BigDecimal  totalCost;

    //赔偿总数
    private BigDecimal  totalPayment;

    private BigDecimal  netPayment;

    private BigDecimal  deductible;

    private BigDecimal  netCPIPayment;

    private BigDecimal  cpiTotal;


    public void init(VesselSubCase vesselSubCase, ClaimToolUtility claimToolUtility) {
        this.vesselSubCase = vesselSubCase;
        if (vesselSubCase.getSubcaseCode() != null)
            vesselSubCaseCode = vesselSubCase.getSubcaseCode();
        if (vesselSubCase.getRisk() != null )
            risk = vesselSubCase.getRisk().getRiskNameEnglish();

        deductible = vesselSubCase.getDeductDollar();

        //担保
        List<CaseGuarantee> caseGuarantees = claimToolUtility.caseGuaranteeRepository.findAllBySubcase(vesselSubCase);
        if (caseGuarantees.size() > 0) {
            guaranteeNumber       = caseGuarantees.size();
            for (CaseGuarantee guarantee : caseGuarantees) {
                if (guarantee.getGuaranteeAmountDollar() != null)
                    guaranteeAmount = guaranteeAmount.add(guarantee.getGuaranteeAmountDollar());
                if (guarantee.getArrestDate() != null)
                    guaranteeLastDate = guarantee.getArrestDate();
            }
        }

        //评估
        List<CaseEstimate> caseEstimates = claimToolUtility.caseEstimateRepository.findAllBySubcase(vesselSubCase);
        if (caseEstimates.size() > 0) {
            estimateNumber       = caseEstimates.size();
            CaseEstimate lastCaseEstimate = claimToolUtility.caseEstimateRepository.findFirstBySubcaseOrderByEstimateDateDesc(vesselSubCase);
            if (lastCaseEstimate.getEstimateCostFee() != null)
                estimateAmount = estimateAmount.add(lastCaseEstimate.getEstimateCostFee());
            if (lastCaseEstimate.getEstimateEntityFee() != null)
                estimateAmount = estimateAmount.add(lastCaseEstimate.getEstimateEntityFee());
            if (lastCaseEstimate.getEstimateDate() != null)
                estimateLastDate = lastCaseEstimate.getEstimateDate();
        }

        //索赔
        List<CaseClaim> caseClaims = claimToolUtility.caseClaimRepository.findAllBySubcase(vesselSubCase);
        if (caseClaims.size() > 0) {
            claimNumber       = caseClaims.size();
            for (CaseClaim caseClaim : caseClaims) {
                if (caseClaim.getClaimCostDollar() != null)
                    claimAmount = claimAmount.add(caseClaim.getClaimCostDollar());
                if (caseClaim.getClaimDate() != null)
                    claimLastDate = caseClaim.getClaimDate();
            }
        }

        //再保追偿
        RecoveryType recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_REINSURANCE);
        List<CaseRecovery> caseRecoveries = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
        if (caseRecoveries.size() > 0) {
            recoveryNumber       = caseRecoveries.size();
            for (CaseRecovery caseRecovery : caseRecoveries) {
                if (caseRecovery.getAmountDollar() != null)
                    recoveryAmount = recoveryAmount.add(caseRecovery.getAmountDollar());
                if (caseRecovery.getReceivedDate() != null)
                    recoveryLastDate = caseRecovery.getReceivedDate();

                List<CaseRecoveryBill> caseRecoveryBills = claimToolUtility.caseRecoveryBillRepository.findAllByCaseRecovery(caseRecovery);
                for (CaseRecoveryBill caseRecoveryBill : caseRecoveryBills) {
                    recoveryBillNumber          += 1;
                    if (caseRecoveryBill.getCaseClaimBill().isIsSigned().equals(true))
                        recoveryBillSigned      += 1;
                    else
                        recoveryBillNoSign      += 1;
                }
            }
        }

        //第三方追偿
        recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_THIRDPART);
        List<CaseRecovery> caseThirdRecoveries = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
        if (caseThirdRecoveries.size() > 0) {
            thirdpartNumber       = caseThirdRecoveries.size();
            for (CaseRecovery caseRecovery : caseThirdRecoveries) {
                if (caseRecovery.getAmountDollar() != null)
                    thirdpartAmount = thirdpartAmount.add(caseRecovery.getAmountDollar());
                if (caseRecovery.getReceivedDate() != null)
                    thirdpartLastDate = caseRecovery.getReceivedDate();

                List<CaseRecoveryBill> caseRecoveryBills = claimToolUtility.caseRecoveryBillRepository.findAllByCaseRecovery(caseRecovery);
                for (CaseRecoveryBill caseRecoveryBill : caseRecoveryBills) {
                    thirdpartBillNumber          += 1;
                    if (caseRecoveryBill.getCaseClaimBill().isIsSigned())
                        thirdpartBillSigned      += 1;
                    else
                        thirdpartBillNoSign      += 1;
                }

            }
        }

        //会员赔偿
        PaymentType paymentType = claimToolUtility.paymentTypeRepository.getOne(PaymentType.PAYMENTTYPE_MEMBER);
        List<CasePayment> memberPayments = claimToolUtility.casePaymentRepository.findAllBySubcaseAndPaymentType(vesselSubCase, paymentType);
        if (memberPayments.size() > 0) {
            memberPaymentNumber       = memberPayments.size();
            for (CasePayment memberPayment : memberPayments) {
                if (memberPayment.getAmount() != null)
                    memberPaymentAmount = memberPaymentAmount.add(memberPayment.getAmount());
            }
        }

        List<CasePaymentBill> casePaymentBills = claimToolUtility.casePaymentBillRepository.findAllBySubcase(vesselSubCase);
        for (CasePaymentBill casePaymentBill : casePaymentBills) {
            if (casePaymentBill.getCaseClaimBill().getClaimBillDate() != null)
                memberPaymentLastDate = casePaymentBill.getCaseClaimBill().getClaimBillDate();
            paymentBillNumber          += 1;
            if (casePaymentBill.getCaseClaimBill().isIsSigned())
                paymentBillSigned      += 1;
            else
                paymentBillNoSign      += 1;
        }


        //第三方赔偿
        paymentType = claimToolUtility.paymentTypeRepository.getOne(PaymentType.PAYMENTTYPE_THIRDPART);
        List<CasePayment> thirdPayments = claimToolUtility.casePaymentRepository.findAllBySubcaseAndPaymentType(vesselSubCase, paymentType);
        if (thirdPayments.size() > 0) {
            thirdpartPaymentNumber       = thirdPayments.size();
            for (CasePayment thirdPayment : thirdPayments) {
                if (thirdPayment.getAmount() != null)
                    thirdpartPaymentAmount  = thirdpartPaymentAmount.add(thirdPayment.getAmount());
                if (thirdPayment.getFeeCreateDate() != null)
                    thirdpartPaymentLastDate = thirdPayment.getFeeCreateDate();
            }
        }


        //费用
        List<CaseFeeBill> caseFeeBills = null;
        List<CaseFee> caseFees = claimToolUtility.caseFeeRepository.findAllBySubcase(vesselSubCase);
        for (CaseFee caseFee : caseFees) {
            switch (caseFee.getFeeType().getId().intValue()) {
                case FeeType.CLAIMTYPE_SURVEYOR :
                    surveytNumber         += 1;
                    surveyAmount = surveyAmount.add(caseFee.getAmountDollar());

                    caseFeeBills = claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
                    for (CaseFeeBill claimFeeBill : caseFeeBills) {
                        surveyBillNumber          += 1;
                        if (claimFeeBill.getCaseClaimBill().isIsSigned())
                            surveyBillSigned      += 1;
                        else
                            surveyBillNoSign      += 1;
                        surveyLastDate        = claimFeeBill.getCaseClaimBill().getClaimBillDate();
                    }

                    break;
//				case FeeType.CLAIMTYPE_GUARANTEE :
//					break;
                case FeeType.CLAIMTYPE_CORRESPONDENT :
                    correspondentNumber   += 1;
                    correspondentAmount = correspondentAmount.add(caseFee.getAmountDollar());

                    caseFeeBills =claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
                    for (CaseFeeBill claimFeeBill : caseFeeBills) {
                        correspondentBillNumber          += 1;
                        if (claimFeeBill.getCaseClaimBill().isIsSigned())
                            correspondentBillSigned      += 1;
                        else
                            correspondentBillNoSign      += 1;
                        correspondentLastDate = claimFeeBill.getCaseClaimBill().getClaimBillDate();
                    }

                    break;
                case FeeType.CLAIMTYPE_LAWYER :
                    lawayerNumber         += 1;
                    lawayerAmount = lawayerAmount        .add(caseFee.getAmountDollar());

                    caseFeeBills =claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
                    for (CaseFeeBill claimFeeBill : caseFeeBills) {
                        lawayerBillNumber          += 1;
                        if (claimFeeBill.getCaseClaimBill().isIsSigned())
                            lawayerBillSigned      += 1;
                        else
                            lawayerBillNoSign      += 1;
                        lawayerLastDate = claimFeeBill.getCaseClaimBill().getClaimBillDate();
                    }

                    break;
                case FeeType.CLAIMTYPE_OTHER:
                    otherNumber           += 1;
                    otherAmount = otherAmount.add(caseFee.getAmountDollar());
                    otherLastDate         = caseFee.getFeeCostDate();

                    caseFeeBills =claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
                    for (CaseFeeBill claimFeeBill : caseFeeBills) {
                        otherBillNumber          += 1;
                        if (claimFeeBill.getCaseClaimBill().isIsSigned())
                            otherBillSigned      += 1;
                        else
                            otherBillNoSign      += 1;
                        lawayerLastDate = claimFeeBill.getCaseClaimBill().getClaimBillDate();
                    }
                    break;
                default:
                    otherNumber          += 1;
                    otherAmount = otherAmount.add(caseFee.getAmountDollar());

                    caseFeeBills =claimToolUtility.caseFeeBillRepository.findAllByCaseFee(caseFee);
                    for (CaseFeeBill claimFeeBill : caseFeeBills) {
                        otherBillNumber          += 1;
                        if (claimFeeBill.getCaseClaimBill().isIsSigned())
                            otherBillSigned      += 1;
                        else
                            otherBillNoSign      += 1;
                        otherLastDate = claimFeeBill.getCaseClaimBill().getClaimBillDate();
                    }
                    break;
            }
            totalCost = totalCost.add(caseFee.getAmountDollar());
        }

        //total payment = total cost + member payment + thirdpartPaymentAmount - third party recovery
        totalPayment = totalPayment.add(thirdpartPaymentAmount).add(memberPaymentAmount).add(totalCost);
        totalPayment = totalPayment.subtract(thirdpartAmount);
        netPayment = totalPayment.subtract(recoveryAmount);
        if(thirdpartPaymentAmount.add(memberPaymentAmount).compareTo(new BigDecimal(0.0)) > 0) {
            netCPIPayment = netPayment.subtract(deductible);
            cpiTotal   =  totalPayment.subtract(deductible);
        } else {
            netCPIPayment = netPayment;
            cpiTotal      = totalPayment;
        }
    }

    public VesselSubCaseOverviewBean() {
        this.vesselSubCase = null;
        this.vesselSubCaseCode = null;
        this.risk = null;
        this.guaranteeNumber = 0;
        this.guaranteeAmount =  new BigDecimal(0);
        this.guaranteeLastDate = null;
        this.claimNumber = 0;
        this.claimAmount =  new BigDecimal(0);
        this.claimLastDate = null;
        this.estimateNumber = 0;
        this.estimateAmount =  new BigDecimal(0);
        this.estimateLastDate = null;
        this.recoveryNumber = 0;
        this.recoveryAmount =  new BigDecimal(0);
        this.recoveryLastDate = null;
        this.recoveryBillNumber = 0;
        this.recoveryBillSigned = 0;
        this.recoveryBillNoSign = 0;
        this.thirdpartNumber = 0;
        this.thirdpartAmount =  new BigDecimal(0);
        this.thirdpartLastDate = null;
        this.thirdpartBillNumber = 0;
        this.thirdpartBillSigned = 0;
        this.thirdpartBillNoSign = 0;
        this.memberPaymentNumber = 0;
        this.memberPaymentAmount = new BigDecimal(0);
        this.memberPaymentLastDate = null;
        this.paymentBillNumber = 0;
        this.paymentBillSigned = 0;
        this.paymentBillNoSign = 0;
        this.thirdpartPaymentNumber = 0;
        this.thirdpartPaymentAmount =  new BigDecimal(0);
        this.thirdpartPaymentLastDate = null;
        this.surveytNumber = 0;
        this.surveyAmount =  new BigDecimal(0);
        this.surveyLastDate = null;
        this.surveyBillNumber = 0;
        this.surveyBillSigned = 0;
        this.surveyBillNoSign = 0;
        this.lawayerNumber = 0;
        this.lawayerAmount =  new BigDecimal(0);
        this.lawayerLastDate = null;
        this.lawayerBillNumber = 0;
        this.lawayerBillSigned = 0;
        this.lawayerBillNoSign = 0;
        this.correspondentNumber = 0;
        this.correspondentAmount =  new BigDecimal(0);
        this.correspondentLastDate = null;
        this.correspondentBillNumber = 0;
        this.correspondentBillSigned = 0;
        this.correspondentBillNoSign = 0000;
        this.otherNumber = 0;
        this.otherAmount =  new BigDecimal(0);
        this.otherLastDate = null;
        this.otherBillNumber = 0;
        this.otherBillSigned = 0;
        this.otherBillNoSign = 0;
        this.totalCost =  new BigDecimal(0);
        this.totalPayment =  new BigDecimal(0);
        this.netPayment =  new BigDecimal(0);
        this.deductible =  new BigDecimal(0);
        this.netCPIPayment =  new BigDecimal(0);
        this.cpiTotal =  new BigDecimal(0);
    }

    public VesselSubCase getVesselSubCase() {
        return vesselSubCase;
    }

    public void setVesselSubCase(VesselSubCase vesselSubCase) {
        this.vesselSubCase = vesselSubCase;
    }

    public String getVesselSubCaseCode() {
        return vesselSubCaseCode;
    }

    public void setVesselSubCaseCode(String vesselSubCaseCode) {
        this.vesselSubCaseCode = vesselSubCaseCode;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Integer getGuaranteeNumber() {
        return guaranteeNumber;
    }

    public void setGuaranteeNumber(Integer guaranteeNumber) {
        this.guaranteeNumber = guaranteeNumber;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public Instant getGuaranteeLastDate() {
        return guaranteeLastDate;
    }

    public void setGuaranteeLastDate(Instant guaranteeLastDate) {
        this.guaranteeLastDate = guaranteeLastDate;
    }

    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Integer claimNumber) {
        this.claimNumber = claimNumber;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Instant getClaimLastDate() {
        return claimLastDate;
    }

    public void setClaimLastDate(Instant claimLastDate) {
        this.claimLastDate = claimLastDate;
    }

    public Integer getEstimateNumber() {
        return estimateNumber;
    }

    public void setEstimateNumber(Integer estimateNumber) {
        this.estimateNumber = estimateNumber;
    }

    public BigDecimal getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(BigDecimal estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public Instant getEstimateLastDate() {
        return estimateLastDate;
    }

    public void setEstimateLastDate(Instant estimateLastDate) {
        this.estimateLastDate = estimateLastDate;
    }

    public Integer getRecoveryNumber() {
        return recoveryNumber;
    }

    public void setRecoveryNumber(Integer recoveryNumber) {
        this.recoveryNumber = recoveryNumber;
    }

    public BigDecimal getRecoveryAmount() {
        return recoveryAmount;
    }

    public void setRecoveryAmount(BigDecimal recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }

    public Instant getRecoveryLastDate() {
        return recoveryLastDate;
    }

    public void setRecoveryLastDate(Instant recoveryLastDate) {
        this.recoveryLastDate = recoveryLastDate;
    }

    public Integer getRecoveryBillNumber() {
        return recoveryBillNumber;
    }

    public void setRecoveryBillNumber(Integer recoveryBillNumber) {
        this.recoveryBillNumber = recoveryBillNumber;
    }

    public Integer getRecoveryBillSigned() {
        return recoveryBillSigned;
    }

    public void setRecoveryBillSigned(Integer recoveryBillSigned) {
        this.recoveryBillSigned = recoveryBillSigned;
    }

    public Integer getRecoveryBillNoSign() {
        return recoveryBillNoSign;
    }

    public void setRecoveryBillNoSign(Integer recoveryBillNoSign) {
        this.recoveryBillNoSign = recoveryBillNoSign;
    }

    public Integer getThirdpartNumber() {
        return thirdpartNumber;
    }

    public void setThirdpartNumber(Integer thirdpartNumber) {
        this.thirdpartNumber = thirdpartNumber;
    }

    public BigDecimal getThirdpartAmount() {
        return thirdpartAmount;
    }

    public void setThirdpartAmount(BigDecimal thirdpartAmount) {
        this.thirdpartAmount = thirdpartAmount;
    }

    public Instant getThirdpartLastDate() {
        return thirdpartLastDate;
    }

    public void setThirdpartLastDate(Instant thirdpartLastDate) {
        this.thirdpartLastDate = thirdpartLastDate;
    }

    public Integer getThirdpartBillNumber() {
        return thirdpartBillNumber;
    }

    public void setThirdpartBillNumber(Integer thirdpartBillNumber) {
        this.thirdpartBillNumber = thirdpartBillNumber;
    }

    public Integer getThirdpartBillSigned() {
        return thirdpartBillSigned;
    }

    public void setThirdpartBillSigned(Integer thirdpartBillSigned) {
        this.thirdpartBillSigned = thirdpartBillSigned;
    }

    public Integer getThirdpartBillNoSign() {
        return thirdpartBillNoSign;
    }

    public void setThirdpartBillNoSign(Integer thirdpartBillNoSign) {
        this.thirdpartBillNoSign = thirdpartBillNoSign;
    }

    public Integer getMemberPaymentNumber() {
        return memberPaymentNumber;
    }

    public void setMemberPaymentNumber(Integer memberPaymentNumber) {
        this.memberPaymentNumber = memberPaymentNumber;
    }

    public BigDecimal getMemberPaymentAmount() {
        return memberPaymentAmount;
    }

    public void setMemberPaymentAmount(BigDecimal memberPaymentAmount) {
        this.memberPaymentAmount = memberPaymentAmount;
    }

    public Instant getMemberPaymentLastDate() {
        return memberPaymentLastDate;
    }

    public void setMemberPaymentLastDate(Instant memberPaymentLastDate) {
        this.memberPaymentLastDate = memberPaymentLastDate;
    }

    public Integer getPaymentBillNumber() {
        return paymentBillNumber;
    }

    public void setPaymentBillNumber(Integer paymentBillNumber) {
        this.paymentBillNumber = paymentBillNumber;
    }

    public Integer getPaymentBillSigned() {
        return paymentBillSigned;
    }

    public void setPaymentBillSigned(Integer paymentBillSigned) {
        this.paymentBillSigned = paymentBillSigned;
    }

    public Integer getPaymentBillNoSign() {
        return paymentBillNoSign;
    }

    public void setPaymentBillNoSign(Integer paymentBillNoSign) {
        this.paymentBillNoSign = paymentBillNoSign;
    }

    public Integer getThirdpartPaymentNumber() {
        return thirdpartPaymentNumber;
    }

    public void setThirdpartPaymentNumber(Integer thirdpartPaymentNumber) {
        this.thirdpartPaymentNumber = thirdpartPaymentNumber;
    }

    public BigDecimal getThirdpartPaymentAmount() {
        return thirdpartPaymentAmount;
    }

    public void setThirdpartPaymentAmount(BigDecimal thirdpartPaymentAmount) {
        this.thirdpartPaymentAmount = thirdpartPaymentAmount;
    }

    public Instant getThirdpartPaymentLastDate() {
        return thirdpartPaymentLastDate;
    }

    public void setThirdpartPaymentLastDate(Instant thirdpartPaymentLastDate) {
        this.thirdpartPaymentLastDate = thirdpartPaymentLastDate;
    }

    public Integer getSurveytNumber() {
        return surveytNumber;
    }

    public void setSurveytNumber(Integer surveytNumber) {
        this.surveytNumber = surveytNumber;
    }

    public BigDecimal getSurveyAmount() {
        return surveyAmount;
    }

    public void setSurveyAmount(BigDecimal surveyAmount) {
        this.surveyAmount = surveyAmount;
    }

    public Instant getSurveyLastDate() {
        return surveyLastDate;
    }

    public void setSurveyLastDate(Instant surveyLastDate) {
        this.surveyLastDate = surveyLastDate;
    }

    public Integer getSurveyBillNumber() {
        return surveyBillNumber;
    }

    public void setSurveyBillNumber(Integer surveyBillNumber) {
        this.surveyBillNumber = surveyBillNumber;
    }

    public Integer getSurveyBillSigned() {
        return surveyBillSigned;
    }

    public void setSurveyBillSigned(Integer surveyBillSigned) {
        this.surveyBillSigned = surveyBillSigned;
    }

    public Integer getSurveyBillNoSign() {
        return surveyBillNoSign;
    }

    public void setSurveyBillNoSign(Integer surveyBillNoSign) {
        this.surveyBillNoSign = surveyBillNoSign;
    }

    public Integer getLawayerNumber() {
        return lawayerNumber;
    }

    public void setLawayerNumber(Integer lawayerNumber) {
        this.lawayerNumber = lawayerNumber;
    }

    public BigDecimal getLawayerAmount() {
        return lawayerAmount;
    }

    public void setLawayerAmount(BigDecimal lawayerAmount) {
        this.lawayerAmount = lawayerAmount;
    }

    public Instant getLawayerLastDate() {
        return lawayerLastDate;
    }

    public void setLawayerLastDate(Instant lawayerLastDate) {
        this.lawayerLastDate = lawayerLastDate;
    }

    public Integer getLawayerBillNumber() {
        return lawayerBillNumber;
    }

    public void setLawayerBillNumber(Integer lawayerBillNumber) {
        this.lawayerBillNumber = lawayerBillNumber;
    }

    public Integer getLawayerBillSigned() {
        return lawayerBillSigned;
    }

    public void setLawayerBillSigned(Integer lawayerBillSigned) {
        this.lawayerBillSigned = lawayerBillSigned;
    }

    public Integer getLawayerBillNoSign() {
        return lawayerBillNoSign;
    }

    public void setLawayerBillNoSign(Integer lawayerBillNoSign) {
        this.lawayerBillNoSign = lawayerBillNoSign;
    }

    public Integer getCorrespondentNumber() {
        return correspondentNumber;
    }

    public void setCorrespondentNumber(Integer correspondentNumber) {
        this.correspondentNumber = correspondentNumber;
    }

    public BigDecimal getCorrespondentAmount() {
        return correspondentAmount;
    }

    public void setCorrespondentAmount(BigDecimal correspondentAmount) {
        this.correspondentAmount = correspondentAmount;
    }

    public Instant getCorrespondentLastDate() {
        return correspondentLastDate;
    }

    public void setCorrespondentLastDate(Instant correspondentLastDate) {
        this.correspondentLastDate = correspondentLastDate;
    }

    public Integer getCorrespondentBillNumber() {
        return correspondentBillNumber;
    }

    public void setCorrespondentBillNumber(Integer correspondentBillNumber) {
        this.correspondentBillNumber = correspondentBillNumber;
    }

    public Integer getCorrespondentBillSigned() {
        return correspondentBillSigned;
    }

    public void setCorrespondentBillSigned(Integer correspondentBillSigned) {
        this.correspondentBillSigned = correspondentBillSigned;
    }

    public Integer getCorrespondentBillNoSign() {
        return correspondentBillNoSign;
    }

    public void setCorrespondentBillNoSign(Integer correspondentBillNoSign) {
        this.correspondentBillNoSign = correspondentBillNoSign;
    }

    public Integer getOtherNumber() {
        return otherNumber;
    }

    public void setOtherNumber(Integer otherNumber) {
        this.otherNumber = otherNumber;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Instant getOtherLastDate() {
        return otherLastDate;
    }

    public void setOtherLastDate(Instant otherLastDate) {
        this.otherLastDate = otherLastDate;
    }

    public Integer getOtherBillNumber() {
        return otherBillNumber;
    }

    public void setOtherBillNumber(Integer otherBillNumber) {
        this.otherBillNumber = otherBillNumber;
    }

    public Integer getOtherBillSigned() {
        return otherBillSigned;
    }

    public void setOtherBillSigned(Integer otherBillSigned) {
        this.otherBillSigned = otherBillSigned;
    }

    public Integer getOtherBillNoSign() {
        return otherBillNoSign;
    }

    public void setOtherBillNoSign(Integer otherBillNoSign) {
        this.otherBillNoSign = otherBillNoSign;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public BigDecimal getNetPayment() {
        return netPayment;
    }

    public void setNetPayment(BigDecimal netPayment) {
        this.netPayment = netPayment;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public BigDecimal getNetCPIPayment() {
        return netCPIPayment;
    }

    public void setNetCPIPayment(BigDecimal netCPIPayment) {
        this.netCPIPayment = netCPIPayment;
    }

    public BigDecimal getCpiTotal() {
        return cpiTotal;
    }

    public void setCpiTotal(BigDecimal cpiTotal) {
        this.cpiTotal = cpiTotal;
    }
}
