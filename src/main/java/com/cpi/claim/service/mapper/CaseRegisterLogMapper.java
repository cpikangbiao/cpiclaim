package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseRegisterLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseRegisterLog and its DTO CaseRegisterLogDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselCaseMapper.class})
public interface CaseRegisterLogMapper extends EntityMapper<CaseRegisterLogDTO, CaseRegisterLog> {

    @Mapping(source = "vesselCase.id", target = "vesselCaseId")
    @Mapping(source = "vesselCase.caseCode", target = "vesselCaseCaseCode")
    CaseRegisterLogDTO toDto(CaseRegisterLog caseRegisterLog);

    @Mapping(source = "vesselCaseId", target = "vesselCase")
    CaseRegisterLog toEntity(CaseRegisterLogDTO caseRegisterLogDTO);

    default CaseRegisterLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseRegisterLog caseRegisterLog = new CaseRegisterLog();
        caseRegisterLog.setId(id);
        return caseRegisterLog;
    }
}
