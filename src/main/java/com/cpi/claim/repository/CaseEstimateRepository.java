package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.CaseEstimate;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseEstimate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseEstimateRepository extends JpaRepository<CaseEstimate, Long>, JpaSpecificationExecutor<CaseEstimate> {
    List<CaseEstimate> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseEstimate> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseEstimate cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);

    CaseEstimate findFirstBySubcaseOrderByEstimateDateDesc(VesselSubCase subcase);

}
