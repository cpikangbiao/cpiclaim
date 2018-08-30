package com.cpi.claim.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBillDeleteLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillDeleteLogRepository extends JpaRepository<CaseClaimBillDeleteLog, Long>, JpaSpecificationExecutor<CaseClaimBillDeleteLog> {

}
