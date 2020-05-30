package com.yunmu.core.dao.sys;

import com.yunmu.core.model.sys.SysRole;
import com.yunmu.core.model.sys.SysRoleExample;
import com.yunmu.core.model.sys.SysRoleExt;
import com.yunmu.core.model.sys.SysRoleMenuKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRoleMapperExt {

    List<SysRoleExt> getRolePage(Map<String, Object> param);

    int insertRoleMenu(List<SysRoleMenuKey> sysRoleMenuKeys);
}