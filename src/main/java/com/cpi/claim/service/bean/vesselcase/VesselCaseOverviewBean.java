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

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.VesselSubCase;
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
public class VesselCaseOverviewBean implements Serializable {

    private VesselCase vesselCase;

    private List<VesselSubCaseOverviewBean> vesselSubCaseOverviewBeans;


    private Integer numberSubCase;

    //担保
    private Integer guaranteeNumber;

    private BigDecimal  guaranteeAmount;

    //索赔
    private Integer claimNumber;

    private BigDecimal claimAmount;

    //评估
    private Integer estimateNumber;

    private BigDecimal  estimateAmount;

    //分保赔偿
    private Integer recoveryNumber;

    private BigDecimal  recoveryAmount;

    //第三方赔偿
    private Integer thirdpartNumber;

    private BigDecimal  thirdpartAmount;

    //会员赔偿
    private Integer memberPaymentNumber;

    private BigDecimal  memberPaymentAmount;

    //第三方赔偿
    private Integer thirdpartPaymentNumber;

    private BigDecimal  thirdpartPaymentAmount;


    //检验费
    private Integer surveytNumber;

    private BigDecimal  surveyAmount;

    //律师费
    private Integer lawayerNumber;

    private BigDecimal  lawayerAmount;

    //通代费
    private Integer correspondentNumber;

    private BigDecimal  correspondentAmount;

    //其他费用
    private Integer otherNumber;

    private BigDecimal  otherAmount;

    //花费总数
    private BigDecimal  totalCost;

    //赔偿总数
    private BigDecimal  totalPayment;

    private BigDecimal  netPayment;

    private BigDecimal  deductible;

    private BigDecimal  netCPIPayment;

    private BigDecimal  cpiTotal;

    public void init (VesselCase vesselCase, ClaimToolUtility claimToolUtility) {
        this.vesselCase = vesselCase;
        List<VesselSubCase> subCases = claimToolUtility.vesselSubCaseRepository.findAllByVesselCase(vesselCase);

        for (VesselSubCase subCase : subCases) {
            VesselSubCaseOverviewBean vesselSubCaseOverviewBean = new VesselSubCaseOverviewBean();
            vesselSubCaseOverviewBean.init(subCase, claimToolUtility);
            vesselSubCaseOverviewBeans.add(vesselSubCaseOverviewBean);
        }

        numberSubCase   = vesselSubCaseOverviewBeans.size();
        for (VesselSubCaseOverviewBean vesselSubCaseOverviewBean : vesselSubCaseOverviewBeans) {
            guaranteeNumber        += vesselSubCaseOverviewBean.getGuaranteeNumber();
            guaranteeAmount        = guaranteeAmount.add(vesselSubCaseOverviewBean.getGuaranteeAmount());
            claimNumber            += vesselSubCaseOverviewBean.getClaimNumber();
            claimAmount            = claimAmount    .add(vesselSubCaseOverviewBean.getClaimAmount());
            estimateNumber         += vesselSubCaseOverviewBean.getEstimateNumber();
            estimateAmount         = estimateAmount .add(vesselSubCaseOverviewBean.getEstimateAmount());
            recoveryNumber         += vesselSubCaseOverviewBean.getRecoveryNumber();
            recoveryAmount         = recoveryAmount .add(vesselSubCaseOverviewBean.getRecoveryAmount());
            thirdpartNumber        += vesselSubCaseOverviewBean.getThirdpartNumber();
            thirdpartAmount        = thirdpartAmount.add(vesselSubCaseOverviewBean.getThirdpartAmount());
            memberPaymentNumber    += vesselSubCaseOverviewBean.getMemberPaymentNumber();
            memberPaymentAmount    = memberPaymentAmount.add(vesselSubCaseOverviewBean.getMemberPaymentAmount());
            thirdpartPaymentNumber += vesselSubCaseOverviewBean.getThirdpartPaymentNumber();
            thirdpartPaymentAmount = thirdpartPaymentAmount.add(vesselSubCaseOverviewBean.getThirdpartPaymentAmount());
            surveytNumber          += vesselSubCaseOverviewBean.getSurveytNumber();
            surveyAmount           = surveyAmount   .add(vesselSubCaseOverviewBean.getSurveyAmount());
            lawayerNumber          +=  vesselSubCaseOverviewBean.getLawayerNumber();
            lawayerAmount          = lawayerAmount  .add(vesselSubCaseOverviewBean.getLawayerAmount());
            correspondentNumber    += vesselSubCaseOverviewBean.getCorrespondentNumber();
            correspondentAmount    = correspondentAmount.add(vesselSubCaseOverviewBean.getCorrespondentAmount());
            otherNumber            += vesselSubCaseOverviewBean.getOtherNumber();
            otherAmount            = otherAmount    .add(vesselSubCaseOverviewBean.getOtherAmount());
            totalCost              = totalCost      .add(vesselSubCaseOverviewBean.getTotalCost());
            totalPayment           = totalPayment   .add(vesselSubCaseOverviewBean.getTotalPayment());
            netPayment             = netPayment   .add(vesselSubCaseOverviewBean.getNetPayment());
            deductible             = deductible   .add(vesselSubCaseOverviewBean.getDeductible());
            netCPIPayment          = netCPIPayment   .add(vesselSubCaseOverviewBean.getNetCPIPayment());
            cpiTotal               = cpiTotal   .add(vesselSubCaseOverviewBean.getCpiTotal());
        }

    }

    public VesselCaseOverviewBean() {
        this.vesselCase = null;
        this.vesselSubCaseOverviewBeans = new ArrayList<>();
        this.numberSubCase = 0;
        this.guaranteeNumber = 0;
        this.guaranteeAmount = new BigDecimal(0);
        this.claimNumber = 0;
        this.claimAmount = new BigDecimal(0);
        this.estimateNumber = 0;
        this.estimateAmount = new BigDecimal(0);
        this.recoveryNumber = 0;
        this.recoveryAmount = new BigDecimal(0);
        this.thirdpartNumber = 0;
        this.thirdpartAmount = new BigDecimal(0);
        this.memberPaymentNumber = 0;
        this.memberPaymentAmount = new BigDecimal(0);
        this.thirdpartPaymentNumber = 0;
        this.thirdpartPaymentAmount = new BigDecimal(0);
        this.surveytNumber = 0;
        this.surveyAmount = new BigDecimal(0);
        this.lawayerNumber = 0;
        this.lawayerAmount = new BigDecimal(0);
        this.correspondentNumber = 0;
        this.correspondentAmount = new BigDecimal(0);
        this.otherNumber = 0;
        this.otherAmount = new BigDecimal(0);
        this.totalCost = new BigDecimal(0);
        this.totalPayment = new BigDecimal(0);
        this.netPayment = new BigDecimal(0);
        this.deductible = new BigDecimal(0);
        this.netCPIPayment = new BigDecimal(0);
        this.cpiTotal = new BigDecimal(0);
    }

    public VesselCase getVesselCase() {
        return vesselCase;
    }

    public void setVesselCase(VesselCase vesselCase) {
        this.vesselCase = vesselCase;
    }

    public List<VesselSubCaseOverviewBean> getVesselSubCaseOverviewBeans() {
        return vesselSubCaseOverviewBeans;
    }

    public void setVesselSubCaseOverviewBeans(List<VesselSubCaseOverviewBean> vesselSubCaseOverviewBeans) {
        this.vesselSubCaseOverviewBeans = vesselSubCaseOverviewBeans;
    }

    public Integer getNumberSubCase() {
        return numberSubCase;
    }

    public void setNumberSubCase(Integer numberSubCase) {
        this.numberSubCase = numberSubCase;
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
