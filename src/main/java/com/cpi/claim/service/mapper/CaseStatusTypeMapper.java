package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseStatusTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseStatusType and its DTO CaseStatusTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaseStatusTypeMapper extends EntityMapper<CaseStatusTypeDTO, CaseStatusType> {



    default CaseStatusType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseStatusType caseStatusType = new CaseStatusType();
        caseStatusType.setId(id);
        return caseStatusType;
    }
}
