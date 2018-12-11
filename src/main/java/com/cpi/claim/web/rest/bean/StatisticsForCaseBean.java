/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:40
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
