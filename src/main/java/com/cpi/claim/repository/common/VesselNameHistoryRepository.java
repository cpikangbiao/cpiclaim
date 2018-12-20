/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-20 上午9:55
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


package com.cpi.claim.repository.common;

import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.service.dto.common.VesselNameHistoryDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈get country from remote microservice〉
 *
 * @author admin
 * @create 2018/4/26
 * @since 1.0.0
 */

@AuthorizedFeignClient(name = "cpicommon")
public interface VesselNameHistoryRepository {

    @RequestMapping(value = "/api/vessel-name-histories/{id}", method = RequestMethod.GET)
    VesselNameHistoryDTO findVesselNameHistoryByID(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/vessel-name-histories/by-vessel-id/{vesselId}", method = RequestMethod.GET)
    List<VesselNameHistoryDTO> findAllByVesselId(@PathVariable("vesselId") Long vesselId);

    @RequestMapping(value = "/api/vessel-name-histories/find-fit-name-by-vessel-id", method = RequestMethod.GET)
    VesselNameHistoryDTO findFitVesselNameByVesselId(@RequestParam("vesselId") Long vesselId,
                                                     @RequestParam("specialTime") Instant specialTime);
}
