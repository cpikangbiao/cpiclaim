package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.ClaimBillStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClaimBillStatus} and its DTO {@link ClaimBillStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ClaimBillStatusMapper extends EntityMapper<ClaimBillStatusDTO, ClaimBillStatus> {



    default ClaimBillStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        ClaimBillStatus claimBillStatus = new ClaimBillStatus();
        claimBillStatus.setId(id);
        return claimBillStatus;
    }
}
