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

                    getSS();
                    if(sessionStorage.getItem(ss_prefix + "_projectSel") && sessionStorage.getItem(ss_prefix + "_projectSel") !== "null"){
                        var cinemas = sessionStorage.getItem(ss_prefix + "_projectSel").split(",");
                        //$("#cinemaCode").select2('val', [11062001,11061501]).trigger('change');
                        $("#projectSel").val(cinemas);
                    } else {
                        $("#projectSel").val("-1");
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
