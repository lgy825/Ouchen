package com.ouchen.back.service.sys;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.project.Project;
import com.ouchen.core.model.sys.SysMenu;
import com.ouchen.core.model.sys.SysRole;
import com.ouchen.core.model.sys.SysUser;
import com.ouchen.core.model.sys.SysUserExt;

import java.util.List;
import java.util.Map;

public interface SysUserService {

    GenericPage<SysUserExt> getPageByCondition(Map<String, Object> params);

    Boolean insert(SysUserExt sysUserExt);

    Boolean update(SysUserExt sysUserExt);

    Boolean nameExist(SysUserExt sysUserExt);

    SysUserExt getUserExt(String id);

    List<Project> getProjectByUserId(String userId);

    List<SysUser> getUsersByCondition(Map<String, Object> param);

    boolean getUsersBeDisabled(String userId);

    boolean updatePassWord(SysUser sysUser);

    int getSysUserByCompanyCode(String companyCode);

    List<SysRole> getRolesByUserId(String userId);

    List<SysMenu> getMenusByUserId(String userId);

    //Boolean beFirstLogin(String userId);


    boolean selectWeakPass(String password);

}
