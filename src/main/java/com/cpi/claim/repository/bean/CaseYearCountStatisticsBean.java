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
public class CaseYearCountStatisticsBean {

    private String year;

    private String type;

    private Long  count;

    public CaseYearCountStatisticsBean(String year, String type, Long count) {
        this.year = year;
        this.type = type;
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
