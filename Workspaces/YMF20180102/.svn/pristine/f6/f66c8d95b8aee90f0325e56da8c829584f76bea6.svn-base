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
        <div class="registertitle">易码付注册申请</div>
        <div class="codeshow">
            <p class="codeshow-p">二维码的展示</p>
            <div class="codeshow-img">
                <img src="${qrCode}" alt="" />
            </div>
        </div>
         <div class="form-submit">
              <input type="button" class="conquer" id="conquer" value="保存"/>
         </div>
</body>
<script>
  $(document).ready(function(){
	  $("#conquer").click(function(){
		 location.href="${pageContext.request.contextPath}/sales/show/${qrId}"; 
	  });
  });
 </script>
</html>