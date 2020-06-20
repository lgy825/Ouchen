package com.ouchen.back.service.sys.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ouchen.back.service.sys.SysRoleService;
import com.ouchen.core.constant.DelFlagConstants;
import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.constant.PermisionConstants;
import com.ouchen.core.dao.company.CompanyMapper;
import com.ouchen.core.dao.sys.SysRoleMapper;
import com.ouchen.core.dao.sys.SysRoleMapperExt;
import com.ouchen.core.dao.sys.SysRoleMenuMapper;
import com.ouchen.core.exception.DataException;
import com.ouchen.core.model.company.Company;
import com.ouchen.core.model.company.CompanyExample;
import com.ouchen.core.model.sys.*;
import com.ouchen.core.util.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapperExt sysRoleMapperExt;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public GenericPage<SysRoleExt> getRolePageByCondition(Map<String, Object> param) {
        int pageIndex = 1, pageSize = 10;
        if(param.containsKey("pageIndex")) {
            if(param.get("pageIndex") != null &&
                    StringUtils.isNotBlank(param.get("pageIndex").toString())) {
                pageIndex = Integer.valueOf(param.get("pageIndex").toString());
                if(pageIndex < 1) {
                    pageIndex = 1;
                }
            }
        }
        if(param.containsKey("pageSize")) {
            if(param.get("pageIndex") != null &&
                    StringUtils.isNotBlank(param.get("pageSize").toString())) {
                pageSize = Integer.valueOf(param.get("pageSize").toString());
                if(pageSize < 1) {
                    pageSize = 10;
                }
            }
        }
        Page<SysRoleExt> pageInfo = PageHelper.startPage(pageIndex, pageSize, true);
        List<SysRoleExt> sysUSerList = sysRoleMapperExt.getRolePage(param);
        //获取项目名称
        for(SysRoleExt sysRoleExt:sysUSerList){
            CompanyExample companyExample=new CompanyExample();
            CompanyExample.Criteria criteria=companyExample.createCriteria();
            criteria.andCompanyCodeEqualTo(sysRoleExt.getCompanyCode());
            criteria.andDelFlagEqualTo(0);
            List<Company> companies=companyMapper.selectByExample(companyExample);
            sysRoleExt.setCompanyName(companies.get(0).getCompanyName());
        }
        return new GenericPage<>(pageIndex, pageSize, sysUSerList, pageInfo.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRoleExt(SysRoleExt sysRoleExt) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleExt, sysRole);
        String roleId = IdUtils.uuid2();
        sysRole.setId(roleId);
        sysRole.setCreateTime(new Date());
        sysRole.setDelFlag(DelFlagConstants.DEL_NO);
        sysRole.setStatus(PermisionConstants.DISABLED_NO);
        if("-1".equals(sysRole.getCompanyCode()) || StringUtils.isBlank(sysRole.getCompanyCode())) {
            sysRole.setCompanyCode(null);
        }

        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria sysRoleCri = sysRoleExample.createCriteria();
        sysRoleCri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
        sysRoleCri.andRoleNameEqualTo(sysRole.getRoleName());
        if(sysRoleMapper.countByExample(sysRoleExample) > 0) {
            throw new DataException("角色名已存在");
        }

        sysRoleMapper.insert(sysRole);

        List<String> menuIds = sysRoleExt.getMenuIds();
        List<SysRoleMenuKey> sysRoleMenuKeyList = new ArrayList<>();
        menuIds.forEach(el -> {
            SysRoleMenuKey sysRoleMenuKey = new SysRoleMenuKey();
            sysRoleMenuKey.setMenuId(el);
            sysRoleMenuKey.setRoleId(roleId);
            sysRoleMenuKeyList.add(sysRoleMenuKey);
        });
        sysRoleMapperExt.insertRoleMenu(sysRoleMenuKeyList);
        return true;
    }

    @Override
    public boolean updateRoleExt(SysRoleExt sysRoleExt) {
        if(sysRoleExt != null) {
            String roleId = sysRoleExt.getId();
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);

            int compareCount = 0;
            if(sysRole.getRoleName().equals(sysRoleExt.getRoleName())) {
                compareCount = 1;
            }

            sysRoleExt.setCompanyCode(null);
            BeanUtils.copyProperties(sysRoleExt, sysRole);
            sysRole.setUpdateTime(new Date());

            SysRoleExample sysRoleExample = new SysRoleExample();
            SysRoleExample.Criteria sysRoleCri = sysRoleExample.createCriteria();
            sysRoleCri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
            sysRoleCri.andRoleNameEqualTo(sysRole.getRoleName());
            if (sysRoleMapper.countByExample(sysRoleExample) > compareCount) {
                throw new DataException("角色名已存在");
            }

            sysRoleMapper.updateByPrimaryKeySelective(sysRole);

            SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
            SysRoleMenuExample.Criteria sysRoleMCri = sysRoleMenuExample.createCriteria();
            sysRoleMCri.andRoleIdEqualTo(roleId);
            sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);

            List<String> menuIds = sysRoleExt.getMenuIds();
            List<SysRoleMenuKey> sysRoleMenuKeyList = new ArrayList<>();
            menuIds.forEach(el -> {
                SysRoleMenuKey sysRoleMenuKey = new SysRoleMenuKey();
                sysRoleMenuKey.setMenuId(el);
                sysRoleMenuKey.setRoleId(roleId);
                sysRoleMenuKeyList.add(sysRoleMenuKey);
            });
            sysRoleMapperExt.insertRoleMenu(sysRoleMenuKeyList);
            return true;
        }
        return false;
    }

    @Override
    public SysRoleExt getRoleExt(String roleId) {
        SysRoleExt sysRoleExt = new SysRoleExt();

        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        BeanUtils.copyProperties(sysRole, sysRoleExt);

        SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
        SysRoleMenuExample.Criteria sysRoleMCri = sysRoleMenuExample.createCriteria();
        sysRoleMCri.andRoleIdEqualTo(roleId);
        List<SysRoleMenuKey> sysRoleMenuKeys = sysRoleMenuMapper.selectByExample(sysRoleMenuExample);
        List<String> mids = sysRoleMenuKeys.stream().map(SysRoleMenuKey::getMenuId).collect(Collectors.toList());
        sysRoleExt.setMenuIds(mids);
        return sysRoleExt;
    }

    @Override
    public boolean nameExist(SysRoleExt sysRoleExt) {
        int compare = 0;
        if(StringUtils.isNotBlank(sysRoleExt.getId())) {
            // 修改时候判断
            SysRole dbRole = sysRoleMapper.selectByPrimaryKey(sysRoleExt.getId());
            if(dbRole.getRoleName().equals(sysRoleExt.getRoleName())) {
                compare = 1;
            }
        }

        SysRoleExample sysRoleExample = new SysRoleExample();
        SysRoleExample.Criteria sysRoleCri = sysRoleExample.createCriteria();
        sysRoleCri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
        sysRoleCri.andRoleNameEqualTo(sysRoleExt.getRoleName());

        if(sysRoleMapper.countByExample(sysRoleExample) > compare) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean disableRole(String roleId, String userId) {
        if(StringUtils.isNotBlank(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setId(roleId);
            sysRole.setUpdateBy(userId);
            sysRole.setUpdateTime(new Date());
            sysRole.setStatus(PermisionConstants.DISABLED_YES);
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            return true;
        }
        return false;
    }

    @Override
    public boolean unDisableRole(String roleId, String userId) {
        if(StringUtils.isNotBlank(roleId)) {
            SysRole sysRole = new SysRole();
            sysRole.setId(roleId);
            sysRole.setUpdateBy(userId);
            sysRole.setUpdateTime(new Date());
            sysRole.setStatus(PermisionConstants.DISABLED_NO);
            sysRoleMapper.updateByPrimaryKeySelective(sysRole);
            return true;
        }
        return false;
    }
}
