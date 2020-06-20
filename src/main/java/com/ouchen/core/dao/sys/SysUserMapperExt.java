package com.ouchen.core.dao.sys;

import com.ouchen.core.model.sys.*;

import java.util.List;
import java.util.Map;

public interface SysUserMapperExt {

    List<SysUserExt> getSysUserPage(Map<String, Object> params);

    void insertBatchUserCinema(List<SysUserProject> param);

    void insertBatchUserRole(List<SysUserRole> param);

    List<SysMenu> getMenusByUserId(String userId);

    List<SysRole> getRolesByUserId(String userId);
}