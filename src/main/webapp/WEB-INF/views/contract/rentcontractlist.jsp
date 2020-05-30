<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>合同管理</title>
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
                    <th style='width: 10%;'><div>合同类型</div></th>
                    <th style='width: 10%;'><div>销售代表</div></th>
                    <th style='width: 10%;'><div>客户名称</div></th>
                    <th style='width: 10%;'><div>租金</div></th>
                    <th style='width: 15%;'><div>免租期</div></th>
                    <th style='width: 10%;'><div>创建人</div></th>
                    <th style='width: 15%;'><div>合同期限</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>
                       {{if contractType == 10}}
                            固定租金
                        {{else contractType ==20}}
                            收益分成
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div>{{:personnelName}}</div>
                </td>
                <td>
                    <div>{{:customerName}}</div>
                </td>
                <td>
                    <div>
                        {{:rentAmount}}
                    </div>
                </td>
                <td>
                    <div>
                           {{dateTime:rentFreeStartTime}}至{{dateTime:rentFreeEndTime}}
                    </div>
                </td>
                <td>
                    <div>{{:createName}}</div>
                </td>
                <td>
                    <div>
                           {{dateTime:contractStartTime}}至{{dateTime:contractStartTime}}
                    </div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="contract:rent:edit">
                        <a href="${ctx}/contract/toeditRent?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="contract:rent:look">
                            <a href="${ctx}/contract/tolookRent?id={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="查看">
                            </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="contract:rent:del">
                        <input type="button" class="deleteRent gray_btn mr10" data-sid="{{:id}}" value="删除">
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
        <div class="b_title">合同管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="contract:rent:add">
            <a href="${ctx}/contract/toaddRent">
                <input type="button" class="blue_btn" value="新建合同">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
<%--                    <select class="select  ml20" id="projectSel">--%>
<%--                    </select>--%>
                    <input id="contractCode" type="text" class="inpW ml20" placeholder="合同编码">
                    <select class="select  ml20" id="projectSel">

                    </select>
                    <select class="select  ml20" id="contractType">
                        <option value="-1">请选择合同类型</option>
                        <option value="10">固定租金</option>
                        <option value="20">委托分红</option>
                    </select>
                    <input type="text" class="inpW ml20 timer" id="timeSpick" placeholder="开始时间"/>
                    <span class="zhi">至</span>
                    <input type="text" class="inpW timer" id="timeEpick" placeholder="结束时间"/>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="contractTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/contract/rentcontractlist.js"></script>
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