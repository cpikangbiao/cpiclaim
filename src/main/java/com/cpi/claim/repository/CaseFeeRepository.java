package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseFeeRepository extends JpaRepository<CaseFee, Long>, JpaSpecificationExecutor<CaseFee> {

}
