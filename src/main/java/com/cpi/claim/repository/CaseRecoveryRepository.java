package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseRecovery;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseRecovery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryRepository extends JpaRepository<CaseRecovery, Long>, JpaSpecificationExecutor<CaseRecovery> {

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseRecovery cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
