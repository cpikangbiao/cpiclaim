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
package com.cpi.claim.service.utility.vesselcase;

import com.cpi.claim.domain.VesselCase;
import com.cpi.claim.domain.VesselSubCase;
import com.cpi.claim.service.bean.vesselcase.VesselCaseOverviewBean;
import com.cpi.claim.service.bean.vesselcase.VesselSubCaseOverviewBean;
import com.cpi.claim.service.utility.ClaimToolUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/21
 * @since 1.0.0
 */

@Service
@Transactional
public class VesselCaseOverviewUtility  {

    @Autowired
    private ClaimToolUtility claimToolUtility;

    @Transactional(readOnly = true)
    public VesselCaseOverviewBean createVesselCaseOverviewBean(Long vesselCaseId) {
        VesselCaseOverviewBean vesselCaseOverviewBean = new VesselCaseOverviewBean();
        VesselCase vesselCase = claimToolUtility.vesselCaseRepository.getOne(vesselCaseId);

        if (vesselCase != null) {
            vesselCaseOverviewBean.init(vesselCase, claimToolUtility);
        }

        return vesselCaseOverviewBean;
    }

}
