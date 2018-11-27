<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>二维码展示</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="format-detection" content="telephone=no">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/common.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/layer.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.10.1.min.js"></script>
</head>
<body>
      <div class="twocodetitle twocodetitlept">${groupName}</div>
      <div class="twocodetitle">${customerName}</div>
      <div class="codepay">
            <div class="item"><i class="iconfont zhifu" style="color: green;">&#xe61f;</i><div>微信支付</div></div>
            <div class="item"><i class="iconfont zhifu" style="color: blue;">&#xe63c;</i><div>支付宝</div></div>
            <div class="item"><i class="iconfont zhifu" style="color: green;">&#xe624;</i><div>一键支付</div></div>

      </div>
      <div class="codeshow">
            <div class="codeshow-img">
                <img src="${qrCode}" alt="" />
            </div>
      </div>
      <div class="codepay">
            <div class="item"><i class="iconfont samao">&#xe631;</i><div>1.扫描二维码</div></div>
            <div class="item"><i class="iconfont samao">&#xe610;</i><div>2.查询保单</div></div>
            <div class="item"><i class="iconfont samao">&#xe6e1;</i><div>3.进行支付</div></div>

      </div>

</body>
</html>