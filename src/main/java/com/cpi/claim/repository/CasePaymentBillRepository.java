package com.cpi.claim.repository;

import com.cpi.claim.domain.CasePaymentBill;
import com.cpi.claim.domain.VesselSubCase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CasePaymentBill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasePaymentBillRepository extends JpaRepository<CasePaymentBill, Long>, JpaSpecificationExecutor<CasePaymentBill> {

    List<CasePaymentBill> findAllBySubcase(VesselSubCase vesselSubCase);
}
