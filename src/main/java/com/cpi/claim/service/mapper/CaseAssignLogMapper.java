package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseAssignLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseAssignLog and its DTO CaseAssignLogDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselCaseMapper.class})
public interface CaseAssignLogMapper extends EntityMapper<CaseAssignLogDTO, CaseAssignLog> {

    @Mapping(source = "vesselCase.id", target = "vesselCaseId")
    @Mapping(source = "vesselCase.caseCode", target = "vesselCaseCaseCode")
    CaseAssignLogDTO toDto(CaseAssignLog caseAssignLog);

    @Mapping(source = "vesselCaseId", target = "vesselCase")
    CaseAssignLog toEntity(CaseAssignLogDTO caseAssignLogDTO);

    default CaseAssignLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseAssignLog caseAssignLog = new CaseAssignLog();
        caseAssignLog.setId(id);
        return caseAssignLog;
    }
}
