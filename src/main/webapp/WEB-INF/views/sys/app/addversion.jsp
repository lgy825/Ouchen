<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增APP版本</title>
    <%@ include file="/static/commons/common.jspf" %>
    <script src="${ctx}/static/js/mod/sys/app/addversions.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/jquery.datetimepicker.css">
    <script type="text/javascript" src="${ctx}/static/js/lib/jquery.datetimepicker.js"></script>
    <link href="${ctx}/static/css/bootstrap-fileupload.min.css" rel="stylesheet">
    <script src="${ctx}/static/js/lib/fileinput/fileinput.min.js"></script>
    <script src="${ctx}/static/js/lib/fileinput/fileinput_locale_zh.js"></script>
    <script src="${ctx}/static/js/lib/fileinput/ajaxfileupload.js"></script>
</head>
<body>
<div class="p20">
    <div class="bgc-ff full-white">
        <!-- 新增公司 -->
        <div class="b_title">新增APP版本</div>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <!-- 必填选项 -->
            <input id="appId" type="hidden" value="${appId}" />
            <div class="">
                <div class="align-r mr8">选择公司</div>
                <select id="companySel" class="select ml20 wid-238">
                </select>
            </div>
            <div class="mt12 clearfix">
                <div class="align-r mr8">升级类型</div>
                <select id="appType" class="select mr20 wid-238">
                    <option value="0">IOS</option>
                    <option value="1">Android</option>
                </select>
                <%--<span class="checkBtn check radio on">强制升级</span>
                <span class="checkBtn check radio ml20">非强制升级</span>--%>
            </div>
            <div class="mt12 clearfix">
                <div class="align-r mr8">Android版本号</div>
                <input type="text" id="androidVersion" class="inpW wid-238" placeholder="如2.8.5,必填">
            </div>
            <div class="mt12 clearfix">
                <div class="align-r mr8">网址</div>
                <input type="text" id="downloadPath" class="inpW wid-238" placeholder="请输入网址">
            </div>
            <div class="mt12 clearfix">
                <div class="align-r mr8">app联系电话</div>
                <input type="text" id="appTel" class="inpW wid-238" placeholder="如2.8.5,必填">
            </div>
            <div class="mt12 clearfix">
                <div class="align-r mr8">app联系微信</div>
                <input type="text" id="appWeixin" class="inpW wid-238"  placeholder="如2.8.5,必填">
            </div>

            <div class="mt12 clearfix">
                <div class="align-r mr8">app标题</div>
                <input type="text" id="title"  class="inpW wid-238" placeholder="升级关键标题,必填">
            </div>
            <div class="mt12 clearfix">
                <div class="align-r fl">app内容</div>
                <div class="fl ml12 text-des rights-txt">
                    <textarea id="content" ></textarea>
                </div>
            </div>
<%--            <div class="mt12 clearfix">--%>
<%--                <div class="align-r fl mt6">上传升级文件</div>--%>
<%--                <div class="fl ml12 relative">--%>
<%--                    <input type="file" id="apk" name="apk" data-allowed-file-extensions='["apk"]' data-show-upload="true" data-show-caption="false">--%>
<%--                    <input type="hidden" id="apkUrl" class="form-control fl w180" placeholder="">--%>
<%--                    <input type="hidden" id="originalName" class="form-control fl w180" placeholder="">--%>
<%--                    <span class="color-lred ml8">仅限上传安卓升级文件，IOS需通过App Store上传</span>--%>
<%--                </div>--%>
<%--                &lt;%&ndash;<div class="fl ml12 relative">--%>
<%--                    <input type="button" class="blue_btn prefix-img" value="上传文件">--%>
<%--                    <input type="file" class="uld-file">--%>
<%--                    <span class="color-lred ml8">仅限上传安卓升级文件，IOS需通过App Store上传</span>--%>
<%--                </div>&ndash;%&gt;--%>
<%--              &lt;%&ndash;  <div class="fl relative ml10">--%>
<%--                    &lt;%&ndash;<input type="button" class="blue_btn ml8 prefix-img" value="上传文件">&ndash;%&gt;--%>
<%--                    <input type="file" id="myfile" name="myfile" data-allowed-file-extensions='["png"]' data-show-upload="true" data-show-caption="false">--%>
<%--                    <span class="color-lred ml8">请上传1024*1024的LOGO图，格式要求PNG。</span>--%>
<%--                </div>&ndash;%&gt;--%>
<%--            </div>--%>
        </div>

        <div class="mt30 tc">
            <input type="button" id="saveBtn" class="blue_btn blue_btn30" value="完成">
            <a href="${ctx}/appmana/toApplist">
                <input type="button" class="gray_btn gray-btn30 ml20" value="返回列表">
            </a>
        </div>
    </div>
</div>

</body>

<!-- scripts -->
<script>
    $(function () {
        // 占满一屏
        fullScreen($('.full-white'), 40);
    });

    var starturl=null;

    $("#apk").fileinput({
        dropZoneEnabled: false,
        showCaption:false,
        maxImageWidth: 1024,
        maxImageHeight: 1024,
        uploadUrl: ctx+ "sysVersions/upload.do",
        uploadAsync: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        showPreview:true,
        uploadExtraData:function(previewId, index){
            var obj = {companyCode:$("#cinemaSel").val()};
            return obj;
        }
    }).on('fileuploaded', function(logo, data, id, index) {
        var datefileName=  JSON.parse(data.response)

        $("#apkUrl").val(datefileName.filename);
        $("#originalName").val(datefileName.originalName);
    }).on('filecleared', function(logo, data, id, index) {
        $("#apkUrl").val("");
        $("#originalName").val("");
    });








</script>
</html>