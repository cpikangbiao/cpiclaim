package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.ClaimBillFinanceType} entity.
 */
public class ClaimBillFinanceTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String claimBillFinanceTypeName;


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

    public String getClaimBillFinanceTypeName() {
        return claimBillFinanceTypeName;
    }

    public void setClaimBillFinanceTypeName(String claimBillFinanceTypeName) {
        this.claimBillFinanceTypeName = claimBillFinanceTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimBillFinanceTypeDTO claimBillFinanceTypeDTO = (ClaimBillFinanceTypeDTO) o;
        if (claimBillFinanceTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimBillFinanceTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimBillFinanceTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillFinanceTypeName='" + getClaimBillFinanceTypeName() + "'" +
            "}";
    }
}
