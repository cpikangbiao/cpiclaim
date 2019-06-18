package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseSettlementMode;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseSettlementMode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseSettlementModeRepository extends JpaRepository<CaseSettlementMode, Long>, JpaSpecificationExecutor<CaseSettlementMode> {

}
