$(function () {

    loadOrder();
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