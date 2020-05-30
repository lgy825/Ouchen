package com.yunmu.back.service.hourse;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.hourse.HourseType;
import com.yunmu.core.model.hourse.HourseTypeExt;

import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/6/18.
 */
public interface HourseTypeService {

    public GenericPage<HourseTypeExt> getPageByCondition(Map<String, Object> params);

    boolean insert(HourseType hourseType);

    Boolean update(HourseType hourse);

    HourseType getHourseByIdById(String id);

    Boolean deleteByPrimaryKey(String id);

    List<HourseType> getHourseTypeListById(List<String> ids);


}
