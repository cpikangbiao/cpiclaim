/*
 * Copyright (c)  2015-2018, All rights Reserved, Designed By Kang Biao
 * Email: alex.kangbiao@gmail.com
 * Create by Alex Kang on 18-12-27 上午10:07
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

package com.cpi.claim.service;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.service.utility.ClaimToolUtility;
import com.cpi.share.uw.insuredvessel.InsuredVesselInfo;
import io.github.jhipster.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UWInfoService extends QueryService<VesselCase> {

    private final Logger log = LoggerFactory.getLogger(UWInfoService.class);

    private final ClaimToolUtility claimToolUtility;

    public UWInfoService(ClaimToolUtility claimToolUtility) {
        this.claimToolUtility = claimToolUtility;
    }

    @Transactional(readOnly = true)
    public InsuredVesselInfo getInsuredVesselInfo(Long vesselCaseId) {
        InsuredVesselInfo insuredVesselInfo = claimToolUtility.insuredVesselRepository.getInsuredVesselInfo(vesselCaseId);
        return insuredVesselInfo;
    }

    @Transactional(readOnly = true)
    public byte[] getHTMLFileForCertificate(Long ivCertificateVersionId) {
        return claimToolUtility.insuredVesselRepository.getHTMLFileForCertificate(ivCertificateVersionId);
    }
}
