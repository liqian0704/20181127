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
<title>易码付</title>
<script type="text/javascript">


</script>
</head>
<body>
<div class="box">
<div class="logo" id="logo"><img src="${pageContext.request.contextPath }/static/images/ymf.png"></div>
	<div class="title">易码付-提示信息：</div>
	<div class="title">${msg }</div>
</div>
<footer>本支付服务由易宝支付(yeepay.com)提供</footer>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/common.js"></script>
</body>
</html>