package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseRecovery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseRecovery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryRepository extends JpaRepository<CaseRecovery, Long>, JpaSpecificationExecutor<CaseRecovery> {

}
