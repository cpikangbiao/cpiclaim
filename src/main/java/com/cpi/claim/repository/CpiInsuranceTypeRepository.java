package com.cpi.claim.repository;

import com.cpi.claim.domain.CpiInsuranceType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CpiInsuranceType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CpiInsuranceTypeRepository extends JpaRepository<CpiInsuranceType, Long>, JpaSpecificationExecutor<CpiInsuranceType> {

}
