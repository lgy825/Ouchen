$(function () {


    $("#customerStatus").change(function(){
        if($("#customerStatus").val()==20){
            $("#bankNumberStr").show();
            $("#bankNameStr").show();
            $("#openingBankStr").show();
        }else{
            $("#bankNumberStr").hide();
            $("#bankNameStr").hide();
            $("#openingBankStr").hide();
        }
    });


    loadProject();

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
                               alert($("#customerId").val());
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
                                                      if (idx > 0 && idx < su.customerRoomList) {
                                                          $(".p_addBtn").click();
                                                      }

                                                      var $room = $(".p_selingBox:last");
                                                      $room.find('.roomNumber').val(customerRoom.roomNumber);
                                                      $room.find('.roomAddr').val(customerRoom.roomAddr);
                                                      $room.find('.roomArea').val(customerRoom.roomArea);
                                                      $room.find('.roomSerialCode').val(customerRoom.roomSerialCode);
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
});
