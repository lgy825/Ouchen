var cinemaArr = [];
var roleArr = [];
var cityCinemaMap = {};
var chooseArr = [];
var chooseRoleArr = [];
var su = {};
$(function () {
    loadCompany();
    $("#saveBtn").click(function () {

        var userName = $.trim($("#userName").val());
        if (!userName) {
            layer.msg("请输入姓名");
            return;
        } else if (!ValidUtils.validText(userName, 1, 15)) {
            layer.msg("姓名不超过15个字，不能出现其他特殊字符");
            return;
        }
        var loginName = $.trim($("#loginName").val());
        if (!loginName) {
            layer.msg("请输入用户名");
            return;
        } else if (!ValidUtils.validUserName(loginName, 1, 15)) {
            layer.msg("用户名不超过15个字母或数字，不能出现其他特殊字符");
            return;
        }
        var companyCode = $("#companySel").val();
        if ($("#companySel").val() == -1) {
            layer.msg("请选择员工所属公司");
            return;
        }

        var password = $.trim($("#password").val());
        if (!$("#userId").val()) {
            if (!password) {
                layer.msg("请输入密码");
                return;
            } else if (!ValidUtils.validUserName(password, 1, 10)) {
                layer.msg("密码不超过10个字母或数字，不能出现其他特殊字符");
                return;
            }
        } else {
            if (password.length > 0 && !ValidUtils.validUserName(password, 1, 10)) {
                layer.msg("密码不超过10个字母或数字，不能出现其他特殊字符");
                return;
            }
        }

        var userEmail = $.trim($("#userEmail").val());
        if (userEmail && !ValidUtils.validEmail(userEmail, 1, 10)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }

        var userTel = $.trim($("#userTel").val());
        if (userTel && !ValidUtils.validNum(userTel, 15)) {
            layer.msg("联系电话过长或输入格式有误");
            return;
        }

        var cinemaChooseWay = $(".cinemar.on").attr("cinemaradio");
        var cinemas;
        if (cinemaChooseWay == 1) {
            // 全部
            cinemas = "-1";
        } else {
            // 指定
            if ($(".pricinema.cur").length < 1) {
                layer.msg("请至少选择一个项目，项目选择决定用户可管理的影院数据权限");
                return;
            }

            var chooseC = [];
            $(".pricinema.cur").each(function () {
                chooseC.push($(this).attr("ccode"));
            });
            cinemas = chooseC.join(",");
        }
        var roles = "";

        if ($(".rolespan.cur").length < 1) {
            layer.msg("请至少选择一种角色，角色选择决定用户可管理的项目菜单权限");
            return;
        }

        var chooseR = [];
        $(".rolespan.cur").each(function () {
            chooseR.push($(this).attr("ccode"));
        });
        roles = chooseR.join(",");

        $.ajax({
            url: ctx + "sysuser/save",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id: $("#userId").val(),
                companyCode: companyCode,
                chooseProjectId: cinemaChooseWay,
                cinemas: cinemas,
                roles: roles,
                loginName: loginName,
                password: password,
                userName: userName,
                userTel: userTel,
                userEmail: userEmail
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "sysuser/toUserlist";
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

                    $("#companySel").change(function () {
                        if (!$(this).val() || $(this).val() == -1) {
                            $("#cinemaDiv").find(".pricinema").remove();
                        } else {
                            getCinemaList([], $("#companySel").val());
                            getRoleList([], $("#companySel").val());
                        }
                        clearSearch();
                        clearRoleSearch();
                    });

                    // 加载数据 -------------
                    if ($("#userId").val()) {
                        $.ajax({
                            url: ctx + "sysuser/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#userId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#companySel").val(su.companyCode).trigger("change");
                                    $("#userName").val(su.userName);
                                    $("#loginName").val(su.loginName);
                                    // $("#upassword").val(su.password);
                                    $("#password").attr("placeholder", "如需修改，请直接输入新密码");
                                    $("#userEmail").val(su.userEmail);
                                    $("#userTel").val(su.userTel);
                                    $(".cinemar").removeClass("on");
                                    $(".cinemar[cinemaradio=" + su.chooseProjectId + "]").addClass("on");
                                    if (su.chooseProjectId == 2) {
                                        $('#p_selCimemaPan').removeClass("none");
                                    }
                                    var waitCinema = setInterval(function () {
                                        if ($(".pricinema").length > 0 && $(".rolespan").length > 0) {
                                            if (su.cinemas) {
                                                $(su.cinemas.split(",")).each(function (idx, elem) {
                                                    $(".pricinema[ccode=" + elem + "]").addClass("cur");
                                                });
                                            }
                                            if (su.roles) {
                                                $(su.roles.split(",")).each(function (idx, elem) {
                                                    $(".rolespan[ccode=" + elem + "]").addClass("cur");
                                                });
                                            }
                                            clearInterval(waitCinema);
                                        }
                                    }, 500);
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

    function getCinemaList(chooseCinema, companyCode) {
        $.ajax({
            url: ctx + "project/getProject",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {companyCode: companyCode},
            success: function (data) {
                if (data && data.resultCode === '0') {
                    cinemaArr = data.resultData;
                    if(cinemaArr.length < 1) {
                        layer.msg("所选公司无可用项目");
                        $("#companySel").val("-1").trigger("change");
                        clearSearch();
                    }
                    showCinemaList(cinemaArr, chooseCinema)
                }
            }
        });
    }

    function showCinemaList(cinemaArr, chooseCinemaCode) {
        $("#cinemaDiv").empty();
        $(cinemaArr).each(function (idx, elem) {
            var cur = '';
            if (chooseCinemaCode != null &&
                chooseCinemaCode != undefined &&
                $.inArray(elem.cinemaCode, chooseCinemaCode) != -1) {
                cur = 'cur';
                if (chooseArr) {
                    chooseArr.push(elem.cinemaCode)
                }
            }
            $("#cinemaDiv").append(
                '<p><span class="pricinema checkBtn check ' + cur + '" ccode="'
                + elem.id
                + '">' + elem.projectName + '</span></p>'
            );
        });
        //复选框
        $("#cinemaDiv").find('.check').off("click").on('click', function () {
            var _this = $(this);
            _this.toggleClass('cur');
            if (_this.hasClass('cur') && $.inArray(_this.attr('ccode'), chooseArr) == -1) {
                chooseArr.push($(this).attr('ccode'))
            } else {
                _.pull(chooseArr, $(this).attr('ccode'));
            }
        });
    }

    //选择影院
    $('.cinema_box').on('click', '.radio', function () {
        var _this = $(this),
            $data_i = _this.attr('data-i'),
            $p_selCimema = $('#p_selCimemaPan');

        if ($data_i == 1) {
            if(!$("#companySel").val() || $("#companySel").val() == -1) {
                layer.msg("请选择公司后再选项目");
                return;
            }
            $p_selCimema.removeClass('none');
        } else {
            // 全部
            $p_selCimema.addClass('none');
            clearSearch();
        }
        $(".cinemar").removeClass("on");
        $(this).addClass("on");
    });

    function clearSearch() {
        $("#pricinemanamesearch").val("");
    }

    $("#loginName").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validUserName($(this).val(), 1, 15)) {
            $.ajax({
                url: ctx + "sysuser/nameexist",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {
                    id: $("#uid").val(),
                    loginName: $("#loginName").val()
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        if (data.resultData == true) {
                            if ($("#loginName").hasClass("inputBg")) {
                                $("#loginName").removeClass("inputBg");
                            }
                            $("#loginName").next().removeClass("none");
                            layer.msg("用户名已存在");
                        } else {
                            $("#loginName").addClass("inputBg");
                            $("#loginName").next().addClass("none");
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
        } else {
            if ($(this).hasClass("inputBg")) {
                $(this).removeClass("inputBg");
            }
        }
    }).focus(function () {
        $(this).next().removeClass("none");
    });

    $("#userName, #userTel").blur(function () {
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
    $("#userTel").blur(function () {
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

    $("#password").blur(function () {
        $(this).val($.trim($(this).val()));

        if (ValidUtils.validUserName($(this).val(), 1, 15)) {
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

    $("#userEmail").blur(function () {
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


    $("#pricinemanamesearch").on('keyup', function () {
        var searchStr = $.trim($(this).val());

        if (searchStr) {

            // 渲染匹配的影院(选中已选择的影院)
            var searchResult = _.filter(cinemaArr, function (elem) {
                return elem.projectName.indexOf(searchStr) != -1;
            });

            showCinemaList(searchResult);

        } else {
            showCinemaList(cinemaArr, chooseArr);
        }
    });

    $("#rolenamesearch").on('keyup', function () {
        var searchStr = $.trim($(this).val());

        if (searchStr) {

            // 渲染匹配的影院(选中已选择的影院)
            var searchResult = _.filter(roleArr, function (elem) {
                return elem.roleName.indexOf(searchStr) != -1;
            });

            showRoleList(searchResult);

        } else {
            showRoleList(roleArr, chooseRoleArr);
        }
    });

    function getRoleList(chooseRole, companyCode) {
        $.ajax({
            url: ctx + "sysrole/getpage",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {companyCode: companyCode, pageIndex:0, pageSize: 99999},
            success: function (data) {
                if (data && data.resultCode === '0') {
                    roleArr = data.resultData.list;
                    if(roleArr.length < 1) {
                        layer.msg("所选公司无可用角色");
                        $("#companySel").val("-1").trigger("change");
                        clearRoleSearch();
                    }
                    showRoleList(roleArr, chooseRole);
                    $("#roleDiv").show();
                }
            }
        });
    }

    function showRoleList(roleArr, chooseRoleId) {
        $("#roleDiv").empty();
        $(roleArr).each(function (idx, elem) {
            var cur = '';
            if (chooseRoleId != null &&
                chooseRoleId != undefined &&
                $.inArray(elem.id, chooseRoleId) != -1) {
                cur = 'cur';
                if (chooseRoleArr) {
                    chooseRoleArr.push(elem.id)
                }
            }
            $("#roleDiv").append(
                '<p><span class="rolespan checkBtn check ' + cur + '" ccode="'
                + elem.id
                + '">' + elem.roleName + '</span></p>'
            );
        });
        //复选框
        $("#roleDiv").find('.check').off("click").on('click', function () {
            var _this = $(this);
            _this.toggleClass('cur');
            if (_this.hasClass('cur') && $.inArray(_this.attr('ccode'), chooseRoleArr) == -1) {
                chooseRoleArr.push($(this).attr('ccode'))
            } else {
                _.pull(chooseRoleArr, $(this).attr('ccode'));
            }
        });
    }


    function clearRoleSearch() {
        $("#rolenamesearch").val("");
    }
});
