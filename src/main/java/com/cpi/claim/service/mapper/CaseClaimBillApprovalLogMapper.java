package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimBillApprovalLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseClaimBillApprovalLog} and its DTO {@link CaseClaimBillApprovalLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {CaseClaimBillMapper.class})
public interface CaseClaimBillApprovalLogMapper extends EntityMapper<CaseClaimBillApprovalLogDTO, CaseClaimBillApprovalLog> {

    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    CaseClaimBillApprovalLogDTO toDto(CaseClaimBillApprovalLog caseClaimBillApprovalLog);

    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    CaseClaimBillApprovalLog toEntity(CaseClaimBillApprovalLogDTO caseClaimBillApprovalLogDTO);

    default CaseClaimBillApprovalLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaimBillApprovalLog caseClaimBillApprovalLog = new CaseClaimBillApprovalLog();
        caseClaimBillApprovalLog.setId(id);
        return caseClaimBillApprovalLog;
    }
}
