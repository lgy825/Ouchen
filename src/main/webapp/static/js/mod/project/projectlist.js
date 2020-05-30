$(function(){


    loadPage();

    $("#searchBtn").click(function () {
        loadPage();
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
                                           url: ctx + 'project/getpage',
                                           params:{
                                               projectName: $.trim($("#projectName").val())
                                           },
                                           success: function (data) {
                                               // data为ajax返回数据
                                               $("#projectTable").empty().html($("#trTmpl").render(data.resultData));
                                           },
                                           totalName: 'total'
                                       }
                                   });
    }

    $("#resetBtn").click(function () {
        $("#projectName").val("").trigger('change');
    });


    $("#projectTable").on("click", ".shutbtn", function () {
        var id = $(this).data("sid");
        layer.confirm('停用后该项目就会被停用？', function () {
            $.ajax({
                       url: ctx + "project/disableproject",
                       type: "GET",
                       cache: false,
                       async: false,
                       dataType: 'json',
                       data: {id: id},
                       success: function (data) {
                           if (data && data.resultCode === '0') {
                               layer.msg('停用成功 !');
                               loadPage();
                           } else {
                               if (data.resultDesc) {
                                   layer.msg(data.resultDesc);
                               } else {
                                   layer.msg('停用失败 !');
                               }
                           }
                       },
                       error: function () {
                           layer.msg('停用失败 !');
                       }
                   });
        });
    });

    $("#projectTable").on("click", ".openbtn", function () {
        var id = $(this).data("sid");
        layer.confirm('是否继续启用项目？', function () {
            $.ajax({
                       url: ctx + "project/undisableproject",
                       type: "GET",
                       cache: false,
                       async: false,
                       dataType: 'json',
                       data: {id: id},
                       success: function (data) {
                           if (data && data.resultCode === '0') {
                               layer.msg('启用成功 !');
                               loadPage();
                           } else {
                               if (data.resultDesc) {
                                   layer.msg(data.resultDesc);
                               } else {
                                   layer.msg('启用失败 !');
                               }
                           }
                       },
                       error: function () {
                           layer.msg('启用失败 !');
                       }
                   });
        });
    });

    $("#projectTable").on("click", ".delete", function (){
        var sid = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "project/delete",
                type: "GET",
                cache: false,
                // async: false,
                dataType: 'json',
                data: {id: sid},
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