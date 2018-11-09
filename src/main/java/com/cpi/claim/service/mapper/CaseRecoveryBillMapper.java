package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseRecoveryBillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseRecoveryBill and its DTO CaseRecoveryBillDTO.
 */
@Mapper(componentModel = "spring", uses = {CaseRecoveryMapper.class, CaseClaimBillMapper.class})
public interface CaseRecoveryBillMapper extends EntityMapper<CaseRecoveryBillDTO, CaseRecoveryBill> {

    @Mapping(source = "caseRecovery.id", target = "caseRecoveryId")
    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    @Mapping(source = "writeOffBill.id", target = "writeOffBillId")
    @Mapping(source = "writeOffBill.claimBillCode", target = "writeOffBillClaimBillCode")
    CaseRecoveryBillDTO toDto(CaseRecoveryBill caseRecoveryBill);

    @Mapping(source = "caseRecoveryId", target = "caseRecovery")
    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    @Mapping(source = "writeOffBillId", target = "writeOffBill")
    CaseRecoveryBill toEntity(CaseRecoveryBillDTO caseRecoveryBillDTO);

    default CaseRecoveryBill fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseRecoveryBill caseRecoveryBill = new CaseRecoveryBill();
        caseRecoveryBill.setId(id);
        return caseRecoveryBill;
    }
}
