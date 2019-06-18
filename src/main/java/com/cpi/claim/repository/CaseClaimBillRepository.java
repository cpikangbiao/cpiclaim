package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillRepository extends JpaRepository<CaseClaimBill, Long>, JpaSpecificationExecutor<CaseClaimBill> {

}
