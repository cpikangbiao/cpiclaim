package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseStatusType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseStatusType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseStatusTypeRepository extends JpaRepository<CaseStatusType, Long>, JpaSpecificationExecutor<CaseStatusType> {

}
