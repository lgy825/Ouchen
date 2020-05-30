
var chooseRoleArr = [];

$(function(){

    loadProject();
    loadCompany();
    loadPersonnel();
    loadCustomer();
    $('.p_addBtn').on('click',function(){
        $('.p_selBox').append($('.p_selingBox:last').clone(true));
        $('.p_selingBox:last').find(".close-set").show();
    });

    $(".close-set").click(function () {
        $(this).parent().remove();
    });

    //rentFreeCount
    $("#rentFreeCount").change(function(){


        var contractStartTime = timeContractStart.val();
        var count=$("#rentFreeCount").val();
        $.ajax({
            url: ctx + "contract/getRentFree",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                count:count,
                contractStartTime:contractStartTime+" 00:00:00",
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    su = data.resultData;
                    timeRentFreeStart.val(su.rentFreeStartTime.split(" ")[0]);
                    timeRentFreeEnd.val(su.rentFreeEndTime.split(" ")[0]);

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

    });

    //加载项目
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

    //加载业务员信息
    function loadPersonnel() {
        $.ajax({
            url: ctx + "sysuser/getpage",
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
                    $("#personnelSel").select2({placeholder: '请选择业务员'});
                    $("#personnelSel").append("<option value='-1'>*所属业务员*</option>");
                    $(data.resultData.list).each(function (idx, user) {
                        $("#personnelSel").append("<option value='" + user.id + "'>" + user.userName + "</option>");
                    });

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

    //加载客户信息
    function loadCustomer() {
        $.ajax({
            url: ctx + "customer/getpage",
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
                    $("#customerSel").select2({placeholder: '请选择所属客户'});
                    $("#customerSel").append("<option value='-1'>*所属客户*</option>");
                    $(data.resultData.list).each(function (idx, pro) {
                        $("#customerSel").append("<option value='" + pro.id + "'>" + pro.customerName + "</option>");
                    });

                    $("#customerSel").change(function () {
                        if (!$(this).val() || $(this).val() == -1) {

                        }else{
                            getRoleList([], $("#customerSel").val());
                        }
                        clearRoleSearch();
                    });

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

    //公司选中事件
    $("#companySel").change(function(){
        var companyCode=$(this).val();
        // 加载数据 -------------
        if (companyCode) {
            $.ajax({
                url: ctx + "company/get",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {
                    id: companyCode,
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        su = data.resultData;
                        $("#entrustIDcard").val(su.companyZipcode);

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

    //业务员选中事件
    $("#personnelSel").change(function(){
        var personnelCode=$(this).val();
        // 加载数据 -------------
        if (personnelCode) {
            $.ajax({
                url: ctx + "sysuser/get",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {
                    id: personnelCode,
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        su = data.resultData;
                        $("#entrustTel").val(su.userTel);

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

    //客户选中事件
    $("#customerSel").change(function(){
        var customerCode=$(this).val();
        // 加载数据 -------------
        if (customerCode) {
            $.ajax({
                url: ctx + "customer/get",
                type: "GET",
                cache: false,
                async: false,
                dataType: 'json',
                data: {
                    id: customerCode,
                },
                success: function (data) {
                    if (data && data.resultCode === '0') {
                        su = data.resultData;
                        $("#idCard").val(su.idCard);
                        $("#customerProxyName").val(su.customerProxyName);
                        $("#customerTel").val(su.customerTel);
                        $("#customerAddr").val(su.customerAddr);
                        $("#customerEmail").val(su.customerEmail);
                        $("#openingBank").val(su.openingBank);
                        $("#bankName").val(su.bankName);
                        $("#bankNumber").val(su.bankNumber);

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



    var timeContractStart= $("#timeContractStart").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeEpick").datetimepicker({
        //         minDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeContractEnd=$("#timeContractEnd").datetimepicker({
        format: 'Y-m-d',
        // minDate: 0,
        // onChangeDateTime: function (curDate) {
        //     var curDateTime = curDate.sformat("yyyy-MM-dd");
        //     $("#timeEpick").datetimepicker({
        //         minDate: curDateTime ? curDateTime : false
        //     });
        // },
        timepicker: false
    });
    var timeContract=$("#timeContract").datetimepicker({
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
    var timeRentFreeStart=$("#timeRentFreeStart").datetimepicker({
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
    var timeRentFreeEnd=$("#timeRentFreeEnd").datetimepicker({
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


    $("#saveBtn").click(function () {


        if ($("#projectSel").val() == -1) {
            layer.msg("请选择合同所属的项目");
            return;
        }


        var contractType = $.trim($("#contractType").val());
        if (!contractType) {
            layer.msg("请选择合同类型");
            return;
        }

        var customerCode = $.trim($("#customerSel").val());
        if (!customerCode) {
            layer.msg("请选择客户");
            return;
        }

        var contractCode = $.trim($("#contractCode").val());
        if (!contractCode) {
            layer.msg("请输入合同编码");
            return;
        }


        var contractStartTime = timeContractStart.val();
        var contractEndTime = timeContractEnd.val();
        if (contractStartTime.length < 1 || contractEndTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(contractStartTime) > Date.parse(contractEndTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }

        var operativeWay = $.trim($("#operativeWay").val());
        if (!operativeWay) {
            layer.msg("请选择运营方式");
            return;
        }

        var projectId = $.trim($("#projectSel").val());
        if (!projectId) {
            layer.msg("请选择合同所属的项目");
            return;
        }

        var payType = $.trim($("#payType").val());
        if (!payType) {
            layer.msg("请选择支付方式");
            return;
        }

        var contractTime = timeContract.val();
        if (contractTime.length < 1) {
            layer.msg("请选择签约时间");
            return;
        }


        var rentFreeStartTime = timeRentFreeStart.val();
        var rentFreeEndTime = timeRentFreeEnd.val();
        if (rentFreeStartTime.length < 1 || rentFreeEndTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(rentFreeStartTime) > Date.parse(rentFreeEndTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }

        var rentAmount = $.trim($("#rentAmount").val());
        if (!rentAmount) {
            layer.msg("请输入租金或收益");
            return;
        }

        var rentIncreaseWay = $.trim($("#rentIncreaseWay").val());
        if (!rentIncreaseWay) {
            layer.msg("请选择递增方式");
            return;
        }

        var customerSel = $.trim($("#customerSel").val());
        if (!customerSel) {
            layer.msg("请选择甲方信息");
            return;
        }

        var companySel = $.trim($("#companySel").val());
        if (!companySel) {
            layer.msg("请选择乙方公司");
            return;
        }

        var personnelSel = $.trim($("#personnelSel").val());
        if (!personnelSel) {
            layer.msg("请选择乙方业务代表");
            return;
        }


        if ($(".rolespan.cur").length < 1) {
            layer.msg("请至少选择一个房间");
            return;
        }
        var roomCodes = "";
        var chooseR = [];
        $(".rolespan.cur").each(function () {
            chooseR.push($(this).attr("ccode"));
        });
        roomCodes = chooseR.join(",");

        $.ajax({
            url: ctx + "contract/addContract",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                projectId:projectId,
                id:$.trim($("#contractId").val()),
                contractType:contractType,
                contractCode:contractCode,
                customerCode:customerCode,
                contractStartTime:contractStartTime+" 00:00:00",
                contractEndTime:contractEndTime+" 23:59:59",
                operativeWay:operativeWay,
                contractTime:contractTime+" 00:00:00",
                rentFreeStartTime:rentFreeStartTime+" 00:00:00",
                rentFreeEndTime:rentFreeEndTime+" 23:59:59",
                rentAmount:rentAmount,
                personnelCode:customerSel,
                companyCode:companySel,
                personnelCode:personnelSel,
                payType:payType,
                rentIncreaseWay:$.trim($("#rentIncreaseWay").val()),
                roomCode:roomCodes

            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "contract/toRentContractlist";
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

    // 加载数据 -------------
    if ($("#contractId").val()) {

        $.ajax({
            url: ctx + "contract/getContract",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                id: $("#contractId").val(),
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    su = data.resultData;
                    $("#projectSel").val(su.projectId);
                    $("#contractCode").val(su.contractCode);
                    $("#rentAmount").val(su.rentAmount);
                    $("#operativeWay").val(su.operativeWay);
                    timeContractStart.val(su.contractStartTime.split(" ")[0]);
                    timeContractEnd.val(su.contractEndTime.split(" ")[0]);
                    timeContract.val(su.contractTime.split(" ")[0]);
                    timeRentFreeStart.val(su.rentFreeStartTime.split(" ")[0]);
                    timeRentFreeEnd.val(su.rentFreeEndTime.split(" ")[0]);
                    $("#rentAmount").val(su.rentAmount);
                    $("#rentIncreaseWay").val(su.rentIncreaseWay);
                    $("#excuteOpeningBank").val(su.excuteOpeningBank);
                    $("#banksName").val(su.banksName);
                    $("#bankNumber").val(su.bankNumber);
                    $("#payType").val(su.payType);
                    $("#contractType").val(su.contractType);
                    $("#customerSel").val(su.customerCode);
                    $("#companySel").val(su.companyCode);
                    $("#personnelSel").val(su.personnelCode);
                    var roomCodes=su.roomCode;
                    var waitCinema = setInterval(function () {
                        if ($(".rolespan").length > 0) {
                            if (roomCodes) {
                                $(roomCodes.split(",")).each(function (idx, elem) {
                                    $(".rolespan[ccode=" + elem + "]").addClass("cur");
                                });
                            }
                            clearInterval(waitCinema);
                        }
                    }, 500);
                    $("#customerSel").trigger("change");
                    $("#companySel").trigger("change");
                    $("#personnelSel").trigger("change");





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
            url: ctx + "customer/getroompage",
            type: "GET",
            cache: false,
            // async: false,
            dataType: 'json',
            data: {customerCode: companyCode, pageIndex:0, pageSize: 99999},
            success: function (data) {
                if (data && data.resultCode === '0') {
                    roleArr = data.resultData.list;
                    if(roleArr.length < 1) {
                        layer.msg("所选客户无所用房间");
                        $("#customerSel").val("-1").trigger("change");
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
                + '">' + elem.roomNumber + '</span></p>'
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