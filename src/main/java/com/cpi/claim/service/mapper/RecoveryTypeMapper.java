package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.RecoveryTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RecoveryType and its DTO RecoveryTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecoveryTypeMapper extends EntityMapper<RecoveryTypeDTO, RecoveryType> {



    default RecoveryType fromId(Long id) {
        if (id == null) {
            return null;
        }
        RecoveryType recoveryType = new RecoveryType();
        recoveryType.setId(id);
        return recoveryType;
    }
}
