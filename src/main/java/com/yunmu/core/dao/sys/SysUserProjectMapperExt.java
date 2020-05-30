package com.yunmu.core.dao.sys;

import com.yunmu.core.model.sys.SysUserProject;
import com.yunmu.core.model.sys.SysUserProjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserProjectMapperExt {


    List<String> getProjectIdsByUserId(String userId);
}