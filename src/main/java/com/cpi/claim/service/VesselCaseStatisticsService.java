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

import com.cpi.claim.domain.StatisticsCaseMonthCount;
import com.cpi.claim.domain.StatisticsCaseYearCount;
import com.cpi.claim.repository.StatisticsCaseMonthCountRepository;
import com.cpi.claim.repository.StatisticsCaseYearCountRepository;
import com.cpi.claim.repository.VesselCaseRepository;
import com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import com.cpi.claim.service.dto.VesselCaseCriteria;
import com.cpi.claim.service.dto.VesselCaseDTO;
import com.cpi.claim.service.mapper.VesselCaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class VesselCaseStatisticsService {

    private final Logger log = LoggerFactory.getLogger(VesselCaseStatisticsService.class);

    private final VesselCaseRepository vesselCaseRepository;

    private final VesselCaseMapper vesselCaseMapper;

    @Autowired
    private StatisticsCaseYearCountRepository statisticsCaseYearCountRepository;

    @Autowired
    private StatisticsCaseMonthCountRepository statisticsCaseMonthCountRepository;

    public VesselCaseStatisticsService(VesselCaseRepository vesselCaseRepository, VesselCaseMapper vesselCaseMapper) {
        this.vesselCaseRepository = vesselCaseRepository;
        this.vesselCaseMapper = vesselCaseMapper;
    }

    @Transactional(readOnly = true)
    public List<CaseYearCountStatisticsBean> findCaseYearStaticsCount(String startYear, String  endYear) {
        log.debug("find by startYear : {}, endYear: {}", startYear, endYear);
        List<String> years = new ArrayList<String>();

        Integer startYearInt = new Integer(startYear);
        Integer endYearInt = new Integer(endYear);
        for (Integer i = startYearInt; i <= endYearInt; i++) {
            years.add(i.toString());
        }

        List<StatisticsCaseYearCount> statisticsCaseYearCounts = statisticsCaseYearCountRepository.findAllByCaseYearInOrderByCaseYear(years);

        List<CaseYearCountStatisticsBean> caseYearCountStatisticsBeans = new ArrayList<>();
        for (StatisticsCaseYearCount statisticsCaseYearCount : statisticsCaseYearCounts) {
            caseYearCountStatisticsBeans.add( new CaseYearCountStatisticsBean(
                statisticsCaseYearCount.getCaseYear(),
                statisticsCaseYearCount.getInsuranceType(),
                statisticsCaseYearCount.getCaseCount().longValue()
            ));
        }

        return caseYearCountStatisticsBeans;
    }


    public void gatherStaticForCaseYearCount() {
        log.debug("find by gather Static For Member Year Count");

        List<CaseYearCountStatisticsBean> caseYearCountStatisticsBeans =
            vesselCaseRepository.findCaseYearStaticsCount();
        if (caseYearCountStatisticsBeans.size() > 0) {
            statisticsCaseYearCountRepository.deleteAllInBatch();

            for (CaseYearCountStatisticsBean caseYearCountStatisticsBean : caseYearCountStatisticsBeans) {
                StatisticsCaseYearCount statisticsCaseYearCount = new StatisticsCaseYearCount();
                statisticsCaseYearCount.setCaseYear(caseYearCountStatisticsBean.getYear());
                statisticsCaseYearCount.setInsuranceType(caseYearCountStatisticsBean.getType());
                statisticsCaseYearCount.setCaseCount(caseYearCountStatisticsBean.getCount().intValue());
                statisticsCaseYearCount.setUpdateTime(Instant.now());
                statisticsCaseYearCountRepository.save(statisticsCaseYearCount);
            }
        }
    }

    public void gatherStaticForCaseMonthCount() {
        log.debug("find by gather Statics For case month Count");

        List<CaseMonthCountStatisticsBean> caseMonthCountStatisticsBeans =
            vesselCaseRepository.findCaseMonthStaticsCount();
        if (caseMonthCountStatisticsBeans.size() > 0) {
            statisticsCaseMonthCountRepository.deleteAllInBatch();

            for (CaseMonthCountStatisticsBean caseMonthCountStatisticsBean : caseMonthCountStatisticsBeans) {
                if (caseMonthCountStatisticsBean.getYear() != null &&  caseMonthCountStatisticsBean.getMonth() != null) {
                    StatisticsCaseMonthCount statisticsCaseMonthCount = new StatisticsCaseMonthCount();
                    statisticsCaseMonthCount.setCaseYear(new Integer(caseMonthCountStatisticsBean.getYear()));
                    statisticsCaseMonthCount.setCaseMonth(new Integer(caseMonthCountStatisticsBean.getMonth()));
                    statisticsCaseMonthCount.setInsuranceType(caseMonthCountStatisticsBean.getType());
                    statisticsCaseMonthCount.setCaseCount(caseMonthCountStatisticsBean.getCount().intValue());
                    statisticsCaseMonthCount.setUpdateTime(Instant.now());
                    statisticsCaseMonthCountRepository.save(statisticsCaseMonthCount);
                }
            }
        }
    }

    @Transactional(readOnly = true)
    public List<CaseMonthCountStatisticsBean> findCasMonthStaticsCount(String startYear, String endYear, String startMonth, String endMonth) {
        log.debug("find by startYear : {}, endYear: {}", startYear, endYear);

        List<StatisticsCaseMonthCount> statisticsCaseMonthCounts =
            statisticsCaseMonthCountRepository.findAllByCaseYearBetweenAndCaseMonthBetweenOrderByCaseYearAscCaseMonthAsc(
                new Integer(startYear), new Integer(endYear), new Integer(startMonth), new Integer(endMonth));

        List<CaseMonthCountStatisticsBean> caseMonthCountStatisticsBeans = new ArrayList<>();
        for (StatisticsCaseMonthCount statisticsCaseMonthCount : statisticsCaseMonthCounts) {
            caseMonthCountStatisticsBeans.add( new CaseMonthCountStatisticsBean(
                statisticsCaseMonthCount.getCaseYear().toString(),
                statisticsCaseMonthCount.getCaseMonth().toString(),
                statisticsCaseMonthCount.getInsuranceType(),
                statisticsCaseMonthCount.getCaseCount().longValue()
            ));
        }

        return caseMonthCountStatisticsBeans;
    }

}
