package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseClaimDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CaseClaim and its DTO CaseClaimDTO.
 */
@Mapper(componentModel = "spring", uses = {VesselSubCaseMapper.class})
public interface CaseClaimMapper extends EntityMapper<CaseClaimDTO, CaseClaim> {

    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    CaseClaimDTO toDto(CaseClaim caseClaim);

    @Mapping(source = "subcaseId", target = "subcase")
    CaseClaim toEntity(CaseClaimDTO caseClaimDTO);

    default CaseClaim fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseClaim caseClaim = new CaseClaim();
        caseClaim.setId(id);
        return caseClaim;
    }
}
