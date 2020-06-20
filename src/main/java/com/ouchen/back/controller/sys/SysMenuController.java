package com.ouchen.back.controller.sys;

import com.ouchen.back.service.sys.SysMenuService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.model.sys.SysMenu;
import com.ouchen.core.util.ShiroUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 菜单配置 ClassName:SysMenu
 *
 * @author ligy-008494
 * @create 2019-07-12 11:30
 */
@Controller
@RequestMapping("sysmenu")
public class SysMenuController  extends BaseController {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/toMenulist")
    public String toList() {
        return "sys/menu/list";
    }

    @RequestMapping("/getmenulist")
    @ResponseBody
    public Result<List<SysMenu>> getMenuList() {
        return createSuccessResult(sysMenuService.getMenuList());
    }


    @RequestMapping("/savemenu")
    @ResponseBody
    public Result<Boolean> saveMenu(SysMenu sysMenu) {
        if(StringUtils.isBlank(sysMenu.getId())) {
            sysMenu.setCreateBy(ShiroUtils.getUserId());
            return createSuccessResult(sysMenuService.saveMenu(sysMenu));
        } else {
            sysMenu.setUpdateBy(ShiroUtils.getUserId());
            return createSuccessResult(sysMenuService.updateMenu(sysMenu));
        }
    }

    @RequestMapping("/getmenu")
    @ResponseBody
    public Result<SysMenu> getMenu(String menuId) {
        return createSuccessResult(sysMenuService.getMenu(menuId));
    }

    @RequestMapping("/disablemenu")
    @ResponseBody
    public Result<Boolean> disableMenu(String menuId) {
        return createSuccessResult(sysMenuService.disableMenu(menuId, ShiroUtils.getUserId()));
    }

    @RequestMapping("/undisablemenu")
    @ResponseBody
    public Result<Boolean> unDisableMenu(String menuId) {
        return createSuccessResult(sysMenuService.unDisableMenu(menuId, ShiroUtils.getUserId()));
    }

    @RequestMapping("/del")
    @ResponseBody
    public Result<Boolean> del(String menuId) {
        return createSuccessResult(sysMenuService.del(menuId, ShiroUtils.getUserId()));
    }

}

