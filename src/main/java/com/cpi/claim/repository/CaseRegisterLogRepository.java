package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseRegisterLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseRegisterLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRegisterLogRepository extends JpaRepository<CaseRegisterLog, Long>, JpaSpecificationExecutor<CaseRegisterLog> {


}
