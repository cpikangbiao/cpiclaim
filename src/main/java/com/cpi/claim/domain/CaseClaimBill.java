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
 * A CaseClaimBill.
 */
@Entity
@Table(name = "case_claim_bill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseClaimBill extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "claim_bill_code")
    private String claimBillCode;

    @Column(name = "claim_bill_date")
    private Instant claimBillDate;

    @Column(name = "register_user_id")
    private Long registerUserId;

    @Column(name = "client_bill_no")
    private String clientBillNo;

    @Column(name = "bill_description")
    private String billDescription;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "member_year")
    private String memberYear;

    @Column(name = "number_id")
    private Integer numberId;

    @Column(name = "claim_amount_currency")
    private Long claimAmountCurrency;

    @Column(name = "claim_amount", precision = 10, scale = 2)
    private BigDecimal claimAmount;

    @Column(name = "deductible", precision = 10, scale = 2)
    private BigDecimal deductible;

    @Column(name = "deductible_currency")
    private Long deductibleCurrency;

    @Column(name = "deductible_currency_rate")
    private Double deductibleCurrencyRate;

    @Column(name = "deductible_dollar", precision = 10, scale = 2)
    private BigDecimal deductibleDollar;

    @Column(name = "bill_currency")
    private Long billCurrency;

    @Column(name = "bill_amount", precision = 10, scale = 2)
    private BigDecimal billAmount;

    @Column(name = "bill_currency_rate")
    private Double billCurrencyRate;

    @Column(name = "bill_amount_dollar", precision = 10, scale = 2)
    private BigDecimal billAmountDollar;

    @Lob
    @Column(name = "remark")
    private String remark;

    @Column(name = "is_signed")
    private Boolean isSigned;

    @Column(name = "sign_user")
    private Long signUser;

    @Column(name = "sign_date")
    private Instant signDate;

    @Column(name = "process_id")
    private Long processId;

    @Column(name = "print_number")
    private Integer printNumber;

    @ManyToOne
    @JsonIgnoreProperties("")
    private VesselSubCase subcase;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ClaimBillStatus claimBillStatus;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ClaimBillType claimBillType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private ClaimBillFinanceType claimBillFinanceType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Creditor creditor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimBillCode() {
        return claimBillCode;
    }

    public CaseClaimBill claimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
        return this;
    }

    public void setClaimBillCode(String claimBillCode) {
        this.claimBillCode = claimBillCode;
    }

    public Instant getClaimBillDate() {
        return claimBillDate;
    }

    public CaseClaimBill claimBillDate(Instant claimBillDate) {
        this.claimBillDate = claimBillDate;
        return this;
    }

    public void setClaimBillDate(Instant claimBillDate) {
        this.claimBillDate = claimBillDate;
    }

    public Long getRegisterUserId() {
        return registerUserId;
    }

    public CaseClaimBill registerUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
        return this;
    }

    public void setRegisterUserId(Long registerUserId) {
        this.registerUserId = registerUserId;
    }

    public String getClientBillNo() {
        return clientBillNo;
    }

    public CaseClaimBill clientBillNo(String clientBillNo) {
        this.clientBillNo = clientBillNo;
        return this;
    }

    public void setClientBillNo(String clientBillNo) {
        this.clientBillNo = clientBillNo;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public CaseClaimBill billDescription(String billDescription) {
        this.billDescription = billDescription;
        return this;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public Instant getDueDate() {
        return dueDate;
    }

    public CaseClaimBill dueDate(Instant dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public void setDueDate(Instant dueDate) {
        this.dueDate = dueDate;
    }

    public String getMemberYear() {
        return memberYear;
    }

    public CaseClaimBill memberYear(String memberYear) {
        this.memberYear = memberYear;
        return this;
    }

    public void setMemberYear(String memberYear) {
        this.memberYear = memberYear;
    }

    public Integer getNumberId() {
        return numberId;
    }

    public CaseClaimBill numberId(Integer numberId) {
        this.numberId = numberId;
        return this;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Long getClaimAmountCurrency() {
        return claimAmountCurrency;
    }

    public CaseClaimBill claimAmountCurrency(Long claimAmountCurrency) {
        this.claimAmountCurrency = claimAmountCurrency;
        return this;
    }

    public void setClaimAmountCurrency(Long claimAmountCurrency) {
        this.claimAmountCurrency = claimAmountCurrency;
    }

    public BigDecimal getClaimAmount() {
        return claimAmount;
    }

    public CaseClaimBill claimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
        return this;
    }

    public void setClaimAmount(BigDecimal claimAmount) {
        this.claimAmount = claimAmount;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public CaseClaimBill deductible(BigDecimal deductible) {
        this.deductible = deductible;
        return this;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public Long getDeductibleCurrency() {
        return deductibleCurrency;
    }

    public CaseClaimBill deductibleCurrency(Long deductibleCurrency) {
        this.deductibleCurrency = deductibleCurrency;
        return this;
    }

    public void setDeductibleCurrency(Long deductibleCurrency) {
        this.deductibleCurrency = deductibleCurrency;
    }

    public Double getDeductibleCurrencyRate() {
        return deductibleCurrencyRate;
    }

    public CaseClaimBill deductibleCurrencyRate(Double deductibleCurrencyRate) {
        this.deductibleCurrencyRate = deductibleCurrencyRate;
        return this;
    }

    public void setDeductibleCurrencyRate(Double deductibleCurrencyRate) {
        this.deductibleCurrencyRate = deductibleCurrencyRate;
    }

    public BigDecimal getDeductibleDollar() {
        return deductibleDollar;
    }

    public CaseClaimBill deductibleDollar(BigDecimal deductibleDollar) {
        this.deductibleDollar = deductibleDollar;
        return this;
    }

    public void setDeductibleDollar(BigDecimal deductibleDollar) {
        this.deductibleDollar = deductibleDollar;
    }

    public Long getBillCurrency() {
        return billCurrency;
    }

    public CaseClaimBill billCurrency(Long billCurrency) {
        this.billCurrency = billCurrency;
        return this;
    }

    public void setBillCurrency(Long billCurrency) {
        this.billCurrency = billCurrency;
    }

    public BigDecimal getBillAmount() {
        return billAmount;
    }

    public CaseClaimBill billAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
        return this;
    }

    public void setBillAmount(BigDecimal billAmount) {
        this.billAmount = billAmount;
    }

    public Double getBillCurrencyRate() {
        return billCurrencyRate;
    }

    public CaseClaimBill billCurrencyRate(Double billCurrencyRate) {
        this.billCurrencyRate = billCurrencyRate;
        return this;
    }

    public void setBillCurrencyRate(Double billCurrencyRate) {
        this.billCurrencyRate = billCurrencyRate;
    }

    public BigDecimal getBillAmountDollar() {
        return billAmountDollar;
    }

    public CaseClaimBill billAmountDollar(BigDecimal billAmountDollar) {
        this.billAmountDollar = billAmountDollar;
        return this;
    }

    public void setBillAmountDollar(BigDecimal billAmountDollar) {
        this.billAmountDollar = billAmountDollar;
    }

    public String getRemark() {
        return remark;
    }

    public CaseClaimBill remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean isIsSigned() {
        return isSigned;
    }

    public CaseClaimBill isSigned(Boolean isSigned) {
        this.isSigned = isSigned;
        return this;
    }

    public void setIsSigned(Boolean isSigned) {
        this.isSigned = isSigned;
    }

    public Long getSignUser() {
        return signUser;
    }

    public CaseClaimBill signUser(Long signUser) {
        this.signUser = signUser;
        return this;
    }

    public void setSignUser(Long signUser) {
        this.signUser = signUser;
    }

    public Instant getSignDate() {
        return signDate;
    }

    public CaseClaimBill signDate(Instant signDate) {
        this.signDate = signDate;
        return this;
    }

    public void setSignDate(Instant signDate) {
        this.signDate = signDate;
    }

    public Long getProcessId() {
        return processId;
    }

    public CaseClaimBill processId(Long processId) {
        this.processId = processId;
        return this;
    }

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Integer getPrintNumber() {
        return printNumber;
    }

    public CaseClaimBill printNumber(Integer printNumber) {
        this.printNumber = printNumber;
        return this;
    }

    public void setPrintNumber(Integer printNumber) {
        this.printNumber = printNumber;
    }

    public VesselSubCase getSubcase() {
        return subcase;
    }

    public CaseClaimBill subcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
        return this;
    }

    public void setSubcase(VesselSubCase vesselSubCase) {
        this.subcase = vesselSubCase;
    }

    public ClaimBillStatus getClaimBillStatus() {
        return claimBillStatus;
    }

    public CaseClaimBill claimBillStatus(ClaimBillStatus claimBillStatus) {
        this.claimBillStatus = claimBillStatus;
        return this;
    }

    public void setClaimBillStatus(ClaimBillStatus claimBillStatus) {
        this.claimBillStatus = claimBillStatus;
    }

    public ClaimBillType getClaimBillType() {
        return claimBillType;
    }

    public CaseClaimBill claimBillType(ClaimBillType claimBillType) {
        this.claimBillType = claimBillType;
        return this;
    }

    public void setClaimBillType(ClaimBillType claimBillType) {
        this.claimBillType = claimBillType;
    }

    public ClaimBillFinanceType getClaimBillFinanceType() {
        return claimBillFinanceType;
    }

    public CaseClaimBill claimBillFinanceType(ClaimBillFinanceType claimBillFinanceType) {
        this.claimBillFinanceType = claimBillFinanceType;
        return this;
    }

    public void setClaimBillFinanceType(ClaimBillFinanceType claimBillFinanceType) {
        this.claimBillFinanceType = claimBillFinanceType;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public CaseClaimBill creditor(Creditor creditor) {
        this.creditor = creditor;
        return this;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
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
        CaseClaimBill caseClaimBill = (CaseClaimBill) o;
        if (caseClaimBill.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseClaimBill.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseClaimBill{" +
            "id=" + getId() +
            ", claimBillCode='" + getClaimBillCode() + "'" +
            ", claimBillDate='" + getClaimBillDate() + "'" +
            ", registerUserId=" + getRegisterUserId() +
            ", clientBillNo='" + getClientBillNo() + "'" +
            ", billDescription='" + getBillDescription() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", memberYear='" + getMemberYear() + "'" +
            ", numberId=" + getNumberId() +
            ", claimAmountCurrency=" + getClaimAmountCurrency() +
            ", claimAmount=" + getClaimAmount() +
            ", deductible=" + getDeductible() +
            ", deductibleCurrency=" + getDeductibleCurrency() +
            ", deductibleCurrencyRate=" + getDeductibleCurrencyRate() +
            ", deductibleDollar=" + getDeductibleDollar() +
            ", billCurrency=" + getBillCurrency() +
            ", billAmount=" + getBillAmount() +
            ", billCurrencyRate=" + getBillCurrencyRate() +
            ", billAmountDollar=" + getBillAmountDollar() +
            ", remark='" + getRemark() + "'" +
            ", isSigned='" + isIsSigned() + "'" +
            ", signUser=" + getSignUser() +
            ", signDate='" + getSignDate() + "'" +
            ", processId=" + getProcessId() +
            ", printNumber=" + getPrintNumber() +
            "}";
    }
}
