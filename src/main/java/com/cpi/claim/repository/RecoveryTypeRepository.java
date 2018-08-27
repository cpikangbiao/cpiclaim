package com.cpi.claim.repository;

import com.cpi.claim.domain.RecoveryType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RecoveryType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecoveryTypeRepository extends JpaRepository<RecoveryType, Long>, JpaSpecificationExecutor<RecoveryType> {

}
