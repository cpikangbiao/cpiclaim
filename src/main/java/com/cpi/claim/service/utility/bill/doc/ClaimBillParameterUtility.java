/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午2:44
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
package com.cpi.claim.service.utility.bill.doc;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/10/29
 * @since 1.0.0
 */
public  class ClaimBillParameterUtility {

//    public static  Map<String, Object> copyCreditorParameter(CaseClaimBill caseClaimBill) {
//        Map<String, Object> parameter = new HashMap<>();
//        VesselCase vesselCase = caseClaimBill.getSubcase().getVesselCase();
//        VesselSubCase vesselSubCase   = caseClaimBill.getSubcase();
//        InsuredVesselDTO insuredvessel = vesselcase.getInsuredVesselId();
//
//        String companyName = insuredvessel.getMemberId().getCompanyId().getCode()
//            + " "
//            + insuredvessel.getMemberId().getCompanyId().getCompanyName();
//        parameter.put("CompanyName", companyName);
//        //2013-09-06修改曾用名
//        if (vesselcase.getVesselName() != null)
//            parameter.put("VesselName", vesselcase.getVesselName());
//        else if (insuredvessel.getVesselId() != null)
//            parameter.put("VesselName", insuredvessel.getVesselId().getVesselName());
//
//        //20161128 应贾金涛的要求在账单上显示IMOstringImoNumber.append("IMO:");stringImoNumber.append("IMO:");
//        StringBuilder stringImoNumber = new StringBuilder();
//        if (insuredvessel.getVesselId().getImoNumber() != null) {
//            stringImoNumber.append("IMO:");
//            stringImoNumber.append(insuredvessel.getVesselId().getImoNumber());
//        }UWToolUtility
//        parameter.put("VesselIMO", stringImoNumber.toString());
//
//        parameter.put("ClaimBillCode", caseClaimBill.getClaimBillCode());
//        parameter.put("CaseCode", vesselcase.getCaseCode());
//        parameter.put("CaseDate", sdf.format(vesselcase.getCaseDate()));
//
//        parameter.put("VoyageNo", vesselcase.getVoy());
//        if (vesselcase.getCaseLocation() != null)
//            parameter.put("Port", vesselcase.getCaseLocation().getPortName());
//
////    	parameter.put("BLNo", vesselcase.getVoy());
//        parameter.put("Incident", vesselcase.getCaseDescription());
//        parameter.put("Year", insuredvessel.getMemberId().getYear());
//
//        if (caseClaimBill.getClaimBillType().getId().equals(ClaimBillTypeDAO.CLAIM_BILL_TYPE_CLAIMFEE)) {
//            List<ClaimFeeBillVO> list = new ArrayList<ClaimFeeBillVO>();
//            ClaimFeeBillDAO dao = new ClaimFeeBillDAO();
//            list = dao.getClaimFeeList(caseClaimBill);
//            for (ClaimFeeBillVO claimFeeBill : list) {
//                if (claimFeeBill.getClaimFee().getClaimTypeId().getNameKey() != null)
//                    parameter.put("NatureOfFee",
//                        MessageUtil.message(request, claimFeeBill.getClaimFee().getClaimTypeId().getNameKey()));
//                else
//                    parameter.put("NatureOfFee",
//                        claimFeeBill.getClaimFee().getClaimTypeId().getName());
//
//                parameter.put("DNNo",
//                    claimFeeBill.getClaimBill().getClientBillNo());
//            }
//
//            //2014-05-14根据关于免赔额账单第二次会议的意见，进行修改
//            //1、将Amount修改为未扣除免赔额之前的数值
//            //2、添加免赔额
//            if (caseClaimBill.getAmount() != null)
//                parameter.put("Amount", nf.format(caseClaimBill.getAmount()));
//            parameter.put("Currency", caseClaimBill.getCurrency().getName());
//
//            if (caseClaimBill.getBillDescription() != null) {
//                parameter.put("NatureOfFee", caseClaimBill.getBillDescription());
//            }
//
//        } else if (caseClaimBill.getClaimBillType().getId().equals(ClaimBillTypeDAO.CLAIM_BILL_TYPE_PAYMENT)) {
//            parameter.put("NatureOfFee", "Reimbursement");
//            parameter.put("DNNo",
//                caseClaimBill.getClientBillNo());
////        	//2014-05-14根据关于免赔额账单第二次会议的意见，进行修改
////        	//1、将Amount修改为未扣除免赔额之前的数值
////        	//2、添加免赔额
////    		parameter.put("Currency", caseClaimBill.getClaimAmountCurrency().getName());
////        	if (caseClaimBill.getAmount() != null)
////        		parameter.put("Amount", nf.format(caseClaimBill.getClaimAmount()));
////
////        	StringBuilder deductibleString = new StringBuilder();
////        	deductibleString.append("USD ");
////        	deductibleString.append(nf.format(caseClaimBill.getDeductibleDollar()));
////        	if (caseClaimBill.getAmount() != null)
////        		parameter.put("Deductible",deductibleString.toString());
////        	//end
//
//            if (caseClaimBill.getAmount() != null)
//                parameter.put("Amount", nf.format(caseClaimBill.getAmount()));
//            parameter.put("Currency", caseClaimBill.getCurrency().getName());
//
//        } else {
//            parameter.put("NatureOfFee", caseClaimBill.getBillDescription());
//        }
//
//
//
//        parameter.put("Risk", subcase.getRiskId().getEnglishName());
//
//        if (caseClaimBill.getAmountDollar() != null)
//            parameter.put("AmountDollar", nf.format(caseClaimBill.getAmountDollar()));
//        if (caseClaimBill.getCurrencyRate() != null)
//            parameter.put("CurrencyRate", nf5.format(caseClaimBill.getCurrencyRate()));
//
//        //2017-11-22 根据周幼生的要求添加Partner评价结构，
//        //这里修改为如果存在Partner Account的账户信息，账单的这个数据就从这里提取
//        if (caseClaimBill.getClaimPartnerAccount() != null) {
//            parameter.put("CreditorName", caseClaimBill.getClaimPartnerAccount().getAccountName());
//            parameter.put("BankName", caseClaimBill.getClaimPartnerAccount().getBankName());
//            parameter.put("BankAddress", caseClaimBill.getClaimPartnerAccount().getBankAddress());
//            parameter.put("AccountNo", caseClaimBill.getClaimPartnerAccount().getAccountNo());
//        } else if (caseClaimBill.getCreditor() != null) {
//            parameter.put("CreditorName", caseClaimBill.getCreditor().getCreditorName());
//            parameter.put("BankName", caseClaimBill.getCreditor().getBankName());
//            parameter.put("BankAddress", caseClaimBill.getCreditor().getBankAddress());
//            parameter.put("AccountNo", caseClaimBill.getCreditor().getAccountNo());
//        }
//
//        parameter.put("Handler", caseClaimBill.getRegisterUser().getUserName().toUpperCase());
//        //2014-09-18 贾晋涛要求修改为账单生成时间
//        //康工，收到。我们提出这个需求，本以为是赔款通知书已经是显示生成账单时间了，但没想到还是打印时间。烦请您将赔款通知书的显示时间同样改为输入时间，谢谢！
//        //2018-01-30
//    	/*
//    	 * 藜迈
//		在目前的理赔业务系统中，“账单”即Claim Bill的系统生成时间（Date）为制作/添加账单日。
//
//		经理赔人员的工作实践，我们大多觉得这和理赔业务实践不太贴合。经与业务部门内部讨论沟通，从便利业务流程的角度出发，我们更希望账单生成日期为账单打印日。请参附件示例图片。
//
//		同时，我们理解在2014年系统进行过修正。经过探讨，我们觉得将账单日期修正为打印日期更加便利于工作开展。
//
//		*/
//        //    	parameter.put("Date", sdf.format(caseClaimBill.getClaimBillDate()));
//        parameter.put("Date", sdf.format(new Date()));
//        parameter.put("Remark", caseClaimBill.getRemark());
//
//        //2017-12-12 应严莉芳的要求添加IMO，Owner，flag
//        StringBuilder ownerString = new StringBuilder();
//        ownerString.append("Owner:");
//        if (insuredvessel.getVesselId().getOwnerId() != null) {
//            ownerString.append(insuredvessel.getVesselId().getOwnerId().getCompanyName());
//        } else {
//            ownerString.append("--");
//        }
//        parameter.put("OwnerName", ownerString.toString());
//
//        StringBuilder flagString = new StringBuilder();
//        flagString.append("Flag/Nationality:");
//        if (insuredvessel.getVesselId().getCountryId() != null) {
//            flagString.append(insuredvessel.getVesselId().getCountryId().getName());
//        } else {
//            flagString.append("--");
//        }
//        parameter.put("flag", flagString.toString());
//
//        return  parameter;
//
//    }
//
//    public static void CopyDebitParameter(ClaimBillVO caseClaimBill, Map<String, Object> parameter, HttpServletRequest request) {
//        PICaseVO vesselcase = caseClaimBill.getVesselSubCase().getCaseId();
//        SubCaseVO subcase   = caseClaimBill.getVesselSubCase();
//        InsuredVesselVO insuredvessel = vesselcase.getInsuredVesselId();
//
//        if (caseClaimBill.getCreditor() != null)
//            parameter.put("reciever", caseClaimBill.getCreditor().getCreditorName());
//
//        StringBuilder title = new StringBuilder();
//        //2013-09-06修改曾用名
//        if (vesselcase.getVesselName() != null)
//            title.append("'").append(vesselcase.getVesselName());
//        else if (insuredvessel.getVesselId() != null)
//            title.append("'").append(insuredvessel.getVesselId().getVesselName());
//
//
//        if (subcase.getCaseId().getCaseDescription() != null)
//            title.append("' ").append(subcase.getCaseId().getCaseDescription());
//        if (vesselcase.getCaseLocation() != null)
//            title.append(" at ").append(vesselcase.getCaseLocation().getPortName());
//        if (vesselcase.getCaseDate() != null)
//            title.append(" (").append(sdf.format(vesselcase.getCaseDate())).append(")");
//        parameter.put("Title", title.toString());
//
//        parameter.put("DebitNoteCode", caseClaimBill.getClaimBillCode());
////    	parameter.put("DebiteDate", sdf.format(new Date()));
//        //2014-05-04 贾晋涛要求修改为账单生成时间
//        parameter.put("DebiteDate", sdf.format(caseClaimBill.getClaimBillDate()));
//        parameter.put("Oref", subcase.getSubCaseCode());
//        //1.4.8 	2018-08-10 	理赔部新加功能：添加Debit Note的“Yref:”填写“Client Bill No. ”。 	新需求开发 	理赔部 	黎迈
//        if (caseClaimBill.getClientBillNo() != null)
//            parameter.put("Yref", caseClaimBill.getClientBillNo());
//        parameter.put("isSigned", false);
//
//        StringBuilder narrative = new StringBuilder();
//        if (caseClaimBill.getRemark() != null)
//            narrative.append(caseClaimBill.getRemark());
//        else {
//            narrative.append(caseClaimBill.getClaimBillType().getName()).append(" For ").append(title);
//        }
//
//        parameter.put("Narrative", caseClaimBill.getBillDescription());
//
//        StringBuilder amount = new StringBuilder();
//        amount.append(caseClaimBill.getCurrency().getName()).append(nf.format(caseClaimBill.getAmount()));
//        StringBuilder amountDollar = new StringBuilder();
//        amountDollar.append("USD").append(nf.format(caseClaimBill.getAmountDollar()));
//        parameter.put("Amount", amount.toString());
//        parameter.put("TotalAmount", amount.toString());
////    	parameter.put("AmountDollar", amountDollar.toString());
////    	parameter.put("TotalAmountDollar", amountDollar.toString());
//        parameter.put("Handler", caseClaimBill.getRegisterUser().getUserName().toUpperCase());
//
//        CompanyVO company = insuredvessel.getMemberId().getCompanyId();
//        parameter.put("Member", company.getCode() + " " + company.getCompanyName());
//    }


}
