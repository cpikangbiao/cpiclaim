package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.ClaimBillType} entity.
 */
public class ClaimBillTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String claimBillTypeName;


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

    public String getClaimBillTypeName() {
        return claimBillTypeName;
    }

    public void setClaimBillTypeName(String claimBillTypeName) {
        this.claimBillTypeName = claimBillTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimBillTypeDTO claimBillTypeDTO = (ClaimBillTypeDTO) o;
        if (claimBillTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimBillTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimBillTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillTypeName='" + getClaimBillTypeName() + "'" +
            "}";
    }
}
