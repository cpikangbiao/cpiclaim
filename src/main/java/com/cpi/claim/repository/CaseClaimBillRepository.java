package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseEstimate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CaseClaimBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillRepository extends JpaRepository<CaseClaimBill, Long>, JpaSpecificationExecutor<CaseClaimBill> {

    List<CaseClaimBill> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CaseClaimBill> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);

    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CaseClaimBill cc "
        + " WHERE cc.memberYear = :memberYear ")
    Integer findMaxNumberIdByYear(@Param("memberYear") String memberYear);
}
