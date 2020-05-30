<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误提示页</title>
    <%@ include file="/static/commons/common.jspf" %>
</head>
<body>

<!-- 没有权限访问 -->
<div class="error-page none">
    <div class="bgc-ff full-white">
        <div class="error-prompt">
            <div class="error-img"></div>
            <p class="f14 mt20">对不起，您没有权限访问该页面，请联系系统管理员。</p>
            <div class="mt20"><em class="f18 orange">5s</em><span class="color-gray8c f14">后返回登录页面</span></div>
            <div class="mt20"><input type="button" class="blue_btn blue_btn30" value="立即返回"></div>
        </div>
    </div>
</div>

<!-- 没有权限访问 -->
<div class="error-page">
    <div class="bgc-ff full-white">
        <div class="error-prompt">
            <div class="error-img no-network"></div>
            <p class="f14 mt20">对不起，网络开小差了，系统出现未知错误。</p>
            <%--<div class="mt20" id="error"><em id="errorSpan"  class="f18 orange"></em><span class="color-gray8c">后返回登录页面</span></div>
            <div class="mt20"><input type="button" class="blue_btn blue_btn30" value="立即返回"></div>--%>
        </div>
    </div>
</div>

</body>
<script>

    /*$(function () {
        $("#error").click(function () {
            location.href = ctx + "/index/tologin.do"
        });
        console.log(ctx)
        var tim = 5;
        setInterval(function () {
            $("#errorSpan").html(tim + "s");
            if(tim === 0) {
                location.href = ctx + "/index/tologin.do"
            }
            tim--;
        }, 1000);

        // 铺满一屏
        fullScreen($('.full-white'), 42);
    });*/
    $(function () {
        // 铺满一屏
        fullScreen($('.full-white'), 40);

        // 改变窗口
        $(window).on('resize', function () {
            fullScreen($('.full-white'), 40);
        });
    });
</script>
</html>