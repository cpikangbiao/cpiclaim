package com.cpi.claim.repository.uw;

import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.service.dto.uw.InsuredVesselDTO;
import com.cpi.claim.service.dto.uw.MemberDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AuthorizedFeignClient(name = "cpiuw")
public interface MemberRepository {

    @RequestMapping(value = "/api/members/{id}", method = RequestMethod.GET)
    MemberDTO findMemberByID(@PathVariable("id") Long id);

}
