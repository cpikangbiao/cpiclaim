package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseClaimBill;
import com.cpi.claim.domain.CaseClaimBillPrintLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseClaimBillPrintLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseClaimBillPrintLogRepository extends JpaRepository<CaseClaimBillPrintLog, Long>, JpaSpecificationExecutor<CaseClaimBillPrintLog> {

    Page<CaseClaimBillPrintLog> findAllByCaseClaimBill(CaseClaimBill caseClaimBill, Pageable pageable);
}
