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
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import io.github.jhipster.service.QueryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class VesselCaseQueryExtService extends QueryService<VesselCase> {

    private final Logger log = LoggerFactory.getLogger(VesselCaseQueryExtService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    public VesselCaseQueryExtService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    public List<VesselCase> getListForStats(
        List<Long> insuredVesselIds,
        CpiInsuranceType cpiInsuranceType,
        CaseStatusType  caseStatusType,
        Instant caseDateFrom,
        Instant caseDateTo,
        Instant registeredDateFrom,
        Instant registeredDateTo,
        Instant closeDateFrom,
        Instant closeDateTo
    ) {
        return vesselCaseRepository.findAll(getSpecification(
            insuredVesselIds,
            cpiInsuranceType,
            caseStatusType,
            caseDateFrom,
            caseDateTo,
            registeredDateFrom,
            registeredDateTo,
            closeDateFrom,
            closeDateTo));

    }

    private Specification<VesselCase> getSpecification(
                                            List<Long> insuredVesselIds,
                                            CpiInsuranceType cpiInsuranceType,
                                            CaseStatusType  caseStatusType,
                                            Instant caseDateFrom,
                                            Instant caseDateTo,
                                            Instant registeredDateFrom,
                                            Instant registeredDateTo,
                                            Instant closeDateFrom,
                                            Instant closeDateTo
        ) {
        Specification<VesselCase> specification = new Specification<VesselCase>() {

            @Override
            public javax.persistence.criteria.Predicate toPredicate(Root<VesselCase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (insuredVesselIds.size() > 0) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("insuredVesselId"));

                    for (Long insuredVesselId : insuredVesselIds) {
                        in.value(insuredVesselId);
                    }

                    predicates.add(in);
                }

                if (cpiInsuranceType != null) {
                    predicates.add(criteriaBuilder.equal(root.get("cpiInsuranceType"), cpiInsuranceType));
                }
                if (caseStatusType != null) {
                    predicates.add(criteriaBuilder.equal(root.get("caseStatus"), caseStatusType));
                }

                if (caseDateFrom != null && caseDateTo != null) {
                    predicates.add(criteriaBuilder.between(root.get("caseDate"), caseDateFrom, caseDateTo));
                }
                if (registeredDateFrom != null && registeredDateTo != null) {
                    predicates.add(criteriaBuilder.between(root.get("registeredDate"), registeredDateFrom, registeredDateTo));
                }
                if (closeDateFrom != null && closeDateTo != null) {
                    predicates.add(criteriaBuilder.between(root.get("closeDate"), closeDateFrom, closeDateTo));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };

        return specification;
    }


}
