<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//    session.setAttribute("LoginErrorNumber", 0);//初始化登陆错误次数

    String name = "" ;
    String password =  "";
    String flag = "0";
    String flychannle = "";

    Cookie[] cookies = request.getCookies();
    try{
        if(cookies!=null){
            for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("cookie_user")){
                    String value = cookies[i].getValue();
                    if(value!=null&&!"".equals(value)){
                        name = cookies[i].getValue().split("-")[0];
                        password=cookies[i].getValue().split("-")[1];
                        flag=cookies[i].getValue().split("-")[2];
                    }
                }
                if(cookies[i].getName().equals("cookie_user_flychannle")){
                    String value = cookies[i].getValue();
                    if(value!=null&&!"".equals(value)){
                        flychannle = value;
                    }
                }
                request.setAttribute("name",name);
                request.setAttribute("passward",password);
                request.setAttribute("flychannle", flychannle);
            }
        }
    }catch(Exception e){
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>云幕国际运营管理平台</title>
    <%@ include file="/static/commons/common.jspf"%>

</head>
<body class="container">

<div>
    <div class="login-box clearfix">
        <div class="login-text fl">
            <h1 class="login-logo"></h1>
            <p class="mt20 fz30">互联网智慧运营专家</p>
            <p class="text-large mt20">云幕国际管理平台</p>
            <p class="mt20 fz30">全面助力酒店公寓时代</p>
        </div>
        <div class="login-input fr">
            <p class="f24 fw tc">登 录</p>
            <div class="username-wrap mt30 relative">
                <input id="username" type="text" class="user-name" value="<%=name %>" placeholder="用户名">
            </div>
            <div class="password-wrap mt20 relative">
                <input id="passwd" type="password" class="password" value="<%=password %>" placeholder="密码">
            </div>
            <div class="code-wrap mt20 relative clearfix">
                <div class="fl">
                    <input id="verifyCode" type="text" class="discount-code" placeholder="验证码">
                </div>
                <div class="fr mt6">
                    <img id="checkImg" class="code-icon" src="<%=path%>/index/getCodeImg?type=math"  onclick="checkCode()">
                </div>
            </div>
            <div class="choose-login clearfix mt20">
                <span class="checkBtn check fl" id="flag">记住密码</span>
            </div>
            <div class="tc mt30">
                <input id="login" type="button" class="blue_btn login-btn" value="登录" onclick="login()">
            </div>
        </div>
    </div>
</div>

<div class="company-info">
    <p class="telphone">业务咨询热线151-1797-3890，周一 至 周日 09:30-18:00</p>
    <p class="mt20">Copyright &copy; 重庆云幕国际 All rights reserved. 渝ICP备16008734号</p>
</div>

<script>
    $(function () {
        $("#username").focus().keypress(function (event) {
            var e = event ? event : (window.event ? window.event : null);
            if (e.which == 13) {
                $("#passwd").focus()
            }
        });
        $("#passwd").keypress(function (event) {
            var e = event ? event : (window.event ? window.event : null);
            if (e.which == 13) {
                $("#verifyCode").focus();
            }
        });
        $("#verifyCode").keypress(function (event) {
            var e = event ? event : (window.event ? window.event : null);
            if (e.which == 13) {
                login();
            }
        });
    });

    function login() {
        var username = $("#username").val().replace(/(^\s*)|(\s*$)/g, "");
        var passwd = $("#passwd").val().replace(/(^\s*)|(\s*$)/g, "");
        var verifyCode = $("#verifyCode").val();
        var flag = "<%=flag%>";
        if ($("#flag").hasClass('cur')) {
            flag = 1;
        } else {
            flag = 0;
        }
        if (username == "") {
            layer.msg('请输入用户名 !');
            return fasle;
        }
        if (passwd == "") {
            layer.msg('请输入密码 !');
            return false;
        }
        if (verifyCode == "") {
            layer.msg('请输入验证码 !');
            return false;
        }
        $.ajax({
            type: 'post',
            async: false,
            cache: false,
            url: '${ctx}/login',
            dataType: 'json',
            data: {
                "username": username,
                "passwd": passwd,
                "verifyCode": verifyCode,
                "flag": flag
            },
            success: function (data) {
                if (data.resultData) {
                    window.location.href = ctx + data.resultData;
                } else {
                    if(data.resultDesc) {
                        layer.msg(data.resultDesc);
                    }
                    window.setTimeout(function () {
                        checkCode()
                    }, 500)
                }
            }
        });
    }

    function checkCode() {

        $("#checkImg").attr("src",  '${ctx}/index/getCodeImg?type=math');
    }

    $(".check").click(function () {
        $(this).toggleClass("cur");
    });
</script>
</body>
</html>