package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.FeeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FeeType and its DTO FeeTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FeeTypeMapper extends EntityMapper<FeeTypeDTO, FeeType> {



    default FeeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        FeeType feeType = new FeeType();
        feeType.setId(id);
        return feeType;
    }
}
