package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseGuarantee;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseGuarantee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseGuaranteeRepository extends JpaRepository<CaseGuarantee, Long>, JpaSpecificationExecutor<CaseGuarantee> {

    List<CaseGuarantee> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseGuarantee> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseGuarantee cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);

    List<CaseGuarantee> findAllBySubcase(VesselSubCase vesselSubCase);
}
