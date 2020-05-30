<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建用户</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建员工</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="userId" type="hidden" value="${userId}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="clearfix">
                    <div class="align-r mr8 mt6">公司</div>
                    <select id="companySel" class="select ml20 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择员工所属公司</span>
                </div>
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="userName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入姓名">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">登录名</div>
                    <input id="loginName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入登录名">
                    <span class="color-lred ml8 none">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">登录密码</div>
                    <input id="password" maxlength="10" type="text" class="inpW set-inpwid ml8" placeholder="请输入登录密码">
                    <span class="color-lred ml8 none">* 不超过10个字母或数字，不能出现特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="userEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="userTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8 none">请输入用户联系电话，可以为固话也可以为手机号，可为空</span>
                </div>
                <!-- 可管理影院 -->
                <div class="mt12">
                    <div class="cinema_box">
                        <div class="b_label lab_wid1 relative">可管理项目</div>
                        <span cinemaradio="1" class="cinemar checkBtn radio mr20 on" data-i="0">全部项目</span>
                        <span cinemaradio="2" class="cinemar checkBtn radio" data-i="1">指定项目</span>
                    </div>
                    <div id="p_selCimemaPan" class="pl88 mt10 p_selCimema none">
                        <input id="pricinemanamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入项目名称">
                        <span class="color-lred">* 请至少选择一个项目，项目选择决定用户可管理的项目数据权限</span>
                        <div id="cinemaDiv" class="bore1 p_movBox">

                        </div>
                    </div>
                </div>
                <!-- 用户角色 -->
                <div class="mt12 clearfix">
                    <div class="align-r fl mt6">用户角色</div>
                    <div id="p_selRolePan" class="p_selCimema fl ml8">
                        <input id="rolenamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入角色名称">
                        <span class="color-lred">* 请至少选择一种角色，角色选择决定用户可管理的项目菜单权限</span>
                        <div id="roleDiv" class="bore1 p_movBox none">

                        </div>
                    </div>
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/sysuser/toUserlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回员工列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/sys/user/newuser.js"></script>
</html>