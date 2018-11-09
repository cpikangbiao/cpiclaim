package com.cpi.claim.service.dto.uw;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the Member entity.
 */
public class MemberDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4)
    private String memberYear;

    @NotNull
    private Long company;

    @NotNull
    private Instant entryDate;

    @NotNull
    private Instant withdrawDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberYear() {
        return memberYear;
    }

    public void setMemberYear(String memberYear) {
        this.memberYear = memberYear;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    public Instant getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Instant entryDate) {
        this.entryDate = entryDate;
    }

    public Instant getWithdrawDate() {
        return withdrawDate;
    }

    public void setWithdrawDate(Instant withdrawDate) {
        this.withdrawDate = withdrawDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberDTO memberDTO = (MemberDTO) o;
        if (memberDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), memberDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
            "id=" + getId() +
            ", memberYear='" + getMemberYear() + "'" +
            ", company=" + getCompany() +
            ", entryDate='" + getEntryDate() + "'" +
            ", withdrawDate='" + getWithdrawDate() + "'" +
            "}";
    }
}
