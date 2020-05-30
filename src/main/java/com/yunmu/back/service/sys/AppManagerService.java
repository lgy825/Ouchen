package com.yunmu.back.service.sys;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.sys.AppVersion;
import com.yunmu.core.model.sys.AppVersionExt;

import java.util.Map;

public interface AppManagerService {

    GenericPage<AppVersionExt> getPageByCondition(Map<String, Object> params);

    boolean insert(AppVersion appVersion);

    boolean update(AppVersion appVersion);

    AppVersion getAppById(String id);
}
