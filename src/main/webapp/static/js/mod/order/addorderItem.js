$(function () {

    loadOrder();
    $("#saveBtn").click(function () {

        if($("#orderSel").val() == -1) {
            layer.msg("请选择所属的订单");
            return;
        }
        var oId = $("#orderSel").val();
        var dName = $.trim($("#dName").val());
        if(!dName) {
            layer.msg("请输入名称");
            return;
        } else if(!ValidUtils.validText(dName, 1, 15)) {
            layer.msg("名称不超过15个字，不能出现其他特殊字符");
            return;
        }

        var dAmount = $.trim($("#dAmount").val());
        if(dAmount==null){
            layer.msg("输入不能为空");
        }
        var tr= /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
        if(!tr.test(dAmount)){
            layer.msg('金额输入有误，请重新输入!');
            return;
        }
        var dDesc=$.trim($("#dDesc").val());
        $.ajax({
            url: ctx + "order/saveOrderItem",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                oId:oId,
                dAmount: dAmount,
                dName: dName,
                dDesc: dDesc
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "order/toOrderItemlist";
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('保存失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('保存失败 !');
            },
            beforeSend: function () {
                layer.load(1, {shade:[0.3]})
            }
        });
    });




    function loadOrder() {
        $.ajax({
            url: ctx + "order/getpage",
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
                    //
                    $("#orderSel").select2({placeholder: '*选择订单*'});
                    $("#orderSel").append("<option value='-1'>*选择订单*</option>");
                    $(data.resultData.list).each(function (idx, order) {
                        $("#orderSel").append("<option value='" + order.oId + "'>" + order.oId + "</option>");
                    });

                    // 加载数据 -------------
                    if ($("#dId").val()) {
                        $.ajax({
                            url: ctx + "order/getItem",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                dId: $("#dId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    //alert(su.oWay);
                                    $("#orderSel").val(su.oId);
                                    $("#dName").val(su.dName);
                                    $("#dAmount").val(su.dAmount);
                                    $("#dDesc").val(su.dDesc);

                                } else {
                                    if (data.resultDesc) {
                                        layer.msg(data.resultDesc);
                                    } else {
                                        layer.msg('查询失败 !');
                                    }
                                }
                            }
                        });
                    }
                } else {
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
});
