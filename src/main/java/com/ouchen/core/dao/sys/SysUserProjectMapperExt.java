package com.ouchen.core.dao.sys;

import java.util.List;

public interface SysUserProjectMapperExt {


    List<String> getProjectIdsByUserId(String userId);
}