package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CasePaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CasePayment} and its DTO {@link CasePaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentTypeMapper.class, VesselSubCaseMapper.class, CreditorMapper.class})
public interface CasePaymentMapper extends EntityMapper<CasePaymentDTO, CasePayment> {

    @Mapping(source = "paymentType.id", target = "paymentTypeId")
    @Mapping(source = "paymentType.paymentTypeName", target = "paymentTypePaymentTypeName")
    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "creditor.id", target = "creditorId")
    @Mapping(source = "creditor.creditorCode", target = "creditorCreditorCode")
    CasePaymentDTO toDto(CasePayment casePayment);

    @Mapping(source = "paymentTypeId", target = "paymentType")
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
