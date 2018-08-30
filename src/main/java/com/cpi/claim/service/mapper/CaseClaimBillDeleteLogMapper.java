package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimBillDeleteLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseClaimBillDeleteLog and its DTO CaseClaimBillDeleteLogDTO.
 */
@Mapper(componentModel = "spring", uses = {CaseClaimBillMapper.class})
public interface CaseClaimBillDeleteLogMapper extends EntityMapper<CaseClaimBillDeleteLogDTO, CaseClaimBillDeleteLog> {

    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    CaseClaimBillDeleteLogDTO toDto(CaseClaimBillDeleteLog caseClaimBillDeleteLog);

    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    CaseClaimBillDeleteLog toEntity(CaseClaimBillDeleteLogDTO caseClaimBillDeleteLogDTO);

    default CaseClaimBillDeleteLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaimBillDeleteLog caseClaimBillDeleteLog = new CaseClaimBillDeleteLog();
        caseClaimBillDeleteLog.setId(id);
        return caseClaimBillDeleteLog;
    }
}
