package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaim;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaim entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimRepository extends JpaRepository<CaseClaim, Long>, JpaSpecificationExecutor<CaseClaim> {

}
