package com.yunmu.back.service.sys.impl;
import com.yunmu.back.service.sys.SysMenuService;
import com.yunmu.core.constant.DelFlagConstants;
import com.yunmu.core.constant.PermisionConstants;
import com.yunmu.core.dao.sys.SysMenuMapper;
import com.yunmu.core.model.sys.SysMenu;
import com.yunmu.core.model.sys.SysMenuExample;
import com.yunmu.core.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 菜单业务处理类 ClassName:SysMunuServiceImpl
 *
 * @author ligy-008494
 * @create 2019-07-12 11:35
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getMenuList() {
        SysMenuExample sysMenuExample = new SysMenuExample();
        SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
        criteria.andDelFlagEqualTo(0);
        sysMenuExample.setOrderByClause(" parent_id asc ");
        return sysMenuMapper.selectByExample(sysMenuExample);
    }

    @Override
    public boolean saveMenu(SysMenu sysMenu) {
        sysMenu.setDelFlag(DelFlagConstants.DEL_NO);
        sysMenu.setId(IdUtils.uuid2());
        sysMenu.setCreateTime(new Date());
        sysMenuMapper.insert(sysMenu);
        return true;
    }

    @Override
    public boolean updateMenu(SysMenu sysMenu) {
        sysMenu.setUpdateTime(new Date());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return true;
    }

    @Override
    public SysMenu getMenu(String menuId) {
        return sysMenuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean disableMenu(String menuId, String userId) {
        if(StringUtils.isNotBlank(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(menuId);
            sysMenu.setIsShow(PermisionConstants.DISABLED_YES);
            sysMenu.setUpdateTime(new Date());
            sysMenu.setUpdateBy(userId);
            sysMenuMapper.updateByPrimaryKeySelective(sysMenu);

            // 联动隐藏下级菜单
            SysMenuExample sysMenuExample = new SysMenuExample();
            SysMenuExample.Criteria mCri = sysMenuExample.createCriteria();
            mCri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
            mCri.andParentIdEqualTo(menuId);
            List<SysMenu> child = sysMenuMapper.selectByExample(sysMenuExample);

            if(child != null && !child.isEmpty()) {
                SysMenu sysMenuTmp = new SysMenu();
                sysMenuTmp.setUpdateBy(userId);
                sysMenuTmp.setUpdateTime(new Date());
                sysMenuTmp.setIsShow(PermisionConstants.DISABLED_YES);
                sysMenuMapper.updateByExampleSelective(sysMenuTmp, sysMenuExample);

                if(child.get(0) != null && child.get(0).getMenuType() == PermisionConstants.RESOURCE_TYPE_MENU) {
                    for(SysMenu m1Tmp: child) {
                        SysMenuExample m1Example = new SysMenuExample();
                        SysMenuExample.Criteria m1Cri = m1Example.createCriteria();
                        m1Cri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
                        m1Cri.andParentIdEqualTo(m1Tmp.getId());
                        List<SysMenu> child1 = sysMenuMapper.selectByExample(m1Example);
                        if(child1 != null && !child1.isEmpty()) {
                            if(child1.get(0) != null && child1.get(0).getMenuType() == PermisionConstants.RESOURCE_TYPE_BUTTON) {
                                SysMenu sysMenuTmp1 = new SysMenu();
                                sysMenuTmp1.setUpdateBy(userId);
                                sysMenuTmp1.setUpdateTime(new Date());
                                sysMenuTmp1.setIsShow(PermisionConstants.DISABLED_YES);
                                sysMenuMapper.updateByExampleSelective(sysMenuTmp1, m1Example);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean unDisableMenu(String menuId, String userId) {
        if(StringUtils.isNotBlank(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(menuId);
            sysMenu.setIsShow(PermisionConstants.DISABLED_NO);
            sysMenu.setUpdateTime(new Date());
            sysMenu.setUpdateBy(userId);
            sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
            return true;
        }
        return false;
    }

    @Override
    public boolean del(String menuId, String userId) {
        if(StringUtils.isNotBlank(menuId)) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setId(menuId);
            sysMenu.setDelFlag(DelFlagConstants.DEL_YES);
            sysMenu.setUpdateTime(new Date());
            sysMenu.setUpdateBy(userId);
            sysMenuMapper.updateByPrimaryKeySelective(sysMenu);

            // 联动删除下级
            SysMenuExample sysMenuExample = new SysMenuExample();
            SysMenuExample.Criteria mCri = sysMenuExample.createCriteria();
            mCri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
            mCri.andParentIdEqualTo(menuId);
            List<SysMenu> child = sysMenuMapper.selectByExample(sysMenuExample);

            if(child != null && !child.isEmpty()) {
                SysMenu sysMenuTmp = new SysMenu();
                sysMenuTmp.setUpdateBy(userId);
                sysMenuTmp.setUpdateTime(new Date());
                sysMenuTmp.setDelFlag(DelFlagConstants.DEL_YES);
                sysMenuMapper.updateByExampleSelective(sysMenuTmp, sysMenuExample);

                if(child.get(0) != null && child.get(0).getMenuType() == PermisionConstants.RESOURCE_TYPE_MENU) {
                    for(SysMenu m1Tmp: child) {
                        SysMenuExample m1Example = new SysMenuExample();
                        SysMenuExample.Criteria m1Cri = m1Example.createCriteria();
                        m1Cri.andDelFlagEqualTo(DelFlagConstants.DEL_NO);
                        m1Cri.andParentIdEqualTo(m1Tmp.getId());
                        List<SysMenu> child1 = sysMenuMapper.selectByExample(m1Example);
                        if(child1 != null && !child1.isEmpty()) {
                            if(child1.get(0) != null && child1.get(0).getMenuType() == PermisionConstants.RESOURCE_TYPE_BUTTON) {
                                SysMenu sysMenuTmp1 = new SysMenu();
                                sysMenuTmp1.setUpdateBy(userId);
                                sysMenuTmp1.setUpdateTime(new Date());
                                sysMenuTmp1.setDelFlag(DelFlagConstants.DEL_YES);
                                sysMenuMapper.updateByExampleSelective(sysMenuTmp1, m1Example);
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;

    }


}

