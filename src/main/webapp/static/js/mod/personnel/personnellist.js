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
                url: ctx + 'personnel/getpage',
                params:{
                    personnelName: $.trim($("#personnelName").val())
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#personnelTable").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

    $("#resetBtn").click(function () {
        $("#personnelName").val("").trigger('change');
    });


    $("#personnelTable").on("click", ".shutbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('停用后该业务员无法创建合同，是否继续？', function () {
            $.ajax({
                url: ctx + "personnel/disable",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {id: sid},
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

    $("#personnelTable").on("click", ".openbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('是否继续启用该业务员？', function () {
            $.ajax({
                url: ctx + "personnel/undisable",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {id: sid},
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

    $("#personnelTable").on("click", ".delete", function (){
        var sid = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "personnel/delete",
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