package com.cpi.claim.repository;

import com.cpi.claim.domain.StatisticsCaseYearCount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the StatisticsCaseYearCount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsCaseYearCountRepository extends JpaRepository<StatisticsCaseYearCount, Long> {

    List<StatisticsCaseYearCount> findAllByCaseYearInOrderByCaseYear(List<String> years);
}
