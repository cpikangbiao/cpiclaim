package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.ClaimBillTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ClaimBillType and its DTO ClaimBillTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimBillTypeMapper extends EntityMapper<ClaimBillTypeDTO, ClaimBillType> {



    default ClaimBillType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimBillType claimBillType = new ClaimBillType();
        claimBillType.setId(id);
        return claimBillType;
    }
}
