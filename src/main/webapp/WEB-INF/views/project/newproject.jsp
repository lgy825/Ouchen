<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建项目</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建项目</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="projectId" type="hidden" value="${projectId}" />
            <div class="">
                <div class="mt12">
                    <div class="align-r">项目名称</div>
                    <input id="projectName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入项目名称">
                    <span class="color-lred ml8">*必填项，不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">项目类型</div>
                    <select id="typeCodeSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 请选择项目类型，必选</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">*项目所属公司*</div>
                    <select id="companySel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 请选择所属公司，必选</span>
                </div>
                <div class="mt12">
                    <div class="align-r">联系电话</div>
                    <input id="projectTel" maxlength="11" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8">* 必填项，请输入用户联系电话，可以为固话也可以为手机号</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="projectAddr" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8">* 请输入用户的地址，可为空</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r fl relative">
                        项目描述
                        <i class="whats define-layer"></i>
                        <p class="modify-what">项目描述</p>
                    </div>
                    <div class="text-des ml8 fl"><textarea id="projectDesc"></textarea></div>
                    <span class="color-lred fl ml8">* 不超过100个字，可为空</span>
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/project/toProjectlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回用户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/project/newproject.js"></script>
</html>