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
import com.cpi.claim.service.utility.ClaimToolUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class CaseStatsBean implements Serializable {

//    private List<VesselCase> vesselCases;

    private List<CaseStatsPerCaseBean> caseStatsPerCaseBeans;

//    private List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans;

    private Integer language;

    private Integer caseNum;

    private Integer subCaseNum;

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

    private BigDecimal  paymentAmount;

    private BigDecimal  grossPayment;

    private BigDecimal  netPayment;
    //效益率
    private BigDecimal  benifitRatio;
    //成本率
    private BigDecimal  costRatio;

    public CaseStatsBean() {
//        this.vesselCases = new ArrayList<>();
        this.caseStatsPerCaseBeans = new ArrayList<>();
//        this.caseStatsPerSubCaseBeans = new ArrayList<>();
        this.language = null;
        this.caseNum = 0;
        this.subCaseNum = 0;
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
        this.paymentAmount = new BigDecimal(0);
        this.grossPayment = new BigDecimal(0);
        this.netPayment = new BigDecimal(0);
        this.benifitRatio = new BigDecimal(0);
        this.costRatio = new BigDecimal(0);
    }

    public void init(List<VesselCase> vesselCases, Integer language, ClaimToolUtility claimToolUtility) {
//        this.vesselCases = vesselCases;
        this.caseStatsPerCaseBeans = new ArrayList<>();
//        this.caseStatsPerSubCaseBeans = new ArrayList<>();
        int i = 1;
        for (VesselCase vesselCase : vesselCases) {
            CaseStatsPerCaseBean caseStatsPerCaseBean = new CaseStatsPerCaseBean();
            caseStatsPerCaseBean.init(vesselCase, language, claimToolUtility);
            this.caseStatsPerCaseBeans.add(caseStatsPerCaseBean);
//            this.caseStatsPerSubCaseBeans.addAll(caseStatsPerCaseBean.getCaseStatsPerSubCaseBeans());
            i ++;
        }

        for (CaseStatsPerCaseBean caseStatsPerCaseBean : caseStatsPerCaseBeans) {
            caseNum             += 1;
            subCaseNum          += caseStatsPerCaseBean.getSubCaseNum();
            guaranteeAmount     = guaranteeAmount.add(caseStatsPerCaseBean.getGuaranteeAmount());
            estimateAmount      = estimateAmount .add(caseStatsPerCaseBean.getEstimateAmount());
            claimAmount         = claimAmount    .add(caseStatsPerCaseBean.getClaimAmount());
            riAmount            = riAmount       .add(caseStatsPerCaseBean.getRiAmount());
            thirdpartAmount     = thirdpartAmount.add(caseStatsPerCaseBean.getThirdpartAmount());
            memberPaymentAmount = memberPaymentAmount.add(caseStatsPerCaseBean.getMemberPaymentAmount());
            surveyorFee         = surveyorFee    .add(caseStatsPerCaseBean.getSurveyorFee());
            correspondentFee    = correspondentFee.add(caseStatsPerCaseBean.getCorrespondentFee());
            lawyerFee           = lawyerFee      .add(caseStatsPerCaseBean.getLawyerFee());
            otherFee            = otherFee       .add(caseStatsPerCaseBean.getOtherFee());
            totalCost           = totalCost      .add(caseStatsPerCaseBean.getTotalCost());
            paymentAmount       = paymentAmount  .add(caseStatsPerCaseBean.getPaymentAmount());
            grossPayment        = grossPayment   .add(caseStatsPerCaseBean.getGrossPayment());
            netPayment          = netPayment     .add(caseStatsPerCaseBean.getNetPayment());
        }

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

    @Override
    public String toString() {
        return "CaseStatsBean{" +
//            "vesselCases=" + vesselCases +
            ", caseStatsPerCaseBeans=" + caseStatsPerCaseBeans +
//            ", caseStatsPerSubCaseBeans=" + caseStatsPerSubCaseBeans +
            ", language=" + language +
            ", caseNum=" + caseNum +
            ", subCaseNum=" + subCaseNum +
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
        if (!(o instanceof CaseStatsBean)) return false;
        CaseStatsBean that = (CaseStatsBean) o;
        return Objects.equals(getCaseStatsPerCaseBeans(), that.getCaseStatsPerCaseBeans()) &&
            Objects.equals(getLanguage(), that.getLanguage()) &&
            Objects.equals(getCaseNum(), that.getCaseNum()) &&
            Objects.equals(getSubCaseNum(), that.getSubCaseNum()) &&
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
            Objects.equals(getPaymentAmount(), that.getPaymentAmount()) &&
            Objects.equals(getGrossPayment(), that.getGrossPayment()) &&
            Objects.equals(getNetPayment(), that.getNetPayment()) &&
            Objects.equals(getBenifitRatio(), that.getBenifitRatio()) &&
            Objects.equals(getCostRatio(), that.getCostRatio());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCaseStatsPerCaseBeans(), getLanguage(), getCaseNum(), getSubCaseNum(), getGuaranteeAmount(), getEstimateAmount(), getClaimAmount(), getRiAmount(), getThirdpartAmount(), getMemberPaymentAmount(), getSurveyorFee(), getCorrespondentFee(), getLawyerFee(), getOtherFee(), getTotalCost(), getPaymentAmount(), getGrossPayment(), getNetPayment(), getBenifitRatio(), getCostRatio());
    }

    //    public List<VesselCase> getVesselCases() {
//        return vesselCases;
//    }
//
//    public void setVesselCases(List<VesselCase> vesselCases) {
//        this.vesselCases = vesselCases;
//    }

    public List<CaseStatsPerCaseBean> getCaseStatsPerCaseBeans() {
        return caseStatsPerCaseBeans;
    }

    public void setCaseStatsPerCaseBeans(List<CaseStatsPerCaseBean> caseStatsPerCaseBeans) {
        this.caseStatsPerCaseBeans = caseStatsPerCaseBeans;
    }

//    public List<CaseStatsPerSubCaseBean> getCaseStatsPerSubCaseBeans() {
//        return caseStatsPerSubCaseBeans;
//    }
//
//    public void setCaseStatsPerSubCaseBeans(List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans) {
//        this.caseStatsPerSubCaseBeans = caseStatsPerSubCaseBeans;
//    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getCaseNum() {
        return caseNum;
    }

    public void setCaseNum(Integer caseNum) {
        this.caseNum = caseNum;
    }

    public Integer getSubCaseNum() {
        return subCaseNum;
    }

    public void setSubCaseNum(Integer subCaseNum) {
        this.subCaseNum = subCaseNum;
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
