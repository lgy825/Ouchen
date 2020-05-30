var menuMap = {};
var idMap = {};
var menu1 = [], menu2 = [], menu3 = [];
$(function () {

    loadData();

    function loadData() {
        $.ajax({
            url: ctx + "sysmenu/getmenulist",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    rendPage(data.resultData);
                } else {
                    if (data.resultDesc) {
                        layer.msg(data.resultDesc);
                    } else {
                        layer.msg('获取失败 !');
                    }
                }
            },
            error: function () {
                layer.msg('获取失败 !');
            }
        });
    }

    function rendPage(data) {
        if(!data) {
            return;
        }

        _.forEach(data, function (menu) {
            if(menu.parentId && menu.menuType === 1) {
                menu2.push(menu);
            }
            if(menu.menuType == 2) {
                menu3.push(menu);
            }
            if(!menu.parentId && menu.menuType === 1) {
                menu1.push(menu);
                menu.parentId = 'm1';
            }
            if(!_.has(menuMap, menu.parentId)) {
                menuMap[menu.parentId] = [];
            }
            menuMap[menu.parentId].push(menu);
            idMap[menu.id] = menu;
        });
        // 一级菜单
        menu1 = _.sortBy(menu1, 'menuSort');
        _.forEach(menu1, function (menu) {
            $("#menu1").append(
                '<li class="" data-menuid="'+menu.id+'">' +
                '   <span class="menu1chk check checkBtn color" data-isshow="'+menu.isShow+'" data-menuid="'+menu.id+'">'+menu.menuName+'</span>' +
                '</li>'
            );
        });

        // 二级菜单

        $("#menu1").on("click", "li", function () {
            $("#menu1").find("li").removeClass("selected");
            $("#menu1").find("span").removeClass("cur");
            $(this).addClass("selected");
            $(this).find("span").addClass("cur");
            var menu1Id = $(this).data('menuid');
            var menu1Name = $.trim($(this).text());
            if(menu1Id) {
                var menu2tmp = menuMap[menu1Id];
                $("#menu2").empty();
                $("#menu3").empty();
                _.forEach(menu2tmp, function (menu) {
                    $("#menu2").append(
                        '<li data-menuid="'+menu.id+'">' +
                        '   <span class="menu2chk check checkBtn" data-isshow="'+menu.isShow+'" data-pid="'+menu1Id+'" data-pname="'+menu1Name+'" data-menuid="'+menu.id+'">'+menu.menuName+'</span>' +
                        '</li>'
                    );
                });
                if($(this).find("span").data("isshow") == 1) {
                    $("#hidebtn").val("隐藏");
                    // $("#hidebtn").attr("isshow", 1);
                } else {
                    $("#hidebtn").val("显示");
                    // $("#hidebtn").val("isshow", 2);
                }
            }
        });

        // 按钮
        $("#menu2").on("click", "li", function () {
            if($(this).find("span").hasClass("cur")) {
                $(this).find("span").removeClass("cur");
                $(this).removeClass("selected");
                if($("#menu1").find("span.cur").data("isshow") == 1) {
                    $("#hidebtn").val("隐藏");
                    // $("#hidebtn").attr("isshow", 1);
                } else {
                    $("#hidebtn").val("显示");
                    // $("#hidebtn").val("isshow", 2);
                }
                $("#menu3").empty();
            } else {
                $("#menu2").find("li").removeClass("selected");
                $("#menu2").find("span").removeClass("cur");
                $(this).addClass("selected");
                $(this).find("span").addClass("cur");
                var menu2Id = $(this).data('menuid');
                var menu2Name = $.trim($(this).text());
                if (menu2Id) {
                    $("#menu3").empty();
                    var menu3tmp = menuMap[menu2Id];
                    _.forEach(menu3tmp, function (menu) {
                        $("#menu3").append(
                            '<li data-menuid="' + menu.id + '">' +
                            '   <span class="menu3chk check checkBtn" data-isshow="' + menu.isShow + '" data-pid="' + menu2Id + '" data-pname="' + menu2Name + '" data-menuid="' + menu.id + '">' + menu.menuName + '</span>' +
                            '</li>'
                        );
                    });
                    if ($(this).find("span").data("isshow") == 1) {
                        $("#hidebtn").val("隐藏");
                        // $("#hidebtn").attr("isshow", 1);
                    } else {
                        $("#hidebtn").val("显示");
                        // $("#hidebtn").val("isshow", 2);
                    }
                }
            }
        });

        $("#menu3").on("click", "li", function () {
            if($(this).find("span").hasClass("cur")) {
                $(this).find("span").removeClass("cur");
                if($("#menu2").find("span.cur").data("isshow") == 1) {
                    $("#hidebtn").val("隐藏");
                    // $("#hidebtn").attr("isshow", 1);
                } else {
                    $("#hidebtn").val("显示");
                    // $("#hidebtn").val("isshow", 2);
                }
            } else {
                $("#menu3").find("span").removeClass("cur");
                $(this).find("span").addClass("cur");
                if($(this).find("span").data("isshow") == 1) {
                    $("#hidebtn").val("隐藏");
                    // $("#hidebtn").attr("isshow", 1);
                } else {
                    $("#hidebtn").val("显示");
                    // $("#hidebtn").val("isshow", 2);
                }
            }
        });

        $("#menu1").find("li:eq(0)").click();
    }

    $("#saveBtn").click(function () {
        var parentId = $("#parentId").val();
        var menuType = $("#menuType").val();
        var menuName = $("#menuName").val();
        var menuHref = $("#menuLink").val();
        var menuTarget = menuType == 1 ? "homepage" : "";
        var menuSort = $("#menuSort").val();
        // if(!ValidUtils.validNum(menuSort)) {
        //     layer.msg("排序字段请使用数字");
        //     return;
        // }
        if(menuSort < 1) {
            layer.msg("除工作台外其他菜单排序请使用大于1的数字");
            return;
        }
        var isShow = $("#isShow").val();
        var id = $("#mid").val();
        var iconClass = $("#iconClass").val();
        var shiroFlag = $("#shiroFlag").val();

        $.ajax({
            url: ctx + "sysmenu/savemenu",
            type: "POST",
            cache: false,
            // async: false,
            dataType: 'json',
            // contentType: "application/json",
            data: {
                id: id,
                parentId: parentId,
                menuType: menuType,
                menuName: menuName,
                menuHref: menuHref,
                menuTarget: menuTarget,
                menuSort: menuSort,
                isShow: isShow,
                iconClass: iconClass,
                shiroFlag: shiroFlag
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功", {shade:[0.3]});
                    location.reload();
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

    // 添加1级菜单
    $('#add1btn').on('click', function () {
        showAddLayer(1);

    });
    // 添加2级
    $('#add2btn').on('click', function () {
        if($("#menu1").find("span.cur").length === 1) {
            var m1 = $($("#menu1").find("span.cur")[0]);
            showAddLayer(2, m1.data("menuid"), m1.text());
        } else {
            layer.msg("请选择一个模块后再创建下级菜单");
        }
    });
    // 添加按钮
    $('#add3btn').on('click', function () {
        if($("#menu2").find("span.cur").length === 1) {
            var m2 = $($("#menu2").find("span.cur")[0]);
            showAddLayer(4, m2.data("menuid"), m2.text());
        } else {
            layer.msg("请选择一个菜单后再创建操作权限");
        }
    });

    // 修改按钮
    $('#editbtn').on('click', function () {
        // 3级优先，然后2级, 然后1级
        if($("#menu3").find("span.cur").length === 1) {
            var m3 = $($("#menu3").find("span.cur")[0]);
            showAddLayer(5, m3.data("pid"), m3.data("pname"), m3.data("menuid"));
        } else if($("#menu2").find("span.cur").length === 1) {
            var m2 = $($("#menu2").find("span.cur")[0]);
            showAddLayer(6, m2.data("pid"), m2.data("pname"), m2.data("menuid"));
        } else if($("#menu1").find("span.cur").length === 1) {
            var m1 = $($("#menu1").find("span.cur")[0]);
            showAddLayer(3, "", "", m1.data("menuid"));
        } else {
            layer.msg("请选择后再进行修改");
        }
    });

    // 隐藏显示按钮
    $('#hidebtn').on('click', function () {
        // 3级优先，然后2级, 然后1级
        if($("#menu3").find("span.cur").length === 1) {
            var m3 = $($("#menu3").find("span.cur")[0]);
            show(m3.data("menuid"));
        } else if($("#menu2").find("span.cur").length === 1) {
            var m2 = $($("#menu2").find("span.cur")[0]);
            show(m2.data("menuid"));
        } else if($("#menu1").find("span.cur").length === 1) {
            var m1 = $($("#menu1").find("span.cur")[0]);
            show(m1.data("menuid"));
        } else {
            layer.msg("请选择后再进行修改");
        }
    });

    // 删除按钮
    $('#delbtn').on('click', function () {
        // 3级优先，然后2级, 然后1级
        if($("#menu3").find("span.cur").length === 1) {
            var m3 = $($("#menu3").find("span.cur")[0]);
            show(m3.data("menuid"), 1);
        } else if($("#menu2").find("span.cur").length === 1) {
            var m2 = $($("#menu2").find("span.cur")[0]);
            show(m2.data("menuid"), 1);
        } else if($("#menu1").find("span.cur").length === 1) {
            var m1 = $($("#menu1").find("span.cur")[0]);
            show(m1.data("menuid"), 1);
        } else {
            layer.msg("请选择后再进行修改");
        }
    });

    $('.layer-close, #cancelBtn').on('click', function () {
        $('.menu-layer').hide();
        // $('.operate-layer').hide();
    });

    function show(mid, del) {
        var mtmp = idMap[mid];
        var url;
        var msg = "";
        if(del) {
            url = ctx + "sysmenu/del";
            msg = "是否删除" + mtmp.menuName + "，删除该菜单会自动删除其下级菜单或按钮，点击确认继续。";
        } else {
            if (mtmp.isShow === 1) {
                url = ctx + "sysmenu/disablemenu";
                msg = "是否隐藏" + mtmp.menuName + "，隐藏该菜单会自动隐藏其下级菜单或按钮，点击确认继续。";
            } else {
                // 判断父级是否是隐藏的
                if(mtmp.parentId) {
                    if(idMap[mtmp.parentId] && idMap[mtmp.parentId].isShow == 2) {
                        layer.msg("请先显示上级菜单");
                        return;
                    }
                }

                url = ctx + "sysmenu/undisablemenu";
                msg = "是否显示" + mtmp.menuName + "，点击确认继续。";
            }
        }
        layer.confirm(msg, function () {
            $.ajax({
                url: url,
                type: "POST",
                cache: false,
                dataType: 'json',
                data: {
                    menuId: mid,
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        layer.msg("修改成功");
                        location.reload();
                    } else {
                        if (data.resultDesc) {
                            layer.msg(data.resultDesc);
                        } else {
                            layer.msg('修改失败 !');
                        }
                    }
                },
                error: function () {
                    layer.msg('修改失败 !');
                }
            });
        });
    }

    function showAddLayer(type, pid, pname, mid) {
        $("#pdiv").removeClass("none");
        // 父id
        if(pid) {
            $("#parentId").val(pid);
        } else {
            $("#parentId").val("");
        }
        // 父名称
        if(pname) {
            $("#parentName").val(pname);
        } else {
            $("#parentName").val("");
        }
        // 修改id
        if(mid) {
            $("#mid").val(mid);
            var mtmp = idMap[mid];
            $("#menuName").val(mtmp.menuName);
            $("#menuLink").val(mtmp.menuHref);
            $("#menuSort").val(mtmp.menuSort);
            $("#isShow").val(mtmp.isShow);
        } else {
            $("#mid").val("");
            $("#menuName").val("");
            $("#menuLink").val("");
            $("#menuSort").val("");
            $("#isShow").val("1");
        }

        if(!$("#iconDiv").hasClass("none")) {
            $("#iconDiv").addClass("none");
        }

        if($("#msDiv").hasClass("none")) {
            $("#msDiv").removeClass("none");
        }

        // 1 添加1级，2添加2级，3修改1级，6修改2级
        if(type === 1 || type === 2 || type === 3 || type === 6) {
            $("#menuType").val("1");
            $("#pntext").text("上级菜单");
            $("#mntext").text("菜单名称");
            $("#mltext").text("菜单链接");
            $("#mstext").text("菜单排序");
            if(type === 1) {
                $("#mttext").text("添加菜单");
                if(!$("#pdiv").hasClass("none")) {
                    $("#pdiv").addClass("none");
                }
                if($("#iconDiv").hasClass("none")) {
                    $("#iconDiv").removeClass("none");
                }
            } else if(type === 2) {
                $("#mttext").text("添加菜单");
            } else if(type === 3) {
                $("#mttext").text("修改菜单");
                if(!$("#pdiv").hasClass("none")) {
                    $("#pdiv").addClass("none");
                }
                if($("#iconDiv").hasClass("none")) {
                    $("#iconDiv").removeClass("none");
                }
            } else if(type === 6) {
                $("#mttext").text("修改菜单");
            }
        }
        // 4添加按钮，5修改按钮
        if(type === 4 || type === 5) {
            $("#menuType").val("2");
            $("#pntext").text("菜单页面");
            $("#mntext").text("操作权限");
            $("#mltext").text("操作链接");
            $("#mstext").text("操作排序");
            if(type === 4) {
                $("#mttext").text("添加操作权限");
            } else {
                $("#mttext").text("修改操作权限");
            }
            if($("#msDiv").hasClass("none")) {
                $("#msDiv").addClass("none");
            }
        }
        $('.menu-layer').show();
    }
});