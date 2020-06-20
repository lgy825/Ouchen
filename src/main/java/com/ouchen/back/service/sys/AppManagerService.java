package com.ouchen.back.service.sys;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.sys.AppVersion;
import com.ouchen.core.model.sys.AppVersionExt;

import java.util.Map;

public interface AppManagerService {

    GenericPage<AppVersionExt> getPageByCondition(Map<String, Object> params);

    boolean insert(AppVersion appVersion);

    boolean update(AppVersion appVersion);

    AppVersion getAppById(String id);
}
