package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseCloseLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseCloseLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseCloseLogRepository extends JpaRepository<CaseCloseLog, Long>, JpaSpecificationExecutor<CaseCloseLog> {

    Page<CaseCloseLog> findAllByVesselCaseIdOrderByOperateDateDesc(Long vesselCaseId, Pageable pageable);
}
