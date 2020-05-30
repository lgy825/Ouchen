<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>新增租赁合同</title>
    <%@include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<input type="hidden" id="contractId" value="${contractId}"/>
<div class="p20">
    <div class="bgc-ff min620">
        <h1 class="b_title">新增租赁合同</h1>
        <div class="dashed">
            <hr>
        </div>
        <div class="p20">
            <div class="pl70 clearfix limitBox">
                <span class="b_label lab_wid1 fl">合同基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">

                        <p>
                            <div class="b_label lab_wid1 relative">合同所属项目:</div>
                            <select class="select ml16 wid-238" id="projectSel">
                            </select>
                        </p>
<%--                        <p>--%>
<%--                            <div class="b_label lab_wid1 relative">合同名称:</div>--%>
<%--                            <input id="contractName" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="合同名称">--%>
<%--                            <span class="color-lred">* 请输入合同名称，不超过16字 </span>--%>
<%--                        </p>--%>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">合同类型:</div>
                            <select id="contractType" class="select ml16 wid-238">
                                <option value="10">固定租金</option>
                                <option value="20">委托分红</option>
                            </select>
                            <span class="color-lred none">* 请选择合同类型 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">合同编码:</div>
                            <input id="contractCode" type="text" class="inpW inpWid4 mr8" maxlength="64" placeholder="合同编码">
                            <span class="color-lred">* 请输入合同编码 </span>
                        </p>
<%--                        <p class="mt12">--%>
<%--                            <div class="b_label lab_wid1 relative">请选择我方身份:</div>--%>
<%--                            <select id="contractIdentity" class="select mr8">--%>
<%--                                <option value="1">甲方</option>--%>
<%--                                <option value="2">乙方</option>--%>
<%--                                <option value="3">其它</option>--%>
<%--                            </select>--%>
<%--                            <span class="color-lred">* 请选择我方身份 </span>--%>
<%--                        </p>--%>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">合同开始时间:</div>
                            <input type="text" class="inpW inpWid3 timer" id="timeContractStart" placeholder="合同开始时间">
                            <div class="b_label lab_wid1 relative">合同结束时间:</div>
                            <input type="text" class="inpW inpWid3 timer" id="timeContractEnd" placeholder="合同结束时间">
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">运营方式:</div>
                            <select id="operativeWay" class="select ml16 wid-238">
                                <option value="10">二八分红</option>
                                <option value="20">三七分红</option>
                                <option value="30">固定租金</option>
                            </select>
                            <span class="color-lred">* 请输入运营方式，不超过16字符 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">打款方式:</div>
                            <select id="payType" class="select ml16 wid-238">
                                <option value="10">月付</option>
                                <option value="20">季付</option>
                                <option value="20">半年付</option>
                            </select>
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
            <div class="pl70 clearfix">
                <span class="b_label lab_wid1 fl">租金/收益明细</span>
                <div class="pl88 w764">
                    <div class="p_selBox">
                        <div class="bore6 p20 p_selingBox mb20 relative">
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">免租天数:
                                    <i class="whats"></i>
                                    <p class="modify-what">此天数是工作日</p>
                                </div>
                                <input id="rentFreeCount" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="请输入免租天数">
                            </p>
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">免租时间:</div>
                                <input type="text" class="inpW inpWid3 timer" id="timeRentFreeStart" placeholder="开始日期">
                                <div class="b_label"> 至 </div>
                                <input type="text" class="inpW inpWid3 timer" id="timeRentFreeEnd" placeholder="结束日期">
                            </p>
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">租金/收益(元/月):</div>
                                <input id="rentAmount" type="text" class="inpW inpWid4 mr8" maxlength="8" placeholder="租金/收益">
                                <span class="color-lred">* 请输入租金，不超过16字符 </span>
                            </p>
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">递增方式:</div>
                                <input id="rentIncreaseWay" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="递增方式">
                                <span class="color-lred">* 请输入递增方式，不超过16字符 </span>
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
                <span class="b_label lab_wid1 fl">甲方基本信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <p>
                            <div class="b_label lab_wid1 relative">甲方(委托方)::</div>
                            <select id="customerSel" class="select ml16 wid-238">
                            </select>
                            <span class="color-lred">* 请选择客户信息 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">身份证号(统一社会信用代码):</div>
                            <input id="idCard" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="委托方">
                            <span class="color-lred">* 请输入甲方身份证号(统一社会信用代码) </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">代理人:</div>
                            <input id="customerProxyName" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="代理人">
                            <span class="color-lred">* 请输入甲方代理人信息，不超过16字符 </span>
                           </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">联系电话:</div>
                            <input id="customerTel" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="联系电话">
<%--                            <span class="color-lred">* 请输入甲方联系电话，不超过16字符 </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">地址:</div>
                            <input id="customerAddr" type="text" class="inpW inpWid4 mr8" maxlength="64" placeholder="地址">
<%--                            <span class="color-lred"> 请输入甲方地址(可不填) </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">邮箱:</div>
                            <input id="customerEmail" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="邮箱">
<%--                            <span class="color-lred">* 请输入甲方邮箱(可不填) </span>--%>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">开户行:</div>
                            <input id="openingBank" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="开户行">
                            <span class="color-lred">* 请输入甲方开户行 </span>
                        </p>
                        <p class="mt12">
                            <div class="b_label lab_wid1 relative">户名:</div>
                            <input id="bankName" type="text" class="inpW inpWid4 mr8" maxlength="16" placeholder="户名">
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
                                <select id="companySel" class="select ml20 wid-238">
                                </select>
                                <span class="color-lred">* 请选择乙方信息 *</span>
                            </p>
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">身份证号(统一社会信用代码):</div>
                                <input id="entrustIDcard" type="text" class="inpW inpWid4 mr8" maxlength="32" placeholder="身份证号(统一社会信用代码)" >
                                <span class="color-lred none">* 身份证号(统一社会信用代码) </span>
                            </p>
                            <p class="mt12">
                                <div class="b_label lab_wid1 relative">业务代表:</div>
                                <select id="personnelSel" class="select ml20 wid-238">
                                </select>
                                <span class="color-lred">* 请选择业务代表 *</span>
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
                <span class="b_label lab_wid1 fl">房间信息</span>
                <div class="pl88 w764">
                    <div class="bore6 p20">
                        <p>
                            <div class="align-r fl mt6">选择关联房间</div>
                            <div id="p_selRolePan" class="p_selCimema fl ml8">
                                <input id="rolenamesearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入房间号">
                                <span class="color-lred">* 请至少选择个房间</span>
                                <div id="roleDiv" class="bore1 p_movBox none">

                                </div>
                            </div>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div class="tc mt30">
            <input type="button" class="blue_btn blue_btn30 " id="saveBtn"  value="完成创建">
            <a href="${ctx}/contract/toRentContractlist">
                <input type="button" class="gray_btn gray-btn30 ml20" value="返回租赁合同列表">
            </a>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script src="${ctx}/static/js/mod/contract/newrentcontract.js"></script>
</body>
</html>