package com.cpi.claim.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A CaseGuarantee.
 */
@Entity
@Table(name = "case_guarantee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseGuarantee extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vessel_id")
    private Long vesselId;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "arrest_date")
    private Instant arrestDate;

    @Column(name = "arrest_port")
    private Long arrestPort;

    @Column(name = "arrest_vessel_id")
    private Long arrestVesselId;

    @Column(name = "arrest_vessel_name")
    private String arrestVesselName;

    @Column(name = "bill_of_lading")
    private String billOfLading;

    @Column(name = "bill_lading_date")
    private Instant billLadingDate;

    @Column(name = "nature_of_claim")
    private String natureOfClaim;

    @Column(name = "guarantee")
    private String guarantee;

    @Column(name = "guarantee_date")
    private Instant guaranteeDate;

    @Column(name = "guarantee_currency")
    private Long guaranteeCurrency;

    @Column(name = "guarantee_rate")
    private Double guaranteeRate;

    @Column(name = "guarantee_amount", precision = 10, scale = 2)
    private BigDecimal guaranteeAmount;

    @Column(name = "guarantee_amount_dollar", precision = 10, scale = 2)
    private BigDecimal guaranteeAmountDollar;

    @Column(name = "guarantee_to")
    private String guaranteeTo;

    @Column(name = "guarantee_to_address")
    private String guaranteeToAddress;

    @Column(name = "guarantee_no")
    private String guaranteeNo;

    @Column(name = "guarantee_fee", precision = 10, scale = 2)
    private BigDecimal guaranteeFee;

    @Column(name = "guarantee_other")
    private String guaranteeOther;

    @Column(name = "cancel_date")
    private Instant cancelDate;

    @Column(name = "con_guarantee")
    private String conGuarantee;

    @Column(name = "con_guarantee_date")
    private Instant conGuaranteeDate;

    @Column(name = "con_guarantee_currency")
    private Long conGuaranteeCurrency;

    @Column(name = "con_guarantee_rate")
    private Double conGuaranteeRate;

    @Column(name = "con_guarantee_amount", precision = 10, scale = 2)
    private BigDecimal conGuaranteeAmount;

    @Column(name = "con_guarantee_amount_dollar", precision = 10, scale = 2)
    private BigDecimal conGuaranteeAmountDollar;

    @Column(name = "con_guarantee_no")
    private String conGuaranteeNo;

    @Column(name = "con_guarantee_to")
    private String conGuaranteeTo;

    @Column(name = "con_guarantee_cancel_date")
    private Instant conGuaranteeCancelDate;

    @Lob
    @Column(name = "remark")
    private String remark;

    @Column(name = "release_date")
    private Instant releaseDate;

    @Column(name = "register_user_id")
    private Long registerUserId;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("")
    private GuaranteeType guaranteeType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private GuaranteeType conGuaranteeType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVesselId() {
        return vesselId;
    }

    public CaseGuarantee vesselId(Long vesselId) {
        this.vesselId = vesselId;
        return this;
    }

    public void setVesselId(Long vesselId) {
        this.vesselId = vesselId;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseGuarantee numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Instant getArrestDate() {
        return arrestDate;
    }

    public CaseGuarantee arrestDate(Instant arrestDate) {
        this.arrestDate = arrestDate;
        return this;
    }

    public void setArrestDate(Instant arrestDate) {
        this.arrestDate = arrestDate;
    }

    public Long getArrestPort() {
        return arrestPort;
    }

    public CaseGuarantee arrestPort(Long arrestPort) {
        this.arrestPort = arrestPort;
        return this;
    }

    public void setArrestPort(Long arrestPort) {
        this.arrestPort = arrestPort;
    }

    public Long getArrestVesselId() {
        return arrestVesselId;
    }

    public CaseGuarantee arrestVesselId(Long arrestVesselId) {
        this.arrestVesselId = arrestVesselId;
        return this;
    }

    public void setArrestVesselId(Long arrestVesselId) {
        this.arrestVesselId = arrestVesselId;
    }

    public String getArrestVesselName() {
        return arrestVesselName;
    }

    public CaseGuarantee arrestVesselName(String arrestVesselName) {
        this.arrestVesselName = arrestVesselName;
        return this;
    }

    public void setArrestVesselName(String arrestVesselName) {
        this.arrestVesselName = arrestVesselName;
    }

    public String getBillOfLading() {
        return billOfLading;
    }

    public CaseGuarantee billOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
        return this;
    }

    public void setBillOfLading(String billOfLading) {
        this.billOfLading = billOfLading;
    }

    public Instant getBillLadingDate() {
        return billLadingDate;
    }

    public CaseGuarantee billLadingDate(Instant billLadingDate) {
        this.billLadingDate = billLadingDate;
        return this;
    }

    public void setBillLadingDate(Instant billLadingDate) {
        this.billLadingDate = billLadingDate;
    }

    public String getNatureOfClaim() {
        return natureOfClaim;
    }

    public CaseGuarantee natureOfClaim(String natureOfClaim) {
        this.natureOfClaim = natureOfClaim;
        return this;
    }

    public void setNatureOfClaim(String natureOfClaim) {
        this.natureOfClaim = natureOfClaim;
    }

    public String getGuarantee() {
        return guarantee;
    }

    public CaseGuarantee guarantee(String guarantee) {
        this.guarantee = guarantee;
        return this;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee;
    }

    public Instant getGuaranteeDate() {
        return guaranteeDate;
    }

    public CaseGuarantee guaranteeDate(Instant guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
        return this;
    }

    public void setGuaranteeDate(Instant guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
    }

    public Long getGuaranteeCurrency() {
        return guaranteeCurrency;
    }

    public CaseGuarantee guaranteeCurrency(Long guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
        return this;
    }

    public void setGuaranteeCurrency(Long guaranteeCurrency) {
        this.guaranteeCurrency = guaranteeCurrency;
    }

    public Double getGuaranteeRate() {
        return guaranteeRate;
    }

    public CaseGuarantee guaranteeRate(Double guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
        return this;
    }

    public void setGuaranteeRate(Double guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public BigDecimal getGuaranteeAmount() {
        return guaranteeAmount;
    }

    public CaseGuarantee guaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
        return this;
    }

    public void setGuaranteeAmount(BigDecimal guaranteeAmount) {
        this.guaranteeAmount = guaranteeAmount;
    }

    public BigDecimal getGuaranteeAmountDollar() {
        return guaranteeAmountDollar;
    }

    public CaseGuarantee guaranteeAmountDollar(BigDecimal guaranteeAmountDollar) {
        this.guaranteeAmountDollar = guaranteeAmountDollar;
        return this;
    }

    public void setGuaranteeAmountDollar(BigDecimal guaranteeAmountDollar) {
        this.guaranteeAmountDollar = guaranteeAmountDollar;
    }

    public String getGuaranteeTo() {
        return guaranteeTo;
    }

    public CaseGuarantee guaranteeTo(String guaranteeTo) {
        this.guaranteeTo = guaranteeTo;
        return this;
    }

    public void setGuaranteeTo(String guaranteeTo) {
        this.guaranteeTo = guaranteeTo;
    }

    public String getGuaranteeToAddress() {
        return guaranteeToAddress;
    }

    public CaseGuarantee guaranteeToAddress(String guaranteeToAddress) {
        this.guaranteeToAddress = guaranteeToAddress;
        return this;
    }

    public void setGuaranteeToAddress(String guaranteeToAddress) {
        this.guaranteeToAddress = guaranteeToAddress;
    }

    public String getGuaranteeNo() {
        return guaranteeNo;
    }

    public CaseGuarantee guaranteeNo(String guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
        return this;
    }

    public void setGuaranteeNo(String guaranteeNo) {
        this.guaranteeNo = guaranteeNo;
    }

    public BigDecimal getGuaranteeFee() {
        return guaranteeFee;
    }

    public CaseGuarantee guaranteeFee(BigDecimal guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
        return this;
    }

    public void setGuaranteeFee(BigDecimal guaranteeFee) {
        this.guaranteeFee = guaranteeFee;
    }

    public String getGuaranteeOther() {
        return guaranteeOther;
    }

    public CaseGuarantee guaranteeOther(String guaranteeOther) {
        this.guaranteeOther = guaranteeOther;
        return this;
    }

    public void setGuaranteeOther(String guaranteeOther) {
        this.guaranteeOther = guaranteeOther;
    }

    public Instant getCancelDate() {
        return cancelDate;
    }

    public CaseGuarantee cancelDate(Instant cancelDate) {
        this.cancelDate = cancelDate;
        return this;
    }

    public void setCancelDate(Instant cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getConGuarantee() {
        return conGuarantee;
    }

    public CaseGuarantee conGuarantee(String conGuarantee) {
        this.conGuarantee = conGuarantee;
        return this;
    }

    public void setConGuarantee(String conGuarantee) {
        this.conGuarantee = conGuarantee;
    }

    public Instant getConGuaranteeDate() {
        return conGuaranteeDate;
    }

    public CaseGuarantee conGuaranteeDate(Instant conGuaranteeDate) {
        this.conGuaranteeDate = conGuaranteeDate;
        return this;
    }

    public void setConGuaranteeDate(Instant conGuaranteeDate) {
        this.conGuaranteeDate = conGuaranteeDate;
    }

    public Long getConGuaranteeCurrency() {
        return conGuaranteeCurrency;
    }

    public CaseGuarantee conGuaranteeCurrency(Long conGuaranteeCurrency) {
        this.conGuaranteeCurrency = conGuaranteeCurrency;
        return this;
    }

    public void setConGuaranteeCurrency(Long conGuaranteeCurrency) {
        this.conGuaranteeCurrency = conGuaranteeCurrency;
    }

    public Double getConGuaranteeRate() {
        return conGuaranteeRate;
    }

    public CaseGuarantee conGuaranteeRate(Double conGuaranteeRate) {
        this.conGuaranteeRate = conGuaranteeRate;
        return this;
    }

    public void setConGuaranteeRate(Double conGuaranteeRate) {
        this.conGuaranteeRate = conGuaranteeRate;
    }

    public BigDecimal getConGuaranteeAmount() {
        return conGuaranteeAmount;
    }

    public CaseGuarantee conGuaranteeAmount(BigDecimal conGuaranteeAmount) {
        this.conGuaranteeAmount = conGuaranteeAmount;
        return this;
    }

    public void setConGuaranteeAmount(BigDecimal conGuaranteeAmount) {
        this.conGuaranteeAmount = conGuaranteeAmount;
    }

    public BigDecimal getConGuaranteeAmountDollar() {
        return conGuaranteeAmountDollar;
    }

    public CaseGuarantee conGuaranteeAmountDollar(BigDecimal conGuaranteeAmountDollar) {
        this.conGuaranteeAmountDollar = conGuaranteeAmountDollar;
        return this;
    }

    public void setConGuaranteeAmountDollar(BigDecimal conGuaranteeAmountDollar) {
        this.conGuaranteeAmountDollar = conGuaranteeAmountDollar;
    }

    public String getConGuaranteeNo() {
        return conGuaranteeNo;
    }

    public CaseGuarantee conGuaranteeNo(String conGuaranteeNo) {
        this.conGuaranteeNo = conGuaranteeNo;
        return this;
    }

    public void setConGuaranteeNo(String conGuaranteeNo) {
        this.conGuaranteeNo = conGuaranteeNo;
    }

    public String getConGuaranteeTo() {
        return conGuaranteeTo;
    }

    public CaseGuarantee conGuaranteeTo(String conGuaranteeTo) {
        this.conGuaranteeTo = conGuaranteeTo;
        return this;
    }

    public void setConGuaranteeTo(String conGuaranteeTo) {
        this.conGuaranteeTo = conGuaranteeTo;
    }

    public Instant getConGuaranteeCancelDate() {
        return conGuaranteeCancelDate;
    }

    public CaseGuarantee conGuaranteeCancelDate(Instant conGuaranteeCancelDate) {
        this.conGuaranteeCancelDate = conGuaranteeCancelDate;
        return this;
    }

    public void setConGuaranteeCancelDate(Instant conGuaranteeCancelDate) {
        this.conGuaranteeCancelDate = conGuaranteeCancelDate;
    }

    public String getRemark() {
        return remark;
    }

    public CaseGuarantee remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Instant getReleaseDate() {
        return releaseDate;
    }

    public CaseGuarantee releaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public void setReleaseDate(Instant releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public CaseGuarantee registerUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
        return this;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseGuarantee subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public GuaranteeType getGuaranteeType() {
        return guaranteeType;
    }

    public CaseGuarantee guaranteeType(GuaranteeType guaranteeType) {
        this.guaranteeType = guaranteeType;
        return this;
    }

    public void setGuaranteeType(GuaranteeType guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public GuaranteeType getConGuaranteeType() {
        return conGuaranteeType;
    }

    public CaseGuarantee conGuaranteeType(GuaranteeType guaranteeType) {
        this.conGuaranteeType = guaranteeType;
        return this;
    }

    public void setConGuaranteeType(GuaranteeType guaranteeType) {
        this.conGuaranteeType = guaranteeType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CaseGuarantee caseGuarantee = (CaseGuarantee) o;
        if (caseGuarantee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseGuarantee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseGuarantee{" +
            "id=" + getId() +
            ", vesselId=" + getVesselId() +
            ", numberId=" + getNumberId() +
            ", arrestDate='" + getArrestDate() + "'" +
            ", arrestPort=" + getArrestPort() +
            ", arrestVesselId=" + getArrestVesselId() +
            ", arrestVesselName='" + getArrestVesselName() + "'" +
            ", billOfLading='" + getBillOfLading() + "'" +
            ", billLadingDate='" + getBillLadingDate() + "'" +
            ", natureOfClaim='" + getNatureOfClaim() + "'" +
            ", guarantee='" + getGuarantee() + "'" +
            ", guaranteeDate='" + getGuaranteeDate() + "'" +
            ", guaranteeCurrency=" + getGuaranteeCurrency() +
            ", guaranteeRate=" + getGuaranteeRate() +
            ", guaranteeAmount=" + getGuaranteeAmount() +
            ", guaranteeAmountDollar=" + getGuaranteeAmountDollar() +
            ", guaranteeTo='" + getGuaranteeTo() + "'" +
            ", guaranteeToAddress='" + getGuaranteeToAddress() + "'" +
            ", guaranteeNo='" + getGuaranteeNo() + "'" +
            ", guaranteeFee=" + getGuaranteeFee() +
            ", guaranteeOther='" + getGuaranteeOther() + "'" +
            ", cancelDate='" + getCancelDate() + "'" +
            ", conGuarantee='" + getConGuarantee() + "'" +
            ", conGuaranteeDate='" + getConGuaranteeDate() + "'" +
            ", conGuaranteeCurrency=" + getConGuaranteeCurrency() +
            ", conGuaranteeRate=" + getConGuaranteeRate() +
            ", conGuaranteeAmount=" + getConGuaranteeAmount() +
            ", conGuaranteeAmountDollar=" + getConGuaranteeAmountDollar() +
            ", conGuaranteeNo='" + getConGuaranteeNo() + "'" +
            ", conGuaranteeTo='" + getConGuaranteeTo() + "'" +
            ", conGuaranteeCancelDate='" + getConGuaranteeCancelDate() + "'" +
            ", remark='" + getRemark() + "'" +
            ", releaseDate='" + getReleaseDate() + "'" +
            ", registerUserId=" + getRegisterUserId() +
            "}";
    }
}
