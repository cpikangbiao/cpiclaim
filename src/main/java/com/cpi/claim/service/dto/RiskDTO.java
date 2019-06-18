package com.cpi.claim.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Risk entity.
 */
public class RiskDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    private Boolean tclType;

    private Boolean piType;

    private String riskName;

    private String riskNameChinese;

    private String riskNameEnglish;

    private String riskNameEnglishAbbr;

    private Long riskGroupId;

    private String riskGroupRiskGroupName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Boolean isTclType() {
        return tclType;
    }

    public void setTclType(Boolean tclType) {
        this.tclType = tclType;
    }

    public Boolean isPiType() {
        return piType;
    }

    public void setPiType(Boolean piType) {
        this.piType = piType;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskNameChinese() {
        return riskNameChinese;
    }

    public void setRiskNameChinese(String riskNameChinese) {
        this.riskNameChinese = riskNameChinese;
    }

    public String getRiskNameEnglish() {
        return riskNameEnglish;
    }

    public void setRiskNameEnglish(String riskNameEnglish) {
        this.riskNameEnglish = riskNameEnglish;
    }

    public String getRiskNameEnglishAbbr() {
        return riskNameEnglishAbbr;
    }

    public void setRiskNameEnglishAbbr(String riskNameEnglishAbbr) {
        this.riskNameEnglishAbbr = riskNameEnglishAbbr;
    }

    public Long getRiskGroupId() {
        return riskGroupId;
    }

    public void setRiskGroupId(Long riskGroupId) {
        this.riskGroupId = riskGroupId;
    }

    public String getRiskGroupRiskGroupName() {
        return riskGroupRiskGroupName;
    }

    public void setRiskGroupRiskGroupName(String riskGroupRiskGroupName) {
        this.riskGroupRiskGroupName = riskGroupRiskGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RiskDTO riskDTO = (RiskDTO) o;
        if (riskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", tclType='" + isTclType() + "'" +
            ", piType='" + isPiType() + "'" +
            ", riskName='" + getRiskName() + "'" +
            ", riskNameChinese='" + getRiskNameChinese() + "'" +
            ", riskNameEnglish='" + getRiskNameEnglish() + "'" +
            ", riskNameEnglishAbbr='" + getRiskNameEnglishAbbr() + "'" +
            ", riskGroup=" + getRiskGroupId() +
            ", riskGroup='" + getRiskGroupRiskGroupName() + "'" +
            "}";
    }
}
