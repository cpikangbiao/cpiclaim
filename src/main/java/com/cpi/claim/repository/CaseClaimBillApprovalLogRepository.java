package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillApprovalLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBillApprovalLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillApprovalLogRepository extends JpaRepository<CaseClaimBillApprovalLog, Long>, JpaSpecificationExecutor<CaseClaimBillApprovalLog> {

    Page<CaseClaimBillApprovalLog> findAllByCaseClaimBill(CaseClaimBill caseClaimBill, Pageable pageable);
}
