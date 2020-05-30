<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看信息录入</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">查看房子信息</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="hourseId" type="hidden" value="${hourseId}" />
            <div class="">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房主</div>
                    <select id="ownerSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择房子所属的房主</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">类型</div>
                    <select id="typeCodeSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6 none">* 选择房子所属的类型</span>
                </div>
                <div class="mt12">
                    <div class="align-r">房间号</div>
                    <input id="hourseNumber" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入房间号">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地区编号</div>
                    <input id="areaCode" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入地区编号">
                    <span class="color-lred ml8 none">* 不超过15个字母或数字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="hourseArea" maxlength="10" type="text" class="inpW set-inpwid ml8" placeholder="请输入房子所在地址">
                    <span class="color-lred ml8 none">* 不超过10个字母或数字，不能出现特殊字符</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r fl relative">
                        描述
                        <i class="whats define-layer"></i>
                        <p class="modify-what">房子说明</p>
                    </div>
                    <div class="text-des ml8 fl"><textarea id="hourseDesc"></textarea></div>
                    <span class="color-lred fl ml8">* 不超过100个字</span>
                </div>
                <div class="pl88 mt30">
                    <a href="${ctx}/hourse/toHourselist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回用户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/hourse/lookhourse.js"></script>
</html>