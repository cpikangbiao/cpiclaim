package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseRegisterLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseRegisterLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseRegisterLogRepository extends JpaRepository<CaseRegisterLog, Long>, JpaSpecificationExecutor<CaseRegisterLog> {

    Page<CaseRegisterLog> findAllByVesselCaseIdOrderByAssignTimeDesc(Long vesselCaseId,  Pageable pageable);
}
