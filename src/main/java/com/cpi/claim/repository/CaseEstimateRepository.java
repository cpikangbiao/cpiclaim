package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseEstimate;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseEstimate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseEstimateRepository extends JpaRepository<CaseEstimate, Long>, JpaSpecificationExecutor<CaseEstimate> {


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseEstimate cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
