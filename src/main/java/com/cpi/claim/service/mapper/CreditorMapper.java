package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.CreditorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Creditor and its DTO CreditorDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CreditorMapper extends EntityMapper<CreditorDTO, Creditor> {



    default Creditor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Creditor creditor = new Creditor();
        creditor.setId(id);
        return creditor;
    }
}
