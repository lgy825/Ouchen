<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit">
    <title>订单详情</title>
    <%@ include file="/static/commons/common.jspf" %>
</head>
<body>
<div class="p20">
    <div class="bgc-ff">
        <h1 class="b_title">基本信息</h1>
        <div class="hr">
            <hr>
        </div>
        <input id="orderId" type="hidden" value="${orderId}"/>
        <div class="movBox movBox1 clearfix p20 modify-percent">
            <div>
                <div>
                    <span class="color-graya8">订单号</span>
                    <p class="mt10">${orderExt.id}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">房间信息</span>
                    <p class="mt10">${orderExt.hourseNumber}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">订单创建时间</span>
                    <p class="mt10">
                        <fmt:formatDate value="${orderExt.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">订单来源</span>
                    <p class="mt10">${orderExt.sourceWay}</p>
                </div>
            </div>
            <div class="w17">
                <div>
                    <span class="color-graya8">订单金额(元)</span>
                    <p class="mt10">${orderExt.orderRecAmount}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">实收金额</span>
                    <p class="mt10 color-blue">${orderExt.orderActAmount==''||orderExt.orderActAmount==null ? "0" :orderExt.orderActAmount}</p>
                </div>
            </div>
            <div>
                <div>
                    <span class="color-graya8">商品金额</span>
                    <p class="mt10 color-blue">${orderExt.orderProAmount==''||orderExt.orderProAmount==null ? "0" :orderExt.orderProAmount}</p>
                </div>
            </div>
            <div class="mt12">
                <div>
                    <span class="color-graya8">支付方式</span>
                    <p class="mt10 color-blue">${orderExt.payWay}</p>
                </div>
            </div>
            <div class="mt12">
                <div>
                    <span class="color-graya8">订单状态</span>
                    <p class="mt10 color-blue">${orderExt.orderStatusStr}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="bgc-ff mt20">
        <h1 class="b_title">订单支出详情</h1>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="movBox movBox1 clearfix modify-percent">
                <div>
                    <div>
                        <span class="color-graya8">支出名称</span>
                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">单价(元)</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">数量</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">总额</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">描述</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">创建时间</span>
                    </div>
                </div>
            </div>
            <c:forEach items="${orderExt.orderDetails}" var="item">
                <div class="movBox movBox1 clearfix modify-percent mt10">
                    <div>
                        <div>
                            <p>${item.payName}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.amount}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.count}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.allAmount}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p>${item.payDesc==''||item.payDesc==null ? "--" :item.payDesc}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="bgc-ff mt20">
        <h1 class="b_title">订单商品详情</h1>
        <div class="hr">
            <hr>
        </div>
        <div class="p20">
            <div class="movBox movBox1 clearfix modify-percent">
                <div>
                    <div>
                        <span class="color-graya8">商品名称</span>
                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">单价(元)</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">数量</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">总额(元)</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">描述</span>

                    </div>
                </div>
                <div>
                    <div>
                        <span class="color-graya8">创建时间</span>
                    </div>
                </div>
            </div>
            <c:forEach items="${orderExt.orderProducts}" var="item">
                <div class="movBox movBox1 clearfix modify-percent mt10">
                    <div>
                        <div>
                            <p>${item.productName}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.amount}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.count}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatNumber value="${item.allAmount}" maxFractionDigits="2"
                                                 minFractionDigits="2"/></p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p>${item.productDesc==''||item.productDesc==null ? "--" :item.productDesc}</p>
                        </div>
                    </div>
                    <div>
                        <div>
                            <p><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div>
        <div>
            <div class="tc mt30">
                <input type="button" id="rtBtn" class="blue_btn blue_btn30" value="返回"/>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#rtBtn").on("click", function (event) {
        window.history.back();
    });
</script>
</body>
</html>