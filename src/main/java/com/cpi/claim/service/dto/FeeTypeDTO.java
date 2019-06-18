package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.FeeType} entity.
 */
public class FeeTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String feeTypeName;


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

    public String getFeeTypeName() {
        return feeTypeName;
    }

    public void setFeeTypeName(String feeTypeName) {
        this.feeTypeName = feeTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FeeTypeDTO feeTypeDTO = (FeeTypeDTO) o;
        if (feeTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), feeTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FeeTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", feeTypeName='" + getFeeTypeName() + "'" +
            "}";
    }
}
