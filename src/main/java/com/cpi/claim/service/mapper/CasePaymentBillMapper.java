package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CasePaymentBillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CasePaymentBill} and its DTO {@link CasePaymentBillDTO}.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class, CaseClaimBillMapper.class})
public interface CasePaymentBillMapper extends EntityMapper<CasePaymentBillDTO, CasePaymentBill> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    @Mapping(source = "writeOffBill.id", target = "writeOffBillId")
    @Mapping(source = "writeOffBill.claimBillCode", target = "writeOffBillClaimBillCode")
    CasePaymentBillDTO toDto(CasePaymentBill casePaymentBill);

    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    @Mapping(source = "writeOffBillId", target = "writeOffBill")
    CasePaymentBill toEntity(CasePaymentBillDTO casePaymentBillDTO);

    default CasePaymentBill fromId(Long id) {
        if (id == null) {
            return null;
        }
        CasePaymentBill casePaymentBill = new CasePaymentBill();
        casePaymentBill.setId(id);
        return casePaymentBill;
    }
}
