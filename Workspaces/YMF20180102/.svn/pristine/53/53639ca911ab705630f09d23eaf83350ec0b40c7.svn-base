<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="format-detection" content="telephone=no"/>
<meta name="description" content="来客收银台">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/laike/common.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.10.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/layer.m.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/tools.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/AmtUtil.js"></script>
<title>来客</title>
<script type="text/javascript">

	$(document).ready(function(){
		(function(){
			var isWrchart  = isWeixin();
			if(isWrchart){
				$('.pay-wrapper').css("border-color","#1BAC19");
				$('.submit').css("background-color","#1BAC19");
			}else{
				$('.pay-wrapper').css("border-color","#148DE5");
				$('.submit').css("background-color","#148DE5");
			}
		})();
	});


function isWeixin(){
    var userAgentString = window.navigator ? window.navigator.userAgent : "";
    var weixinreg = /MicroMessenger/i;
    var androidreg = /android/i;
    if (weixinreg.test(userAgentString) ) {
      return true;
    }
    var iosreg = /(iphone)|(ipod)/i;
    if (iosreg.test(userAgentString) || androidreg.test(userAgentString)) {
    }
    return false;
};

function doPay(){
    var timeOut = 150;
    var load =null;
    var payMethod = "" ;
	$('#doPayBtn').attr("disabled",true);
	$('#transAmt').val($('#amountSpan').text());
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
	<div class="merchart-info">
		<span><img src="${customerLogo}" alt="商家logo" class="logo"></span>
		<span id="merchartName">${customerName}</span>
	</div>
	<form method="POST" id="payForm">
		<div class="pay-wrapper">
			<div class="title">消费金额：</div>
			<span class="amount focus" keyboard="custom"  id="amountSpan"></span>
		</div>
		<input type="text" placeholder="备注" class="note" name="remark" id="remark" maxlength="40">
		<button class="submit input-btn" type="button" value="去支付" id="doPayBtn" onClick="return doPay();">确认支付</button>
		<div class="logo-group"><img src="${pageContext.request.contextPath }/static/images/laike/img_13.png"></div>
		<input name="openId" id="openId" value="${openId}" type="hidden"/>
		<input name="qrCode" id="qrCode" value="${qrCode}" type="hidden"/>
		<input name="transAmt" id="transAmt" type="hidden"/>
		<input name="customerName" id="customerName" type="hidden" value="${customerName }"/>
		<input name="customerNumber" id="customerNumber" type="hidden" value="${customerNumber }"/>
		<input name="customerKey" id="customerKey" type="hidden" value="${customerKey }"/>
		<input name="custShotName" id="custShotName" type="hidden" value="${custShotName }"/>
	</form>

</body>
<script>
	(function (exports) {
		/*
		 * 模拟键盘
		 * ying.xia@yeepay.com
		 * */
		var dom = exports.document;
		var activeEle = dom.querySelector(".amount");//存储当前活动元素
		dom.body.addEventListener('touchstart', function () {
		}, false);
		/******************生成键盘*******************/
		function keyboard() {
			this.version = "1.0.0";
			this.key = '<div id="keyboard">' +
					'    <div class="row">' +
					'        <span class="key" data-which="49">1</span>' +
					'        <span class="key" data-which="50">2</span>' +
					'        <span class="key" data-which="51">3</span>' +
					'    </div>' +
					'    <div class="row">' +
					'        <span class="key" data-which="52">4</span>' +
					'        <span class="key" data-which="53">5</span>' +
					'        <span class="key" data-which="54">6</span>' +
					'    </div>' +
					'    <div class="row">' +
					'        <span class="key" data-which="55">7</span>' +
					'        <span class="key" data-which="56">8</span>' +
					'        <span class="key" data-which="57">9</span>' +
					'    </div>' +
					'    <div class="row">' +
					'        <span class="key" data-which="190">.</span>' +
					'        <span class="key" data-which="48">0</span>' +
					'        <span class="key delete" data-which="8"></span>' +
					'    </div>' +
					'</div>';

		}

		keyboard.prototype.init = function () {
			//初始化键盘插入DOM
			dom.body.insertAdjacentHTML("beforeEnd", this.key);
			this.self = dom.querySelector("#keyboard");
			this.self.addEventListener("touchstart", function () {
				event.preventDefault();
				event.stopPropagation();
				var target = event.target;
				if (target.dataset.which == 8) {
					var last = activeEle.textContent.split("").pop();
					activeEle.textContent = activeEle.textContent.replace(new RegExp(last + "$"), "");
					if (!activeEle.textContent.length) dom.querySelector(".submit").disabled = true;
					return;
				}
				if ((target.dataset.which != 190 && activeEle.textContent.length >= 5 && activeEle.textContent.indexOf(".") == -1) || (activeEle.textContent.length >= 5 && target.dataset.which != 190 && activeEle.textContent.substr(activeEle.textContent.length - 1, 1) != "." && activeEle.textContent.substr(activeEle.textContent.indexOf(".")).length > 2) || (target.dataset.which == 190 && activeEle.textContent.indexOf(".") != -1) || (target.dataset.which == 190 && !activeEle.textContent.length) || (activeEle.textContent.indexOf(".") != -1 && activeEle.textContent.substr(activeEle.textContent.indexOf(".")).length >= 3)) {
					return;
				}
				if (target.classList.contains("key") && target.dataset.which != 8) {
					activeEle.textContent += target.textContent;
					dom.querySelector(".submit").disabled = false;
				}
			}, false);
		};
		keyboard.prototype.popup = function () {
			//调起键盘
			this.self.classList.add("popup");
		};
		keyboard.prototype.hide = function () {
			//隐藏键盘
			this.self.classList.remove("popup");
		};
		exports.Keyboard = new keyboard();
		Keyboard.init();

		/******************调起自定义键盘事件绑定*******************/
		var merchartName = dom.querySelector("#merchartName").textContent;
		if (merchartName.length > 16) {
			dom.querySelector("#merchartName").textContent = merchartName.substr(0, 16) + "...";
		}
		dom.querySelector(".pay-wrapper").addEventListener("click", function () {
			dom.querySelector(".amount").classList.add("focus");
			Keyboard.popup();
		}, false);
		dom.querySelector(".note").addEventListener("focus", function () {
			dom.querySelector(".amount").classList.remove("focus");
			Keyboard.hide();
		}, false);
		Keyboard.popup();
	})(window);
</script>
</html>