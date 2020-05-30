$(function () {

    if ($("#companyId").val()) {
        $.ajax({
            url: ctx + "company/get",
            type: "GET",
            cache: false,
            async: false,
            dataType: 'json',
            data: {
                id: $("#companyId").val(),
            },
            success: function (data) {
                if (data && data.resultCode === '0') {
                    su = data.resultData;
                    $("#companyName").val(su.companyName);
                    $("#companyCode").val(su.companyCode);
                    $("#companyDescription").val(su.companyDescription);
                    $("#companyAddress").val(su.companyAddress);
                    $("#companyZipcode").val(su.companyZipcode);
                    $("#phoneName").val(su.phoneName);
                    $("#phoneNo").val(su.phoneNo);
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

    $("#saveBtn").on("click", function () {
        var companyCode = $("#companyCode").val();
        var ischek = checkFied();
        if (ischek) {
            $.ajax({
                url: ctx + "company/save",
                type: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify({
                    companyName: $.trim($("#companyName").val()),
                    companyCode:companyCode,
                    companyDescription: $.trim($("#companyDescription").val()),
                    companyAddress: $.trim($("#companyAddress").val()),
                    companyZipcode: $.trim($("#companyZipcode").val()),
                    phoneName: $.trim($("#phoneName").val()),
                    phoneNo: $.trim($("#phoneNo").val()),
                    logoid: $("#filename").val()
                }),
                success: function (data) {
                    layer.msg("保存成功");
                    location.href = ctx + "company/toCompanylist";
                },
                error: function () {
                    layer.msg("服务器错误，请刷新页面后重试！");
                },
                beforeSend: function () {
                    layer.load(1, {shade: [0.3]})
                }
            })
        }
    });


    function checkFied() {
        if ($("#companyName").val() == "" || $("#companyName").val() == null) {
            layer.msg('请填写公司名称!', {
                icon: 5,
                skin: 'layui-layer-lan',
                closeBtn: 0
            });
            return false;
        }
        // if ($("#companyCode").val() == "" || $("#companyCode").val() == null) {
        //     layer.msg('请填写公司Code!', {
        //         icon: 5,
        //         skin: 'layui-layer-lan',
        //         closeBtn: 0
        //     });
        //     return false;
        // }
        if ($('#companyZipcode').val() != "" && $('#companyZipcode').val() != null) {
            var reg = /^[0-9]*[1-9][0-9]*$/;
            var companyzipcode = $('#companyZipcode').val()
            if (!reg.test(companyzipcode) || companyzipcode.length != 6) {
                layer.msg('邮编号只能为6位数字!', {
                    icon: 5,
                    skin: 'layui-layer-lan',
                    closeBtn: 0
                });
                return false;
            }
        }
        if ($("#companyDescription").val() == "" || $("#companyDescription").val() == null) {
            layer.msg('请填写公司描述!', {
                icon: 5,
                skin: 'layui-layer-lan',
                closeBtn: 0
            });
            return false;
        }

        // if ($("#filename").val() == "" || $("#filename").val() == null) {
        //     layer.msg('请上传公司LoGo!', {
        //         icon: 5,
        //         skin: 'layui-layer-lan',
        //         closeBtn: 0
        //     });
        //     return false;
        // }
        if (phoneName.length > 16) {
            layer.msg("联系人名称输入过长");
            return false;
        }
        return true;
    }
});