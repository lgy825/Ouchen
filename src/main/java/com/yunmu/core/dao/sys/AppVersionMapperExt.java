package com.yunmu.core.dao.sys;


import com.yunmu.core.model.sys.AppVersionExt;

import java.util.List;
import java.util.Map;

public interface AppVersionMapperExt {

    List<AppVersionExt> getAppManagerPage(Map<String, Object> params);
}