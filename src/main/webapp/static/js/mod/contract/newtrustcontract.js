$(function(){
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

        var contractName = $.trim($("#contractName").val());
        if (!contractName) {
            layer.msg("请输入合同名称");
            return;
        }

        // var contractType = $.trim($("#contractType").val());
        // if (!contractType) {
        //     layer.msg("请输入合同类型");
        //     return;
        // }

        var contractCode = $.trim($("#contractCode").val());
        if (!contractCode) {
            layer.msg("请输入合同编码");
            return;
        }

        var contractIdentity = $.trim($("#contractIdentity").val());
        if (!contractIdentity) {
            layer.msg("请选择我方身份");
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
            layer.msg("请输入运营方式");
            return;
        }

        var contractTime = timeContract.val();
        if (contractTime.length < 1) {
            layer.msg("请选择签约时间");
            return;
        }

        var contractExcute = $.trim($("#contractExcute").val());
        if (!contractExcute) {
            layer.msg("请输入甲方信息");
            return;
        }
        var excuteIDcard = $.trim($("#excuteIDcard").val());
        if (!excuteIDcard) {
            layer.msg("请输入甲方身份证号(统一社会信用代码)");
            return;
        }

        var excuteProxy = $.trim($("#excuteProxy").val());
        if (!excuteProxy) {
            layer.msg("请输入甲方代理人姓名");
            return;
        }

        var excuteTel = $.trim($("#excuteTel").val());
        // if (!excuteTel) {
        //     layer.msg("请输入甲方联系方式");
        //     return;
        // }

        var excuteAddr = $.trim($("#excuteAddr").val());
        // if (!excuteAddr) {
        //     layer.msg("请输入甲方地址");
        //     return;
        // }

        var excuteEmail = $.trim($("#excuteEmail").val());
        if (!ValidUtils.validEmail(excuteEmail)) {
            layer.msg("邮箱输入不合法");
            return;
        }

        var excuteOpeningBank = $.trim($("#excuteOpeningBank").val());
        if (!excuteOpeningBank) {
            layer.msg("请输入甲方银行卡开户行");
            return;
        }

        var banksName = $.trim($("#banksName").val());
        if (!banksName) {
            layer.msg("请输入甲方银行卡户名");
            return;
        }

        var bankNumber = $.trim($("#bankNumber").val());
        if (!bankNumber) {
            layer.msg("请输入甲方银行卡账号");
            return;
        }else if (!ValidUtils.validNum(bankNumber)) {
            layer.msg("开户行只能是数字，不能包含特殊字符");
            return;
        }

        var contractEntrust = $.trim($("#contractEntrust").val());
        if (!contractEntrust) {
            layer.msg("请输入乙方名称");
            return;
        }

        var entrustIDcard = $.trim($("#entrustIDcard").val());
        if (!entrustIDcard) {
            layer.msg("请输入乙方身份证号(统一社会信用代码)");
            return;
        }

        var entrustProxy = $.trim($("#entrustProxy").val());
        if (!entrustProxy) {
            layer.msg("请输入乙方代理人姓名");
            return;
        }


        var entrustTel = $.trim($("#entrustTel").val());
        // if (!entrustProxy) {
        //     layer.msg("请输入乙方代理人姓名");
        //     return;
        // }
        var hourseAddr = $.trim($("#hourseAddr").val());
        // if (!hourseAddr) {
        //     layer.msg("请输入房屋地址");
        //     return;
        // }

        var hourseArea = $.trim($("#hourseArea").val());
        if (!hourseArea) {
            layer.msg("请输入房屋面积");
            return;
        }

        var hourseUses = $.trim($("#hourseUses").val());
        // if (!hourseUses) {
        //     layer.msg("请输入房屋用途");
        //     return;
        // }



        $.ajax({
            url: ctx + "contract/addContract",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                //projectId:projectId,
                id:$.trim($("#contractId").val()),
                contractName:contractName,
                contractType:11,
                contractCode:contractCode,
                contractIdentity:contractIdentity,
                contractStartTime:contractStartTime+" 00:00:00",
                contractEndTime:contractEndTime+" 23:59:59",
                operativeWay:operativeWay,
                contractTime:contractTime+" 00:00:00",
                contractExcute:contractExcute,
                excuteIdcard:excuteIDcard,
                excuteProxy:excuteProxy,
                excuteTel:excuteTel,
                excuteAddr:excuteAddr,
                excuteEmail:excuteEmail,
                excuteOpeningBank:excuteOpeningBank,
                banksName:banksName,
                bankNumber:bankNumber,
                contractEntrust:contractEntrust,
                entrustIdcard:entrustIDcard,
                entrustProxy:entrustProxy,
                entrustTel:entrustTel,
                hourseAddr:hourseAddr,
                hourseArea:hourseArea,
                hourseUses:hourseUses
            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "contract/toTrustContractlist";
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
                           $("#contractName").val(su.contractName);
                           $("#contractCode").val(su.contractCode);
                           $("#contractIdentity").val(su.contractIdentity);
                           $("#operativeWay").val(su.operativeWay);
                           timeContractStart.val(su.contractStartTime.split(" ")[0]);
                           timeContractEnd.val(su.contractEndTime.split(" ")[0]);
                           timeContract.val(su.contractTime.split(" ")[0]);
                           $("#contractExcute").val(su.contractExcute);
                           $("#excuteIDcard").val(su.excuteIdcard);
                           $("#excuteProxy").val(su.excuteProxy);
                           $("#excuteTel").val(su.excuteTel);
                           $("#excuteAddr").val(su.excuteAddr);
                           $("#excuteEmail").val(su.excuteEmail);
                           $("#excuteOpeningBank").val(su.excuteOpeningBank);
                           $("#banksName").val(su.banksName);
                           $("#bankNumber").val(su.bankNumber);
                           $("#contractEntrust").val(su.contractEntrust);
                           $("#entrustIDcard").val(su.entrustIdcard);
                           $("#entrustProxy").val(su.entrustProxy);
                           $("#entrustTel").val(su.entrustTel);
                           $("#hourseAddr").val(su.hourseAddr);
                           $("#hourseArea").val(su.hourseArea);
                           $("#hourseUses").val(su.hourseUses);



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