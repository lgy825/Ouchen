$(function () {
    var timeJoined = $("#timeJoined").datetimepicker({
                                                       format: 'Y-m-d',
                                                       // minDate: 0,
                                                       // onChangeDateTime: function (curDate) {
                                                       //     var curDateTime = curDate.sformat("yyyy-MM-dd");
                                                       //     $("#timeSpick").datetimepicker({
                                                       //         maxDate: curDateTime ? curDateTime : false
                                                       //     });
                                                       // },
                                                       timepicker: false
                                                   });


    loadCompany();
    $("#saveBtn").click(function () {

        var personnelName = $.trim($("#personnelName").val());
        if (!personnelName) {
            layer.msg("请输入姓名");
            return;
        } else if (!ValidUtils.validText(personnelName, 1, 15)) {
            layer.msg("姓名不超过15个字，不能出现其他特殊字符");
            return;
        }

        var companyCode = $("#companySel").val();

        var personnelEmail = $.trim($("#personnelEmail").val());
        if (personnelEmail && !ValidUtils.validEmail(personnelEmail, 1, 10)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }

        var personnelTel = $.trim($("#personnelTel").val());
        if (personnelTel && !ValidUtils.validNum(personnelTel, 15)) {
            layer.msg("联系电话过长或输入格式有误");
            return;
        }

        var personnelAge = $.trim($("#personnelAge").val());
        if (personnelAge && !ValidUtils.validNum(personnelAge, 2)) {
            layer.msg("年龄格式输入有误，只能为数字");
            return;
        }

        if ($("#companySel").val() == -1) {
            layer.msg("请选择业务员所属的公司");
            return;
        }

        if ($("#personnelSex").val() == -1) {
            layer.msg("请选择业务员的性别");
            return;
        }

        var joinedDate = timeJoined.val();
        alert(joinedDate)
        if (joinedDate.length < 1 ) {
            layer.msg("请选择员工的入职日期");
            return;
        }
        $.ajax({
            url: ctx + "personnel/savePersonnel",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data:JSON.stringify( {
                id: $("#personnelId").val(),
                companyCode: companyCode,
                personnelName: personnelName,
                personnelEmail: personnelEmail,
                personnelTel: personnelTel,
                personnelAge: $("#personnelAge").val(),
                personnelIdCard: $("#personnelIdCard").val(),
                personnelSex: $("#personnelSex").val(),
                joinedDate: joinedDate+" 00:00:00",
                personnelAddr: $("#personnelAddr").val()
            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "personnel/toPersonnellist";
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
    });

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

                    // 加载数据 -------------
                    if ($("#personnelId").val()) {
                        $.ajax({
                            url: ctx + "personnel/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#personnelId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#companySel").val(su.companyCode).trigger("change");
                                    $("#personnelName").val(su.personnelName);
                                    $("#personnelEmail").val(su.personnelEmail);
                                    $("#personnelTel").val(su.personnelTel);
                                    $("#personnelAddr").val(su.personnelAddr);
                                    $("#personnelSex").val(su.personnelSex);
                                    $("#personnelAge").val(su.personnelAge);
                                    $("#personnelIdCard").val(su.personnelIdCard);
                                    timeJoined.val(su.joinedDate.split(" ")[0]);

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


    $("#personnelName, #personnelTel").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validText($(this).val(), 1, 15)) {
            $(this).addClass("inputBg");
            $(this).next().addClass("none");
        } else {
            if ($(this).hasClass("inputBg")) {
                $(this).removeClass("inputBg");
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });
    $("#personnelTel").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validNum($(this).val(), 15)) {
            $(this).addClass("inputBg");
            $(this).next().addClass("none");
        } else {
            if ($(this).hasClass("inputBg")) {
                $(this).removeClass("inputBg");
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });


    $("#personnelEmail").blur(function () {
        $(this).val($.trim($(this).val()));

        if ($(this).val()) {
            if (ValidUtils.validEmail($(this).val())) {
                $(this).addClass("inputBg");
                $(this).next().addClass("none");
            } else {
                if ($(this).hasClass("inputBg")) {
                    $(this).removeClass("inputBg");
                }
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });
});
