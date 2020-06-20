package com.ouchen.core.dao.sys;

import com.ouchen.core.model.sys.SysRoleExt;
import com.ouchen.core.model.sys.SysRoleMenuKey;

import java.util.List;
import java.util.Map;

public interface SysRoleMapperExt {

    List<SysRoleExt> getRolePage(Map<String, Object> param);

    int insertRoleMenu(List<SysRoleMenuKey> sysRoleMenuKeys);
}