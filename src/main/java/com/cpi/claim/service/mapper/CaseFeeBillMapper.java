package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseFeeBillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseFeeBill and its DTO CaseFeeBillDTO.
 */
@Mapper(componentModel = "spring", uses = {CaseFeeMapper.class, CaseClaimBillMapper.class})
public interface CaseFeeBillMapper extends EntityMapper<CaseFeeBillDTO, CaseFeeBill> {

    @Mapping(source = "caseFee.id", target = "caseFeeId")
    @Mapping(source = "caseClaimBill.id", target = "caseClaimBillId")
    @Mapping(source = "caseClaimBill.claimBillCode", target = "caseClaimBillClaimBillCode")
    @Mapping(source = "writeOffBill.id", target = "writeOffBillId")
    @Mapping(source = "writeOffBill.claimBillCode", target = "writeOffBillClaimBillCode")
    CaseFeeBillDTO toDto(CaseFeeBill caseFeeBill);

    @Mapping(source = "caseFeeId", target = "caseFee")
    @Mapping(source = "caseClaimBillId", target = "caseClaimBill")
    @Mapping(source = "writeOffBillId", target = "writeOffBill")
    CaseFeeBill toEntity(CaseFeeBillDTO caseFeeBillDTO);

    default CaseFeeBill fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseFeeBill caseFeeBill = new CaseFeeBill();
        caseFeeBill.setId(id);
        return caseFeeBill;
    }
}
