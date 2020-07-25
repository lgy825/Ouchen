$(function () {

    loadProject();


    $("#saveBtn").click(function () {
        var bussinessName = $.trim($("#bussinessName").val());
        if (!bussinessName) {
            layer.msg("请输入姓名");
            return;
        } else if (!ValidUtils.validText(bussinessName, 1, 15)) {
            layer.msg("姓名不超过15个字母或数字，不能出现其他特殊字符");
            return;
        }
        var projectId = $.trim($("#projectSel").val());
        if ($("#projectSel").val() == -1) {
            layer.msg("请选择该经理所属的项目");
            return;
        }
        var bussinessEmail = $.trim($("#bussinessEmail").val());
        if(bussinessEmail && !ValidUtils.validEmail(bussinessEmail, 1, 15)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }
        var bussinessTel = $.trim($("#bussinessTel").val());
        if (!bussinessTel) {
            layer.msg("请输入电话");
            return;
        }else if(bussinessTel && !ValidUtils.validNum(bussinessTel, 11)) {
            layer.msg("联系电话输入过长或输入有误");
            return;
        }
        var bussinessDesc=$.trim($("#bussinessDesc").val());

        $.ajax({
            url: ctx + "bussiness/saveBussiness",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id:$("#bussinessId").val(),
                projectId:projectId,
                bussinessName:bussinessName,
                bussinessTel: bussinessTel,
                bussinessEmail: bussinessEmail,
                bussinessDesc:bussinessDesc
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "bussiness/toBussinesslist";
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

                    //getSS();
                    // if(sessionStorage.getItem(ss_prefix + "_projectSel") && sessionStorage.getItem(ss_prefix + "_projectSel") !== "null"){
                    //     var cinemas = sessionStorage.getItem(ss_prefix + "_projectSel").split(",");
                    //     //$("#cinemaCode").select2('val', [11062001,11061501]).trigger('change');
                    //     $("#projectSel").val(cinemas);
                    // } else {
                    //     $("#projectSel").val("-1");
                    // }
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
