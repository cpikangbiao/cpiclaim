package com.cpi.claim.repository;

import com.cpi.claim.domain.StatisticsCaseYearCount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatisticsCaseYearCount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsCaseYearCountRepository extends JpaRepository<StatisticsCaseYearCount, Long> {

}
