$(function(){


    loadPage();
    $("#searcheType").click(function () {
        loadPage();
    });


    $("#resetBtn").click(function () {
        $("#typeName").val("");
    });

    $("#hoursetypeTable").on("click", ".delete", function (){
        var sid = $(this).data("sid");
        layer.confirm('删除后将无法恢复，是否继续？', function () {
            $.ajax({
                url: ctx + "hoursetype/delete",
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

    $("#typeNames")
        .focus(function () {
            $(this).val($.trim($(this).val()));
            $(this).removeClass("inputBg");
            $(this).siblings('.color-lred').removeClass("none");
            if($(this).val().length > 10) {
                $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
            } else {
                $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
            }
        })
        .blur(function () {
            $(this).val($.trim($(this).val()));
            if ($(this).val() &&
                $(this).val().length > 0 &&
                $(this).val().length <= 10) {
                $(this).addClass("inputBg");
                $(this).siblings('.color-lred').addClass("none");
            } else {
                $(this).removeClass("inputBg");
                if($(this).val().length > 0) {
                    $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
                } else {
                    $(this).siblings('.color-lred').text("* 名称输入过长，不超过10字");
                }
                $(this).siblings('.color-lred').removeClass("none");
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
            url: ctx + 'hourseType/getpage',
            params:{
                projectId:$("#projectSels").val() == -1 ? null : $("#projectSels").val(),
                typeName: $.trim($("#typeName").val())
            },
            success: function (data) {
                // data为ajax返回数据
                $("#hoursetypeTable").empty().html($("#trTmpl").render(data.resultData));
            },
            totalName: 'total'
        }
    });
}
function saveType() {
    var typeName = $.trim($("#typeNames").val());
    if (!typeName) {
        layer.msg("请输入类型名称");
        return;
    } else if (!ValidUtils.validText(typeName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var projectId = $.trim($("#projectSel").val());
    if ($("#projectSel").val() == -1) {
        layer.msg("请选择类型所属的项目");
        return;
    }
    var typeDesc=$.trim($("#typeDesc").val());
    $.ajax({
        url: ctx + "hourseType/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            typeName: typeName,
            projectId:projectId,
            typeDesc:typeDesc
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

function updateType() {
    var typeName = $.trim($("#typeNames").val());
    if (!typeName) {
        layer.msg("请输入类型名称");
        return;
    } else if (!ValidUtils.validText(typeName, 1, 10)) {
        layer.msg("名称不超过10个字母或数字，不能出现其他特殊字符");
        return;
    }
    var projectId=$("#projectSel").attr('code');
    var id=$(".ids").val();
    var typeDesc=$.trim($("#typeDesc").val());
    $.ajax({
        url: ctx + "hourseType/save",
        type: "POST",
        cache: false,
        dataType: 'json',
        data: {
            id:id,
            typeName: typeName,
            projectId:projectId,
            typeDesc:typeDesc
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
function deleteType(id) {
    layer.confirm('删除后将无法恢复，是否继续？', function () {
        $.ajax({
            url: ctx + "hourseType/delete",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {id: id},
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