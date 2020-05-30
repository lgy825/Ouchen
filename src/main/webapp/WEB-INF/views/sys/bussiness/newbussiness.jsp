<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建商务经理</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建商务经理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="bussinessId" type="hidden" value="${bussinessId}" />
            <div class="">
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="bussinessName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入商务经理姓名">
                    <span class="color-lred ml8">*必填项，不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">所属项目</div>
                    <select id="projectSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择所属的项目，必选</span>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="bussinessEmail" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="bussinessTel" maxlength="11" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8">*必填项，请输入联系电话，建议是手机号</span>
                </div>
                <div class="mt12">
                    <div class="align-r">描述</div>
                    <input id="bussinessDesc" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入该商务描述">
                    <span class="color-lred ml8">请输入该经理的描述，可为空</span>
                </div>
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/bussiness/toBussinesslist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/sys/bussiness/newbussiness.js"></script>
</html>