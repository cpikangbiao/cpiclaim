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

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.service.common.Contants;
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
import java.util.ArrayList;
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
public class CaseStatsPerCaseBean implements Serializable {

//    private List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans;

    private Integer numberId;

//    private VesselCase vesselCase;

    private Integer language;

    private String  year;

    private String  caseCode;

    private String  companyName;

    private String  vesselName;

    private String  registUser;

    private Instant  registDate;

    private String  assignedUser;

    private Instant  caseDate;

    private String  caseLocation;

    private String  caseStatus;

    private Instant closeDate;

    private Integer subCaseNum;

    private String  risk;

    private String  riskNumber;

    private BigDecimal guaranteeAmount;

    private BigDecimal  estimateAmount;

    private BigDecimal  claimAmount;

    private BigDecimal  riAmount;

    private BigDecimal  thirdpartAmount;

    private BigDecimal  memberPaymentAmount;

    private BigDecimal  surveyorFee;

    private BigDecimal  correspondentFee;

    private BigDecimal  lawyerFee;

    private BigDecimal  otherFee;

    private BigDecimal  totalCost;

    private BigDecimal  thirdpartRecoveryAmount;

    private BigDecimal  paymentAmount;

    private BigDecimal  grossPayment;

    private BigDecimal  netPayment;
    //效益率
    private BigDecimal  benifitRatio;
    //成本率
    private BigDecimal  costRatio;

    public CaseStatsPerCaseBean() {
//        this.caseStatsPerSubCaseBeans = new ArrayList<>();
        this.numberId = 0;
//        this.vesselCase = null;
        this.language = null;
        this.year = null;
        this.caseCode = null;
        this.companyName = null;
        this.vesselName = null;
        this.registUser = null;
        this.registDate = null;
        this.assignedUser = null;
        this.caseDate = null;
        this.caseLocation = null;
        this.caseStatus = null;
        this.closeDate = null;
        this.subCaseNum = 0;
        this.risk = null;
        this.riskNumber = null;
        this.guaranteeAmount = new BigDecimal(0);
        this.estimateAmount = new BigDecimal(0);
        this.claimAmount = new BigDecimal(0);
        this.riAmount = new BigDecimal(0);
        this.thirdpartAmount = new BigDecimal(0);
        this.memberPaymentAmount = new BigDecimal(0);
        this.surveyorFee = new BigDecimal(0);
        this.correspondentFee = new BigDecimal(0);
        this.lawyerFee = new BigDecimal(0);
        this.otherFee = new BigDecimal(0);
        this.totalCost = new BigDecimal(0);
        this.thirdpartRecoveryAmount = new BigDecimal(0);
        this.paymentAmount = new BigDecimal(0);
        this.grossPayment = new BigDecimal(0);
        this.netPayment = new BigDecimal(0);
        this.benifitRatio = new BigDecimal(0);
        this.costRatio = new BigDecimal(0);
    }

    public void init (VesselCase vesselCase, Integer language, ClaimToolUtility claimToolUtility) {
//        this.vesselCase = vesselCase;
        this.language = language;
        List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans = new ArrayList<>();
        this.subCaseNum = 0;

        InsuredVesselDTO insuredVessel = claimToolUtility.insuredVesselRepository.findInsuredVesselByID(vesselCase.getInsuredVesselId());
        MemberDTO member = claimToolUtility.memberRepository.findMemberByID(insuredVessel.getMemberId());
        CompanyDTO company = claimToolUtility.companyRepository.findCompanyByID(member.getCompany());
        VesselDTO vessel = claimToolUtility.vesselRepository.findVesselByID(insuredVessel.getVesselId());

        year            = member.getMemberYear();
        caseCode        = vesselCase.getCaseCode();
        vesselName      = vessel.getVesselName();
        companyName     = company.getCompanyName();
        caseDate        = vesselCase.getCaseDate();
        registDate      = vesselCase.getRegisteredDate();
        if (vesselCase.getCaseLocation() != null)  {
            PortDTO caseLocationPort = claimToolUtility.portRepository.findPortByID(vesselCase.getCaseLocation());
            caseLocation    = caseLocationPort.getPortName();
        }

        if (vesselCase.getCaseStatus() != null) {
            caseStatus      = vesselCase.getCaseStatus().getCaseStatusName();
        }

        closeDate           = vesselCase.getCloseDate();
        if (language.equals(Contants.CONTANT_LANGUAGE_ENGLISH)) {
            if (vesselCase.getRegisteredHandler() != null) {
                UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getRegisteredHandler());
                registUser   = user.getLogin();
            }
            if (vesselCase.getAssignedHandler() != null) {
                UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getAssignedHandler());
                registUser   = user.getLogin();
            }
        } else {
            if (vesselCase.getRegisteredHandler() != null) {
                UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getRegisteredHandler());
                registUser   = user.getLogin();
            }
            if (vesselCase.getAssignedHandler() != null) {
                UserDTO user = claimToolUtility.userRepository.findUserByID(vesselCase.getAssignedHandler());
                registUser   = user.getLogin();
            }        }

        //这里为了计算每一个案件的赔偿总额
        BigDecimal caseGuaranteeAmount  = new BigDecimal(0);
        BigDecimal caseEstimate         = new BigDecimal(0);
        BigDecimal caseMemberPayment    = new BigDecimal(0);
        BigDecimal caseCorrespondentFee = new BigDecimal(0);
        BigDecimal caseLawerFee         = new BigDecimal(0);
        BigDecimal caseSurveyFee        = new BigDecimal(0);
        BigDecimal caseOtherFee         = new BigDecimal(0);
        BigDecimal caseThirdPart        = new BigDecimal(0);
        BigDecimal caseRI               = new BigDecimal(0);
        BigDecimal caseClaim            = new BigDecimal(0);

        List<VesselSubCase> vesselSubCases = claimToolUtility.vesselSubCaseRepository.findAllByVesselCase(vesselCase);
        BigDecimal maxClaimAmount = new BigDecimal(0);
        for (VesselSubCase vesselSubCase : vesselSubCases) {
            subCaseNum += 1;
            CaseStatsPerSubCaseBean caseStatsPerSubCaseBean = new CaseStatsPerSubCaseBean();
            caseStatsPerSubCaseBean.init(vesselSubCase, language, claimToolUtility);
            caseStatsPerSubCaseBeans.add(caseStatsPerSubCaseBean);

            if (vesselSubCase.getRisk() != null) {
                if (language.equals(1))
                    this.risk  = vesselSubCase.getRisk().getRiskName();
                else
                    this.risk  = vesselSubCase.getRisk().getRiskNameEnglish();
            }


            caseGuaranteeAmount  = caseGuaranteeAmount.add(caseStatsPerSubCaseBean.getGuaranteeAmount());
            caseEstimate         = caseEstimate.add(caseStatsPerSubCaseBean.getEstimateAmount());
            caseMemberPayment    = caseMemberPayment.add(caseStatsPerSubCaseBean.getMemberPaymentAmount());
            caseCorrespondentFee = caseCorrespondentFee.add(caseStatsPerSubCaseBean.getCorrespondentFee());
            caseLawerFee         = caseLawerFee.add(caseStatsPerSubCaseBean.getLawyerFee());
            caseSurveyFee        = caseSurveyFee.add(caseStatsPerSubCaseBean.getSurveyorFee());
            caseOtherFee         = caseOtherFee.add(caseStatsPerSubCaseBean.getOtherFee());
            caseThirdPart        = caseThirdPart.add(caseStatsPerSubCaseBean.getThirdpartAmount());
            caseRI               = caseRI.add(caseStatsPerSubCaseBean.getRiAmount());
            caseClaim            = caseClaim.add(caseStatsPerSubCaseBean.getClaimAmount());

//			if (vesselsubcase.getRiskId() != null && riskNumber == null)
//				riskNumber = vesselsubcase.getRiskId().getName();
//			else if(vesselsubcase.getRiskId() != null)
//				riskNumber = riskNumber + " " + vesselsubcase.getRiskId().getName();
            if (caseClaim.compareTo(maxClaimAmount) >= 0) {
                maxClaimAmount = caseClaim;
                if (vesselSubCase.getRisk() != null)
                    riskNumber = vesselSubCase.getRisk().getRiskName();
            }
        }
        //
        if (vesselSubCases.size() > 1) {
            riskNumber = riskNumber + "+";
        }

        totalCost = totalCost.add(caseSurveyFee).add(caseCorrespondentFee).add(caseLawerFee).add(caseOtherFee);
        paymentAmount = paymentAmount.add(caseMemberPayment).add(caseSurveyFee).add(caseCorrespondentFee).add(caseLawerFee).add(caseOtherFee);

        //根据贾金涛2015-05-12邮件修改
        paymentAmount = paymentAmount.add(paymentAmount).subtract(thirdpartAmount);
        // end 2015-05-12

        grossPayment = grossPayment.add(caseMemberPayment).add(caseSurveyFee).add(caseCorrespondentFee).add(caseLawerFee).add(caseOtherFee).add(
                caseEstimate).subtract(caseThirdPart);
        netPayment   = netPayment.add(caseMemberPayment).add(caseSurveyFee).add(caseCorrespondentFee).add(caseLawerFee).add(caseOtherFee)
            .add(caseEstimate).subtract(caseThirdPart).subtract(caseRI);

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

//    public List<CaseStatsPerSubCaseBean> getCaseStatsPerSubCaseBeans() {
//        return caseStatsPerSubCaseBeans;
//    }
//
//    public void setCaseStatsPerSubCaseBeans(List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans) {
//        this.caseStatsPerSubCaseBeans = caseStatsPerSubCaseBeans;
//    }

    @Override
    public String toString() {
        return "CaseStatsPerCaseBean{" +
//            "caseStatsPerSubCaseBeans=" + caseStatsPerSubCaseBeans +
            ", numberId=" + numberId +
//            ", vesselCase=" + vesselCase +
            ", language=" + language +
            ", year='" + year + '\'' +
            ", caseCode='" + caseCode + '\'' +
            ", companyName='" + companyName + '\'' +
            ", vesselName='" + vesselName + '\'' +
            ", registUser='" + registUser + '\'' +
            ", registDate=" + registDate +
            ", assignedUser='" + assignedUser + '\'' +
            ", caseDate=" + caseDate +
            ", caseLocation='" + caseLocation + '\'' +
            ", caseStatus='" + caseStatus + '\'' +
            ", closeDate=" + closeDate +
            ", subCaseNum=" + subCaseNum +
            ", risk='" + risk + '\'' +
            ", riskNumber='" + riskNumber + '\'' +
            ", guaranteeAmount=" + guaranteeAmount +
            ", estimateAmount=" + estimateAmount +
            ", claimAmount=" + claimAmount +
            ", riAmount=" + riAmount +
            ", thirdpartAmount=" + thirdpartAmount +
            ", memberPaymentAmount=" + memberPaymentAmount +
            ", surveyorFee=" + surveyorFee +
            ", correspondentFee=" + correspondentFee +
            ", lawyerFee=" + lawyerFee +
            ", otherFee=" + otherFee +
            ", totalCost=" + totalCost +
            ", thirdpartRecoveryAmount=" + thirdpartRecoveryAmount +
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
        if (!(o instanceof CaseStatsPerCaseBean)) return false;
        CaseStatsPerCaseBean that = (CaseStatsPerCaseBean) o;
        return Objects.equals(getNumberId(), that.getNumberId()) &&
            Objects.equals(getLanguage(), that.getLanguage()) &&
            Objects.equals(getYear(), that.getYear()) &&
            Objects.equals(getCaseCode(), that.getCaseCode()) &&
            Objects.equals(getCompanyName(), that.getCompanyName()) &&
            Objects.equals(getVesselName(), that.getVesselName()) &&
            Objects.equals(getRegistUser(), that.getRegistUser()) &&
            Objects.equals(getRegistDate(), that.getRegistDate()) &&
            Objects.equals(getAssignedUser(), that.getAssignedUser()) &&
            Objects.equals(getCaseDate(), that.getCaseDate()) &&
            Objects.equals(getCaseLocation(), that.getCaseLocation()) &&
            Objects.equals(getCaseStatus(), that.getCaseStatus()) &&
            Objects.equals(getCloseDate(), that.getCloseDate()) &&
            Objects.equals(getSubCaseNum(), that.getSubCaseNum()) &&
            Objects.equals(getRisk(), that.getRisk()) &&
            Objects.equals(getRiskNumber(), that.getRiskNumber()) &&
            Objects.equals(getGuaranteeAmount(), that.getGuaranteeAmount()) &&
            Objects.equals(getEstimateAmount(), that.getEstimateAmount()) &&
            Objects.equals(getClaimAmount(), that.getClaimAmount()) &&
            Objects.equals(getRiAmount(), that.getRiAmount()) &&
            Objects.equals(getThirdpartAmount(), that.getThirdpartAmount()) &&
            Objects.equals(getMemberPaymentAmount(), that.getMemberPaymentAmount()) &&
            Objects.equals(getSurveyorFee(), that.getSurveyorFee()) &&
            Objects.equals(getCorrespondentFee(), that.getCorrespondentFee()) &&
            Objects.equals(getLawyerFee(), that.getLawyerFee()) &&
            Objects.equals(getOtherFee(), that.getOtherFee()) &&
            Objects.equals(getTotalCost(), that.getTotalCost()) &&
            Objects.equals(getThirdpartRecoveryAmount(), that.getThirdpartRecoveryAmount()) &&
            Objects.equals(getPaymentAmount(), that.getPaymentAmount()) &&
            Objects.equals(getGrossPayment(), that.getGrossPayment()) &&
            Objects.equals(getNetPayment(), that.getNetPayment()) &&
            Objects.equals(getBenifitRatio(), that.getBenifitRatio()) &&
            Objects.equals(getCostRatio(), that.getCostRatio());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumberId(), getLanguage(), getYear(), getCaseCode(), getCompanyName(), getVesselName(), getRegistUser(), getRegistDate(), getAssignedUser(), getCaseDate(), getCaseLocation(), getCaseStatus(), getCloseDate(), getSubCaseNum(), getRisk(), getRiskNumber(), getGuaranteeAmount(), getEstimateAmount(), getClaimAmount(), getRiAmount(), getThirdpartAmount(), getMemberPaymentAmount(), getSurveyorFee(), getCorrespondentFee(), getLawyerFee(), getOtherFee(), getTotalCost(), getThirdpartRecoveryAmount(), getPaymentAmount(), getGrossPayment(), getNetPayment(), getBenifitRatio(), getCostRatio());
    }

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

//    public VesselCase getVesselCase() {
//        return vesselCase;
//    }
//
//    public void setVesselCase(VesselCase vesselCase) {
//        this.vesselCase = vesselCase;
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

    public Integer getSubCaseNum() {
        return subCaseNum;
    }

    public void setSubCaseNum(Integer subCaseNum) {
        this.subCaseNum = subCaseNum;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getRiskNumber() {
        return riskNumber;
    }

    public void setRiskNumber(String riskNumber) {
        this.riskNumber = riskNumber;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public BigDecimal getEstimateAmount() {
        return estimateAmount;
    }

    public void setEstimateAmount(BigDecimal estimateAmount) {
        this.estimateAmount = estimateAmount;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
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

    public BigDecimal getThirdpartRecoveryAmount() {
        return thirdpartRecoveryAmount;
    }

    public void setThirdpartRecoveryAmount(BigDecimal thirdpartRecoveryAmount) {
        this.thirdpartRecoveryAmount = thirdpartRecoveryAmount;
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
}
