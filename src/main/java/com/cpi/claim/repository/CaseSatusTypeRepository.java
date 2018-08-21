package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseSatusType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseSatusType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseSatusTypeRepository extends JpaRepository<CaseSatusType, Long>, JpaSpecificationExecutor<CaseSatusType> {

}
