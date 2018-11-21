package com.cpi.claim.repository;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the VesselSubCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VesselSubCaseRepository extends JpaRepository<VesselSubCase, Long>, JpaSpecificationExecutor<VesselSubCase> {

    @Query("SELECT COALESCE(MAX(c.numberId), 0) "
        + " FROM VesselSubCase c "
        + " WHERE c.vesselCase.id = :vesselCaseId ")
    Integer findMaxNumberIdByVesselCaseId(@Param("vesselCaseId") Long vesselCaseId);

    List<VesselSubCase> findAllByVesselCase(VesselCase vesselCase);
}
