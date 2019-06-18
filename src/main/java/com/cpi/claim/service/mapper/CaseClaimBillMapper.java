package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimBillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseClaimBill and its DTO CaseClaimBillDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class, ClaimBillStatusMapper.class, ClaimBillTypeMapper.class, ClaimBillFinanceTypeMapper.class, CreditorMapper.class})
public interface CaseClaimBillMapper extends EntityMapper<CaseClaimBillDTO, CaseClaimBill> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "claimBillStatus.id", target = "claimBillStatusId")
    @Mapping(source = "claimBillStatus.claimBillStatusName", target = "claimBillStatusClaimBillStatusName")
    @Mapping(source = "claimBillType.id", target = "claimBillTypeId")
    @Mapping(source = "claimBillType.claimBillTypeName", target = "claimBillTypeClaimBillTypeName")
    @Mapping(source = "claimBillFinanceType.id", target = "claimBillFinanceTypeId")
    @Mapping(source = "claimBillFinanceType.claimBillFinanceTypeName", target = "claimBillFinanceTypeClaimBillFinanceTypeName")
    @Mapping(source = "creditor.id", target = "creditorId")
    @Mapping(source = "creditor.creditorName", target = "creditorCreditorName")
    CaseClaimBillDTO toDto(CaseClaimBill caseClaimBill);

    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "claimBillStatusId", target = "claimBillStatus")
    @Mapping(source = "claimBillTypeId", target = "claimBillType")
    @Mapping(source = "claimBillFinanceTypeId", target = "claimBillFinanceType")
    @Mapping(source = "creditorId", target = "creditor")
    CaseClaimBill toEntity(CaseClaimBillDTO caseClaimBillDTO);

    default CaseClaimBill fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaimBill caseClaimBill = new CaseClaimBill();
        caseClaimBill.setId(id);
        return caseClaimBill;
    }
}
