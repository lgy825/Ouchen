var menuMap = {};
var idMap = {};
var menu1 = [], menu2 = [], menu3 = [];
$(function () {
    loadMenuData();
    loadCompany();

    $("#saveBtn").click(function () {
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
                    if($("#rid").val()) {
                        $.ajax({
                            url: ctx + "sysrole/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#rid").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $("#companySel").val(su.companyCode).trigger("change");
                                    $("#roleName").val(su.roleName);
                                    $("#roleDesc").val(su.roleDesc);

                                    $("#companySel").attr("disabled", "disabled");

                                    var rendMenu = setInterval(function () {
                                        if($(".menuchk").length > 0) {
                                            clearInterval(rendMenu);

                                            var mids = su.menuIds;
                                            _.forEach(menu1, function (m1id) {
                                                if(_.indexOf(mids, m1id.id) > -1) {
                                                    $(".menuchk[data-menuid="+m1id.id+"]").click();
                                                }
                                            });
                                            _.forEach(menu2, function (m2id) {
                                                if(_.indexOf(mids, m2id.id) > -1) {
                                                    $(".menuchk[data-menuid="+m2id.id+"]").click();
                                                }
                                            });
                                            _.forEach(menu3, function (m3id) {
                                                if(_.indexOf(mids, m3id.id) > -1) {
                                                    $(".menuchk[data-menuid="+m3id.id+"]").click();
                                                }
                                            });

                                            $("#menu1").off("click");
                                            $("#menu2").off("click");
                                            $("#menu3").off("click");
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


    function loadMenuData() {
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
                '   <span class="menuchk check checkBtn color" data-isshow="'+menu.isShow+'" data-menuid="'+menu.id+'">'+menu.menuName+'</span>' +
                '</li>'
            );
        });

        // 二级菜单

        $("#menu1").on("click", "li", function () {
            var menu1Id = $(this).data('menuid');
            if($(this).hasClass("selected")) {
                $(this).removeClass("selected");
                $(this).find("span").removeClass("cur");
                $("#menu2").find("span[data-pid='"+menu1Id+"']").each(function (idx, elem) {
                    var m2id = $(elem).data("menuid");
                    $("#menu3").find("span[data-pid='"+m2id+"']").each(function (idx1, elem1) {
                        $(elem1).parent().remove();
                    });
                    $(elem).parent().remove();
                });
            } else {
                $(this).addClass("selected");
                $(this).find("span").addClass("cur");
                var menu1Name = $.trim($(this).text());
                if (menu1Id) {
                    var menu2tmp = menuMap[menu1Id];
                    _.forEach(menu2tmp, function (menu) {
                        $("#menu2").append(
                            '<li data-menuid="' + menu.id + '">' +
                            '   <span class="menuchk check checkBtn" data-isshow="' + menu.isShow + '" data-pid="' + menu1Id + '" data-pname="' + menu1Name + '" data-menuid="' + menu.id + '">' + menu1Name + '->'+ menu.menuName + '</span>' +
                            '</li>'
                        );
                    });
                }
            }
        });

        // 按钮
        $("#menu2").on("click", "li", function () {
            var menu2Id = $(this).data('menuid');
            if($(this).find("span").hasClass("cur")) {
                $(this).find("span").removeClass("cur");
                $(this).removeClass("selected");
                $("#menu3").find("span[data-pid='"+menu2Id+"']").each(function (idx, elem) {
                    $(elem).parent().remove();
                });
            } else {
                $(this).addClass("selected");
                $(this).find("span").addClass("cur");
                var menu2Name = $.trim($(this).text());
                if (menu2Id) {
                    var menu3tmp = menuMap[menu2Id];
                    _.forEach(menu3tmp, function (menu) {
                        $("#menu3").append(
                            '<li data-menuid="' + menu.id + '">' +
                            '   <span class="menuchk check checkBtn" data-isshow="' + menu.isShow + '" data-pid="' + menu2Id + '" data-pname="' + menu2Name + '" data-menuid="' + menu.id + '">' + menu2Name + '->'+ menu.menuName + '</span>' +
                            '</li>'
                        );
                    });
                }
            }
        });

        $("#menu3").on("click", "li", function () {
            if($(this).find("span").hasClass("cur")) {
                $(this).find("span").removeClass("cur");
            } else {
                $(this).find("span").addClass("cur");
            }
        });

        // $("#menu1").find("li:eq(0)").click();
    }

});