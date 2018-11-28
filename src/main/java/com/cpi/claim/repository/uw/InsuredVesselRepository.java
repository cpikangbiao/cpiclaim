package com.cpi.claim.repository.uw;

import com.codahale.metrics.annotation.Timed;
import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@AuthorizedFeignClient(name = "cpiuw")
public interface InsuredVesselRepository {

    @RequestMapping(value = "/api/insured-vessels/{id}", method = RequestMethod.GET)
    InsuredVesselDTO findInsuredVesselByID(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/insured-vessels/claim/get-ids/{startYear}/{endYear}/{companyId}/{cpiInsuranceTypeId}", method = RequestMethod.GET)
    List<Long> getIdsForYearAndCompany(
        @PathVariable("startYear") String startYear,
        @PathVariable("endYear") String endYear,
        @PathVariable("companyId") Long companyId,
        @PathVariable("cpiInsuranceTypeId") Long cpiInsuranceTypeId
    );


}

