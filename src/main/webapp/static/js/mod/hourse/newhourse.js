$(function () {


    loadProject();
    loadHourseType();
    loadOwner();

    $("#saveBtn").click(function () {

        if ($("#ownerSel").val() == -1) {
            layer.msg("请选择房子所属的房主");
            return;
        }
        if ($("#typeNameSel").val() == -1) {
            layer.msg("请选择房子所属的类型");
            return;
        }
        if ($("#projectSel").val() == -1) {
            layer.msg("请选择房子所属的项目");
            return;
        }
        var ownerId = $("#ownerSel").val();
        var typeCode=$("#typeCodeSel").val();
        var hourseNumber = $.trim($("#hourseNumber").val());
        if (!hourseNumber) {
            layer.msg("请输入房间号");
            return;
        } else if (!ValidUtils.validText(hourseNumber, 1, 8)) {
            layer.msg("房间号不超过8个字，不能出现其他特殊字符");
            return;
        }

        var areaCode = $.trim($("#areaCode").val());
        if (!areaCode) {
            layer.msg("请输入地区号");
            return;
        } else if (!ValidUtils.validUserName(areaCode, 1, 8)) {
            layer.msg("地区号不超过8个字母或数字，不能出现其他特殊字符");
            return;
        }
        var hourseArea = $.trim($("#hourseArea").val());
        if (!$("#hourseArea").val()) {
            if (!hourseArea) {
                layer.msg("请输入房子所在地址");
                return;
            } else if (!ValidUtils.validUserName(hourseArea, 1, 50)) {
                layer.msg("地址不超过50个字母或数字，不能出现其他特殊字符");
                return;
            }
        } else {
            if (hourseArea.length > 0 && !ValidUtils.validText(hourseArea, 1, 50)) {
                layer.msg("地址不能超过50个字符，不能出现其他特殊字符");
                if (hourseArea.length > 0 && !ValidUtils.validUserName(hourseArea, 1, 50)) {
                    layer.msg("地址不超过50个个字母或数字，不能出现其他特殊字符");
                    return;
                }
            }
            var hourseDesc = $.trim($("#hourseDesc").val());
            $.ajax({
                       url: ctx + "hourse/saveHourse",
                       type: "POST",
                       cache: false,
                       dataType: 'json',
                       data: {
                           id: $("#hourseId").val(),
                           ownerCode: ownerId,
                           typeCode: typeCode,
                           projectId:$("#projectSel").val(),
                           areaCode: areaCode,
                           hourseNumber: hourseNumber,
                           hourseArea: hourseArea,
                           hourseDesc: hourseDesc
                       },
                       success: function (data) {
                           if (data && data.resultCode === '0') {
                               layer.msg("保存成功");
                               location.href = ctx + "hourse/toHourselist";
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
                           layer.load(1, {shade: [0.3]})
                       }
                   });
        }
    });




    function loadOwner() {
        $.ajax({
            url: ctx + "owner/getpage",
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
                    $("#ownerSel").select2({placeholder: '房主'});
                    $("#ownerSel").append("<option value='-1'>*请选择所属房主*</option>");
                    $(data.resultData.list).each(function (idx, owner) {
                        $("#ownerSel").append("<option value='" + owner.id + "'>" + owner.ownerName + "</option>");
                    });
                    // 加载数据 -------------
                    if ($("#hourseId").val()) {
                        $.ajax({
                            url: ctx + "hourse/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#hourseId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#hourseNumber").val(su.hourseNumber);
                                    $("#areaCode").val(su.areaCode);
                                    $("#hourseDesc").val(su.hourseDesc);
                                    $("#hourseArea").val(su.hourseArea);
                                    $("#ownerSel").val(su.ownerCode);
                                    $("#typeCodeSel").val(su.typeCode);
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

    function loadHourseType() {
        $.ajax({
            url: ctx + "hourseType/getpage",
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
                    $("#typeCodeSel").select2({placeholder: '类型'});
                    $("#typeCodeSel").append("<option value='-1'>*请选择类型*</option>");
                    $(data.resultData.list).each(function (idx, type) {
                        $("#typeCodeSel").append("<option value='" + type.id + "'>" + type.typeName + "</option>");
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
