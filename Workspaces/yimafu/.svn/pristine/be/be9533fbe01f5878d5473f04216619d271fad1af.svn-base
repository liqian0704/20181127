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
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/layer.m.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/tools.js"></script>
<title>易码付-标准版</title>
<script type="text/javascript">
function isWeixin(){
    var userAgentString = window.navigator ? window.navigator.userAgent : "";
    var weixinreg = /MicroMessenger/i;
    var androidreg = /android/i;
    if (!weixinreg.test(userAgentString) ) {
      return true;
    }
    var iosreg = /(iphone)|(ipod)/i;
    if (iosreg.test(userAgentString) || androidreg.test(userAgentString)) {
    }
    return false;
};

function checkAmt(){
    var _this = $("#transAmt");
    var value = _this.val() ;
    if(/^\d+(\.\d{1,2})?$/g.test(value)){
        value = Number(value) * Number(100);
        if(value > Number(99999999)){
        	_this.val('0');
        	Tools.alert("金额太大，请重新输入！");
            return false;
        } else if (value == 0) {
        	_this.val('0');
        	Tools.alert("金额不合法，请重新输入！");
            return false;
        }
        return true;
    } else {
    	_this.val('0');
    	Tools.alert("金额不合法，请重新输入！");
        return false;
    }
}

function doPay(){
    var timeOut = 150;
    var load =null;
	var canNotDo = document.querySelector(".input-btn").classList.contains("not") ;
	if (canNotDo) {
		return false ;
	}
    if (!checkAmt()) {
    	return false ;
    }
	
    var payMethod = "" ;
    var childs = document.querySelectorAll(".pm-child");
    for(var i=0, ln=childs.length; i<ln; i++){
		if (childs[i].querySelector(".icon-yes").classList.contains("on")) {
			payMethod = childs[i].id ;
		}        
    }
    var url = "" ;
    	url = "${pageContext.request.contextPath }/standard/doPay" ;
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
<div class="box">

<div class="logo" id="logo"><img src="${customerLogo }"></div>
<div class="title">付款给<br/><br/>${customerName }</div>
<form action="" method="POST" id="payForm">
<div class="money">
	<label for="money">金额</label>
	<input type="number" class="input-money" id="transAmt" name="transAmt" autofocus placeholder="输入金额">
	<span>元</span>
</div>
<div class="btn-box">
	<input class="input-btn not" type="button" value="去支付" onClick="return doPay();">
</div>

<input name="openId" id="openId" value="${openId}" type="hidden"/>
<input name="qrCode" id="qrCode" value="${qrCode}" type="hidden"/>
<input name="customerName" id="customerName" type="hidden" value="${customerName }"/>
<input name="customerNumber" id="customerNumber" type="hidden" value="${customerNumber }"/>
<input name="customerKey" id="customerKey" type="hidden" value="${customerKey }"/>
<input name="custShotName" id="custShotName" type="hidden" value="${custShotName }"/>
</form>

<footer>本支付服务由易宝支付(yeepay.com)提供</footer>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/common.js"></script>
</body>
</html>