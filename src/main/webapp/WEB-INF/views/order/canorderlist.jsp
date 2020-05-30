<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
    <script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 10%;'><div>订单号</div></th>
                    <th style='width: 5%;'><div>房间信息</div></th>
                    <th style='width: 8%;'><div>开始时间</div></th>
                    <th style='width: 8%;'><div>结束时间</div></th>
                    <th style='width: 5%;'><div>入住天数</div></th>
                    <th style='width: 8%;'><div>订单来源</div></th>
                    <th style='width: 8%;'><div>订单金额(元)</div></th>
                    <th style='width: 8%;'><div>实收金额(元)</div></th>
                    <th style='width: 8%;'><div>订单状态</div></th>
                    <th style='width: 8%;'><div>支付方式</div></th>
                    <th style='width: 22%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:id}}</div>
                </td>
                <td>
                    <div>{{:hourseNumber}}</div>
                </td>
                <td>
                    <div>
                           {{dateTime:orderStartDate}}
                    </div>
                </td>
                <td>
                    <div>
                           {{dateTime:orderEndTime}}
                    </div>
                </td>
                <td>
                    <div>{{:orderStatus}}</div>
                </td>
                <td>
                    <div>{{:sourceWay}}</div>
                </td>
                <td>
                    <div>
                    {{if orderRecAmount == null || orderRecAmount ==''}}
                            0
                    {{else}}
                           {{:orderRecAmount}}
                    {{/if}}
                    </div>
                </td>
                <td>
                    <div>
                    {{if orderActAmount == null || orderActAmount=='' }}
                            0
                    {{else}}
                           {{:orderActAmount}}
                    {{/if}}
                    </div>
                </td>

                <td>
                    <div>
                        {{if orderStatus == '10'}}
                            订单完成
                        {{else orderStatus == '11'}}
                             未入住
                        {{else orderStatus == '12'}}
                            已入住
                        {{else orderStatus == '13'}}
                            已取消
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div>
                        {{:payWay}}
                    </div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="ordermana:canorder:detail">
                        <a href="${ctx}/order/tolook?id={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="订单详情">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="ordermana:canorder:edit">
                        <a href="${ctx}/order/toedit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="ordermana:canorder:del">
                        <input type="button" class="delete gray_btn mr10" data-sid="{{:id}}" value="删除">
                        </shiro:hasPermission>
                        <shiro:hasPermission name="ordermana:canorder:status">
                            <input type="button" data-sid="{{:id}}"  class="editStatus gray_btn mt12 mr10" value="编辑状态">
                        </shiro:hasPermission>
                    </div>
                </td>
            </tr>
        {{/for}}
        </tbody>
    </script>
</head>
<body>
<div class="p20">
    <div class="bgc-ff min620">
        <div class="b_title">订单管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="ordermana:order:add">
            <a href="${ctx}/order/toaddOrder">
                <input type="button" class="blue_btn" value="新建订单">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="orderId" type="text" class="inpW ml20" placeholder="订单号">
                    <input id="hourseNumber" type="text" class="inpW ml20" placeholder="房间号">
                    <input type="text" class="inpW  inpWid2 timer" id="timeSpick" placeholder="开始时间"/>
                    <span class="zhi">至</span>
                    <input type="text" class="inpW inpWid2 timer" id="timeEpick" placeholder="结束时间"/>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                    <shiro:hasPermission name="ordermana:order:export">
                    <input id="exportBtn" type="button" class="blue_btn ml20" value="导出订单">
                    </shiro:hasPermission>
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="orderTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<!-- 修改订单状态 -->
<div class="modality-layer none">
    <div class="modality-box">
        <div class="modality-title clearfix">
            <span class="fl layer-title">修改订单状态</span>
            <span class="fr layer-close cursor"></span>
        </div>
        <div class="layer-line">
            <hr>
        </div>
        <div class="p20">
            <div class="cinema mt12">
                <input id="tempId" type="hidden" class="inpW ml20">
                <div class="align-r relative">
                    订单状态
                    <i class="whats define-layer"></i>
                    <p class="modify-what">订单只可选择一种状态</p>
                </div>
                <form action="" class="inline-block ml8">
                    <div class="">
                        <select class="select select-wid" id="orderSel">
                        </select>
                    </div>
                </form>
            </div>
            <div class="mt12 clearfix">
                <div class="align-r fl relative">
                    修改描述
                    <i class="whats define-layer none-bg"></i>
                </div>
                <div class="text-des ml8 fl"><textarea id="orderDesc"></textarea></div>
            </div>
            <div class="layer-prompt">
                <input type="button" class="update blue_btn blue_btn30 mt20 mb10" onclick="updateStatus()" value="修改">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/order/canorderlist.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/js/lib/ss_helper.js"></script>--%>
<script>
    $(function () {
        fullScreen($('.scroll-table'),264);

        // 屏幕发生改变时重新加载
        $(window).on('resize', function () {
            fullScreen($('.scroll-table'), 264);
        });
    });
</script>
</body>
</html>