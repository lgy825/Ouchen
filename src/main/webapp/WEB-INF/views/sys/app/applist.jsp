<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>版本管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet" />
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 8%;'><div>所属公司</div></th>
                    <th style='width: 7%;'><div>app类型</div></th>
                    <th style='width: 12%;'><div>版本号</div></th>
                    <th style='width: 15%;'><div>下载网址</div></th>
                    <th style='width: 10%;'><div>客服电话</div></th>
                    <th style='width: 10%;'><div>微信</div></th>
                    <th style='width: 8%;'><div>版本状态</div></th>
                    <th style='width: 10%;'><div>添加时间</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div>{{:companyName}}</div>
                </td>
                <td>
                    <div>{{:appType}}</div>
                </td>
                <td>
                    <div>{{:verNo}}</div>
                </td>
                <td>
                    <div>{{:downloadPath}}</div>
                </td>
                <td>
                    <div>{{:appTel}}</div>
                </td>
                <td>
                    <div>{{:appWeixin}}</div>
                </td>
                <td>
                    <div>
                        {{if status == '0'}}
                            使用中
                        {{else status == '1'}}
                            未使用
                        {{/if}}
                    </div>
                </td>
                <td>
                    <div>{{dateTime:createTime}}</div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="sysmgr:app:look">
                        <a href="${ctx}/owner/tolook?id={{:id}}">
                            <input type="button" class="lookbtn gray_btn mr10" value="查看">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:app:edit">
                        <a href="${ctx}/appmana/toedit?id={{:id}}">
                            <input type="button" class="editbtn gray_btn mr10" value="编辑">
                        </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sysmgr:app:disable">
                        {{if status == 0}}
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
        <div class="b_title">版本管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="pdtrl20">
            <shiro:hasPermission name="sysmgr:app:add">
            <a href="${ctx}/appmana/toaddApp">
                <input type="button" class="blue_btn" value="新增版本">
            </a>
            </shiro:hasPermission>
            <a href="" class="none">
                <input type="button" class="gray_btn ml20" value="功能介绍">
            </a>
        </div>
        <div class="select-search pdtrl20">
            <form action="">
                <div>
                    <select class="select  ml20" id="companySel">
                    </select>
                    <input id="searchBtn" type="button" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置">
                </div>
            </form>
        </div>
        <div class="pdtrl20">
            <div class="scroll-table">
                <table id="appTable" class="seller-lib sell-type vip-type tr-bg" cellpadding="0" cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/sys/app/applist.js"></script>
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