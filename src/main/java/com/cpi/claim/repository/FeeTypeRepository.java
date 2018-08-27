package com.cpi.claim.repository;

import com.cpi.claim.domain.FeeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeTypeRepository extends JpaRepository<FeeType, Long>, JpaSpecificationExecutor<FeeType> {

}
