<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增公司</title>
    <%@include file="/static/commons/common.jspf" %>
    <link href="${ctx}/static/css/bootstrap-fileupload.min.css" rel="stylesheet">
    <script src="${ctx}/static/js/lib/fileinput/fileinput.min.js"></script>
    <script src="${ctx}/static/js/lib/fileinput/fileinput_locale_zh.js"></script>
    <script src="${ctx}/static/js/lib/fileinput/ajaxfileupload.js"></script>
<body>
<div class="p20">
    <div class="bgc-ff full-white">
        <!-- 新增公司 -->
        <div class="b_title">新增公司</div>
        <div class="hr">
            <hr>
        </div>
        <input id="companyId" type="hidden" value="${companyId}" />
        <div class="prbl20">
            <!-- 公司信息 -->
            <div class="tab-content">
                <!-- 必填选项 -->
                <div class="">
                    <div class="align-r"><span class="color-lred">* </span>公司名称</div>
                    <input type="text" id="companyName" class="inpW wid-544 ml8" placeholder="请输入公司名称，建议不超过50个字" maxlength="50">
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r"><span class="color-lred">* </span>公司描述</div>
                    <input type="text" id="companyDescription" class="inpW wid-544 ml8" placeholder="请输入公司描述，例：云幕国际" maxlength="50">
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r">公司地址</div>
                    <input type="text" id="companyAddress" class="inpW wid-544 ml8" placeholder="请输入公司地址">
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r">公司邮编</div>
                    <input type="text" id="companyZipcode" class="inpW wid-544 ml8"  maxlength="6" onkeyup="if(event.keyCode !=37 && event.keyCode != 39){if (!/^[\d]+$/ig.test(this.value)){this.value='';}}"  placeholder="请输入6位数邮编">
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r">联系人及电话</div>
                    <input type="text" id="phoneName" class="inpW set-inpwid ml8" placeholder=" 请输入联系人姓名">
                    <input type="text" id="phoneNo" class="inpW ml8" maxlength="11" placeholder="请输入联系人电话">
                </div>
                <div class="mt12 clearfix">
                    <div class="align-r fl mt6"><span class="color-lred">* </span>公司LOGO</div>
                    <div class="fl relative ml10">
                        <%--<input type="button" class="blue_btn ml8 prefix-img" value="上传文件">--%>
                        <input type="file" id="myfile" name="myfile" data-allowed-file-extensions='["png"]' data-show-upload="true" data-show-caption="false">
                        <span class="color-lred ml8">请上传1024*1024的LOGO图，格式要求PNG。</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="mt30 tc">
            <input type="button" id="saveBtn" class="blue_btn blue_btn30 mb20 pres-btn" value="保存">
        </div>
    </div>
</div>
</body>
<script>
    var filename = $('#filename').val();
    var logoUrl=null;
    var imageUrl=$("#imageUrl").val();
    if(filename!=null &&filename!=""){
        logoUrl= "<img style='height:160px' src='"+imageUrl+filename+"'>";
    }
    $("#myfile").fileinput({
        dropZoneEnabled: false,
        showCaption:false,
        maxFileSize:1024,
        maxImageWidth: 1024,
        maxImageHeight: 1024,
        uploadUrl: ctx+'company/uploadImg', // server upload action
        uploadAsync: true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        showPreview:true,
        initialPreview: [
            logoUrl==null?null:logoUrl,
        ]
    }).on('fileuploaded', function(logo, data, id, index) {
        $("#filename").val(data.response.resultData.path);
    }).on('filecleared', function(logo, data, id, index) {
        $("#filename").val("");
    });
</script>
<!-- scripts -->
<script type="text/javascript" src="${ctx}/static/js/mod/company/addcompany.js"></script>
</html>