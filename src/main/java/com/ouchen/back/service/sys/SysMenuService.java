package com.ouchen.back.service.sys;

import com.ouchen.core.model.sys.SysMenu;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> getMenuList();

    boolean saveMenu(SysMenu sysMenu);

    boolean updateMenu(SysMenu sysMenu);

    SysMenu getMenu(String menuId);

    boolean disableMenu(String menuId,String userId);

    boolean unDisableMenu(String menuId,String userId);

    boolean del(String menuId,String userId);
}
