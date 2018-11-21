package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseFee;
import com.cpi.claim.domain.CasePayment;
import com.cpi.claim.domain.PaymentType;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CasePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasePaymentRepository extends JpaRepository<CasePayment, Long>, JpaSpecificationExecutor<CasePayment> {

    List<CasePayment> findAllBySubcaseVesselCaseId(Long vesselCaseId);

    Page<CasePayment> findAllBySubcaseVesselCaseId(Long vesselCaseId, Pageable pageable);


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM CasePayment cc "
        + " WHERE cc.subcase.id = :subcaseId ")
    Integer findMaxNumberIdBySubcaseId(@Param("subcaseId") Long subcaseId);

    List<CasePayment> findAllBySubcaseAndPaymentType(VesselSubCase vesselSubCase, PaymentType paymentType);
}
