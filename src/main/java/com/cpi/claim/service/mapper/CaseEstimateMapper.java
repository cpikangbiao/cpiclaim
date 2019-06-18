package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseEstimateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseEstimate and its DTO CaseEstimateDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class})
public interface CaseEstimateMapper extends EntityMapper<CaseEstimateDTO, CaseEstimate> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    CaseEstimateDTO toDto(CaseEstimate caseEstimate);

    @Mapping(source = "subcaseId", target = "subcase")
    CaseEstimate toEntity(CaseEstimateDTO caseEstimateDTO);

    default CaseEstimate fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseEstimate caseEstimate = new CaseEstimate();
        caseEstimate.setId(id);
        return caseEstimate;
    }
}
