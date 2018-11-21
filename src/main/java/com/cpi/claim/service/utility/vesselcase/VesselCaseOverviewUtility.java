/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: VesselCaseOverviewBean
 * Author:   admin
 * Date:     2018/11/21 10:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
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
