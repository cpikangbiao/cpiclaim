package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseGuarantee;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseGuarantee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseGuaranteeRepository extends JpaRepository<CaseGuarantee, Long>, JpaSpecificationExecutor<CaseGuarantee> {

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseGuarantee cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
