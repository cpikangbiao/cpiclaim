package com.cpi.claim.repository;

import com.cpi.claim.domain.RiskGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RiskGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskGroupRepository extends JpaRepository<RiskGroup, Long>, JpaSpecificationExecutor<RiskGroup> {

}
