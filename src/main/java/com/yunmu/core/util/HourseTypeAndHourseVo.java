package com.yunmu.core.util;

import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseExt;
import com.yunmu.core.model.hourse.HourseType;

import java.util.List;
import java.util.Map;

public class HourseTypeAndHourseVo {
    public HourseTypeAndHourseVo(List<HourseType> hourseTypeList, Map<String, List<Hourse>> hourseTypeMap) {
        this.hourseTypeList = hourseTypeList;
        this.hourseTypeMap = hourseTypeMap;
    }

    private List<HourseType> hourseTypeList;
    private Map<String, List<Hourse>> hourseTypeMap;

    public List<HourseType> getHourseTypeList() {
        return hourseTypeList;
    }

    public Map<String, List<Hourse>> getHourseTypeMap() {
        return hourseTypeMap;
    }

    public void setHourseTypeMap(Map<String, List<Hourse>> hourseTypeMap) {
        this.hourseTypeMap = hourseTypeMap;
    }

    public void setHourseTypeList(List<HourseType> hourseTypeList) {
        this.hourseTypeList = hourseTypeList;
    }
}
