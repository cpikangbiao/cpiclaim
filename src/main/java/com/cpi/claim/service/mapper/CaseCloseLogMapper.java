package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseCloseLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseCloseLog and its DTO CaseCloseLogDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselCaseMapper.class})
public interface CaseCloseLogMapper extends EntityMapper<CaseCloseLogDTO, CaseCloseLog> {

    @Mapping(source = "vesselCase.id", target = "vesselCaseId")
    @Mapping(source = "vesselCase.caseCode", target = "vesselCaseCaseCode")
    CaseCloseLogDTO toDto(CaseCloseLog caseCloseLog);

    @Mapping(source = "vesselCaseId", target = "vesselCase")
    CaseCloseLog toEntity(CaseCloseLogDTO caseCloseLogDTO);

    default CaseCloseLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseCloseLog caseCloseLog = new CaseCloseLog();
        caseCloseLog.setId(id);
        return caseCloseLog;
    }
}
