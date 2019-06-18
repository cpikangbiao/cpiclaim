/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-17 下午4:10
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

package com.cpi.claim.web.rest;
import com.cpi.claim.service.CaseFeeForBillService;
import com.cpi.claim.service.bean.fee.CaseFeeBean;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST controller for managing CaseFee.
 */
@RestController
@RequestMapping("/api")
public class CaseFeeForBillResource {

    private final Logger log = LoggerFactory.getLogger(CaseFeeForBillResource.class);

    private static final String ENTITY_NAME = "caseFee";

    private final CaseFeeForBillService caseFeeForBillService;

    public CaseFeeForBillResource(CaseFeeForBillService caseFeeForBillService) {
        this.caseFeeForBillService = caseFeeForBillService;
    }


    @GetMapping("/case-fees/get-bean/{id}")
    public ResponseEntity<CaseFeeBean> getCaseFeeBeanForCaseFeeId(@PathVariable Long id) {
        log.debug("REST request to get CaseFeeBean For CaseFeeId(: {}", id);
        return new ResponseEntity<>(caseFeeForBillService.getCaseFeeBeanForCaseFeeId(id), HttpStatus.OK);
    }

    @PostMapping("/case-fees/create-bill/credit")
    public ResponseEntity<CaseClaimBillDTO> createClaimBillForCredit(
        @RequestParam Long caseFeeId,
        @RequestParam String clientBillNo,
        @RequestParam Long creditorId,
        @RequestParam Long userId,
        @RequestParam Long claimAmountCurrency,
        @RequestParam BigDecimal claimAmount
    ) {
        log.debug("REST request to create Claim Bill For Credit with caseFeeId : {} clientBillNo: {} : creditorId : {} userId: {} claimAmountCurrency: {} claimAmount: {}",
            caseFeeId, clientBillNo, creditorId, userId, claimAmountCurrency, claimAmount);
        return new ResponseEntity<>(
            caseFeeForBillService.createClaimBillForCredit(
                caseFeeId,
                clientBillNo,
                creditorId,
                userId,
                claimAmountCurrency,
                claimAmount), HttpStatus.OK);
    }

    @PostMapping("/case-fees/create-bill/credit-with-deductible")
    public ResponseEntity<CaseClaimBillDTO> createClaimBillForCreditWithDeductible(
        @RequestParam Long caseFeeId,
        @RequestParam String clientBillNo,
        @RequestParam Long creditorId,
        @RequestParam Long userId,
        @RequestParam Long claimAmountCurrency,
        @RequestParam BigDecimal claimAmount,
        @RequestParam Long deductibleCurrency,
        @RequestParam BigDecimal deductibleCurrencyRate,
        @RequestParam BigDecimal deductibleAmount
    ) {
        log.debug("REST request to create Claim Bill For Credit With Deductible with caseFeeId : {} clientBillNo: {} : creditorId : {} userId: {} claimAmountCurrency: {} claimAmount: {}",
            caseFeeId, clientBillNo, creditorId, userId, claimAmountCurrency, claimAmount);
        return new ResponseEntity<>(
            caseFeeForBillService.createClaimBillForCreditWithDeductible(
                caseFeeId,
                clientBillNo,
                creditorId,
                userId,
                claimAmountCurrency,
                claimAmount,
                deductibleCurrency,
                deductibleCurrencyRate,
                deductibleAmount), HttpStatus.OK);
    }

    @PostMapping("/case-fees/create-bill/debit")
    public ResponseEntity<CaseClaimBillDTO> createClaimBillForDebit(
        @RequestParam Long caseFeeId,
        @RequestParam String clientBillNo,
        @RequestParam Long creditorId,
        @RequestParam Long userId,
        @RequestParam Long claimAmountCurrency,
        @RequestParam BigDecimal claimAmount
    ) {
        log.debug("REST request to create Claim Bill For Debit with caseFeeId : {} clientBillNo: {} : creditorId : {} userId: {} claimAmountCurrency: {} claimAmount: {}",
            caseFeeId, clientBillNo, creditorId, userId, claimAmountCurrency, claimAmount);
        return new ResponseEntity<>(
            caseFeeForBillService.createClaimBillForDebit(
                caseFeeId,
                clientBillNo,
                creditorId,
                userId,
                claimAmountCurrency,
                claimAmount), HttpStatus.OK);
    }

}
