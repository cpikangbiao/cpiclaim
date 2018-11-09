package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.CaseClaimBillRepository;
import com.cpi.claim.service.dto.CaseClaimBillCriteria;
import com.cpi.claim.service.dto.CaseClaimBillDTO;
import com.cpi.claim.service.mapper.CaseClaimBillMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CaseClaimBillExtService extends QueryService<CaseClaimBill> {

    private final Logger log = LoggerFactory.getLogger(CaseClaimBillExtService.class);

    private final CaseClaimBillRepository caseClaimBillRepository;

    private final CaseClaimBillMapper caseClaimBillMapper;

    public CaseClaimBillExtService(CaseClaimBillRepository caseClaimBillRepository, CaseClaimBillMapper caseClaimBillMapper) {
        this.caseClaimBillRepository = caseClaimBillRepository;
        this.caseClaimBillMapper = caseClaimBillMapper;
    }

    @Transactional(readOnly = true)
    public Integer findMaxNumberIdByYear(String year) {
        log.debug("find Max NumberId By year : {}", year);
        Integer maxNumberId = caseClaimBillRepository.findMaxNumberIdByYear(year);
        return maxNumberId + 1;
    }

}
