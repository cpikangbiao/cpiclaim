package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaim;
import com.cpi.claim.domain.CaseFee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseFeeRepository extends JpaRepository<CaseFee, Long>, JpaSpecificationExecutor<CaseFee> {

    List<CaseFee> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseFee> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseFee cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
