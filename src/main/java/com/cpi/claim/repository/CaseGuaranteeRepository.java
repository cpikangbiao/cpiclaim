package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseGuarantee;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseGuarantee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseGuaranteeRepository extends JpaRepository<CaseGuarantee, Long>, JpaSpecificationExecutor<CaseGuarantee> {

}
