
package com.cpi.claim.service.bean.timeline;

import java.io.Serializable;
import java.time.Instant;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/11/21
 * @since 1.0.0
 */
public class TimeLineBean implements Comparable<TimeLineBean>, Serializable{


    private Instant incidentTime;

    private String  incident;

    private String  incidentType;

    public TimeLineBean() {
    }

    public TimeLineBean(Instant incidentTime, String incident, String incidentType) {
        this.incidentTime = incidentTime;
        this.incident = incident;
        this.incidentType = incidentType;
    }

    public Instant getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(Instant incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    @Override
    public int compareTo(TimeLineBean o) {
        int mark = 1;
        if(this.getIncidentTime().equals(o.getIncidentTime())){
            mark =  0;
        } else if (this.getIncidentTime().isAfter(o.getIncidentTime()) ) {
            mark =  -1;
        }

        return mark;

    }

}
