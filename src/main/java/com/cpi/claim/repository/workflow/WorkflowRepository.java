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
