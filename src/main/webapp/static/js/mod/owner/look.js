$(function () {
    loadProject();
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

                    // 加载数据 -------------
                    if ($("#ownerId").val()) {
                        $.ajax({
                            url: ctx + "owner/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#ownerId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#ownerName").val(su.ownerName);
                                    $("#ownerPwd").attr("placeholder", "如需修改，请直接输入新密码");
                                    $("#ownerEmail").val(su.ownerEmail);
                                    $("#ownerTel").val(su.ownerTel);
                                    $("#ownerAddr").val(su.ownerAddr);
                                    $("#projectSel").val(su.projectId);
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
});