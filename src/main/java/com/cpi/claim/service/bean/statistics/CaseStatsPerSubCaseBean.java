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
package com.cpi.claim.service.bean.statistics;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.common.CompanyDTO;
import com.cpi.claim.service.dto.common.PortDTO;
import com.cpi.claim.service.dto.common.VesselDTO;
import com.cpi.claim.service.dto.uaa.UserDTO;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import com.cpi.claim.service.dto.uw.MemberDTO;
import com.cpi.claim.service.utility.ClaimToolUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/21
 * @since 1.0.0
 */
public class CaseStatsPerSubCaseBean implements Serializable {

    private Integer  numberId;

//    private VesselSubCase vesselSubCase;

    private Integer language;

    private String  year;

    private String  caseCode;

    private String  subCaseCode;

    private String  companyName;

    private String  vesselName;

    private String  registUser;

    private Instant registDate;

    private String  assignedUser;

    private Instant   caseDate;

    private String  caseLocation;

    private String  caseStatus;

    private Instant   closeDate;

    private String  risk;

    private BigDecimal guaranteeAmount;

    private BigDecimal  claimAmount;

    private BigDecimal  estimateAmount;

    private BigDecimal  riAmount;

    private BigDecimal  thirdpartAmount;

    private BigDecimal  memberPaymentAmount;

    private BigDecimal  surveyorFee;

    private BigDecimal  correspondentFee;

    private BigDecimal  lawyerFee;

    private BigDecimal  otherFee;

    private BigDecimal  totalCost;

    private BigDecimal  paymentAmount;

    private BigDecimal  grossPayment;

    private BigDecimal  netPayment;

    //效益率
    private BigDecimal  benifitRatio;

    //成本率
    private BigDecimal  costRatio;

    public CaseStatsPerSubCaseBean() {
        this.numberId = 0;
//        this.vesselSubCase = null;
        this.language = null;
        this.year = null;
        this.caseCode = null;
        this.subCaseCode = null;
        this.companyName = null;
        this.vesselName = null;
        this.registUser = null;
        this.registDate = Instant.now();
        this.assignedUser = null;
        this.caseDate = Instant.now();
        this.caseLocation = null;
        this.caseStatus = null;
        this.closeDate = Instant.now();
        this.risk = null;
        this.guaranteeAmount = new BigDecimal(0);
        this.claimAmount = new BigDecimal(0);
        this.estimateAmount = new BigDecimal(0);
        this.riAmount = new BigDecimal(0);
        this.thirdpartAmount = new BigDecimal(0);
        this.memberPaymentAmount = new BigDecimal(0);
        this.surveyorFee = new BigDecimal(0);
        this.correspondentFee = new BigDecimal(0);
        this.lawyerFee = new BigDecimal(0);
        this.otherFee = new BigDecimal(0);
        this.totalCost = new BigDecimal(0);
        this.paymentAmount = new BigDecimal(0);
        this.grossPayment = new BigDecimal(0);
        this.netPayment = new BigDecimal(0);
        this.benifitRatio = new BigDecimal(0);
        this.costRatio = new BigDecimal(0);
    }

    public void init(VesselSubCase vesselSubCase, Integer language, ClaimToolUtility claimToolUtility) {
//        this.vesselSubCase = vesselSubCase;
        this.language = language;

        VesselCase vesselCase = vesselSubCase.getVesselCase();
        if (vesselSubCase.getRisk() != null) {
            if (language.equals(1))
                this.risk  = vesselSubCase.getRisk().getRiskName();
            else
                this.risk  = vesselSubCase.getRisk().getRiskNameEnglish();
        }

        subCaseCode = vesselSubCase.getSubcaseCode();

        year = vesselCase.getCaseYear();

        caseCode        = vesselCase.getCaseCode();
        InsuredVesselDTO insuredVessel = claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselCase.getInsuredVesselId());
        MemberDTO member = claimToolUtility.memberRepository.findMemberByID(insuredVessel.getMemberId());
        CompanyDTO company = claimToolUtility.companyRepository.findCompanyByID(member.getCompany());
        VesselDTO vessel = claimToolUtility.vesselRepository.findVesselByID(insuredVessel.getVesselId());
        if (vessel != null) {
            vesselName      = vessel.getVesselName();
        }

        if (company != null)
            companyName     = company.getCompanyName();
        caseDate            = vesselCase.getCaseDate();
        registDate          = vesselCase.getRegisteredDate();


        if (vesselCase.getCaseLocation() != null)  {
            PortDTO caseLocationPort = claimToolUtility.portRepository.findPortByID(vesselCase.getCaseLocation());
            caseLocation    = caseLocationPort.getPortName();
        }

        if (vesselCase.getCaseStatus() != null) {
            caseStatus      = vesselCase.getCaseStatus().getCaseStatusName();
        }

        closeDate           = vesselCase.getCloseDate();
        if (vesselCase.getRegisteredHandler() != null) {
            UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getRegisteredHandler());
            if (language.equals(1))
                registUser   = user.getLogin();
            else
                registUser   = user.getLogin();
        }

        if (vesselCase.getAssignedHandler() != null) {
            UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getAssignedHandler());
            if (language.equals(1))
                assignedUser = user.getLogin().toUpperCase();
            else
                assignedUser = user.getLogin();
        }

        this.paymentAmount       = new BigDecimal(0);
        this.grossPayment        = new BigDecimal(0);
        this.netPayment          = new BigDecimal(0);
        this.benifitRatio        = new BigDecimal(0);

        //担保
        List<CaseGuarantee> caseGuarantees = claimToolUtility.caseGuaranteeRepository.findAllBySubcase(vesselSubCase);
        for (CaseGuarantee caseGuarantee : caseGuarantees) {
            if (caseGuarantee.getGuaranteeAmountDollar() != null)
                guaranteeAmount = guaranteeAmount.add(caseGuarantee.getGuaranteeAmountDollar());
        }

        //获得最后一次评估
        CaseEstimate caseEstimate = claimToolUtility.caseEstimateRepository.findFirstBySubcaseOrderByEstimateDateDesc(vesselSubCase);
        if (caseEstimate != null && caseEstimate.getEstimateEntityFee() != null)
            estimateAmount = estimateAmount.add(caseEstimate.getEstimateEntityFee());
        if (caseEstimate != null && caseEstimate.getEstimateCostFee() != null)
            estimateAmount = estimateAmount.add(caseEstimate.getEstimateCostFee());

        //索赔
        List<CaseClaim> caseClaims = claimToolUtility.caseClaimRepository.findAllBySubcase(vesselSubCase);
        for (CaseClaim caseClaim : caseClaims) {
            if (caseClaim.getClaimCostDollar() != null) {
                claimAmount = claimAmount.add(caseClaim.getClaimCostDollar());
            }
        }

        //再保赔偿
        RecoveryType recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_REINSURANCE);
        List<CaseRecovery> caseRecoveries = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
        for (CaseRecovery caseRecovery : caseRecoveries) {
            if (caseRecovery.getAmountDollar() != null) {
                riAmount = riAmount.add(caseRecovery.getAmountDollar());
            }
        }

        //第三方追偿
        recoveryType = claimToolUtility.recoveryTypeRepository.getOne(RecoveryType.RECOVERY_TYPE_THIRDPART);
        List<CaseRecovery> thirdpartRecoverys = claimToolUtility.caseRecoveryRepository.findAllBySubcaseAndRecoveryType(vesselSubCase, recoveryType);
        for (CaseRecovery thirdpartRecovery : thirdpartRecoverys) {
            if (thirdpartRecovery.getAmountDollar() != null) {
                thirdpartAmount = thirdpartAmount.add(thirdpartRecovery.getAmountDollar());
            }
        }

        //会员赔偿
        List<CasePayment> casePayments = claimToolUtility.casePaymentRepository.findAllBySubcase(vesselSubCase);
        for (CasePayment casePayment : casePayments) {
            if (casePayment.getPayCostDollar() != null) {
                memberPaymentAmount = memberPaymentAmount.add(casePayment.getPayCostDollar());
            }
        }

//		根据2012-12-21日朴俊龙和金聪的意见进行修改
//		参刚才康工提的问题。我的意见是计算LOSS RATIO时选择CPI total（即CPI对外总赔付已扣免的数据）
//		，在统计版块下的CLAIM LIST中总赔付也是选择该数据，不知康工是否可以处理，及还有什么其它问题。
//		对外赔付为扣除免赔额的金额
        BigDecimal deductible = new BigDecimal(0);
        if (vesselSubCase.getDeductDollar() != null) {
            deductible = deductible.add(vesselSubCase.getDeductDollar() );
        }

        memberPaymentAmount =  memberPaymentAmount.subtract(deductible);
        if (memberPaymentAmount.compareTo(new BigDecimal(0)) < 0) {
            memberPaymentAmount = new BigDecimal(0);
        }

//		end 2012-12-21 by kangbiao

        //费用
        List<CaseFee> caseFees = claimToolUtility.caseFeeRepository.findAllBySubcase(vesselSubCase);
        for (CaseFee caseFee : caseFees) {
            switch (caseFee.getFeeType().getId().intValue()) {
                case FeeType.CLAIMTYPE_SURVEYOR:
                    if (caseFee.getAmountDollar() != null)
                        surveyorFee = surveyorFee.add(caseFee.getAmountDollar());
                    break;
                case FeeType.CLAIMTYPE_CORRESPONDENT:
                    if (caseFee.getAmountDollar() != null)
                        correspondentFee = correspondentFee.add(caseFee.getAmountDollar());
                    break;
                case FeeType.CLAIMTYPE_LAWYER:
                    if (caseFee.getAmountDollar() != null)
                        lawyerFee = lawyerFee.add(caseFee.getAmountDollar());
                    break;
                case FeeType.CLAIMTYPE_OTHER:
                    if (caseFee.getAmountDollar() != null)
                        otherFee = otherFee.add(caseFee.getAmountDollar());
                    break;
                default:
                    if (caseFee.getAmountDollar() != null)
                        otherFee = otherFee.add(caseFee.getAmountDollar());
                    break;
            }
        }

        //感谢您的及时回复。所有统计下payment的统计都包括member和thirdparty 2014-02-24 贾晋涛邮件要求
//         2014-02-27 这里的memberPaymentAmount已经是所有赔付之和了。

        totalCost     = totalCost.add(surveyorFee).add(correspondentFee).add(lawyerFee).add(otherFee);
        paymentAmount = paymentAmount.add(memberPaymentAmount).add(surveyorFee).add(correspondentFee).add(lawyerFee).add(otherFee);

        //根据贾金涛2015-05-12邮件修改
        paymentAmount = paymentAmount.add(paymentAmount).add(thirdpartAmount);

        grossPayment = grossPayment.add(memberPaymentAmount).add(surveyorFee)
            .add(correspondentFee).add(lawyerFee).add(otherFee).add(estimateAmount)
            .subtract(thirdpartAmount);
        netPayment   = netPayment.add(memberPaymentAmount)
            .add(surveyorFee).add(correspondentFee )
            .add(lawyerFee).add(otherFee).add(estimateAmount)
            .subtract(thirdpartAmount)
            .subtract(riAmount);

        if (claimAmount.compareTo(BigDecimal.ZERO) == 0) {
            benifitRatio = new BigDecimal(0);
        } else {
            benifitRatio = new BigDecimal(1).subtract(paymentAmount.divide(claimAmount, 4, RoundingMode.HALF_UP));
        }


        if (paymentAmount.compareTo(BigDecimal.ZERO) == 0) {
            costRatio = new BigDecimal(0);
        } else  {
            costRatio = new BigDecimal(1).subtract(totalCost.divide(paymentAmount, 4, RoundingMode.HALF_UP));
        }
    }

    public Instant getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Instant registDate) {
        this.registDate = registDate;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public Instant getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(Instant caseDate) {
        this.caseDate = caseDate;
    }

    public String getCaseLocation() {
        return caseLocation;
    }

    public void setCaseLocation(String caseLocation) {
        this.caseLocation = caseLocation;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Instant getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Instant closeDate) {
        this.closeDate = closeDate;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public BigDecimal getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(BigDecimal estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public BigDecimal getRiAmount() {
        return riAmount;
    }

    public void setRiAmount(BigDecimal riAmount) {
        this.riAmount = riAmount;
    }

    public BigDecimal getThirdpartAmount() {
        return thirdpartAmount;
    }

    public void setThirdpartAmount(BigDecimal thirdpartAmount) {
        this.thirdpartAmount = thirdpartAmount;
    }

    public BigDecimal getMemberPaymentAmount() {
        return memberPaymentAmount;
    }

    public void setMemberPaymentAmount(BigDecimal memberPaymentAmount) {
        this.memberPaymentAmount = memberPaymentAmount;
    }

    public BigDecimal getSurveyorFee() {
        return surveyorFee;
    }

    public void setSurveyorFee(BigDecimal surveyorFee) {
        this.surveyorFee = surveyorFee;
    }

    public BigDecimal getCorrespondentFee() {
        return correspondentFee;
    }

    public void setCorrespondentFee(BigDecimal correspondentFee) {
        this.correspondentFee = correspondentFee;
    }

    public BigDecimal getLawyerFee() {
        return lawyerFee;
    }

    public void setLawyerFee(BigDecimal lawyerFee) {
        this.lawyerFee = lawyerFee;
    }

    public BigDecimal getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(BigDecimal otherFee) {
        this.otherFee = otherFee;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getGrossPayment() {
        return grossPayment;
    }

    public void setGrossPayment(BigDecimal grossPayment) {
        this.grossPayment = grossPayment;
    }

    public BigDecimal getNetPayment() {
        return netPayment;
    }

    public void setNetPayment(BigDecimal netPayment) {
        this.netPayment = netPayment;
    }

    public BigDecimal getBenifitRatio() {
        return benifitRatio;
    }

    public void setBenifitRatio(BigDecimal benifitRatio) {
        this.benifitRatio = benifitRatio;
    }

    public BigDecimal getCostRatio() {
        return costRatio;
    }

    public void setCostRatio(BigDecimal costRatio) {
        this.costRatio = costRatio;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

//    public VesselSubCase getVesselSubCase() {
//        return vesselSubCase;
//    }
//
//    public void setVesselSubCase(VesselSubCase vesselSubCase) {
//        this.vesselSubCase = vesselSubCase;
//    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCaseCode() {
        return caseCode;
    }

    public void setCaseCode(String caseCode) {
        this.caseCode = caseCode;
    }

    public String getSubCaseCode() {
        return subCaseCode;
    }

    public void setSubCaseCode(String subCaseCode) {
        this.subCaseCode = subCaseCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getRegistUser() {
        return registUser;
    }

    public void setRegistUser(String registUser) {
        this.registUser = registUser;
    }

    @Override
    public String toString() {
        return "CaseStatsPerSubCaseBean{" +
            "numberId=" + numberId +
//            ", vesselSubCase=" + vesselSubCase +
            ", language=" + language +
            ", year='" + year + '\'' +
            ", caseCode='" + caseCode + '\'' +
            ", subCaseCode='" + subCaseCode + '\'' +
            ", companyName='" + companyName + '\'' +
            ", vesselName='" + vesselName + '\'' +
            ", registUser='" + registUser + '\'' +
            ", registDate=" + registDate +
            ", assignedUser='" + assignedUser + '\'' +
            ", caseDate=" + caseDate +
            ", caseLocation='" + caseLocation + '\'' +
            ", caseStatus='" + caseStatus + '\'' +
            ", closeDate=" + closeDate +
            ", risk='" + risk + '\'' +
            ", guaranteeAmount=" + guaranteeAmount +
            ", claimAmount=" + claimAmount +
            ", estimateAmount=" + estimateAmount +
            ", riAmount=" + riAmount +
            ", thirdpartAmount=" + thirdpartAmount +
            ", memberPaymentAmount=" + memberPaymentAmount +
            ", surveyorFee=" + surveyorFee +
            ", correspondentFee=" + correspondentFee +
            ", lawyerFee=" + lawyerFee +
            ", otherFee=" + otherFee +
            ", totalCost=" + totalCost +
            ", paymentAmount=" + paymentAmount +
            ", grossPayment=" + grossPayment +
            ", netPayment=" + netPayment +
            ", benifitRatio=" + benifitRatio +
            ", costRatio=" + costRatio +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaseStatsPerSubCaseBean)) return false;
        CaseStatsPerSubCaseBean that = (CaseStatsPerSubCaseBean) o;
        return Objects.equals(getNumberId(), that.getNumberId()) &&
//            Objects.equals(getVesselSubCase(), that.getVesselSubCase()) &&
            Objects.equals(getLanguage(), that.getLanguage()) &&
            Objects.equals(getYear(), that.getYear()) &&
            Objects.equals(getCaseCode(), that.getCaseCode()) &&
            Objects.equals(getSubCaseCode(), that.getSubCaseCode()) &&
            Objects.equals(getCompanyName(), that.getCompanyName()) &&
            Objects.equals(getVesselName(), that.getVesselName()) &&
            Objects.equals(getRegistUser(), that.getRegistUser()) &&
            Objects.equals(getRegistDate(), that.getRegistDate()) &&
            Objects.equals(getAssignedUser(), that.getAssignedUser()) &&
            Objects.equals(getCaseDate(), that.getCaseDate()) &&
            Objects.equals(getCaseLocation(), that.getCaseLocation()) &&
            Objects.equals(getCaseStatus(), that.getCaseStatus()) &&
            Objects.equals(getCloseDate(), that.getCloseDate()) &&
            Objects.equals(getRisk(), that.getRisk()) &&
            Objects.equals(getGuaranteeAmount(), that.getGuaranteeAmount()) &&
            Objects.equals(getClaimAmount(), that.getClaimAmount()) &&
            Objects.equals(getEstimateAmount(), that.getEstimateAmount()) &&
            Objects.equals(getRiAmount(), that.getRiAmount()) &&
            Objects.equals(getThirdpartAmount(), that.getThirdpartAmount()) &&
            Objects.equals(getMemberPaymentAmount(), that.getMemberPaymentAmount()) &&
            Objects.equals(getSurveyorFee(), that.getSurveyorFee()) &&
            Objects.equals(getCorrespondentFee(), that.getCorrespondentFee()) &&
            Objects.equals(getLawyerFee(), that.getLawyerFee()) &&
            Objects.equals(getOtherFee(), that.getOtherFee()) &&
            Objects.equals(getTotalCost(), that.getTotalCost()) &&
            Objects.equals(getPaymentAmount(), that.getPaymentAmount()) &&
            Objects.equals(getGrossPayment(), that.getGrossPayment()) &&
            Objects.equals(getNetPayment(), that.getNetPayment()) &&
            Objects.equals(getBenifitRatio(), that.getBenifitRatio()) &&
            Objects.equals(getCostRatio(), that.getCostRatio());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumberId(), getLanguage(), getYear(), getCaseCode(), getSubCaseCode(), getCompanyName(), getVesselName(), getRegistUser(), getRegistDate(), getAssignedUser(), getCaseDate(), getCaseLocation(), getCaseStatus(), getCloseDate(), getRisk(), getGuaranteeAmount(), getClaimAmount(), getEstimateAmount(), getRiAmount(), getThirdpartAmount(), getMemberPaymentAmount(), getSurveyorFee(), getCorrespondentFee(), getLawyerFee(), getOtherFee(), getTotalCost(), getPaymentAmount(), getGrossPayment(), getNetPayment(), getBenifitRatio(), getCostRatio());
    }
}
