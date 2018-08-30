package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseCloseLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseCloseLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseCloseLogRepository extends JpaRepository<CaseCloseLog, Long>, JpaSpecificationExecutor<CaseCloseLog> {

}
