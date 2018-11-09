package com.cpi.claim.repository.uw;

import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AuthorizedFeignClient(name = "cpiuw")
public interface InsuredVesselRepository {

    @RequestMapping(value = "/api/insuredvessels/{id}", method = RequestMethod.GET)
    InsuredVesselDTO findInsuredVesselByID(@PathVariable("id") Long id);

}
