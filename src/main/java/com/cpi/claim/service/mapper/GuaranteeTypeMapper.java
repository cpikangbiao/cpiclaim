package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.GuaranteeTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GuaranteeType} and its DTO {@link GuaranteeTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GuaranteeTypeMapper extends EntityMapper<GuaranteeTypeDTO, GuaranteeType> {



    default GuaranteeType fromId(Long id) {
        if (id == null) {
            return null;
        }
        GuaranteeType guaranteeType = new GuaranteeType();
        guaranteeType.setId(id);
        return guaranteeType;
    }
}
