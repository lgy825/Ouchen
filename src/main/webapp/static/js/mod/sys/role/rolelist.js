var ss_prefix = "sysuserlist";
$(function(){

    loadCompany();
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
                url: ctx + 'sysrole/getpage',
                params:{
                    companyCode: $.isArray($("#companySel").val())  ? $("#companySel").val().join(",") : ($("#companySel").val() == -1 ? "" : $("#companySel").val()),
                    roleName: $.trim($("#roleName").val()),
                    filter: 1
                },
                success: function (data) {
                    // data为ajax返回数据
                    $("#pricetabl").empty().html($("#trTmpl").render(data.resultData));
                },
                totalName: 'total'
            }
        });
    }

    function loadCompany() {
        $.ajax({
                   url: ctx + "company/getpage",
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
                           $("#companySel").select2({placeholder: '请选择所属公司'});
                           $("#companySel").append("<option value='-1'>选择公司</option>");
                           $(data.resultData.list).each(function (idx, comp) {
                               $("#companySel").append("<option value='" + comp.companyCode + "'>" + comp.companyName + "</option>");
                           });

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

    $("#pricetabl").on("click", ".shutbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('停用后该角色将无法使用，是否继续？', function () {
            $.ajax({
                url: ctx + "sysrole/disablerole",
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

    $("#pricetabl").on("click", ".openbtn", function () {
        var sid = $(this).data("sid");
        layer.confirm('是否继续启用该角色？', function () {
            $.ajax({
                url: ctx + "sysrole/undisablerole",
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

    $("#resetBtn").click(function () {
        $("#companySel").val("").trigger('change');
        $("#roleName").val("");
    });
});