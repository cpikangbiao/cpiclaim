package com.cpi.claim.repository;

import com.cpi.claim.domain.CasePayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CasePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasePaymentRepository extends JpaRepository<CasePayment, Long>, JpaSpecificationExecutor<CasePayment> {

}
