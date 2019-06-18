package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.ClaimBillStatus} entity.
 */
public class ClaimBillStatusDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String claimBillStatusName;


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

    public String getClaimBillStatusName() {
        return claimBillStatusName;
    }

    public void setClaimBillStatusName(String claimBillStatusName) {
        this.claimBillStatusName = claimBillStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClaimBillStatusDTO claimBillStatusDTO = (ClaimBillStatusDTO) o;
        if (claimBillStatusDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), claimBillStatusDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClaimBillStatusDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", claimBillStatusName='" + getClaimBillStatusName() + "'" +
            "}";
    }
}
