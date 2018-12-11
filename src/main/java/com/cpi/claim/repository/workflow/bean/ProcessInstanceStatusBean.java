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
package com.cpi.claim.repository.workflow.bean;

import java.io.Serializable;
import java.time.Instant;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/10/23
 * @since 1.0.0
 */
public class ProcessInstanceStatusBean implements Serializable {

    private String processInstanceId;

    private String currentNode;

    private String currentUserId;

    private Instant processBeginTime;

    private Instant processEndTime;

    private Boolean isSuspended;

    private Boolean isFinished;

    private String createUserId;

    public ProcessInstanceStatusBean() {
        this.processInstanceId = null;
        this.currentNode = null;
        this.currentUserId = null;
        this.processBeginTime = null;
        this.processEndTime = null;
        this.isSuspended = false;
        this.isFinished = false;
        this.createUserId = null;
    }
    public ProcessInstanceStatusBean(String processInstanceId, String currentNode, String currentUserId, Instant processBeginTime, Instant processEndTime, Boolean isSuspended, String createUserId, Boolean isFinished) {
        this.processInstanceId = processInstanceId;
        this.currentNode = currentNode;
        this.currentUserId = currentUserId;
        this.processBeginTime = processBeginTime;
        this.processEndTime = processEndTime;
        this.isSuspended = isSuspended;
        this.isFinished = isFinished;
        this.createUserId = createUserId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Instant getProcessBeginTime() {
        return processBeginTime;
    }

    public void setProcessBeginTime(Instant processBeginTime) {
        this.processBeginTime = processBeginTime;
    }

    public Instant getProcessEndTime() {
        return processEndTime;
    }

    public void setProcessEndTime(Instant processEndTime) {
        this.processEndTime = processEndTime;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
