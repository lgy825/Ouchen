<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>新增委托合同</title>
    <%@include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<input type="hidden" id="contractId" value="${contractId}"/>
<div class="p20">
    <div class="bgc-ff min620">
        <h1 class="b_title">新增委托合同</h1>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix limitBox">
                <span class="b_label lab_wid1 fl">合同基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <%--                        <p>--%>
                        <%--                            <div class="b_label lab_wid1 relative">合同所属项目:</div>--%>
                        <%--                            <select class="select  ml20" id="projectSel">--%>
                        <%--                            </select>--%>
                        <%--                        </p>--%>
                        <p>
                            <div class="b_label lab_wid1 relative">合同名称:</div>
                            <input id="contractName" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="合同名称">
                            <span class="color-lred">* 请输入合同名称，不超过16字 </span>
                        </p>
                        <%--                        <p class="mt12">--%>
                        <%--                            <div class="b_label lab_wid1 relative">合同类型:</div>--%>
                        <%--                            <input id="contractType" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="合同类型" value="租赁合同">--%>
                        <%--                            <span class="color-lred none">* 请选择合同类型 </span>--%>
                        <%--                        </p>--%>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">合同编码:</div>
                            <input id="contractCode" type="text" class="inpW inpWid4 mr8" maxlength="64" placeholder="合同编码">
                            <span class="color-lred">* 请输入合同编码 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">请选择我方身份:</div>
                            <select id="contractIdentity" class="select mr8">
                                <option value="1">甲方</option>
                                <option value="2">乙方</option>
                                <option value="3">其它</option>
                            </select>
                            <span class="color-lred">* 请选择我方身份 </span>
                        </p>
                        <p class="mt12">
                        <div class="b_label lab_wid1 relative">合同开始时间:</div>
                            <input type="text" class="inpW inpWid3 timer" id="timeContractStart" placeholder="合同开始时间">
                            <div class="b_label lab_wid1 relative">合同结束时间:</div>
                            <input type="text" class="inpW inpWid3 timer" id="timeContractEnd" placeholder="合同结束时间">
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">运营方式:</div>
                            <input id="operativeWay" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="运营方式">
                            <span class="color-lred">* 请输入运营方式，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">签约时间:</div>
                            <input type="text" class="inpW inpWid4 timer" id="timeContract" placeholder="签约时间">
                            <span class="color-lred">* 请选择签约时间</span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix limitBox">
                <span class="b_label lab_wid1 fl">甲方基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <p>
                            <div class="b_label lab_wid1 relative">甲方(委托方)::</div>
                            <input id="contractExcute" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="委托方">
                            <span class="color-lred">* 请输入甲方委托方信息，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">身份证号(统一社会信用代码):</div>
                            <input id="excuteIDcard" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="身份证号(统一社会信用代码)">
                            <span class="color-lred">* 请输入甲方身份证号(统一社会信用代码) </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">代理人:</div>
                            <input id="excuteProxy" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="代理人">
                            <span class="color-lred">* 请输入甲方代理人信息，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">联系电话:</div>
                            <input id="excuteTel" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="联系电话">
                        <%--                            <span class="color-lred">* 请输入甲方联系电话，不超过16字符 </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">地址:</div>
                            <input id="excuteAddr" type="text" class="inpW inpWid4 mr8" maxlength="64" placeholder="地址">
                        <%--                            <span class="color-lred"> 请输入甲方地址(可不填) </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">邮箱:</div>
                            <input id="excuteEmail" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="邮箱">
                        <%--                            <span class="color-lred">* 请输入甲方邮箱(可不填) </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">开户行:</div>
                            <input id="excuteOpeningBank" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="开户行">
                            <span class="color-lred">* 请输入甲方开户行 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">户名:</div>
                            <input id="banksName" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="户名">
                            <span class="color-lred">* 请输入甲方户名，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">账号:</div>
                            <input id="bankNumber" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="账号">
                            <span class="color-lred">* 请输入甲方账号 </span>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix limitBox">
                <span class="b_label lab_wid1 fl">乙方基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <p>
                            <div class="b_label lab_wid1 relative">乙方(受委托方):</div>
                            <input id="contractEntrust" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="乙方(受委托方)" value="重庆云幕酒店管理有限公司">
                            <span class="color-lred">* 请输入乙方(受委托方)，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">身份证号(统一社会信用代码):</div>
                            <input id="entrustIDcard" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="身份证号(统一社会信用代码)">
                            <span class="color-lred">* 身份证号(统一社会信用代码) </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">代理人:</div>
                            <input id="entrustProxy" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="代理人">
                        <%--                                <span class="color-lred">请输入乙方代理人姓名(可不填)，不超过16字符 </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">联系电话:</div>
                            <input id="entrustTel" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="联系电话">
                        <%--                                <span class="color-lred">请输入乙方联系电话(可不填)，不超过16字符 </span>--%>
                        </p>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="dashed">
        <hr>
    </div>
    <div class="p20">
        <div class="pl70 clearfix limitBox">
            <span class="b_label lab_wid1 fl">房屋基本信息</span>
            <div class="pl88 w764">
                <div class="bore6 p20">
                    <p>
                        <div class="b_label lab_wid1 relative">房屋地址:</div>
                        <input id="hourseAddr" type="text" class="inpW inpWid4 mr8" maxlength="64" placeholder="房屋地址">
                    <%--                            <span class="color-lred">请输入房屋地址(可不填) </span>--%>
                    </p>
                    <p class="mt12">
                        <div class="b_label lab_wid1 relative">房屋面积(建筑面积):</div>
                        <input id="hourseArea" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="房屋面积(建筑面积)">
                        <span class="color-lred">* 请输入房屋面积 </span>
                    </p>
                    <p class="mt12">
                        <div class="b_label lab_wid1 relative">房屋用途:</div>
                        <input id="hourseUses" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="房屋用途">
                    <%--                            <span class="color-lred">* 请输入房屋用途(可不填)，不超过16字符 </span>--%>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <div class="tc mt30">
        <input type="button" class="blue_btn blue_btn30 " id="saveBtn"  value="完成创建">
        <a href="${ctx}/contract/toTrustContractlist">
            <input type="button" class="gray_btn gray-btn30 ml20" value="返回租赁合同列表">
        </a>
    </div>
</div>
</div>
</div>
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script src="${ctx}/static/js/mod/contract/newtrustcontract.js"></script>
</body>
</html>