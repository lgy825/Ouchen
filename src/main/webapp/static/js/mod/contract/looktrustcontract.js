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
                           $("#entrustProxy").val(su.entrustProxy);
                           $("#entrustTel").val(su.entrustTel);
                           $("#hourseAddr").val(su.hourseAddr);
                           $("#hourseArea").val(su.hourseArea);
                           $("#hourseUses").val(su.hourseUses);
                           $("#entrustIDcard").val(su.entrustIdcard);


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