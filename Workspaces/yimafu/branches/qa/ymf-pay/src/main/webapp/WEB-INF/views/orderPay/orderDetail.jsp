<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<meta name="format-detection" content="telephone=no"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/layer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/layer.m.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/tools.js"></script>
<title>易码付-订单详情</title>
<script type="text/javascript">
function doPay(){
	var payStatus = "${param.payStatus }" ;
	if (payStatus == "0") {
		Tools.alert("订单已支付");
		return false ;
	}
    var timeOut = 150;
    var load =null;
   	url = "${pageContext.request.contextPath }/orderPay/doPay" ;
    $.ajax({
        url : url,
        type: "POST",
        data: $('#payForm').serialize(),
        dataType: "json",
        contentType: "application/x-www-form-urlencoded;charset=gbk", 
        beforeSend: function(){
            load = Tools.load();
        },
        success: function(resp){
            setTimeout(function(){
                layer.closeAll(load);
                if(resp.code=='00'){
                    var payHref = resp.data ;
                    payHref = payHref.trim() ;
                    if (payHref.indexOf("http") >= 0) {
                    } else {
                    	payHref = "http://" + payHref ;
                    }
                    window.location.href = payHref ;
                } else {
                    Tools.alert(resp.msg);
                }
            },timeOut);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            setTimeout(function(){
                layer.closeAll(load);
                Tools.alert("请求失败，请重试");
            } , timeOut);
        }
    });
    return false ;
}
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/orderPay/doPay" method="POST" id="payForm">
<div class="logo" id="logo"><img src="${param.customerLogo }"></div>
<div class="title">${param.customerName }</div>
<div class="content">
	<span class="bg-lf"></span>
	<div class="infor">
		<!-- 计划商品名称为 二维码对应个人账户资金安全保险 -->
		<p>商品名称：<span>${param.custShotName }-保险缴费</span></p>
		<!-- <p>险种：<span>汽车商业保险</span></p> -->
		<p>缴费通知单号：<span>${param.customerRequestId}</span></p>
		<p>投保人：<span>${param.receiverName }</span></p>
		<%
			String receiverTel = request.getParameter("receiverTel") ;
			if (receiverTel != null && !"".equals(receiverTel)) {
				out.print("<p>投保人电话：<span>" + receiverTel + "</span></p>") ;
			}
		%>
		<%-- <p>投保人电话：<span>${param.receiverTel }</span></p> --%>
		<!-- <p>车牌号：<span>京N68E73</span></p> -->
		<p>支付状态：
		<span>
		<%
			String status = request.getParameter("payStatus") ;
			if ("0".equals(status)) {
				out.print("已支付") ;
			} else {
				out.print("未支付") ;
			}
		%>
		</span></p>
		
		<!-- 如果是已支付，显示支付时间。 -->
	</div>
	<div class="total"><p>您共需支付：<span><b>${param.transAmt }</b>元</span></p></div>
	<span class="bg-rg"></span>
</div>
<input name="openId" id="openId" value="${param.openId}" type="hidden"/>
<input name="qrCode" id="qrCode" value="${param.qrCode}" type="hidden"/>
<input name="customerName" id="customerName" type="hidden" value="${param.customerName }"/>
<input name="customerNumber" id="customerNumber" type="hidden" value="${param.customerNumber }"/>
<input name="receiverName" id="receiverName" type="hidden" value="${param.receiverName }"/>
<input name="receiverTel" id="receiverTel" type="hidden" value="${param.receiverTel }"/>
<input name="payStatus" id="payStatus" type="hidden" value="${param.payStatus }"/>
<input name="transAmt" id="transAmt" type="hidden" value="${param.transAmt }"/>
<input name="payDate" id="payDate" type="hidden" value="${param.payDate }"/>
<input name="customerRequestId" id="customerRequestId" type="hidden" value="${param.customerRequestId }"/>
<input name="orderSign" id="orderSign" type="hidden" value="${param.orderSign }"/>
<input name="customerKey" id="customerKey" type="hidden" value="${param.customerKey }"/>
<input name="custShotName" id="custShotName" type="hidden" value="${param.custShotName }"/>
<div class="btn-box">
	<input class="input-btn" type="submit" value="立即支付" onClick="return doPay();">
</div>
</form>
</body>
</html>