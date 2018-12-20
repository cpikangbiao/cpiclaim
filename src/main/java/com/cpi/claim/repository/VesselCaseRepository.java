/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

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


    @Query("SELECT COALESCE(MAX(cc.numberId), 0) "
        + " FROM VesselCase cc "
        + " WHERE cc.caseYear.id = :caseYear "
        + " AND cc.cpiInsuranceType.id = :cpiInsuranceTypeId ")
    Integer findMaxNumberIdByCaseYearAndCpiInsuranceTypeId(@Param("caseYear") String caseYear, @Param("cpiInsuranceTypeId") Long cpiInsuranceTypeId);


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

}
