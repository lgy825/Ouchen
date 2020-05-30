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
            <input id="ownerId" type="hidden" value="${ownerId}" />
            <div class="">
                <div class="mt12">
                    <div class="align-r">用户名</div>
                    <input id="ownerName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户名">
                    <span class="color-lred ml8 none">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">登录密码</div>
                    <input id="ownerPwd" maxlength="10" type="text" class="inpW set-inpwid ml8" placeholder="请输入登录密码">
                    <span class="color-lred ml8 none">* 不超过10个字母或数字，不能出现特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="ownerEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="ownerTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8 none">请输入用户联系电话，可以为固话也可以为手机号，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="ownerAddr" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8 none">请输入用户的地址，可为空</span>
                </div>
                <div class="pl88 mt30">
                    <a href="${ctx}/owner/toOwnerlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回用户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/owner/look.js"></script>
</html>