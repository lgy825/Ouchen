$(function(){
   loadProject();
   loadPage();


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


    var timeSpick=$("#timeSpick").datetimepicker({
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
    var timeEpick=$("#timeEpick").datetimepicker({
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
        $("#contractCode").val("");

        $("#contractName").val("");
        $("#timeSpick").val("");
        $("#timeEpick").val("");
        $("#searchBtn").click();
    });

    $("#searchBtn").click(function () {
        loadPage();
    });
    $("#contractTable").on("click", ".delete", function (){
        var contractId = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                       url: ctx + "contract/delete",
                       type: "GET",
                       cache: false,
                       // async: false,
                       dataType: 'json',
                       data: {id: contractId},
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
            url: ctx + 'contract/getpage',
            params:{
                contractCode: $.trim($("#contractCode").val()),
                beginTime:$("#timeSpick").val(),
                contractName:$.trim($("#contractName").val()),
                endTime:$("#timeEpick").val(),
                projectId:$("#projectSel").val() == -1 ? null : $("#projectSel").val(),
                contractType:$("#contractType").val() == -1 ? null : $("#contractType").val()
            },
            success: function (data) {
                // data为ajax返回数据
                $("#contractTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });


}