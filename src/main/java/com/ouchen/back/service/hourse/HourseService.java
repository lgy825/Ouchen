package com.ouchen.back.service.hourse;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.hourse.Hourse;
import com.ouchen.core.model.hourse.HourseExt;

import java.util.List;
import java.util.Map;

/**
 * Created by 13544 on 2019/5/20.
 */
public interface HourseService {

    public GenericPage<HourseExt> getPageByCondition(Map<String, Object> params);

    boolean insert(Hourse hourse);

    Hourse getHourseByIdById(String hId);

    Boolean update(Hourse hourse);

    boolean deleteByPrimaryKey(String hid);

    List<Hourse> getAllHourse();
}
