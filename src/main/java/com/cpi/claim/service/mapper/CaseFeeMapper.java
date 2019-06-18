package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CaseFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CaseFee} and its DTO {@link CaseFeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FeeTypeMapper.class, VesselSubCaseMapper.class, CreditorMapper.class})
public interface CaseFeeMapper extends EntityMapper<CaseFeeDTO, CaseFee> {

    @Mapping(source = "feeType.id", target = "feeTypeId")
    @Mapping(source = "feeType.feeTypeName", target = "feeTypeFeeTypeName")
    @Mapping(source = "subcase.id", target = "subcaseId")
    @Mapping(source = "subcase.subcaseCode", target = "subcaseSubcaseCode")
    @Mapping(source = "creditor.id", target = "creditorId")
    @Mapping(source = "creditor.creditorCode", target = "creditorCreditorCode")
    CaseFeeDTO toDto(CaseFee caseFee);

    @Mapping(source = "feeTypeId", target = "feeType")
    @Mapping(source = "subcaseId", target = "subcase")
    @Mapping(source = "creditorId", target = "creditor")
    CaseFee toEntity(CaseFeeDTO caseFeeDTO);

    default CaseFee fromId(Long id) {
        if (id == null) {
            return null;
        }
        CaseFee caseFee = new CaseFee();
        caseFee.setId(id);
        return caseFee;
    }
}
