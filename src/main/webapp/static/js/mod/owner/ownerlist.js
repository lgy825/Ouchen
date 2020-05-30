$(function(){


    loadPage();
    loadProject();


    $("#searchBtn").click(function () {
        loadPage();
    });

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
                url: ctx + 'owner/getpage',
                params:{
                    projectId:$("#projectSel").val() == -1 ? null : $("#projectSel").val(),
                    ownerName: $.trim($("#uName").val()),
                    ownerTel:$.trim($("#uTel").val())
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#ownerTable").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

    $("#resetBtn").click(function () {
        $("#uname").val("").trigger('change');
        $("#urelname").val("");
    });


    $("#ownerTable").on("click", ".shutbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('停用后该用户将无法登录，是否继续？', function () {
            $.ajax({
                url: ctx + "owner/disableowner",
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

    $("#ownerTable").on("click", ".openbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('是否继续启用该用户？', function () {
            $.ajax({
                url: ctx + "owner/undisableowner",
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
});