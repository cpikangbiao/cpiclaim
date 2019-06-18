package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.VesselSubCaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link VesselSubCase} and its DTO {@link VesselSubCaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {VesselCaseMapper.class, RiskMapper.class})
public interface VesselSubCaseMapper extends EntityMapper<VesselSubCaseDTO, VesselSubCase> {

    @Mapping(source = "vesselCase.id", target = "vesselCaseId")
    @Mapping(source = "vesselCase.caseCode", target = "vesselCaseCaseCode")
    @Mapping(source = "risk.id", target = "riskId")
    @Mapping(source = "risk.riskName", target = "riskRiskName")
    VesselSubCaseDTO toDto(VesselSubCase vesselSubCase);

    @Mapping(source = "vesselCaseId", target = "vesselCase")
    @Mapping(source = "riskId", target = "risk")
    VesselSubCase toEntity(VesselSubCaseDTO vesselSubCaseDTO);

    default VesselSubCase fromId(Long id) {
        if (id == null) {
            return null;
        }
        VesselSubCase vesselSubCase = new VesselSubCase();
        vesselSubCase.setId(id);
        return vesselSubCase;
    }
}
