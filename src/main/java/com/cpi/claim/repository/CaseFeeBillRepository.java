package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CaseFeeBill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseFeeBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseFeeBillRepository extends JpaRepository<CaseFeeBill, Long>, JpaSpecificationExecutor<CaseFeeBill> {

    List<CaseFeeBill> findAllByCaseFee(CaseFee caseFee);
}
