<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>二维码展示</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="format-detection" content="telephone=no">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weui2.css">
  <script src="${pageContext.request.contextPath}/static/js/zepto.min.js"></script>
</head>
<body>
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">注册成功</h2>
            <p class="weui-msg__desc"> <img src="${qrCode}" alt="" /></p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="${pageContext.request.contextPath}/sales/show/${qrId}" class="weui-btn weui-btn_primary">查看详情</a>
            </p>
        </div>
    </div>
</body>
</html>