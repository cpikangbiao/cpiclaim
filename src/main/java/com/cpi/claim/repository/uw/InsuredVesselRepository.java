/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-11 下午2:41
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

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

