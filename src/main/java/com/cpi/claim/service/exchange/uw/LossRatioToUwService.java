/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-27 下午1:25
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

package com.cpi.claim.service.exchange.uw;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.service.bean.statistics.CaseStatsPerCaseBean;
import com.cpi.claim.service.utility.ClaimToolUtility;
import com.cpi.share.claim.lossratio.LossRatioForClaimPartInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class LossRatioToUwService  {

    private final Logger log = LoggerFactory.getLogger(LossRatioToUwService.class);

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Transactional(readOnly = true)
    public LossRatioForClaimPartInfo getLossRatioForClaimPartInfo(Long insuredVesselId) {
        LossRatioForClaimPartInfo lossRatioForClaimPartInfo = new LossRatioForClaimPartInfo();
        List<VesselCase> vesselCases = claimToolUtility.vesselCaseRepository.findAllByInsuredVesselId(insuredVesselId);

        Integer caseNumber = 0;
        Integer subCaseNumber = 0;

        BigDecimal estimateAmount = BigDecimal.ZERO;
        BigDecimal claimAmount = BigDecimal.ZERO;
        BigDecimal riAmount = BigDecimal.ZERO;
        BigDecimal thirdpartAmount = BigDecimal.ZERO;
        BigDecimal memberPaymentAmount = BigDecimal.ZERO;
        BigDecimal surveyorFee = BigDecimal.ZERO;
        BigDecimal correspondentFee = BigDecimal.ZERO;
        BigDecimal lawyerFee = BigDecimal.ZERO;
        BigDecimal otherFee = BigDecimal.ZERO;
        BigDecimal paymentAmount = BigDecimal.ZERO;
        BigDecimal grossPayment = BigDecimal.ZERO;
        BigDecimal netPayment = BigDecimal.ZERO;

        for (VesselCase vesselCase : vesselCases) {
            caseNumber++;

            CaseStatsPerCaseBean caseStatsPerCaseBean = new CaseStatsPerCaseBean();
            caseStatsPerCaseBean.init(vesselCase, 1, claimToolUtility);
            subCaseNumber = subCaseNumber + caseStatsPerCaseBean.getSubCaseNum();

            estimateAmount = estimateAmount.add(caseStatsPerCaseBean.getEstimateAmount());
            claimAmount = claimAmount.add(caseStatsPerCaseBean.getClaimAmount());
            riAmount = riAmount.add(caseStatsPerCaseBean.getRiAmount());
            thirdpartAmount = thirdpartAmount.add(caseStatsPerCaseBean.getThirdpartAmount());
            memberPaymentAmount = memberPaymentAmount.add(caseStatsPerCaseBean.getMemberPaymentAmount());

            surveyorFee = surveyorFee.add(caseStatsPerCaseBean.getSurveyorFee());
            correspondentFee = correspondentFee.add(caseStatsPerCaseBean.getCorrespondentFee());
            lawyerFee = lawyerFee.add(caseStatsPerCaseBean.getLawyerFee());
            otherFee = otherFee.add(caseStatsPerCaseBean.getOtherFee());

            BigDecimal casePaymentAmount =
                caseStatsPerCaseBean.getMemberPaymentAmount()
                    .add(caseStatsPerCaseBean.getSurveyorFee())
                    .add(caseStatsPerCaseBean.getCorrespondentFee())
                    .add(caseStatsPerCaseBean.getLawyerFee())
                    .add(caseStatsPerCaseBean.getOtherFee())
                    .add(caseStatsPerCaseBean.getEstimateAmount())
                    .subtract(caseStatsPerCaseBean.getThirdpartAmount())
                    .subtract(caseStatsPerCaseBean.getRiAmount());

// 2015-09-28根据胡总要求，计算净赔付的时候，需要考虑到分保免赔额，如果没有分保，则是按照正常数值计算。
//			承保部新加功能：修改Loss Ratio的算法。
//			修改之前没有考虑到分保免赔额的做法，修改为考虑本身这个案件是否存在分保的情况：
//			之前算法：
//			所以案件只要超过40万，按照40万记。
//			现在算法：
//			1. 如果存在分保，则净赔付为超过免赔额按照免赔额计算。
//			2. 如果不存在分保，则净赔付为实际支出计算。

            if (vesselCase.getDeduct() != null
                && vesselCase.getDeduct().compareTo(BigDecimal.ZERO) > 0) {
                if (casePaymentAmount.compareTo(vesselCase.getDeduct()) > 0 ) {
                    casePaymentAmount = vesselCase.getDeduct();
                }
            }

            netPayment = netPayment.add(casePaymentAmount);

        }

        paymentAmount  = paymentAmount.add(memberPaymentAmount)
            .add(surveyorFee)
            .add(correspondentFee)
            .add(lawyerFee)
            .add(otherFee);
        grossPayment   = grossPayment.add(memberPaymentAmount)
            .add(surveyorFee)
            .add(correspondentFee)
            .add(lawyerFee)
            .add(otherFee)
            .add(estimateAmount);


        lossRatioForClaimPartInfo.init(
            caseNumber,  subCaseNumber,  estimateAmount,  claimAmount,  riAmount,
            thirdpartAmount,  memberPaymentAmount,  surveyorFee,
            correspondentFee,  lawyerFee,  otherFee,  paymentAmount,  grossPayment,  netPayment);

        return lossRatioForClaimPartInfo;
    }


}
