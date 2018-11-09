/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CountryRepository
 * Author:   admin
 * Date:     2018/4/26 8:44
 * Description: get country from remote microservice
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


package com.cpi.claim.repository.workflow;

import com.cpi.claim.client.AuthorizedFeignClient;
import com.cpi.claim.config.LongTimeFeignConfiguration;
import com.cpi.claim.repository.workflow.bean.FormPropertyBean;
import com.cpi.claim.repository.workflow.bean.ProcessInstanceStatusBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈get country from remote microservice〉
 *
 * @author admin
 * @create 2018/4/26
 * @since 1.0.0
 */

@AuthorizedFeignClient(name = "cpiworkflow", configuration = LongTimeFeignConfiguration.class)
public interface WorkflowRepository {

    @RequestMapping(value = "/api/workflow/create", method = RequestMethod.GET)
    String createProcessInstance(@RequestParam("processDefinitionKey") final String processDefinitionKey,
                                 @RequestParam("entityId") final String entityId,
                                 @RequestParam("userId") final String userId);

    @RequestMapping(value = "/api/workflow/get-task-list/count", method = RequestMethod.GET)
    Long getTaskListCountForUserId(@RequestParam("processDefinitionKey") final String processDefinitionKey,
                                   @RequestParam("userId") final String userId);

    @RequestMapping(value = "/api/workflow/get-task-list", method = RequestMethod.GET)
    List<String> getTaskListForUserId(@RequestParam("processDefinitionKey") final String processDefinitionKey,
                                      @RequestParam("userId") final String userId);

//    @RequestMapping(value = "/api/workflow/get-task-list", method = RequestMethod.GET)
//    List<String> getTaskListForUserId(@RequestParam("processDefinitionKey") final String processDefinitionKey,
//                                      @RequestParam("userId") final String userId,
//                                      @RequestParam("page") final Pageable page);

    @RequestMapping(value = "/api/workflow/get-task-form-property", method = RequestMethod.GET)
    List<FormPropertyBean> getTaskFormPropertyListForProcessInstanceId(@RequestParam("processInstanceId") final String processInstanceId);

    @RequestMapping(value = "/api/workflow/complete", method = RequestMethod.POST)
    void completeTaskForProcessInstanceId(@RequestParam("processInstanceId") final String processInstanceId,
                                          @RequestBody final Map<String, Object> variables);

    @RequestMapping(value = "/api/workflow/current-image", method = RequestMethod.GET)
    byte[] getCurrentImageForProcessInstanceId(@RequestParam("processInstanceId") final String processInstanceId);

    @RequestMapping(value = "/api/workflow/get-process-status", method = RequestMethod.GET)
    ProcessInstanceStatusBean getProcessStatusForProcessInstanceId(@RequestParam("processInstanceId") String processInstanceId);


    @RequestMapping(value = "/api/workflow/activate-or-suspend", method = RequestMethod.GET)
    void activateOrSuspendProcessInstanceById(@RequestParam("processInstanceId") String processInstanceId);

    @RequestMapping(value = "/api/workflow/delete", method = RequestMethod.GET)
    void deleteProcessInstanceById(@RequestParam("processInstanceId") String processInstanceId);

}
