var timeComplete;
var ss_prefix="order";
$(function(){

    loadOrderStatus();

    loadCollectData();
    loadProject();
    //加载项目
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

                    getSS();
                    if(sessionStorage.getItem(ss_prefix + "_projectSel") && sessionStorage.getItem(ss_prefix + "_projectSel") !== "null"){
                        var cinemas = sessionStorage.getItem(ss_prefix + "_projectSel").split(",");
                        //$("#cinemaCode").select2('val', [11062001,11061501]).trigger('change');
                        $("#projectSel").val(cinemas);
                    } else {
                        $("#projectSel").val("-1");
                    }
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


    $('.layer-close').on('click', function () {
        $('.modality-layer').hide();
        $(".update").show();
        $("#orderDesc").val("");

        $("#orderStatus").val("-1").trigger('change');

    });

    $("#orderSel").change(function(){
        if($("#orderSel").val()==10){
            $("#completeStr").show();
        }else{
            $("#completeStr").hide();
        }
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

    //加载订单状态
    function loadOrderStatus() {
        $("#orderStatus").select2({placeholder: '*选择订单状态*'});
        $("#orderStatus").append("<option value='-1'>*选择订单状态*</option>");
        $("#orderStatus").append("<option value='10'>已完成</option>"+
            "<option value='11'>待完成</option>"+
            // "<option value='12'>已入住</option>"+
            "<option value='13'>已取消</option>"
        );

    }


    loadPage();
    $("#searchBtn").click(function () {
        loadPage();
        loadCollectData();
        saveSS();
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

    timeComplete = $("#timeComplete").datetimepicker({
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




    $("#resetBtn").click(function () {
        $("#orderId").val("");

        $("#hourseNumber").val("");
        $("#timeSpick").val("");
        $("#timeEpick").val("");
        $("#searchBtn").click();
        $("#orderStatus").val("-1").trigger('change');
        $("#projectSel").val("-1").trigger('change');
    });

    $("#orderTable").on("click", ".delete", function (){
        var orderId = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "order/delete",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {id: orderId},
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

    $("#exportBtn").click(function () {
        var orderId= $("#orderId").val();
        var hourseNumber=$("#hourseNumber").val();
        var beginTime=$("#timeSpick").val();
        var endTime =$("#timeEpick").val();
        if ($.trim(hourseNumber)=='' || $.trim(hourseNumber)==null) {
            layer.msg("请至少输入房间号再导出");
            return;
        }
        if (beginTime.length < 1 || endTime.length < 1) {
            layer.confirm('您没有选择导出起止时间，是否继续？', function () {
                location.href = ctx + "order/exportOrder?hourseNumber="+hourseNumber+"&beginTime="+beginTime+"&endTime="+endTime;
            });
        } else {
            if (Date.parse(beginTime) > Date.parse(endTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
            location.href = ctx + "order/exportOrder?hourseNumber="+hourseNumber+"&beginTime="+beginTime+"&endTime="+endTime;
        }
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
                projectId:$("#projectSel").val() == -1 ? null : $("#projectSel").val(),
                orderId: $.trim($("#orderId").val()),
                beginTime:$("#timeSpick").val(),
                hourseNumber:$.trim($("#hourseNumber").val()),
                endTime:$("#timeEpick").val(),
                orderStatus:$("#orderStatus").val() == -1 ? null : $("#orderStatus").val()
            },
            success: function (data) {
                // data为ajax返回数据
                $("#orderTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });
}

function loadCollectData(){
    $.ajax({
        url: ctx + "order/getIncomSummary",
        cache: false,
        // async: false,
        dataType: 'json',
        data:{
            orderId: $.trim($("#orderId").val()),
            beginTime:$("#timeSpick").val(),
            hourseNumber:$.trim($("#hourseNumber").val()),
            endTime:$("#timeEpick").val(),
            orderStatus:$("#orderStatus").val() == -1 ? null : $("#orderStatus").val()
        },
        success: function (data) {
            if (data && data.resultCode === '0') {
                var incomSummaryObj=data.resultData;
                $("#orderRecAmountAll").html(incomSummaryObj.orderRecAmountAll+"<i>元</i>");
                $("#orderPayAmountAll").html(incomSummaryObj.orderPayAmountAll+"<i>元</i>");
                $("#orderActmountAll").html(incomSummaryObj.orderActmountAll+"<i>元</i>");
                $("#orderCountAll").html(incomSummaryObj.orderCountAll+"<i>天</i>");

            } else {
                if (data.resultDesc) {
                    layer.msg(data.resultDesc);
                } else {
                    layer.msg('获取收益汇总失败 !');
                }
            }
        },
        error: function () {
            layer.msg('获取收益汇总失败 !');
        },
        beforeSend: function () {
            layer.load(1, {shade:[0.3]})
        }
    });
}
function updateStatus() {

    var completeTime = timeComplete.val();
    if($("#orderSel").val() ==10){
        if (completeTime.length < 1 ) {
            layer.msg("如果订单状态完成，请选择完成日期");
            return;
        }
    }

    var nowDate = new Date();
    var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
    var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
    var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
    var time=hour+":"+minute+":"+second;

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
            orderStatus:$("#orderSel").val(),
            completeTime:completeTime?completeTime+" "+time:""
        }),
        success: function (data) {
            if (data && data.resultCode === '0') {
                layer.msg("修改成功");
                $('.modality-layer').hide();
                $("#orderDesc").val("");
                $("#completeStr").hide();
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