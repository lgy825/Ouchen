var hourseTypeMap={};
var projectId="";
$(function () {
    loadOrderStatus();
    initTimePicker();
    //加载订单状态
    function loadOrderStatus() {
        $("#orderStatus").select2({placeholder: '*选择订单状态*'});
        $("#orderStatus").append("<option value='-1'>*选择订单状态*</option>");
        $("#orderStatus").append("<option value='10'>已完成</option>"+
            "<option value='11'>待完成</option>"+
            // "<option value='12'>已入住</option>"+
            "<option value='13'>已取消</option>"
        );

    }


    function initTimePicker() {
        $('.times').each(function () {
            $(this).datetimepicker('destroy');
            $(this).datetimepicker({
                format: 'H:i',
                step: 1,
                datepicker: false
            });
        });
    }

    //出事化日其插件
    var timeSpick = $("#timeSpick").datetimepicker({
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
    var timeEpick = $("#timeEpick").datetimepicker({
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

    var timeComplete = $("#timeComplete").datetimepicker({
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

    loadPay();
    loadProduct();
    loadOrderSource();
    loadHourseAndType();
    loadPayWay();
    loadProject();

    $("#orderStatus").change(function(){
        if($("#orderStatus").val()==10){
            $("#completeStr").show();
        }else{
            $("#completeStr").hide();
        }
    });




    $("#saveBtn").click(function () {

        var cinemaChooseWay = $(".cinemar.on").attr("cinemaradio");
        var isChoose;
        if (cinemaChooseWay == 1) {
            isChoose=1;
        }else{
            isChoose=2;
            var $vous = $("#payTbody").find(".paycheck.cur");
            if($vous.length < 1) {
                layer.msg("请选择支出");
                return;
            }
            var pays = [];
            $($vous).each(function (idx, elem) {
                pays.push({
                    payId: $(elem).data("payid"),
                    amount:parseInt(($vous.parent().parent().parent().find("."+ $(elem).data("payid")).val()*100).toFixed(2)),
                    count:$vous.parent().parent().parent().find(".count_"+ $(elem).data("payid")).val()
                });

            });
        }

        //商品选项
        var productChooseWay = $(".productr.on").attr("productradio");
        var isChooseProduct;
        if (productChooseWay == 1) {
            isChooseProduct=1;
        }else{
            isChooseProduct=2;
            var $vouss = $("#productTbody").find(".productcheck.cur");
            if($vouss.length < 1) {
                layer.msg("请选择商品");
                return;
            }
            var productObjs = [];
            $($vouss).each(function (idx, elem) {
                productObjs.push({
                    productId: $(elem).data("productid"),
                    amount:parseInt(($vouss.parent().parent().parent().find("."+ $(elem).data("productid")).val() * 100).toFixed(2)),
                    count:$vouss.parent().parent().parent().find(".count_"+ $(elem).data("productid")).val()
                });

            });
        }

        if ($("#projectSel").val() == -1) {
            layer.msg("请选择房子所属的项目");
            return;
        }

        if ($("#paySel").val() == -1) {
            layer.msg("请选择支付方式");
            return;
        }

        if ($("#sourceSel").val() == -1) {
            layer.msg("请选择订单来源");
            return;
        }

        if ($("#orderStatus").val() == -1) {
            layer.msg("请选择订单状态");
            return;
        }
        var completeTime = timeComplete.val();
        if($("#orderStatus").val() ==10){
            if (completeTime.length < 1 ) {
                layer.msg("如果订单状态完成，请选择完成日期");
                return;
            }
        }

        if ($("#hourseSel").val() == -1) {
            layer.msg("请选择房间");
            return;
        }

        var orderActAmount = $.trim($("#orderActAmount").val());
        if (!orderActAmount) {
            layer.msg("请输入房费");
            return;
        } else if (!ValidUtils.validMoney(orderActAmount)) {
            layer.msg("房费不能包含特殊字符，保留一位有效数字");
            return;
        }

        var startTime = timeSpick.val();
        var endTime = timeEpick.val();
        var stimes=$("#stimes").val();
        var etimes=$("#etimes").val();
        if (startTime.length < 1 || endTime.length < 1) {
            layer.msg("请选择起止时间");
            return;
        } else {
            if (Date.parse(startTime) > Date.parse(endTime)) {
                layer.msg("结束日期不能早于开始日期");
                return;
            }
        }
        if(stimes.length<1){
            stimes="00:00:00";
        }else{
            stimes+=":00";
        }
        if(etimes.length<1){
            etimes="00:00:00";
        }else{
            etimes+=":00";
        }
        var nowDate = new Date();
        var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
        var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
        var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
        var time=hour+":"+minute+":"+second;

        $.ajax({
            url: ctx + "order/addOrder",
            type: "POST",
            cache: false,
            async: false,
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                id:$.trim($("#orderId").val()),
                orderRecAmount:orderActAmount,
                paramVos:pays,
                projectId: $.trim($("#projectSel").val()),
                orderWay: $.trim($("#paySel").val()),
                orderSource: $.trim($("#sourceSel").val()),
                hourseCode: $("#hourseSel").val(),
                orderStartDate:startTime+" "+stimes,
                orderEndTime:endTime+" "+etimes,
                orderActAmount:orderActAmount,
                isChoose:isChoose,
                isChooseProduct:isChooseProduct,
                productObjs:productObjs,
                orderStatus:$("#orderStatus").val(),
                completeTime:completeTime?completeTime+" "+time:""
            }),
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "order/toOrderlist";
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

    function loadPayWay() {
        $.ajax({
            url: ctx + "order/getPayWayAll",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    //
                    $("#paySel").select2({placeholder: '*选择支付方式*'});
                    $("#paySel").append("<option value='-1'>*选择支付方式*</option>");
                    $(data.resultData).each(function (idx, item) {
                        $("#paySel").append("<option value='" + item.id + "'>" + item.pName + "</option>");
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

    function loadOrderSource() {
        $.ajax({
            url: ctx + "order/getOrdeSourceAll",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {
                if (data && data.resultCode === '0') {
                    //
                    $("#sourceSel").select2({placeholder: '*选择订单来源*'});
                    $("#sourceSel").append("<option value='-1'>*选择订单来源*</option>");
                    $(data.resultData).each(function (idx, item) {
                        $("#sourceSel").append("<option value='" + item.id + "'>" + item.name + "</option>");
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

                    // 加载数据 -------------
                    if ($("#orderId").val()) {

                        $.ajax({
                            url: ctx + "order/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#orderId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;
                                    $(".cinemar").removeClass("on");
                                    $(".cinemar[cinemaradio=" + su.isChoose + "]").addClass("on");
                                    if (su.isChoose == 2) {
                                        $('#p_selCimemaPan').removeClass("none");
                                    }

                                    $(".productr").removeClass("on");
                                    $(".productr[productradio=" + su.isChooseProduct + "]").addClass("on");
                                    if (su.isChooseProduct == 2) {
                                        $('#p_selProductPan').removeClass("none");
                                    }

                                    var canedit = setInterval(function () {
                                        $(_.filter(su.orderDetails)).each(function (idx, elem) {
                                            console.info(".paycheck[data-payid=" + elem.payCode);
                                            $(".paycheck[data-payid='" + elem.payCode + "']").click();
                                            $(".paycheck[data-payid='" + elem.payCode + "']").parent().parent().parent().find("."+elem.payCode ).val(elem.amount);
                                            $(".paycheck[data-payid='" + elem.payCode + "']").parent().parent().parent().find(".span_"+elem.payCode ).html(elem.amount);
                                            $(".paycheck[data-payid='" + elem.payCode + "']").parent().parent().parent().find(".count_"+elem.payCode ).val(elem.count);
                                            $(".paycheck[data-payid='" + elem.payCode + "']").parent().parent().parent().find(".spanCount_"+elem.payCode ).html(elem.count);
                                        });
                                        $(_.filter(su.orderProducts)).each(function (idx, elem) {
                                            //console.info(".paycheck[data-payid=" + elem.payId );
                                            $(".productcheck[data-productid='" + elem.productCode + "']").click();
                                            $(".productcheck[data-productid='" + elem.productCode + "']").parent().parent().parent().find("."+elem.productCode ).val(elem.amount);
                                            $(".productcheck[data-productid='" + elem.productCode + "']").parent().parent().parent().find(".span_"+elem.productCode ).html(elem.amount);
                                            $(".productcheck[data-productid='" + elem.productCode + "']").parent().parent().parent().find(".count_"+elem.productCode ).val(elem.count);
                                            $(".productcheck[data-productid='" + elem.productCode + "']").parent().parent().parent().find(".spanCount_"+elem.productCode ).html(elem.count);
                                        });
                                        clearInterval(canedit);
                                    }, 50);
                                    $("#orderActAmount").val(su.orderRecAmount);
                                    $("#projectSel").val(su.projectId);
                                    $("#typeCodeSel").val(su.typeCode);
                                    $("#hourseSel").val(su.hourseCode);
                                    $("#paySel").val(su.orderWay);
                                    $("#sourceSel").val(su.orderSource);
                                    $("#orderStatus").val(su.orderStatus);
                                    timeSpick.val(su.orderStartDate.split(" ")[0]);
                                    timeEpick.val(su.orderEndTime.split(" ")[0]);
                                    if(su.orderStatus==10){
                                        $("#completeStr").show();
                                        timeComplete.val(su.completeTime.split(" ")[0]);
                                    }else{
                                        $("#completeStr").hide();
                                    }


                                    //$("#orderStatus").attr("disabled",true);

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


    //加载房屋类型和房间号
    function loadHourseAndType() {
        $.ajax({
            url: ctx + "hourse/getHourseAndType",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            success: function (data) {

                if (data && data.resultCode === '0') {
                    hourseTypeMap = data.resultData.hourseTypeMap;
                    var hourseType = data.resultData.hourseTypeList;
                    // 城市列表
                    $("#typeCodeSel").select2({placeholder: '请房间类型'});
                    $("#typeCodeSel").append("<option value='-1'>*请房间类型*</option>");
                    $(hourseType).each(function (idx, type) {
                        $("#typeCodeSel").append("<option value='" + type.id + "'>" +type.typeName + "</option>");
                    });

                    $("#typeCodeSel").change(function () {
                        $("#hourseSel").select2({placeholder: '请选择房间'});
                        $("#hourseSel").empty();
                        if($("#typeCodeSel").val() == -1) {
                            $(_.values(hourseTypeMap)).each(function (idx, hourses) {
                                $(hourses).each(function (idxtmp, hourse) {
                                    $("#hourseSel").append("<option value='" + hourse.id + "'>" + hourse.hourseNumber + "</option>");
                                });
                            });
                        } else {
                            $(hourseTypeMap[$("#typeCodeSel").val()]).each(function (idx, hourse) {
                                $("#hourseSel").append("<option value='" + hourse.id + "'>" + hourse.hourseNumber + "</option>");
                            });
                        }
                    });
                    $("#typeCodeSel").trigger('change');

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


    $("#payTbody").on('click', '.check', function () {
        var _this = $(this);
        var ccode = _this.attr("ccode");
        _this.toggleClass('cur');

    });
    $("#paySearch").on('keyup',function () {
        loadPay();
    });

    function loadPay() {
        var paySearch=$.trim($("#paySearch").val());
        $.ajax({
            url:ctx+"pay/getpage",
            type:"GET",
            cache: false,
            dataType: 'json',
            contentType: "application/json",
            data:{
                pageIndex: 1,
                pageSize: 99999,
                payName:paySearch
            },
            success: function (result) {
                if (result && result.resultCode === '0') {
                    $("#payTbody").empty();
                    if(result.resultData && result.resultData.list) {
                        $(result.resultData.list).each(function (index, item) {
                            $("#payTbody").append(
                                '<tr>' +
                                '<td><div><span class="paycheck checkBtn check w14" data-payid="'+item.payId+'"></span></div></td>' +
                                '<td><div>' + (_.isUndefined(item.payName) ? '' : item.payName) + '</div></td>' +
                                '<td><div><span class="relative"><span class="rename-inp inline-block span_'+item.payId+'">' + (item.payAmount ? item.payAmount : 0) + '</span>'
                                + '<i class="rename"></i><input  type="text"  value="'+item.payAmount+'" class="rename-inp none '+item.payId+'"></span></div></td>' +
                                '<td><div><span class="relative"><span class="rename-inp inline-block spanCount_'+item.payId+'">1</span>'
                                + '<i class="rename"></i><input  type="text"  value="1" class="rename-inp none count_'+item.payId+'"></span></div></td>' +
                                '<td><div title="'+item.payDesc+'">'+item.payDesc+'</div></td>' +
                                ' </tr>'
                            );
                        });
                    }
                } else {
                    if (result.resultDesc) {
                        layer.msg(result.resultDesc);
                    } else {
                        layer.msg('没有查询支出信息!');
                    }
                }
            },
            error: function () {
                layer.msg('查询支出失败!');
            }

        })
    }

    $('.oInfo_table').on('click', '.rename', function () {
        $(this).prev().hide();
        $(this).next().show().addClass('border-el');
    });


    $('.cinema_box').on('click', '.radio', function () {
        var _this = $(this),
            $data_i = _this.attr('data-i'),
            $p_selCimema = $('#p_selCimemaPan');

        if ($data_i == 1) {
            $p_selCimema.removeClass('none');
        } else {
            // 全部
            $p_selCimema.addClass('none');
            //clearSearch();
        }
        $(".cinemar").removeClass("on");
        $(this).addClass("on");
        clearSearch();
    });

    function clearSearch() {
        $("#paySearch").val("");
    }


    //商品列表展示
    $("#productTbody").on('click', '.check', function () {
        var _this = $(this);
        var ccode = _this.attr("ccode");
        _this.toggleClass('cur');

    });
    $("#productSearch").on('keyup',function () {
        loadProduct();
    });

    function loadProduct() {
        var productSearch=$.trim($("#productSearch").val());
        $.ajax({
            url:ctx+"product/getpage",
            type:"GET",
            cache: false,
            dataType: 'json',
            contentType: "application/json",
            data:{
                pageIndex: 1,
                pageSize: 99999,
                productName:productSearch
            },
            success: function (result) {
                if (result && result.resultCode === '0') {
                    $("#productTbody").empty();
                    if(result.resultData && result.resultData.list) {
                        $(result.resultData.list).each(function (index, item) {
                            $("#productTbody").append(
                                '<tr>' +
                                '<td><div><span class="productcheck checkBtn check w14" data-productid="'+item.id+'"></span></div></td>' +
                                '<td><div>' + (_.isUndefined(item.productName) ? '' : item.productName) + '</div></td>' +
                                '<td><div><span class="relative"><span class="rename-inp inline-block span_'+item.id+'">' + (item.productAmount ? item.productAmount : 0) + '</span>'
                                + '<i class="rename"></i><input  type="text"  value="'+item.productAmount+'" class="rename-inp none '+item.id+'"></span></div></td>' +
                                '<td><div><span class="relative"><span class="rename-inp inline-block spanCount_'+item.id+'">1</span>'
                                + '<i class="rename"></i><input  type="text"  value="1" class="rename-inp none count_'+item.id+'"></span></div></td>' +
                                '<td><div title="'+item.productDesc+'">'+item.productDesc+'</div></td>' +
                                ' </tr>'
                            );
                        });
                    }
                } else {
                    if (result.resultDesc) {
                        layer.msg(result.resultDesc);
                    } else {
                        layer.msg('没有查询支出信息!');
                    }
                }
            },
            error: function () {
                layer.msg('查询支出失败!');
            }

        })
    }

    $('.oInfo_tables').on('click', '.rename', function () {
        $(this).prev().hide();
        $(this).next().show().addClass('border-el');
    });


    $('.product_box').on('click', '.radio', function () {
        var _this = $(this),
            $data_i = _this.attr('data-i'),
            $p_selCimema = $('#p_selProductPan');

        if ($data_i == 1) {
            $p_selCimema.removeClass('none');
        } else {
            // 全部
            $p_selCimema.addClass('none');
            //clearSearch();
        }
        $(".productr").removeClass("on");
        $(this).addClass("on");
        clearProductSearch();
    });

    function clearProductSearch() {
        $("#productSearch").val("");
    }


    // $('.oInfo_table').on('click', 'tr', function () {
    //     $(this).find(".params").addClass("on");
    // });


});



