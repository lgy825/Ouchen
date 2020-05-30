<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>公司管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 13%;'><div>公司code</div></th>
                    <th style='width: 10%;'><div>公司名称</div></th>
                    <th style='width: 17%;'><div>公司描述</div></th>
                    <th style='width: 10%;'><div>项目数</div></th>
                    <th style='width: 10%;'><div>添加时间</div></th>
                    <th style='width: 10%;'><div>创建人</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:companyCode}}</div>
                </td>
                <td>
                    <div>{{:companyName}}</div>
                </td>
                <td>
                    <div>{{:companyDescription}}</div>
                </td>
                <td>
                    <div>{{:projectCount}}</div>
                </td>
                <td>
                    <div>{{dateTime:createTime}}</div>
                </td>
                <td>
                    <div>{{:createBy}}</div>
                </td>

                <td>
                    <div class="">
                        <shiro:hasPermission name="sysmgr:company:edit">
                        <a href="${ctx}/company/toEdit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:company:del">
                        <input type="button" class="delete gray_btn mr10" data-sid="{{:id}}" value="删除">
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
        <div class="b_title">项目管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="compnymana:company:add">
            <a href="${ctx}/company/toAddCompany">
                <input type="button" class="blue_btn" value="新建公司">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <input id="companyName" type="text" class="inpW ml20" placeholder="公司名称">
<%--                    <input id="uTel" type="text" class="inpW ml20" placeholder="电话">--%>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="companyTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/company/companylist.js"></script>
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