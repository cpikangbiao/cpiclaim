package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CasePaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CasePayment and its DTO CasePaymentDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class, CreditorMapper.class})
public interface CasePaymentMapper extends EntityMapper<CasePaymentDTO, CasePayment> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "creditor.id", target = "creditorId")
    @Mapping(source = "creditor.creditorCode", target = "creditorCreditorCode")
    CasePaymentDTO toDto(CasePayment casePayment);

    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "creditorId", target = "creditor")
    CasePayment toEntity(CasePaymentDTO casePaymentDTO);

    default CasePayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CasePayment casePayment = new CasePayment();
        casePayment.setId(id);
        return casePayment;
    }
}
