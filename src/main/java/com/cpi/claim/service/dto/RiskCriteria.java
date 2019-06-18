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

package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.cpi.claim.domain.Risk} entity. This class is used
 * in {@link com.cpi.claim.web.rest.RiskResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /risks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RiskCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter sortNum;

    private BooleanFilter tclType;

    private BooleanFilter piType;

    private StringFilter riskName;

    private StringFilter riskNameChinese;

    private StringFilter riskNameEnglish;

    private StringFilter riskNameEnglishAbbr;

    private LongFilter riskGroupId;

    public RiskCriteria(){
    }

    public RiskCriteria(RiskCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.sortNum = other.sortNum == null ? null : other.sortNum.copy();
        this.tclType = other.tclType == null ? null : other.tclType.copy();
        this.piType = other.piType == null ? null : other.piType.copy();
        this.riskName = other.riskName == null ? null : other.riskName.copy();
        this.riskNameChinese = other.riskNameChinese == null ? null : other.riskNameChinese.copy();
        this.riskNameEnglish = other.riskNameEnglish == null ? null : other.riskNameEnglish.copy();
        this.riskNameEnglishAbbr = other.riskNameEnglishAbbr == null ? null : other.riskNameEnglishAbbr.copy();
        this.riskGroupId = other.riskGroupId == null ? null : other.riskGroupId.copy();
    }

    @Override
    public RiskCriteria copy() {
        return new RiskCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getSortNum() {
        return sortNum;
    }

    public void setSortNum(IntegerFilter sortNum) {
        this.sortNum = sortNum;
    }

    public BooleanFilter getTclType() {
        return tclType;
    }

    public void setTclType(BooleanFilter tclType) {
        this.tclType = tclType;
    }

    public BooleanFilter getPiType() {
        return piType;
    }

    public void setPiType(BooleanFilter piType) {
        this.piType = piType;
    }

    public StringFilter getRiskName() {
        return riskName;
    }

    public void setRiskName(StringFilter riskName) {
        this.riskName = riskName;
    }

    public StringFilter getRiskNameChinese() {
        return riskNameChinese;
    }

    public void setRiskNameChinese(StringFilter riskNameChinese) {
        this.riskNameChinese = riskNameChinese;
    }

    public StringFilter getRiskNameEnglish() {
        return riskNameEnglish;
    }

    public void setRiskNameEnglish(StringFilter riskNameEnglish) {
        this.riskNameEnglish = riskNameEnglish;
    }

    public StringFilter getRiskNameEnglishAbbr() {
        return riskNameEnglishAbbr;
    }

    public void setRiskNameEnglishAbbr(StringFilter riskNameEnglishAbbr) {
        this.riskNameEnglishAbbr = riskNameEnglishAbbr;
    }

    public LongFilter getRiskGroupId() {
        return riskGroupId;
    }

    public void setRiskGroupId(LongFilter riskGroupId) {
        this.riskGroupId = riskGroupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RiskCriteria that = (RiskCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(sortNum, that.sortNum) &&
            Objects.equals(tclType, that.tclType) &&
            Objects.equals(piType, that.piType) &&
            Objects.equals(riskName, that.riskName) &&
            Objects.equals(riskNameChinese, that.riskNameChinese) &&
            Objects.equals(riskNameEnglish, that.riskNameEnglish) &&
            Objects.equals(riskNameEnglishAbbr, that.riskNameEnglishAbbr) &&
            Objects.equals(riskGroupId, that.riskGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        sortNum,
        tclType,
        piType,
        riskName,
        riskNameChinese,
        riskNameEnglish,
        riskNameEnglishAbbr,
        riskGroupId
        );
    }

    @Override
    public String toString() {
        return "RiskCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (sortNum != null ? "sortNum=" + sortNum + ", " : "") +
                (tclType != null ? "tclType=" + tclType + ", " : "") +
                (piType != null ? "piType=" + piType + ", " : "") +
                (riskName != null ? "riskName=" + riskName + ", " : "") +
                (riskNameChinese != null ? "riskNameChinese=" + riskNameChinese + ", " : "") +
                (riskNameEnglish != null ? "riskNameEnglish=" + riskNameEnglish + ", " : "") +
                (riskNameEnglishAbbr != null ? "riskNameEnglishAbbr=" + riskNameEnglishAbbr + ", " : "") +
                (riskGroupId != null ? "riskGroupId=" + riskGroupId + ", " : "") +
            "}";
    }

}
