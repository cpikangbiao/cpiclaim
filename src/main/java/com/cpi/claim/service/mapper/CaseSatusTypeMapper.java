package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseSatusTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseSatusType and its DTO CaseSatusTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaseSatusTypeMapper extends EntityMapper<CaseSatusTypeDTO, CaseSatusType> {



    default CaseSatusType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseSatusType caseSatusType = new CaseSatusType();
        caseSatusType.setId(id);
        return caseSatusType;
    }
}
