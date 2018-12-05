/**
 * Copyright (C); 2015-2018; XXX有限公司
 * FileName: StatisticsForCaseBean
 * Author:   admin
 * Date:     2018/12/4 15:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cpi.claim.web.rest.bean;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.time.Instant;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/12/4
 * @since 1.0.0
 */
public class StatisticsForCaseBean implements Serializable{

    private String fromYear;
    private  String endYear;
    private  Long companyId;
       private  Long cpiInsuranceTypeId;
       private  Long caseStatusId;
       private  Instant caseDateFrom;
       private  Instant caseDateTo;
       private  Instant registeredDateFrom;
       private  Instant registeredDateTo;
       private  Instant closeDateFrom;
       private  Instant closeDateTo;
       private  Integer language;
       private  Integer operateType;

    public StatisticsForCaseBean() {
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCpiInsuranceTypeId() {
        return cpiInsuranceTypeId;
    }

    public void setCpiInsuranceTypeId(Long cpiInsuranceTypeId) {
        this.cpiInsuranceTypeId = cpiInsuranceTypeId;
    }

    public Long getCaseStatusId() {
        return caseStatusId;
    }

    public void setCaseStatusId(Long caseStatusId) {
        this.caseStatusId = caseStatusId;
    }

    public Instant getCaseDateFrom() {
        return caseDateFrom;
    }

    public void setCaseDateFrom(Instant caseDateFrom) {
        this.caseDateFrom = caseDateFrom;
    }

    public Instant getCaseDateTo() {
        return caseDateTo;
    }

    public void setCaseDateTo(Instant caseDateTo) {
        this.caseDateTo = caseDateTo;
    }

    public Instant getRegisteredDateFrom() {
        return registeredDateFrom;
    }

    public void setRegisteredDateFrom(Instant registeredDateFrom) {
        this.registeredDateFrom = registeredDateFrom;
    }

    public Instant getRegisteredDateTo() {
        return registeredDateTo;
    }

    public void setRegisteredDateTo(Instant registeredDateTo) {
        this.registeredDateTo = registeredDateTo;
    }

    public Instant getCloseDateFrom() {
        return closeDateFrom;
    }

    public void setCloseDateFrom(Instant closeDateFrom) {
        this.closeDateFrom = closeDateFrom;
    }

    public Instant getCloseDateTo() {
        return closeDateTo;
    }

    public void setCloseDateTo(Instant closeDateTo) {
        this.closeDateTo = closeDateTo;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }
}
