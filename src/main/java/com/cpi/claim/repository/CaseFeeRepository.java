package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseFeeRepository extends JpaRepository<CaseFee, Long>, JpaSpecificationExecutor<CaseFee> {

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseFee cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
