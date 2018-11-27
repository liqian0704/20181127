<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
<title>易码付-订单查询</title>
<script type="text/javascript">

function doQuery(){
	var canNotDo = document.querySelector(".input-btn").classList.contains("not") ;
	if (canNotDo) {
		return false ;
	}
    var orderNumElementText = $("#orderNo");
    var orderNum = $.trim(orderNumElementText.val());
    var timeOut = 150;
    var load =null;
    if (orderNum == '') {
    	Tools.alert("请输入查单信息");
    	return false ;
    }

    var requestParm = "customerRequestId=" + orderNum
    $.ajax({
        url : "${pageContext.request.contextPath }/pay/queryCodOrder",
        type: "POST",
        data: requestParm,
        dataType: "json",
        contentType: "application/x-www-form-urlencoded;charset=GBK", 
        beforeSend: function(){
            load = Tools.load();
        },
        success: function(resp){
            setTimeout(function(){
                layer.closeAll(load);
                if(resp.code=='00'){
                    parseToPayPage(resp,resp.data);
                } else {
                    Tools.alert(resp.msg);
                }
            },timeOut);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
            setTimeout(function(){
                layer.closeAll(load);
                Tools.alert("查询失败，请重试");
            } , timeOut);
        }
    });
    return false ;
}

function parseToPayPage(resp,order){
    $("#customerNumber").val(encodeURI(order.customerNumber)) ;
    $("#customerName").val(order.customerName) ;
    $("#receiverName").val(order.receiverName) ;
    $("#receiverTel").val(order.receiverTel) ;
    $("#payStatus").val(order.payStatus) ;
    $("#transAmt").val(order.transAmt) ;
    $("#payDate").val(order.payDate) ;
    $("#customerRequestId").val(order.customerRequestId) ;
    $("#orderSign").val(order.orderSign) ;
    $("#customerKey").val(order.customerKey) ;
    $("#custShotName").val(order.custShotName) ;
    $("#form2").attr('action','${pageContext.request.contextPath }/pay/orderDetail');
    $("#form2").submit() ;
}
</script>
</head>
<body>
<div class="box">
    <div class="logo" id="logo"><img src="${customerLogo }"></div>
    <div class="title">付款给<br/><br/>${customerName }</div>
<br/>
<br/>
    <input name="customerNo" id="customerNo" type="hidden" value="${customerNumber }"/>
	<div class="plate">
		<span></span>
		<input class="input-text" name="orderNo" id="orderNo"  type="text" autofocus="autofocus" placeholder="缴费通知单号">
	</div>
	<div class="btn-box">
		<input class="input-btn not" type="button" value="查 询" onClick="return doQuery();">
	</div>
<form action="#" method="POST" id="form2">
    <input name="openId" id="openId" value="${openId}" type="hidden"/>
    <input name="customerLogo" id="customerLogo" value="${customerLogo}" type="hidden"/>
	<input name="customerNumber" id="customerNumber" type="hidden"/>
	<input name="customerName" id="customerName" type="hidden"/>
	<input name="receiverName" id="receiverName" type="hidden"/>
	<input name="receiverTel" id="receiverTel" type="hidden"/>
	<input name="payStatus" id="payStatus" type="hidden"/>
	<input name="transAmt" id="transAmt" type="hidden"/>
	<input name="customerRequestId" id="customerRequestId" type="hidden"/>
	<input name="orderSign" id="orderSign" type="hidden"/>
	<input name="customerKey" id="customerKey" type="hidden"/>
	<input name="custShotName" id="custShotName" type="hidden"/>
</form>
</div>
<footer>本支付服务由易宝支付(yeepay.com)提供</footer>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/common.js"></script>
</body>
</html>