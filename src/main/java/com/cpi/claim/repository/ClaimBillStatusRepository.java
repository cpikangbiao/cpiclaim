package com.cpi.claim.repository;

import com.cpi.claim.domain.ClaimBillStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimBillStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimBillStatusRepository extends JpaRepository<ClaimBillStatus, Long>, JpaSpecificationExecutor<ClaimBillStatus> {

}
