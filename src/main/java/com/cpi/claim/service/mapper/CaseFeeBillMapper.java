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

package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseFeeBillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseFeeBill} and its DTO {@link CaseFeeBillDTO}.
 */
@Mapper(componentModel = "spring", uses = {CaseFeeMapper.class, CaseClaimBillMapper.class})
public interface CaseFeeBillMapper extends EntityMapper<CaseFeeBillDTO, CaseFeeBill> {

    @Mapping(source = "caseFee.id", target = "caseFeeId")
    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    @Mapping(source = "writeOffBill.id", target = "writeOffBillId")
    @Mapping(source = "writeOffBill.claimBillCode", target = "writeOffBillClaimBillCode")
    CaseFeeBillDTO toDto(CaseFeeBill caseFeeBill);

    @Mapping(source = "caseFeeId", target = "caseFee")
    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    @Mapping(source = "writeOffBillId", target = "writeOffBill")
    CaseFeeBill toEntity(CaseFeeBillDTO caseFeeBillDTO);

    default CaseFeeBill fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseFeeBill caseFeeBill = new CaseFeeBill();
        caseFeeBill.setId(id);
        return caseFeeBill;
    }
}
