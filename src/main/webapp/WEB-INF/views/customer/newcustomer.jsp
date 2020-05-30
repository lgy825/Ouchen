<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新建客户信息</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">新建客户信息</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <input id="customerId" type="hidden" value="${customerId}" />
            <div class="pl70">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">所属项目</div>
                    <select id="projectSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择所属的项目，必选</span>
                </div>
                <div class="mt12">
                    <div class="align-r">姓名</div>
                    <input id="customerName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入姓名">
                    <span class="color-lred ml8">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">客户代理人</div>
                    <input id="customerProxyName" maxlength="15" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户代理人">
                    <span class="color-lred ml8 none">* 不超过15个字，不能出现其他特殊字符</span>
                </div>
                <div class="mt12">
                    <div class="align-r">性别</div>
                    <select id="customerSex" class="select ml8">
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </div>
                <div class="mt12">
                    <div class="align-r">客户状态</div>
                    <select id="customerStatus" class="select ml8">
                        <option value="10">未签约</option>
                        <option value="20">已签约</option>
                    </select>
                </div>
                <div class="mt12">
                    <div class="align-r">邮箱</div>
                    <input id="customerEmail" type="text" class="inpW set-inpwid ml8" placeholder="请输入邮箱">
                    <span class="color-lred ml8">请输入正确的邮箱格式，可为空</span>
                </div>
                <div class="mt12">
                    <div class="align-r">电话</div>
                    <input id="customerTel" maxlength="20" type="text" class="inpW set-inpwid ml8" placeholder="请输入联系电话">
                    <span class="color-lred ml8">请输入用户联系电话，可以为固话也可以为手机号</span>
                </div>
                <div class="mt12">
                    <div class="align-r">地址</div>
                    <input id="customerAddr" maxlength="50" type="text" class="inpW set-inpwid ml8" placeholder="请输入用户地址">
                    <span class="color-lred ml8 none">请输入用户的地址，可为空</span>
                </div>
                <div class="mt12 none" id="idCardStr">
                    <div class="align-r">社会统一代码</div>
                    <input id="idCard" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户社会统一代码">
                    <span class="color-lred ml8 none">请输入客户社会统一代码</span>
                </div>
                <div class="mt12 none" id="openingBankStr">
                    <div class="align-r">客户银行开户行</div>
                    <input id="openingBank" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户银行开户行">
                    <span class="color-lred ml8">请输入客户银行开户行</span>
                </div>
                <div class="mt12 none" id="bankNameStr">
                    <div class="align-r">客户银行户名</div>
                    <input id="bankName" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户户名">
                    <span class="color-lred ml8">请输入客户银行户名</span>
                </div>
                <div class="mt12 none" id="bankNumberStr">
                    <div class="align-r">银行账号</div>
                    <input id="bankNumber" maxlength="32" type="text" class="inpW set-inpwid ml8" placeholder="请输入客户银行账号">
                    <span class="color-lred ml8">请输入客户银行账号</span>
                </div>

            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">房间信息</span>
                <div class="pl88 w764">
                    <div class="p_selBox">
                        <div class="bore6 p_selingBox mb20 p20 relative">
                            <span class="close-set" style="display: none;"></span>
                            <div class="p20">
                                <input id="roomId" class="roomId" type="hidden" />
                                <p>
                                    <div class="b_label lab_wid1">房间号:</div>
                                    <input  type="text" class="inpW inpWid4 mr8 roomNumber" maxlength="64" placeholder="房屋地址">
                                    <span class="color-lred">请输入房间号 </span>
                                </p>
                                <p class="mt12">
                                    <div class="b_label lab_wid1">房间地址:</div>
                                    <input type="text" class="inpW inpWid4 mr8 roomAddr"  maxlength="64" placeholder="房屋地址">
                                <%--                            <span class="color-lred">请输入房屋地址(可不填) </span>--%>
                                </p>
                                <p class="mt12">
                                    <div class="b_label lab_wid1">房屋面积:</div>
                                    <input  type="text" class="inpW inpWid4 mr8 roomArea" maxlength="16" placeholder="房屋面积(建筑面积)">
                                    <span class="color-lred">* 请输入房屋面积 </span>
                                </p>
                                    <p class="mt12">
                                    <div class="b_label lab_wid1">房产编码:</div>
                                    <input type="text" class="inpW inpWid4 mr8 roomSerialCode" maxlength="16" placeholder="房产编码">
                                <%--                            <span class="color-lred">* 请输入房屋用途(可不填)，不超过16字符 </span>--%>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix">
                        <input type="button" class="blue_btn new-add fr p_addBtn" value="添加房间信息">
                    </div>
                </div>
            </div>
            <div class="tc mt30">
                <div class="pl88 mt30">
                    <input id="saveBtn" type="button" class="blue_btn blue_btn30" value="创建完成">
                    <a href="${ctx}/customer/tocustomerlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回客户列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/customer/newcustomer.js"></script>
</html>