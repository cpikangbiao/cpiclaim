package com.cpi.claim.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.cpi.claim.domain.CaseSettlementMode} entity.
 */
public class CaseSettlementModeDTO implements Serializable {

    private Long id;

    private Integer sortNum;

    @NotNull
    private String settlementModeName;


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

    public String getSettlementModeName() {
        return settlementModeName;
    }

    public void setSettlementModeName(String settlementModeName) {
        this.settlementModeName = settlementModeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CaseSettlementModeDTO caseSettlementModeDTO = (CaseSettlementModeDTO) o;
        if (caseSettlementModeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), caseSettlementModeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CaseSettlementModeDTO{" +
            "id=" + getId() +
            ", sortNum=" + getSortNum() +
            ", settlementModeName='" + getSettlementModeName() + "'" +
            "}";
    }
}
