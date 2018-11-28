package com.cpi.claim.repository;

import com.cpi.claim.domain.CaseStatusType;
import com.cpi.claim.domain.CpiInsuranceType;
import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean;
import com.cpi.claim.repository.bean.CaseYearCountStatisticsBean;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data  repository for the VesselCase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VesselCaseRepository extends JpaRepository<VesselCase, Long>, JpaSpecificationExecutor<VesselCase> {

    @Query(value = "SELECT "
        + " new com.cpi.claim.repository.bean.CaseYearCountStatisticsBean( c.caseYear, t.cpiInsuranceTypeName, COUNT(c) ) "
        + " FROM VesselCase c JOIN c.cpiInsuranceType t "
        + " GROUP BY c.caseYear, t.cpiInsuranceTypeName")
    List<CaseYearCountStatisticsBean> findCaseYearStaticsCount();


    @Query(value = "SELECT "
        + " new com.cpi.claim.repository.bean.CaseMonthCountStatisticsBean( DATE_FORMAT(c.caseDate, '%Y'), DATE_FORMAT(c.caseDate, '%m'), t.cpiInsuranceTypeName, COUNT(c) ) "
        + " FROM VesselCase c JOIN c.cpiInsuranceType t "
        + " GROUP BY DATE_FORMAT(c.caseDate, '%Y'), DATE_FORMAT(c.caseDate, '%m'), t.cpiInsuranceTypeName")
    List<CaseMonthCountStatisticsBean> findCaseMonthStaticsCount();

    @Query(value = "SELECT c "
        + " FROM VesselCase c"
        + " WHERE c.insuredVesselId IN :insuredVesselIds "
        + "  AND c.cpiInsuranceType = :cpiInsuranceType "
        + "  AND c.caseStatus = :caseStatus "
        + "  AND c.caseDate >= :caseDateFrom "
        + "  AND c.caseDate <= :caseDateTo "
        + "  AND c.registeredDate >= :registeredDateFrom "
        + "  AND c.registeredDate <= :registeredDateTo "
        + "  AND c.closeDate >= :closeDateFrom "
        + "  AND c.closeDate <= :closeDateTo "
        )
    List<VesselCase> findAllCaseForStatics(
        @Param("insuredVesselIds") List<Long> insuredVesselIds,
        @Param("cpiInsuranceType") CpiInsuranceType cpiInsuranceType,
        @Param("caseStatus") CaseStatusType caseStatus,
        @Param("caseDateFrom") Instant caseDateFrom,
        @Param("caseDateTo") Instant caseDateTo,
        @Param("registeredDateFrom") Instant registeredDateFrom,
        @Param("registeredDateTo") Instant registeredDateTo,
        @Param("closeDateFrom") Instant closeDateFrom,
        @Param("closeDateTo") Instant closeDateTo
    );

}
