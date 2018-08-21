package com.cpi.claim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CaseSatusType entity.
 */
public class CaseSatusTypeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String caseStatusName;

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

    public String getCaseStatusName() {
        return caseStatusName;
    }

    public void setCaseStatusName(String caseStatusName) {
        this.caseStatusName = caseStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseSatusTypeDTO caseSatusTypeDTO = (CaseSatusTypeDTO) o;
        if (caseSatusTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseSatusTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseSatusTypeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", caseStatusName='" + getCaseStatusName() + "'" +
            "}";
    }
}
