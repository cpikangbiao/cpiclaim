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

package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseFeeRepository;
import com.cpi.claim.service.dto.CaseEstimateDTO;
import com.cpi.claim.service.dto.CaseFeeCriteria;
import com.cpi.claim.service.dto.CaseFeeDTO;
import com.cpi.claim.service.mapper.CaseFeeMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for executing complex queries for CaseFee entities in the database.
 * The main input is a {@link CaseFeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CaseFeeDTO} or a {@link Page} of {@link CaseFeeDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class CaseFeeExtService extends QueryService<CaseFee> {

    private final Logger log = LoggerFactory.getLogger(CaseFeeExtService.class);

    private final CaseFeeRepository caseFeeRepository;

    private final CaseFeeMapper caseFeeMapper;

    public CaseFeeExtService(CaseFeeRepository caseFeeRepository, CaseFeeMapper caseFeeMapper) {
        this.caseFeeRepository = caseFeeRepository;
        this.caseFeeMapper = caseFeeMapper;
    }


    @Transactional(readOnly = true)
    public List<CaseFeeDTO> findAllByVesselCaseId(Long vesselCaseId) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final List<CaseFee> caseFees = caseFeeRepository.findAllBySubcaseVesselCaseId(vesselCaseId);
        return caseFeeMapper.toDto(caseFees);
    }

    @Transactional(readOnly = true)
    public Page<CaseFeeDTO> findAllByVesselCaseId(Long vesselCaseId, Pageable page) {
        log.debug("find All By VesselCase Id : {}", vesselCaseId);
        final Page<CaseFee> caseFees = caseFeeRepository.findAllBySubcaseVesselCaseId(vesselCaseId, page);
        return caseFees.map(caseFeeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdBySubCaseId(Long vesselCaseId) {
        log.debug("find Max NumberId By VesselCase Id : {}", vesselCaseId);
        Integer maxNumberId = caseFeeRepository.findMaxNumberIdBySubcaseId(vesselCaseId);
        return maxNumberId + 1;
    }


}
