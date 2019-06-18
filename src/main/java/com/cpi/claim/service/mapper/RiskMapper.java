package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.RiskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Risk} and its DTO {@link RiskDTO}.
 */
@Mapper(componentModel = "spring", uses = {RiskGroupMapper.class})
public interface RiskMapper extends EntityMapper<RiskDTO, Risk> {

    @Mapping(source = "riskGroup.id", target = "riskGroupId")
    @Mapping(source = "riskGroup.riskGroupName", target = "riskGroupRiskGroupName")
    RiskDTO toDto(Risk risk);

    @Mapping(source = "riskGroupId", target = "riskGroup")
    Risk toEntity(RiskDTO riskDTO);

    default Risk fromId(Long id) {
        if (id == null) {
            return null;
        }
        Risk risk = new Risk();
        risk.setId(id);
        return risk;
    }
}
