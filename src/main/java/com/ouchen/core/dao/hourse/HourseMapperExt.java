package com.ouchen.core.dao.hourse;

import com.ouchen.core.model.hourse.HourseExt;

import java.util.List;
import java.util.Map;

public interface HourseMapperExt {


    int getHouseCountByCondition(String ownerCode);

    List<HourseExt> getHoursePage(Map<String, Object> params);

    List<HourseExt> getAllHourse();

}