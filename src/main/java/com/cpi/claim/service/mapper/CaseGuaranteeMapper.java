package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseGuaranteeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseGuarantee and its DTO CaseGuaranteeDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class, GuaranteeTypeMapper.class})
public interface CaseGuaranteeMapper extends EntityMapper<CaseGuaranteeDTO, CaseGuarantee> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "guaranteeType.id", target = "guaranteeTypeId")
    @Mapping(source = "guaranteeType.guaranteeTypeName", target = "guaranteeTypeGuaranteeTypeName")
    @Mapping(source = "conGuaranteeType.id", target = "conGuaranteeTypeId")
    @Mapping(source = "conGuaranteeType.guaranteeTypeName", target = "conGuaranteeTypeGuaranteeTypeName")
    CaseGuaranteeDTO toDto(CaseGuarantee caseGuarantee);

    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "guaranteeTypeId", target = "guaranteeType")
    @Mapping(source = "conGuaranteeTypeId", target = "conGuaranteeType")
    CaseGuarantee toEntity(CaseGuaranteeDTO caseGuaranteeDTO);

    default CaseGuarantee fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseGuarantee caseGuarantee = new CaseGuarantee();
        caseGuarantee.setId(id);
        return caseGuarantee;
    }
}
