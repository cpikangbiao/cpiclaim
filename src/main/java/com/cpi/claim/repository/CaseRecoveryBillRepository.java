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

package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.domain.CaseRecoveryBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseRecoveryBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryBillRepository extends JpaRepository<CaseRecoveryBill, Long>, JpaSpecificationExecutor<CaseRecoveryBill> {

    @Query("SELECT i.caseClaimBill "
        + " FROM CaseRecoveryBill i "
        + " WHERE i.caseRecovery = :caseRecovery ")
    List<CaseClaimBill> findAllCaseClaimBillByCaseRecovery(@Param("caseRecovery") CaseRecovery caseRecovery);

    List<CaseRecoveryBill> findAllByCaseRecovery(CaseRecovery caseRecovery);

    CaseRecoveryBill findFirstByWriteOffBill(CaseClaimBill caseClaimBill);


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseRecoveryBill cc "
        + " WHERE cc.caseRecovery = :caseRecovery ")
    Integer findMaxNumberIdByCaseRecovery(@Param("caseRecovery") CaseRecovery caseRecovery);
}
