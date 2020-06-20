package com.ouchen.back.controller.sys;

import com.ouchen.back.service.sys.SysRoleService;
import com.ouchen.core.base.BaseController;
import com.ouchen.core.base.Result;
import com.ouchen.core.constant.PageResult;
import com.ouchen.core.model.sys.SysRoleExt;
import com.ouchen.core.util.ShiroUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("sysrole")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/getpage")
    @ResponseBody
    public PageResult<SysRoleExt> getShopPageByCondition(HttpServletRequest request,
                                                         Integer pageIndex,
                                                         Integer pageSize,
                                                         String roleName) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        params.put("pageIndex", pageIndex + 1);
        params.put("pageSize", pageSize);
        return createSuccessPageResult(sysRoleService.getRolePageByCondition(params));
    }

    //@RequiresPermissions("sysmgr:role:list")
    @RequestMapping("/toRolelist")
    public String toList() {
        return "sys/role/list";
    }

    @RequestMapping("/toadd")
    public String toAdd() {
        return "sys/role/new";
    }

    @RequestMapping("/toedit")
    public String toEdit(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "sys/role/list";
        }
        model.addAttribute("rid", id);
        return "sys/role/new";
    }

    @RequestMapping("/tolook")
    public String toLook(String id, Model model) {
        if(StringUtils.isBlank(id)) {
            return "sys/role/list";
        }
        model.addAttribute("rid", id);
        return "sys/role/look";
    }

    @RequestMapping("/save")
    @ResponseBody
    public Result<Boolean> save(@RequestBody SysRoleExt sysroleExt) {
        if(StringUtils.isBlank(sysroleExt.getId())) {
            sysroleExt.setCreateBy(ShiroUtils.getUserId());
            return createSuccessResult(sysRoleService.saveRoleExt(sysroleExt));
        } else {
            sysroleExt.setUpdateBy(ShiroUtils.getUserId());
            return createSuccessResult(sysRoleService.updateRoleExt(sysroleExt));
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> update(SysRoleExt sysroleExt) {
        sysroleExt.setUpdateBy(ShiroUtils.getUserId());
        return createSuccessResult(sysRoleService.updateRoleExt(sysroleExt));
    }

    @RequestMapping("/get")
    @ResponseBody
    public Result<SysRoleExt> update(String id) {
        if(StringUtils.isBlank(id)) {
            return createFailedResult("查询失败");
        }
        return createSuccessResult(sysRoleService.getRoleExt(id));
    }

    @RequestMapping("/nameexist")
    @ResponseBody
    public Result<Boolean> nameExist(SysRoleExt sysRoleExt) {
        return createSuccessResult(sysRoleService.nameExist(sysRoleExt));
    }

    @RequestMapping("/disablerole")
    @ResponseBody
    public Result<Boolean> disablerole(String id) {
        return createSuccessResult(sysRoleService.disableRole(id, ShiroUtils.getUserId()));
    }

    @RequestMapping("/undisablerole")
    @ResponseBody
    public Result<Boolean> unDisableRole(String id) {
        return createSuccessResult(sysRoleService.unDisableRole(id, ShiroUtils.getUserId()));
    }

}
