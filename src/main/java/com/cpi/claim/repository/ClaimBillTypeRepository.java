package com.cpi.claim.repository;

import com.cpi.claim.domain.ClaimBillType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ClaimBillType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimBillTypeRepository extends JpaRepository<ClaimBillType, Long>, JpaSpecificationExecutor<ClaimBillType> {

}
