/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ScheduledForStatis
 * Author:   admin
 * Date:     2018/8/29 15:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cpi.claim.web.schedule;

import com.cpi.claim.service.VesselCaseExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/8/29
 * @since 1.0.0
 */

@Component
public class ScheduledForStatistics {

    private final Logger log = LoggerFactory.getLogger(ScheduledForStatistics.class);

    @Autowired
    private VesselCaseExtService vesselCaseExtService;


    @Scheduled(cron = "0 59 23 * * ?")
    public void pushDataScheduled(){
        log.info("start gather Statistic  data scheduled!");

        // gather Static For Member Year Count
        log.info("gather Static For case Year Count at {}", Instant.now());
        vesselCaseExtService.gatherStaticForCaseYearCount();

        // gather Static For Member Year Count
        log.info("gather Static For case month Count at {}", Instant.now());
        vesselCaseExtService.gatherStaticForCaseMonthCount();

        log.info("end gather Statistic  data scheduled!");
    }

}
