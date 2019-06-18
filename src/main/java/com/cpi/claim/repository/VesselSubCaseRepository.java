package com.cpi.claim.repository;

import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VesselSubCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VesselSubCaseRepository extends JpaRepository<VesselSubCase, Long>, JpaSpecificationExecutor<VesselSubCase> {

}
