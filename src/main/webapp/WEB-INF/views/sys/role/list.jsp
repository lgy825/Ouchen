<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
            <tr>
                <th style='width: 20%;'><div>角色名称</div></th>
                <th style='width: 25%;'><div>所属公司</div></th>
                <th style='width: 15%;'><div>描述</div></th>
                <th style='width: 15%;'><div>添加时间</div></th>
                <th style='width: 25%;'><div>操作</div></th>
            </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td><div>{{:roleName}}</div></td>
                <td><div>{{:companyName}}</div></td>
                <td><div>{{:roleDesc}}</div></td>
                <td><div>{{dateTime:createTime}}</div></td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="sysmgr:role:look">
                        <a href="${ctx}/sysrole/tolook?id={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="查看">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:role:edit">
                        <a href="${ctx}/sysrole/toedit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:role:startstop">
                        {{if status == 1}}
                        <input type="button" class="shutbtn gray_btn mr10" data-sid="{{:id}}" value="停用">
                        {{else}}
                        <input type="button" class="openbtn gray_btn mr10" data-sid="{{:id}}" value="启用">
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
        <div class="b_title">角色管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="sysmgr:role:add">
            <a href="${ctx}/sysrole/toadd">
                <input type="button" class="blue_btn" value="新建角色">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <select id="companySel" class="select set-width">
                    </select>
                    <input id="roleName" type="text" class="inpW ml20" placeholder="角色名称">
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="pricetabl" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/sys/role/rolelist.js"></script>
<script type="text/javascript" src="${ctx}/static/js/lib/ss_helper.js"></script>
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