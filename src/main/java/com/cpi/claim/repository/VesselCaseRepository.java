package com.cpi.claim.repository;

import com.cpi.claim.domain.VesselCase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VesselCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VesselCaseRepository extends JpaRepository<VesselCase, Long>, JpaSpecificationExecutor<VesselCase> {

}
