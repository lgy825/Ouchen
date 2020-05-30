<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建角色</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建角色</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="rid" type="hidden" value="${rid}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="">
                    <div class="align-r">角色名称</div>
                    <input disabled="disabled" id="roleName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入角色名">
                    <span class="color-lred ml8">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">描述</div>
                    <input disabled="disabled" id="roleDesc" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入描述信息">
                    <span class="color-lred ml8">* 角色具体描述，不超过20个字</span>
                </div>
                <div class="mt12">
                    <div class="align-r">公司</div>
                    <select id="companySel" class="select ml8 wid-238">
                    </select>
                    <span class="color-lred ml8">* 选择角色所属项目</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r fl mt6">权限配置</div>
                    <div class="ml8 fl clearfix">
                        <div class="fl module has-bd">
                            <div class="bgc-f4 fw menu-head">模块</div>
                            <ul id="menu1" class="new-role" style="overflow-y: scroll; height: 466px;">
                            </ul>
                        </div>
                        <div class="fl menu ml20 has-bd">
                            <div class="bgc-f4 fw menu-head">菜单权限</div>
                            <ul id="menu2" class="new-role" style="overflow-y: scroll; height: 466px;">
                            </ul>
                        </div>
                        <div class="fl operate ml20 has-bd">
                            <div class="bgc-f4 fw menu-head">操作权限</div>
                            <div class="third-menu">
                                <ul id="menu3" class="new-role clearfix" style="overflow-y: scroll; height: 466px;">
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="pl88 mt30">
                    <a href="${ctx}/sysrole/toRolelist">
                        <input id="returnBtn" type="button" class="gray_btn gray-btn30 ml20" value="返回角色列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->

<script type="text/javascript" src="${ctx}/static/js/mod/sys/role/look.js"></script>
</html>