<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/mricode.pagination.css" rel="stylesheet"/>
    <script src="${ctx}/static/js/lib/jsrender.min.js"></script>
    <script src="${ctx}/static/js/lib/mricode.pagination.js"></script>
    <script src="${ctx}/static/js/lib/jsrender-converts.js"></script>
    <script id="trTmpl" type="text/x-jrender">
        <thead>
                <tr>
                    <th style='width: 10%;'><div>序号</div></th>
                    <th style='width: 10%;'><div>商品名称</div></th>
                    <th style='width: 10%;'><div>商品金额(元)</div></th>
                    <th style='width: 10%;'><div>所属项目</div></th>
                    <th style='width: 18%;'><div>类型描述</div></th>
                    <th style='width: 12%;'><div>添加时间</div></th>
                    <th style='width: 20%;'><div>操作</div></th>
                </tr>
        </thead>
        <tbody>
        {{for list}}
            <tr>
                <td>
                    <div class="">{{:#index+1}}</div>
                </td>
                <td>
                    <div>{{:productName}}</div>
                </td>
                <td>
                    <div>{{:productAmount}}</div>
                </td>
                <td>
                    <div>{{:projectName}}</div>
                </td>
                <td>
                    <div>{{:productDesc}}</div>
                </td>
                <td>
                    <div>{{dateTime:createTime}}</div>
                </td>
                <td>
                    <div class="">
                        <shiro:hasPermission name="productmana:product:edit">
                        <input type="button" onclick="editProduct('{{:id}}','{{:projectId}}','{{:productName}}','{{:productDesc}}','{{:productAmount}}')" class="edit gray_btn mr10" value="编辑">
                        </shiro:hasPermission>
                        <shiro:hasPermission name="productmana:product:del">
                        <input type="button" data-sid="{{:id}}"   class="delete gray_btn" value="删除">
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
    <!-- 卖品类别 -->
    <div class="bgc-ff">
        <div class="b_title">商品管理</div>
        <div class="hr">
            <hr>
        </div>
        <div class="select-search p20">
            <form action="">
                <div>
                    <input type="text" id="payName" class="inpW ml20"  placeholder="商品名称">
                    <input type="button" id="search" class="blue_btn ml20" value="查询">
                    <input id="resetBtn" type="button" class="blue_btn ml20" value="重置"/>
                </div>
            </form>
        </div>
        <div class="ml20 mb10">
            <shiro:hasPermission name="productmana:product:add">
            <input type="button" class="blue_btn add-btn" value="新增商品">
            </shiro:hasPermission>
        </div>
        <div class="sell-add">
            <div class="scroll-table">
                <table id="productTable" class="sell-type seller-lib modify-type" cellpadding="0"
                       cellspacing="0">
                </table>
            </div>
            <div class="page" id="tablPage">
                <div id="pagetotal" class="m-pagination"></div>
            </div>
        </div>
    </div>
</div>

<!-- 添加模态框 -->
<div class="modality-layer none">
    <div class="modality-box">
        <div class="modality-title clearfix">
            <span class="fl layer-title">新增商品</span>
            <span class="fr layer-close cursor"></span>
        </div>
        <div class="layer-line">
            <hr>
        </div>
        <div class="p20">
            <div class="">
                <div class="align-r relative">
                    商品名称
                    <i class="whats define-layer "></i>
                    <p class="modify-what">同一个项目的商品名称不可重复</p>
                </div>
                <input type="hidden" class="ids inpW set-inpwid ml8">
                <input type="text" id="productNames" class="inpW set-inpwid ml8" onclick="" placeholder="请输入商品名称"
                       maxlength="10">
                <span class="color-lred ml8">* 不超过10个字</span>
            </div>
            <div class="mt12">
                <div class="align-r relative">
                    金额
                    <i class="whats define-layer "></i>
                    <p class="modify-what">商品折扣支持小数点后两位</p>
                </div>
                <input type="text" class="productAmount inpW inp-wid ml8" placeholder="">
                <span class="color-lred ml8">* 精确到2位小数</span>
            </div>
<%--            <div class="cinema mt12">--%>
<%--                <div class="align-r relative">--%>
<%--                    所属类型--%>
<%--                    <i class="whats define-layer"></i>--%>
<%--                    <p class="modify-what">支出只可选择一种类型</p>--%>
<%--                </div>--%>
<%--                <form action="" class="inline-block ml8">--%>
<%--                    <div class="">--%>
<%--                        <select class="select select-wid" id="typeSel">--%>
<%--                        </select>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
            <div class="cinema mt12">
                <div class="align-r relative">
                    所属项目
                    <i class="whats define-layer"></i>
                    <p class="modify-what">支出只可选择一个项目</p>
                </div>
                <form action="" class="inline-block ml8">
                    <div class="">
                        <select class="select select-wid" id="projectSel">
                        </select>
                    </div>
                </form>
            </div>
            <div class="mt12 clearfix">
                <div class="align-r fl relative">
                    描述
                    <i class="whats define-layer none-bg"></i>
                </div>
                <div class="text-des ml8 fl"><textarea id="productDesc"></textarea></div>
            </div>
            <div class="layer-prompt">
                <input type="button" class="add blue_btn blue_btn30 mt20 mb10" onclick="saveProduct()" value="添加">
                <input type="button" class="update blue_btn blue_btn30 mt20 mb10" onclick="updateProduct()" value="修改">
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/static/js/mod/product/productlist.js"></script>
<%--<script type="text/javascript" src="${ctx}/static/js/lib/ss_helper.js"></script>--%>
<script>
    $(function () {
        loadProject();
        // loadType();
        // function loadType() {
        //     $("#typeSel").select2({placeholder: '请选择所属支出类型'});
        //     $("#typeSel").append("<option value='-1'>*支出类型*</option>");
        //     $("#typeSel").append("<option value='0'>日支出</option>");
        //     $("#typeSel").append("<option value='1'>月支出</option>");
        //
        // }
        function loadProject() {
            $.ajax({
                       url: ctx + "project/getpage",
                       type: "GET",
                       cache: false,
                       async: false,
                       dataType: 'json',
                       data: {
                           pageIndex: 1,
                           pageSize: 99999
                       },
                       success: function (data) {
                           if (data && data.resultCode === '0') {
                               // // 城市列表
                               $("#projectSel").select2({placeholder: '请选择所属项目'});
                               $("#projectSel").append("<option value='-1'>*所属项目*</option>");
                               $(data.resultData.list).each(function (idx, pro) {
                                   $("#projectSel").append("<option value='" + pro.id + "'>" + pro.projectName + "</option>");
                               });
                           }else {
                               if (data.resultDesc) {
                                   layer.msg(data.resultDesc);
                               } else {
                                   layer.msg('查询失败 !');
                               }
                           }
                       },
                       error: function () {
                           layer.msg('查询失败 !');
                       }
                   });
        }

        // 控制模态框
        $('.add-btn').on('click', function () {
            $('.modality-layer').show();
            $("#productNames").val("");
            $(".productAmount").val("");
            $("#productDesc").val("");
            $(".update").hide();
        });
        $('.layer-close').on('click', function () {
            $('.modality-layer').hide();
            $(".cinema").show();
            $('.add').show();
            $(".update").show();

            $("#productNames").val("");
            $("#productDesc").val("");
            $(".productAmount").val("");
            $(".prodcutNames").removeAttr("disabled");

        });

        fullScreen($('.scroll-table'), 264);

        // 屏幕发生改变时重新加载
        $(window).on('resize', function () {
            fullScreen($('.scroll-table'), 264);
        });


    });
    function toCancel() {
        $("#productNames").val("");
        $(".productAmount").val("");
        $("#productDesc").val("");
        $('.modality-layer').hide();
        $(".appName").removeAttr("disabled");
    }
    function editProduct(id,projectId,productName,productDesc,productAmount) {
        $('.modality-layer').show();
        $("#productNames").val(productName);
        $(".productAmount").val(productAmount);
        $("#productDesc").val(productDesc);
        $(".ids").val(id);
        $("#projectSel").attr('code', projectId);
        //$("#typeSel").attr('code', payType);
        $(".cinema").hide();
        $('.add').hide();
    }
</script>
</body>
</html>