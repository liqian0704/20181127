<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="format-detection" content="telephone=no"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/layer.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/layer.m.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/tools.js"></script>
<title>扫码支付</title>
<script type="text/javascript">


</script>
</head>
<body>
<div class="box">
	<div class="company-name">
		<img src="${customer.customerLogo }">
		<p>${customer.customerName}</p>
	</div>
	<div class="num">
		<h4><span>￥</span>${order.trxAmt }</h4>
		<p>缴费成功</p>
	</div>
	<ul class="info-list">
		<li>
			<div>商<span class="space">品</span></div>
			<p>${customer.customerName}-${memo }缴费</p>
		</li>
		<li>
			<div>姓<span class="space">名</span></div>
			<p>${order.receiverName }</p>
		</li>
		<li>
			<div class="letter">交易时间</div>
			<p>${createTime }</p>
		</li>
		<li>
			<div>支付流水号</div>
			<p>${order.externalId }</p>
		</li>
		<li>
			<div class="letter">订单编号</div>
			<p>${order.customerOrderId }</p>
		</li>
	</ul>
</div>
<footer>本支付服务由易宝支付(yeepay.com)提供</footer>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/common.js"></script>
</body>
</html>