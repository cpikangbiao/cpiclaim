package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBillPrintLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBillPrintLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillPrintLogRepository extends JpaRepository<CaseClaimBillPrintLog, Long>, JpaSpecificationExecutor<CaseClaimBillPrintLog> {

}
