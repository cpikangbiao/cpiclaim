package com.cpi.claim.repository;

import com.cpi.claim.domain.CasePayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CasePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasePaymentRepository extends JpaRepository<CasePayment, Long>, JpaSpecificationExecutor<CasePayment> {

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CasePayment cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);
}
