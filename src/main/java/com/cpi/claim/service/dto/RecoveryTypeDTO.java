package com.cpi.claim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RecoveryType entity.
 */
public class RecoveryTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String recoveryTypeName;

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

    public String getRecoveryTypeName() {
        return recoveryTypeName;
    }

    public void setRecoveryTypeName(String recoveryTypeName) {
        this.recoveryTypeName = recoveryTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RecoveryTypeDTO recoveryTypeDTO = (RecoveryTypeDTO) o;
        if (recoveryTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recoveryTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RecoveryTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", recoveryTypeName='" + getRecoveryTypeName() + "'" +
            "}";
    }
}
