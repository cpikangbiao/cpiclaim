/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CaseStatsBean
 * Author:   admin
 * Date:     2018/11/21 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cpi.claim.service.bean.statistics;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.service.utility.ClaimToolUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/21
 * @since 1.0.0
 */
public class CaseStatsBean implements Serializable {

    private List<VesselCase> vesselCases;

    private List<CaseStatsPerCaseBean> caseStatsPerCaseBeans;

    private List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans;

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

    public void init(List<VesselCase> vesselCases, Integer language, ClaimToolUtility claimToolUtility) {
        this.vesselCases = vesselCases;
        this.caseStatsPerCaseBeans = new ArrayList<>();
        this.caseStatsPerSubCaseBeans = new ArrayList<>();
        int i = 1;
        for (VesselCase vesselCase : vesselCases) {
            CaseStatsPerCaseBean caseStatsPerCaseBean = new CaseStatsPerCaseBean();
            caseStatsPerCaseBean.init(vesselCase, language, claimToolUtility);
            this.caseStatsPerCaseBeans.add(caseStatsPerCaseBean);
            this.caseStatsPerSubCaseBeans.addAll(caseStatsPerCaseBean.getCaseStatsPerSubCaseBeans());
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

        if (claimAmount.equals(new BigDecimal(0))) {
            benifitRatio = new BigDecimal(0);
        } else {
            benifitRatio = new BigDecimal(1).subtract(paymentAmount.divide(claimAmount));
        }


        if (paymentAmount.equals(new BigDecimal(0))) {
            costRatio = new BigDecimal(0);
        } else  {
            costRatio = new BigDecimal(1).subtract(totalCost.divide(paymentAmount));
        }
    }

    public CaseStatsBean() {
    }

    public List<VesselCase> getVesselCases() {
        return vesselCases;
    }

    public void setVesselCases(List<VesselCase> vesselCases) {
        this.vesselCases = vesselCases;
    }

    public List<CaseStatsPerCaseBean> getCaseStatsPerCaseBeans() {
        return caseStatsPerCaseBeans;
    }

    public void setCaseStatsPerCaseBeans(List<CaseStatsPerCaseBean> caseStatsPerCaseBeans) {
        this.caseStatsPerCaseBeans = caseStatsPerCaseBeans;
    }

    public List<CaseStatsPerSubCaseBean> getCaseStatsPerSubCaseBeans() {
        return caseStatsPerSubCaseBeans;
    }

    public void setCaseStatsPerSubCaseBeans(List<CaseStatsPerSubCaseBean> caseStatsPerSubCaseBeans) {
        this.caseStatsPerSubCaseBeans = caseStatsPerSubCaseBeans;
    }

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
