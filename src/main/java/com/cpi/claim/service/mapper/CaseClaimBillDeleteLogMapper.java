package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseClaimBillDeleteLog and its DTO CaseClaimBillDeleteLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaseClaimBillDeleteLogMapper extends EntityMapper<CaseClaimBillDeleteLogDTO, CaseClaimBillDeleteLog> {



    default CaseClaimBillDeleteLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = new CaseClaimBillDeleteLog();
        caseClaimBillDeleteLog.setId(id);
        return caseClaimBillDeleteLog;
    }
}
