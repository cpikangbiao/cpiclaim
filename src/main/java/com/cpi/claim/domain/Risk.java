package com.cpi.claim.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Risk.
 */
@Entity
@Table(name = "risk")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Risk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sort_num")
    private Integer sortNum;

    @Column(name = "tcl_type")
    private Boolean tclType;

    @Column(name = "pi_type")
    private Boolean piType;

    @Column(name = "risk_name")
    private String riskName;

    @Column(name = "risk_name_chinese")
    private String riskNameChinese;

    @Column(name = "risk_name_english")
    private String riskNameEnglish;

    @Column(name = "risk_name_english_abbr")
    private String riskNameEnglishAbbr;

    @ManyToOne
    @JsonIgnoreProperties("risks")
    private RiskGroup riskGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public Risk sortNum(Integer sortNum) {
        this.sortNum = sortNum;
        return this;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Boolean isTclType() {
        return tclType;
    }

    public Risk tclType(Boolean tclType) {
        this.tclType = tclType;
        return this;
    }

    public void setTclType(Boolean tclType) {
        this.tclType = tclType;
    }

    public Boolean isPiType() {
        return piType;
    }

    public Risk piType(Boolean piType) {
        this.piType = piType;
        return this;
    }

    public void setPiType(Boolean piType) {
        this.piType = piType;
    }

    public String getRiskName() {
        return riskName;
    }

    public Risk riskName(String riskName) {
        this.riskName = riskName;
        return this;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskNameChinese() {
        return riskNameChinese;
    }

    public Risk riskNameChinese(String riskNameChinese) {
        this.riskNameChinese = riskNameChinese;
        return this;
    }

    public void setRiskNameChinese(String riskNameChinese) {
        this.riskNameChinese = riskNameChinese;
    }

    public String getRiskNameEnglish() {
        return riskNameEnglish;
    }

    public Risk riskNameEnglish(String riskNameEnglish) {
        this.riskNameEnglish = riskNameEnglish;
        return this;
    }

    public void setRiskNameEnglish(String riskNameEnglish) {
        this.riskNameEnglish = riskNameEnglish;
    }

    public String getRiskNameEnglishAbbr() {
        return riskNameEnglishAbbr;
    }

    public Risk riskNameEnglishAbbr(String riskNameEnglishAbbr) {
        this.riskNameEnglishAbbr = riskNameEnglishAbbr;
        return this;
    }

    public void setRiskNameEnglishAbbr(String riskNameEnglishAbbr) {
        this.riskNameEnglishAbbr = riskNameEnglishAbbr;
    }

    public RiskGroup getRiskGroup() {
        return riskGroup;
    }

    public Risk riskGroup(RiskGroup riskGroup) {
        this.riskGroup = riskGroup;
        return this;
    }

    public void setRiskGroup(RiskGroup riskGroup) {
        this.riskGroup = riskGroup;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Risk)) {
            return false;
        }
        return id != null && id.equals(((Risk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Risk{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", tclType='" + isTclType() + "'" +
            ", piType='" + isPiType() + "'" +
            ", riskName='" + getRiskName() + "'" +
            ", riskNameChinese='" + getRiskNameChinese() + "'" +
            ", riskNameEnglish='" + getRiskNameEnglish() + "'" +
            ", riskNameEnglishAbbr='" + getRiskNameEnglishAbbr() + "'" +
            "}";
    }
}
