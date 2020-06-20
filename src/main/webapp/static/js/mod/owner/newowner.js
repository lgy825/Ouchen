$(function () {

    loadProject();


    $("#saveBtn").click(function () {
        var ownerName = $.trim($("#ownerName").val());
        if (!ownerName) {
            layer.msg("请输入用户名");
            return;
        } else if (!ValidUtils.validUserName(ownerName, 1, 15)) {
            layer.msg("用户名不超过15个字母或数字，不能出现其他特殊字符");
            return;
        }
        var projectId = $.trim($("#projectSel").val());
        if ($("#projectSel").val() == -1) {
            layer.msg("请选择用户所属的项目");
            return;
        }
        var ownerPwd = $.trim($("#ownerPwd").val());
        if(!$("#ownerId").val()) {
            if (!ownerPwd) {
                layer.msg("请输入密码");
                return;
            } else if (!ValidUtils.validUserName(ownerPwd, 1, 15)) {
                layer.msg("密码不超过10个字母或数字，不能出现其他特殊字符");
                return;
            }
        } else {
            if (ownerPwd.length > 0 && !ValidUtils.validUserName(ownerPwd, 1, 15)) {
                layer.msg("密码不超过10个字母或数字，不能出现其他特殊字符");
                return;
            }
        }
        var ownerEmail = $.trim($("#ownerEmail").val());
        if(ownerEmail && !ValidUtils.validEmail(ownerEmail, 1, 15)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }
        var ownerTel = $.trim($("#ownerTel").val());
        if(ownerTel && !ValidUtils.validNum(ownerTel, 11)) {
            layer.msg("联系电话输入过长或输入有误");
            return;
        }
        var ownerAddr=$.trim($("#ownerAddr").val());

        $.ajax({
            url: ctx + "owner/saveOwner",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id:$("#ownerId").val(),
                ownerPwd: ownerPwd,
                projectId:projectId,
                ownerName:ownerName,
                ownerTel: ownerTel,
                ownerEmail: ownerEmail,
                ownerAddr:ownerAddr
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "owner/toOwnerlist";
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
    });

    function loadProject() {
        $.ajax({
            url: ctx + "project/getProjectShiro",
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
                    $(data.resultData).each(function (idx, pro) {
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
