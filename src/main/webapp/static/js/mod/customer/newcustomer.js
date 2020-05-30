$(function () {

    $('.p_addBtn').on('click',function(){
        $('.p_selBox').append($('.p_selingBox:last').clone(true));
        $('.p_selingBox:last').find(".close-set").show();
    });

    $(".close-set").click(function () {
        $(this).parent().remove();
    });

    $("#customerStatus").change(function(){
        if($("#customerStatus").val()==20){
            $("#bankNumberStr").show();
            $("#bankNameStr").show();
            $("#openingBankStr").show();
            $("#idCardStr").show();
        }else{
            $("#bankNumberStr").hide();
            $("#bankNameStr").hide();
            $("#openingBankStr").hide();
            $("#idCardStr").hide();
        }
    });


    loadProject();

    $("#saveBtn").click(function () {

        var customerName = $.trim($("#customerName").val());
        if (!customerName) {
            layer.msg("请输入姓名");
            return;
        } else if (!ValidUtils.validText(customerName, 1, 15)) {
            layer.msg("姓名不超过15个字，不能出现其他特殊字符");
            return;
        }

        var projectId = $("#projectSel").val();

        var customerEmail = $.trim($("#customerEmail").val());
        if (customerEmail && !ValidUtils.validEmail(customerEmail, 1, 10)) {
            layer.msg("请输入正确的邮箱格式，可为空");
            return;
        }

        var customerTel = $.trim($("#customerTel").val());
        if (customerTel && !ValidUtils.validNum(customerTel, 15)) {
            layer.msg("联系电话过长或输入格式有误");
            return;
        }

        if ($("#projectSel").val() == -1) {
            layer.msg("请选择客户所属项目");
            return;
        }

        if ($("#customerSex").val() == -1) {
            layer.msg("请选客户的性别");
            return;
        }


        var openingBank = $.trim($("#openingBank").val());
        var bankName = $.trim($("#bankName").val());
        var idCard = $.trim($("#idCard").val());
        var bankNumber = $.trim($("#bankNumber").val());
        if($("#customerStatus").val()==20){
            if (!openingBank) {
                layer.msg("请输入客户银行卡开户行");
                return;
            }
            if (!bankName) {
                layer.msg("请输入客户银行卡户名");
                return;
            }
            if (!bankNumber) {
                layer.msg("请输入客户银行卡账号");
                return;
            }else if (!ValidUtils.validNum(bankNumber,19)) {
                layer.msg("银行账号只能是数字，不能包含特殊字符");
                return;
            }
            if (!bankName) {
                layer.msg("请输入客户银行卡户名");
                return;
            }
            if (!idCard) {
                layer.msg("请输入客户社会身份唯一代码");
                return;
            }
        }

        var roomArr = [];
        var stop = false;
        $('.p_selingBox').each(function (idx, elem) {
            var $this = $(elem);
            var room = {};
            if(!$this.find('.roomNumber').val()){
                layer.msg("请输入房间号");
                stop = true;
                return true;
            }
            room['roomNumber'] =$this.find('.roomNumber').val();


            if(!$this.find('.roomAddr').val()){
                layer.msg("请输入房间地址");
                stop = true;
                return true;
            }
            room['roomAddr'] =$this.find('.roomAddr').val();

            if(!$this.find('.roomArea').val()){
                layer.msg("请输入房间地址");
                stop = true;
                return true;
            }
            room['roomArea'] =$this.find('.roomArea').val();

            room['id'] =$this.find('.roomId').val();
            room['roomSerialCode'] =$this.find('.roomSerialCode').val();
            roomArr.push(room);
        });

        if(stop) {
            return;
        }
        $.ajax({
            url: ctx + "customer/saveCustomer",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data:JSON.stringify( {
                id: $("#customerId").val(),
                projectId: projectId,
                customerName: customerName,
                customerEmail: customerEmail,
                customerTel: customerTel,
                customerSex: $("#customerSex").val(),
                customerAddr: $("#customerAddr").val(),
                openingBank:openingBank,
                bankName:bankName,
                bankNumber:bankNumber,
                idCard:idCard,
                customerProxyName:$("#customerProxyName").val(),
                customerRoomList:roomArr,
                status:$("#customerStatus").val()

            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "customer/tocustomerlist";
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
                           if ($("#customerId").val()) {
                               $.ajax({
                                          url: ctx + "customer/get",
                                          type: "GET",
                                          cache: false,
                                          async: false,
                                          dataType: 'json',
                                          data: {
                                              id: $("#customerId").val(),
                                          },
                                          success: function (data) {
                                              if (data && data.resultCode === '0') {
                                                  su = data.resultData;
                                                  if(su.status==20){
                                                      $("#bankNumberStr").show();
                                                      $("#bankNameStr").show();
                                                      $("#openingBankStr").show();
                                                      $("#idCardStr").show();
                                                  }
                                                  $("#projectSel").val(su.projectId);
                                                  $("#customerName").val(su.customerName);
                                                  $("#customerProxyName").val(su.customerProxyName);
                                                  $("#customerSex").val(su.customerSex);
                                                  $("#customerStatus").val(su.status);
                                                  $("#customerEmail").val(su.customerEmail);
                                                  $("#customerTel").val(su.customerTel);
                                                  $("#customerAddr").val(su.customerAddr);
                                                  $("#idCard").val(su.idCard);
                                                  $("#openingBank").val(su.openingBank);
                                                  $("#bankName").val(su.bankName);
                                                  $("#bankNumber").val(su.bankNumber);

                                                  $(su.customerRoomList).each(function (idx, customerRoom) {
                                                      if (idx > 0 && idx < su.customerRoomList.length) {
                                                          $(".p_addBtn").click();
                                                      }

                                                      var $room = $(".p_selingBox:last");
                                                      $room.find('.roomNumber').val(customerRoom.roomNumber);
                                                      $room.find('.roomAddr').val(customerRoom.roomAddr);
                                                      $room.find('.roomArea').val(customerRoom.roomArea);
                                                      $room.find('.roomSerialCode').val(customerRoom.roomSerialCode);
                                                      $room.find('.roomId').val(customerRoom.id);
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


    $("#personnalName, #personnalTel").blur(function () {
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
    $("#personnalTel").blur(function () {
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


    $("#personnalEmail").blur(function () {
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
