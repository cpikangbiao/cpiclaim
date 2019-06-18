package com.cpi.claim.repository;

import com.cpi.claim.domain.GuaranteeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GuaranteeType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GuaranteeTypeRepository extends JpaRepository<GuaranteeType, Long>, JpaSpecificationExecutor<GuaranteeType> {

}
