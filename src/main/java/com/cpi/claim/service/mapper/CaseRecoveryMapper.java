package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseRecoveryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseRecovery and its DTO CaseRecoveryDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class, RecoveryTypeMapper.class, CreditorMapper.class})
public interface CaseRecoveryMapper extends EntityMapper<CaseRecoveryDTO, CaseRecovery> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "recoveryType.id", target = "recoveryTypeId")
    @Mapping(source = "recoveryType.recoveryTypeName", target = "recoveryTypeRecoveryTypeName")
    @Mapping(source = "creditor.id", target = "creditorId")
    @Mapping(source = "creditor.creditorCode", target = "creditorCreditorCode")
    CaseRecoveryDTO toDto(CaseRecovery caseRecovery);

    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "recoveryTypeId", target = "recoveryType")
    @Mapping(source = "creditorId", target = "creditor")
    CaseRecovery toEntity(CaseRecoveryDTO caseRecoveryDTO);

    default CaseRecovery fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseRecovery caseRecovery = new CaseRecovery();
        caseRecovery.setId(id);
        return caseRecovery;
    }
}
