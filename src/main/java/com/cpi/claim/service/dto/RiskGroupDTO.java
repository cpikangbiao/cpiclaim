package com.cpi.claim.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RiskGroup entity.
 */
public class RiskGroupDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String riskGroupName;

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

    public String getRiskGroupName() {
        return riskGroupName;
    }

    public void setRiskGroupName(String riskGroupName) {
        this.riskGroupName = riskGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RiskGroupDTO riskGroupDTO = (RiskGroupDTO) o;
        if (riskGroupDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), riskGroupDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RiskGroupDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", riskGroupName='" + getRiskGroupName() + "'" +
            "}";
    }
}
