<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>工作台</title>
    <%@include file="/static/commons/common.jspf"%>
</head>
<body>
<!-- 工作台 -->
<div class="p20">
    <!-- 搜索框 -->
<%--    <div class="select-search">--%>
<%--        <form action="">--%>
<%--            <div>--%>
<%--                <select class="select  ml20" id="projectSel">--%>
<%--                </select>--%>
<%--                <input type="button" class="blue_btn ml20" value="更新数据"/>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
    <!-- 票务数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">订单数据</div>
            <span class="title-des fl color-gray8c">数据为今日数据，实时更新</span>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <c:set var="todaySellSaleFee" value="${todayResult['orderRecAmountAll']==null ?0 :todayResult['orderRecAmountAll']}"/>
                        <c:set var="yesdaySellSaleFee" value="${yesResult['orderRecAmountAll']==null ? 0:yesResult['orderRecAmountAll']}"/>
                        <p class="mt20 color-gray8c">订单总收益(元)</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todaySellSaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todaySellSaleFee-yesdaySellSaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdaySellSaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todaySellSaleFee-yesdaySellSaleFee}" maxFractionDigits="1" minFractionDigits="1"/>
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdaySellSaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdaySellSaleFee-todaySellSaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <c:set var="todayActSaleFee" value="${todayResult['orderActmountAll']==null ?0 :todayResult['orderActmountAll']}"/>
                        <c:set var="yesdayActSaleFee" value="${yesResult['orderActmountAll']==null ? 0:yesResult['orderActmountAll']}"/>
                        <p class="mt20 color-gray8c">房间总收益(元)</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todayActSaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todayActSaleFee-yesdayActSaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdayActSaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todayActSaleFee-yesdayActSaleFee}" maxFractionDigits="1" minFractionDigits="1"/>
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdayActSaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdayActSaleFee-todayActSaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <c:set var="todayPaySaleFee" value="${todayResult['orderPayAmountAll']==null ?0 :todayResult['orderPayAmountAll']}"/>
                        <c:set var="yesdayPaySaleFee" value="${yesResult['orderPayAmountAll']==null ? 0:yesResult['orderPayAmountAll']}"/>
                        <p class="mt20 color-gray8c">支出总额(元)</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todayPaySaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todayPaySaleFee-yesdayPaySaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdayPaySaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todayPaySaleFee-yesdayPaySaleFee}" maxFractionDigits="1" minFractionDigits="1"/>
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdayPaySaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdayActSaleFee-todayPaySaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10">
                        <p class="mt20 color-gray8c">商品收益(元)</p>
                        <p class="mt30 color-blue fw f24">0</p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <span class="data-up color-red"><i class="up-rise">0%</i></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!-- 卖品数据 -->
    <div class="bgc-ff mt20">
        <div class="clearfix b_title">
            <div class="fl mr20">房间数据</div>
            <span class="fl color-gray8c title-des">数据为今日数据，实时更新</span>
        </div>
        <div class="hr">
            <hr>
        </div>
        <div class="data-wrap p20">
            <ul class="data-list-wrap clearfix">
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <c:set var="hourseCount" value="${todayResult['hourseCount']==null ?0 :todayResult['hourseCount']}"/>
                        <p class="mt20 color-gray8c">总房间数</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${hourseCount}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                    </div>
                </li>
                <li class="data-list">
                    <div class="data-content mr10 relative">
                        <c:set var="todayCountSaleFee" value="${todayResult['orderCountAll']==null ?0 :todayResult['orderCountAll']}"/>
                        <c:set var="yesdayCountSaleFee" value="${yesResult['orderCountAll']==null ? 0:yesResult['orderCountAll']}"/>
                        <p class="mt20 color-gray8c">入住人次</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todayCountSaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todayCountSaleFee-yesdayCountSaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdayCountSaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todayCountSaleFee-yesdayCountSaleFee}" maxFractionDigits="1" minFractionDigits="1"/>
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdayCountSaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdayCountSaleFee-todayCountSaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li class="data-list ">
                    <div class="data-content mr10">
                        <c:set var="todaysurplusSaleFee" value="${todayResult['surplusHouser']==null ?0 :todayResult['surplusHouser']}"/>
                        <c:set var="yesdaysurplusSaleFee" value="${yesResult['surplusHouser']==null ? 0:yesResult['surplusHouser']}"/>
                        <p class="mt20 color-gray8c">房间剩余</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todaysurplusSaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todaysurplusSaleFee-yesdaysurplusSaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdaysurplusSaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todaysurplusSaleFee-yesdaysurplusSaleFee}" maxFractionDigits="1" minFractionDigits="1"/>
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdaysurplusSaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdaysurplusSaleFee-todaysurplusSaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
                <li class="data-list ">
                    <div class="data-content mr10">
                        <c:set var="todayOccupSaleFee" value="${todayResult['occupancynew']==null ?0 :todayResult['occupancynew']}"/>
                        <c:set var="yesdayOccupSaleFee" value="${yesResult['occupancynew']==null ? 0:yesResult['occupancynew']}"/>
                        <p class="mt20 color-gray8c">出租率</p>
                        <p class="mt30 color-blue fw f24">
                            <fmt:formatNumber value="${todayOccupSaleFee}" maxFractionDigits="2" minFractionDigits="2"/>
                        </p>
                        <div class="mt30">
                            <span class="color-gray8c">比前一天</span>
                            <c:choose>
                                <c:when test="${todayOccupSaleFee-yesdayOccupSaleFee >=0}">
                                        <span class="data-up color-red">
                                                    <i class="up-rise">
                                                         <c:choose>
                                                             <c:when test="${yesdayOccupSaleFee== 0}">
                                                                 -%
                                                             </c:when>
                                                             <c:otherwise>
                                                                 <fmt:formatNumber value="${todayOccupSaleFee-yesdayOccupSaleFee}" maxFractionDigits="1" minFractionDigits="1"/>%
                                                             </c:otherwise>
                                                         </c:choose>
                                                    </i>
                                         </span>
                                </c:when>
                                <c:otherwise>
                                        <span class="data-down color-green">
                                            <i class="up-rise">
                                                 <c:choose>
                                                     <c:when test="${yesdayOccupSaleFee== 0}">
                                                         -%
                                                     </c:when>
                                                     <c:otherwise>
                                                         <fmt:formatNumber value="${(yesdayOccupSaleFee-todayOccupSaleFee)}" maxFractionDigits="1" minFractionDigits="1"/>%
                                                     </c:otherwise>
                                                 </c:choose>
                                            </i>
                                        </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- 引导层 -->
<div class="modality-layer none">
    <div class="mask-box">
        <div class="guide-bg">
            <span class="iknow"></span>
        </div>
    </div>
</div>

</body>
<script>
    $(function () {
        $('.list-boxoffice').on('mouseenter', function () {
            $(this).hide();
            $(this).next().show();
        });
        $('.list-activity').on('mouseleave', function () {
            $(this).hide();
            $(this).prev().show();
        });

        // 引导层关闭
        $('.iknow').on('click', function () {
            $('.modality-layer').hide();
        });

    });
</script>
<script type="text/javascript">

</script>
</html>