package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.GuaranteeType} entity.
 */
public class GuaranteeTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String guaranteeTypeName;


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

    public String getGuaranteeTypeName() {
        return guaranteeTypeName;
    }

    public void setGuaranteeTypeName(String guaranteeTypeName) {
        this.guaranteeTypeName = guaranteeTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GuaranteeTypeDTO guaranteeTypeDTO = (GuaranteeTypeDTO) o;
        if (guaranteeTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), guaranteeTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GuaranteeTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", guaranteeTypeName='" + getGuaranteeTypeName() + "'" +
            "}";
    }
}
