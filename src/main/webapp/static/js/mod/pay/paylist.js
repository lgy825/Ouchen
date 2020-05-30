$(function(){


    loadPage();
    $("#search").click(function () {
        loadPage();
    });

    $("#resetBtn").click(function () {
        $("#payName").val("");
        loadPage();
    });

    $("#payTable").on("click", ".delete", function (){
        var sid = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "pay/delete",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {hId: sid},
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg('删除成功 !');
                        loadPage();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('删除失败 !');
                        }
                    }
                },
                error: function () {
                    layer.msg('删除失败 !');
                }
            });
        });
    });

});

function loadPage() {
    if($("#pagetotal").pagination()) {
        $("#pagetotal").pagination('destroy');
    }
    $("#pagetotal").pagination({
        pageSize: 10,
        pageElementSort: ['$info', '$page', '$size', '$jump'],
        showInfo: true,
        infoFormat: '共 {total} 数据',
        showJump: true,
        jumpBtnText: '跳转',
        noInfoText: '无数据',
        showPageSizes: true,
        pageSizeItems: [10, 30, 50],
        firstBtnText: '首页',
        lastBtnText: '尾页',
        prevBtnText: '上一页',
        nextBtnText: '下一页',
        remote: {
            url: ctx + 'pay/getpage',
            params:{
                projectId:$("#projectSels").val() == -1 ? null : $("#projectSels").val(),
                payName: $.trim($("#payName").val())
            },
            success: function (data) {
                // data为ajax返回数据
                $("#payTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });
}
function savePay() {
    var payName = $.trim($("#payNames").val());
    if (!payName) {
        layer.msg("请输入支出名称");
        return;
    } else if (!ValidUtils.validText(payName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var payAmount=$.trim($(".payAmount").val());
    if (!payAmount) {
        layer.msg("请输入支出金额");
        return;
    }else if (!ValidUtils.validMoney(payAmount)) {
        layer.msg("金额不能出现其他特殊字符");
        return;
    }
    var projectId = $.trim($("#projectSel").val());
    if ($("#projectSel").val() == -1) {
        layer.msg("请选择支出所属的项目");
        return;
    }
    var payType=$.trim($("#typeSel").val());
    if ($("#typeSel").val() == -1) {
        layer.msg("请选择支出所属的类型");
        return;
    }
    var payDesc=$.trim($("#payDesc").val());
    payAmount = parseInt((payAmount * 100).toFixed(2)) ;
    $.ajax({
        url: ctx + "pay/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            payName: payName,
            projectId:projectId,
            payAmount:payAmount,
            payType:payType,
            payDesc:payDesc
        },
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("保存成功");
                $('.modality-layer').hide();
                loadPage();
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
}

function updatePay() {
    var payName = $.trim($("#payNames").val());
    if (!payName) {
        layer.msg("请输入支出名称");
        return;
    } else if (!ValidUtils.validText(payName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var payAmount=$.trim($(".payAmount").val());
    if (!payAmount) {
        layer.msg("请输入支出金额");
        return;
    }else if (!ValidUtils.validMoney(payAmount)) {
        layer.msg("金额不能出现其他特殊字符");
        return;
    }
    var projectId=$("#projectSel").attr('code');
    var payType=$("#typeSel").attr('code');
    var id=$(".ids").val();
    var payDesc=$.trim($("#payDesc").val());
    payAmount = parseInt((payAmount * 100).toFixed(2)) ;
    $.ajax({
        url: ctx + "pay/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            payId:id,
            payName: payName,
            projectId:projectId,
            payAmount:payAmount,
            payType:payType,
            payDesc:payDesc
        },
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("保存成功");
                $('.modality-layer').hide();
                loadPage();
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
}
function deletePay(payId) {
    layer.confirm('删除后将无法恢复，是否继续？', function () {
        $.ajax({
            url: ctx + "pay/delete",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {payId: payId},
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg('删除成功 !');
                    loadPage();
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('删除失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('删除失败 !');
            }
        });
    });

}