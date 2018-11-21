package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.domain.RecoveryType;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseRecovery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryRepository extends JpaRepository<CaseRecovery, Long>, JpaSpecificationExecutor<CaseRecovery> {

    List<CaseRecovery> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseRecovery> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseRecovery cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);

    List<CaseRecovery> findAllBySubcaseAndRecoveryType(VesselSubCase subCase, RecoveryType recoveryType);
}
