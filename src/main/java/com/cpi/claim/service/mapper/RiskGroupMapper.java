package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.RiskGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RiskGroup and its DTO RiskGroupDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RiskGroupMapper extends EntityMapper<RiskGroupDTO, RiskGroup> {



    default RiskGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        RiskGroup riskGroup = new RiskGroup();
        riskGroup.setId(id);
        return riskGroup;
    }
}
