$(function(){

    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
    });

    $('.layer-close').on('click', function () {
        $('.modality-layer').hide();
        $(".update").show();
        $("#orderDesc").val("");

        $("#orderStatus").val("-1").trigger('change');

    });


    $("#orderTable").on("click", ".editStatus", function (){
        $("#orderSel").empty();
        $('.modality-layer').show();
        var orderId = $(this).data("sid");
        $("#tempId").val(orderId);
        $("#orderSel").select2({placeholder: '*选择订单状态*'});
        $("#orderSel").append("<option value='-1'>*选择订单状态*</option>");
        $("#orderSel").append("<option value='10'>已完成</option>"+
            "<option value='11'>待完成</option>"+
            // "<option value='12'>已入住</option>"+
            "<option value='13'>已取消</option>"
        );
    });


    $("#timeSpick").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeEpick").datetimepicker({
        //         minDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    $("#timeEpick").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeSpick").datetimepicker({
        //         maxDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });




    $("#resetBtn").click(function () {
        $("#orderId").val("");

        $("#hourseNumber").val("");
        $("#timeSpick").val("");
        $("#timeEpick").val("");
        $("#searchBtn").click();
    });

    $("#orderTable").on("click", ".revoke", function (){
        var orderId = $(this).data("sid");
        layer.confirm('撤销确认，是否继续？', function () {
            $.ajax({
                url: ctx + "order/revoke",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {id: orderId},
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg('恢复成功 !');
                        loadPage();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('恢复失败 !');
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
            url: ctx + 'order/getpage',
            params:{
                orderId: $.trim($("#orderId").val()),
                orderStatus:13,
                beginTime:$("#timeSpick").val(),
                hourseNumber:$.trim($("#hourseNumber").val()),
                endTime:$("#timeEpick").val()
            },
            success: function (data) {
                // data为ajax返回数据
                $("#orderTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });
}

function updateStatus() {

    $.ajax({
        url: ctx + "order/updateStatus",
        type: "POST",
        cache: false,
        async: false,
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            id:$.trim($("#tempId").val()),
            orderDesc: $.trim($("#orderDesc").val()),
            orderStatus:$("#orderSel").val()
        }),
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("修改成功");
                $('.modality-layer').hide();
                loadPage();
            } else {
                if (data.resultDesc) {
                    layer.msg(data.resultDesc);
                } else {
                    layer.msg('修改失败 !');
                }
            }
        },
        error: function () {
            layer.msg('修改失败 !');
        },
        beforeSend: function () {
            layer.load(1, {shade:[0.3]})
        }
    });
}