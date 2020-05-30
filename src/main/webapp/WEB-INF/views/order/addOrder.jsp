<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>创建订单</title>
    <%@ include file="/static/commons/common.jspf" %>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 订单管理 -->
        <div class="b_title">创建订单</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20 relative clearfix">
            <div class="fl">
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">所属项目</div>
                    <select id="projectSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择所属的项目，必选</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">支付方式</div>
                    <select id="paySel" class="select ml16 wid-238">

                    </select>
                    <span class="color-lred ml8 mt6">* 支付方式*</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">订单来源</div>
                    <select id="sourceSel" class="select ml16 wid-238">

                    </select>
                    <span class="color-lred ml8 mt6">* 请选择订单来源*</span>
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房屋类型</div>
                    <select id="typeCodeSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 选择房子的类型*</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">房间号</div>
                    <select id="hourseSel" class="select ml16 wid-238">
                    </select>
                    <span class="color-lred ml8 mt6">* 请选择所属房间*</span>
                </div>
                <div class="mt12">
                    <div class="align-r">房费</div>
                    <input id="orderActAmount" maxlength="5" type="text" class="inpW set-inpwid ml8" placeholder="请输入房费">
                    <span class="color-lred ml8 mt6">* 不超过5个字，不能出现其他特殊字符</span>
                </div>
                <!-- 选择支出 -->
                <div class="mt12">
                    <div class="cinema_box">
                        <div class="b_label lab_wid1 relative">选择支出</div>
                        <span cinemaradio="1" class="cinemar checkBtn radio mr20 on" data-i="0">暂不选择</span>
                        <span cinemaradio="2" class="cinemar checkBtn radio" data-i="1">指定支出</span>
                    </div>
                    <div id="p_selCimemaPan" class="pl88 mt10 p_selCimema none">
                        <div class="mt10">
                            <input id="paySearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入支出名称"/>
                            <span class="color-lred">* 输入支出名称搜索 </span>
                            <div class="bore1 p_movBox p_movBox2">
                                <table class="oInfo_table">
                                    <thead>
                                    <tr>
                                        <th style="width: 12%"><div>选择</div></th>
                                        <th style="width: 20%"><div>支出名称</div></th>
                                        <th style="width: 23%"><div>支出单价</div></th>
                                        <th style="width: 23%"><div>支出数量</div></th>
                                        <th style="width: 40%"><div>支出描述</div></th>
                                    </tr>
                                    </thead>
                                    <tbody id="payTbody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 选择支出 -->
                <div class="mt12">
                    <div class="product_box">
                        <div class="b_label lab_wid1 relative">选择商品</div>
                        <span productradio="1" class="productr checkBtn radio mr20 on" data-i="0">暂不选择</span>
                        <span productradio="2" class="productr checkBtn radio" data-i="1">指定商品</span>
                    </div>
                    <div id="p_selProductPan" class="pl88 mt10 p_selProduct none">
                        <div class="mt10">
                            <input id="productSearch" type="text" class="inpW inpWid1 mr8" placeholder="请输入商品名称"/>
                            <span class="color-lred">* 输入商品名称搜索 </span>
                            <div class="bore1 p_movBox p_movBox2">
                                <table class="oInfo_tables">
                                    <thead>
                                    <tr>
                                        <th style="width: 12%"><div>选择</div></th>
                                        <th style="width: 20%"><div>商品名称</div></th>
                                        <th style="width: 23%"><div>商品单价</div></th>
                                        <th style="width: 23%"><div>商品数量</div></th>
                                        <th style="width: 40%"><div>商品描述</div></th>
                                    </tr>
                                    </thead>
                                    <tbody id="productTbody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="mt20">
                    <div class="align-r mr8">订单时间</div>
                    <input type="text" class="inpW inpWid5 timer" id="timeSpick" placeholder="开始日期">
                    <input type="text" class="times inpW inpWid2 timer"  id="stimes" placeholder="开始时间">
                    <span class="zhi">至</span>
                    <input type="text" class="inpW inpWid5 timer" id="timeEpick" placeholder="结束日期">
                    <input type="text" class="times inpW inpWid2 timer"  id="etimes" placeholder="结束时间">
                    <span class="color-lred ml8">* 结束日期不能早于开始日期</span>
                </div>
                <!-- 必填选项 -->
                <div class="mt12 clearfix">
                    <div class="align-r mr8 mt6">订单状态</div>
                    <select id="orderStatus" class="select ml16 wid-238">

                    </select>
                    <span class="color-lred ml8 mt6">* 请选择订单状态*</span>
                </div>
                <div class="mt20 none" id="completeStr">
                    <div class="align-r mr8">订单所属日期</div>
                    <input type="text" class="inpW inpWid5 timer" id="timeComplete" placeholder="订单所属日期">
                </div>
                <div class="mt30 ml170">
                    <input type="button" class="blue_btn blue_btn30 " id="saveBtn"  value="完成创建">
                    <a href="${ctx}/order/toOrderlist">
                        <input type="button" class="gray_btn gray-btn30 ml20" value="返回列表">
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" value="${orderId}" id="orderId"/>
</div>
</body>
<script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
<script src="${ctx}/static/js/mod/order/addorder.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/js/commons/common.js"></script>--%>

</html>
