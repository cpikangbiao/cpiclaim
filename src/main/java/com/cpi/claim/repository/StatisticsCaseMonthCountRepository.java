package com.cpi.claim.repository;

import com.cpi.claim.domain.StatisticsCaseMonthCount;
import com.cpi.claim.domain.StatisticsCaseYearCount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the StatisticsCaseMonthCount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsCaseMonthCountRepository extends JpaRepository<StatisticsCaseMonthCount, Long> {


    List<StatisticsCaseMonthCount> findAllByCaseYearBetweenAndCaseMonthBetweenOrderByCaseYearAscCaseMonthAsc(Integer startYear, Integer endYear, Integer startMonth, Integer endMonth);

}
