$(function(){

    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
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

    $("#orderSel").change(function(){
        if($("#orderStatus").val()==10){
            $("#completeStr").show();
        }else{
            $("#completeStr").hide();
        }
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
                url: ctx + 'order/getdelpage',
                params:{
                    orderId: $.trim($("#orderId").val()),
                    beginTime:$("#timeSpick").val()+"00:00:00",
                    hourseNumber:$.trim($("#hourseNumber").val()),
                    endTime:$("#timeEpick").val()+"23:59:59"
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#orderTable").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

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