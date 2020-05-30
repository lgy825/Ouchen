<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>客户管理</title>
    <%@include file="/static/commons/common.jspf" %>

    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 10%;'><div>客户名称</div></th>
                    <th style='width: 10%;'><div>所属项目</div></th>
                    <th style='width: 10%;'><div>客户电话</div></th>
                    <th style='width: 15%;'><div>客户邮箱</div></th>
                    <th style='width: 10%;'><div>客户地址</div></th>
                    <th style='width: 10%;'><div>客户状态</div></th>
                    <th style='width: 10%;'><div>房间统计</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:customerName}}</div>
                </td>
                <td>
                    <div>{{:projectName}}</div>
                </td>
                <td>
                    <div>{{:customerTel}}</div>
                </td>
                <td>
                    <div>{{:customerEmail}}</div>
                </td>
                <td>
                    <div>{{:customerAddr}}</div>
                </td>
                <td>
                    <div>
                        {{if status == 20}}
                            已签约
                        {{else status ==10}}
                            未签约
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div>{{:customerCount}}</div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="salemanager:customer:edit">
                        <a href="${ctx}/customer/toedit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="salemanager:customer:look">
                        <a href="${ctx}/customer/tolook?id={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="查看">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="salemanager:customer:del">
                            <input type="button" class="delete gray_btn mr10" data-sid="{{:id}}" value="删除">
                        </shiro:hasPermission>
                        <shiro:hasPermission name="salemanager:customer:startstop">
                        {{if disabledFlg == 1}}
                        <input type="button" class="shutbtn gray_btn mr10" data-sid="{{:id}}" value="已签约">
                        {{else}}
                        <input type="button" class="openbtn gray_btn mt12 mr10" data-sid="{{:id}}" value="未签约">
                        {{/if}}
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
        <div class="b_title">客户管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="salemanager:customer:add">
            <a href="${ctx}/customer/toadd">
                <input type="button" class="blue_btn" value="新增客户信息">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="customerName" type="text" class="inpW ml20" placeholder="名称">
                    <select class="select  ml20" id="customerStatus">
                        <option value="-1">*请选择状态*</option>
                        <option value="10">未签约</option>
                        <option value="20">已签约</option>
                    </select>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="customerTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/customer/customerlist.js"></script>
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