package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseAssignLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseAssignLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseAssignLogRepository extends JpaRepository<CaseAssignLog, Long>, JpaSpecificationExecutor<CaseAssignLog> {

}
