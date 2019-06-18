package com.cpi.claim.repository;

import com.cpi.claim.domain.ClaimBillFinanceType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimBillFinanceType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimBillFinanceTypeRepository extends JpaRepository<ClaimBillFinanceType, Long>, JpaSpecificationExecutor<ClaimBillFinanceType> {

}
