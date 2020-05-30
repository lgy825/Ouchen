<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增业务员信息</title>
    <%@include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新增业务员</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="personnelId" type="hidden" value="${personnelId}" />
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
                    <input id="personnelName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户姓名">
                    <span class="color-lred ml8">* 不超过15个字，不能出现其他特殊字符</span>
                </div>

                <div class="mt12">
                    <div class="align-r">性别</div>
                    <select id="personnelSex" class="select ml8">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="personnelEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8 none">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">年龄</div>
                    <input id="personnelAge" type="text" class="inpW set-inpwid ml8" placeholder="请输入年龄">
                    <span class="color-lred ml8 none">请输入业务员的年龄</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="personnelTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8 none">请输入业务员联系电话，可以为固话也可以为手机号，不可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">身份证号</div>
                    <input id="personnelIdCard" maxlength="18" type="text" class="inpW set-inpwid ml8" placeholder="请输入身份证号">
                    <span class="color-lred ml8 none">请输入业务员的身份证号，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="personnelAddr" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8">请输入用户的地址，可为空</span>
                </div>
                <div class="mt20">
                    <div class="align-r mr8">入职日期</div>
                    <input type="text" class="inpW inpWid5 timer" id="timeJoined" placeholder="入职日期">
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/personnel/toPersonnellist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回业务员列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/mod/personnel/newpersonnel.js"></script>
</html>