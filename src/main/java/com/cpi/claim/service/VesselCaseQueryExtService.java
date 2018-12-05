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
