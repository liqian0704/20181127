<%--
  Created by IntelliJ IDEA.
  User: yp-tc-m-2889
  Date: 17/1/18
  Time: 下午4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试-获取订单二维码链接</title>
</head>
<body>
<form id="form" action="${pageContext.request.contextPath }/qrPay/mockCreteQrCode">
    环境类型(local  qa product  inner):<input name="env" type="text">
    二维码id:<input name="qr" type="text">
    订单号:<input name="requestId" type="text">
    操作密码：<input name="pass" type="password">
    <input type="submit" value="提交">
</form>

</body>
</html>
