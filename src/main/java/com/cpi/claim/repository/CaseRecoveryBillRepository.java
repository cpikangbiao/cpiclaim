package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseRecoveryBill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseRecoveryBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryBillRepository extends JpaRepository<CaseRecoveryBill, Long>, JpaSpecificationExecutor<CaseRecoveryBill> {

}
