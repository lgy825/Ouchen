$(function () {

    loadCompany();

    $("#saveBtn").click(function () {

        var companyCode = $.trim($("#companySel").val());
        if ($("#companyCode").val() == -1) {
            layer.msg("请选择app所属公司");
            return;
        }

        var appType = $.trim($("#appType").val());
        if ($("#appType").val() ==null) {
            layer.msg("请选择app类型");
            return;
        }

        var reg = /^\d+(\.\d+)+$/;
        var androidVersion = $("#androidVersion").val();
        if(!androidVersion){
            layer.msg('请输入安卓版本号!');
            return;
        }
        if(!reg.test(androidVersion)){
            layer.msg('请输入正确的安卓版本号!');
            return;
        }
        var title = $("#title").val();
        if(!title){
            layer.msg('请输入app标题!');
            return;
        }
        var appTel = $("#appTel").val();
        if(!appTel){
            layer.msg('请输入app联系电话!');
            return;
        }
        var appWeixin = $("#appWeixin").val();
        if(!appWeixin){
            layer.msg('请输入app联系微信!');
            return;
        }
        var downloadPath = $("#downloadPath").val();
        if(!downloadPath){
            layer.msg('请输入网址!');
            return;
        }
        var content = $("#content").val();
        if(!content){
            layer.msg('请输入app内容!');
            return;
        }

        $.ajax({
            url: ctx + "appmana/saveApp",
            type: "POST",
            cache: false,
            dataType: 'json',
            data: {
                id:$("#appId").val(),
                appType: appType,
                companyCode:companyCode,
                verNo:androidVersion,
                appTel: appTel,
                appWeixin: appWeixin,
                verTitle:title,
                verContent:content,
                downloadPath:downloadPath
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    layer.msg("保存成功");
                    location.href = ctx + "appmana/toApplist";
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
                    if ($("#appId").val()) {
                        $.ajax({
                            url: ctx + "appmana/get",
                            type: "GET",
                            cache: false,
                            async: false,
                            dataType: 'json',
                            data: {
                                id: $("#appId").val(),
                            },
                            success: function (data) {
                                if (data && data.resultCode === '0') {
                                    su = data.resultData;

                                    if(su.appType=='IOS'){
                                        $("#appType").val(0);
                                    }else if(su.appType=='Android'){
                                        $("#appType").val(1);
                                    }
                                    $("#companySel").val(su.companyCode);
                                    $("#appTel").val(su.appTel);
                                    $("#appWeixin").val(su.appWeixin);
                                    $("#title").val(su.verTitle);
                                    $("#content").val(su.verContent);
                                    $("#androidVersion").val(su.verNo);
                                    $("#downloadPath").val(su.downloadPath);
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
