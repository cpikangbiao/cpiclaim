package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.ClaimBillFinanceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimBillFinanceType} and its DTO {@link ClaimBillFinanceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimBillFinanceTypeMapper extends EntityMapper<ClaimBillFinanceTypeDTO, ClaimBillFinanceType> {



    default ClaimBillFinanceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimBillFinanceType claimBillFinanceType = new ClaimBillFinanceType();
        claimBillFinanceType.setId(id);
        return claimBillFinanceType;
    }
}
