package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CpiInsuranceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CpiInsuranceType} and its DTO {@link CpiInsuranceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CpiInsuranceTypeMapper extends EntityMapper<CpiInsuranceTypeDTO, CpiInsuranceType> {



    default CpiInsuranceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        CpiInsuranceType cpiInsuranceType = new CpiInsuranceType();
        cpiInsuranceType.setId(id);
        return cpiInsuranceType;
    }
}
