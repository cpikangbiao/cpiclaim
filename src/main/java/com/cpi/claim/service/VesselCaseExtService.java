package com.cpi.claim.service;

import com.cpi.claim.domain.*;
import com.cpi.claim.repository.StatisticsCaseMonthCountRepository;
import com.cpi.claim.repository.StatisticsCaseYearCountRepository;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for executing complex queries for VesselCase entities in the database.
 * The main input is a {@link VesselCaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VesselCaseDTO} or a {@link Page} of {@link VesselCaseDTO} which fulfills the criteria.
 */
@Service
@Transactional
public class VesselCaseExtService {

    private final Logger log = LoggerFactory.getLogger(VesselCaseExtService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    @Autowired
    private StatisticsCaseYearCountRepository statisticsCaseYearCountRepository;

    @Autowired
    private StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository;

    public VesselCaseExtService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }



}
