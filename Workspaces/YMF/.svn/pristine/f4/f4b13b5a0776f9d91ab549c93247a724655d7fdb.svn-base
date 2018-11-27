<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0042)http://weixin.yoby123.cn/weui/c/form2.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>易码付代理人注册申请</title>
 <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weui.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weui2.css">
  <script src="${pageContext.request.contextPath}/static/js/zepto.min.js"></script>
  <script language="javascript">
  	function isEmpty(variable){
  		return (variable == null || variable == undefined || variable == '');
  	}
  	function validateForm(){
  		var tel = /^(1+\d{10})$/; 
  		if(isEmpty($("#groupId").val())){
  			showError("请选择公司");
  			return false;
  		}
  		if(isEmpty($("#customerId").val())){
  			showError("请选择分支机构");
  			return false;
  		}
  		if(isEmpty($("#userName").val())){
  			//$("#userName").parent().parent().addClass("weui-cell_warn");
  		    $("#userName").focus();
  			showError("请填写用户姓名");
  			return false;
  		}
  		if(!tel.test($("#mobile").val())){
  		    $("#mobile").focus();
  			showError("请填写正确的手机号码");
  			return false;
  		}
  		if(isEmpty($("#bankNo").val())){
  		    $("#bankNo").focus();
  			showError("请填写银行卡号");
  			return false;
  		}
  		if(isEmpty($("#bankName").val())){
  		    $("#bankName").focus();
  			showError("请填写银行名称");
  			return false;
  		}
  		return true;
  	}
  	function showError(msg){
  		$('.js_tooltips').html(msg);
  		if ($('.js_tooltips').css('display') != 'none') return;
        $('.js_tooltips').css('display', 'block');
        setTimeout(function () {
            $('.js_tooltips').css('display', 'none');
        },2000);
  	}
  	$(function(){
  		$("#submitButton").click(function(){
            if(validateForm()){
            	$.ajax({
	  				url:"${pageContext.request.contextPath}/sales/save",
	  				data:$('#form1').serialize(),
	  				dataType:"json",
	  				type:"POST",
	  				beforeSend:function(){
	  					$('#loadingToast').fadeIn(100);
						$('#loadingToastMsg').html("正在提交...");
	  				},
	  				success:function(data,status){
	  					$('#loadingToast').fadeOut(100);
	  					if(data.result=="success"){
	  						location.href="${pageContext.request.contextPath}/sales/qrcode/"+data.message;
	  					}else{
	  						showError(data.message);
	  					}
	  				}
	  			});
            }
  		});
  		$("#groupId").change(function(){
		  if(this.value!=null&&this.value!=""){
			  $.ajax({
					url:"${pageContext.request.contextPath}/sales/customers/"+this.value,
					dataType:"json",
					beforeSend:function(){
						//$('#loadingToast').css('opacity:1');
						$('#loadingToast').fadeIn(100);
						$('#loadingToastMsg').html("加载分支机构...");						
					},
					success:function(data,status){
						$('#loadingToast').fadeOut(100);
						$("#customerId").html("<option value=\"\">请选择</option>");
						$.each(data,function(index,obj){
							$("#customerId").append("<option value='"+obj.id+"'>"+obj.customerName+"</option>");
							//console.log(obj.id+"\t"+obj.customerName);
						})
					}
				});
		  }
	  });
  	});
  </script>
</head>

<body ontouchstart="">
<form id="form1" name="form1">
	<div class="weui-toptips weui-toptips_warn js_tooltips"></div>
	<div class="weui-cells__title">易码付代理人注册申请</div>
	<div class="weui-cells weui-cells_form">
       
         <div class="weui-cell weui-cell_select weui-cell_select-after">
             <div class="weui-cell__hd">
                 <label for="" class="weui-label">公司名称<span style="color:red">*</span></label>
             </div>
             <div class="weui-cell__bd">
                 <select class="weui-select" name="groupId" id="groupId">
                     <option value="">请选择</option>
                        <c:forEach items="${groupList}" var="group">  
                       	<option value="${group.id}">${group.groupName}</option>
                       	</c:forEach>
                 </select>
             </div>
        </div>
         <div class="weui-cell weui-cell_select weui-cell_select-after">
             <div class="weui-cell__hd">
                 <label for="" class="weui-label">分支机构<span style="color:red">*</span></label>
             </div>
             <div class="weui-cell__bd">
                 <select class="weui-select" name="customerId" id="customerId">
                     <option value="">请选择</option>
                 </select>
             </div>
        </div>
       <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">姓名<span style="color:red">*</span></label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入姓名" maxlength="10" name="userName" id="userName"/>
            </div>
        </div>
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">手机号<span style="color:red">*</span></label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="tel" placeholder="请输入手机号" maxlength="11" name="mobile" id="mobile"/>
            </div>
        </div>
      
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">工号</label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入工号" maxlength="30" name="userNo" id="userNo"/>
            </div>
        </div>
       
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">微信号</label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入微信号" maxlength="30" name="wxName" id="wxName"/>
            </div>
        </div>
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">邮箱</label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="email" placeholder="请输入邮箱" maxlength="50" name="email" id="email" />
            </div>
        </div>
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">银行卡号<span style="color:red">*</span></label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="number" maxlength="30" placeholder="请输入银行卡号" name="bankNo" id="bankNo"/>
            </div>
        </div>
        <div class="weui-cell">
       		<div class="weui-cell__hd">
                 <label for="" class="weui-label">银行名称<span style="color:red">*</span></label>
             </div>
            <div class="weui-cell__bd">
                <input class="weui-input" type="text" placeholder="请输入银行名称" maxlength="30" name="bankName" id="bankName"/>
            </div>
        </div>
       
        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="javascript:;" id="submitButton">确定</a>
        </div>
 	</div>
 	
 	<div id="loadingToast" style="opacity: 0; display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content" id="loadingToastMsg"></p>
        </div>
    </div>
 </form>
</body>
</html>