/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: YearCountStatistics
 * Author:   admin
 * Date:     2018/8/6 8:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cpi.claim.repository.bean;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author admin
 * @create 2018/8/6
 * @since 1.0.0
 */
public class CaseMonthCountStatisticsBean {

    private String year;

    private String month;

    private String type;

    private Long  count;

    public CaseMonthCountStatisticsBean() {
    }

    public CaseMonthCountStatisticsBean(String year, String month, String type, Long count) {
        this.year = year;
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
