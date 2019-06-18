package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseSettlementModeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseSettlementMode} and its DTO {@link CaseSettlementModeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CaseSettlementModeMapper extends EntityMapper<CaseSettlementModeDTO, CaseSettlementMode> {



    default CaseSettlementMode fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseSettlementMode caseSettlementMode = new CaseSettlementMode();
        caseSettlementMode.setId(id);
        return caseSettlementMode;
    }
}
