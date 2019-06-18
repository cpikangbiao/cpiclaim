package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBillApprovalLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillApprovalLogRepository extends JpaRepository<CaseClaimBillApprovalLog, Long>, JpaSpecificationExecutor<CaseClaimBillApprovalLog> {

}
