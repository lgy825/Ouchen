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
        <div class="b_title">查看用户</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="uid" type="hidden" value="${uid}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="clearfix">
                    <div class="align-r">公司</div>
                    <select id="companySel" class="select ml20 wid-238" disabled>
                    </select>
                    <span class="color-lred ml8">* 选择用户所属院线/影投公司</span>
                </div>
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="userName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入姓名" disabled>
                    <span class="color-lred ml8 none">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">用户名</div>
                    <input id="loginName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户名" disabled>
                    <span class="color-lred ml8 none">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">登录密码</div>
                    <input id="upassword" maxlength="10" type="text" class="inpW set-inpwid ml8" placeholder="请输入登录密码" disabled>
                    <span class="color-lred ml8 none">* 不超过10个字母或数字，不能出现特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="uemail" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户名" disabled>
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="uphone" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话" disabled>
                    <span class="color-lred ml8 none">请输入用户联系电话，可以为固话也可以为手机号，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邀请码</div>
                    <input id="uinviteCode" type="text" disabled="disabled" class="inpW set-inpwid ml8" disabled>
                    <span class="color-lred ml8">邀请码由系统生成，不可编辑，用于推广人员进行绩效统计</span>
                </div>
                <!-- 可管理影院 -->
                <div class="mt12">
                    <div class="cinema_box">
                        <div class="b_label lab_wid1 relative">可管理影院</div>
                        <span cinemaradio="1" class="cinemar checkBtn radio mr20 on" data-i="0">全部影院</span>
                        <span cinemaradio="2" class="cinemar checkBtn radio" data-i="1">指定影院</span>
                    </div>
                    <div id="p_selCimemaPan" class="pl88 mt10 p_selCimema none">
                        <input id="pricinemanamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入影院名称" disabled>
                        <span class="color-lred">* 请至少选择一家影城，影院选择决定用户可管理的影院数据权限</span>
                        <div id="cinemaDiv" class="bore1 p_movBox">
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                        </div>
                    </div>
                </div>

                <!-- 用户角色 -->
                <div class="mt12 clearfix">
                    <div class="align-r fl mt6">用户角色</div>
                    <div id="p_selRolePan" class="p_selCimema fl ml8">
                        <input id="rolenamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入角色名称" disabled>
                        <span class="color-lred">* 请至少选择一种角色，角色选择决定用户可管理的影院菜单权限</span>
                        <div id="roleDiv" class="bore1 p_movBox">
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                            <%--<p><span class="checkBtn check">美嘉三里屯店</span></p>--%>
                        </div>
                    </div>
                </div>

                <div class="pl88 mt30">
                    <a href="${ctx}/sysuser/tolist.do">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回用户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/js/mod/sys/user/look.js"></script>
</html>