/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午4:06
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

package com.cpi.claim.service;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.Creditor;
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.bean.fee.CaseFeeBean;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;
import com.cpi.claim.service.mapper.CaseFeeMapper;
import com.cpi.claim.service.utility.ClaimToolUtility;
import com.cpi.claim.service.utility.bill.ClaimBillFeeUtility;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for executing complex queries for CaseFee entities in the database.
 * The main input is a {@link CaseFeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseFeeDTO} or a {@link Page} of {@link CaseFeeDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class CaseFeeForBillService extends QueryService<CaseFee> {

    private final Logger log = LoggerFactory.getLogger(CaseFeeForBillService.class);

    private final CaseFeeRepository caseFeeRepository;

    private final CaseFeeMapper caseFeeMapper;

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Autowired
    private ClaimBillFeeUtility claimBillFeeUtility;

    @Autowired
    private CaseClaimBillMapper caseClaimBillMapper;;

    public CaseFeeForBillService(CaseFeeRepository caseFeeRepository, CaseFeeMapper caseFeeMapper) {
        this.caseFeeRepository = caseFeeRepository;
        this.caseFeeMapper = caseFeeMapper;
    }


    @Transactional(readOnly = true)
    public CaseFeeBean getCaseFeeBeanForCaseFeeId(Long caseFeeId) {
        log.debug("find getCaseFeeBeanForCaseFeeId By caseFeeId Id : {}", caseFeeId);
        CaseFeeBean caseFeeBean = new CaseFeeBean();
        CaseFee caseFee = caseFeeRepository.getOne(caseFeeId);
        if (caseFee != null) {
            caseFeeBean.init(caseFee, claimToolUtility);
        }

        return caseFeeBean;
    }

    @Transactional
    public CaseClaimBillDTO createClaimBillForCredit(
        Long caseFeeId,
        String clientBillNo,
        Long creditorId,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount
    ) {
        log.debug("find getCaseFeeBeanForCaseFeeId By caseFeeId Id : {}", caseFeeId);
        CaseClaimBill caseClaimBill = null;
        CaseFee caseFee = claimToolUtility.caseFeeRepository.getOne(caseFeeId);
        Creditor creditor = claimToolUtility.creditorRepository.getOne(creditorId);
        if (caseFee != null
            && creditor != null) {
            caseClaimBill = claimBillFeeUtility.createClaimBillForCredit(
                caseFee,
                clientBillNo,
                creditor,
                userId,
                claimAmountCurrency,
                claimAmount
            );
        }

        return caseClaimBillMapper.toDto(caseClaimBill) ;
    }

    @Transactional
    public CaseClaimBillDTO createClaimBillForDebit(
        Long caseFeeId,
        String clientBillNo,
        Long creditorId,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount
    ) {
        log.debug("find getCaseFeeBeanForCaseFeeId By caseFeeId Id : {}", caseFeeId);
        CaseClaimBill caseClaimBill = null;
        CaseFee caseFee = claimToolUtility.caseFeeRepository.getOne(caseFeeId);
        Creditor creditor = claimToolUtility.creditorRepository.getOne(creditorId);
        if (caseFee != null
            && creditor != null) {
            caseClaimBill = claimBillFeeUtility.createClaimBillForDebit(
                caseFee,
                clientBillNo,
                creditor,
                userId,
                claimAmountCurrency,
                claimAmount
            );
        }

        return caseClaimBillMapper.toDto(caseClaimBill);
    }

    @Transactional
    public CaseClaimBillDTO createClaimBillForCreditWithDeductible(
        Long caseFeeId,
        String clientBillNo,
        Long creditorId,
        Long userId,
        Long claimAmountCurrency,
        BigDecimal claimAmount,
        Long deductibleCurrency,
        BigDecimal deductibleCurrencyRate,
        BigDecimal deductibleAmount
    ) {
        log.debug("find getCaseFeeBeanForCaseFeeId By caseFeeId Id : {}", caseFeeId);
        CaseClaimBill caseClaimBill = null;
        CaseFee caseFee = claimToolUtility.caseFeeRepository.getOne(caseFeeId);
        Creditor creditor = claimToolUtility.creditorRepository.getOne(creditorId);
        if (caseFee != null
            && creditor != null) {
            caseClaimBill = claimBillFeeUtility.createClaimBillForCreditWithDeductible(
                caseFee,
                clientBillNo,
                creditor,
                userId,
                claimAmountCurrency,
                claimAmount,
                deductibleCurrency,
                deductibleCurrencyRate,
                deductibleAmount
            );
        }

        return caseClaimBillMapper.toDto(caseClaimBill);
    }

}
