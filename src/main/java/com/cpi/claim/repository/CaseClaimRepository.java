package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the CaseClaim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimRepository extends JpaRepository<CaseClaim, Long>, JpaSpecificationExecutor<CaseClaim> {

    List<CaseClaim> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseClaim> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseClaim cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
