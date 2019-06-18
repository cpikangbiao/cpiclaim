package com.cpi.claim.service.mapper;

import com.cpi.claim.domain.*;
import com.cpi.claim.service.dto.PaymentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentType} and its DTO {@link PaymentTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentTypeMapper extends EntityMapper<PaymentTypeDTO, PaymentType> {



    default PaymentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentType paymentType = new PaymentType();
        paymentType.setId(id);
        return paymentType;
    }
}
