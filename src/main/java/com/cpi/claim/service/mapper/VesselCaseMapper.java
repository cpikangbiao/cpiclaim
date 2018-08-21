package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.VesselCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VesselCase and its DTO VesselCaseDTO.
 */
@Mapper(componentModel = "spring", uses = {CpiInsuranceTypeMapper.class, CaseSatusTypeMapper.class, CaseSettlementModeMapper.class})
public interface VesselCaseMapper extends EntityMapper<VesselCaseDTO, VesselCase> {

    @Mapping(source = "cpiInsuranceType.id", target = "cpiInsuranceTypeId")
    @Mapping(source = "cpiInsuranceType.cpiInsuranceTypeName", target = "cpiInsuranceTypeCpiInsuranceTypeName")
    @Mapping(source = "caseStatus.id", target = "caseStatusId")
    @Mapping(source = "caseStatus.caseStatusName", target = "caseStatusCaseStatusName")
    @Mapping(source = "settlementMode.id", target = "settlementModeId")
    @Mapping(source = "settlementMode.settlementModeName", target = "settlementModeSettlementModeName")
    VesselCaseDTO toDto(VesselCase vesselCase);

    @Mapping(source = "cpiInsuranceTypeId", target = "cpiInsuranceType")
    @Mapping(source = "caseStatusId", target = "caseStatus")
    @Mapping(source = "settlementModeId", target = "settlementMode")
    VesselCase toEntity(VesselCaseDTO vesselCaseDTO);

    default VesselCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        VesselCase vesselCase = new VesselCase();
        vesselCase.setId(id);
        return vesselCase;
    }
}
