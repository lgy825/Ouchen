<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单配置</title>
    <%@include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <!-- 广告投放 -->
        <div class="b_title">菜单配置</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="clearfix">
                <!-- 模块 -->
                <div class="fl module has-bd">
                    <div class="bgc-f4 fw menu-head">模块</div>
                    <ul id="menu1" class="new-role">
                        <%--<li class="selected">--%>
                            <%--<!-- 如果操作过改选项且子菜单有被选中 添加类名color-blue 变色 -->--%>
                            <%--<span class="check checkBtn 添加类名color">工作台</span>--%>
                        <%--</li>--%>
                    </ul>
                </div>

                <!-- 菜单权限 -->
                <div class="fl menu ml20 has-bd">
                    <div class="bgc-f4 fw menu-head">菜单权限</div>
                    <!-- 数据魔方 -->
                    <ul id="menu2" class="new-role">
                        <%--<li><span class="check checkBtn">票务数据</span></li>--%>
                    </ul>
                </div>

                <!-- 操作权限 -->
                <div class="fl operate ml20 has-bd">
                    <div class="bgc-f4 fw menu-head">操作权限</div>
                    <%--<卖品管理- 卖品 -->--%>
                    <div class="third-menu">
                        <!-- 卖品库 -->
                        <ul id="menu3" class="new-role clearfix">
                            <%--<li>--%>
                                <%--<span class="check checkBtn">编辑卖品</span>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<span class="check checkBtn">商城上下架</span>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<span class="check checkBtn">购票流程上下架</span>--%>
                            <%--</li>--%>
                        </ul>
                    </div>
                </div>

                <!-- 操作 -->
                <div class="fl operate-set ml20 has-bd">
                    <div class="bgc-f4 fw menu-head">操作</div>
                    <div class="operate-wrap">
                        <input id="add1btn" type="button" class="gray_btn mr10 first-menu" value="创建一级菜单">
                        <input id="add2btn" type="button" class="gray_btn mr10 second-menu" value="创建下级菜单">
                        <input id="editbtn" type="button" class="gray_btn mr10" value="修改">
                        <input id="hidebtn" isshow="1" type="button" class="gray_btn mr10" value="隐藏">
                        <input id="delbtn" type="button" class="gray_btn mr10" value="删除">
                        <input id="add3btn" type="button" class="gray_btn mr10 add-operate" value="添加操作权限">
                    </div>
                </div>
            </div>

            <div class="pl88 mt30">
                <%--<input type="button" class="blue_btn blue_btn30" value="创建完成">--%>
                <%--<a href="javascript:;">--%>
                    <%--<input type="button" class="gray_btn gray-btn30 ml20" value="返回角色列表">--%>
                <%--</a>--%>
            </div>
        </div>
    </div>
</div>

<!-- add-menu -->
<div class="modality-layer menu-layer none">
    <div class="modality-box">
        <div class="modality-title clearfix">
            <span id="mttext" class="fl layer-title">添加菜单</span>
            <span class="fr layer-close cursor"></span>
        </div>
        <div class="layer-line">
            <hr>
        </div>
        <div class="p20">
            <!-- 可删除 -->
            <div class="">
                <input id="mid" type="hidden" value="" />
                <input id="menuType" type="hidden" value="" />
                <div id="pdiv" class="">
                    <span id="pntext">上级菜单</span>
                    <input id="parentId" type="hidden" value="" />
                    <input id="parentName" type="text" class="inpW set-inpwid ml8" disabled="disabled">
                </div>
                <div class="mt12">
                    <span id="mntext">菜单名称</span>
                    <input id="menuName" type="text" maxlength="15" class="inpW set-inpwid ml8">
                </div>
                <div class="mt12">
                    <span id="mltext">菜单链接</span>
                    <input id="menuLink" type="text" maxlength="500" class="inpW set-inpwid ml8">
                </div>
                <div id="msDiv" class="mt12">
                    <span id="mstext">菜单排序</span>
                    <input id="menuSort" type="text" class="inpW set-inpwid ml8" placeholder="用数字显示顺序，数字越大，越靠后">
                </div>
                <div id="iconDiv" class="mt12">
                    <span id="mitext">图标样式</span>
                    <input id="iconClass" type="text" class="inpW set-inpwid ml8" >
                </div>
                <div class="mt12">
                    <span>权限标识</span>
                    <input id="shiroFlag" type="text" class="inpW set-inpwid ml8" placeholder="区分大小写，谨慎填写">
                </div>
                <div class="mt12">
                    <span>是否显示</span>
                    <select id="isShow" class="select ml8">
                        <option value="1">显示</option>
                        <option value="2">隐藏</option>
                    </select>
                </div>
            </div>
            <div class="layer-prompt">
                <input id="saveBtn" type="button" class="blue_btn blue_btn30 mt20 mb10" value="保存">
                <input id="cancelBtn" type="button" class="gray_btn gray-btn30 ml20 cancel" value="取消">
            </div>
        </div>
    </div>
</div>

<!-- add-operate -->
<%--<div class="modality-layer operate-layer none">--%>
    <%--<div class="modality-box">--%>
        <%--<div class="modality-title clearfix">--%>
            <%--<span class="fl layer-title">添加菜单</span>--%>
            <%--<span class="fr layer-close cursor"></span>--%>
        <%--</div>--%>
        <%--<div class="layer-line">--%>
            <%--<hr>--%>
        <%--</div>--%>
        <%--<div class="p20">--%>
            <%--<!-- 可删除 -->--%>
            <%--<div class="">--%>
                <%--<div class="">--%>
                    <%--<span>菜单页面</span>--%>
                    <%--<input type="text" class="inpW set-inpwid ml8" placeholder="继承上级菜单名称，不可编辑">--%>
                <%--</div>--%>
                <%--<div class="mt12">--%>
                    <%--<span>操作权限</span>--%>
                    <%--<input type="text" class="inpW set-inpwid ml8">--%>
                <%--</div>--%>
                <%--<div class="mt12">--%>
                    <%--<span>操作链接</span>--%>
                    <%--<input type="text" class="inpW set-inpwid ml8">--%>
                <%--</div>--%>
                <%--<div class="mt12">--%>
                    <%--<span>操作排序</span>--%>
                    <%--<input type="text" class="inpW set-inpwid ml8" placeholder="用数字显示顺序，数字越大，越靠后">--%>
                <%--</div>--%>
                <%--<div class="mt12">--%>
                    <%--<span>是否显示</span>--%>
                    <%--<select class="select ml8">--%>
                        <%--<option value="0">显示</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="layer-prompt">--%>
                <%--<input type="button" class="blue_btn blue_btn30 mt20 mb10" value="保存">--%>
                <%--<input type="button" class="gray_btn gray-btn30 ml20 cancel" value="取消">--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

</body>

<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/sys/menu/menulist.js"></script>
</html>