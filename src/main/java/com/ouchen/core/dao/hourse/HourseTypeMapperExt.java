package com.ouchen.core.dao.hourse;

import com.ouchen.core.model.hourse.HourseTypeExt;

import java.util.List;
import java.util.Map;

public interface HourseTypeMapperExt {

    List<HourseTypeExt> getHourseTypePage(Map<String, Object> params);
}