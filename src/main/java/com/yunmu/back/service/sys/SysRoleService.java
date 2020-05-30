package com.yunmu.back.service.sys;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.sys.SysRoleExt;

import java.util.Map;

public interface SysRoleService {
    GenericPage<SysRoleExt> getRolePageByCondition(Map<String, Object> param);

    boolean saveRoleExt(SysRoleExt sysRoleExt);

    boolean updateRoleExt(SysRoleExt sysRoleExt);

    SysRoleExt getRoleExt(String roleId);

    boolean nameExist(SysRoleExt sysRoleExt );

    boolean disableRole(String roleId,String userId);

    boolean unDisableRole(String roleId,String userId);


}
