package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseRecovery;
import com.cpi.claim.domain.CaseRecoveryBill;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseRecoveryBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRecoveryBillRepository extends JpaRepository<CaseRecoveryBill, Long>, JpaSpecificationExecutor<CaseRecoveryBill> {

    @Query("SELECT i.caseClaimBill "
        + " FROM CaseRecoveryBill i "
        + " WHERE i.caseRecovery = :caseRecovery ")
    List<CaseClaimBill> findAllCaseClaimBillByCaseRecovery(@Param("caseRecovery") CaseRecovery caseRecovery);

    List<CaseRecoveryBill> findAllByCaseRecovery(CaseRecovery caseRecovery);
}
