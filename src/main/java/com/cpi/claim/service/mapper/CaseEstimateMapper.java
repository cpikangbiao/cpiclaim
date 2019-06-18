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
import com.cpi.claim.service.dto.CaseEstimateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseEstimate} and its DTO {@link CaseEstimateDTO}.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class})
public interface CaseEstimateMapper extends EntityMapper<CaseEstimateDTO, CaseEstimate> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    CaseEstimateDTO toDto(CaseEstimate caseEstimate);

    @Mapping(source = "subcaseId", target = "subcase")
    CaseEstimate toEntity(CaseEstimateDTO caseEstimateDTO);

    default CaseEstimate fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseEstimate caseEstimate = new CaseEstimate();
        caseEstimate.setId(id);
        return caseEstimate;
    }
}
