package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimBillPrintLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseClaimBillPrintLog} and its DTO {@link CaseClaimBillPrintLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {CaseClaimBillMapper.class})
public interface CaseClaimBillPrintLogMapper extends EntityMapper<CaseClaimBillPrintLogDTO, CaseClaimBillPrintLog> {

    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    CaseClaimBillPrintLogDTO toDto(CaseClaimBillPrintLog caseClaimBillPrintLog);

    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    CaseClaimBillPrintLog toEntity(CaseClaimBillPrintLogDTO caseClaimBillPrintLogDTO);

    default CaseClaimBillPrintLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaimBillPrintLog caseClaimBillPrintLog = new CaseClaimBillPrintLog();
        caseClaimBillPrintLog.setId(id);
        return caseClaimBillPrintLog;
    }
}
