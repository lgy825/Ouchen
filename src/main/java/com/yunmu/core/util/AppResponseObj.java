package com.yunmu.core.util;

/**
 * Created by 13544 on 2019/6/11.
 */
public class AppResponseObj {
    private String oId;
    private String date;
    private String incomeAll;
    private String days;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIncomeAll() {
        return incomeAll;
    }

    public void setIncomeAll(String incomeAll) {
        this.incomeAll = incomeAll;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
