package com.cpi.claim.repository;

import com.cpi.claim.domain.StatisticsCaseMonthCount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StatisticsCaseMonthCount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatisticsCaseMonthCountRepository extends JpaRepository<StatisticsCaseMonthCount, Long> {

}
