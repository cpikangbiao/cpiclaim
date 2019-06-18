package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.CpiInsuranceType} entity.
 */
public class CpiInsuranceTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String cpiInsuranceTypeName;


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

    public String getCpiInsuranceTypeName() {
        return cpiInsuranceTypeName;
    }

    public void setCpiInsuranceTypeName(String cpiInsuranceTypeName) {
        this.cpiInsuranceTypeName = cpiInsuranceTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CpiInsuranceTypeDTO cpiInsuranceTypeDTO = (CpiInsuranceTypeDTO) o;
        if (cpiInsuranceTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cpiInsuranceTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CpiInsuranceTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", cpiInsuranceTypeName='" + getCpiInsuranceTypeName() + "'" +
            "}";
    }
}
