$(function () {
    // 加载数据 -------------

    loadProjectType();
    loadCompany();

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
                    $("#companySel").append("<option value='-1'>所属公司</option>");
                    $(data.resultData.list).each(function (idx, comp) {
                        $("#companySel").append("<option value='" + comp.companyCode + "'>" + comp.companyName + "</option>");
                    });
                    if ($("#projectId").val()) {
                        $.ajax({
                            url: ctx + "project/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#projectId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#projectName").val(su.projectName);
                                    $("#typeCodeSel").val(su.typeCode);
                                    $("#projectDesc").val(su.projectDesc);
                                    $("#projectTel").val(su.projectTel);
                                    $("#projectAddr").val(su.projectAddr);
                                    $("#companySel").val(su.companyCode);
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

    $("#saveBtn").click(function () {
        var projectName = $.trim($("#projectName").val());
        if (!projectName) {
            layer.msg("请输入项目名称");
            return;
        } else if (!ValidUtils.validText(projectName, 1, 15)) {
            layer.msg("项目名称不超过15个字母或数字，不能出现其他特殊字符");
            return;
        }
        if ($("#typeCodeSel").val() == -1) {
            layer.msg("请选择项目所属类型");
            return;
        }
        if ($("#companySel").val() == -1) {
            layer.msg("请选择项目所属公司");
            return;
        }
        var projectTel = $.trim($("#projectTel").val());
        if(projectTel && !ValidUtils.validNum(projectTel, 11)) {
            layer.msg("联系电话输入过长或输入有误");
            return;
        }
        var projectAddr=$.trim($("#projectAddr").val());
        var projectDesc=$.trim($("#projectDesc").val());
        $.ajax({
            url: ctx + "project/saveProject",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id:$("#projectId").val(),
                typeCode: $("#typeCodeSel").val(),
                companyCode: $("#companySel").val(),
                projectName:projectName,
                projectTel: projectTel,
                projectAddr:projectAddr,
                projectDesc:projectDesc
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "project/toProjectlist";
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

    function loadProjectType() {
        $.ajax({
            url: ctx + "project/getProjectTypeAll",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    //
                    $("#typeCodeSel").select2({placeholder: '*选择项目类型*'});
                    $("#typeCodeSel").append("<option value='-1'>*选择项目类型*</option>");
                    $(data.resultData).each(function (idx, item) {
                        $("#typeCodeSel").append("<option value='" + item.id + "'>" + item.typeName + "</option>");
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


});
